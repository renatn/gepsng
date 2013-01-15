package ru.gosuslugi.geps.ng.model;

/**
 * User: rnasyrov
 * Date: 16.12.12
 * Time: 12:24
 */
public class User {

    private Long userId;
    private String name;
    private String facebookId;

    public User() {
    }

    public User(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
