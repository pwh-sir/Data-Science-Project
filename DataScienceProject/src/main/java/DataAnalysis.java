import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.seg.common.Term;


import java.io.IOException;
import java.util.*;

public class DataAnalysis{
    ArrayList<Cell> first_stage_news_list, first_stage_blog_list,
            second_stage_news_list,second_stage_blog_list, third_stage_news_list,third_stage_blog_list,
            forth_stage_news_list,forth_stage_blog_list;
    IClassifier classifier;
    String trainDocumentPath;
    AnalysysModel model;
    ArrayList<String> stopList;
    public DataAnalysis(String trainDocumentPath,AnalysysModel model) throws IOException {
        this.trainDocumentPath = trainDocumentPath;
        stopList = TXTOperation.TXTLoad("src/main/resources/终极停用词表.txt");
        this.model = model;
        classifier = new NaiveBayesClassifier();
        classifierTrain(trainDocumentPath);
    }
    public void classifierTrain(String trainDocumentPath) throws IOException {
        classifier.train(trainDocumentPath);
    }

    public ArrayList<Cell> dataLoad(String filePath){
        return ExcelOperation.readExcelData(filePath);
    }

    public void predict(Cell cell,IClassifier classifier){
        switch (classifier.classify(cell.getText())){
            case "Positive":
                cell.setMentality(Cell.Mentality.POSITIVE);
                break;
            case "Negative":
                cell.setMentality(Cell.Mentality.NEGATIVE);
                break;
            case "anger":
                cell.setMentality(Cell.Mentality.ANGER);
                break;
            case "disgust":
                cell.setMentality(Cell.Mentality.DISGUST);
                break;
            case "fear":
                cell.setMentality(Cell.Mentality.FEAR);
                break;
            case "happiness":
                cell.setMentality(Cell.Mentality.HAPPINESS);
                break;
            case "like":
                cell.setMentality(Cell.Mentality.LIKE);
                break;
            case "none":
                cell.setMentality(Cell.Mentality.NONE);
                break;
            case "sadness":
                cell.setMentality(Cell.Mentality.SADNESS);
                break;
            case "surprise":
                cell.setMentality(Cell.Mentality.SURPRISE);
                break;
            default:
                break;
        }
    }
    public void predictAll(ArrayList<Cell> list,IClassifier classifier){
        for(Cell cell:list){
            predict(cell,classifier);
//            List<String> keywords = HanLP.extractKeyword(cell.getText(),5);
            ArrayList<String> keywords = handleTXT(cell.getText());
            cell.setKeywords(keywords);

        }
    }

    private ArrayList<String> handleTXT(String text) {
        List<Term> list = HanLP.segment(text);
        ArrayList<String> res = new ArrayList<String>();
        for (Term term:list){
            String segment = term.word;
            if(!stopList.contains(segment)){
                res.add(segment);
            }
        }
        return res;
    }

