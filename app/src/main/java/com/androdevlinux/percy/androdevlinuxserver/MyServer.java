package com.androdevlinux.percy.androdevlinuxserver;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class MyServer extends NanoHTTPD {
    private final static int PORT = 8080;

    MyServer() throws IOException {
        super(PORT);
        start();
    }

    @Override
    public Response serve(IHTTPSession session) {
        switch (session.getUri()) {
            case "/": {
                String msg = "<html><body><h1>Hello server</h1>\n";
                msg += "<p>We serve " + session.getUri() + " !</p>";
                return newFixedLengthResponse(msg + "</body></html>\n");
            }
            case "/login": {
                Log.i(MyServer.class.getSimpleName(), session.getParms().get("username"));
                Log.i(MyServer.class.getSimpleName(), session.getParms().get("password"));
                String user_name = session.getParms().get("username");
                String password = session.getParms().get("password");
                if (user_name.equals("percy") && password.equals("12345")) {
                    MessageWrapper messageWrapper = new MessageWrapper();
                    messageWrapper.setMessage("SUCCESS");
                    messageWrapper.setMessageType(MessageWrapper.MessageType.NORMAL);
                    messageWrapper.setMessageSubType(MessageWrapper.MessageSubType.LOGIN_CHECK);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    String messageJson;
                    try {
                        messageJson = mapper.writeValueAsString(messageWrapper);
                    } catch (JsonProcessingException e) {
                        messageJson = "ERROR";
                        e.printStackTrace();
                    }
                    return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "application/json", messageJson);
                } else {
                    MessageWrapper messageWrapper = new MessageWrapper();
                    messageWrapper.setMessage("ERROR");
                    messageWrapper.setMessageType(MessageWrapper.MessageType.NORMAL);
                    messageWrapper.setMessageSubType(MessageWrapper.MessageSubType.LOGIN_CHECK);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    String messageJson;
                    try {
                        messageJson = mapper.writeValueAsString(messageWrapper);
                    } catch (JsonProcessingException e) {
                        messageJson = "ERROR";
                        e.printStackTrace();
                    }
                    return newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "application/json", messageJson);
                }
            }
            default: {
                String msg = "<html><body><h1>Hello server</h1>\n";
                msg += "<p>We serve " + session.getUri() + " !</p>";
                return newFixedLengthResponse(msg + "</body></html>\n");
            }
        }
    }
}
