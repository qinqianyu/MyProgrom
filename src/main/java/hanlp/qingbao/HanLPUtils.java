package hanlp.qingbao;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Joetao
 */
public class HanLPUtils {
    public static List<Term> segment(String text) {
        HanLP.newSegment().enableCustomDictionary(true);
        return HanLP.segment(text);
    }

    public static Set<String> getWordsByType(List<Term> termList, String type) {
        Set<String> set = new HashSet<>();
        for (Term term : termList) {
            if (term.nature.startsWith(type)) {
                set.add(term.word);
            }
        }
        return set;
    }

    public static Set<String> extractKeyword(String text) {
        Set<String> words = new HashSet<>();
        List<String> keywordList = HanLP.extractKeyword(text, 7);
        for (String s : keywordList) {
            if (s.length() > 1
                    && !SystemUtils.isContainEnglish(s)
                    && !SystemUtils.isContainChinesePunctuation(s)) {
                words.add(s);
            }
        }
        return words;
    }
}
