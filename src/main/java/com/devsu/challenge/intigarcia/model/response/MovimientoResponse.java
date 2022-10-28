package com.devsu.challenge.intigarcia.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MovimientoResponse {

  private LocalDateTime timestamp;
  private String tipo;
  private BigDecimal valor;
  private BigDecimal saldo;


}
