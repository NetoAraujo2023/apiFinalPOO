package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.CarroDAO;
import com.example.model.Carro;

@RestController
@RequestMapping("/carros")
public class CarroController {
	private final CarroDAO carroDAO;

    public CarroController(CarroDAO carroDAO) {
        this.carroDAO = carroDAO;
    }
    
    
    @PostMapping("/criar")
    public ResponseEntity<Carro> criarCarro(@RequestBody Carro m) {
        carroDAO.inserir(m);
        return ResponseEntity.ok(m);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarCarro(@PathVariable int id) {
        Carro m = carroDAO.buscarPorId(id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public List<Carro> listarCarros() {
        return carroDAO.listarTodos();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarCarro(@PathVariable int id, @RequestBody Carro m) {
        m.setId(id);
        carroDAO.atualizar(m);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable int id) {
        carroDAO.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
