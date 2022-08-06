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

        String sql = "SELECT * from Pacote p, Agencia a, Destino d, Usuario u where p.id_agencia = a.id_agencia and p.id_destino = d.id_destino and a.id_usuario= u.id_usuario order by p.id_pacote;";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id_pacote = resultSet.getLong("p.id_pacote");
                Long id_agencia = resultSet.getLong(2);
                Long id_destino = resultSet.getLong(3);
                String data_partida = resultSet.getString("p.data_partida");
                int duracao = resultSet.getInt("p.duracao");
                float valor = resultSet.getFloat("p.valor");
                String descricao = resultSet.getString("p.descricao");

                Long id = resultSet.getLong(8);
                String nome = resultSet.getString("a.nome");
                String cnpj = resultSet.getString("a.cnpj");
                String descricao1 = resultSet.getString("a.descricao");
                
                String cidade = resultSet.getString("d.cidade");
                String estado = resultSet.getString("d.estado");
                String pais = resultSet.getString("d.pais");
                
                String nome1 = resultSet.getString("u.nome");
                String login = resultSet.getString("u.login");
                String senha = resultSet.getString("u.senha");
                String papel = resultSet.getString("u.papel");
                
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
       String sql = "DELETE FROM pacote where id_pacote = ?";

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
   
     public pacote get(Long id_pacote)
   {
       pacote pacote = null;
       String sql = "SELECT *from pacote p where id_pacote = ?";

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

               pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, agencia, destino);
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
       sql += "id_agencia = ?, id_destino = ?, WHERE id_pacote =? ";

       try
       {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setString(1, pacote.getData_partida());
           statement.setInt(2, pacote.getDuracao());
           statement.setFloat(3, pacote.getValor());
           statement.setString(4, pacote.getDescricao());
           statement.setLong(5, pacote.getAgencia().getId_agencia());
           statement.setLong(6, pacote.getDestino().getId_destino());
           statement.setLong(7, pacote.getId_pacote());
           statement.executeUpdate();



       }
       catch (SQLException e) 
       {
           throw new RuntimeException(e);
       }
   }
}
