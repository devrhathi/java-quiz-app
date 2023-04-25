package com.ooadproject.participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.ooadproject.models.Database.Database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.ooadproject.App;
import com.ooadproject.models.ResultModel.Result;
import com.ooadproject.models.UserModel.SingletonFactoryUser;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;;

public class ParticipantDashboardController {
    @FXML
    private TableView<Result> resultsTable;

    @FXML
    private TextField quizID;

    @FXML
    private TableColumn<Result, String> quizTitleColumn;

    @FXML
    private TableColumn<Result, String> createdOnColumn;
    @FXML
    private TableColumn<Result, String> attemptedOnColumn;

    @FXML
    private TableColumn<Result, String> createdByColumn;

    @FXML
    private TableColumn<Result, Integer> marksColumn;

    MongoCollection<Document> collection;

    List<Result> results;

    public void initialize() {
        collection = Database.getInstance().getCollection("result");

        quizTitleColumn.setCellValueFactory(new PropertyValueFactory<>("quizName"));
        createdByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        createdOnColumn.setCellValueFactory(new PropertyValueFactory<>("quizCreatedOn"));
        attemptedOnColumn.setCellValueFactory(new PropertyValueFactory<>("quizAttemptedOn"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));

        loadQuizzesAttempted();

        resultsTable.getItems().addAll(results);
    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("authentication");
    }

    private void loadQuizzesAttempted() {
        String username = SingletonFactoryUser.getInstance().getUser().getUsername();
        MongoCursor<Document> cursor = collection.find(Filters.eq("username", username)).iterator();
        results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            Result quiz = new Result(doc);
            results.add(quiz);
        }

        cursor.close();
    }

    @FXML
    private void handleJoinQuiz() throws IOException {
        if (quizID.getText().length() == 0)
            return;
        QuizListController.setQuizId(quizID.getText());
        App.setRoot("quizlist");
    }
}
