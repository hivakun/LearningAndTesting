package br.com.daydream.dao.jdbc;

import br.com.daydream.model.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcImportUsuarios {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public List<User> obterUsuarios(File arquivo) {
        List<User> resultado = new ArrayList<User>();
        if (arquivo.exists()) {
            InputStream input = null;
            try {
                input = new FileInputStream(arquivo);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String linha = null;
                while ((linha = reader.readLine()) != null) {
                    String[] componentes = linha.split(",");
                    User usuario = new User();
                    usuario.setNome(componentes[0]);
                    usuario.setLogin(componentes[1]);
                    usuario.setSenha(componentes[2]);
                    resultado.add(usuario);
                }
            } catch (IOException ex) {

            }
        }
        return resultado;
    }

    public void importarUsuarios(File arquivo) {
        final List<User> usuarios = getUser();
        jdbcTemplate.batchUpdate("insert into usuario (nome, login, senha) values (?,?,?)",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        User usr = usuarios.get(i);
                        ps.setString(1, usr.getNome());
                        ps.setString(2, usr.getLogin());
                        ps.setString(3, usr.getSenha());
                    }

                    public int getBatchSize() {
                        return usuarios.size();
                    }
                }
        );
    }

    private List<User> getUser() {
        User usuario = new User();
        usuario.setNome("Rivaldo");
        usuario.setLogin("hivakun");
        usuario.setSenha("123456");

        return Lists.newArrayList(usuario);
    }


}
