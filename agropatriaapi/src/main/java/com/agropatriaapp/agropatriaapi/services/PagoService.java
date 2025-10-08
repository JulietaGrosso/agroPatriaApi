package com.agropatriaapp.agropatriaapi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.CuentasDto;
import com.agropatriaapp.agropatriaapi.dto.MyInfoDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Cuentas;
import com.agropatriaapp.agropatriaapi.model.Pagos;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Vendedor;
import com.agropatriaapp.agropatriaapi.repositorios.CuentasRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.PagoRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.VendedorRepositorio;
import com.agropatriaapp.agropatriaapi.utils.EncryptUtils;
import com.agropatriaapp.agropatriaapi.utils.Utils;


@Service
public class PagoService {

    @Autowired
    private PagoRepositorio pagoRepositorio;

     public Pagos getLastPayment(int userId){
        Pagos pago = pagoRepositorio.findLastPagoByIdCuenta(userId);
        return pago;
     }




}
