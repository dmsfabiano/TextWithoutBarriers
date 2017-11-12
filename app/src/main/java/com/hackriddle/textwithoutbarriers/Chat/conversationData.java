package com.hackriddle.textwithoutbarriers.Chat;

/**
 * Created by david on 11/12/17.
 */

public class conversationData {
    String user_name;
    String last_message;
    int image_id;

    public conversationData(String user_name, String last_message, int image_id) {
        this.user_name = user_name;
        this.last_message = last_message;
        this.image_id = image_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
