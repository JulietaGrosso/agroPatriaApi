package com.agropatriaapp.agropatriaapi.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

  public static Date agregarDias(Date fecha, int dias) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(Calendar.DAY_OF_MONTH, dias);
    return cal.getTime();
  }

}
