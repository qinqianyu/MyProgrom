package game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class gametest {
    static Set<Yuansu> zong = new HashSet<Yuansu>();
    static Set<Yuansu> zhen = new HashSet<Yuansu>();
    static Set<Yuansu> tmpset = new HashSet<Yuansu>();


    public static void main(String[] args) {
        zong.add(new Yuansu(0, 0, 0));
        // System.out.println(check(new Yuansu(1,1,1)));
        for (int i = 0; i < 20; i++) {
            // System.out.println(i + "---------->");
            for (Yuansu tmp : zong) {
                Yuansu yuansua = new Yuansu(tmp.getA() + 1, tmp.getB(), tmp.getC());
                if (!(zong.contains(yuansua) || tmpset.contains(yuansua))) {
                    if (check(yuansua)) {
                        zhen.add(yuansua);
                    }
                    tmpset.add(yuansua);
                }
                Yuansu yuansub = new Yuansu(tmp.getA(), tmp.getB() + 1, tmp.getC());
                if (!(zong.contains(yuansub) || tmpset.contains(yuansub))) {
                    if (check(yuansub)) {
                        zhen.add(yuansub);
                    }
                    tmpset.add(yuansub);
                }
                Yuansu yuansuc = new Yuansu(tmp.getA(), tmp.getB(), tmp.getC() + 1);
                if (!(zong.contains(yuansuc) || tmpset.contains(yuansuc))) {
                    if (check(yuansuc)) {
                        zhen.add(yuansuc);
                    }
                    tmpset.add(yuansuc);
                }
            }
            zong.addAll(tmpset);
            tmpset.clear();
        }
        ArrayList<Yuansu> arrayList = new ArrayList<>();
        arrayList.addAll(zhen);
        Collections.sort(arrayList);
        for(Yuansu yuansu:arrayList){
            if(yuansu.getA()!=yuansu.getB())
            System.out.println(yuansu);
        }
    }

    static boolean check(Yuansu yuansu) {
        //System.out.println("jiancha:" + yuansu);
        int a, b, c;
        for (Yuansu tmp : zhen) {
            a = tmp.getA();
            b = tmp.getB();
            c = tmp.getC();
            if (yuansu.jian(a, b, c).checkresult())
                return false;
            if (yuansu.jian(a, c, b).checkresult())
                return false;
            if (yuansu.jian(b, a, c).checkresult())
                return false;
            if (yuansu.jian(b, c, a).checkresult())
                return false;
            if (yuansu.jian(c, a, b).checkresult())
                return false;
            if (yuansu.jian(c, b, a).checkresult())
                return false;
        }
        return true;
    }

    @Test
    public void test() {
        Yuansu yuansu = new Yuansu(2, 0, 0);
        System.out.println(yuansu.checkresult());
    }
}
