package com.work.exception;

public class RecordNotFoundException extends Exception {
	
	/**
	 * 데이터 미존재 예외클래스
	 */
	public RecordNotFoundException() {
		super("데이터 검색 예외");
	}

	public RecordNotFoundException(String message) {
		super("데이터 검색 예외 : " + message);
	}
}
