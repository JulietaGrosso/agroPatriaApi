package com.agropatriaapp.agropatriaapi.repositorios;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Productos;

@Repository
public interface ProductosRepositorio extends JpaRepository<Productos,Integer>, JpaSpecificationExecutor<Productos>{

    @Query(
        value = "SELECT p.*, " +
                "(SELECT COUNT(*) FROM publicacion pub WHERE pub.productos_id = p.id) AS cantidadPublicaciones " +
                "FROM productos p " +
                "WHERE (:categoriaFiltro IS NULL OR p.categoria = :categoriaFiltro) " +
                "AND (:condicionFiltro IS NULL OR EXISTS (" +
                "    SELECT 1 FROM publicacion pub2 " +
                "    WHERE pub2.productos_id = p.id " +
                "      AND LOWER(pub2.condicion) = CASE " +
                "           WHEN :condicionFiltro = 1 THEN 'nuevo' " +
                "           WHEN :condicionFiltro = 0 THEN 'usado' " +
                "           ELSE pub2.condicion " +
                "      END" +
                "))",
        nativeQuery = true
    )
    List<Map<String, Object>> buscarProductosDePublicaciones(Integer categoriaFiltro, Integer condicionFiltro);

    Optional<Productos> findTop1ByNombreContainingIgnoreCase(String nombreFiltro);

} 