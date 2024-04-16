package com.tech.blog.entities;

public class Message {

    private String content;
    private String type;
    private String cssClass;

    public Message(String content, String type, String cssClass) { // Constructor with parameters to initialize the Message object
        this.content = content;
        this.type = type;
        this.cssClass = cssClass;
    }

//    getters and setters
    public String getContent() { 
        return content;          
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { // Setter method for setting the type
        this.type = type; //assigning the value of type parameter to the type instance
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

}


