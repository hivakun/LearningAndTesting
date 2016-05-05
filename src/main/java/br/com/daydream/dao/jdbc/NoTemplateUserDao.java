package br.com.daydream.dao.jdbc;

import br.com.daydream.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rivaldo
 *         Created on 05/05/2016.
 */
public class NoTemplateUserDao {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    public List<User> list(int max) {
        List<User> resultado = new ArrayList<User>();
        Connection conexao = null;

        try {
            conexao = dataSource.getConnection();

            PreparedStatement stmt = conexao.prepareStatement("SELECT nome, login, senha FROM usuario LIMIT	?");
            stmt.setInt(1, max);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                resultado.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            // tratamento
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    // trate o erro
                }
            }
        }

        return resultado;
    }
}
