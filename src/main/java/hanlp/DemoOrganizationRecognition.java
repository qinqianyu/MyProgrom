package hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * 机构名识别
 *
 * @author hankcs
 */
public class DemoOrganizationRecognition {
    public static void main(String[] args) {
        String[] testCase = new String[]{
                "北京心连心园艺中心",
                "北京心贴心商贸中心",
                "北京平和心文化中心",
                "北京中海港商贸中心",
                "北京中隆中商贸中心",
                "北京中方祥云商贸中心",
                "北京科中环科贸中心朝阳分中心",
                "北京迎泽兴业商贸中心",
                "北京安顺永和科技中心",
                "北京平安仓储中心",
                "北京同江仓储中心",
                "北京顺德东方商贸中心",
                "北京安顺无为科贸中心",
                "北京顺平振兴运输中心",
                "北京龙山兴业商贸中心",
                "北京石门东海商贸中心",
                "北京芙蓉长兴建材中心",
                "北京南湖兴业经贸中心",
                "北京海伦美容中心",
        };
        Segment segment = HanLP.newSegment().enableCustomDictionary(false).enableOrganizationRecognize(true);
        for (String sentence : testCase) {
            List<Term> termList = segment.seg(sentence);
            System.out.println(termList);
        }
        System.out.println("***************************************");
        for (String sentence : testCase) {
            List<Term> termList = segment.seg(sentence);
            for (Term term : termList) {
                if (term.nature.toString().equals("nt")) {
                    System.out.print(term);
                    System.out.print("--->");
                    List<Term> list = HanLP.segment(term.word);
                    System.out.println(list);
                }
            }
        }


    }
}
