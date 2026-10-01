package com.msb.embeddedbanking.enums.role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");
    private final String value;

    public static RoleStatus fromValue(String value) {
        for (RoleStatus rs : RoleStatus.values()) {
            if (rs.getValue().equalsIgnoreCase(value)) {
                return rs;
            }
        }
        throw new IllegalArgumentException("Unknown RoleStatus: " + value);
    }

}
