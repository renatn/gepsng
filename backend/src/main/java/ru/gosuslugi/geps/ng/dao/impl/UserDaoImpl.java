package ru.gosuslugi.geps.ng.dao.impl;

import ru.gosuslugi.geps.ng.dao.UserDao;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.ServiceException;

import java.util.List;

/**
 * User: renatn
 * Date: 15.01.13
 * Time: 14:09
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public User create(User user) throws ServiceException {
        long id = getNextId();
        user.setUserId(id);
        add("user", id, user);
        return getById(id);
    }

    @Override
    public User update(User user) throws ServiceException {
        User found = getById(user.getUserId());
        if (found == null) {
            throw new ServiceException("User not found");
        }
        add("user", user.getUserId(), user);
        return getById(user.getUserId());
    }

    @Override
    public User getById(Long id) throws ServiceException {
        return get("user", id, User.class);
    }

    @Override
    public User findByFacebookId(String facebookId) throws ServiceException {
        List<User> users = getAll("user", User.class);
        for (User user : users) {
            if (facebookId.equals(user.getFacebookId())) {
                return user;
            }
        }
        return null;
    }
}
