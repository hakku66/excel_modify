package back_end;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


import java.util.List;



import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFCell;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
public class SimpleExcelReaderExample {
    List<RowObject> newExcelData = new ArrayList<RowObject>();
    String sheetname;

    public  void readfromExcel(String filename) throws IOException {
        // String excelFilePath = "C:\\Users\\ahake\\Desktop\\tally2other\\DayBook.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(filename));



        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        sheetname=firstSheet.getSheetName();
        RowObject newRowData = new RowObject();
        boolean process_data = false;
        int rowcount=0;
        for (Row row : firstSheet) {
            // For each Row.
            Cell cell = row.getCell(0); // Get the Cell at the Index / Column you want.
            Cell cell1 = row.getCell(7); // Get the Cell at the Index / Column you want.
            //System.out.println(rowcount + "cell  " + cell.toString()+"  cell1 "+cell1.toString());
            rowcount++;
            if(cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue()=="Date"){


            }
            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {

                newRowData.setDate(cell.getDateCellValue());
                newRowData.setParty_name(row.getCell(1).getStringCellValue());
                newRowData.setCity(row.getCell(2).getStringCellValue());
                newRowData.setVch_no(row.getCell(4).getStringCellValue());
                newRowData.setGstn_uin(row.getCell(5).getStringCellValue());
                process_data = true;
            } else if ((cell.getCellType() == Cell.CELL_TYPE_BLANK && !cell1.toString().isEmpty()) && process_data == true) {

                DecimalFormat f = new DecimalFormat("##.00");

                newRowData.setItemName(row.getCell(1).getStringCellValue());
                newRowData.setHsc_sac(row.getCell(7).getStringCellValue());
                newRowData.setQuantity(row.getCell(8).getNumericCellValue());
                int unit=row.getCell(8).getCellStyle( ).getDataFormat( );
                String unitString=null;
                System.out.println("row number "+rowcount+" ... unit no is "+unit);
                switch (unit){
                    case 166: unitString="KG"; break;
                    case 169: unitString="KG"; break;
                    case 171: unitString="nos";break;
                    case 172: unitString="nos";break;
                    case 173: unitString="sheet";break;
                    case 174: unitString="sheet";break;
                    case 175: unitString="Packet";break;
                    case 184: unitString="Packet";break;
                    case 177: unitString="Liter";break;
                    case 178: unitString="Liter";break;
                    case 179: unitString="Roll";break;
                    case 180: unitString="Roll";break;
                    case 181: unitString="BOX";break;
                    case 182: unitString="BOX";break;
                    case 183: unitString="Meter";break;
                    case 176: unitString="Meter";break;
                    case 185: unitString="SqFt";break;
                    case 186: unitString="SqFt";break;
                    default:
                            unitString="no value";
                }
                newRowData.setunitValue(unitString);

                newRowData.setRate(row.getCell(9).getNumericCellValue());
                
                newRowData.setValue(Double.parseDouble(f.format(newRowData.getQuantity() * newRowData.getRate())));

                if(row.getCell(6).getCellType()==Cell.CELL_TYPE_STRING) {
                    newRowData.setCentral_gst_p(Double.parseDouble((row.getCell(6).getStringCellValue().replace("%", " ").trim())) / 2);
                }else{
                    newRowData.setCentral_gst_p((row.getCell(6).getNumericCellValue()) / 2);
                }

                newRowData.setState_gst_p(newRowData.getCentral_gst_p());
                newRowData.setCentral_gst(Double.parseDouble(f.format((newRowData.getCentral_gst_p() / 100) * newRowData.getValue())));
                newRowData.setState_gst(newRowData.getCentral_gst());
                newRowData.setGross_Total(newRowData.getValue() +(newRowData.getCentral_gst()*2) );
                //	    	  System.out.println(newRowData);
                newExcelData.add(new RowObject(newRowData));
            }


        }


        workbook.close();
        inputStream.close();
    }
    public boolean writetoExcel(String filename) throws IOException {


        XSSFWorkbook workbook1 = new XSSFWorkbook();
        XSSFSheet sheet = workbook1.createSheet(sheetname);


        CellStyle cellStyle = workbook1.createCellStyle();
        CreationHelper createHelper = workbook1.getCreationHelper();
        // Set the date format of date
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        List<Object>  cellHeader= new ArrayList<Object>();
        cellHeader.addAll(Collections.unmodifiableList(Arrays.asList("Date","Particulars","Vch No.","ItemName","GSTIN/UIN","HSC/SAC","Quantity","Unit","City","State","State Code","Rate","Value","CGST%","CGST","SGST%","SGST","IGST%","IGSTA","CESS%","CESS","Gross Total")));

        int rowCount = 1;
        int columnCount=0;
        Row row = sheet.createRow(0);
        for (Object field : cellHeader) {
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue((String) field);
        }
        for (RowObject rowData : newExcelData) {
            row = sheet.createRow(rowCount++);

            writetoExcelrow(rowData, row,cellStyle);
        }
        row= sheet.createRow(rowCount);
        XSSFCellStyle style = workbook1.createCellStyle();
        //style.setBorderTop( 6); // double lines border
       // style.setBorderBottom(1); // single line border
        XSSFFont font = workbook1.createFont();
        //font.setFontHeightInPoints((short) 15);
        font.setBold(true);
        style.setFont(font);
        Cell cell = row.createCell(21);
        String formula= "SUM(V2:V"+(rowCount)+")";
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(formula);
        cell.setCellStyle(style);

        cell=row.createCell(0);
        cell.setCellValue("Total");
        cell.setCellStyle(style);

        cell=row.createCell(12);
        formula= "SUM(M2:M"+(rowCount)+")";
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(formula);
        cell.setCellStyle(style);

        cell=row.createCell(14);
        formula= "SUM(O2:O"+(rowCount)+")";
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(formula);
        cell.setCellStyle(style);

        cell=row.createCell(16);
        formula= "SUM(Q2:Q"+(rowCount)+")";
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(formula);
        cell.setCellStyle(style);

        cell=row.createCell(18);
        formula= "SUM(S2:S"+(rowCount)+")";
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(formula);
        cell.setCellStyle(style);

        cell=row.createCell(20);
        formula= "SUM(U2:U"+(rowCount)+")";
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(formula);
        cell.setCellStyle(style);
        sheet.createFreezePane(0,1);
        FileOutputStream  outputStream = new FileOutputStream(filename) ;
        workbook1.write(outputStream);

        workbook1.close();


        outputStream.close();
        return true;
    }

