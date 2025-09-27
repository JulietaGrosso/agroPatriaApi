package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Busqueda;

@Repository
public interface BusquedaRepositorio extends JpaRepository<Busqueda, Integer>{


}
