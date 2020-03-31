package qingdao.works.text;

import org.junit.Test;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-27 12:09
 **/
public class Struntil {
    @Test
    public void test() {
     String files=" id,title,content,author,url,publishTime,domain,siteName,crawlerTime,city,informationSourceTypeId,informationTypeId,businessTypeId";
        String[] split = files.split(",");
        for (String s : split) {
            System.out.println("    private String "+s+";");
        }
    }
}
