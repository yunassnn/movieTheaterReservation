package com.work.model.dto;

/**
 * 영화 도메인 클래스 
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class Movie {
	
	/** 영화 제목, 필수 */
	private String movieTitle;
	
	/** 영화 번호, 필수*/
	private int movieNum;
	
	/** 영화 연령 제한, 필수 */
	private String movieAge;

	/** 영화 줄거리, 필수 */
	private String movieSummary;
	
	
	/** 기본 생성자 */
	public Movie() {

	}

	/**
	 * 필수 데이터 초기화 생성자
	 * @param movieTitle 영화제목
	 * @param movieNum 영화번호
	 * @param movieAge 영화연령
	 * @param movieSummary 영화줄거리
	 */
	public Movie(String movieTitle, int movieNum, String movieAge, String movieSummary) {
		this.movieTitle = movieTitle;
		this.movieNum = movieNum;
		this.movieAge = movieAge;
		this.movieSummary = movieSummary;
	}

	/**
	 * @return the movieTitle
	 */
	public String getMovieTitle() {
		return movieTitle;
	}

	/**
	 * @param movieTitle the movieTitle to set
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	/**
	 * @return the movieNum
	 */
	public int getMovieNum() {
		return movieNum;
	}

	/**
	 * @param movieNum the movieNum to set
	 */
	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}

	/**
	 * @return the movieAge
	 */
	public String getMovieAge() {
		return movieAge;
	}

	/**
	 * @param movieAge the movieAge to set
	 */
	public void setMovieAge(String movieAge) {
		this.movieAge = movieAge;
	}

	/**
	 * @return the movieSummary
	 */
	public String getMovieSummary() {
		return movieSummary;
	}

	/**
	 * @param movieSummary the movieSummary to set
	 */
	public void setMovieSummary(String movieSummary) {
		this.movieSummary = movieSummary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + movieNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (movieNum != other.movieNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(movieTitle);
		builder.append(", ");
		builder.append(movieNum);
		builder.append(", ");
		builder.append(movieAge);
		builder.append(", ");
		builder.append(movieSummary);
		return builder.toString();
	}
	

}
