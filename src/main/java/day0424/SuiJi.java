package day0424;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SuiJi {

    public static void main(String[] args) {

        List<String> a=new ArrayList<>();//初始化 并赋值
        a.add("科特迪瓦");
        a.add("阿根廷");
        a.add("澳大利亚");
        a.add("塞尔维亚");
        a.add("荷兰");
        a.add("尼日利亚");
        a.add("日本");
        a.add("美国");
        a.add("中国");
        a.add("新西兰");
        a.add("巴西");
        a.add("比利时");
        a.add("韩国");
        a.add("喀麦隆");
        a.add("洪都拉斯");
        a.add("澳大利亚");


        Random ran = new Random();
        String b ;
        for(int i =1;i<=4;i++)
        {
            System.out.println(i+"组");//输出随机获得的String
            for(int j = 0;j<4;j++)
            {
                b = a.get(ran.nextInt(a.size()));
                System.out.print("   "+b);
                a.remove(b);//在集合中删除输出的String
            }
            System.out.println("\n");

        }


    }

}