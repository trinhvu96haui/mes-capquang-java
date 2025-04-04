package com.techie.microservices.menu.Enums;

import lombok.Getter;

@Getter
public enum GroupProcessType {
    Production(1),
    OCC(2),
    KCS(3);

    private final int value;

    GroupProcessType(int value) {
        this.value = value;
    }

}
