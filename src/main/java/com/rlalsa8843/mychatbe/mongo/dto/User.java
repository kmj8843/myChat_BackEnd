package com.rlalsa8843.mychatbe.mongo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {
    @Id
    private String _id;
    private String name;
    private String deviceID;
    private String token;

    public User(String _id, String name, String deviceID, String token) {
        this._id = _id;
        this.name = name;
        this.deviceID = deviceID;
        this.token = token;
    }

    public User(String name, String deviceID, String token) {
        this(null, name, deviceID, token);
//        this.deviceID = deviceID;
//        this.name = name;
//        this.token = token;
    }

    public User() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
