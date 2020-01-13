//从命令行读入一个字符串，表示一个年份，
//        输出该年的世界杯冠军是哪支球队。
//        如果该 年没有举办世界杯，则输出：没有举办世界杯。
//        附：世界杯冠军以及对应的夺冠年份，
//        请参考本章附录。 附录
//        历届世界杯冠军
//        届数	举办年份	举办地点	冠军
//        第一届	1930年	乌拉圭	乌拉圭
//        第二届	1934年	意大利	意大利
//        第三届	1938年	法国	意大利
//        第四届	1950年	巴西	乌拉圭
//        第五届	1954年	瑞士	西德
//        第六届	1958年	瑞典	巴西
//        第七届	1962年	智利	巴西
//        第八届	1966年	英格兰	英格兰
//        第九届	1970年	墨西哥	巴西
//        第十届	1974年	前西德	西德
//        第十一届	1978年	阿根廷	阿根廷
//        第十二届	1982年	西班牙	意大利
//        第十三届	1986年	墨西哥	阿根廷
//        第十四届	1990年	意大利	西德
//        第十五届	1994年	美国	巴西
//        第十六届	1998年	法国	法国
//        第十七届	2002年	韩日	巴西
//        第十八届	2006年	德国	意大利
//        第十九届	2010年	南非	西班牙
//        第二十届	2014年	巴西	德国
//        2/在原有世界杯Map 的基础上，增加如下功能：
//        读入一支球队的名字，输出该球队夺冠的年份列表。
//        例如，读入“巴西”，应当输出 1958 1962 1970 1994 2002
//        读入“荷兰”，应当输出 没有获得过世界杯

package day0424;
import java.util.Scanner;
import java.util.TreeMap;
public class Map {
    public static void main(String[] args) {

        TreeMap<Integer, String>tm=new TreeMap<>();
        tm.put(1930, "乌拉圭");
        tm.put(1934, "意大利");
        tm.put(1938, "意大利");
        tm.put(1950, "乌拉圭");
        tm.put(1954, "西德");
        tm.put(1958, "巴西");
        tm.put(1962, "巴西");
        tm.put(1966, "英格兰");
        tm.put(1970, "巴西");
        tm.put(1974, "西德");
        tm.put(1978, "阿根廷");
        tm.put(1982, "意大利");
        tm.put(1986, "阿根廷");
        tm.put(1990, "西德");
        tm.put(1994, "巴西");
        tm.put(1998, "法国");
        tm.put(2002, "巴西");
        tm.put(2006, "意大利");
        tm.put(2010, "西班牙");
        tm.put(2014, "德国");
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入年份：");
        int c=sc.nextInt();

        if(tm.get(c)==null)
        {
            System.out.println("没有举办世界杯");
        }
        else
        {
            System.out.println(tm.get(c));
        }
        System.out.println("请输入国家：");
        String country=sc.next();
        sc.close();
        for(int k:tm.keySet())
        {
            if(tm.get(k).equals(country))
            {
                System.out.println(k);
            }
        }

    }

}
