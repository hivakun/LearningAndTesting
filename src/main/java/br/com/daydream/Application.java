package br.com.daydream;

import br.com.daydream.dao.jdbc.TemplateUserDAO;
import br.com.daydream.dao.jpa.UserCRUDDao;
import br.com.daydream.model.User;
import br.com.daydream.util.UserSample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Rivaldo on 04/05/16.
 */
public class Application {

    @Autowired
    private UserCRUDDao dao;

    @Autowired
    private TemplateUserDAO jdbcDao;

    public void start() {
        addSampleUser();
    }

    private void testSave() {
        User usuario = new User();
        usuario.setName("Rivaldo");
        usuario.setLogin("hivakun");
        usuario.setPassword("123456");

        dao.save(usuario);
    }

    private void testListJDBC() {
        List<User> list = jdbcDao.list(5);
        list.forEach(System.err::println);
    }

    private void testStreamQuery() {
        dao.readAllByNameNotNull().forEach(System.err::println);
    }

    private void testCountPassword() {
        Long count = dao.countByPassword("123456");
        System.err.println("Count: " + count);
    }

    private void testFindByName() {
        List<User> users = dao.findByName("Ormazabal");
        users.forEach(System.err::println);
    }

    private void addSampleUser() {
        dao.save(UserSample.getSample());
    }
}
