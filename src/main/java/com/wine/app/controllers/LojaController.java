package com.wine.app.controllers;

import java.util.List;


import com.wine.app.models.Loja;
import com.wine.app.models.repository.LojaRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
        if(lojaRepository.ExistCepInRange(loja.getFaixaInicio(), loja.getFaixaFim())!=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }     
        if(loja.isValid()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(lojaRepository.save(loja));
        } 
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  
        }             
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Loja> atualizarLoja(@PathVariable Long id, @RequestBody Loja loja) {
        Long faixaInicio = 0L;
        Long faixaFim = 0L;
        if(loja.getFaixaInicio()!=null) faixaInicio=loja.getFaixaInicio(); 
        if(loja.getFaixaFim()!=null) faixaFim=loja.getFaixaFim();        
       
        if(lojaRepository.ExistCepInRange(faixaInicio, faixaFim)!=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }  
        Loja lojaAtual = lojaRepository.findById(id).get();
        if (lojaAtual != null) {
            if (loja.getCodigoLoja() != null) lojaAtual.setCodigoLoja(loja.getCodigoLoja());
            if (loja.getFaixaInicio() != null) lojaAtual.setFaixaInicio(loja.getFaixaInicio());
            if (loja.getFaixaFim() != null) lojaAtual.setFaixaFim(loja.getFaixaFim());
            if (!lojaAtual.isValid()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            } 
            lojaRepository.save(lojaAtual);
            return ResponseEntity.ok(lojaAtual);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeletarCep(@PathVariable Long id){
        lojaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
        
    }
    


}
