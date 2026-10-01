package com.msb.embeddedbanking.service.impl;

import com.msb.embeddedbanking.controller.response.menu.MenuResponse;
import com.msb.embeddedbanking.repository.MenuRepository;
import com.msb.embeddedbanking.repository.PermissionRepository;
import com.msb.embeddedbanking.repository.RolePermissionRepository;
import com.msb.embeddedbanking.repository.entity.Menu;
import com.msb.embeddedbanking.repository.entity.Permission;
import com.msb.embeddedbanking.repository.entity.User;
import com.msb.embeddedbanking.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public List<MenuResponse> toMenuResponse(User user) {
        Long roleId = user.getRoleId();
        List<Long> permissionIds = rolePermissionRepository.findPermissionIdByRoleId(roleId);
        List<Menu> menus = permissionRepository.findMenusByPermissionIds(permissionIds);
        Map<Long, Menu> menuMap = loadMenusWithParents(menus);

        return menus.stream()
                .map(menu -> buildMenuResponse(menu, menuMap))
                .toList();
    }
    private MenuResponse buildMenuResponse(
            Menu menu,
            Map<Long, Menu> menuMap
    ) {
        MenuResponse parentResponse = null;

        if (menu.getParentId() != null) {
            Menu parent = menuMap.get(menu.getParentId());

            if (parent != null) {
                parentResponse = buildMenuResponse(parent, menuMap);
            }
        }

        return MenuResponse.builder()
                .id(menu.getId())
                .code(menu.getCode())
                .parent(parentResponse)
                .build();
    }
    private Map<Long, Menu> loadMenusWithParents(List<Menu> directMenus) {

        Map<Long, Menu> menuMap = directMenus.stream()
                .collect(Collectors.toMap(
                        Menu::getId,
                        Function.identity()
                ));

        Set<Long> parentIds = directMenus.stream()
                .map(Menu::getParentId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        while (!parentIds.isEmpty()) {

            Set<Long> idsToLoad = parentIds.stream()
                    .filter(id -> !menuMap.containsKey(id))
                    .collect(Collectors.toSet());

            if (idsToLoad.isEmpty()) {
                break;
            }

            List<Menu> parents = menuRepository.findAllById(idsToLoad);

            parents.forEach(parent ->
                    menuMap.put(parent.getId(), parent)
            );

            parentIds = parents.stream()
                    .map(Menu::getParentId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }

        return menuMap;
    }
}
