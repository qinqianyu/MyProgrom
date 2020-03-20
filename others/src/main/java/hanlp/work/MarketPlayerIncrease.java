package hanlp.work;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MarketPlayerIncrease {
    /**
     * //分词机构词性（前缀）
     */
    private final Set<String> orgTypeSet=new HashSet<String>();
    private final String placeType = "ns";
    private Segment segment = HanLP.newSegment().enableOrganizationRecognize(true);
    {
       orgTypeSet.add("nt");
       orgTypeSet.add("nis");
       orgTypeSet.add("nit");
       orgTypeSet.add("nic");
    }
    public boolean analyse(String text) {
        List<Term> list = segment.seg(text);
        int len = list.size();
        if (len == 1) {
            if (orgTypeSet.contains(list.get(0).nature.toString())) {
                return true;
            } else return false;
        }
        int i = len - 1;
        Term term = list.get(i);
        if (orgTypeSet.contains(term.nature.toString())) {
            StringBuilder stringBuilder = new StringBuilder(term.word);
            for (int j = 1; j < 6; j++) {
                if ((len - 1 - j) >= 0) {
                    Term termbefor5 = list.get(len - 1 - j);
                    stringBuilder.insert(0, termbefor5.word);
                    if (termbefor5.nature.toString().equals(placeType) && (len - 1 - j) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
