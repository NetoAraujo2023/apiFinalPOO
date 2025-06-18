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
import com.example.model.Cliente;
import com.example.model.Funcionario;
import com.example.model.ItensPedido;
import com.example.model.Pedido;
import com.example.model.Produto;

@Repository
public class ItensPedidoDAO {

    public void inserir(ItensPedido item) {
        String sql = "INSERT INTO itens_pedido (id_pedido, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, item.getPedido().getId());
            stmt.setInt(2, item.getProduto().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPrecoUnitario());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        item.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ItensPedido buscarPorId(int id) {
        String sql = "SELECT * FROM itens_pedido WHERE id = ?";
        ItensPedido item = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = new ItensPedido();
                    item.setId(rs.getInt("id"));
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setPrecoUnitario(rs.getDouble("preco_unitario"));

                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("id_pedido"));
                    item.setPedido(pedido);

                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id_produto"));
                    item.setProduto(produto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public List<ItensPedido> listarTodos() {
        List<ItensPedido> lista = new ArrayList<>();
        String sql = """
                SELECT 
                    i.*,
                    p.nome_produto,
                    p.preco,
                    p.categoria,
                    ped.codigo_pedido,
                    ped.data_pedido,
                    ped.id_cliente,
                    c.nome AS nome_cliente,
                    ped.id_entregador,
                    f.nome AS nome_funcionario
                FROM itens_pedido i
                JOIN produto p ON p.id = i.id_produto
                JOIN pedido ped ON ped.id = i.id_pedido
                JOIN cliente c ON c.id = ped.id_cliente
                JOIN funcionario f ON f.id = ped.id_entregador
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	ItensPedido item = new ItensPedido();
                item.setId(rs.getInt("id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getDouble("preco_unitario"));

                // Produto
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setCategoria(rs.getString("categoria"));
                item.setProduto(produto);

                // Cliente
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));

                // Funcionario
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id_entregador"));
                funcionario.setNome(rs.getString("nome_funcionario"));

                // Pedido
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setCodigoPedido(rs.getString("codigo_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido").toLocalDate());
                pedido.setCliente(cliente);
                pedido.setEntregador(funcionario);
                item.setPedido(pedido);

                lista.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(ItensPedido item) {
        String sql = "UPDATE itens_pedido SET id_pedido = ?, id_produto = ?, quantidade = ?, preco_unitario = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getPedido().getId());
            stmt.setInt(2, item.getProduto().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPrecoUnitario());
            stmt.setInt(5, item.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM itens_pedido WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
