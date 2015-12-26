package mongodbmanipulation;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enamul
 */
public class Constants {
    public static MongoClient mongoClient;
    public static MongoDatabase db;
    public static MongoIterable<String> collectionNames;
    public static MongoIterable<String> databaseNames;
    public static List<Document> documents;
    public static MongoCollection<Document> collections;
    public static Object[] columns;
    public static DefaultTableModel model;
    public static int _idcol=-1;
    public static boolean columnUpdating=false;
    
}
