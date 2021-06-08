package com.work.exception;

public class CommonException extends Exception {

	/**
	 * 사용자 미존재 예외클래스
	 */
	public CommonException() {
		super("사용자 예외");
	}

	public CommonException(String message) {
		super("[오류] " + message);
	}
}
