package com.wildercoding.sudoku;

/**
 * The standard representation of values that can be held by a sudoku puzzle
 * 
 * @author Donovan J. Wilder
 * @since 2016-07-17
 *
 */
public enum Number {
	EMPTY(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);
	private int value;

	/**
	 * Sets the value field to the value
	 * 
	 * @param value
	 *            an int 0-9
	 */
	private Number(int value) {
		this.value = value;
	}

	/**
	 * Returns the Number of an int value of the numbers 0-9
	 * 
	 * @param value
	 *            an int of 0-9
	 *             if the value is not of int 0-9
	 */
	public static Number valueOf(int value) {
		switch (value) {
		case 0:
			return EMPTY;
		case 1:
			return ONE;
		case 2:
			return TWO;
		case 3:
			return THREE;
		case 4:
			return FOUR;
		case 5:
			return FIVE;
		case 6:
			return SIX;
		case 7:
			return SEVEN;
		case 8:
			return EIGHT;
		case 9:
			return NINE;
		default:
			return null;
		}

	}

	/**
	 * Returns the Number of a character representation of the value 0-9
	 * @param value a char of the numbers 0-9
	 */
	public static Number valueOf(char value) {

		switch (value) {
		case '0':
			return EMPTY;
		case '1':
			return ONE;
		case '2':
			return TWO;
		case '3':
			return THREE;
		case '4':
			return FOUR;
		case '5':
			return FIVE;
		case '6':
			return SIX;
		case '7':
			return SEVEN;
		case '8':
			return EIGHT;
		case '9':
			return NINE;
		default:
			return null;
		}
	}

	/**
	 * Returns the a string representation of the value 0-9
	 */
	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
