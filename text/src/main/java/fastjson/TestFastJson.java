package fastjson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-23 15:49
 **/
public class TestFastJson {
    @Test
    public void test1(){
        Student zhangsan = Student.builder().id(1).name("张三").build();
        Student lisi = Student.builder().id(2).name("李四").build();
        ArrayList<Student> students = new ArrayList<>();
        students.add(zhangsan);
        students.add(lisi);
        String jsonString = JSON.toJSONString(students);
     //   System.out.println(jsonString);
        ArrayList<Student> list = JSON.parseObject(jsonString, new TypeReference<ArrayList<Student>>() {
        });
        System.out.println(list);
    }
}
