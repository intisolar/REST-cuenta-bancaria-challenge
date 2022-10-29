package com.devsu.challenge.intigarcia.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name = "CUENTAS")
public class Cuenta {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "CUENTA_ID")
  private String id;
  @ManyToOne
  @JoinColumn(name = "CLIENTE_ID")
  private Cliente cliente;

  @Column(name = "NUMERO_CUENTA")
  private String numeroCuenta;

  @Column(name = "TIPO")
  private String tipo;

  @Column(name = "SALDO_INICIAL")
  private BigDecimal saldoInicial;

  @Column(name = "SALDO")
  private BigDecimal saldo;

  @Column(name = "DISPONIBLE")
  private BigDecimal disponible;

  @Column(name = "ULTIMA_TRANSACCION")
  private LocalDateTime ultimaTransaccion;

  @OneToMany(mappedBy = "cuenta", orphanRemoval = true)
  private List<Movimiento> movimientos;

  @Column(name = "CREATION_TIMESTAMP")
  @CreationTimestamp
  private LocalDateTime CreationTimestamp;

  @Column(name = "SOFT_DELETE")
  private Boolean softDelete;


  public boolean isSoftDelete() {
    return softDelete;
  }

  public Cuenta() {
    this.movimientos = new ArrayList<>();
    this.saldoInicial = BigDecimal.valueOf(0);
    this.saldo = BigDecimal.valueOf(0);
    this.disponible = BigDecimal.valueOf(0);
  }
/*
  public void setDisponible() {
    if (saldo.compareTo(BigDecimal.valueOf(1000)) > 0) {
      this.disponible = BigDecimal.valueOf(1000);
    } else {
      this.disponible = saldo;
    }
  }*/
}
