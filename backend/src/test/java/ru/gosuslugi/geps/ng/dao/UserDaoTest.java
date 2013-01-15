package ru.gosuslugi.geps.ng.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.gosuslugi.geps.ng.dao.impl.UserDaoImpl;
import ru.gosuslugi.geps.ng.model.User;

/**
 * User: renatn
 * Date: 15.01.13
 * Time: 14:28
 */
public class UserDaoTest {

    @Test
    public void testCreate() throws Exception {
        UserDao dao = new UserDaoImpl();
        User user = new User();
        user.setName("Renat Nasyrov");
        User createdUser = dao.create(user);
        Assert.assertNotNull(createdUser.getUserId());
        Assert.assertEquals(user.getName(), createdUser.getName());
    }

    @Test
    public void testGetById() throws Exception {
        UserDao dao = new UserDaoImpl();

        User user = new User();
        user.setName("Renat Nasyrov");
        User createdUser = dao.create(user);

        User found = dao.getById(createdUser.getUserId());
        Assert.assertEquals(user.getName(), found.getName());
        Assert.assertEquals(createdUser.getUserId(), found.getUserId());
    }

}

