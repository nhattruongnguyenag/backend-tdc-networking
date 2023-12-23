package com.chatapp.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.commond.ResponseData;
import com.chatapp.dto.request.option.OptionRequestDTO;
import com.chatapp.dto.response.option.OptionResponseDTO;
import com.chatapp.enums.Option;
import com.chatapp.service.OptionService;

@RestController
@RequestMapping("/api")
public class OptionAPI {
    @Autowired
    private OptionService optionService;

    //////////////////
    //Post
    //////////////////
    @PostMapping({"option/language", "option/language/"})
    public ResponseEntity<ResponseData<String>> saveOrUpdateLanguage(@RequestBody OptionRequestDTO optionRequestDTO) {
        optionRequestDTO.setOptionKey(Option.LANGUAGE.getValue());
        ResponseData<String> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",optionService.saveOrUpdateLanguage(optionRequestDTO));
        return ResponseEntity.created(null).body(responseData);
    }
    
    @PostMapping({"option/get", "option/get/"})
    public ResponseEntity<ResponseData<OptionResponseDTO>> getOptionByUserAndOptionKey(@RequestBody OptionRequestDTO optionRequestDTO) {
        ResponseData<OptionResponseDTO> responseData = new ResponseData<>(HttpStatus.OK, "sucesss",optionService.getOptionByKeyAndUserId(optionRequestDTO));
        return ResponseEntity.ok(responseData);
    }
}
