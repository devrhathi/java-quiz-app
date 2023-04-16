package com.ooadproject;

import java.io.IOException;

import javafx.fxml.FXML;

public class QuizMasterDashboardController {
    @FXML
    private void handleCreateQuiz() throws IOException {
        App.setRoot("createquizwizard");
    }
}