    //数学工具进行分析
    public void mathAnalysis(AnalysysModel model){
        switch (model){
            case FIRSTMODEL:
                System.out.println("第一种模型分析结果如下：");
                break;
            case SECONDMODEL:
                System.out.println("第二种模型分析结果如下");
                break;
            default:
                break;
        }
        System.out.println("--------------分割线--------------");
        System.out.println();
        System.out.println("第一阶段的各项数据:");
        System.out.println("第一阶段新闻的数据：");
        MathAnalysis.calculateAllVariable(first_stage_news_list,"新闻",model);
        System.out.println("第一阶段新闻的关键词：");
        MathAnalysis.keywordAnalysis(first_stage_news_list,"新闻");
        System.out.println("第一阶段动态的数据");
        MathAnalysis.calculateAllVariable(first_stage_blog_list,"动态",model);
        System.out.println("第一阶段动态的关键词：");
        MathAnalysis.keywordAnalysis(first_stage_blog_list,"动态");
        System.out.println("--------------分割线--------------");
        System.out.println();
        System.out.println("第二阶段的各项数据");
        System.out.println("第二阶段新闻的数据");
        MathAnalysis.calculateAllVariable(second_stage_news_list,"新闻",model);
        System.out.println("第二阶段新闻的关键词：");
        MathAnalysis.keywordAnalysis(second_stage_news_list,"新闻");
        System.out.println("第二阶段动态的数据");
        MathAnalysis.calculateAllVariable(second_stage_blog_list,"动态",model);
        System.out.println("第二阶段动态的关键词：");
        MathAnalysis.keywordAnalysis(second_stage_blog_list,"动态");
        System.out.println("--------------分割线--------------");
        System.out.println();
        System.out.println("第三阶段的各项数据");
        System.out.println("第三阶段新闻的数据");
        MathAnalysis.calculateAllVariable(third_stage_news_list,"新闻",model);
        System.out.println("第三阶段新闻的关键词：");
        MathAnalysis.keywordAnalysis(third_stage_news_list,"新闻");
        System.out.println("第三阶段动态的数据");
        MathAnalysis.calculateAllVariable(third_stage_blog_list,"动态",model);
        System.out.println("第三阶段动态的关键词：");
        MathAnalysis.keywordAnalysis(third_stage_blog_list,"动态");
        System.out.println("--------------分割线--------------");
        System.out.println();
        System.out.println("第四阶段的各项数据");
        System.out.println("第四阶段新闻的数据");
        MathAnalysis.calculateAllVariable(forth_stage_news_list,"新闻",model);
        System.out.println("第四阶段新闻的关键词：");
        MathAnalysis.keywordAnalysis(forth_stage_news_list,"新闻");
        System.out.println("第四阶段动态的数据");
        MathAnalysis.calculateAllVariable(forth_stage_blog_list,"动态",model);
        System.out.println("第四阶段动态的关键词：");
        MathAnalysis.keywordAnalysis(forth_stage_blog_list,"动态");
    }

    public void dataAnalysis() throws IOException{
        //第一步：加载数据
        first_stage_news_list = dataLoad("src/main/resources/新闻（第一阶段）.xls");
        System.out.println("新闻第一阶段加载完成");
        first_stage_blog_list = dataLoad("src/main/resources/用户评论（第一阶段）.xls");
        System.out.println("用户评论第一阶段加载完成");
        second_stage_news_list = dataLoad("src/main/resources/新闻（第二阶段）.xls");
        System.out.println("新闻第二阶段加载完成");
        second_stage_blog_list = dataLoad("src/main/resources/用户评论（第二阶段）.xls");
        System.out.println("用户评论第二阶段加载完成");
        third_stage_news_list = dataLoad("src/main/resources/新闻（第三阶段）.xls");
        System.out.println("新闻第三阶段加载完成");
        third_stage_blog_list = dataLoad("src/main/resources/用户评论（第三阶段）.xls");
        System.out.println("用户评论第三阶段加载完成");
        forth_stage_news_list = dataLoad("src/main/resources/新闻（第四阶段）.xls");
        System.out.println("新闻第四阶段加载完成");
        forth_stage_blog_list = dataLoad("src/main/resources/用户评论（第四阶段）.xls");
        System.out.println("用户评论第四阶段加载完成");

        //第二部: 将数据刻印上心态词,将所有的文本全部提取关键词
        predictAll(first_stage_news_list, classifier);
        System.out.println("新闻第一阶段评估完成");
        predictAll(first_stage_blog_list, classifier);
        System.out.println("动态第一阶段评估完成");
        predictAll(second_stage_news_list,classifier);
        System.out.println("新闻第二阶段评估完成");
        predictAll(second_stage_blog_list,classifier);
        System.out.println("动态第二阶段评估完成");
        predictAll(third_stage_news_list,classifier);
        System.out.println("新闻第三阶段评估完成");
        predictAll(third_stage_blog_list,classifier);
        System.out.println("动态第三阶段评估完成");
        predictAll(forth_stage_news_list,classifier);
        System.out.println("新闻第四阶段评估完成");
        predictAll(forth_stage_blog_list,classifier);
        System.out.println("动态第四阶段评估完成");
        //第三部：数据分析
        mathAnalysis(model);

    }

    enum AnalysysModel{
        FIRSTMODEL,SECONDMODEL;
    }

    public static void main(String[] args) throws IOException {
        DataAnalysis firstModel = new DataAnalysis("src/main/resources/训练语料",AnalysysModel.FIRSTMODEL);
//        DataAnalysis secondModel = new DataAnalysis("src/main/resources/训练语料第二弹",AnalysysModel.SECONDMODEL);
        firstModel.dataAnalysis();
//        secondModel.dataAnalysis();
    }


}
