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
import br.ufscar.dc.dsw.dao.UsuarioDAO;

public class pacoteDAO extends GenericDAO {


    public void insert(pacote pacote)
    {
        String sql = "INSERT INTO Pacote (id_usuario, id_destino, data_partida, duracao, valor, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacote.getUsuario().getId());
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

        String sql = "SELECT * from Pacote p, Destino d, Usuario u where p.id_usuario = u.id_usuario and p.id_destino = d.id_destino order by p.id_pacote;";
        


        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id_pacote = resultSet.getLong("p.id_pacote");
                Long id_usuario = resultSet.getLong(2);
                Long id_destino = resultSet.getLong(3);
                String data_partida = resultSet.getString("p.data_partida");
                int duracao = resultSet.getInt("p.duracao");
                float valor = resultSet.getFloat("p.valor");
                String descricao = resultSet.getString("p.descricao");

                String cidade = resultSet.getString("d.cidade");
                String estado = resultSet.getString("d.estado");
                String pais = resultSet.getString("d.pais");
                
                Usuario usuario = new UsuarioDAO().getAgenciaByID(id_usuario);
                destino destino = new destino(id_destino, cidade, estado, pais);
                pacote pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, usuario, destino);
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

	public List<pacote> getAll() {

    	List<pacote>  listaPacotes = new ArrayList<>();

        String sql = "SELECT * from Pacote p, Destino d, Usuario u where p.id_usuario = u.id_usuario and p.id_destino = d.id_destino order by p.id_pacote;";
        


        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id_pacote = resultSet.getLong("p.id_pacote");
                Long id_usuario = resultSet.getLong(2);
                Long id_destino = resultSet.getLong(3);
                String data_partida = resultSet.getString("p.data_partida");
                int duracao = resultSet.getInt("p.duracao");
                float valor = resultSet.getFloat("p.valor");
                String descricao = resultSet.getString("p.descricao");

                String cidade = resultSet.getString("d.cidade");
                String estado = resultSet.getString("d.estado");
                String pais = resultSet.getString("d.pais");
                
                Usuario usuario = new UsuarioDAO().getAgenciaByID(id_usuario);
                destino destino = new destino(id_destino, cidade, estado, pais);
                pacote pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, usuario, destino);
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
   
