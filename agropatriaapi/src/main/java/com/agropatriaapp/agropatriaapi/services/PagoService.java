package com.agropatriaapp.agropatriaapi.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.model.Pagos;
import com.agropatriaapp.agropatriaapi.repositorios.PagoRepositorio;


@Service
public class PagoService {

    @Autowired
    private PagoRepositorio pagoRepositorio;

     public Pagos getLastPayment(int userId){
        Pagos pago = pagoRepositorio.findLastPagoByIdCuenta(userId);
        return pago;
     }

     public List<Map<String, Object>> getHistoricoPagos(int mes, int anio){
        List<Map<String, Object>> pagos = pagoRepositorio.getHistoricoPagos(mes, anio);
        return pagos;
     }




}
