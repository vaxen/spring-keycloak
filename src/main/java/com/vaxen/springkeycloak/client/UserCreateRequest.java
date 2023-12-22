package com.vaxen.springkeycloak.client;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCreateRequest implements Serializable {
    private static final long serialVersionUID = 3L;

    private String email;
}
