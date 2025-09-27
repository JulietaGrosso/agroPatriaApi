package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Categorias;
import com.agropatriaapp.agropatriaapi.model.PlanesCategoria;


@Repository
public interface CategoriaRepositorio extends JpaRepository<Categorias, Integer>{



}
