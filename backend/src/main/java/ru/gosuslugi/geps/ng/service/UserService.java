package ru.gosuslugi.geps.ng.service;

import ru.gosuslugi.geps.ng.model.User;

/**
 * User: rnasyrov
 * Date: 16.12.12
 * Time: 12:23
 */
public interface UserService {

    User getCurrentUser();
    User getUserById(Long userId);

}
