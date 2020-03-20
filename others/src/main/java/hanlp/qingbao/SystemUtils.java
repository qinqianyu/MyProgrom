package hanlp.qingbao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author Joetao
 * Created at 2017/6/10.
 */
public class SystemUtils {
    private final static Pattern PATTERN_ZH_PUNCTUATION = Pattern.compile("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？\\-]");
    private final static Pattern PATTERN_ZH = Pattern.compile("[\u4e00-\u9fa5]");
    private final static Pattern PATTERN_ENG = Pattern.compile("[a-zA-z]");

    /**
     * 日期格式化输出 hh:mm:ss
     *
     * @param date 原始日期
     * @return 格式化字符串
     */
    public static String dateFormate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 判断字符串是否包含中文
     *
     * @param str 匹配字符串
     * @return 匹配结果True/False
     */
    public static boolean isContainChinese(String str) {
        return PATTERN_ZH.matcher(str).find();
    }

    /**
     * 判断字符串是否包含中文标点
     *
     * @param str 匹配字符串
     * @return 匹配结果True/False
     */
    public static boolean isContainChinesePunctuation(String str) {
        return PATTERN_ZH_PUNCTUATION.matcher(str).find();
    }

    /**
     * 判断字符串是否包含英文字母
     *
     * @param str 匹配字符串
     * @return 匹配结果True/False
     */
    public static boolean isContainEnglish(String str){
        return PATTERN_ENG.matcher(str).find();
    }
}
