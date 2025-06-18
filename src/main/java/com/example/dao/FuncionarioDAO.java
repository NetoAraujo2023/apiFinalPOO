package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.connection.ConnectionFactory;
import com.example.model.Funcionario;

@Repository
public class FuncionarioDAO {
	 public void inserir(Funcionario f) {
	        String sql = "INSERT INTO funcionario (nome, cargo, salario) VALUES (?, ?, ?)";
	        try (Connection conn = ConnectionFactory.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            stmt.setString(1, f.getNome());
	            stmt.setString(2, f.getCargo());
	            stmt.setDouble(3, f.getSalario());

	            int affected = stmt.executeUpdate();
	            if (affected > 0) {
	                try (ResultSet rs = stmt.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        f.setId(rs.getInt(1));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public Funcionario buscarPorId(int id) {
	        String sql = "SELECT * FROM funcionario WHERE id = ?";
	        Funcionario f = null;
	        try (Connection conn = ConnectionFactory.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    f = new Funcionario();
	                    f.setId(rs.getInt("id"));
	                    f.setNome(rs.getString("nome"));
	                    f.setCargo(rs.getString("cargo"));
	                    f.setSalario(rs.getDouble("salario"));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return f;
	    }

	    public List<Funcionario> listarTodos() {
	        List<Funcionario> lista = new ArrayList<>();
	        String sql = "SELECT * FROM funcionario";
	        try (Connection conn = ConnectionFactory.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Funcionario f = new Funcionario();
	                f.setId(rs.getInt("id"));
	                f.setNome(rs.getString("nome"));
	                f.setCargo(rs.getString("cargo"));
	                f.setSalario(rs.getDouble("salario"));
	                lista.add(f);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }

	    public void atualizar(Funcionario f) {
	    	
	        String sql = "UPDATE funcionario SET nome = ?, cargo = ?, salario = ? WHERE id = ?";
	        try (Connection conn = ConnectionFactory.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, f.getNome());
	            stmt.setString(2, f.getCargo());
	            stmt.setDouble(3, f.getSalario());
	            stmt.setInt(4, f.getId());

	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deletar(int id) {
	        String sql = "DELETE FROM funcionario WHERE id = ?";
	        try (Connection conn = ConnectionFactory.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
