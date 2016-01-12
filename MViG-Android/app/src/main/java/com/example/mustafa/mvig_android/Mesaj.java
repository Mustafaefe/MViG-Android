package com.example.mustafa.mvig_android;

/**
 * Created by Mustafa on 13.12.2015.
 */
public class Mesaj {

    private String destination_number;
    private String message_content;
    private String created_at;

    public Mesaj(String destination_number, String message_content, String created_at) {
        this.destination_number = destination_number;
        this.message_content = message_content;
        this.created_at = created_at;
    }

    public String getDestination_number() {
        return destination_number;
    }

    public void setDestination_number(String destination_number) {
        this.destination_number = destination_number;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
