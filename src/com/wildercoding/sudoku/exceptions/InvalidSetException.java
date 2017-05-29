package com.wildercoding.sudoku.exceptions;
/**
 * A InvalidSetException is thrown when trying to set a Square value when it has been Initialized or locked
 * @author Donovan J. Wilder
 *
 */
public class InvalidSetException extends Exception {

	public InvalidSetException(){
		
	}
	public InvalidSetException(String message){
		super(message);
	}

}
