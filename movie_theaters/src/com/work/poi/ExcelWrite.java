package com.work.poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel File Write class
 * 
 * @author HwangYuna
 * @version ver.1.0
 * @since jdk1.8
 * @see apache.poi.5.0
 */
public class ExcelWrite {
	
	public void writeExcel(ArrayList<Member> list) {
		
		String path = ExcelWrite.class.getResource("").getPath();
		
		try {
			File file = new File(path + "memberList.xlsx"); 
			FileOutputStream out = new FileOutputStream(file);
			
			XSSFWorkbook xworkbook = new XSSFWorkbook();
			
			XSSFSheet xsheet = xworkbook.createSheet("영화관회원");
			XSSFRow curRow;
			
			int row = list.size(); // list 크기
			Cell cell = null;
			
			// Title
			curRow = xsheet.createRow(0);
			cell = curRow.createCell(0);
			cell.setCellValue("영화관 회원 목록");
			
			// Head 
			curRow = xsheet.createRow(1);
			cell = curRow.createCell(0);
			cell.setCellValue("아이디");
			
			cell = curRow.createCell(1);
			cell.setCellValue("비밀번호");
			
			cell = curRow.createCell(2);
			cell.setCellValue("이름");
			
			cell = curRow.createCell(3);
			cell.setCellValue("핸드폰");
			
			cell = curRow.createCell(4);
			cell.setCellValue("생일");
			
			cell = curRow.createCell(5);
			cell.setCellValue("가입일");
			
			cell = curRow.createCell(6);
			cell.setCellValue("등급");
			
			cell = curRow.createCell(7);
			cell.setCellValue("마일리지");
			
			// Body
			for(int i = 1; i < row; i++) {
				curRow = xsheet.createRow(i); //row 생성
				
				cell = curRow.createCell(0);
				cell.setCellValue(list.get(i).getName());
				
			}
			
			// 열 너비 설정
			for(int i = 0; i < 3; i++) {
				xsheet.autoSizeColumn(i);
				xsheet.setColumnWidth(i, (xsheet.getColumnWidth(i)) + 256);
			}
			xworkbook.write(out);
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
