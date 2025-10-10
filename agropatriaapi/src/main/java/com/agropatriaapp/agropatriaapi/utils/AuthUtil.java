package com.agropatriaapp.agropatriaapi.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUtil {
  public static int getAuthenticatedUserId() {
    Authentication auto = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) auto.getPrincipal();
    int userId = Integer.valueOf(user.getUsername());
    return userId;
  }

  public static boolean isAdmin(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMINISTRADOR"));
  }
}
