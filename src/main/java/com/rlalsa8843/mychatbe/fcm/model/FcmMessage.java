package com.rlalsa8843.mychatbe.fcm.model;

public class FcmMessage {
    private boolean validate_only;
    private Message message;

    public FcmMessage(boolean validate_only, Message message) {
        this.validate_only = validate_only;
        this.message = message;
    }

    public FcmMessage(Message message) {
        this(false, message);
    }

    public boolean isValidate_only() {
        return validate_only;
    }

    public void setValidate_only(boolean validate_only) {
        this.validate_only = validate_only;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
