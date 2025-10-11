package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agropatriaapp.agropatriaapi.model.Tutoriales;

public interface TutorialesRepository extends JpaRepository<Tutoriales, Integer> {
  
}
