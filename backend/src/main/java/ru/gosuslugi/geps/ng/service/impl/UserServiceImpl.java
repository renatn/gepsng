package ru.gosuslugi.geps.ng.service.impl;

import ru.gosuslugi.geps.ng.model.User;
import ru.gosuslugi.geps.ng.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rnasyrov
 * Date: 16.12.12
 * Time: 12:27
 */
public class UserServiceImpl implements UserService {

    private static List<User> users = new ArrayList<User>();

    static {
        users.add(new User(1L, "ПФР"));
        users.add(new User(2L, "Минкомсвязь России"));
        users.add(new User(3L, "Минобороны"));
    }

    @Override
    public User getCurrentUser() {
        return new User(4444L, "Насыров Р.В.");
    }

    @Override
    public User getUserById(Long userId) {

        if (userId == null) {
            return null;
        }

        if (userId.equals(4444L)) {
            return new User(4444L, "Насыров Р.В.");
        }

        for(User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getOrganizations() {
        return users;
    }

}
