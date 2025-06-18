package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dao.ClienteDAO;
import com.example.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @PostMapping("/criar")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente c) {
        clienteDAO.inserir(c);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable int id) {
        Cliente c = clienteDAO.buscarPorId(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listar-clientes")
    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarCliente(@PathVariable int id, @RequestBody Cliente c) {
        c.setId(id);
        clienteDAO.atualizar(c);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable int id) {
        clienteDAO.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
