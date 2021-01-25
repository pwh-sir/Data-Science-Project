import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MathAnalysis {
    public static void calculateAllVariable(ArrayList<Cell> list, String type, DataAnalysis.AnalysysModel model){
        int num = list.size();
        System.out.println("我们一共爬取了" + num + "条" + type);
        switch (model){
            case FIRSTMODEL:
                int positiveNum = 0;
                int negativeNum = 0;
                for (Cell cell:list){
                    switch (cell.getMentality()){
                        case NEGATIVE:
                            negativeNum++;
                            break;
                        case POSITIVE:
                            positiveNum++;
                            break;
                        default:
                            break;
                    }
                }
                double percentageOfPositive,percentageOfNegative;
                percentageOfNegative =1.0*negativeNum/num*100;
                percentageOfPositive = 100 - percentageOfNegative;
                System.out.println("在这" + num + "条" + type + "中，情感极性为积极的" + type + "有" + positiveNum + "条,占比为百分之" + String.format("%2f",percentageOfPositive) +
                        ";情感极性为消极的" + type + "有" + negativeNum + "条,占比为百分之" + String.format("%2f",percentageOfNegative) + ".");
                break;
            case SECONDMODEL:
                int angNum = 0;
                int disNum = 0;
                int feaNum = 0;
                int hapNum = 0;
                int likNum = 0;
                int nonNum = 0;
                int sadNum = 0;
                int surNum = 0;
                for (Cell cell:list){
                    switch (cell.getMentality()){
                        case ANGER:
                            angNum++;
                            break;
                        case DISGUST:
                            disNum++;
                            break;
                        case FEAR:
                            feaNum++;
                            break;
                        case HAPPINESS:
                            hapNum++;
                            break;
                        case LIKE:
                            likNum++;
                            break;
                        case NONE:
                            nonNum++;
                            break;
                        case SADNESS:
                            sadNum++;
                            break;
                        case SURPRISE:
                            surNum++;
                            break;
                        default:
                            break;
                    }
                }
                double perOfang,perOfdis,perOffea,perOfhap,perOflik,perOfnon,perOfsad,perOfsur;
                perOfang = 1.0*angNum/num*100;
                perOfdis = 1.0*disNum/num*100;
                perOffea = 1.0*feaNum/num*100;
                perOfhap = 1.0*hapNum/num*100;
                perOflik = 1.0*likNum/num*100;
//                perOfnon = 1.0*nonNum/num*100;
                perOfsad = 1.0*sadNum/num*100;
                perOfsur = 1.0*surNum/num*100;
                System.out.println("在这" + num + "条" + type + "中，情感极性为生气的" + type + "有" + angNum + "条,占比为百分之" + String.format("%2f",perOfang) +
                                    ";情感极性为厌恶的" + type + "有" + disNum + "条,占比为百分之" + String.format("%2f",perOfdis)+
                                    ";情感极性为恐惧的" + type + "有" + feaNum + "条,占比为百分之" + String.format("%2f",perOffea)+
                                    ";情感极性为高兴的" + type + "有" + hapNum + "条,占比为百分之" + String.format("%2f",perOfhap)+
                                    ";情感极性为喜欢的" + type + "有" + likNum + "条,占比为百分之" + String.format("%2f",perOflik)+
//                                    ";情感极性为中性的" + type + "有" + nonNum + "条,占比为百分之" + String.format("%2f",perOfnon)+
                                    ";情感极性为悲伤的" + type + "有" + sadNum + "条,占比为百分之" + String.format("%2f",perOfsad)+
                                    ";情感极性为吃惊的" + type + "有" + surNum + "条,占比为百分之" + String.format("%2f",perOfsur)+
                                    ".");
                break;
            default:
                break;
        }
    }
    public static void keywordAnalysis(ArrayList<Cell> list,String type){
        HashMap<String,Integer> keywords= new HashMap<String,Integer>();
        ArrayList<String> keyList = new ArrayList<>();
        String[] strList;
        for(Cell cell:list){
            for(String txt:cell.getKeywords()){
                if(keywords.containsKey(txt)){
                    int cnt = keywords.get(txt);
                    keywords.replace(txt,cnt,cnt+1);
                }else {
                    keywords.put(txt,0);
                    keyList.add(txt);
                }
            }
        }
        strList = new String[keyList.size()];
        for (int i = 0; i < strList.length; i++){
            strList[i] = keyList.get(i);
        }
        for(int i = 0; i < strList.length-1; i++){
           for (int j = 0; j < strList.length - i - 1; j++){
              if(keywords.get(strList[j]) < keywords.get(strList[j+1])){
                  String temp = strList[j];
                  strList[j] = strList[j+1];
                  strList[j+1] = temp;
              }
           }
        }
        System.out.println("关键词分析");
        for(int i = 0; i < 200; i++){
            System.out.println("关键词：" + strList[i] + "    词频：" + keywords.get(strList[i]));
        }
    }
}
