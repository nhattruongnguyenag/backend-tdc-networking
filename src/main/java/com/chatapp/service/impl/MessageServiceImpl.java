package com.chatapp.service.impl;

import com.chatapp.constant.MessageStatus;
import com.chatapp.converter.request.message.MessageRequestConverter;
import com.chatapp.converter.response.message.MessageResponseConverter;
import com.chatapp.dto.Pagination;
import com.chatapp.dto.request.message.MessageSaveRequestDTO;
import com.chatapp.dto.response.message.MessageResponseDTO;
import com.chatapp.entity.ConversationEntity;
import com.chatapp.entity.MessageEntity;
import com.chatapp.entity.UserEntity;
import com.chatapp.repository.*;
import com.chatapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRequestConverter messageRequestConverter;
    @Autowired
    private MessageResponseConverter messageResponseConverter;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private CustomizedMessageRepository customizedMessageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomizedConversationRepository customizedConversationRepository;
    @Autowired
    private ConversationRepository conversationRepository;


    @Override
    public List<MessageResponseDTO> findBySenderAndReceiver(Long senderId, Long receiverId) {
        if (userRepository.findById(senderId).isPresent()
                && userRepository.findById(receiverId).isPresent()) {
            List<MessageEntity> messageEntities = customizedMessageRepository.findBySenderOrReceiver(senderId, receiverId);
            List<MessageEntity> messagesUpdateToRead = messageEntities.stream().filter(messageEntity -> messageEntity.getSender().getId() == receiverId).toList();
            updateMessageToReadState(messagesUpdateToRead);
            return messageResponseConverter.toDTOGroup(messageEntities);
        }

        throw new RuntimeException("user_does_not_exists");
    }

    @Override
    public List<MessageResponseDTO> findBySenderAndReceiver(Long senderId, Long receiverId, Pagination pagination) {
        if (userRepository.findById(senderId).isPresent()
                && userRepository.findById(receiverId).isPresent()) {
            List<MessageEntity> messageEntities = customizedMessageRepository.findBySenderOrReceiver(senderId, receiverId);
            List<MessageEntity> messagesUpdateToRead = messageEntities.stream().filter(messageEntity -> messageEntity.getSender().getId() == receiverId).toList();
            updateMessageToReadState(messagesUpdateToRead);
            return messageResponseConverter.toDTOGroup(messageEntities);
        }

        throw new RuntimeException("user_does_not_exists");
    }

    @Override
    public List<MessageResponseDTO> findByConversations_Id(Long conversationId) {
        Optional<ConversationEntity> conversationEntity = conversationRepository.findById(conversationId);
        if (conversationEntity.isPresent()) {
            List<MessageEntity> messageEntities = messageRepository.findByConversations_Id(conversationId);
            return messageResponseConverter.toDTOGroup(messageEntities);
        }
        throw new RuntimeException("conversation_not_exists");
    }

    private List<MessageResponseDTO> updateMessageToReadState(List<MessageEntity> messageEntities) {
        for (MessageEntity message : messageEntities) {
                message.setStatus(MessageStatus.SEEN);
        }

        return messageResponseConverter.toDTOGroup(messageRepository.saveAll(messageEntities));
    }

    @Override
    public List<MessageResponseDTO> updateMessagesToReadState(Long senderId, Long receiverId) {
        List<MessageEntity> messages = customizedMessageRepository.findBySenderOrReceiver(senderId, receiverId);

        for (MessageEntity message : messages) {
            if (message.getSender().getId() == receiverId && message.getReceiver().getId() == senderId) {
                message.setStatus(MessageStatus.SEEN);
            }
        }

        return messageResponseConverter.toDTOGroup(messageRepository.saveAll(messages));
    }

    @Override
    public MessageResponseDTO save(MessageSaveRequestDTO messageRequestDTO) {
        final Optional<UserEntity> sender = userRepository.findById(messageRequestDTO.getSenderId());
        final Optional<UserEntity> receiver = userRepository.findById(messageRequestDTO.getReceiverId());

        if (sender.isPresent() && receiver.isPresent()) {
            MessageEntity messageEntity = messageRepository.save(messageRequestConverter.toEntity(messageRequestDTO));
            List<ConversationEntity> conversationEntities = customizedConversationRepository.findBySenderAndReceiver(sender.get().getId(), receiver.get().getId());

            if (conversationEntities.size() == 0) {
                conversationEntities.add(createConversationEntity(sender, receiver, messageEntity));
                conversationEntities.add(createConversationEntity(receiver, sender, messageEntity));
            } else if (conversationEntities.size() == 1) {
                conversationEntities.get(0).getMessages().add(messageEntity);
                if (conversationEntities.get(0).getSender().getId() == sender.get().getId()) {
                    conversationEntities.add(createConversationEntity(receiver, sender, messageEntity));
                } else {
                    conversationEntities.add(createConversationEntity(sender, receiver, messageEntity));
                }
            } else {
                for (ConversationEntity conversationEntity : conversationEntities) {
                    conversationEntity.getMessages().add(messageEntity);
                }
            }

            conversationRepository.saveAll(conversationEntities);

            return messageResponseConverter.toDTO(messageEntity);
        }

        throw new RuntimeException("user_not_exists");
    }

    private static ConversationEntity createConversationEntity(Optional<UserEntity> sender, Optional<UserEntity> receiver, MessageEntity messageEntity) {
        ConversationEntity senderConversation = new ConversationEntity();
        senderConversation.setSender(sender.get());
        senderConversation.setReceiver(receiver.get());
        senderConversation.getMessages().add(messageEntity);
        return senderConversation;
    }

    @Override
    public MessageResponseDTO delete(Long messageId) {
        Optional<MessageEntity> messageEntity = messageRepository.findById(messageId);

        if (messageEntity.isPresent()) {
            MessageResponseDTO messageDTO = messageResponseConverter.toDTO(messageEntity.get());
            messageRepository.deleteById(messageId);
            return messageDTO;
        }

        throw new RuntimeException("message_not_exists");
    }
}
