package com.devsu.challenge.intigarcia.model.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CuentaConsultaSaldoResponse {

  private String nombreTitular;
  private BigDecimal saldo;
}
