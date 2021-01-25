import java.time.LocalDateTime;

public class Blog extends Cell{
    public Blog(String text, LocalDateTime time){
        super(text,time);
    }
    public Blog(){
        this(null,null);
    }
}
