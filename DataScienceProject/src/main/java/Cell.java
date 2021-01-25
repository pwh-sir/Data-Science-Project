import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private String text;
    private LocalDateTime time;
    private Mentality mentality = Mentality.POSITIVE;
    private ArrayList<String> keywords;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Cell(String text, LocalDateTime time){
        this.text = text;
        this.time = time;
    }

    public Mentality getMentality() {
        return mentality;
    }

    public void setMentality(Mentality mentality) {
        this.mentality = mentality;
    }

    public Cell(){
        this(null,null);
    }

    public String toString(){
        return "新闻：" + text + "\n" + "时间：" + time + "\n" + "心态:" + mentality + "\n";
    }

    enum Mentality{
        POSITIVE, NEGATIVE,NONE,LIKE,SADNESS,ANGER,HAPPINESS,FEAR,SURPRISE,DISGUST;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(HanLP.segment("刘晨是个臭弟弟."));
    }
}
