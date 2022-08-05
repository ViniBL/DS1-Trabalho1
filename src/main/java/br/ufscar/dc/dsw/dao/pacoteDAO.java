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

    public void insert(pacote pacote)
    {
        String sql = "INSERT INTO pacote (id_agencia, id_destino, data_partida, duracao, valor, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacote.getAgencia().getId_agencia());
            statement.setLong(2, pacote.getDestino().getId_destino());
            statement.setString(3, pacote.getData_partida());
            statement.setInt(4, pacote.getDuracao());
            statement.setFloat(5, pacote.getValor());
            statement.setString(6, pacote.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(e);
        }


    }

    public List<pacote> getAll() {

    	List<pacote>  listaPacotes = new ArrayList<>();

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
                Long id = resultSet.getLong("id");
                String nome1 = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                Long id_destino = resultSet.getLong("id_destino");
                Usuario usuario = new Usuario(id, nome1, login, senha, papel);
                agencia agencia = new agencia(id_agencia, nome, cnpj, descricao1, usuario);
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

    public void delete(pacote pacote)
    {
       String sql = "DELETE FROM pacote where id = ?";

       try {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, pacote.getId_pacote());
           statement.executeUpdate();

           statement.close();
           conn.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
   /*
     public pacote get(Long id_pacote)
   {
       pacote pacote = null;
       String sql = "SELECT *from pacote p where id = ?";

       try
       {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, id_pacote);

           ResultSet resultSet = statement.executeQuery();

           if(resultSet.next())
           {
               String data_partida = resultSet.getString("data_partida");
               int duracao = resultSet.getInt("duracao");
               float valor = resultSet.getFloat("valor");
               String descricao =  resultSet.getString("descricao");

               Long agenciaId = resultSet.getLong("id_agencia");
               Long destinoId = resultSet.getLong("id_destino");
               agencia agencia =  new AgenciaDAO().get(agenciaId);
               destino destino = new DestinoDAO().get(destinoId);

               pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, ag, dest);
           }
       

       resultSet.close();
       statement.close();
       conn.close();
    }

       catch (SQLException e) 
       {
           throw new RuntimeException(e);
       }

       return pacote;
   }
   
     public void update(pacote pacote)
   {
       String sql = "UPDATE pacote SET data_partida = ?, duracao = ?, valor = ?, descricao = ?";
       sql += "id_agencia = ?, id_destino = ?, WHERE id =? ";

       try
       {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.SetLong(1, pacote.getId_pacote());
           statement.SetString(2, pacote.getData_partida());
           statement.SetInt(3, pacote.getDuracao());
           statement.SetFloat(4, pacote.getValor());
           statement.SetString(5, pacote.getDescricao());
           statement.setAgencia(6, pacote.getAgencia());
           statement.SetString(7, pacote.getDestino());
           statement.executeUpdate();



       }
       catch (SQLException e) 
       {
           throw new RuntimeException(e);
       }
   }
*/
}
