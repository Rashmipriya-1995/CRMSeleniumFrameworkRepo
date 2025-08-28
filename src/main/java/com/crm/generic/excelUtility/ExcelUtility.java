package com.crm.generic.excelUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcelFile(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./TestData/TestScriptData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./TestData/TestScriptData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowNum = wb.getSheet(sheet).getLastRowNum();
		return rowNum;
	}
	
	public void setDataIntoExcelFile(String sheetName, int rowNum, int cellNum, String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./TestData/TestScriptData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		FileOutputStream fos=new FileOutputStream("./TestData/TestScriptData1.xlsx");
		wb.write(fos);
		wb.close();
		
	}

}
