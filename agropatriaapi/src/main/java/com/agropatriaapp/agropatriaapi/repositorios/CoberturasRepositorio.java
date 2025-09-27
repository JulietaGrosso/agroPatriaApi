package com.agropatriaapp.agropatriaapi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Coberturas;

@Repository
public interface CoberturasRepositorio extends JpaRepository<Coberturas, Integer>{

    List<Coberturas> findAllBySeguroId(int seguroId);
}
