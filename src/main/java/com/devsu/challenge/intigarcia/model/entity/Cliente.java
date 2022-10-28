package com.devsu.challenge.intigarcia.model.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "CLIENTES")
public class Cliente extends Persona {

  @Column(name = "PASSWORD")
  private String password;

  @OneToMany(mappedBy = "cliente")
  private List<Cuenta> cuentas;

  @Column(name = "CREATION_TIMESTAMP")
  @CreationTimestamp
  private LocalDateTime CreationTimestamp;

  @Column(name = "SOFT_DELETE")
  private Boolean softDelete;

  public boolean isSoftDelete() {
    return softDelete;
  }

  public Cliente(){
    this.cuentas = new ArrayList<>();
  }
}
