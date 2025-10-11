package com.agropatriaapp.agropatriaapi.services;

import com.agropatriaapp.agropatriaapi.dto.TutorialDto;
import com.agropatriaapp.agropatriaapi.model.Tutoriales;
import com.agropatriaapp.agropatriaapi.repositorios.TutorialesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialesService {

    @Autowired
    private TutorialesRepository tutorialesRepository;

    // Obtener todos los tutoriales
    public List<Tutoriales> obtenerTodos() {
        return tutorialesRepository.findAll();
    }

    // Obtener tutorial por ID
    public Optional<Tutoriales> obtenerPorId(int id) {
        return tutorialesRepository.findById(id);
    }

    // Guardar nuevo tutorial
    public Tutoriales guardar(TutorialDto tutorial) {
      Tutoriales tuto = new Tutoriales();
      tuto.setNombreTutorial(tutorial.getNombre());
      tuto.setYoutubeId(tutorial.getYoutube());
      return tutorialesRepository.save(tuto);
    }

    // Modificar tutorial existente
    public Optional<Tutoriales> modificar(int id, TutorialDto datos) {
        return tutorialesRepository.findById(id).map(tutorial -> {
            tutorial.setYoutubeId(datos.getYoutube());
            tutorial.setNombreTutorial(datos.getNombre());
            return tutorialesRepository.save(tutorial);
        });
    }

    // Eliminar tutorial por ID
    public void eliminar(int id) {
        tutorialesRepository.deleteById(id);
    }
}
