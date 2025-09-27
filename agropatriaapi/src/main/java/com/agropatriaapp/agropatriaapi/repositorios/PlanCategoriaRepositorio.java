package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agropatriaapp.agropatriaapi.model.PlanesCategoria;

public interface PlanCategoriaRepositorio extends JpaRepository<PlanesCategoria, Integer>{

    void deleteByPlanesIdPlan(int id);
}

