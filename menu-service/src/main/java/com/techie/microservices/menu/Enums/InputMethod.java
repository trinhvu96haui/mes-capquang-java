package com.techie.microservices.menu.Enums;

import lombok.Getter;

@Getter
public enum InputMethod {
    GIA_CONG("Gia công"),
    CHECKLIST("Checklist"),
    KIEM_TRA("Kiểm tra (sau gia công)");

    private final String label;

    InputMethod(String label) {
        this.label = label;
    }
}
