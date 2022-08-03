package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.pacote;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.agencia;
import br.ufscar.dc.dsw.domain.destino;

public class pacoteDAO extends GenericDAO {

    public pacote<pacote> getAll() {

    	pacote<pacote>  listaPacotes = new ArrayList<>();

        String sql = "SELECT * from pacote p, agencia a, destino d where p.AGENCIA_ID_AGENCIA = a.ID_AGENCIA, p.DESTINO_ID_DESTINO = d.ID_DESTINO order by p.id_pacote";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id_pacote = resultSet.getLong("id_pacote");
                int duracao = resultSet.getInt("duracao");
                String data_partida = resultSet.getString("data_partida");
                float valor = resultSet.getFloat("valor");
                String descricao = resultSet.getString("descricao");
                Long id_agencia = resultSet.getLong("id_agencia");
                String cnpj = resultSet.getString("cnpj");
                String descricao1 = resultSet.getString("descricao");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Usuario usuario = new Usuario(id, nome, login, senha, papel);
                agencia agencia = new agencia(id_agencia, cnpj, descricao1, usuario);
                destino destino = new destino(id_destino, cidade, estado, pais);
                pacote pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, agencia, destino);
                listaPacotes.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacotes;
    }

}
