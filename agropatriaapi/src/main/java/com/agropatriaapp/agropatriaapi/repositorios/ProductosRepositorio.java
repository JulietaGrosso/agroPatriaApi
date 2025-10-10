package com.agropatriaapp.agropatriaapi.repositorios;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Productos;

@Repository
public interface ProductosRepositorio extends JpaRepository<Productos,Integer>, JpaSpecificationExecutor<Productos>{

    @Query(
        value = "SELECT *, " +
                "(SELECT COUNT(*) FROM publicacion WHERE productos_id = p.id) AS cantidadPublicaciones " +
                "FROM productos p " +
                "WHERE (:categoriaFiltro IS NULL OR categoria = :categoriaFiltro)" +
                "AND (:condicionFiltro IS NULL OR condicion = :condicionFiltro)",
        nativeQuery= true
     )
    List<Map<String, Object>> buscarProductosDePublicaciones(Integer categoriaFiltro, Integer condicionFiltro);

    Optional<Productos> findTop1ByNombreContainingIgnoreCase(String nombreFiltro);

} 