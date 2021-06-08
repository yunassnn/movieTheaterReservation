package com.work.exception;

public class DuplicateException extends Exception {
	
	/**
	 * 중복 예외클래스
	 */
	public DuplicateException() {
		super("중복 예외");
	}
	
	public DuplicateException(String message) {
		super("중복 예외 : " + message);
	}
	
}
