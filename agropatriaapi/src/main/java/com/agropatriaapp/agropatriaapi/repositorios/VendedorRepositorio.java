package com.agropatriaapp.agropatriaapi.repositorios;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(
        value = "SELECT v.id_cuentas AS idCuentas, v.nombre AS nombre, c.email AS email " +
                "FROM vendedor v " +
                "JOIN cuentas c ON v.id_cuentas = c.id",
        nativeQuery = true
    )
    List<Map<String, Object>> findAllVendedoresConEmail();

    @Query(value = """
        SELECT 
            (
                SELECT COUNT(*) 
                FROM publicacion pub 
                WHERE pub.vendedor_cuentas_id = p.id_cuenta 
                AND pub.id IS NOT NULL
                AND pub.fecha_publicacion BETWEEN p.fecha_pago AND p.fecha_expiracion
            ) AS publicacionesRealizadas
        FROM pagos p
        WHERE p.id_cuenta = :idCuenta
        AND p.fecha_pago IS NOT NULL
        AND p.fecha_expiracion > CURRENT_TIMESTAMP
        ORDER BY p.id_pago DESC
        LIMIT 1
        """, nativeQuery = true)
    Map<String, Object> getVendedorData(@Param("idCuenta") int idCuenta);


    @Query(value = """
        SELECT 
            v.id_cuentas AS idCuentas,
            v.nombre AS nombre,
            v.razon AS razon,
            v.cuit_vendedor AS cuitVendedor,
            v.localidad_vendedor AS localidadVendedor,
            v.provincia_vendedor AS provinciaVendedor,
            v.logo_vendedor AS logo,
            v.planes_id_plan AS planesIdPlan,
            v.contacto AS contacto,
            c.activo AS isActivo
        FROM vendedor v
        LEFT JOIN cuentas c ON v.id_cuentas = c.id
        """, nativeQuery = true)
    List<Map<String, Object>> getVendedoresConCuenta();

} 
