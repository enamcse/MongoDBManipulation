/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbmanipulation;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Iterator;
import org.bson.Document;

/**
 *
 * @author Enamul
 */
public class MongoDBManipulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserInterface.main(args);
        try{
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase db = mongoClient.getDatabase("test" );
        System.out.println("Connect to database successfully");
//        boolean auth = db.authenticate(myUserName, myPassword);
//        System.out.println("Authentication: "+auth);
        
        MongoCollection<Document> coll = db.getCollection("animals");
        
        FindIterable<Document> cursor = coll.find();
            int i = 0;
            for (Iterator iterator = cursor.iterator(); iterator.hasNext();i++) {
                System.out.println(iterator.next());
                if(i==100) break;
            }
        
        }catch(Exception e){
            System.out.println("Failed!");
        }
    }
    
}