    public pacote getbyDestino(String destino) {

    	pacote pacote = null;

        String sqlDestino = "SELECT * from Pacote p, Destino d, Usuario u where p.id_usuario = u.id_usuario and p.id_destino = d.id_destino and d.cidade=? order by p.id_pacote;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlDestino);

            statement.setString(1,destino);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Long id_pacote = resultSet.getLong("p.id_pacote");
                Long id_usuario = resultSet.getLong(2);
                Long id_destino = resultSet.getLong(3);
                String data_partida = resultSet.getString("p.data_partida");
                int duracao = resultSet.getInt("p.duracao");
                float valor = resultSet.getFloat("p.valor");
                String descricao = resultSet.getString("p.descricao");        
                
                String cidade = resultSet.getString("d.cidade");
                String estado = resultSet.getString("d.estado");
                String pais = resultSet.getString("d.pais");
                
                Usuario usuario = new UsuarioDAO().getAgenciaByID(id_usuario);
                destino dest = new destino(id_destino, cidade, estado, pais);
                pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, usuario, dest);
                
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacote;
    }

    public List<pacote> getbyAgencia(String nomeAgencia) {

    	List<pacote>  listaPacotes = new ArrayList<>();

        String sqlAgencia = "SELECT * from Pacote p, Destino d, Usuario u where p.id_usuario = u.id_usuario and p.id_destino = d.id_destino and u.nome=? order by p.id_pacote;";
       
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlAgencia);

            statement.setString(1,nomeAgencia);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id_pacote = resultSet.getLong("p.id_pacote");
                Long id_usuario = resultSet.getLong(2);
                Long id_destino = resultSet.getLong(3);
                String data_partida = resultSet.getString("p.data_partida");
                int duracao = resultSet.getInt("p.duracao");
                float valor = resultSet.getFloat("p.valor");
                String descricao = resultSet.getString("p.descricao");

                String cidade = resultSet.getString("d.cidade");
                String estado = resultSet.getString("d.estado");
                String pais = resultSet.getString("d.pais");
                
                Usuario usuario = new UsuarioDAO().getAgenciaByID(id_usuario);
                destino destino = new destino(id_destino, cidade, estado, pais);
                pacote pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, usuario, destino);
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

    public List<pacote> getbyData_partida(String filtroTextData_partida) {

    	List<pacote>  listaPacotes = new ArrayList<>();
   
        String sqlData_partida = "SELECT * from Pacote p, Destino d, Usuario u where p.id_usuario = u.id_usuario and p.id_destino = d.id_destino and p.data_partida=? order by p.id_pacote;";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlData_partida);

            statement.setString(1,filtroTextData_partida);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Long id_pacote = resultSet.getLong("p.id_pacote");
                Long id_usuario = resultSet.getLong(2);
                Long id_destino = resultSet.getLong(3);
                String data_partida = resultSet.getString("p.data_partida");
                int duracao = resultSet.getInt("p.duracao");
                float valor = resultSet.getFloat("p.valor");
                String descricao = resultSet.getString("p.descricao");

                String cidade = resultSet.getString("d.cidade");
                String estado = resultSet.getString("d.estado");
                String pais = resultSet.getString("d.pais");
                
                Usuario usuario = new UsuarioDAO().getAgenciaByID(id_usuario);
                destino destino = new destino(id_destino, cidade, estado, pais);
                pacote pacote = new pacote(id_pacote, data_partida, duracao, valor, descricao, usuario, destino);
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
       String sql = "DELETE FROM Pacote where id_pacote = ?";

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
       String sql = "SELECT *from Pacote p where id_pacote = ?";

       try
       {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, id_pacote);

           ResultSet resultSet = statement.executeQuery();

           if(resultSet.next())
           {

               Long agenciaId = resultSet.getLong("id_usuario");
               Long destinoId = resultSet.getLong("id_destino");
               String data_partida = resultSet.getString("data_partida");
               int duracao = resultSet.getInt("duracao");
               float valor = resultSet.getFloat("valor");
               Float valorProposta = resultSet.getFloat("valorProposta");
               String descricao =  resultSet.getString("descricao");

               
               Usuario agencia =  new UsuarioDAO().getAgenciaByID(agenciaId);
               destino destino = new DestinoDAO().get(destinoId);

               pacote = new pacote(id_pacote, data_partida, duracao, valor, valorProposta, descricao, agencia, destino);
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

   public pacote getValorProposta(Long id_pacote)
   {
       pacote pacote = null;
       String sql = "SELECT *from Pacote p where id_pacote = ?";

       try
       {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, id_pacote);

           ResultSet resultSet = statement.executeQuery();

           if(resultSet.next())
           {
               Float valorProposta = resultSet.getFloat("valorProposta");

               pacote = new pacote(id_pacote, valorProposta);
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
       String sql = "UPDATE Pacote SET id_usuario = ?, id_destino = ?, data_partida = ?, duracao = ?, valor = ?, descricao = ? WHERE id_pacote =? ";

       try
       {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, pacote.getUsuario().getId());
           statement.setLong(2, pacote.getDestino().getId_destino());
           statement.setString(3, pacote.getData_partida());
           statement.setInt(4, pacote.getDuracao());
           statement.setFloat(5, pacote.getValor());
           statement.setString(6, pacote.getDescricao());
           statement.setLong(7, pacote.getId_pacote());
           statement.executeUpdate();



       }
       catch (SQLException e) 
       {
           throw new RuntimeException(e);
       }
   }
}
