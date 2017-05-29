package com.wildercoding.sudoku;

import com.wildercoding.sudoku.exceptions.InvalidCoordinateException;

/**
 * The Standard constant for referring to areas in sudoku. An area in the puzzle is a 
 * section of the 9x9 puzzle when it is equally divided into 3x3 pieces. the first section when looking at a puzzle from the top
 * and scanning left to right starts at 1 and ends at 9.
 * @author Donovan J. Wilder
 * @since 2016-07-17
 *
 */
public enum Area {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);
	private int value;
	Coord[] coord;
	{
		coord = new Coord[9];
	}

	/**
	 * Loads the coords for that area into the coords field
	 * 
	 * @param value
	 *            the area number.
	 */
	private Area(int value) {
		this.value = value;
		coord = new Coord[9];
		switch (value) {
		case 1:
			coord[0] = new Coord(0, 0);
			coord[1] = new Coord(1, 0);
			coord[2] = new Coord(2, 0);

			coord[3] = new Coord(0, 1);
			coord[4] = new Coord(1, 1);
			coord[5] = new Coord(2, 1);

			coord[6] = new Coord(0, 2);
			coord[7] = new Coord(1, 2);
			coord[8] = new Coord(2, 2);
			break;
		case 2:
			coord[0] = new Coord(3, 0);
			coord[1] = new Coord(4, 0);
			coord[2] = new Coord(5, 0);

			coord[3] = new Coord(3, 1);
			coord[4] = new Coord(4, 1);
			coord[5] = new Coord(5, 1);

			coord[6] = new Coord(3, 2);
			coord[7] = new Coord(4, 2);
			coord[8] = new Coord(5, 2);
			break;
		case 3:
			coord[0] = new Coord(6, 0);
			coord[1] = new Coord(7, 0);
			coord[2] = new Coord(8, 0);

			coord[3] = new Coord(6, 1);
			coord[4] = new Coord(7, 1);
			coord[5] = new Coord(8, 1);

			coord[6] = new Coord(6, 2);
			coord[7] = new Coord(7, 2);
			coord[8] = new Coord(8, 2);
			break;
		case 4:
			coord[0] = new Coord(0, 3);
			coord[1] = new Coord(1, 3);
			coord[2] = new Coord(2, 3);

			coord[3] = new Coord(0, 4);
			coord[4] = new Coord(1, 4);
			coord[5] = new Coord(2, 4);

			coord[6] = new Coord(0, 5);
			coord[7] = new Coord(1, 5);
			coord[8] = new Coord(2, 5);
			break;
		case 5:

			coord[0] = new Coord(3, 3);
			coord[1] = new Coord(4, 3);
			coord[2] = new Coord(5, 3);

			coord[3] = new Coord(3, 4);
			coord[4] = new Coord(4, 4);
			coord[5] = new Coord(5, 4);

			coord[6] = new Coord(3, 5);
			coord[7] = new Coord(4, 5);
			coord[8] = new Coord(5, 5);
			break;
		case 6:

			coord[0] = new Coord(6, 3);
			coord[1] = new Coord(7, 3);
			coord[2] = new Coord(8, 3);

			coord[3] = new Coord(6, 4);
			coord[4] = new Coord(7, 4);
			coord[5] = new Coord(8, 4);

			coord[6] = new Coord(6, 5);
			coord[7] = new Coord(7, 5);
			coord[8] = new Coord(8, 5);
			break;
		case 7:
			coord[0] = new Coord(0, 6);
			coord[1] = new Coord(1, 6);
			coord[2] = new Coord(2, 6);

			coord[3] = new Coord(0, 7);
			coord[4] = new Coord(1, 7);
			coord[5] = new Coord(2, 7);

			coord[6] = new Coord(0, 8);
			coord[7] = new Coord(1, 8);
			coord[8] = new Coord(2, 8);
			break;
		case 8:
			coord[0] = new Coord(3, 6);
			coord[1] = new Coord(4, 6);
			coord[2] = new Coord(5, 6);

			coord[3] = new Coord(3, 7);
			coord[4] = new Coord(4, 7);
			coord[5] = new Coord(5, 7);

			coord[6] = new Coord(3, 8);
			coord[7] = new Coord(4, 8);
			coord[8] = new Coord(5, 8);
			break;
		case 9:
			coord[0] = new Coord(6, 6);
			coord[1] = new Coord(7, 6);
			coord[2] = new Coord(8, 6);

			coord[3] = new Coord(6, 7);
			coord[4] = new Coord(7, 7);
			coord[5] = new Coord(8, 7);

			coord[6] = new Coord(6, 8);
			coord[7] = new Coord(7, 8);
			coord[8] = new Coord(8, 8);
		}
	}

	/**
	 * Returns the value of an area based on its number. The starting from the
	 * upper left 3x3 area its area number is one 2 is the second 3x3 area
	 * moving to the right and 3 is the 3rd. the fourth is the 3x3 area under
	 * the first and continue in that fashion
	 * 
	 * @param areaNumber
	 *            a number 1-9
	 * @returns the area
	 */
	static Area valueOf(int areaNumber) {

		switch (areaNumber) {
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
	 * Takes a  coordinate and returns the area in the
	 * puzzle where the coord is located
	 * 
	 * @param coord
	 *            holds x and y values
	 * @return the Area where the Coord is located
	 * @throws InvalidCoordinateException
	 *             if coordinate is not an index value of an 9x9 array
	 * @see Coord
	 */
	public static Area valueOf(Coord coord) throws InvalidCoordinateException {
		Coord coordVar = coord;
		// checks if coordinates are valid
		if (coordVar.x > 8 || coordVar.x < 0 || coordVar.y > 8 || coordVar.y < 0) {
			throw new InvalidCoordinateException("Invalid coordinates: (" + coordVar.x + "," + coordVar.y + ")");
		}

		// goes through each area to find where the coordinates came from
		for (int indexArea = 1; indexArea <= 9; indexArea++) {
			// if a the test equals the coord from the parameter than the area
			// frome where
			// it came will be returned
			for (Coord test : Area.valueOf(indexArea).coord) {
				if (coord.equals(test)) {
					return Area.valueOf(indexArea);
				}

			}

		}

		throw new InvalidCoordinateException("Can't find area: " + coordVar);
	}

	/**
	 * Return a string of the coordinates for the area the area
	 * 
	 * @see {@link wilderSudoku.Coord Coord}
	 */
	@Override
	public String toString() {
		StringBuilder coordinates = new StringBuilder("Coordinates of Area: " + value);
		int intIndex = 0;
		for (Coord index : coord) {
			intIndex++;
			coordinates.append("\n" + intIndex + ". " + index.toString());

		}
		return coordinates.toString();

	}

}
