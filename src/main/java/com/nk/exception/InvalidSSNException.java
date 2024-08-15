package com.nk.exception;

public class InvalidSSNException extends Exception{
	
	private static final long serialVersionUID = -7100144257323879059L;

	public InvalidSSNException() {
		super();
	}
	
	public InvalidSSNException(String msg) {
		super(msg);
	}
}
