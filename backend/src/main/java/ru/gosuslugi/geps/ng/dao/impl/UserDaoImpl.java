package ru.gosuslugi.geps.ng.dao.impl;

import ru.gosuslugi.geps.ng.dao.UserDao;
import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.ServiceException;

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
    public User getById(Long id) throws ServiceException {
        return get("user", id, User.class);
    }

}
