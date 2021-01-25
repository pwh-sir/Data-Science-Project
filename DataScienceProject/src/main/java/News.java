import java.time.LocalDateTime;
import java.util.ArrayList;
//新闻对象
public class News extends Cell{
    public News(String text, LocalDateTime time){
        super(text,time);
    }
    public News(){
        this(null,null);
    }
}
