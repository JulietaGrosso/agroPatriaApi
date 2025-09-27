package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Textos;

@Repository
public interface TextosRepositorio extends JpaRepository<Textos,Integer>{

}
