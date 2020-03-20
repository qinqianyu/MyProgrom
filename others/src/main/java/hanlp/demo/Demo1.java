package hanlp.demo;

import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.dictionary.CoreDictionary;

import java.io.IOException;
import java.util.TreeMap;

public class Demo1 {
    public static void main(String[] args) throws IOException {

        TreeMap<String, CoreDictionary.Attribute> dictionary = IOUtil.loadDictionary("C:/Users/24109/AppData/Local/Programs/Python/Python37/Lib/site-packages/pyhanlp/static/data/dictionary/CoreNatureDictionary.mini.txt");
        System.out.printf("词典大小: %d个词条\n",dictionary.size());
        System.out.println(dictionary.keySet().iterator().next());
    }
}
