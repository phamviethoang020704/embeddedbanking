package com.msb.embeddedbanking.enums;

import com.msb.embeddedbanking.enums.role.RoleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");
    private String value;

    public static MenuStatus fromValue(String value) {
        for (MenuStatus ms : MenuStatus.values()) {
            if (ms.value.equalsIgnoreCase(value)) {
                return ms;
            }
        }
        throw new IllegalArgumentException("Unknown MenuStatus: " + value);
    }
}
