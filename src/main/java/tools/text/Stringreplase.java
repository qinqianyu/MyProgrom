package tools.text;

import java.util.ArrayList;
import java.util.Arrays;

public class Stringreplase {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(
                "个人合伙执照",
                "个人独资执照",
                "个体工商执照",
                "中华人民共和国军官证",
                "中华人民共和国居民身份证",
                "中华人民共和国警官证",
                "公司执照",
                "其他类型",
                "其它有效身份证件",
                "分公司执照",
                "外商承包企业执照",
                "外商投资企业执照",
                "外国（地区）护照",
                "居民证",
                "澳门居民身份证",
                "营业执照",
                "通行证",
                "集团登记证",
                "非公司制企业法人执照",
                "香港居民身份证"
                ));

        String oldstr = "map.put(\"****\",1);";
        String oldword = "****";
        for (String str:list) {
            String replace = oldstr.replace(oldword, str);
            System.out.println(replace);
        }
    }
}