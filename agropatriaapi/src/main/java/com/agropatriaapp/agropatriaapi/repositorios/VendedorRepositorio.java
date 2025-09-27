package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Vendedor;

@Repository
public interface VendedorRepositorio extends JpaRepository<Vendedor, Integer> {

    
} 
