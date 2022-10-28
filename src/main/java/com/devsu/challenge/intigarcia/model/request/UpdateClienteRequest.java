package com.devsu.challenge.intigarcia.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClienteRequest {

 // @NotBlank(message = "Debe ingresar su password para modificar los datos")
  private String password;

  @Size(min = 1, max = 15, message = "Escriba una calle válida.")
  private String calle;

  @Pattern(regexp = "^(?=.*?[0-9]){2,5}$", message =
      "Provea una altura en números.")
  private String altura;

 @Pattern(regexp = "^[a-zA-Z ]+$", message = "Ciudad debe contener solo letras, sin acentos.")
 private String ciudad;

 @Pattern(regexp = "^[a-zA-Z ]+$", message = "Provincia debe contener solo letras, sin acentos.")
 private String provincia;

 @Pattern(regexp = "^[a-zA-Z ]+$", message = "Pais debe contener solo letras, sin acentos.")
 private String pais;

 @Pattern(regexp = "^(?=.*?[0-9]){1,3}$", message =
     "Provea un codigo de país válido. Por ejemplo el de Argentina es 54")
  private String codigoPais;
 @Pattern(regexp = "^(?=.*?[0-9]){1,3}$", message =
     "Provea un código de área válido sin 0 inicial")
  private String codigoArea;
 @Pattern(regexp = "^(?=.*?[0-9]){5,7}$", message =
     "Provea un número de teléfono sin el 15 inicial.")
  private String telefono;
}
