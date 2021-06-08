package com.work.model.service;

import java.util.ArrayList;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Member;
import com.work.model.dto.Movie;
import com.work.util.Utility;

/**
 * Movie Service class
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class MovieService {
	/** 영화를 저장관리하기 위한 자료 저장구조 */
	private static ArrayList<Movie> movielist = new ArrayList<Movie>();

	/** 기본생성자 */
	public MovieService() {
	}

	/**
	 * 현재 상영중인 영화 목록 display
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void movieTheater() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("현재 상영중");

		for (int i = 0; i < movielist.size(); i++) {
			System.out.println(
					(i + 1) + ". " + movielist.get(i).getMovieTitle() + "\t(" + movielist.get(i).getMovieAge() + ")");
		}
		System.out.println("0. 뒤로가기");
		System.out.print("메뉴를 선택하세요 : ");

		do {
			String k = Utility.inputString();
			if (Integer.valueOf(k) > 0) {
				reservation(k);
				break;
			} else if (Integer.valueOf(k) == 0) {
				MemberService.menuBack();
				break;
			} else {
				System.out.println("\n - 메뉴선택 오류 메뉴를 다시 확인하세요\n");
				MemberService.menuBack();
				break;
			}
		} while (false);
	}

	/**
	 * 영화 관리 display
	 * 
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void movieManagement() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("영화 관리");
		System.out.println("1. 현재 상영중인 영화 목록 조회");
		System.out.println("2. 영화 등록");
		System.out.println("2. 영화 삭제");
		System.out.println("0. 뒤로가기");
		System.out.print("메뉴를 선택하세요 : ");
		do {
			switch (Utility.inputString()) {
			case "1":
				getMovie();
				break;
			case "2":
				addMovie();
				break;
			case "3":
				removeMovie();
				break;
			case "0":
				MemberService.menuBack();
				break;
			default:
				System.out.println("\n - 메뉴선택 오류 메뉴를 다시 확인하세요\n");
				break;
			}
		} while (false);

	}

	/**
	 * 영화 등록 display
	 * 
	 * @throws DuplicateException
	 * @throws CommonException
	 * @throws RecordNotFoundException
	 */
	public static void addMovie() throws DuplicateException, RecordNotFoundException, CommonException {
		Utility.printTitle("영화 등록");
		System.out.print("영화 고유번호 : ");
		String movieNum = Utility.inputString();
		System.out.print("영화 제목 : ");
		String movieTitle = Utility.inputString();
		System.out.print("영화 연령제한 : ");
		String movieAge = Utility.inputString();

		Utility.printLine();
		addMovie(movieNum, movieTitle, movieAge);
		Utility.printLine();
	}

	/**
	 * 영화 등록
	 * 
	 * @param dto
	 * @throws DuplicateException
	 * @throws CommonException
	 * @throws RecordNotFoundException
	 */
	public static void addMovie(Movie dto) throws DuplicateException {
		int index = exist(dto.getMovieNum());
		if (index >= 0) {
			System.out.println(" - 중복된 영화가 있습니다, 다시 시도해 주세요");
			throw new DuplicateException(dto.getMovieNum() + ", " + dto.getMovieTitle());
		}
		System.out.println(" - 영화 등록 완료, 영화 관리 메뉴로 돌아갑니다.");
		movielist.add(dto);
	}

	/**
	 * 영화 등록
	 * 
	 * @param movieNum   영화 고유번호
	 * @param movieTitle 영화 제목
	 * @param movieAge   영화 연령제한
	 * @throws DuplicateException
	 * @throws CommonException
	 * @throws RecordNotFoundException
	 */
	public static void addMovie(String movieNum, String movieTitle, String movieAge) throws DuplicateException {
		Movie dto = new Movie(movieNum, movieTitle, movieAge);
		addMovie(dto);
	}

	/**
	 * 영화 존재 유무 조회
	 * 
	 * @param movieNum 영화 고유번호
	 * @return 존재시 저장위치 인덱스번호, 미존재시 -1
	 */
	public static int exist(String movieNum) {
		for (int index = 0; index < movielist.size(); index++) {
			if (movielist.get(index).getMovieNum().equals(movieNum)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * 영화 목록 조회 display
	 * 
	 * @throws DuplicateException
	 * @throws CommonException
	 * @throws RecordNotFoundException
	 */
	public static void movieList() throws RecordNotFoundException, CommonException, DuplicateException {
		System.out.println(" - 현재 등록된 영화 : " + getMovieCount() + " 개의 목록입니다.");
		Utility.printLine();
		getMovie();
		Utility.printLine();
		System.out.println(" - 조회를 종료하고 관리자 메뉴로 이동합니다.");
		MemberService.adminMenu();
	}

	/**
	 * 현재 등록된 전체 영화 목록 조회
	 * 
	 * @throws DuplicateException
	 * @throws CommonException
	 * @throws RecordNotFoundException
	 */
	public static ArrayList getMovie() throws RecordNotFoundException, CommonException, DuplicateException {

		Utility.printLine();
		for (int index = 0; index < movielist.size(); index++) {
			Movie dto = movielist.get(index);
			System.out.println("| no : " + dto.getMovieNum() + "\t| LimitAge : " + dto.getMovieAge() + "\t| Title : "
					+ dto.getMovieTitle());
		}
		Utility.printLine();
		System.out.println(" - 영화 목록 조회 완료, 관리자 메뉴로 이동합니다.");
		MemberService.adminMenu();
		return movielist;
	}

	/**
	 * 특정 영화 조회
	 * 
	 * @param movieNum 영화 고유번호
	 * @return 해당 고유번호와 일치하는 인덱스의 영화 정보를 반환
	 */
	public static Movie getMovie(String movieNum) {
		int index = exist(movieNum);
		if (index >= 0) {
			movielist.get(index);
		}
		return null;
	}

	/**
	 * 현재 등록된 영화 수 조회
	 * 
	 * @return 현재 등록된 영화 수
	 */
	public static int getMovieCount() {
		return movielist.size();
	}

	/**
	 * 영화 삭제 display
	 * 
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void removeMovie() throws RecordNotFoundException, CommonException, DuplicateException {
		Utility.printTitle("영화 삭제");
		System.out.println("삭제할 영화 고유번호 : ");
		String num = Utility.inputString();

		Utility.printLine();
		removeMovie(num);
		Utility.printLine();
	}

	/**
	 * 영화 삭제
	 * 
	 * @param movieNum   영화 고유번호
	 * @param movieTitle 영화 제목
	 * @param movieAge   영화 연령제한
	 * @return 일치하는 영화가 있는 경우 삭제 후 관리자 메뉴 / 없는 경우 실패 메시지 출력 후 관리자 메뉴
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static Movie removeMovie(String movieNum)
			throws RecordNotFoundException, CommonException, DuplicateException {
		int index = exist(movieNum);
		if (index >= 0 && movielist.get(index).getMovieNum().equals(movieNum)) {
			Utility.printLine();
			movielist.remove(index);
			System.out.println(" - 영화 삭제 성공");
			System.out.println(" - 관리자 메뉴로 돌아갑니다.");
			MemberService.adminMenu();
			return null;
		}
		System.out.println(" - 입력한 숫자와 일치하는 영화 번호가 없어 삭제에 실패하였습니다.");
		MemberService.adminMenu();
		throw new RecordNotFoundException();
	}

	/** 영화관 좌석을 저장관리하기 위한 배열 저장구조 */
	public static String[][] seat = new String[5][9];

	/**
	 * 영화관 좌석 배열 생성
	 */
	public void resetSeat() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 9; j++) {
				seat[i][j] = "___";
			}
		}
	}

	/**
	 * 영화관 좌석 현황
	 */
	public void reference() {
		char row = 'A';
		for (int i = 1; i < 5; i++) {
			System.out.print("\n" + row + "열  ");
			row++;
			for (int j = 1; j < 9; j++) {
				String seat = (this.seat[i][j].equals("___")) ? "◻︎" : "◼︎";
				System.out.print((j) + seat + "  ");
			}
		}
		System.out.println();
	}

	/**
	 * movie reservation display
	 * 
	 * @param movieNum
	 * @throws RecordNotFoundException
	 * @throws CommonException
	 * @throws DuplicateException
	 */
	public static void reservation(String movieNum)
			throws RecordNotFoundException, CommonException, DuplicateException {

		MovieService rs = new MovieService();
		rs.resetSeat();

		int row, col;
		String user = MainLogin.userId;
		boolean flag = true;
		boolean exit = true;
		user = MainLogin.userId;

		for (int index = 0; index < movielist.size(); index++) {
			if (movielist.get(index).getMovieNum().equals(movieNum)) {
				String title = movielist.get(index).getMovieTitle();

				do {
					Utility.printTitle("[" + title + " 예매 화면]");
					System.out.println("1. 좌석 조회");
					System.out.println("2. 좌석 선택");
					System.out.println("3. 예매 취소");
					System.out.println("0. 뒤로가기");
					System.out.print("메뉴를 선택하세요 : ");
					int num = Utility.inputNumber();
					if (num == 1) {
						Utility.printTitle("좌석 조회");
						rs.reference();
						Utility.printLine();
						System.out.println(" - 좌석 조회가 완료되었습니다. 영화 예매 메뉴로 이동합니다.");
						reservation(movieNum);
					} else if (num == 2) {
						do {
							System.out.print("열을 선택하세요 : ");
							row = Utility.inputNumber();
							System.out.print("행을 선택하세요 : ");
							col = Utility.inputNumber();

							if (seat[row][col].equals("___")) {
								seat[row][col] = user;
								System.out.println(row + "-" + col + " 좌석이 예약되었습니다.");
								flag = false;
								System.out.println(" - 회원 메뉴로 이동합니다.");
							} else {
								System.out.println(" - 이미 예약된 좌석입니다. 다른 좌석을 선택해주세요.");
							}
							rs.reference();
						} while (flag);
					} else if (num == 3) {
						for (int i = 1; i < 5; i++) {
							for (int j = 1; j < 9; j++)
								if (seat[i][j].equals(user)) {
									String yn;
									System.out.println(" - 예약하신 좌석은 " + i + "열" + j + "행 입니다.");
									System.out.print(" - 예약을 취소하시겠습니까? (y : 예약취소 / n : 뒤로가기) : ");
									yn = Utility.inputString();
									if (yn.equals("y")) {
										seat[i][j] = "___";
										System.out.println(" - 예약이 취소되었습니다. 회원 메뉴로 이동합니다.");
									} else if (yn.equals("n")) {
										System.out.println("- 예약이 취소되지 않았습니다. 이전 화면으로 이동합니다.");
									}
									rs.reference();
								}
						}
					} else if (num == 0) {
						System.out.println(" - 영화 선택 화면으로 이동합니다.");
						MovieService.movieTheater();
						break;
					}
				} while (exit);
			}
		}
	}

}
