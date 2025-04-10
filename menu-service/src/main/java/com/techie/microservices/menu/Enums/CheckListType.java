package com.techie.microservices.menu.Enums;

import lombok.Getter;

@Getter
public enum CheckListType {
    NUMBER("Số"),
    PASS_FALSE("Pass false");

    private final String label;

    CheckListType(String label) {
        this.label = label;
    }
}
