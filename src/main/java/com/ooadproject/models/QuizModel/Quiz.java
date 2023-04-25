package com.ooadproject.models.QuizModel;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bson.Document;

public class Quiz {

    Document quizDocument;
    List<Document> questionList;
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HH:mm:ss");

    public Quiz(String username, String title, String category, List<Document> questionList,
            String createdOn) {
        quizDocument = new Document();
        this.questionList = questionList;
        quizDocument.append("username", username).append("title", title)
                .append("category", category)
                .append("createdOn", createdOn.toString()).append("questionsList", questionList);
    }

    public Quiz(Document doc) {
        this.quizDocument = doc;
        this.questionList = doc.get("questionsList", List.class);
    }

    public String getUsername() {
        return this.quizDocument.get("username").toString();
    }

    public String get_id() {
        return this.quizDocument.get("_id").toString();
    }

    public Document getQuizDocument() {
        return this.quizDocument;
    }

    public List<Document> getQuestionsList() {
        return this.questionList;
    }

    public String getTitle() {
        return this.quizDocument.get("title").toString();
    }

    public String getCategory() {
        return this.quizDocument.get("category").toString();
    }

    public String getCreatedOn() {
        //return this.quizDocument.get("createdOn").toString();
        return this.quizDocument.get("createdOn").toString().substring(0,10) + " - "+this.quizDocument.get("createdOn").toString().substring(11,19);
    }
}