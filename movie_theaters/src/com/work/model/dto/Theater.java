package com.work.model.dto;

import java.util.HashMap;

/**
 * 상영관 도메인 클래스
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class Theater {

	/** 상영관, 필수 */
	private String theater; 
	
	/** 상영관 좌석, 필수 */
	private HashMap<String, String> seat;
	
	private String[] theaterTime;

	/** 기본 생성자 */
	public Theater() {
		
	}

	/**
	 * @param theater
	 * @param seat
	 */
	public Theater(String theater, HashMap<String, String> seat, String[] theaterTime) {
		this.theater = theater;
		this.seat = seat;
		this.theaterTime = theaterTime;
	}

	/**
	 * @return the theater
	 */
	public String getTheater() {
		return theater;
	}

	/**
	 * @param theater the theater to set
	 */
	public void setTheater(String theater) {
		this.theater = theater;
	}

	/**
	 * @return the seat
	 */
	public HashMap<String, String> getSeat() {
		return seat;
	}

	/**
	 * @param seat the seat to set
	 */
	public void setSeat(HashMap<String, String> seat) {
		this.seat = seat;
	}

	/**
	 * @return the theaterTime
	 */
	public String[] getTheaterTime() {
		return theaterTime;
	}

	/**
	 * @param theaterTime the theaterTime to set
	 */
	public void setTheaterTime(String[] theaterTime) {
		this.theaterTime = theaterTime;
	}
	
	
}
