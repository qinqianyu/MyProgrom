//定义圆类，它有一个变量radius(半径)。从键盘输入数据，通过构造方法传递给radius，编程计算并输出圆的周长和面积（确保输入的数据不为负数）。
//  要求：进行测试。
package day0424;
import java.util.Scanner;
public class Circle {
    private double radius;

    public Circle(double dRadius){
        this.radius = dRadius;
    }

    public double getLength(){
        return 2*Math.PI*this.radius;
    }

    public double getArea()
    {
        return Math.PI*this.radius*this.radius;
    }


//测试类：
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double iRadius;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入圆的半径：");
        iRadius = sc.nextDouble();
        if(iRadius < 0){
            System.out.println("你输入的半径有误!");
        }
        else{
            Circle oCir = new Circle(iRadius);
            System.out.println("圆的周长为："+oCir.getLength());
            System.out.println("圆的面积为："+oCir.getArea());
        }


    }

}