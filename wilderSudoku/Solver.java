package wilderSudoku;

import java.util.ArrayList;
import exceptions.InitializationException;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidSetException;
import exceptions.InvalidValueException;

/**
 * The solver class is used by the Sudoku system in order to apply solving
 * techniques to the puzzle. This class provides basic methods that are used to
 * get generate the possible values for each square.
 * 
 * @author Donovan J. Wilder
 * @since 2016-07-17
 * @see {@link wilderSudoku.Sudoku Sudoku}
 */
public abstract class Solver {
	protected Puzzle puzzle;
	protected ArrayList<Coord> solved;
	static final boolean DEBUG = false;
	{
		solved = new ArrayList<Coord>();
	}

	/**
	 * Used to load a puzzle from the Sudoku system
	 * 
	 * @param puzzle
	 *            the {@linkplain wilderSudoku.Puzzle Puzzle} to solve
	 * @see {@link wilderSudoku.Sudoku} {@link wilderSudoku.Puzzle Puzzle}
	 */
	public void connect(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	/**
	 * Find the possibles of all squares and fill their collection with the
	 * value of the possiblility
	 */
	protected void findPossibles() {

		// The Number incrementor loop
		for (int indexFind = 1; indexFind <= 9; indexFind++) {

			// The line by line search loops
			// adds numbers to the possible the treeset
			for (int y = 0; y < 9; y++) {

				for (int x = 0; x < 9; x++) {

					Coord targetCoord = new Coord(x, y);
					if (puzzle.getSquareLocked(x, y)) {
						continue;
					} else {
						// checks the area horizontal and verticall for the
						// indexFind Number value
						// indexFind value was declared in the Number incrmentor
						// for loop

						// these 3 booleans should not be all initalized to
						// false
						boolean areaCheck = true;
						boolean horizontalCheck = false;
						boolean verticalCheck = false;
						try {
							areaCheck = puzzle.checkArea(Area.valueOf(targetCoord), Number.valueOf(indexFind));
							horizontalCheck = puzzle.checkHorizontal(y, Number.valueOf(indexFind));
							verticalCheck = puzzle.checkVertical(x, Number.valueOf(indexFind));
						} catch (InvalidCoordinateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidValueException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// test code that prints the boolean for the checks
						if (DEBUG) {
							System.out.println(targetCoord + ": " + indexFind);
							System.out.println(" Area: " + areaCheck + "\n" + "horizontal: " + horizontalCheck
									+ "\n vertical: " + verticalCheck + "\n\n");
						}
						// if the number isn't there in all the units than it is
						// added to the possibilities
						// of that square
						// might be a problem with the condition
						if ((!areaCheck && !horizontalCheck && !verticalCheck)) {
							try {
								puzzle.getSquarePossibles(x, y).add(Number.valueOf(indexFind));
							} catch (InvalidValueException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}

				}

			}

		}
	}

	/**
	 * Set a value if the square as only one possible
	 * @return the numbers of squares set
	 */
	protected int setPossible() {
		int numbersFound = 0;
		// Goes through each value and if it has only one possible it inserts it
		// and lock the square
		// then it dereference the treseet
		for (int y = 0; y < 9; y++) {

			for (int x = 0; x < 9; x++) {
				if (puzzle.getSquarePossibles(x, y) != null) {

					// sets the values that has only one possible
					if (puzzle.getSquarePossibles(x, y).size() == 1) {
						Number value = puzzle.getSquarePossibles(x, y).first();
						try {
							puzzle.setSquare(x, y, value);
						} catch (InvalidCoordinateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InitializationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidSetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						solved.add(new Coord(x, y));

						numbersFound++;
					}
				}
			}
		}
		return numbersFound;
	}
	/**
	 * Checks to say how many squares in a horizontal unit has the possibility of having a certain value and returns the amount.
	 * @param horizontal the index value of a the vertical to search
	 * @param num a {@linkplain wilderSudoku.Number Number} to search for
	 * 
	 * @return the amount of square that has the possibility of having the number
	 * @throws InvalidCoordinateException if the number is not of value 0-8
	 */
	protected int checkHorizontalPossibles(int horizontal, Number num) throws InvalidCoordinateException {
		int numFound=0;
		if(puzzle.checkHorizontal(horizontal, num)==false){
			return numFound;
		}
		for(int x=0;x<9;x++){
			if(puzzle.getSquarePossibles(x	, horizontal)==null){
				continue;
			}
			if(puzzle.getSquarePossibles(x,horizontal).contains(num)){
				numFound++;
			}
		}
		return numFound;

	}

	/**
	 * Checks to see how many squares in a vertical unit has the possibility of
	 * having a certain value and returns the amount.
	 * 
	 * @param vertical
	 *            the index value of the vertical to search
	 * @param num
	 *            a {@linkplain wilderSudoku.Number Number} to search for
	 * @return the amount of squares that has the possibility of having the
	 *         number
	 * @throws InvalidCoordinateException
	 *             if the number is not of value 0-8
	 */
	protected int checkVerticalPossibles(int vertical, Number num) throws InvalidCoordinateException {
		int numFound = 0;
		if (puzzle.checkVertical(vertical, num) == false) {
			return numFound;
		}
		for (int y = 0; y < 9; y++) {
			if (puzzle.getSquarePossibles(vertical, y) == null) {
				continue;
			}
			if (puzzle.getSquarePossibles(vertical, y).contains(num)) {
				numFound++;
			}
		}
		return numFound;
	}

	/**
	 * Checks to see how many squares in an Area has the possibility of having a certain Value and returns the
	 * amount.
	 * 
	 * @param area
	 *            the {@linkplain wilderSudoku.Area Area} to search
	 * @param num
	 *            a {@linkplain wilderSudoku.Number Number} to search for
	 * @return the amount of squares that has the possibility of having the
	 *         number
	 */
	protected int checkAreaPossibles(Area area, Number num) {
		int numFound = 0;
		if (puzzle.checkArea(area, num)) {
			return numFound;
		}
		Coord[] location = area.coord;
		for (Coord target : location) {
			if (puzzle.getSquarePossibles(target.x, target.y) == null) {
				continue;
			}
			boolean isFound = puzzle.getSquarePossibles(target.x, target.y).contains(num);
			if (isFound) {
				numFound++;

			}
		}
		return numFound;
	}
	/**
	 * Returns the puzzle that the solver has
	 * @return the puzzle
	 * @see {@link wilderSudoku.Puzzle Puzzle}
	 */
	public Puzzle getPuzzle(){
		return this.puzzle;
	}
	
	/**
	 * The main method that the solver use to sole the puzzle
	 * it returns the number of squares that have been solved
	 * @return
	 */
	public abstract int solve();
}
