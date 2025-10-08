package com.agropatriaapp.agropatriaapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Response {
    @Getter
    @Setter
    private boolean success;
    @Getter
    @Setter
    private String message;

    public Response(boolean success, String message){
        this.success = success;
        this.message = message;
    }

}
