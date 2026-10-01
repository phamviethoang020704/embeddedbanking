package com.msb.embeddedbanking.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@UtilityClass
public class TokenUtils {
    public String getJwtFromRequest(HttpServletRequest request){
        String jwt = request.getHeader("Authorization");
        if (StringUtils.hasText(jwt) && jwt.startsWith("Bearer ")){
            return jwt.substring(7);
        }
        return null;
    }
}
