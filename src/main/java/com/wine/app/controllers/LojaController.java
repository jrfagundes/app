package com.wine.app.controllers;

import java.util.List;


import com.wine.app.models.Loja;
import com.wine.app.models.repository.LojaRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;



@RestController
@RequestMapping(value = "/lojas")
public class LojaController {
    
    @Autowired
    private LojaRepository lojaRepository;
    
    @GetMapping
	public ResponseEntity<List<Loja>> listarLojas() {
        return ResponseEntity.ok(lojaRepository.findAll());
	}

    @GetMapping("/{id}")
    public ResponseEntity<Loja> buscarPorLoja(@PathVariable Long id) {
            return ResponseEntity.ok(lojaRepository.findById(id).get());     
    }

    @PostMapping
    public ResponseEntity<Loja> adicionarLoja(@RequestBody Loja loja) {        
        return ResponseEntity.status(HttpStatus.CREATED).body(lojaRepository.save(loja));               
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Loja> atualizarLoja(@PathVariable Long id, @RequestBody Loja loja) {
        Loja lojaAtual = lojaRepository.findById(id).get();
        if (lojaAtual != null) {
            if (loja.getCodigoLoja() != null) lojaAtual.setCodigoLoja(loja.getCodigoLoja());
            if (loja.getFaixaInicio() != null) lojaAtual.setFaixaInicio(loja.getFaixaInicio());
            if (loja.getFaixaFim() != null) lojaAtual.setFaixaFim(loja.getFaixaFim());

            lojaRepository.save(lojaAtual);
            return ResponseEntity.ok(lojaAtual);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    


}
