package ru.gosuslugi.geps.ng.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: renatn
 * Date: 23.11.12
 * Time: 15:27
 */

@XmlRootElement(name = "message")
public class MessageDto {

    private Long messageId;
    private String subject;
    private String text;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
