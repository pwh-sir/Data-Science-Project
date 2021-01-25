import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


//处理Excel表格
public class ExcelOperation {
    public static ArrayList<Cell> readExcelData(String filePath){
        ArrayList<Cell> newsList = new ArrayList<Cell>();
        try {
            FileInputStream fil = new FileInputStream(filePath);
            Workbook workbook = null;
            if(filePath.toLowerCase().endsWith("xls")){
                workbook = new HSSFWorkbook(fil);
                int numOfSheet = workbook.getNumberOfSheets();
                for(Sheet sheet: workbook){
                    for (Row row : sheet){
                        //这部分是根据实际情况进行修改的
                        HSSFCell text = (HSSFCell)row.getCell(0);
                        HSSFCell time = (HSSFCell)row.getCell(1);
                        Cell cell = new Cell(text.getStringCellValue(),time.getLocalDateTimeCellValue());
                        newsList.add(cell);
                    }
                }
            }else {
                System.out.println("文件异常");
            }
        }catch (IOException ie){
            ie.printStackTrace();
        }
        return newsList;
    }


    public void handleExcelTrainData(String filePath){
        int angcnt = 0,discnt = 0,feacnt = 0,hapcnt = 0,likcnt = 0,noncnt = 0,sadcnt = 0,surcnt = 0;
        String trainFilePath = "src/main/resources/训练语料第二弹/";
        String in = "/";
        String surfix = ".txt";
        try {
            FileInputStream fil = new FileInputStream(filePath);
            Workbook workbook = null;
            String cellFilePath = null;
            if(filePath.toLowerCase().endsWith("xls")){
                workbook = new HSSFWorkbook(fil);
                for(Sheet sheet: workbook){
                    for (Row row : sheet){
                        HSSFCell text = (HSSFCell)row.getCell(0);
                        text.setCellType(CellType.STRING);
                        HSSFCell mentality = (HSSFCell)row.getCell(1);
                        mentality.setCellType(CellType.STRING);
                        switch (mentality.getStringCellValue()){
                            case "anger":
                                cellFilePath = trainFilePath + mentality.getStringCellValue() + in + angcnt + surfix;
                                angcnt++;
                                break;
                            case "disgust":
                                cellFilePath = trainFilePath + mentality.getStringCellValue() + in + discnt + surfix;
                                discnt++;
                                break;
                            case "fear":
                                cellFilePath = trainFilePath + mentality.getStringCellValue() + in + feacnt + surfix;
                                feacnt++;
                                break;
                            case "happiness":
                                cellFilePath = trainFilePath + mentality.getStringCellValue() + in + hapcnt + surfix;
                                hapcnt++;
                                break;
                            case "like":
                                cellFilePath = trainFilePath + mentality.getStringCellValue() + in + likcnt + surfix;
                                likcnt++;
                                break;
                            case "none":
                                cellFilePath = trainFilePath + mentality.getStringCellValue() + in + noncnt + surfix;
                                noncnt++;
                                break;
                            case "sadness":
                                cellFilePath = trainFilePath + mentality.getStringCellValue() + in + sadcnt + surfix;
                                sadcnt++;
                                break;
                            case "surprise":
                                cellFilePath = trainFilePath + mentality.getStringCellValue() + in + surcnt + surfix;
                                surcnt++;
                                break;
                            default:
                                break;
                        }
                        File file = new File(cellFilePath);
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(text.getStringCellValue());
                        fileWriter.close();
                    }
                }
            }else {
                System.out.println("文件异常");
            }
        }catch (IOException ie){
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new ExcelOperation().handleExcelTrainData("src/main/resources/大作业情感模型数据集第二弹.xls");
    }
}
