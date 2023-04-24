package com.ooadproject.models.Database;

import org.bson.Document;
import com.mongodb.client.*;

public class Database {
    private MongoClient mongoClient = null;
    private MongoDatabase database = null;
    private MongoCollection<Document> collection = null;

    private static Database INSTANCE = new Database();

    private Database() {
        if (this.mongoClient == null) {
            this.mongoClient = MongoClients
                    .create("mongodb+srv://admin:ooadproject@cluster0.95wbe.mongodb.net/?retryWrites=true&w=majority");
            this.database = mongoClient.getDatabase("quizapp");
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public MongoCollection<Document> getCollection(String collection) {
        this.collection = this.database.getCollection(collection);
        return this.collection;
    }

}
