
package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.agropatriaapp.agropatriaapi.model.Publicacion;

public interface PublicacionRespositorio extends JpaRepository<Publicacion,Integer>, JpaSpecificationExecutor<Publicacion>{

    

}
