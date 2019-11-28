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
                "北京平安友谊商贸中心",
        };
        Segment segment = HanLP.newSegment().enableOrganizationRecognize(true);
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
