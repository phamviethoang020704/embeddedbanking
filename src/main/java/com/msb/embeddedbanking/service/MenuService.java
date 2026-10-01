package com.msb.embeddedbanking.service;

import com.msb.embeddedbanking.controller.response.menu.MenuResponse;
import com.msb.embeddedbanking.repository.entity.User;

import java.util.List;

public interface MenuService {
    List<MenuResponse> toMenuResponse(User user);
}
