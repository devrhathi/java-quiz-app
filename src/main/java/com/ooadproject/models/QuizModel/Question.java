package com.ooadproject.models.QuizModel;

import org.bson.Document;

public class Question {
    Document questionDocument;

    public Question(String question, String option1, String option2, String option3, String option4,
            String correctOption, int mark) {
        questionDocument = new Document();
        questionDocument.append("question", question).append("option1", option1).append("option2", option2)
                .append("option3", option3).append("option4", option4).append("correctOption", correctOption)
                .append("mark", mark);
    }

    public Question(Document questionDocument) {
        this.questionDocument = questionDocument;
    }

    public Document getQuestionDocument() {
        return this.questionDocument;
    }

    public String getQuestion() {
        return this.questionDocument.get("question").toString();
    }

    public String getOption1() {
        return this.questionDocument.get("option1").toString();
    }

    public String getOption2() {
        return this.questionDocument.get("option2").toString();
    }

    public String getOption3() {
        return this.questionDocument.get("option3").toString();
    }

    public String getOption4() {
        return this.questionDocument.get("option4").toString();
    }

    public int checkAns(String option) {
        String corr = this.questionDocument.get("correctOption").toString();
        if (option.equalsIgnoreCase(corr)) {
            return Integer.parseInt(this.questionDocument.get("mark").toString());
        } else {
            return 0;
        }
    }

    public int getMark() {
        return Integer.parseInt(this.questionDocument.get("mark").toString());
    }
}
