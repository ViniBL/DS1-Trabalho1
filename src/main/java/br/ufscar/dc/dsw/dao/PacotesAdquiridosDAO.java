package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import br.ufscar.dc.dsw.domain.pacotes_adquiridos;
import br.ufscar.dc.dsw.domain.cliente;
import br.ufscar.dc.dsw.domain.pacote;
import br.ufscar.dc.dsw.dao.pacoteDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.agencia;
import br.ufscar.dc.dsw.domain.destino;


public class PacotesAdquiridosDAO extends GenericDAO {

    public void insert(pacotes_adquiridos pacote_adquirido)
    {
        String sql = "INSERT INTO Pacotes_adquiridos (id_usuario, id_pacote, status) VALUES (?, ?, ?)";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacote_adquirido.getUsuario().getId());
            statement.setLong(2, pacote_adquirido.getPacote().getId_pacote());
            statement.setString(3, pacote_adquirido.getStatus());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(e);
        }

    }

    public List<pacotes_adquiridos> getAllCliente(Long id_cliente) {

    	List<pacotes_adquiridos>  listaPacotes_adquiridos = new ArrayList<>();

        String sql = "SELECT * from Pacotes_adquiridos where id_usuario=?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_cliente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id_pacote_adquirido = resultSet.getLong("id_pacote_adquirido");
                String status = resultSet.getString("status");
                Long id_pacote = resultSet.getLong("id_pacote");

        
                pacote pacote = new pacoteDAO().get(id_pacote);
                Usuario cliente = new UsuarioDAO().getClienteByID(id_cliente);
                pacotes_adquiridos pacote_adquirido = new pacotes_adquiridos(id_pacote_adquirido, status, cliente, pacote);
                listaPacotes_adquiridos.add(pacote_adquirido);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacotes_adquiridos;
    }

    public void delete(pacotes_adquiridos pacote_adquirido)
    {
       String sql = "DELETE FROM Pacotes_adquiridos p where p.id_pacote_adquirido = ?";

       try {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, pacote_adquirido.getId_pacote_adquirido());
           statement.executeUpdate();

           statement.close();
           conn.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
   
     public pacotes_adquiridos get(Long id_pacote_adquirido)
   {
       pacotes_adquiridos pacote_adquirido = null;
       String sql = "SELECT * from Pacotes_adquiridos p where p.id_pacote_adquirido = ?";

       try
       {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, id_pacote_adquirido);

           ResultSet resultSet = statement.executeQuery();

           if(resultSet.next())
           {
               String status = resultSet.getString("status");
               Long pacoteId = resultSet.getLong("id_pacote");
               Long clienteId = resultSet.getLong("id_cliente");

               pacote pacote = new pacoteDAO().get(pacoteId);
               Usuario cliente = new UsuarioDAO().getClienteByID(clienteId);

               pacote_adquirido = new pacotes_adquiridos(id_pacote_adquirido, status, cliente, pacote);
           }
       

       resultSet.close();
       statement.close();
       conn.close();
    }

       catch (SQLException e) 
       {
           throw new RuntimeException(e);
       }

       return pacote_adquirido;
   }
   
     public void update(pacotes_adquiridos pacote_adquirido)
   {
       String sql = "UPDATE Pacotes_adquiridos SET status = ? WHERE id_pacote_adquirido = ? ";

       try
       {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setString(1, pacote_adquirido.getStatus());
           statement.setLong(2, pacote_adquirido.getId_pacote_adquirido());
           statement.executeUpdate();
           statement.close();
           conn.close();
       }
       catch (SQLException e) 
       {
           throw new RuntimeException(e);
       }
   }
}
