package ru.gosuslugi.geps.ng.service;

import ru.gosuslugi.geps.ng.model.User;

import java.util.List;

/**
 * User: rnasyrov
 * Date: 16.12.12
 * Time: 12:23
 */
public interface UserService {

    User getCurrentUser();
    User getUserById(Long userId);
    List<User> getOrganizations();

}
