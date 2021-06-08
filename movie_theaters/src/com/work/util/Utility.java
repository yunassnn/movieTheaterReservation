package com.work.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * 공통으로 사용하는 메서드를 위한 유틸리티 클래스
 * 모든 메서드는 객체생성없이 사용가능 Class Method 선언(static method)
 * 
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class Utility {
	public static void main(String[] args) {
			
	}
	
	/** 
	 * 테스트 시 테스트 항목을 출력하기 위한 메서드
	 * @param message 테스트 문자열
	 */
	public void print(String message) {
		System.out.println("\n### " + message);
	}
	
	/**
	 * 현재 날짜를 기본날짜형식 년도 4자리 월 2자리 일 2자리 형식의 문자열 변환 반환 메서드
	 * @return 지정 형식의 현자날짜 문자열
	 */
	public static String getCurrentDate() {
		return getCurrentDate("yyyy-MM-dd");
	};
	
	
	/**
	 * 아규먼트로 전달받은 날짜 형식의 현재날짜 변환 반환 메서드 
	 * @param pattern 날짜형식
	 * @return 날짜형식의 현재날짜 문자열
	 */
	public static String getCurrentDate(String pattern) {
		return getCurrentDate(pattern, Locale.KOREA);
	}
	
	/**
	 * <pre>
	 * 아규먼트로 전달받은 날짜 형식, 로케일 형식의 현재날짜 변환 반환 메서드
	 * 로케일 : java.util.Locale => FIELD 참고
	 * java.util.SimpleDateFormat 생성자 중복정의 => public SimpleDateFormat(String, Locale)
	 * </pre>
	 * @see java.util.Locale
	 * @see java.util.SimpleDateFormat
	 * @param pattern  날짜형식  및 시간 형식
	 * @param locale 로케일형식
	 * @return 날짜 형식, 로케일 형식의 현재날짜 문자열
	 */
	public static String getCurrentDate(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	/**
	 * 회원정보 입력 실패시 나타나는 오류 메시지
	 */
	public static void notFoundInfoMsg() {
		System.out.println("\n - 입력하신 정보와 일치하는 회원이 없습니다.");
		System.out.println(" - 비회원이시라면, [회원가입]후 로그인 하세요.");
	}
	
	/**
	 * 보안문자 4자리 숫자 반환
	 * @return 4자리 보안문자(숫자)
	 */
	public static String getSecureNumberString() {
		return getSecureNumberString(4);
	}

	/**
	 * 보안문자 지정길이 숫자 반환
	 * @param length 지정 숫자
	 * @return 지정된 길이의 보안문자(숫자)
	 */
	public static String getSecureNumberString(int length) {
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		StringBuilder secureStringBuilder = new StringBuilder();
		for(int index = 0; index < length ; index++) {
			secureStringBuilder.append(random.nextInt(10)); // 0 ~ 9, 4번 반복 : 숫자 랜덤 4자리
		}
		return secureStringBuilder.toString();
	}
	
	/** 
	 * 보안문자 4글자 영문 대문자 반환
	 * @return 4자리 보안문자(알파벳 대문자)
	 */
	public static String getSecureAlphabetString() {
		return getSecureAlphabetString(4);
	}
	
	/**
	 * 보안문자 지정길이 영문 대문자 반환
	 * @param length 보안문자 지정길이
	 * @return 지정된 길이의 보안문자(알파벳 대문자)
	 */
	public static String getSecureAlphabetString(int length) {
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		StringBuilder secureStringBuilder = new StringBuilder();
		
		for(int index = 0; index < length ; index++) {
			secureStringBuilder.append((char)(random.nextInt(26) + 65));
			//secureStringBuilder.append((char)(random.nextInt(26) + 97)); // 소문자
		}
		return secureStringBuilder.toString();
	}
	
	/**
	 * 보안 영문 대/소문자 지정길이 반환
	 * @param length 보안문자 지정길이
	 * @param isUpper 대/소문자 혼합여부 
	 * @return T 대문자 / F 대/소문자 
	 */
	public static String getSecureAlphabetString(int length, boolean isUpper) {
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		StringBuilder secureStringBuilder = new StringBuilder();
		
		for(int index = 0; index < length ; index++) {
			if(isUpper) {
				secureStringBuilder.append((char)(random.nextInt(26) + 'A'));
			} else {
				secureStringBuilder.append((char)(random.nextInt(26) + 'a'));
			}
		}
		return secureStringBuilder.toString();
	}

	
	/**
	 * 보안 영문 대/소문자, 숫자 혼합 지정길이 반환
	 * @param length 보안문자 지정길이
	 * @param isUpper 대/소문자 혼합 여부
	 * @param isMixed 대/소문자, 숫자 혼합 여부
	 * @return  T 대/소문자, 숫자 혼합, F 대/소문자 
	 */
	public static String getSecureAlphabetString(int length, boolean isUpper, boolean isMixed) {
		
		if(isMixed) {
			Random random = new Random((long)(Math.random() * System.nanoTime()));
			StringBuilder secureStringBuilder = new StringBuilder();
			
			for(int index = 0; index < length ; index++) {
				if(random.nextBoolean()) {
					secureStringBuilder.append(random.nextInt(10));
				} else {
					if(isUpper) {
						secureStringBuilder.append((char)(random.nextInt(26) + 'A'));
					} else {
						secureStringBuilder.append((char)(random.nextInt(26) + 'a'));
					}
				}
			}
			return secureStringBuilder.toString();
		} else {
			return getSecureAlphabetString(length, isUpper);
		}
	
	}
	
	/**
	 * 구분선
	 */
	public static void printLine() {
		System.out.println("-------------------------");
	}
	
	/**
	 * 구분선 + 제목
	 * @param title 제목
	 */
	public static void printTitle(String title) {
		System.out.println();
		printLine();
		System.out.println(title);
		printLine();
	}
	
	/**
	 * 문자열 입력 반환
	 * @return 입력 문자열
	 */
	public static String inputString() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 숫자 입력 반환
	 * @return 입력 숫자
	 */
	public static int inputNumber() {
		String data = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			data = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(data);
	}

}









