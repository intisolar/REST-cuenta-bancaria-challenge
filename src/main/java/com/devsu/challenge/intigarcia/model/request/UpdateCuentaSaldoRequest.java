package com.devsu.challenge.intigarcia.model.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdateCuentaSaldoRequest {

  private BigDecimal saldo;
}
