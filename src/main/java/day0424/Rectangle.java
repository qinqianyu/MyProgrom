//定义长方形类，含：
//
//  属性：宽、高（整型）；
//
//  方法：求周长、面积；
//
//  构造方法3个：（1）无参——宽、高默认值为1；（2）1个参数——宽、高均为参数值；（3）2个参数——宽、高各为参数值。
//
//  要求：进行测试。
package day0424;
public class Rectangle {
    //定义长宽属性
    private int iWidth;
    private int iHeight;
    //构造器1
    public Rectangle(){
        this.iHeight = 1;
        this.iWidth = 1;
    }
    //构造器2
    public Rectangle(int iIndex){
        this.iWidth = iIndex;
        this.iHeight = iIndex;
    }
    //构造器3
    public Rectangle(int iWidth, int iHeight){
        this.iHeight = iHeight;
        this.iWidth = iWidth;
    }
    //求周长
    public int getLength(){
        return 2*(this.iHeight+this.iWidth);
    }
    //求面积
    public int getSquare(){
        return this.iHeight*this.iWidth;
    }

//测试类：
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Rectangle oRec1 = new Rectangle();
        System.out.println("默认长方形的周长为："+oRec1.getLength());
        System.out.println("默认长方形的面积为："+oRec1.getSquare());

        Rectangle oRec2 = new Rectangle(2);
        System.out.println("一个参数长方形的周长为："+oRec2.getLength());
        System.out.println("一个参数长方形的面积为："+oRec2.getSquare());

        Rectangle oRec3 = new Rectangle(2,3);
        System.out.println("两个参数长方形的周长为："+oRec3.getLength());
        System.out.println("两个参数长方形的面积为："+oRec3.getSquare());

    }

}

