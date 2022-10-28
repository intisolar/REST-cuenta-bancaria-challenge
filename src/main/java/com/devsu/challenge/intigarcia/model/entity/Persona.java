package com.devsu.challenge.intigarcia.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Persona {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "PERSONA_ID")
  private String id;

  @Column(name = "NOMBRE")
  private String nombre;

  @Column(name = "APELLIDO")
  private String apellido;

  @Column(name = "GENERO")
  private String genero;

  @Column(name = "EDAD")
  private int edad;

  @Column(name = "DNI")
  private int dni;

  @Column(name = "DIRECCION")
  private String direccion;

  @Column(name = "TELEFONO")
  private String telefono;


}
