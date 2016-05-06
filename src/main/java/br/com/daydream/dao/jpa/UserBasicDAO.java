package br.com.daydream.dao.jpa;

import br.com.daydream.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by Rivaldo on 05/05/16.
 */
@Repository
public class UserBasicDAO {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void save(User user) {
        getEntityManager().persist(user);
    }
}
