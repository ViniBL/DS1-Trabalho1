package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.cliente;
import br.ufscar.dc.dsw.domain.Usuario;

public class ClienteDAO extends GenericDAO {

    public void insert(cliente cliente) {

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

        String sql = "SELECT * from cliente c, usuario u WHERE c.id_usuario=u.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("u.id");
                String nome = resultSet.getString("u.nome");
                String login = resultSet.getString("u.login");
                String senha = resultSet.getString("u.senha");
                String papel = resultSet.getString("u.papel");
                long id_cliente = resultSet.getLong("c.id_cliente");
                String cpf = resultSet.getString("u.cpf");
                String telefone = resultSet.getString("u.telefone");
                String sexo = resultSet.getString("u.sexo");
                String data_nascimento = resultSet.getString("u.data_nascimento");
                Usuario usuario = new Usuario(id, nome, login, senha, papel);
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
            statement.setLong(5, cliente.getId_cliente());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public cliente get(Long id_cliente) {
        cliente cliente = null;

        String sql = "SELECT * from cliente c, usuario u WHERE c.id_cliente = ?, c.id_usuario=u.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_cliente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("c.cpf");
                String telefone = resultSet.getString("c.telefone");
                String sexo = resultSet.getString("c.sexo");
                String data_nascimento = resultSet.getString("c.data_nascimento");
                
                Long id = resultSet.getLong("u.id");
                String nome1 = resultSet.getString("u.nome");
                String login = resultSet.getString("u.login");
                String senha = resultSet.getString("u.senha");
                String papel = resultSet.getString("u.papel");
                Usuario usuario = new Usuario(id, nome1, login, senha, papel);


                cliente = new cliente(id_cliente, cpf, telefone, sexo, data_nascimento, usuario);
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
