package com.agropatriaapp.agropatriaapi.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agropatriaapp.agropatriaapi.model.Planes;

@Repository
public interface PlanRepositorio extends JpaRepository<Planes, Integer>{

  List<Planes> findByActivo(boolean activo);
}


