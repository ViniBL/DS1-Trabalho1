package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.fotos_destino;
import br.ufscar.dc.dsw.domain.destino;

public class fotosDAO extends GenericDAO {

    public void insert(fotos_destino fotos) {

        String sql = "INSERT INTO Fotos_destino (url_fotos, destino) VALUES (?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setString(1, fotos.getUrl_foto());
            statement.setLong(2, fotos.getDestino().getId_destino());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<fotos_destino> getAll() {

        List<fotos_destino> listafotos = new ArrayList<>();

        String sql = "SELECT * from Fotos_destino f, Destino d where f.ID_DESTINO = d.ID_DESTINO order by f.id_fotos";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id_foto = resultSet.getLong("f.id_foto");
                String url_foto = resultSet.getString("f.url_foto");
                Long id_destino = resultSet.getLong("id_destino");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                destino destino = new destino(id_destino, cidade, estado, pais);
                fotos_destino fotos = new fotos_destino(id_foto, url_foto, destino);
                listafotos.add(fotos);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listafotos;
    }

    public fotos_destino getByID(Long id_foto) {
        fotos_destino fotos_destino = null;

        String sql = "SELECT * from Fotos_destino WHERE id_fotos = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_foto);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String url_foto = resultSet.getString("url_foto");
                Long id_destino = resultSet.getLong("id_destino");

                destino destino = new DestinoDAO().get(id_destino);

                fotos_destino = new fotos_destino(id_foto, url_foto, destino);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fotos_destino;
    }

    public void update(fotos_destino fotos_destino) {
        String sql = "UPDATE Fotos_destino SET id_fotos = ?, url_foto = ?, id_destino = ? WHERE id_fotos =?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, fotos_destino.getId_foto());
            statement.setString(2, fotos_destino.getUrl_foto());
            statement.setLong(3, fotos_destino.getDestino().getId_destino());
            statement.setLong(4, fotos_destino.getId_foto());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(fotos_destino fotos_destino) {
        String sql = "DELETE FROM Fotos_destino where id_fotos = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, fotos_destino.getId_foto());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
