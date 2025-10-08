package com.agropatriaapp.agropatriaapi.repositorios;


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

}


