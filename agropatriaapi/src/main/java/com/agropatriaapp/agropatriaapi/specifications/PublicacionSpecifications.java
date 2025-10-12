package com.agropatriaapp.agropatriaapi.specifications;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.agropatriaapp.agropatriaapi.model.Publicacion;

public class PublicacionSpecifications {
    public static Specification<Publicacion> byVendedorId(Integer vendedorId) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("vendedorCuentasId"), vendedorId);
    }

    public static Specification<Publicacion> byProductoId(Integer productoId) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("productosId"), productoId);
    }

    public static Specification<Publicacion> byCondicion(Integer condicion) {
      String cond = condicion == 1 ? "Nuevo" : "Usado";
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("condicion"), cond);
    }

    public static Specification<Publicacion> byVendido(Boolean vendido) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("vendido"), vendido);
    }

    public static Pageable withPagination(int page, int size) {
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")); // ordena por id desc
    }

}
