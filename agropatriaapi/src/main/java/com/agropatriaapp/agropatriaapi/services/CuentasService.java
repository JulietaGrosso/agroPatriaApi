package com.agropatriaapp.agropatriaapi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.model.Cuentas;
@Service
public class CuentasService {
    private List<Cuentas>cuentas = new ArrayList<>(Arrays.asList(
   new Cuentas(1, "dfbhdfjdff", "holamundoxd")


));

public List<Cuentas> getCuentas(){
    return cuentas;
}


}
