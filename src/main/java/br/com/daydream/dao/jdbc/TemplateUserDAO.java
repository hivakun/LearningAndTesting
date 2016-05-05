package br.com.daydream.dao.jdbc;

import br.com.daydream.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TemplateUserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> list(int max) {

        Integer[] parametros = {max};

        return jdbcTemplate.query("SELECT nome, login, senha FROM usuario LIMIT ?", parametros,
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User(rs.getString(1), rs.getString(2), rs.getString(3));
                    }
                });
    }
}
