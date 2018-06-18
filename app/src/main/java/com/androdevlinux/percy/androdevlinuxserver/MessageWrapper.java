package com.androdevlinux.percy.androdevlinuxserver;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageWrapper implements Serializable {

    private String message;
    private MessageType messageType;
    private MessageSubType messageSubType;

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("messageType")
    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @JsonProperty("messageSubType")
    public MessageSubType getMessageSubType() {
        return messageSubType;
    }

    public void setMessageSubType(MessageSubType messageSubType) {
        this.messageSubType = messageSubType;
    }

    public enum MessageType {
        NORMAL, CONNECTION_MESSAGE, DISCONNECTION_MESSAGE, REGISTERED_DEVICES
    }

    public enum MessageSubType {
        STARTUP, SALES, SALES_INVOICE, SALES_ITEM, LOGOUT, SALES_CUSTOM_CATEGORY_ITEM, POST_SOT, LOGIN_CHECK, SERVICE_SOT_DETAILS, SERVICE_POINT, PRINT_SOT, EZY_SALES_INVOICE
    }

}
