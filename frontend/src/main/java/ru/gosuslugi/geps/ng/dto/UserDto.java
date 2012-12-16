package ru.gosuslugi.geps.ng.dto;

import ru.gosuslugi.geps.ng.model.User;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: rnasyrov
 * Date: 16.12.12
 * Time: 12:15
 */

@XmlRootElement(name = "user")
public class UserDto {

    private Long userId;
    private String name;

    public UserDto() {
    }

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
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

}
