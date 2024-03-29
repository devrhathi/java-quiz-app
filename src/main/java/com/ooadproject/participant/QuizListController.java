package com.ooadproject.participant;

import com.mongodb.client.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import com.ooadproject.models.Database.Database;
import com.ooadproject.App;
import com.ooadproject.models.ResultModel.Result;
import com.ooadproject.models.QuizModel.Question;
import com.ooadproject.models.QuizModel.Quiz;
import com.ooadproject.models.UserModel.SingletonFactoryUser;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.bson.types.ObjectId;

public class QuizListController {
    private static String quizId;
    MongoCollection<Document> collection;
    Quiz quizList;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    @FXML
    private Button submitButton;

    @FXML
    private void goBack() throws IOException {
        App.setRoot("participantdashboard");
    }

    // Here are the ToggleGroups for each question's options
    private ToggleGroup optionsGroup;

    private int totalMarks;

    public static void setQuizId(String id) {
        quizId = id;
    }

    private HashMap<String, Integer> resultMap;

    private void loadQuizData() {
        ObjectId id = new ObjectId(quizId);
        Document result;
        result = collection.find(new Document("_id", id)).first();
        if (result != null) {
            quizList = new Quiz(result);
        } else {
            System.out.println("Could Not Load Quiz");
        }
        generateQuiz();
    }

    public void initialize() {
        collection = Database.getInstance().getCollection("quiz");
        resultMap = new HashMap<>();
        loadQuizData();
    }

    private void generateQuiz() {
        List<Document> questionList = quizList.getQuestionsList();
        for (Document question : questionList) {
            Question q = new Question(question);

            VBox questionBox = new VBox();
            questionBox.setSpacing(5.0);

            optionsGroup = new ToggleGroup();

            javafx.scene.control.Label questionLabel = new javafx.scene.control.Label(
                    q.getQuestion() + "(Marks : " + q.getMark() + ")");
            RadioButton option1 = new RadioButton(q.getOption1());
            RadioButton option2 = new RadioButton(q.getOption2());
            RadioButton option3 = new RadioButton(q.getOption3());
            RadioButton option4 = new RadioButton(q.getOption4());

            option1.setToggleGroup(optionsGroup);
            option2.setToggleGroup(optionsGroup);
            option3.setToggleGroup(optionsGroup);
            option4.setToggleGroup(optionsGroup);

            option1.setOnAction(e -> {
                if (option1.isSelected()) {
                    resultMap.put(q.getQuestion(), q.checkAns(q.getOption1()));
                }
            });

            option2.setOnAction(e -> {
                if (option2.isSelected()) {
                    resultMap.put(q.getQuestion(), q.checkAns(q.getOption2()));
                }
            });

            option3.setOnAction(e -> {
                if (option3.isSelected()) {
                    resultMap.put(q.getQuestion(), q.checkAns(q.getOption3()));
                }
            });

            option4.setOnAction(e -> {
                if (option4.isSelected()) {
                    resultMap.put(q.getQuestion(), q.checkAns(q.getOption4()));
                }
            });

            questionBox.getChildren().addAll(questionLabel, option1, option2, option3, option4);
            vbox.getChildren().add(questionBox);
        }
    }

    @FXML
    private void handleSubmit() throws IOException {
        totalMarks = 0;
        for (int value : resultMap.values()) {
            totalMarks += value;
        }
        if (saveResultToDB())
            App.setRoot("participantdashboard");
    }

    private boolean saveResultToDB() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        collection = Database.getInstance().getCollection("result");
        Result result = new Result(SingletonFactoryUser.getInstance().getUser().getUsername(), quizList.getUsername(),
                quizList.get_id(), quizList.getTitle(), quizList.getCreatedOn(), formattedDateTime,
                totalMarks);

        try {
            collection.insertOne(result.getResultDocument());
            return true;
        } catch (Exception e) {
            System.out.println("Insert operation failed: " + e.getMessage());
            return false;
        }

    }

}
