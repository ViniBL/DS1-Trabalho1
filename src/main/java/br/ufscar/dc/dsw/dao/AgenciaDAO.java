package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.agencia;

public class AgenciaDAO extends GenericDAO {
    public void insert(agencia agencia) {
        String sql = "INSERT INTO agencia (id_agencia, nome, cnpj, descricao, usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, agencia.getId_agencia());
            statement.setString(2, agencia.getNome());
            statement.setString(3, agencia.getCnpj());
            statement.setString(4, agencia.getDescricao());
            statement.setLong(4, agencia.getUsuario().getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<agencia> getAll() {
        List<agencia> listaAgencia = new ArrayList<>();
        String sql = "SELECT * from agencia a, Usuario u where a.USUARIO_ID = u.ID order by a.id_agencia";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id_agencia = resultSet.getLong("a.id_agencia");
                String nome_a = resultSet.getString("a.nome");
                String cnpj = resultSet.getString("a.cnpj");
                String descricao = resultSet.getString("a.descricao");
                // informacoe do usuario
                Long id = resultSet.getLong("u.id");
                String nome_u = resultSet.getString("u.nome");
                String login = resultSet.getString("u.login");
                String senha = resultSet.getString("u.senha");
                String papel = resultSet.getString("u.papel");

                Usuario usuario = new Usuario(id, nome_u, login, senha, papel);

                agencia agencia = new agencia(id_agencia, nome_a, cnpj, descricao, usuario);

                listaAgencia.add(agencia);
            }
            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgencia;
    }

    public void delete(agencia agencia) {
        String sql = "DELETE FROM agencia where id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, agencia.getId_agencia());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public agencia get(Long id_agencia) {

        agencia agencia = null;
        String sql = "SELECT *from agencia a where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_agencia);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String descricao = resultSet.getString("descricao");

                Long usuarioid = resultSet.getLong("id");
                Usuario usuario = new UsuarioDAO().getbyID(usuarioid);

                agencia = new agencia(id_agencia, nome, cnpj, descricao, usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return agencia;

    }

    public void update(agencia agencia) {

        String sql = "UPDATE agencia SET nome = ?, cnpj = ?, descricao = ?";
        sql += "id = ? WHERE id_agencia =?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agencia.getNome());
            statement.setString(2, agencia.getCnpj());
            statement.setString(3, agencia.getDescricao());
            statement.setLong(4, agencia.getUsuario().getId());
            statement.setLong(5, agencia.getId_agencia());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
