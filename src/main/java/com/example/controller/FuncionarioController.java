package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dao.FuncionarioDAO;
import com.example.model.Funcionario;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioDAO funcionarioDAO;

    public FuncionarioController(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    @PostMapping("/criar")
    public ResponseEntity<Funcionario> criarFuncionario(@RequestBody Funcionario f) {
        funcionarioDAO.inserir(f);
        return ResponseEntity.ok(f);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable int id) {
        Funcionario f = funcionarioDAO.buscarPorId(id);
        return f != null ? ResponseEntity.ok(f) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listarTodos();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarFuncionario(@PathVariable int id, @RequestBody Funcionario f) {
        f.setId(id);
        funcionarioDAO.atualizar(f);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable int id) {
        funcionarioDAO.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
