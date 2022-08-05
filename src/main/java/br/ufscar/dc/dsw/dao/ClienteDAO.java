package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.cliente;

public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO Cliente (id_cliente, cpf, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, cliente.getUsuario().getId());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getTelefone());
            statement.setString(4, cliente.getSexo());
            statement.setDate(5, cliente.getData_nascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<cliente> getAll() {

        List<cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * from cliente c";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                long id_cliente = resultSet.getLong("id_cliente");
                long id_usuario = resultSet.getLong("id_usuario");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String data_nascimento = resultSet.getString("data_nascimento");
                Usuario usuario = new Usuario(id, nome1, login, senha, papel);
                cliente cliente = new cliente(id_cliente, cpf, telefone, sexo, data_nascimento, usuario);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(cliente cliente) {
        String sql = "DELETE FROM cliente where id_cliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId_cliente());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(cliente cliente) {
        String sql = "UPDATE cliente SET cpf = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id_cliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getSexo());
            statement.setString(4, cliente.getData_nascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public cliente getbyID(Long id_cliente) {
        cliente cliente = null;

        String sql = "SELECT * from cliente WHERE id_cliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_cliente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id_usuario = resultSet.getLong("id_usuario");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String data_nascimento = resultSet.getString("data_nascimento");

                cliente = new cliente(id_usuario, cpf, telefone, sexo, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
    
}
