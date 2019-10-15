package neo4j;


import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

public class Enableneo4j {
    private static final File databaseDirectory = new File("D:\\Program Files\\neo4j-community-3.5.11\\data\\databases\\graph.db2");
    GraphDatabaseService graphDb;

    public enum mylables implements Label {
        person
    }

    public enum myRelationship implements RelationshipType {
        love
    }
    Node firstnode;
    Node secondnode;

    void carateDb() {
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(databaseDirectory);
        Transaction tx = graphDb.beginTx();
        try{
            firstnode = graphDb.createNode();
            firstnode.setProperty("name","张三");
            firstnode.addLabel(mylables.person);

            secondnode = graphDb.createNode();
            secondnode.setProperty("name","张红");
            Relationship relationshipTo = firstnode.createRelationshipTo(secondnode, myRelationship.love);
            relationshipTo.setProperty("dengji","1");
            tx.success();
        } catch (Exception e) {
            tx.failure();
        }
    }
    void deleteData(){
        Transaction tx = graphDb.beginTx();
        try {
            firstnode.getSingleRelationship(myRelationship.love,Direction.OUTGOING).delete();
            firstnode.delete();
            secondnode.delete();

        }catch (Exception e){
            tx.failure();
        }

    }
    void serchNode(){

    }

    void shatdown() {
        graphDb.shutdown();
    }

    private static void registershutdownHook(final GraphDatabaseService graphDb) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }

    public static void main(String[] args) {
        Enableneo4j hello = new Enableneo4j();
        hello.carateDb();
        hello.shatdown();
    }
}
