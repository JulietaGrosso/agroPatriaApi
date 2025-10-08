package com.agropatriaapp.agropatriaapi.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Utils {

  public static int getAuthenticatedUserId() {
    Authentication auto = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) auto.getPrincipal();
    int userId = Integer.valueOf(user.getUsername());
    return userId;
  }

  public static Date agregarDias(Date fecha, int dias) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(Calendar.DAY_OF_MONTH, dias);
    return cal.getTime();
  }

}
