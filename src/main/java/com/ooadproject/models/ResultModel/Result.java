package com.ooadproject.models.ResultModel;

import org.bson.Document;

public class Result {
    Document resultDocument;

    public Result(String username, String createdBy, String quizId, String quizName, String quizCreatedOn,
            String quizAttemptedOn,
            int marks) {
        resultDocument = new Document();
        resultDocument.append("username", username).append("createdBy", createdBy).append("quizId", quizId)
                .append("quizName", quizName)
                .append("quizCreatedOn", quizCreatedOn).append("quizAttemptedOn", quizAttemptedOn)
                .append("marks", marks);
    }

    public Result(Document resulDocument) {
        this.resultDocument = resulDocument;
    }

    public Document getResultDocument() {
        return this.resultDocument;
    }

    public String getQuizId() {
        return this.resultDocument.get("quizId").toString();
    }

    public String getUsername() {
        return this.resultDocument.get("username").toString();
    }

    public String getCreatedBy() {
        return this.resultDocument.get("createdBy").toString();
    }

    public String getQuizName() {
        return this.resultDocument.get("quizName").toString();
    }

    public String getQuizCreatedOn() {
        return this.resultDocument.get("quizCreatedOn").toString().substring(0,10)+" - "+this.resultDocument.get("quizCreatedOn").toString().substring(11,19);
    }

    public String getQuizAttemptedOn() {
        return this.resultDocument.get("quizAttemptedOn").toString().substring(0,10)+" - "+this.resultDocument.get("quizAttemptedOn").toString().substring(11,19);
    }

    public int getMarks() {
        return Integer.parseInt(this.resultDocument.get("marks").toString());
    }

}
