package tools.text;

import org.junit.Test;
import org.w3c.dom.html.HTMLParagraphElement;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class Stringreplase {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(
                "/"
        ));

        String oldstr = "C:\\Users\\24109\\Desktop\\log";
        String oldword = "\\";
        for (String str : list) {
            String replace = oldstr.replace(oldword, str);
            System.out.println(replace);
        }
    }


    @Test
    public void tolow() {
        String o = "JSON.ARRAPPEND";
        System.out.println(o.toLowerCase());

    }

    @Test
    public void eeee() {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(
                "id",
                "name",
                "name_zh",
                "data_source_ip",
                "data_source_type",
                "server_ip",
                "create_at",
                "modify_at"
        ));

        String oldstr = "C:\\Users\\24109\\Desktop\\同步\\root";
        String oldword = "";
        for (String str : list) {
            oldword = oldword + ";\nprivate String " + str;

        }
        System.out.println(oldword);
    }


    @Test
    public void ip() {
        // TODO Auto-generated method stub
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();

            String localname = ia.getHostName();
            String localip = ia.getHostAddress();
            System.out.println("本机名称是：" + localname);
            System.out.println("本机的ip是 ：" + localip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void ip2() {
        String a="南京秦淮区$大经路";
        String[] split = a.split("\\$");
        System.out.println(split.length);
        Arrays.stream(split).forEach((x)-> System.out.println(x+"\t"));

       // System.out.println(getLocalIPList());
    }

    public static List<String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;
    }

}