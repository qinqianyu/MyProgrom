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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        final String ruleRegex = "\"(\\S+?)\"";
        Pattern pattern = Pattern.compile(ruleRegex);
        String expression = "+rule_ALL:(\"青岛昌阳投资开发有限公司\"\"昌阳投资\")";
        Matcher matcher = pattern.matcher(expression);
        List<String> keywords = new ArrayList<>();
        while (matcher.find()) {
            keywords.add(matcher.group(1));
        }
        System.out.println(keywords);
    }

    @Test
    public void eeee() {
        String oldstr = "0*11*11*11*10*11*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*39*0*0*0*0*0*7*0*0*0*0*0*0*0*0*39*0*0*0*0*0*0*0*0*0*0*0*0*0*56*0*0*0*0*0*11*0*1*39*0*39*39*48*39*39*0*0*0*0*0*17*14*0*60*0*31*71*0*0*0*0*0*0*14*17*0*17*0*0*0*0*137*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*14*0*0*0*79*0*0*10*0*0*0*267*31*62*0*0*34*0*0*0*0*0*0*0*0*0*41*0*0*0*0*0*0*0*0* 0*0*0*0*35*0*17*90*0*0*24*0*778*0*11*0*0*0*0*68*24*0*0*43*0*0*24*0*0*0*0*0*0*0*0*0*0*12*0*41*0*0*0*0*0*0*17*0*13*0*0*35*0*34*0*0*0*39*14*0*6*0*0*0*0*0*42*0*27*0*0*0*0*17*0*0*0*0*10*31*0*0*53*0*21*0*17*0*0*0*0*0*0*0*0*0*0*17*10*0*0*17*0*14*27*0*0*0*54*0*0*34*18*17*77*0*0*0*10*17*0*0*0*0*49*0*0*0*0*0*0*36*34*37*0*17*42*17*0*0*34*23*17*43*0*0*24*34*0*17*0*0*14*14*0*0*30*0*0*0*0*0*0*0*21*0*31*14*27*0*0*0*0*17*17*19*12*10*0*18*0*17*14*17*88*243*38*0*0*35*0*0*0*17*17*21*0*0*14*0*12*0*14*7*0*12*14*34*46*44*11*0*14*0*14*0*0*158*0*39*14*0*0*267*0*14*17*0*0*56*17*0*0*0*54*0*34*0*0*43*17*47*0*0*90*0*0*38*18*0*0*0*0*0*31*54*31*0*0*22*17*49*17*0*61*0*0*0*14*0*21*0*0*0*0*17*14*0*0*69*17*0*0*0*0*0*0*31*0*0*0*0*17*44*17*14*0*0*0*0*0*17*0*228*21*0*0*0*0*0*0*17*0*0*63*39*33*17*0*0*0*0*0*17*0*0*0*0*17*17*0*39*0*0*0*109*0*100*0*0*0*45*10*0*0*0*12*31*0*0*23*0*0*0*0*17*0*0*0*14*0*0*17*87*31*0*0*0*0*0*266*0*14*76*0*0*90*0*17*14*29*0*17*32*4*0*72*0*14*0*17*0*0*0*0*17*13*0*17*0*0*0*0*0*0*0*0*0*0*0*0*0*0*0*90*0*0*0*0*0*63*8*0*0*0*0*0*0*0*14*0*14*27*0*0*17*0*0*0*81*0*0*0*0*0*12*17*17*27*17*0*0*0*0*0*0*23*0*17*0*0";
        String[] split = oldstr.split("\\*");
        System.out.println(split.length);
        System.out.println(System.currentTimeMillis());
//        for (int i = 0; i < split.length; i++) {
//            System.out.println("private String " + split[i] + ";");
//        }

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
        String a = "南京秦淮区$大经路";
        String[] split = a.split("\\$");
        System.out.println(split.length);
        Arrays.stream(split).forEach((x) -> System.out.println(x + "\t"));

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