package com.devsu.challenge.intigarcia.model.response;

import com.devsu.challenge.intigarcia.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

  private String id;

  private String nombreCompleto;

  private String genero;

  private int edad;

  private int dni;

  private String direccion;

  private String telefono;

  private String alta;


  public void setAlta(Boolean alta) {
    switch (alta.toString().toUpperCase()) {
      case "FALSE":
        this.alta = "NO";
        break;
      case "TRUE":
        this.alta = "SI";
        break;
      default:
        throw new DataNotFoundException("Alta no puede ser nulo");

    }

  }
}
