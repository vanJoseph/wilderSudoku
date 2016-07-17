package solvers;


import exceptions.InitializationException;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidSetException;
import exceptions.InvalidValueException;
import wilderSudoku.Solver;
import wilderSudoku.Number;
import wilderSudoku.Coord;

//Check the square to make sure that it is the only square in all the units that contains the value
//then put the value in the puzzle locking it

public class DeductionSolver extends Solver {
	
	//This method might be fallacoiouse
	// Finds the value of the square by checking to see that a square is the only only square in the area, horrizontal, an vertical that can contain the vau
	
	private int findDeduction()  {
		int numFound = 0;

		for (int indexFind = 1; indexFind <= 9; indexFind++) {
			Number value=null;
			try {
				value = Number.valueOf(indexFind);
			} catch (InvalidValueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int y = 0; y < 9; y++) {
				for (int x = 0; x < 9; x++) {
					if (puzzle.getSquarePossibles(x, y) == null || puzzle.getSquareLocked(x, y)
							|| puzzle.getSquareInital(x, y)) {
						continue;
					}
					wilderSudoku.Area pointArea=null;
					int foundVertical = 0;
					int foundHorizontal = 0;
					try {
						foundHorizontal = checkHorizontalPossibles(y, value);
						foundVertical = checkVerticalPossibles(x, value);
						pointArea = wilderSudoku.Area.valueOf(new Coord(x, y));
					} catch (InvalidCoordinateException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					int foundArea = checkAreaPossibles(pointArea, value);
					if ((foundHorizontal + foundVertical + foundArea) == 3) {
						if (puzzle.getSquareLocked(x, y)) {
							continue;
						} else {
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
							numFound++;
						}
					}

				}
			}
		}
		return numFound;
	}

	@Override
	public int solve() {

		findPossibles();
		int found = setPossible();
		

		return found;

	}

}
