package hanlp.qingbao;

import com.hankcs.hanlp.seg.common.Term;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: jxk
 * @create: 2020-03-09 11:30
 **/
public class myTest {
    @Test
    public void test(){
        String text="邢台市宁晋县";
        List<Term> termList = HanLPUtils.segment(text);
        Set<String> segmentPlaces = HanLPUtils.getWordsByType(termList, "ns");
        for (String tmp :
                segmentPlaces) {
            System.out.println(tmp);
        }

    }
}
