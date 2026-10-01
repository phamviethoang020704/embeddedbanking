package com.msb.embeddedbanking.controller.response.menu;

import com.msb.embeddedbanking.enums.MenuStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MenuResponse {
    private Long id;
    private String code;
    private MenuResponse parent;
//    private String name;
//    private String path;
//    private String icon;
//    private Integer sortOrder;
//    private MenuStatus status;
}
