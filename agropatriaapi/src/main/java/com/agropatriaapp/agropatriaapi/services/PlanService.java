package com.agropatriaapp.agropatriaapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.PlanDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Busqueda;
import com.agropatriaapp.agropatriaapi.model.Categorias;
import com.agropatriaapp.agropatriaapi.model.Planes;
import com.agropatriaapp.agropatriaapi.model.PlanesCategoria;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.repositorios.CategoriaRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.PlanCategoriaRepositorio;
import com.agropatriaapp.agropatriaapi.repositorios.PlanRepositorio;

import jakarta.transaction.Transactional;

@Service
public class PlanService {

    @Autowired
    private PlanRepositorio planRepositorio;
    @Autowired
    private PlanCategoriaRepositorio planCategoriaRepositorio;
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    public List<Planes>getPlanes(){
        return planRepositorio.findByActivo(true);
    }

    @Transactional
    public Response deletePlan(int idPlan){
        Optional<Planes> planesOp = planRepositorio.findById(idPlan);
        if ( planesOp.isPresent() ) {
            Planes plan = planesOp.get();
            plan.setActivo(false);
            planRepositorio.save(plan);
        }
        return new Response(true, "Plan eliminado correctamente");
    }

    public Response postPlan(PlanDto planDto){
        Planes planes = new Planes();
        planes.setCantidadPublic(planDto.getCantidad_public());
        planes.setDescripcion(planDto.getDescripcion());
        planes.setDuracionDias(planDto.getDuracion_dias());
        planes.setPrecio(planDto.getPrecio());
        planes.setTitulo(planDto.getTitulo());
        planes.setActivo(true);
        Planes planGuardado = planRepositorio.save(planes);

        for(Integer idCategoria : planDto.getIdsCategoria()){
            
            Optional<Categorias> categorias = categoriaRepositorio.findById(idCategoria);
            if(categorias.isPresent()){
                PlanesCategoria planesCategoria = new PlanesCategoria();
                planesCategoria.setPlanesIdPlan(planGuardado.getIdPlan());
                planesCategoria.setCategorias(categorias.get());
                planCategoriaRepositorio.save(planesCategoria);
            }
           
        }
        return new Response(true, "Se agregó correctamente");
    }

    @Transactional
    public Response putPlan(int idPlan, PlanDto planDto) throws NotFoundEntityException{
        Optional<Planes>planesOp = planRepositorio.findById(idPlan);
        if(planesOp.isPresent()){
            Planes planes = planesOp.get();
            planes.setCantidadPublic(planDto.getCantidad_public());
            planes.setDescripcion(planDto.getDescripcion());
            planes.setDuracionDias(planDto.getDuracion_dias());
            planes.setPrecio(planDto.getPrecio());
            planes.setTitulo(planDto.getTitulo());
            

            planCategoriaRepositorio.deleteByPlanesIdPlan(idPlan);
             for(Integer idCategoria : planDto.getIdsCategoria()){
            
            Optional<Categorias> categorias = categoriaRepositorio.findById(idCategoria);
            if(categorias.isPresent()){
                PlanesCategoria planesCategoria = new PlanesCategoria();
                planesCategoria.setPlanesIdPlan(planes.getIdPlan());
                planesCategoria.setCategorias(categorias.get());
                planCategoriaRepositorio.save(planesCategoria);
            }
           
        }
            planRepositorio.save(planes);
            return new Response(true, "Se actualizó correctamente");

        }

         throw new NotFoundEntityException("No se encontró el Plan");

    }



}
