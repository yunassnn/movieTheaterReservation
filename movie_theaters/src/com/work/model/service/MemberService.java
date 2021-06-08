package com.work.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Member;
import com.work.util.Utility;

/**
 * Member Service class
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class MemberService {
	/** 회원들을 저장관리하기 위한 자료 저장구조 */
	private static ArrayList<Member> list = new ArrayList<Member>();

	/** 기본생성자 : 초기화 회원 등록 수행 */
	public MemberService() {
	}

	/**
	 * member service menu display
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void memberMenu() throws RecordNotFoundException, CommonException, DuplicateException {
		for (int index = 0; index < list.size(); index++) {
			if (MainLogin.userId.equals(list.get(index).getMemberId())) {
				Utility.printTitle(list.get(index).getMemberId() + " 님 환영합니다");
				System.out.println("1. 영화 에매");
				System.out.println("2. 내 정보 관리");
				System.out.println("3. 예매내역 조회");
				System.out.println("0. 로그아웃");
				System.out.print("메뉴를 선택하세요 : ");

				do {
					switch (Utility.inputString()) {
					case "1":
						MovieService.movieTheater();
						break;

					case "2":
						managementMyInfo();
						break;

					case "3":
						break;

					case "0":
						System.out.println(list.get(index).getMemberId() + " 님 로그아웃 되었습니다.");
						MainLogin.loginDisplay();
						break;

					default:
						System.out.println("\n [메뉴선택오류] 메뉴를 다시 확인하세요\n");
						break;
					}
				} while (false);
			} 
		}
	}

	/**
	 * admin service menu display
	 * @throws DuplicateException 
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 */
	public static void adminMenu() throws RecordNotFoundException, CommonException, DuplicateException {
		for (int index = 0; index < list.size(); index++) {
			if (MainLogin.userId.equals(list.get(index).getMemberId())) {
				Utility.printTitle(list.get(index).getMemberId() + " 관리자님 환영합니다");
				System.out.println("1. 영화 관리");
				System.out.println("2. 회원 조회");
				System.out.println("3. 내 정보 관리");
				System.out.println("0. 로그아웃");
				System.out.print("메뉴를 선택하세요 : ");
				
				do {
					switch (Utility.inputString()) {
					case "1":
						MovieService.movieManagement();
						break;

					case "2":
						memberList();
						break;

					case "3":
						managementMyInfo();
						break;

					case "0":
						System.out.println(list.get(index).getMemberId() + " 관리자님 로그아웃 되었습니다.");
						MainLogin.loginDisplay();
						break;

					default:
						System.out.println("\n [메뉴선택오류] 메뉴를 다시 확인하세요\n");
						break;
					}
				} while (false);
			}
		}
	}
	
	/**
	 * 내 정보 관리 display
	 * @param memberId memberMyInfo에서 받아온 로그인 아이디
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void managementMyInfo() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("내 정보 관리");
		System.out.println("1. 내 정보 조회");
		System.out.println("2. 비밀번호 변경");
		System.out.println("3. 탈퇴하기");
		System.out.println("0. 뒤로가기");
		System.out.print("메뉴를 선택하세요 : ");

		do {
			switch (Utility.inputString()) {
			case "1":
				getMember(MainLogin.userId);
				break;
			case "2":
				changeMemberPw();
				break;
			case "3":
				removeMember();
				break;
			case "0":
				menuBack();
				break;
			default:
				System.out.println("\n - 메뉴선택 오류 메뉴를 다시 확인하세요\n");
				break;
			}
		} while (false);
	}
	
	/**
	 * 내 정보 조회에서 뒤로가기 (등급별 구분)
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void menuBack() throws RecordNotFoundException, CommonException, DuplicateException {
		int index = exist(MainLogin.userId);
		if (index >= 0) {
			Member member = list.get(index);
			if(member.getGrade().equals("G")) {
				System.out.println(" - 회원 메뉴로 이동합니다.");
				memberMenu();
			} else if(member.getGrade().equals("A")) {
				System.out.println(" - 관리자 메뉴로 이동합니다.");
				adminMenu();
			}
		}
	}

	/**
	 * 회원 탈퇴 display
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void removeMember() throws RecordNotFoundException, CommonException, DuplicateException {
		System.out.print("아이디 : ");
		String id3 = Utility.inputString();
		System.out.print("현재 비밀번호 입력 : ");
		String pw3 = Utility.inputString();
		
		Utility.printLine();
		removeMember(id3, pw3);
		Utility.printLine();
	}

	/**
	 * 회원 탈퇴
	 * 
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 성공시 탈퇴회원의 기존 정보 T / 실패시 null F
	 * @throws CommonException 
	 * @throws DuplicateException 
	 */
	public static Member removeMember(String memberId, String memberPw) throws RecordNotFoundException, CommonException, DuplicateException {
		int index = exist(memberId);
		if (index >= 0 && list.get(index).getMemberPw().equals(memberPw)) {
			Utility.printLine();
			list.remove(index);
			System.out.println(" - 회원탈퇴 성공");
			System.out.println(" - 메인화면으로 돌아갑니다.");
			MainLogin.loginDisplay();
			return null;
		}
		// return null;
		System.out.println(" - 회원정보 불일치로 회원탈퇴에 실패하였습니다.");
		memberMenu();
		throw new RecordNotFoundException(memberId);
	}

	/**
	 * 회원정보 전체 변경
	 * 
	 * @param dto 변경된 회원객체
	 * @return 성공시 T 실패시 F
	 */
	public boolean setMember(Member dto) throws RecordNotFoundException {
		int index = exist(dto.getMemberId());
		if (index >= 0) {
			list.set(index, dto);
			return true;
		}
		// return false;
		throw new RecordNotFoundException();
	}

	/**
	 * 비밀번호 변경 display
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void changeMemberPw() throws RecordNotFoundException, CommonException, DuplicateException {
		System.out.print("아이디 : ");
		String id2 = Utility.inputString();
		System.out.print("현재 비밀번호 입력 : ");
		String pw2 = Utility.inputString();
		System.out.print("변경 비밀번호 입력 : ");
		String modifyMemberPw = Utility.inputString();
		
		Utility.printLine();
		setMemberPw(id2, pw2, modifyMemberPw);
		Utility.printLine();
		
	}
	
	/**
	 * 비밀번호 변경 -- 아규먼트 : 아이디, 비밀번호, 변경비밀번호 -- 도메일 데이터 각각 : 아이디, 비밀번호, 변경비밀번호 --
	 * 도메인객체 : Member? xxx 도메인 속성에 변경비밀번호 없음 -- Map : key:value
	 * 
	 * @param memberId
	 * @param memberPw
	 * @param modifyMemberPw
	 * @return
	 * @throws CommonException 
	 * @throws DuplicateException 
	 */
	public static boolean setMemberPw(String memberId, String memberPw, String modifyMemberPw) throws RecordNotFoundException, CommonException, DuplicateException {
		int index = exist(memberId);

		if (index >= 0 && list.get(index).getMemberPw().equals(memberPw)) { 
			list.get(index).setMemberPw(modifyMemberPw);
			Utility.printLine();
			System.out.println(" - 비밀번호가 변경되었습니다. 다시 로그인 해주세요.");
			MainLogin.loginDisplay();
			return true;
		}
		System.out.println(" - 회원정보 불일치로 비밀번호 변경에 실패하였습니다.");
		memberMenu();
		throw new RecordNotFoundException(memberId);
	}

	/**
	 * 회원 목록 조회 display
	 * @throws DuplicateException 
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 */
	public static void memberList() throws RecordNotFoundException, CommonException, DuplicateException {
		System.out.println(" - 현재 등록된 회원 : " + getCount() + " 명의 목록입니다.");
		Utility.printLine();
		getMember();
		Utility.printLine();
		System.out.println(" - 조회를 종료하고 관리자 메뉴로 이동합니다.");
		adminMenu();
	}
	
	/** 현재 등록한 전체 회원 조회 */
	public static ArrayList getMember() {
		
		for (int index = 0; index < list.size(); index++) {
			System.out.println(list.get(index));
		}
		return list;
	}

	/**
	 * 회원 상세조회
	 * 
	 * @param memberId 아이디
	 * @throws DuplicateException 
	 * @throws CommonException 
	 * @throws RecordNotFoundException
	 * @see exist(String)
	 */
	public static Member getMember(String memberId) throws RecordNotFoundException, CommonException, DuplicateException {
		int index = exist(memberId);
		if (index >= 0) {
			Member member = list.get(index);
			Utility.printTitle("[ " + member.getMemberId() + " 님의 회원정보 ]");
			
			System.out.println("ID \t\t: " + member.getMemberId());
			System.out.println("PW \t\t: " + member.getMemberPw());
			System.out.println("Name \t\t: " + member.getName());
			System.out.println("Mobile \t\t: " + member.getMobile());
			System.out.println("Birth \t\t: " + member.getBirth());
			System.out.println("Grade \t\t: " + member.getGrade());
			System.out.println("Entry Date \t: " + member.getEntryDate());
			System.out.println("Mileage \t: " + member.getMileage());
			Utility.printLine();

			menuBack();
		}
		Utility.notFoundInfoMsg();
		return null;
	}
	
	/**
	 * 현재 등록 인원수 조회 
	 * 
	 * @return 현재 등록 인원수
	 */
	public static int getCount() {
		return list.size();
	}

	/**
	 * 회원 등록
	 * 
	 * @param dto 등록 회원
	 * @throws DuplicateException
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 */
	public static void addMember(Member dto) throws DuplicateException {
		int index = exist(dto.getMemberId());
		if (index >= 0) {
			System.out.println(" - 중복된 회원이 있습니다.");
			System.out.println(" - 다시 시도해 주세요.");
			throw new DuplicateException(dto.getMemberId());
		}
		System.out.println(" - 회원가입 완료. 메인메뉴에서 로그인 해주세요.");
		list.add(dto);

	}

	/**
	 * 회원 등록 -- 사용자 입력 데이터 : 아이디, 비밀번호, 이름, 휴대폰, 이메일 -- 시스템 최초 가입시 일반회원 가입처리 : 가입일,
	 * 등급, [마일리지]
	 * 
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param name     이름
	 * @param mobile   휴대폰
	 * @param birth    생년월일
	 * @throws DuplicateException
	 */
	public static void addMember(String memberId, String memberPw, String name, String mobile, String birth)
			throws DuplicateException {
		Member dto = new Member(memberId, memberPw, name, mobile, birth);
		dto.setGrade("G");
		dto.setEntryDate(Utility.getCurrentDate());
		dto.setMileage("1000");

		addMember(dto);
	}
	
	/**
	 * 관리자 등록 
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @param birth 생년월일
	 * @param grade 등급
	 * @throws DuplicateException
	 */
	public static void addAdmin (String memberId, String memberPw, String name, String mobile, String birth, String grade)
			throws DuplicateException {
		Member dto = new Member(memberId, memberPw, name, mobile, birth);
		dto.setGrade("A");
		dto.setEntryDate(Utility.getCurrentDate());
		dto.setMileage("0");
		
		addMember(dto);
	}

	/**
	 * 회원 존재 유무 조회
	 * 
	 * @param memberId 아이디
	 * @return 존재시 저장위치 인덱스번호, 미존재시 -1
	 */
	public static int exist(String memberId) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getMemberId().equals(memberId)) {
				return index;
			} 
		}
		
		return -1;
	}

	
	/**
	 * 로그인
	 * 성공시 회원 서비스 메뉴로 이동 / 실패시 메인 로그인 화면으로 이동
	 * @param memberId 아이디
	 * @param memberPw 비밀번호 
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void login(String memberId, String memberPw) throws RecordNotFoundException, CommonException, DuplicateException {
		int index = exist(memberId);
		if (index >= 0) {
			if (list.get(index).getMemberPw().equals(memberPw) && list.get(index).getGrade().equals("G")) {
				System.out.println(" - 회원 로그인 성공, 회원 메뉴로 이동합니다.");	
				memberMenu();
			} else if (list.get(index).getMemberPw().equals(memberPw) && list.get(index).getGrade().equals("A")) {
				System.out.println(" - 관리자 로그인 성공, 관리자 메뉴로 이동합니다.");	
				adminMenu();
			}
		}
		Utility.notFoundInfoMsg();
		MainLogin.loginDisplay();
	}
	
	/**
	 * 아이디 찾기
	 * 
	 * @param name   이름
	 * @param mobile 핸드폰
	 * @param birth  생일
	 * @return 이름 & 핸드폰 & 생일 정보가 일치하는 경우 아이디 반환 / 이름 or 핸드폰 번호가 틀릴 경우 null 반환
	 */
	public static String findId(String name, String mobile, String birth) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getName().equals(name) && list.get(index).getMobile().equals(mobile)
					&& list.get(index).getBirth().equals(birth)) {
				String findMyId = list.get(index).getMemberId(); 
				System.out.println(" - 회원님의 아이디는 " + findMyId + " 입니다.");
				System.out.println(" - 메인화면에서 다시 로그인 해주세요.");
				return findMyId;
			}
		}
		Utility.notFoundInfoMsg();
		return null;
	}

	/**
	 * 비밀번호 찾기
	 * 
	 * @param memberId 아이디
	 * @param name     이름
	 * @param mobile   핸드폰
	 * @param birth    생일
	 * @return 아이디, 이름, 핸드폰 번호, 생일 정보 일치하는 회원이 있는 경우 임시 비밀번호로 정보 변경 후 반환 / 일치하지 않는 경우 null 반환
	 * @throws DuplicateException 
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 */
	public static String findPw(String memberId, String name, String mobile, String birth) throws RecordNotFoundException, CommonException, DuplicateException {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getMemberId().equals(memberId) && list.get(index).getName().equals(name)
					&& list.get(index).getMobile().equals(mobile) && list.get(index).getBirth().equals(birth)) {
				String findMyPw = Utility.getSecureAlphabetString(8, true, true);
				String memberPw = list.get(index).getMemberPw();
				System.out.println(" - 회원님의 임시 비밀번호는 " + findMyPw + " 입니다.");
				System.out.println(" - 비밀번호 재설정은 내 정보 관리에서 가능합니다.");
				System.out.println(" - 메인화면에서 다시 로그인 해주세요.");
				setMemberPw(memberId, memberPw, findMyPw);
				return findMyPw;
			}
		}
		Utility.notFoundInfoMsg();
		return null;
	}

}
