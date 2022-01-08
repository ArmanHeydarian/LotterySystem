package com.lottery.main.domain.dto;
import java.io.Serializable;

public class AuthenticatonResponse implements Serializable {

        private static final long serialVersionUID = -8091879091924046844L;
        private final String jwttoken;


    public AuthenticatonResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
            return this.jwttoken;
        }

}
