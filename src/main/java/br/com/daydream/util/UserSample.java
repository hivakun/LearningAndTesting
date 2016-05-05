package br.com.daydream.util;

import br.com.daydream.model.User;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Classe utilitária para criar uma lista de usuários de exemplo.
 *
 * @author rivaldo
 * Created on 05/05/2016.
 */
public class UserSample {

    public static List<User> getSample() {

        User usuario1 = new User();
        usuario1.setName("Ormazabal");
        usuario1.setLogin("ozama");
        usuario1.setPassword("123456");

        User usuario2 = new User();
        usuario2.setName("Herbeton");
        usuario2.setLogin("coroinha");
        usuario2.setPassword("123456");

        User usuario3 = new User();
        usuario3.setName("Felipe");
        usuario3.setLogin("amoMeuIrmao");
        usuario3.setPassword("123456");

        return Lists.newArrayList(usuario1, usuario2, usuario3);
    }

}
