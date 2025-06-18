package com.example.controller;

import com.example.dao.PedidoDAO;
import com.example.model.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoDAO pedidoDAO;

    public PedidoController(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    @PostMapping("/criar")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido p) {
        pedidoDAO.inserir(p);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable int id) {
        Pedido p = pedidoDAO.buscarPorId(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public List<Pedido> listarPedidos() {
        return pedidoDAO.listarTodos();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarPedido(@PathVariable int id, @RequestBody Pedido p) {
        p.setId(id);
        pedidoDAO.atualizar(p);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable int id) {
        pedidoDAO.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
