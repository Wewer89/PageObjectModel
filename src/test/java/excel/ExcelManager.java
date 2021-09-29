package excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ExcelManager {

    /**
     * Excel Manager - utility class to extract data from excel sheet
     * Required: sheet name and path to excel file as parameters in "getDataFromExcel()" method
     * Return: List of Hashtables contains key, value pair of data
     * <p>
     * Example of use with @DataProvider:
     *
     @DataProvider
     public Object[][] getData(Method method) throws IOException {
     List<Hashtable> listOfHashTablesWithData = ExcelManager.getDataFromExcel(method.getName(),
     "path");
     int numOfKeyValuePair = listOfHashTablesWithData.size();
     Object[][] data = new Object[numOfKeyValuePair][1];
     for (int i = 0; i < listOfHashTablesWithData.size(); i++) {
     data[i][0] = listOfHashTablesWithData.get(i);
     }
     return data;
     }
     **/

    private static XSSFSheet sheet;
    static int numOfRows;
    private static int numOfCells;
    private static final List<String> listOfKeys = new ArrayList<>();
    private static final List<Hashtable> listOfDataTable = new ArrayList<>();

    public static List<Hashtable> getDataFromExcel(String sheetName, String path) throws IOException {
        createSheet(sheetName, path);
        return listOfDataTable;
    }

    private static void createSheet(String sheetName, String path) throws IOException {
        FileInputStream file = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);
        countNumOfRowsAndCells();
    }

    private static void countNumOfRowsAndCells() {
        numOfRows = sheet.getLastRowNum() + 1;
        numOfCells = sheet.getRow(0).getLastCellNum();
        getKeys();
    }

    private static void getKeys() {
        int firstRow = 0;
        for (int indexOfCell = 0; indexOfCell < numOfCells; indexOfCell++) {
            String value = sheet.getRow(firstRow).getCell(indexOfCell).getStringCellValue();
            listOfKeys.add(value);
        }
        addHashTableToList();
    }

    private static void addHashTableToList() {
        for (int indexOfRow = 1; indexOfRow < numOfRows; indexOfRow++) {
            XSSFRow row = sheet.getRow(indexOfRow);
            Hashtable<String, String> dataTable = new Hashtable<>();
            for (int indexOfCell = 0; indexOfCell < numOfCells; indexOfCell++) {
                if (row.getCell(indexOfCell).getCellType() == CellType.STRING) {
                    String value = row.getCell(indexOfCell).getStringCellValue();
                    dataTable.put(listOfKeys.get(indexOfCell), value);
                } else {
                    double value = row.getCell(indexOfCell).getNumericCellValue();
                    String valueConvertedToString = String.valueOf(value);
                    dataTable.put(listOfKeys.get(indexOfCell), valueConvertedToString);
                }
            }
            listOfDataTable.add(dataTable);
        }
    }
}
