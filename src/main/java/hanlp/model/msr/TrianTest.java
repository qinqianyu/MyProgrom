package hanlp.model.msr;


import com.hankcs.hanlp.model.perceptron.*;
import com.hankcs.hanlp.model.perceptron.model.LinearModel;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TrianTest {
    private static String trainingFile = "C:/programs/HanLP/data/test/icwb2-data/training/msr_training.utf8";
    private static String CWSmodelFile = "C:/programs/MyProgrom/src/main/java/hanlp/msr/cws.bin";
    private static String POSmodelFile = "C:/programs/MyProgrom/src/main/java/hanlp/msr/pos.bin";
    private static String NERmodelFile = "C:/programs/MyProgrom/src/main/java/hanlp/msr/ner.bin";


    @Test
    public void testTrain() throws Exception {
        LinearModel model = new CWSTrainer().train(trainingFile, CWSmodelFile).getModel();
       /*   此方法会训练出一个感知机分词模型，训练的语料由trainingFile(String)指定路径,
        *   模型将被保存至CWSmodelFile(String).
        */
        new POSTrainer().train(trainingFile,POSmodelFile);
        new NERTrainer().train(trainingFile,NERmodelFile);
    }
    @Test
    public void testmain() throws IOException {
        PerceptronLexicalAnalyzer analyzer = new PerceptronLexicalAnalyzer(CWSmodelFile, POSmodelFile, NERmodelFile);
        List<Term> seg = analyzer.seg("");


    }


}
