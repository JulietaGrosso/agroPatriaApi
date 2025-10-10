package com.agropatriaapp.agropatriaapi.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.VendedorDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Pagos;
import com.agropatriaapp.agropatriaapi.model.Planes;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Vendedor;
import com.agropatriaapp.agropatriaapi.repositorios.PagoRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.PlanRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.VendedorRepositorio;
import com.agropatriaapp.agropatriaapi.utils.Utils;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

@Service
public class VendedorService {

    private final PagoRepositorio pagoRepositorio;

    @Autowired
    VendedorRepositorio vendedorRepositorio;
    @Autowired
    private PlanRepositorio planRepositorio;
    @Autowired
    MercadoPagoService mercadoPagoService;

    VendedorService(PagoRepositorio pagoRepositorio) {
        this.pagoRepositorio = pagoRepositorio;
    }

    public Vendedor getVendedor(int idCuentas) throws NotFoundEntityException{
     Optional<Vendedor> vendedorOp = vendedorRepositorio.findById(idCuentas);
     if(vendedorOp.isPresent()){
      return vendedorOp.get();
     }
     throw new NotFoundEntityException("Vendedor no encontrado");
    }

    public List<Map<String, Object>> getVendedorsConfiaron() {
      return vendedorRepositorio.getVendedoresConfiaron();
    }

   public Response deleteVendedor(int idCuentas){
        vendedorRepositorio.deleteById(idCuentas);
        return new Response(true, "Usuario eliminado correctamente");
   }

   public Response postVendedor(VendedorDto vendedorDto){
    Vendedor vendedor = new Vendedor();
    int userId = Utils.getAuthenticatedUserId();
    vendedor.setIdCuentas(userId);
    vendedor.setNombre(vendedorDto.getNombre());
    vendedor.setRazon(vendedorDto.getRazon());
    vendedor.setCuitVendedor(vendedorDto.getCuitVendedor());
    vendedor.setLocalidadVendedor(vendedorDto.getLocalidadVendedor());
    vendedor.setLogo(vendedorDto.getLogo());
    vendedor.setPlanesIdPlan(vendedorDto.getPlanesIdPlan());
    vendedor.setProvinciaVendedor(vendedorDto.getProvinciaVendedor());
    vendedor.setContacto(vendedorDto.getContacto());
    vendedorRepositorio.save(vendedor);
    return new Response(true, "Agregado correctamente");
   }

   public Response putVendedor(int idCuentas, VendedorDto vendedorDto) throws NotFoundEntityException{
     Optional<Vendedor> vendedorOp = vendedorRepositorio.findById(idCuentas);
     if(vendedorOp.isPresent()){
          Vendedor vendedor = vendedorOp.get();
          vendedor.setNombre(vendedorDto.getNombre());
          vendedor.setRazon(vendedorDto.getRazon());
          vendedor.setCuitVendedor(vendedorDto.getCuitVendedor());
          vendedor.setLocalidadVendedor(vendedorDto.getLocalidadVendedor());
          vendedor.setProvinciaVendedor(vendedorDto.getProvinciaVendedor());
          vendedor.setLogo(vendedorDto.getLogo());
          vendedor.setPlanesIdPlan(vendedorDto.getPlanesIdPlan());;
          vendedor.setContacto(vendedorDto.getContacto());
          vendedorRepositorio.save(vendedor);
          return new Response(true, "Se actualizó correctamente");
     }
     throw new NotFoundEntityException("No se encontró el usuario");
   }

   public Response postPago(int idPlan) throws MPException, MPApiException, NotFoundEntityException{
      Optional<Planes> planesOp = planRepositorio.findById(idPlan);
      if(planesOp.isPresent()){
        Planes planes = planesOp.get();
        int userId = Utils.getAuthenticatedUserId();
        String paymentUuid = UUID.randomUUID().toString();
        String paymentUrl = mercadoPagoService.generatePaymentUrl(paymentUuid, planes.getTitulo(), planes.getPrecio());
        
        Pagos pago = new Pagos();
        pago.setIdCuenta(userId);
        pago.setIdPlan(planes.getIdPlan());
        pago.setMonto(planes.getPrecio());
        pago.setUuidPago(paymentUuid);
        pago.setDuracion(planes.getDuracionDias());
        pagoRepositorio.save(pago);

        return new Response(true, paymentUrl);

      }
      throw new NotFoundEntityException("No se encontró el plan");
   }

}
