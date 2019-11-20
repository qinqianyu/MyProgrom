package redis;

public class Jisuanqi {


    public static void main(String[] args) {
        float a= 9.9657842847F*1.44F;
        float b=(float)365*30*10000*3;
        float bitshu = a*b;
        float v = bitshu / 8 / 1024 / 1024 / 1024;
        System.out.println(v);
        System.out.println(b);
        System.out.println(bitshu);

    }
}
