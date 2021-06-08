package com.work.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.util.Utility;


/**
 * MainLogin Service class
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class MainLogin {
	public static String userId; // 로그인 아이디 정보 저장용
	public static String userPw; // 로그인 비밀번호 정보 저장용
	
	/**
	 * 메인 로그인 display
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void loginDisplay() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("영화 예매 프로그램");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 아이디 찾기");
		System.out.println("4. 비밀번호 찾기");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴를 선택하세요 : ");

		do {
			switch (Utility.inputString()) {
			case "1":
				login();
				break;
			case "2":
				join();
				break;
			case "3":
				findId();
				break;
			case "4":
				findPw();
				break;
			case "0":
				System.out.println("영화 예매 프로그램을 종료합니다.");
				System.exit(0);
				break;
			case "admin":
				adminLogin();
				break;

			default:
				System.out.println("\n [메뉴선택오류] 메뉴를 다시 확인하세요\n");
				loginDisplay();
				break;
			}
		} while (false);
	}
	
	/**
	 * 로그인 display
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void login() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("로그인");
		System.out.print("아이디 : ");
		userId = Utility.inputString();
		System.out.print("비밀번호 : ");
		userPw = Utility.inputString();
		Utility.printLine();
		
		MemberService.login(userId, userPw);
		
	}
	
	/**
	 * 관리자 로그인 display
	 * @throws DuplicateException 
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 */
	public static void adminLogin() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("관리자 로그인");
		System.out.print("아이디 : ");
		userId = Utility.inputString();
		System.out.print("비밀번호 : ");
		userPw = Utility.inputString();
		Utility.printLine();
		
		MemberService.login(userId, userPw);
		
	}

	/**
	 * 회원가입 display
	 * @throws DuplicateException
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 */
	public static void join() throws DuplicateException, RecordNotFoundException, CommonException {
		Utility.printTitle("회원가입");
		System.out.print("아이디 : ");
		String memberId = Utility.inputString();
		System.out.print("비밀번호 : ");
		String memberPw = Utility.inputString();
		System.out.print("이름 : ");
		String name = Utility.inputString();
		System.out.print("핸드폰 : ");
		String mobile = Utility.inputString();
		System.out.print("생년월일 : ");
		String birth = Utility.inputString();
		
		Utility.printLine();
		MemberService.addMember(memberId, memberPw, name, mobile, birth);
		Utility.printLine();
		
		loginDisplay();
	}

	/**
	 * 아이디 찾기 display
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void findId() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("아이디 찾기");
		System.out.print("이름 : ");
		String name = Utility.inputString();
		System.out.print("핸드폰 : ");
		String mobile = Utility.inputString();
		System.out.print("생년월일 : ");
		String birth = Utility.inputString();
		
		Utility.printLine();
		MemberService.findId(name, mobile, birth);
		Utility.printLine();
		
		loginDisplay();
	}

	/**
	 * 비밀번호 찾기 display
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void findPw() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("비밀번호 찾기");
		System.out.print("아이디 : ");
		String memberId = Utility.inputString();
		System.out.print("이름 : ");
		String name = Utility.inputString();
		System.out.print("핸드폰 : ");
		String mobile = Utility.inputString();
		System.out.print("생년월일 : ");
		String birth = Utility.inputString();

		Utility.printLine();
		MemberService.findPw(memberId, name, mobile, birth);
		Utility.printLine();
		
		loginDisplay();
	}

}
