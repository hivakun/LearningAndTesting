package br.com.daydream.dao.jpa;

import br.com.daydream.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author rivaldo
 * Created on 02/05/2016.
 */
public interface UserCRUDDao extends CrudRepository<User, Long> {

    Long countByPassword(String password);

    @Query("select p from User p where p.name like %?1")
    List<User> findByName(String name);

    Stream<User> readAllByNameNotNull();
}