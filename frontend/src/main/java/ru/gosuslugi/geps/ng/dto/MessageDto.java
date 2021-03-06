package ru.gosuslugi.geps.ng.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import ru.gosuslugi.geps.ng.model.Message;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * User: renatn
 * Date: 23.11.12
 * Time: 15:27
 */

@XmlRootElement(name = "message")
public class MessageDto {

    private Long messageId;
    private UserDto sender;
    private UserDto recipient;
    private String subject;
    private String text;
    private Date updateDate;
    private Date sendDate;

    // Frontend fields
    private String action;
    private Boolean selected;

    public MessageDto() {
    }

    public MessageDto(Message message) {
        this.messageId = message.getMessageId();
        this.subject = message.getSubject();
        this.text = message.getText();
        this.updateDate = message.getUpdateDate();
        this.sendDate = message.getSendDate();
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public UserDto getSender() {
        return sender;
    }

    public void setSender(UserDto sender) {
        this.sender = sender;
    }

    public UserDto getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDto recipient) {
        this.recipient = recipient;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
