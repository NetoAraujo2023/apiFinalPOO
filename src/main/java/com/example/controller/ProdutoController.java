package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ProdutoDAO;
import com.example.model.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	 private final ProdutoDAO produtoDAO;

    public ProdutoController(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    @PostMapping("/criar-produto")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        produtoDAO.inserir(produto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/listar-produtos/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable int id) {
        Produto p = produtoDAO.buscarPorId(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar-produto/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable int id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoDAO.atualizar(produto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar-produto/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable int id) {
        produtoDAO.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
