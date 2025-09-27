package com.agropatriaapp.agropatriaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.VendedorDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Planes;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Vendedor;
import com.agropatriaapp.agropatriaapi.repositorios.PlanRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.VendedorRepositorio;

@Service
public class VendedorService {

    @Autowired
    VendedorRepositorio vendedorRepositorio;
    @Autowired
    private PlanRepositorio planRepositorio;

    public Vendedor getVendedor(int idCuentas) throws NotFoundEntityException{
     Optional<Vendedor> vendedorOp = vendedorRepositorio.findById(idCuentas);
     if(vendedorOp.isPresent()){
      return vendedorOp.get();
     }
     throw new NotFoundEntityException("Vendedor no encontrado");
    }


   public Response deleteVendedor(int idCuentas){
        vendedorRepositorio.deleteById(idCuentas);
        return new Response(true, "Usuario eliminado correctamente");
   }

   public Response postVendedor(VendedorDto vendedorDto){
    Vendedor vendedor = new Vendedor();
    vendedor.setNombre(vendedorDto.getNombre());
    vendedor.setRazon(vendedorDto.getRazon());
    vendedor.setCuitVendedor(vendedorDto.getCuitVendedor());
    vendedor.setLocalidadVendedor(vendedorDto.getLocalidadVendedor());
    vendedor.setLogo(vendedorDto.getLogo());
    vendedor.setPlanesIdPlan(vendedorDto.getPlanesIdPlan());
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
          vendedorRepositorio.save(vendedor);
          return new Response(true, "Se actualizó correctamente");
     }
     throw new NotFoundEntityException("No se encontró el usuario");
   }

   public Response postPago(int idPlan){
      Optional<Planes> planesOp = planRepositorio.findById(idPlan);
      if(planesOp.isPresent()){
        Planes planes = planesOp.get();
        return new Response(true, planes.getPrecio()+ "");

      }
      return new Response(true, "Pago realizado");
   }

}
