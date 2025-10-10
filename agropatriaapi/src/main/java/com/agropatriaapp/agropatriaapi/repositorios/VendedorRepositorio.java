package com.agropatriaapp.agropatriaapi.repositorios;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Vendedor;

@Repository
public interface VendedorRepositorio extends JpaRepository<Vendedor, Integer> {

    @Query(
        value = "select * from (" +
        "select *, (select count(*) from publicacion p where p.vendedor_cuentas_id = V.id_cuentas ) cantidadProductos " +
        "from vendedor V" +
        ") VL where VL.cantidadproductos > 0",
        nativeQuery = true
      )
    List<Map<String, Object>> getVendedoresConfiaron();
} 
