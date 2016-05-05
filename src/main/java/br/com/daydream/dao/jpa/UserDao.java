package br.com.daydream.dao.jpa;

import br.com.daydream.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author rivaldo
 *         Created on 02/05/2016.
 */
public interface UserDao extends CrudRepository<User, Long> {

    Long countByName(String name);
}