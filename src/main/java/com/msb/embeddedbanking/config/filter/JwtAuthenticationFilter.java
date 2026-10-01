package com.msb.embeddedbanking.config.filter;

import com.msb.embeddedbanking.enums.PermissionStatus;
import com.msb.embeddedbanking.repository.PermissionRepository;
import com.msb.embeddedbanking.repository.RolePermissionRepository;
import com.msb.embeddedbanking.repository.UserRepository;
import com.msb.embeddedbanking.repository.entity.Permission;
import com.msb.embeddedbanking.repository.entity.RolePermission;
import com.msb.embeddedbanking.repository.entity.User;
import com.msb.embeddedbanking.util.JwtUtil;
import com.msb.embeddedbanking.util.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionRepository permissionRepository;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = TokenUtils.getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && jwtUtil.isValidToken(jwt)){
                if (!jwtUtil.isAccessToken(jwt)){
                    log.warn("token is not an access token");
                    filterChain.doFilter(request, response);
                    return;
                }

                String username = jwtUtil.getUserNameFromToken(jwt);
                Long userId = jwtUtil.getUserIdFromToken(jwt);

                User user = userRepository.findByIdAndNotDeleted(userId).orElse(null);
                if (user != null && user.getUsername().equals(username)){
                    List<GrantedAuthority> authorities = new ArrayList<>();

                    List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(user.getRoleId());
                    List<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).toList();

                    List<Permission> permissions = permissionRepository.findAllByIds(permissionIds);

                    if (!permissionIds.isEmpty() && user.getRole() != null){
                        authorities = permissions.stream()
                                .filter(p -> p.getStatus().equals(PermissionStatus.ACTIVE))
                                .map(Permission::getCode)
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
                    }

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.info("set authentication for user: {} with {} permissions", username, permissions.size());
                }
            }
        }catch (Exception e){
            log.error("could not set user authentication in security context", e);
        }
        filterChain.doFilter(request, response);
    }
}
