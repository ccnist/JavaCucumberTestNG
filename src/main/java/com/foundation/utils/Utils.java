package com.foundation.utils;

import com.sun.media.sound.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.*;
import java.rmi.activation.UnknownGroupException;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

 // Siva Nageswararao: 89 77 30 28 29   D:\frameworks\JavaTestngCucumber\src\test\java\com\foundation\website\pages

    public static void main(String Args[])
    {
       // System.out.println(Utils.getCellValueInRow("Links","HomePage","URL"));
        String linkU = "";
       // Utils.writeWorkBook("Links",linkU,"","","","");
        ArrayList<HashMap> linksDetail = new ArrayList<>();
        LinkedHashMap<String, String> linkDetail = new LinkedHashMap<>();
        linkDetail.put("URL","http:www.lovely.com");
        linkDetail.put("Response","super");
        linkDetail.put("Total Links","300");
        linksDetail.add(linkDetail);
        Utils.logToExcel(linksDetail);
    }

    static String LOCATION = "data\\";
    static String EXCEL_FILES = "com\\foundation\\";

    public static JSONObject readJson(String filePath) {
        JSONObject data = null;
        JSONParser parser = new JSONParser();
        try {
            data = (JSONObject) parser.parse(new FileReader(LOCATION + filePath + ".json"));
        } catch (IOException | ParseException d) {
            System.out.println("Got Parse | IO Exception");
        }
        return data;
    }

    public static ArrayList<String> elementLocators(JSONObject js, String elementName) {
        ArrayList<String> elementLoc = new ArrayList<String>();
        String locator = js.get(elementName).toString().split(",")[0];
        elementLoc.add(locator);
        String identifier = js.get(elementName).toString().split(",")[1].trim();
        elementLoc.add(identifier);
        return elementLoc;
    }

    public static By selector(String byType, String value) {
        switch (byType) {
            case "id":
                return By.id(value);
            case "linkText":
            case "link text":
                return By.linkText(value);
            case "name":
                return By.name(value);
            case "partialLinkText":
            case "partial link text":
                return By.partialLinkText(value);
            case "tagName":
            case "tag name":
                return By.tagName(value);
            case "xpath":
                return By.xpath(value);
            case "className":
            case "class":
            case "class name":
                return By.className(value);
            case "cssSelector":
            case "css selector":
            case "css":
                return By.cssSelector(value);
            default:
                return null;
        }
    }

    public static void createWorkBook(String bookName) {
        FileOutputStream fileOut = null;
        Workbook wb = new HSSFWorkbook();
        try {
            fileOut = new FileOutputStream(LOCATION + bookName + ".xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readWorkBook(String bookName, String sheetName) {
        FileInputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(new File(LOCATION + "excelFiles\\" + bookName + ".xls"));
            workbook = new HSSFWorkbook(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sheet firstSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = firstSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.println(cell.getRichStringCellValue());
            }
        }
        try {
            workbook.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getCellValueInRow(String bookName, String sheetName, String ColumnStartWith) {
        int rowIndex = 0;
        int cellIndex = 0;
        FileInputStream inputStream = null;
        ArrayList<String> urls = new ArrayList<>();
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(new File(LOCATION + "excelFiles\\" + bookName + ".xls"));
            workbook = new HSSFWorkbook(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sheet firstSheet = workbook.getSheet(sheetName);
        Row row = firstSheet.getRow(rowIndex);
        cellIndex = row.getLastCellNum();
        for (int j = 0; j < row.getLastCellNum(); j++) {
            if (row.getCell(j).getRichStringCellValue().toString().equals(ColumnStartWith)) {
                cellIndex = j;
                break;
            }
        }
        for (int k = 1; k <= firstSheet.getLastRowNum(); k++) {
            row = firstSheet.getRow(k);
            Cell cell = row.getCell(cellIndex);
            if (cell != null) {
                urls.add(cell.getStringCellValue());
            }
        }
        try {
            workbook.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urls;
    }


    public static void writeWorkBook(String bookName, String linkUrl, String extra, String status, String totalLinks, String dummyLinks) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(LOCATION + "excelFiles\\" + bookName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HSSFWorkbook myWorkBook = new HSSFWorkbook();
        HSSFSheet mySheet = myWorkBook.createSheet("please leave");
        int lastRow = mySheet.getLastRowNum();
        Row row = mySheet.createRow(lastRow + 1);
        row.createCell(0).setCellValue(linkUrl);
        row.createCell(1).setCellValue(status);
        row.createCell(2).setCellValue(totalLinks);
        row.createCell(3).setCellValue(dummyLinks);
        try {
            myWorkBook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void logToExcel(ArrayList<HashMap> objects)
    {
        try {
        FileInputStream inputStream = new FileInputStream(new File(LOCATION + "excelFiles\\" + "Links" + ".xls"));
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        for (HashMap detail : objects) {
            Row row = sheet.createRow(++rowCount);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);
            Iterator iterator = detail.entrySet().iterator();
            do {
                Map.Entry entry = (Map.Entry) iterator.next();
                cell = row.createCell(++columnCount);
                cell.setCellValue(entry.getValue().toString());

            }while (iterator.hasNext());
        }
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(LOCATION + "excelFiles\\" + "Links" + ".xls");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    } catch (IOException | EncryptedDocumentException ex) {
        ex.printStackTrace();
    }

}




}
