package com.agropatriaapp.agropatriaapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tutoriales")
public class Tutoriales {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private int id;

    @Column(name = "youtubeId")
    @Setter
    @Getter
    private String youtubeId;

    @Column(name = "nombre")
    @Setter
    @Getter
    private String nombreTutorial;

    public Tutoriales() {
    }

  
}
