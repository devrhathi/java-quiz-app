package com.ooadproject.models.UserModel;

public class SingletonFactoryUser {
    private User user;
    private final static SingletonFactoryUser INSTANCE = new SingletonFactoryUser();

    private SingletonFactoryUser() {
    }

    public static SingletonFactoryUser getInstance() {
        return INSTANCE;
    }

    public void setUser(String name, String username, String password, String role) {
        this.user = new User(name, username, password, role);
    }

    public User getUser() {
        return this.user;
    }

}

// class QuizMaster extends User {
// private String name;

// QuizMaster(String name, String email, String password) {
// super(email, password);
// this.name = name;
// }

// public String getName() {
// return name;
// }
// }

// class Participant extends User {
// Participant(String email, String password) {
// super(email, password);
// }
// }

// class FactoryUser{
// public static User createUser(String type){
// if()
// }
// }
