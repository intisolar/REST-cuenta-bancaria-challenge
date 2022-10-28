package com.devsu.challenge.intigarcia.model.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateMovimientoRequest {

  private String tipo;
  private BigDecimal monto;

}
