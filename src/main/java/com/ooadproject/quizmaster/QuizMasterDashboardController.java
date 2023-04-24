package com.ooadproject.quizmaster;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.ooadproject.App;
import com.ooadproject.models.UserModel.SingletonFactoryUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import com.ooadproject.models.QuizModel.Quiz;
import com.ooadproject.models.ResultModel.Result;
import com.ooadproject.ResultController;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuizMasterDashboardController {
    @FXML
    private TableView<Quiz> quizTable;

    @FXML
    private TableColumn<Quiz, String> quizIdColumn;

    @FXML
    private TableColumn<Quiz, String> quizTitleColumn;

    @FXML
    private TableColumn<Quiz, LocalDateTime> createdOnColumn;

    @FXML
    private TableColumn<Quiz, LocalDateTime> categoryColumn;

    @FXML
    VBox vbox;

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    List<Quiz> quizList;

    private void getQuizzesFromDB() {
        String username = SingletonFactoryUser.getInstance().getUser().getUsername();
        MongoCursor<Document> cursor = collection.find(Filters.eq("username", username)).iterator();
        quizList = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            Quiz quiz = new Quiz(doc);
            quizList.add(quiz);
        }
        cursor.close();
    }

    public void initialize() {
        mongoClient = MongoClients
                .create("mongodb+srv://admin:ooadproject@cluster0.95wbe.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("quizapp");
        collection = database.getCollection("quiz");

        quizIdColumn.setCellValueFactory(new PropertyValueFactory<>("_id"));
        quizTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        createdOnColumn.setCellValueFactory(new PropertyValueFactory<>("createdOn"));

        getQuizzesFromDB();

        // Populate table with sample data
        quizTable.getItems().addAll(quizList);

        // Add click listener to table rows
        quizTable.setOnMouseClicked(event -> {
            Quiz selectedQuiz = quizTable.getSelectionModel().getSelectedItem();
            if (selectedQuiz != null) {
                if (event.getClickCount() == 1) {
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(selectedQuiz.get_id());
                    clipboard.setContent(content);
                } else if (event.getClickCount() == 2) {
                    ResultController.setQuizId(selectedQuiz.get_id());
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/com/ooadproject/result.fxml"));
                        Parent root = loader.load();
                        Scene currentScene = vbox.getScene();
                        Scene newScene = new Scene(root);
                        Stage stage = (Stage) currentScene.getWindow();
                        stage.setScene(newScene);
                        stage.show();

                        // System.out.println("GOINGGGGGGGGGGGGGGGGGGGG!");
                        // App.setRoot("result");
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    @FXML
    private void handleCreateQuiz() throws IOException {
        App.setRoot("createquizwizard");
    }

}
