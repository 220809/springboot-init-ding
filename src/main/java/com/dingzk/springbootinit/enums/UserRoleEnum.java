package com.dingzk.springbootinit.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {

    USER(0, "user"),
    ADMIN(1, "admin");

    private final int value;
    private final String name;

    public static UserRoleEnum getEnumByValue(int value) {
        for (UserRoleEnum role : values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        return null;
    }
}