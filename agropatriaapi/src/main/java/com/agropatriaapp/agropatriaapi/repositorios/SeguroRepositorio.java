package com.agropatriaapp.agropatriaapi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Categorias;
import com.agropatriaapp.agropatriaapi.model.Seguros;

@Repository
public interface SeguroRepositorio extends JpaRepository<Seguros, Integer>{

    
} 

