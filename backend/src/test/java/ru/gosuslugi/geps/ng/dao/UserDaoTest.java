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

    @Test
    public void shouldFindByFacebookId() throws Exception {
        UserDao dao = new UserDaoImpl();

        User user = new User();
        user.setName("Sergey Brin");
        user.setFacebookId("1234567890");
        User createdUser = dao.create(user);

        User found = dao.findByFacebookId(user.getFacebookId());
        Assert.assertEquals(user.getName(), found.getName());
        Assert.assertEquals(createdUser.getUserId(), found.getUserId());
        Assert.assertEquals(user.getFacebookId(), found.getFacebookId());

    }

    @Test
    public void shouldUpdateUser() throws Exception {
        UserDao dao = new UserDaoImpl();

        User user = new User();
        user.setFacebookId("0987654321");
        User createdUser = dao.create(user);

        createdUser.setName("Bill Gates");
        createdUser.setFacebookId("987654321");
        User updatedUser = dao.update(createdUser);

        Assert.assertEquals(createdUser.getName(), updatedUser.getName());
        Assert.assertEquals(createdUser.getUserId(), updatedUser.getUserId());
        Assert.assertEquals(createdUser.getFacebookId(), updatedUser.getFacebookId());
    }

    @Test
    public void shouldFindExistUser() throws Exception{
        UserDao dao = new UserDaoImpl();
        User user = dao.findByFacebookId("100002223950429");
        Assert.assertEquals("100002223950429", user.getFacebookId());
        Assert.assertEquals(Long.valueOf(4444L), user.getUserId());
    }

}

