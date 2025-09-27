package com.agropatriaapp.agropatriaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agropatriaapp.agropatriaapi.dto.PlanDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Planes;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.services.BusquedaService;
import com.agropatriaapp.agropatriaapi.services.PlanService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("plan")
public class PlanController {

    private final BusquedaService busquedaService;

    @Autowired
    private PlanService planService;

    PlanController(BusquedaService busquedaService) {
        this.busquedaService = busquedaService;
    }

    @GetMapping
    public ResponseEntity<?> getPlanes(){
        List<Planes> planes = planService.getPlanes(); 
        return ResponseEntity.ok(planes);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePlan(@PathVariable("id")int id){
        return ResponseEntity.ok(planService.deletePlan(id));
    }

    @PostMapping
    public ResponseEntity<?> postPlan(@RequestBody PlanDto planDto){
        return ResponseEntity.ok(planService.postPlan(planDto));
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> putPlan(@PathVariable("id")int id, @RequestBody PlanDto planDto){
        try{
          return ResponseEntity.ok(planService.putPlan(id, planDto));
        } catch (NotFoundEntityException e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(404).body(new Response(false, "Plan no encontrado"));

        }
    }




}
