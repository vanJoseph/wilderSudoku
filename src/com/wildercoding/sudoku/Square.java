package com.wildercoding.sudoku;
import java.util.TreeSet;

/**
 * This is the basic holding unit for a Puzzle
 * 
 * @author Donovan J. Wilder
 * @since 2016-07-08
 * @see {@link wilderSudoku.Puzzle Puzzle}
 *
 */
public class Square implements Cloneable {
	private boolean isInitial;
	private boolean isLocked;
	private Number value;
	private TreeSet<Number> possibles;

	/**
	 * Returns rather true if the value was initial set returns false if it was
	 * not
	 * 
	 * @return
	 */
	boolean isInitial() {
		return isInitial;
	}

	/**
	 * Returns the locked value
	 * 
	 * @return
	 */
	boolean isLocked() {
		return isLocked;
	}

	/**
	 * Constructs a newly allocated Square that sets the boolean fields
	 * isInitial and isLocked to false and sets the field Number number to
	 * Number.EMPTY.
	 */
	public Square() {
		isInitial = false;
		isLocked = false;
		possibles = new TreeSet<Number>();
		value = Number.EMPTY;
	}

	/**
	 * Constructs a newly allocated Square that sets the boolean isLocked field
	 * to true and the boolean isInital to true and the field number to the
	 * parameter number. This is the only way to set the isInital Field.
	 * 
	 * @param number
	 */
	public Square(Number number) {
		this.isLocked = true;
		this.isInitial = true;
		this.value = number;
		possibles = null;
	}

	/**
	 * Set the number and lock if the number is already locked an
	 * InvalidSetException is thrown. If islocked is true the possibles array
	 * will be changed to null.
	 * 
	 * @param number
	 * @param isLocked
	 * @throws InvalidSetException
	 */
	void set(Number number, boolean isLocked) throws InvalidSetException {
		if (this.isInitial == true)
			throw new InvalidSetException("isInitial=true");
		if (this.isLocked == true)
			throw new InvalidSetException("isLocked=true");
		this.value = number;
		this.isLocked = isLocked;
		if (isLocked) {
			possibles = null;
		}
	}

	/**
	 * Returns the number field.
	 * 
	 * @return
	 */
	public Number getValue() {
		return this.value;
	}

	/**
	 * Returns the the locked, initial, and value of the square
	 */
	@Override
	public String toString() {
		StringBuilder info = new StringBuilder("Value: " + value);
		info.append(" Locked: " + isLocked);
		info.append(" Initial: " + isInitial);

		return info.toString();
	}

	/**
	 * Returns a copy of the possibles list.
	 * 
	 * @return
	 */
	public TreeSet<Number> getPossibles() {
		if (this.possibles == null) {
			return null;
		}

		return possibles;
	}

	/**
	 * This is used by the clone method in the Square class
	 * 
	 * @param locked
	 * @param initial
	 * @param value
	 * @param possibles
	 */
	private void copy(boolean locked, boolean initial, Number value) {
		this.isInitial = initial;
		this.value = value;
		this.isLocked = locked;
	}

	/**
	 * Makes a deep copy of the Square object
	 */
	
	@Override
	public Object clone() {
		boolean copyIsLocked = this.isLocked;
		boolean copyIsInitial = this.isInitial;
		Number copyValue = this.value;
		

		Square copySquare = new Square();
		copySquare.copy(copyIsLocked, copyIsInitial, copyValue );
		return copySquare;

	}

}
