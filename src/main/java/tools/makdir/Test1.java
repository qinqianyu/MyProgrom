package tools.makdir;

import java.io.File;

public class Test1 {
    public static void test3() {


        File file = new File("C:/Users/24109/Desktop/同步/root/cqgs/");

        if(file.exists()){
            System.out.println("文件已经存在");
        }else {
            // return;
            boolean mkdir = file.mkdirs();
            System.out.println(mkdir);
        }

        if(file.isDirectory()){
            System.out.println("是文件夹");
        }
        if(file.isFile()){
            System.out.println("是文件");
        }

    }

    public static void main(String[] args) {
        test3();
    }
}
