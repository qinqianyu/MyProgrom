package mysql;

import org.neo4j.driver.v1.*;

import java.sql.Connection;
import java.util.ArrayList;

import static org.neo4j.driver.v1.Values.parameters;

public class neo4jinsert implements AutoCloseable {
    private final Driver driver;
    private final Session session;


    public neo4jinsert(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
         session = driver.session();
    }

    @Override
    public void close() throws Exception {
        session.close();
        driver.close();
    }

    public static void main(String... args) throws Exception {
        Connection conn = null;
        try (neo4jinsert greeter = new neo4jinsert("bolt://192.168.20.139:7687",
                "neo4j", "123456")) {
            tomysql tomysql = new tomysql();
             conn = tomysql.getConn();

            int start = 1, end = 100;
            ArrayList<Guanxi> getend = tomysql.getend(conn, start, end);
            //计时开始
            Long startTime = System.currentTimeMillis();
            int count=1;
            while (getend.size() != 0) {
                System.out.println(count+"***********"+getend.size());
                count++;
                System.out.println("正在写入neo4j");
                greeter.insert(getend);
                System.out.println("已经写入neo4j");
                start +=100;
                end += 100;
                System.out.println("正在取出数据"+start+"=="+end);
                getend = tomysql.getend(conn, start, end);
                System.out.println("已经取出数据"+start+"=="+end);
            }
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime));
            conn.close();
        }catch (Exception e){
            conn.close();
        }
    }

    public void insert(ArrayList<Guanxi> getend) {
        Transaction tx = session.beginTransaction();
            for (Guanxi row : getend) {
                tx.run("merge (:gongsi{name:$name,gid:$gid})", parameters("name", row.getStartname(), "gid", row.getStart()));
                tx.run("merge (:gongsi{name:$name,gid:$gid})", parameters("name", row.getEndname(), "gid", row.getEnd()));
                tx.run("merge (:gongsi{name:$name,gid:$gid})", parameters("name", row.getEnd2name(), "gid", row.getEnd2()));
                tx.run("merge (:person{name:$name,pid:$pid})", parameters("name", row.getEnd3name(), "pid", row.getEnd3()));
                tx.run("merge (:person{name:$name,pid:$pid})", parameters("name", row.getEnd4name(), "pid", row.getEnd4()));
                tx.run("match (a:gongsi{name:$fname,gid:$fgid}),(b:gongsi{name:$tname,gid:$tgid}) merge (a)-[:投资]->(b)", parameters("fname", row.getStartname(), "fgid", row.getStart(),"tname",row.getEndname(),"tgid",row.getEnd()));
                tx.run("match (a:gongsi{name:$fname,gid:$fgid}),(b:gongsi{name:$tname,gid:$tgid}) merge (a)-[:投资]->(b)", parameters("fname", row.getStartname(), "fgid", row.getStart(),"tname",row.getEnd2name(),"tgid",row.getEnd2()));
                tx.run("match (a:person{name:$pname,pid:$pid}),(b:gongsi{name:$gname,gid:$gid}) merge (a)-[:任职]->(b)", parameters("pname", row.getEnd3name(), "pid", row.getEnd3(),"gname",row.getStartname(),"gid",row.getStart()));
                tx.run("match (a:person{name:$pname,pid:$pid}),(b:gongsi{name:$gname,gid:$gid}) merge (a)-[:任职]->(b)", parameters("pname", row.getEnd4name(), "pid", row.getEnd4(),"gname",row.getStartname(),"gid",row.getStart()));
            }
            tx.success();
    }
}

