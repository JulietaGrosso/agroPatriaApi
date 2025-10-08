package com.agropatriaapp.agropatriaapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "webhooklogs")
public class WebHookLogs {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter
    @Getter
    private int id;

    @Column(name = "log", columnDefinition = "json")
    @Lob
    @Setter
    @Getter
    private String log;


  @Column(name = "fecha_creacion", 
          nullable = false, 
          updatable = false, 
          columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
  @Getter
  private LocalDateTime fechaCreacion;

  public WebHookLogs() {
    this.fechaCreacion = LocalDateTime.now();
  }
}