    public  void writetoExcelrow(RowObject rowdata,Row row ,CellStyle cellStyle){
        Cell cell = row.createCell(0);

        cell.setCellValue(rowdata.getDate());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue(rowdata.getParty_name());

        cell = row.createCell(2);
        cell.setCellValue(rowdata.getVch_no());

        cell = row.createCell(3);
        cell.setCellValue(rowdata.getItemName());

        cell = row.createCell(4);
        cell.setCellValue(rowdata.getGstn_uin());

        cell = row.createCell(5);
        cell.setCellValue(rowdata.getHsc_sac());

        cell = row.createCell(6);
        cell.setCellValue(rowdata.getQuantity());

        cell = row.createCell(7);
        cell.setCellValue(rowdata.getunitValue());


        cell = row.createCell(8);
        cell.setCellValue(rowdata.getCity());

        cell = row.createCell(9);
        cell.setCellValue(rowdata.getState());

        cell = row.createCell(10);
        cell.setCellValue(rowdata.getState_Code());

        cell = row.createCell(11);
        cell.setCellValue(rowdata.getRate());

        cell = row.createCell(12);
        cell.setCellValue(rowdata.getValue());

        cell = row.createCell(13);
        cell.setCellValue(rowdata.getCentral_gst_p());

        cell = row.createCell(14);
        cell.setCellValue(rowdata.getCentral_gst());

        cell = row.createCell(15);
        cell.setCellValue(rowdata.getState_gst_p());

        cell = row.createCell(16);
        cell.setCellValue(rowdata.getState_gst());

        cell = row.createCell(17);
        cell.setCellValue(rowdata.getIntegrated_gst_p());

        cell = row.createCell(18);
        cell.setCellValue(rowdata.getIntegrated_gst());

        cell = row.createCell(19);
        cell.setCellValue(rowdata.getCess_p());

        cell = row.createCell(20);
        cell.setCellValue(rowdata.getCess());

        cell = row.createCell(21);
        cell.setCellValue(rowdata.getGross_Total());

    }

}
