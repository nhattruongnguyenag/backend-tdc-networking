package com.chatapp.dto.response.group;

import com.chatapp.dto.BaseDTO;

public class GroupResponseDTO extends BaseDTO{
    private Byte active;
    private String background;
    private String name;
    private String code;

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
