package com.example.dao;

import com.example.connection.ConnectionFactory;
import com.example.model.Cliente;
import com.example.model.Funcionario;
import com.example.model.Pedido;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoDAO {

    public void inserir(Pedido p) {
        String sql = "INSERT INTO pedido (codigo_pedido, data_pedido, id_cliente, id_entregador) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getCodigoPedido());
            stmt.setDate(2, Date.valueOf(p.getDataPedido()));
            stmt.setInt(3, p.getCliente().getId());
            stmt.setInt(4, p.getEntregador().getId());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        p.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pedido buscarPorId(int id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";
        Pedido p = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Pedido();
                    p.setId(rs.getInt("id"));
                    p.setCodigoPedido(rs.getString("codigo_pedido"));
                    p.setDataPedido(rs.getDate("data_pedido").toLocalDate());

                    Cliente c = new Cliente();
                    c.setId(rs.getInt("id_cliente"));
                    p.setCliente(c);

                    Funcionario f = new Funcionario();
                    f.setId(rs.getInt("id_entregador"));
                    p.setEntregador(f);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = """
            SELECT p.id, p.codigo_pedido, p.data_pedido,
                   c.id AS id_cliente, c.nome AS cliente_nome,
                   f.id AS id_entregador, f.nome AS funcionario_nome
            FROM pedido p
            JOIN cliente c ON p.id_cliente = c.id
            JOIN funcionario f ON p.id_entregador = f.id
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setCodigoPedido(rs.getString("codigo_pedido"));
                p.setDataPedido(rs.getDate("data_pedido").toLocalDate());

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("cliente_nome"));
                p.setCliente(cliente);

                Funcionario entregador = new Funcionario();
                entregador.setId(rs.getInt("id_entregador"));
                entregador.setNome(rs.getString("funcionario_nome"));
                p.setEntregador(entregador);

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public void atualizar(Pedido p) {
        String sql = "UPDATE pedido SET codigo_pedido = ?, data_pedido = ?, id_cliente = ?, id_entregador = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getCodigoPedido());
            stmt.setDate(2, Date.valueOf(p.getDataPedido()));
            stmt.setInt(3, p.getCliente().getId());
            stmt.setInt(4, p.getEntregador().getId());
            stmt.setInt(5, p.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
