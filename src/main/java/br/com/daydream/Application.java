package br.com.daydream;

import br.com.daydream.dao.jdbc.TemplateUserDAO;
import br.com.daydream.dao.jpa.UserDao;
import br.com.daydream.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Rivaldo on 04/05/16.
 */
public class Application {

    @Autowired
    private UserDao dao;

    @Autowired
    private TemplateUserDAO jdbcDao;

    public void start() {
        testeListJDBC();
    }

    private void testeSalvar() {
        User usuario = new User();
        usuario.setName("Rivaldo");
        usuario.setLogin("hivakun");
        usuario.setPassword("123456");

        dao.save(usuario);
    }

    private void testeListJDBC() {
        List<User> list = jdbcDao.list(5);
        list.forEach(System.err::println);
    }

}
