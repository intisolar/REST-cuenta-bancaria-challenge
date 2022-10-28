package com.devsu.challenge.intigarcia.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum TipoCuentaEnum {
  CUG("Cuenta Universal Gratuita"),
  CAJA_DE_AHORROS("Caja de Ahorros"),
  CUENTA_CORRIENT("Cuenta corriente"),
  CUENTA_SUELDO("Cuenta sueldo"),
  CUENTA_DE_LA_SEGURIDAD_SOCIAL("Cuenta de la seguridad social");

  private final String nombre;

  TipoCuentaEnum(String nombre) {
    this.nombre = nombre;
  }

  /* JSON serialization: retorna el String en el campo JSON respectivo marcado
  * como TipoCuentaEnum

  @JsonValue
  public String getNombre() {
    return this.nombre;
  }

  /*Interpreta los datos del JSON en enumeraciones en los métodos POST
  @JsonCreator
  public static TipoCuentaEnum decode(final String nombre) {
    return Stream.of(TipoCuentaEnum.values()).filter(targetEnum -> targetEnum.nombre.equals(nombre))
        .findFirst().orElse(null);
  }
*/

}
