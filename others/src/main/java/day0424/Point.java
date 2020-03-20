//定义一个点类Point，包含2个成员变量x、y分别表示x和y坐标，2个构造器Point()和Point(int x0,y0),以及一个movePoint（int dx,int dy）方法实现点的位置移动
//创建两个Point对象p1、p2，分别调用movePoint方法后，打印p1和p2的坐标。
package day0424;
public class Point {
    int x;
    int y;

    Point() {

    }

    Point(int x0, int y0) {
        x = x0;
        y = y0;
    }
    void movePoint(int dx,int dy){
        x+=dx;
        y+=dy;
        System.out.println("P1:"+x+"P2:"+y);
    }
}

