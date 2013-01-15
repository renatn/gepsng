package ru.gosuslugi.geps.ng.dao;

import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.ServiceException;

/**
 * User: renatn
 * Date: 15.01.13
 * Time: 14:09
 */
public interface UserDao {

    User create(User user) throws ServiceException;

    User update(User user) throws ServiceException;

    User getById(Long id) throws ServiceException;

    User findByFacebookId(String facebookId) throws ServiceException;

}
