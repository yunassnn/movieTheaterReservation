package com.work.model.dto;

/**
 * 예매 도메인 클래스 
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class Reservation {
	
	/** 회원 클래스 */
	private Member member;
	
	/** 영화 클래스 */
	private Movie movie;
	
	/** 영화관 클래스 */
	private Theater theater;
	
	/** 영화 예매 데이터 */
	private String reservationData;
	
	/** 영화 결제 방식, 필수 */
	private String payment;


	/** 기본 생성자 */
	public Reservation() {
	}
	
	/**
	 * @param member
	 * @param movie
	 * @param theater
	 * @param reservationData
	 * @param payment
	 */
	public Reservation(Member member, Movie movie, Theater theater, String reservationData, String payment) {
		this.member = member;
		this.movie = movie;
		this.theater = theater;
		this.reservationData = reservationData;
		this.payment = payment;
	}

	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the movie
	 */
	public Movie getMovie() {
		return movie;
	}

	/**
	 * @param movie the movie to set
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * @return the theater
	 */
	public Theater getTheater() {
		return theater;
	}

	/**
	 * @param theater the theater to set
	 */
	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	/**
	 * @return the reservationData
	 */
	public String getReservationData() {
		return reservationData;
	}

	/**
	 * @param reservationData the reservationData to set
	 */
	public void setReservationData(String reservationData) {
		this.reservationData = reservationData;
	}

	/**
	 * @return the payment
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	
	
}
