package game;

import lombok.Data;

@Data
public class Yuansu implements Comparable{
    private Integer a;
    private Integer b;
    private Integer c;

    public Yuansu(Integer a, Integer b, Integer c) {
        if (a < b) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        if (b < c) {
            int tmp = c;
            c = b;
            b = tmp;
        }
        if (a < b) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    Yuansu jian(int ja,int jb,int jc) {
        //System.out.println(""+this.a+this.b+this.c+"-"+"(+"+ja+jb+jc+")");
        return new Yuansu(a - ja, b - jb, c - jc);
    }

    boolean checkresult() {
       //System.out.println("checkresult:"+this);
       int d=Math.abs(a)+Math.abs(b)+Math.abs(c);
        if (a != d) {
            if (b != d) {
                if (c != d) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public int compareTo(Object o) {
        o=(Yuansu)o;
        return a.compareTo(((Yuansu) o).a);
    }
}
