package com.agropatriaapp.agropatriaapi.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.agropatriaapp.agropatriaapi.model.Productos;

public class ProductoSpecifications {
    public static Specification<Productos> containsName(String nombre) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(root.get("nombre"), "%" + nombre + "%");
    }

    public static Specification<Productos> byCategoria(Integer categoriaId) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("categoria"), categoriaId);
    }


}
