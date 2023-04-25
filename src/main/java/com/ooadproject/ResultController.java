package com.ooadproject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ooadproject.models.ResultModel.Result;

public class ResultController {
    private static String _id;

    public static void setQuizId(String quizId) {
        _id = quizId;
    }

    @FXML
    private TableView<Result> resultTable;

    @FXML
    private TableColumn<Result, String> attemptedByColumn;

    @FXML
    private TableColumn<Result, String> quizNameColumn;

    @FXML
    private TableColumn<Result, String> quizCreatedOnColumn;

    @FXML
    private TableColumn<Result, String> quizAttemptedOnColumn;

    @FXML
    private TableColumn<Result, Integer> marksColumn;

    @FXML
    private void goBack() throws IOException{
        App.setRoot("authentication");
    }

    List<Result> results;
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;


    public void initialize() {
        mongoClient = MongoClients
                .create("mongodb+srv://admin:ooadproject@cluster0.95wbe.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("quizapp");
        collection = database.getCollection("result");

        attemptedByColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        quizNameColumn.setCellValueFactory(new PropertyValueFactory<>("quizName"));
        quizCreatedOnColumn.setCellValueFactory(new PropertyValueFactory<>("quizCreatedOn"));
        quizAttemptedOnColumn.setCellValueFactory(new PropertyValueFactory<>("quizAttemptedOn"));
        marksColumn.setCellValueFactory(new PropertyValueFactory<>("marks"));

        loadResults();
        resultTable.getItems().addAll(results);
    }

    

    private void loadResults() {
        MongoCursor<Document> cursor = collection.find(Filters.eq("quizId", _id)).sort(Sorts.descending("marks"))
                .iterator();
        results = new ArrayList<>();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            System.out.println(doc.get("username").toString());
            Result result = new Result(doc);
            results.add(result);
        }
        cursor.close();
    }

}
