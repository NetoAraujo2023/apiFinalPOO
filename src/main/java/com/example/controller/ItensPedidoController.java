package com.example.controller;

import com.example.dao.ItensPedidoDAO;
import com.example.model.ItensPedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido")
public class ItensPedidoController {

    private final ItensPedidoDAO itensPedidoDAO;

    public ItensPedidoController(ItensPedidoDAO itensPedidoDAO) {
        this.itensPedidoDAO = itensPedidoDAO;
    }

    @PostMapping("/criar")
    public ResponseEntity<ItensPedido> criarItem(@RequestBody ItensPedido item) {
        itensPedidoDAO.inserir(item);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensPedido> buscarItem(@PathVariable int id) {
        ItensPedido item = itensPedidoDAO.buscarPorId(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public List<ItensPedido> listarItens() {
        return itensPedidoDAO.listarTodos();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarItem(@PathVariable int id, @RequestBody ItensPedido item) {
        item.setId(id);
        itensPedidoDAO.atualizar(item);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable int id) {
        itensPedidoDAO.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
