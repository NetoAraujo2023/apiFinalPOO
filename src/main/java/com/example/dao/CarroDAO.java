package com.example.dao;

import com.example.connection.ConnectionFactory;
import com.example.model.Carro;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarroDAO {

    public void inserir(Carro c) {
        String sql = "INSERT INTO carro (placa, modelo, quantidade_de_portas) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getPlaca());
            stmt.setString(2, c.getModelo());
            stmt.setInt(3, c.getQuantidadeDePortas());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        c.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Carro buscarPorId(int id) {
        String sql = "SELECT * FROM carro WHERE id = ?";
        Carro c = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Carro();
                    c.setId(rs.getInt("id"));
                    c.setPlaca(rs.getString("placa"));
                    c.setModelo(rs.getString("modelo"));
                    c.setQuantidadeDePortas(rs.getInt("quantidade_de_portas"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public List<Carro> listarTodos() {
        List<Carro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Carro";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Carro c = new Carro();
                c.setId(rs.getInt("id"));
                c.setPlaca(rs.getString("placa"));
                c.setModelo(rs.getString("modelo"));
                c.setQuantidadeDePortas(rs.getInt("quantidade_de_portas"));
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Carro c) {
        String sql = "UPDATE carro SET placa = ?, modelo = ?, quantidade_de_portas = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getPlaca());
            stmt.setString(2, c.getModelo());
            stmt.setInt(3, c.getQuantidadeDePortas());
            stmt.setInt(4, c.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM carro WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
