module com.ooadproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;

    opens com.ooadproject to javafx.fxml;
    opens com.ooadproject.participant to javafx.fxml;
    opens com.ooadproject.quizmaster to javafx.fxml;

    opens com.ooadproject.models.QuizModel to javafx.base;
    opens com.ooadproject.models.UserModel to javafx.base;
    opens com.ooadproject.models.ResultModel to javafx.base;

    exports com.ooadproject;
    exports com.ooadproject.participant;
    exports com.ooadproject.quizmaster;
    exports com.ooadproject.models.UserModel;
    exports com.ooadproject.models.QuizModel;
    exports com.ooadproject.models.ResultModel;
    exports com.ooadproject.models.Database;
}