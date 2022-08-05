package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.destino;

public class DestinoDAO extends GenericDAO {

    public void insert(Destino destino)
    {
        String sql = "INSERT INTO destino (cidade, estado, pais) VALUES (?, ?, ?)";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, destino.getCidade());
            statement.setString(2, destino.getEstado());
            statement.setString(3, destino.getPais());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            throw new RuntimeException(e);
        }


    }

    public List<destino> getAll() {

    	List<destino>  listaDestinos = new ArrayList<>();

        String sql = "SELECT * from Destino";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id_destino = resultSet.getLong("id_pacote");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                destino destino = new destino(id_destino, cidade, estado, pais);
                listaDestinos.add(destino);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaDestinos;
    }


    public void update(destino destino) {
        String sql = "UPDATE destino SET cidade = ?, estado = ?, pais = ? WHERE id_destino = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, destino.getCidade());
            statement.setString(2, destino.getEstado());
            statement.setString(3, destino.getPais());
            statement.setLong(4, destino.getId_destino());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(destino destino)
    {
       String sql = "DELETE FROM destino where id_destino = ?";

       try {
           Connection conn = this.getConnection();
           PreparedStatement statement = conn.prepareStatement(sql);

           statement.setLong(1, destino.getId_destino());
           statement.executeUpdate();

           statement.close();
           conn.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

   public destino getbyID(Long id_destino) {
        destino destino = null;

        String sql = "SELECT * from destino WHERE id_destino = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_destino);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                destino = new destino(id_destino, cidade, estado, pais);
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
