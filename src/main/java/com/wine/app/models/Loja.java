package com.wine.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

//import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Loja {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO_LOJA", nullable = false)
    private String codigoLoja;

    @Column(name = "FAIXA_INICIO")
    private Long faixaInicio;

    @Column(name = "FAIXA_FIM")
    private Long faixaFim;
    
    @JsonIgnore
    public Boolean isValid(){
        return this.faixaFim>=this.faixaInicio; 
    }
}
