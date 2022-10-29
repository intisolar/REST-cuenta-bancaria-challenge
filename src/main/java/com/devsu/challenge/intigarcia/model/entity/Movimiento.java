package com.devsu.challenge.intigarcia.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MOVIMIENTOS")
public class Movimiento {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "MOVIMIENTO_ID")
  private String id;

  @ManyToOne
  @JoinColumn(name = "CUENTA_ID")
  private Cuenta cuenta;

  @Column(name = "FECHA_HORA")
  private LocalDateTime fecha;

  @Column(name = "TIPO")
  private String tipo;

  @Column(name = "VALOR")
  private BigDecimal valor;

  @Column(name = "SALDO")
  private BigDecimal saldo;

  @Column(name = "SOFT_DELETE")
  private Boolean softDelete;

  public boolean isSoftDelete() {
    return softDelete;
  }
}
