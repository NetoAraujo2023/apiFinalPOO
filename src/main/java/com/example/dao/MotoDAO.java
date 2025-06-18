package com.example.dao;

import com.example.connection.ConnectionFactory;
import com.example.model.Moto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MotoDAO {

    public void inserir(Moto m) {
        String sql = "INSERT INTO moto (placa, modelo, cilindrada) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, m.getPlaca());
            stmt.setString(2, m.getModelo());
            stmt.setInt(3, m.getCilindrada());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        m.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Moto buscarPorId(int id) {
        String sql = "SELECT * FROM moto WHERE id = ?";
        Moto m = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    m = new Moto();
                    m.setId(rs.getInt("id"));
                    m.setPlaca(rs.getString("placa"));
                    m.setModelo(rs.getString("modelo"));
                    m.setCilindrada(rs.getInt("cilindrada"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public List<Moto> listarTodos() {
        List<Moto> lista = new ArrayList<>();
        String sql = "SELECT * FROM moto";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Moto m = new Moto();
                m.setId(rs.getInt("id"));
                m.setPlaca(rs.getString("placa"));
                m.setModelo(rs.getString("modelo"));
                m.setCilindrada(rs.getInt("cilindrada"));
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Moto m) {
        String sql = "UPDATE moto SET placa = ?, modelo = ?, cilindrada = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getPlaca());
            stmt.setString(2, m.getModelo());
            stmt.setInt(3, m.getCilindrada());
            stmt.setInt(4, m.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM moto WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
