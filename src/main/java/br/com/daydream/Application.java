package br.com.daydream;

import br.com.daydream.dao.jpa.UserDao;
import br.com.daydream.model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Rivaldo on 04/05/16.
 */
public class Application {

    @Autowired
    private UserDao dao;

    public void start() {
        testeSalvar();
    }

    private void testeSalvar() {
        User usuario = new User();
        usuario.setNome("Rivaldo");
        usuario.setLogin("hivakun");
        usuario.setSenha("123456");

        dao.save(usuario);
    }
}
