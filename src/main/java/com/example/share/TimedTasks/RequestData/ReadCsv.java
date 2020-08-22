package com.example.share.TimedTasks.RequestData;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReadCsv {


    public static void main(String[] args) throws IOException {
//        read();

    }

    public void read(InputStream fileStream) throws IOException {

//        String filePath = "C:\\Users\\eddy\\Downloads\\0600776.xls";
//        FileInputStream in = new FileInputStream(filePath);
        HSSFWorkbook book = new HSSFWorkbook(fileStream);

        HSSFSheet sheet = book.getSheetAt(0);
        HSSFRow row;
        String cell = null;

        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++)
        {
            row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++)
            {
                cell = row.getCell(j).toString();
                if (j == 2 ){
                    cell = formattingData(cell);
                }
                System.out.print(cell + " \t");
            }
            System.out.println("");
        }


    }

    private static String formattingData(String str) {
        String regex="(-?[0-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return str;
        }
        double change = Double.parseDouble(str);
       return String.format("%.2f", change);
    }

}
