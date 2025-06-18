package com.example.controller;

import com.example.dao.MotoDAO;
import com.example.model.Moto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    private final MotoDAO motoDAO;

    public MotoController(MotoDAO motoDAO) {
        this.motoDAO = motoDAO;
    }

    @PostMapping("/criar")
    public ResponseEntity<Moto> criarMoto(@RequestBody Moto m) {
        motoDAO.inserir(m);
        return ResponseEntity.ok(m);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> buscarMoto(@PathVariable int id) {
        Moto m = motoDAO.buscarPorId(id);
        return m != null ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listar")
    public List<Moto> listarMotos() {
        return motoDAO.listarTodos();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarMoto(@PathVariable int id, @RequestBody Moto m) {
        m.setId(id);
        motoDAO.atualizar(m);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMoto(@PathVariable int id) {
        motoDAO.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
