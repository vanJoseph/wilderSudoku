package com.wildercoding.sudoku.exceptions;

/**
 * Thrown when coordinates are out of bounds
 */
public class InvalidCoordinateException extends RuntimeException {

	public InvalidCoordinateException(String message){
		super(message);
	}
}
