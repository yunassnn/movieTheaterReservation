package com.work.model.dto;

/**
 * 영화 도메인 클래스 
 * @author Hwang yuna
 * @version ver.1.0
 * @since jdk1.8
 */
public class Movie {
	
	
	/** 영화 번호, 필수*/
	private String movieNum;

	/** 영화 제목, 필수 */
	private String movieTitle;
	
	/** 영화 연령 제한, 필수 */
	private String movieAge;

		
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
	public Movie(String movieNum, String movieTitle, String movieAge) {
		this.movieNum = movieNum;
		this.movieTitle = movieTitle;
		this.movieAge = movieAge;
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
	public String getMovieNum() {
		return movieNum;
	}

	/**
	 * @param movieNum the movieNum to set
	 */
	public void setMovieNum(String movieNum) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieNum == null) ? 0 : movieNum.hashCode());
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
		return builder.toString();
	}
	

}
