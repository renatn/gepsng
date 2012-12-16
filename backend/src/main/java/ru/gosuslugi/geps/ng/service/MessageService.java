package ru.gosuslugi.geps.ng.service;

import ru.gosuslugi.geps.ng.model.Message;

/**
 * User: rnasyrov
 * Date: 16.12.12
 * Time: 12:48
 */
public interface MessageService {

    Message getMessageById(Long messageId);

    Message send(Long messageId);

}
