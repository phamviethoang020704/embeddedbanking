package com.msb.embeddedbanking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");
    private String value;
    public static PermissionStatus fromValue(String value) {
        for (PermissionStatus permissionStatus : PermissionStatus.values()) {
            if(permissionStatus.value.equalsIgnoreCase(value)) {
                    return permissionStatus;
            }
        }
        throw new IllegalArgumentException("Unknown PermissionStatus: " + value);
    }
}
