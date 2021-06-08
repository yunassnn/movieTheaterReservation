package com.work.veiw;

import java.util.ArrayList;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Member;
import com.work.model.service.MainLogin;
import com.work.model.service.MemberService;
import com.work.model.service.MovieService;
import com.work.model.service.ReservationService;
import com.work.util.Utility;

/**
 * 영화 예매 테스트 클래스 
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class Test {
	public static void main(String[] args) throws RecordNotFoundException, CommonException, DuplicateException {
		MemberService member= new MemberService();
		MovieService movie = new MovieService();
		
		member.addMember("test01", "password01", "테스터1", "010-1000-1000", "210517");
		member.addMember("test02", "password02", "테스터2", "010-1000-2000", "210604");
		member.addMember("test03", "password03", "테스터3", "010-1000-2000", "210604");
		member.addMember("t", "t", "테스터3", "010-1000-3000", "210604");
		member.addAdmin("a", "a", "관리자1", "010-1000-1000", "210604", "A");
		member.addAdmin("admin1", "password1", "관리자1", "010-1000-1111", "210604", "A");

		movie.addMovie("1", "크루엘라", "12 ");
		movie.addMovie("2", "THE HUNT", "19 ");
		movie.addMovie("3", "미니언즈", "ALL");
		
		Utility.printLine();
		
		MainLogin.loginDisplay();
		
	}
}
