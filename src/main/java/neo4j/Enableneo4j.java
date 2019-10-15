package neo4j;


import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.util.Map;

public class Enableneo4j {
    private static final File databaseDirectory = new File("D:\\Program Files\\neo4j-community-3.5.11\\data\\databases2\\graph.db");
    GraphDatabaseService graphDb;

    public enum mylables implements Label {
        person,
        female,
        male
    }

    public enum myRelationship implements RelationshipType {
        love
    }

    Node firstnode;
    Node secondnode;

    void carateDb() {
        System.out.println("正在创建数据库");
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(databaseDirectory);
        Transaction tx = graphDb.beginTx();
        try {
            firstnode = graphDb.createNode();
            firstnode.setProperty("name", "张三");
            firstnode.addLabel(mylables.person);

            secondnode = graphDb.createNode();
            secondnode.setProperty("name", "张红");
            secondnode.addLabel(mylables.person);

            Relationship relationshipTo = firstnode.createRelationshipTo(secondnode, myRelationship.love);
            relationshipTo.setProperty("dengji", "1");
            tx.success();
            System.out.println("数据库已经开启");
        } catch (Exception e) {
            tx.failure();
            System.out.println("数据库开启失败");
        }
    }

    void deleteData() {
        System.out.println("正在删除节点");

        Transaction tx = graphDb.beginTx();
        try {
            firstnode.getSingleRelationship(myRelationship.love, Direction.OUTGOING).delete();
            firstnode.delete();
            secondnode.delete();
            System.out.println("删除成功");
        } catch (Exception e) {
            tx.failure();
            System.out.println("删除失败");

        }

    }

    void serchNode() {
        Transaction tx = graphDb.beginTx();
        try {
            ResourceIterator<Node> persons = graphDb.findNodes(mylables.person);
            persons.forEachRemaining((entitytyprgraphnode) -> {
                        System.out.print(entitytyprgraphnode.getProperties("name"));
                        System.out.print("->");
                        Iterable<Relationship> relationships = entitytyprgraphnode.getRelationships();
                        relationships.forEach((relationship) -> {
                            Node endNode = relationship.getEndNode();
                            System.out.print(endNode.getProperties("name"));
                        });
                        System.out.println();
                    }
            );
        } catch (Exception e) {
        }
    }

    void shatdown() {
        System.out.println("正在关闭数据库");
        graphDb.shutdown();
        System.out.println("数据库已经关闭");
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
        hello.serchNode();
        hello.deleteData();
        hello.shatdown();
    }
}
