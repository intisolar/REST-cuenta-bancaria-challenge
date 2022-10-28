package com.devsu.challenge.intigarcia.model.response;

import com.devsu.challenge.intigarcia.exception.DataNotFoundException;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CuentaResponse {

  private String numeroCuenta;
  private String tipo;
  private BigDecimal saldoInicial;
  private String alta;
  private String cliente;

  public void setAlta(Boolean alta) {
    switch (alta.toString().toUpperCase()) {
      case "FALSE":
        this.alta = "SI";
        break;
      case "TRUE":
        this.alta = "NO";
        break;
      default:
        throw new DataNotFoundException("Alta no puede ser nulo");

    }
  }
}