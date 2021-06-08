package com.work.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.work.model.dto.Member;

/**
 * Excel File Read class
 * 
 * @author HwangYuna
 * @version ver.1.0
 * @since jdk1.8
 * @see apache.poi.5.0
 */
public class ExcelRead {

	public ArrayList<Member> readExcel() {
		
		// path에 현재 클래스 절대경로가 저장됨
		String path = ExcelRead.class.getResource("").getPath();
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			File file = new File(path + "memberList.xlsx");
			
			FileInputStream in = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(in);
			
			// excel index 는 0 부터 시작
			int rowindex = 0;
			int colindex = 0;
			
			// sheet 수
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			// 행의 수 
			int rows = sheet.getPhysicalNumberOfRows();
			for(rowindex = 2; rowindex < rows; rowindex++) {
				Member dto = new Member();
				
				// 행 읽기
				XSSFRow row = sheet.getRow(rowindex);
				XSSFCell cell = row.getCell(2);
				
				dto.setMemberId(String.valueOf(row.getCell(0)));
				dto.setMemberPw(String.valueOf(row.getCell(1)));
				dto.setName(String.valueOf(row.getCell(2)));
				dto.setMobile(String.valueOf(row.getCell(3)));
				dto.setBirth(String.valueOf(row.getCell(4)));
				dto.setEntryDate(String.valueOf(row.getCell(5)));
				dto.setGrade(String.valueOf(row.getCell(6)));
				dto.setMileage(String.valueOf(row.getCell(7)));
				
				String value = "";
				
				switch (row.getCell(1).getCellType()) {
				case FORMULA:
					value = row.getCell(1).getCellFormula();
					break;
				case STRING:
					value = row.getCell(1).getStringCellValue() + "";
					break;
				case NUMERIC:
					value = row.getCell(1).getNumericCellValue() + "";
					break;
				case BLANK:
					value = row.getCell(1).getBooleanCellValue() + "";
					break;
				case ERROR:
					value = row.getCell(1).getErrorCellValue() + "";
					break;
				} 
				list.add(dto);
				
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
}
