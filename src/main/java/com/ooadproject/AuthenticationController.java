package com.ooadproject;

import java.io.IOException;

import com.ooadproject.models.UserModel.SingletonFactoryUser;
import com.ooadproject.models.UserModel.User;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.Parent;
import javafx.scene.Scene;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AuthenticationController {
    @FXML
    private Parent createQuizWizardParent;
    @FXML
    private Scene createQuizWizardScene;

    @FXML
    private TextField loginUsernameField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField signUpUsernameField;

    @FXML
    private PasswordField signUpPasswordField;

    @FXML
    private TextField signUpNameField;

    @FXML
    private Button loginAsQuizMasterButton;

    @FXML
    private Button loginAsParticipantButton;

    @FXML
    private Button signupAsQuizMasterButton;

    @FXML
    private Button signupAsParticipantButton;

    @FXML
    private Button goBack;

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    SingletonFactoryUser singletonFactoryUser = SingletonFactoryUser.getInstance();

    
    @FXML
    private void loginAsQuizMaster() throws IOException {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        singletonFactoryUser.setUser("", username, password, "quizmaster");
        System.out.println("Login as QuizMaster clicked with username: " + username + " and password: " + password);
        if(loginDB())
            App.setRoot("quizmasterdashboard");
    }

    @FXML
    private void loginAsParticipant() throws IOException {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        singletonFactoryUser.setUser("", username, password, "participant");
        System.out.println("Login as Participant clicked with username: " + username + " and password: " + password);
        if(loginDB())
            App.setRoot("participantdashboard");
    }

    @FXML
    private void signupAsQuizMaster() throws IOException {
        String name = signUpNameField.getText();
        String username = signUpUsernameField.getText();
        String password = signUpPasswordField.getText();
        System.out.println(
                "Signup as QuizMaster clicked with name: " + name + ", username: " + username + " and password: " + password);
        singletonFactoryUser.setUser(name, username, password, "quizmaster");
        if(registerDB())
            App.setRoot("quizmasterdashboard");

    }

    @FXML
    private void signupAsParticipant() throws IOException {
        String name = signUpNameField.getText();
        String username = signUpUsernameField.getText();
        String password = signUpPasswordField.getText();
        System.out.println("Signup as Participant clicked with name: " + name + ", username: " + username + " and password: "
                + password);

        singletonFactoryUser.setUser(name, username, password, "participant");
        if(registerDB())
            App.setRoot("quizmasterdashboard");
        App.setRoot("participantdashboard");

    }

    @FXML
    private void switchToResult() throws IOException {
        App.setRoot("result");
    }

    public void initialize() {
        mongoClient = MongoClients
                .create("mongodb+srv://admin:ooadproject@cluster0.95wbe.mongodb.net/?retryWrites=true&w=majority");
         database = mongoClient.getDatabase("quizapp");
         collection = database.getCollection("users");
    }

    private boolean loginDB(){
        User user = singletonFactoryUser.getUser();
        Document doc = new Document().append("username", user.getUsername()).append("password", user.getPassword()).append("role", user.getRole());;
        Document result;
        try {
            result = collection.find(doc).first();
            if(result != null)
            return true;
            else return false;
        } catch (Exception e) {
            System.out.println("Insert operation failed: " + e.getMessage());
            return false;
        }
    }

    private boolean registerDB(){
        User user = singletonFactoryUser.getUser();
        Document doc = new Document().append("name", user.getName()).append("username", user.getUsername()).append("password", user.getPassword()).append("role", user.getRole());

        try {
            // insert the document into the collection
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.out.println("Insert operation failed: " + e.getMessage());
            return false;
        }
    }
}
