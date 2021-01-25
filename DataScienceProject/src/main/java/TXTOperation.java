import java.io.*;
import java.util.ArrayList;

public class TXTOperation {
    public static ArrayList<String> TXTLoad(String filePath) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
//                    System.out.println(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }

    public static void ListOperation(ArrayList<String> list, String filePath){
        String suffix = ".txt";
        String currentFilePath = null;
        for(int i = 0; i < list.size(); i++){
            currentFilePath = filePath + "/" + i + suffix;
            try {
                File file = new File(currentFilePath);
                //建立txt文件
                if(!file.exists()){
                    file.createNewFile();
                }
                String txt = list.get(i);
                FileWriter writer = new FileWriter(file);
                writer.write(txt);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void CreateFile(String filePath){
        try {
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
    }
}
