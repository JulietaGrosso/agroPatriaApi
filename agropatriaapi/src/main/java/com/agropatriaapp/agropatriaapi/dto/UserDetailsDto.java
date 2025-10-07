package com.agropatriaapp.agropatriaapi.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.agropatriaapp.agropatriaapi.model.Cuentas;

import lombok.Getter;
import lombok.Setter;

public class UserDetailsDto extends User{
  @Getter
  @Setter
  Cuentas usuario;
  public UserDetailsDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }
  
  public UserDetailsDto(String username, String password, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
  }


}
