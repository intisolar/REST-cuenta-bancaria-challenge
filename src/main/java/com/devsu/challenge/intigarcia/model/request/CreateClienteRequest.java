package com.devsu.challenge.intigarcia.model.request;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClienteRequest {

  @NotBlank(message = "Debe proveer una contraseña.")
  @Pattern(regexp = "^[0-9]{4,5}$",
      message = "Escriba un pin de 4 números")
  private String password;

  @NotBlank(message = "El nombre no puede ser nulo o estar vacío.")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre debe contener solo letras, sin acentos.")
  private String nombre;

  @NotBlank(message = "El apellido no puede ser nulo o estar vacío.")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "El apellido debe contener solo letras, sin acentos.")
  private String apellido;

  @NotBlank(message = "Elija un género.")
  private String genero;

  @NotNull(message = "La edad no puede ser nula.")
  @Min(value = 18, message = "La edad mínima es de 18 años.")
  @Max(value = 99, message = "Debe ingresar una edad válida.")
  private Integer edad;

  @NotNull(message = "El campo DNI no puede estar vacío.")
  @Min(value = 1000000, message = "Provea un numero de DNI válido.")
  private Integer dni;

  @NotBlank(message = "El campo calle no puede estar vacío.")
  @Size(min = 1, max = 15, message = "Escriba una calle válida.")
  private String calle;

  @NotBlank(message = "Ingrese una altura en su dirección.")
  @Pattern(regexp = "^[0-9]{2,5}$", message =
      "Provea una altura en números.")
  private String altura;

  @NotBlank(message = "El campo ciudad no puede ser nulo o estar vacío.")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Ciudad debe contener solo letras, sin acentos.")
  private String ciudad;

  @NotBlank(message = "El campo provincia no puede ser nulo o estar vacío.")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Provincia debe contener solo letras, sin acentos.")
  private String provincia;

  @NotBlank(message = "El campo pais no puede ser nulo o estar vacío.")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Pais debe contener solo letras, sin acentos.")
  private String pais;

  @NotBlank(message = "Provea un código de área nacional.")
  @Pattern(regexp = "^[0-9]{1,3}$", message =
      "Provea un codigo de país válido. Por ejemplo el de Argentina es 54")
  private String codigoPais;

  @NotBlank(message = "Provea un código de área provincial/estatal sin 0.")
  @Pattern(regexp = "^[0-9]{1,4}$", message =
      "Provea un código de área válido sin 0 inicial")
  private String codigoArea;

  @NotBlank(message = "Provea un número de teléfono sin el 15 inicial.")
  @Pattern(regexp = "^[0-9]{5,8}$", message =
      "Provea un número de teléfono válido sin el 15 inicial.")
  private String telefono;

  @NotBlank(message = "El tipo de cuenta inicial no puede ser nulo o estar vacío.")
  private String tipoDeCuentaInicial;

  private BigDecimal saldoInicial;

}
