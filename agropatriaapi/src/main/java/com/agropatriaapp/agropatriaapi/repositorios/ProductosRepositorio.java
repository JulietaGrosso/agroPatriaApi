package com.agropatriaapp.agropatriaapi.repositorios;

import java.util.List;
import java.util.Map;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Productos;

@Repository
public interface ProductosRepositorio extends JpaRepository<Productos,Integer>{

    @Query(
        value = "select *, (select count(*) from publicacion where productos_id = p.id) as cantidadPublicaciones from productos p",
        nativeQuery= true
     )

    List<Map<String, Object>> buscarProductosDePublicaciones();


} 