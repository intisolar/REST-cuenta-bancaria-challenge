package com.devsu.challenge.intigarcia.model.request;

import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CreateCuentaRequest {

  @NotBlank
  private String clienteID;

  private String tipoDeCuenta;
  @Digits(integer = 10, fraction = 2)
  private BigDecimal saldoInicial;

}
