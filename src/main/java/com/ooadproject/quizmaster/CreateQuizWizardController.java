package com.ooadproject.quizmaster;

import com.ooadproject.App;
import com.ooadproject.models.Database.Database;
import com.ooadproject.models.QuizModel.Question;
import com.ooadproject.models.QuizModel.Quiz;
import com.ooadproject.models.UserModel.SingletonFactoryUser;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;

public class CreateQuizWizardController {

    @FXML
    private TextField filePathTextField;

    @FXML
    private TextField quizname;

    @FXML
    private TextField quizcategory;

    MongoCollection<Document> collection;

    @FXML
    private void handleSelectFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) filePathTextField.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            filePathTextField.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("quizmasterdashboard");
    }

    @FXML
    private void handleCreateQuiz() throws IOException {
        if (createQuizDB())
            App.setRoot("quizmasterdashboard");
    }

    public void initialize() {
        collection = Database.getInstance().getCollection("quiz");
    }

    private boolean createQuizDB() throws IOException {
        Quiz quizObj = readCsv();
        try {
            collection.insertOne(quizObj.getQuizDocument());
            return true;
        } catch (Exception e) {
            System.out.println("Insert operation failed: " + e.getMessage());
            return false;
        }
    }

    private Quiz readCsv() throws IOException {
        String line;
        String csvSplitBy = ",";
        List<Document> questionDocuments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePathTextField.getText()))) {
            // Read the header line
            br.readLine();

            // Read each line of the CSV file
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(csvSplitBy);
                String question = fields[0];
                String option1 = fields[1];
                String option2 = fields[2];
                String option3 = fields[3];
                String option4 = fields[4];
                String correctOption = fields[5];
                int marks = Integer.parseInt(fields[6]);

                // Create a new question document
                Question questionObj = new Question(question, option1, option2, option3, option4, correctOption, marks);
                questionDocuments.add(questionObj.getQuestionDocument());
            }
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Quiz quizObj = new Quiz(SingletonFactoryUser.getInstance().getUser().getUsername(), quizname.getText(),
                quizcategory.getText(), questionDocuments, formattedDateTime);
        return quizObj;
    }
}