package com.christian.persistencia.entity;

import java.math.BigDecimal;

import com.christian.persistencia.enums.EstatusTransaccion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRANSACCIONES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length = 30)
    private String operacion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal importe;

    @Column(nullable = false, length = 50)
    private String cliente;

    @Column(nullable = false, unique = true, length = 6)
    private String referencia;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstatusTransaccion estatus;

    @Column(nullable = false, length = 100)
    private String secreto;

}
