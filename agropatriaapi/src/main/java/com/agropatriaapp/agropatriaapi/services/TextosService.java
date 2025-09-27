package com.agropatriaapp.agropatriaapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agropatriaapp.agropatriaapi.dto.TextosDto;
import com.agropatriaapp.agropatriaapi.exceptions.NotFoundEntityException;
import com.agropatriaapp.agropatriaapi.model.Response;
import com.agropatriaapp.agropatriaapi.model.Textos;
import com.agropatriaapp.agropatriaapi.repositorios.TextosRepositorio;

@Service
public class TextosService {
 
  @Autowired
  private TextosRepositorio textosRepositorio;

  public Textos getTexto(int id) throws NotFoundEntityException{
    Optional<Textos>textosOp = textosRepositorio.findById(id);
    if(textosOp.isPresent()){
        return textosOp.get();
    }
    throw new NotFoundEntityException("Texto no encontrado");
  }


  public Response putTextos(int id, TextosDto textosDto) throws NotFoundEntityException{
    Optional<Textos> textosOp = textosRepositorio.findById(id);
    if(textosOp.isPresent()){
      Textos textos = textosOp.get();
      textos.setTexto(textosDto.getTexto());
      textosRepositorio.save(textos);
      return new Response(true, "Se actualizó correctamente");
    }
    throw new NotFoundEntityException("No se encontró el texto");
  }








}
