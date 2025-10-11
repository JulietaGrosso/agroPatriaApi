package com.agropatriaapp.agropatriaapi.repositorios;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Pagos;

@Repository
public interface PagoRepositorio extends JpaRepository<Pagos, Integer>{
    @Query("SELECT p FROM pagos p " +
           "WHERE p.idCuenta = :idCuenta AND p.fechaPago IS NOT NULL " +
           "AND p.fechaExpiracion > CURRENT_TIMESTAMP " +
           "ORDER BY p.id DESC LIMIT 1")
    Pagos findLastPagoByIdCuenta(@Param("idCuenta") int idCuenta);

    Optional<Pagos> findByUuidPago(String uuidPago);

    @Query(
        value = "SELECT p.*, " +
                "pl.titulo AS nombrePlan, v.nombre AS nombreUsuario, v.cuit_vendedor as cuitVendedor " +
                "FROM pagos p " +
                "LEFT JOIN planes pl ON p.id_plan = pl.id_plan " +
                "LEFT JOIN vendedor v ON p.id_cuenta = v.id_cuentas " +
                "WHERE p.fecha_pago IS NOT NULL " +
                "AND MONTH(p.fecha_pago) = :mes " +
                "AND YEAR(p.fecha_pago) = :anio",
        nativeQuery = true
    )
    List<Map<String, Object>> getHistoricoPagos(@Param("mes") int mes, @Param("anio") int anio);


}


