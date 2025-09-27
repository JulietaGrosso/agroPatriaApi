package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agropatriaapp.agropatriaapi.model.Cuentas;
import java.util.Optional;


public interface CuentasRepositorio extends JpaRepository<Cuentas,Integer>{

  Optional<Cuentas> findByCorreo(String correo);
  
} 



