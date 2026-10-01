package com.msb.embeddedbanking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenType {
    ACCESS("ACCESS"),
    REFRESH("REFRESH");
    private String value;
}
