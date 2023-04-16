package com.ooadproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class AuthenticationController {
    @FXML
    private Parent createQuizWizardParent;
    @FXML
    private Scene createQuizWizardScene;

    @FXML
    private TextField loginEmailField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField signUpEmailField;

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
    private void loginAsQuizMaster() throws IOException {
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();
        System.out.println("Login as QuizMaster clicked with email: " + email + " and password: " + password);
        App.setRoot("quizmasterdashboard");
    }

    @FXML
    private void loginAsParticipant() throws IOException {
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();
        System.out.println("Login as Participant clicked with email: " + email + " and password: " + password);
        App.setRoot("participantdashboard");
    }

    @FXML
    private void signupAsQuizMaster() throws IOException {
        String name = signUpNameField.getText();
        String email = signUpEmailField.getText();
        String password = signUpPasswordField.getText();
        System.out.println(
                "Signup as QuizMaster clicked with name: " + name + ", email: " + email + " and password: " + password);
        App.setRoot("quizmasterdashboard");

    }

    @FXML
    private void signupAsParticipant() throws IOException {
        String name = signUpNameField.getText();
        String email = signUpEmailField.getText();
        String password = signUpPasswordField.getText();
        System.out.println("Signup as Participant clicked with name: " + name + ", email: " + email + " and password: "
                + password);
        App.setRoot("participantdashboard");
    }
}
