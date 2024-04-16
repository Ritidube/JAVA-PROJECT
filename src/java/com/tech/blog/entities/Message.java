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
    public String getContent() { // Getter method for retrieving the content
        return content;          // Returning the content
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


//This class has three private instance variables: content, type, and cssClass, representing the content of the message, the type of message (e.g., success, error), and the CSS class to be applied for styling the message, respectively.

//The class includes a constructor to initialize these variables, along with getter and setter methods for accessing and modifying the values of these variables.

//Overall, this class encapsulates the properties and behavior of a message entity, providing a convenient way to work with messages within a Java application.