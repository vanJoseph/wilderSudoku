package com.wildercoding.sudoku;

import java.util.TreeSet;

/**
 * This is the basic container for a 9x9 sudoku puzzle
 * it contains a  array, methods
 * for changing the values of the squares, overridden toString and clone methods.
 *
 * @author Donovan J. Wilder
 * @since 2016-07-14
 */
public class Puzzle implements Cloneable {

    private Square[][] squares;

    {
        squares = new Square[9][9];
    }

    /**
     * Returns the @value of the square
     *
     * @param x index value of a 9x9 array
     * @param y index value of a 9x9 array
     * @return the  constant
     */
    public Number getSquareValue(int x, int y) {
        return squares[y][x].getValue();
    }

    /**
     * Returns a TreeSet of possible values for a square.
     *
     * @param x index value of a 9x9 array
     * @param y index value of a 9x9 array
     * @return a TreeSet< Number> if exists, null if do not exists
     */
    public TreeSet<Number> getSquarePossibles(int x, int y) {
        if (squares[y][x].getPossibles() instanceof TreeSet) {
            return squares[y][x].getPossibles();
        }
        return null;
    }

    /**
     * Returns true if the square is locked
     *
     * @param x index value of a 9x9 array
     * @param y index value of a 9x9 array
     * @return
     */
    public boolean getSquareLocked(int x, int y) {
        return squares[y][x].isLocked();
    }

    /**
     * Returns true if the Square was initialized during load.
     *
     * @param x index value of a 9x9 array
     * @param y index value of a 9x9 array
     * @return
     */
    public boolean getSquareInital(int x, int y) {
        return squares[y][x].isInitial();
    }

    /**
     * Initializes the value of a null Square, making the fields isLocked and isInitial true.
     *
     * @param x     index value of a 9x9 array
     * @param y     index value of a 9x9 array
     * @param value constant of umber
     */
    public void setInitialSquare(int x, int y, Number value)  {
        //Checks that the coordinates are in the right range
        if (x > 8 || x < 0 || y > 8 || y < 0) {
            throw new InvalidCoordinateException("Invalid coordinates: (" + x + "," + y + ")");
        }
        //Checks that the square is null if not that means it already been set
        if (squares[y][x] instanceof Square) {
            throw new InitializationException(new Coord(x, y) + " has already been set.");
        }
        squares[y][x] = new Square(value);
    }

    /**
     * Initialize the value of a {@link wilderSudoku.Square Square}, making the fields isLocked and isInital false and setting the Square value
     * to the constant Number.EMPTY.
     *
     * @param x     index value of a 9x9 array
     * @param y     index value of a 9x9 array
     * @param value constant of {@link wilderSudoku.Number Number}
     * @throws InitializationException    if the square is not null
     * @throws InvalidCoordinateException if the coordinates aren't index values of a 9x9 array
     */
    public void setEmptySquare(int x, int y) throws InvalidCoordinateException, InitializationException {
        // Checks that the coordinates are in the right range
        if (x > 8 || x < 0 || y > 8 || y < 0) {
            throw new InvalidCoordinateException("Invalid coordinates: (" + x + "," + y + ")");
        }
        // Checks that the square is null if not that means it already been set
        if (squares[y][x] instanceof Square) {
            throw new InitializationException(new Coord(x, y) + " has already been set.");
        }
        squares[y][x] = new Square();

    }

    /**
     * Sets the value of a square that isn't locked an all ready Instantiated.
     *
     * @param x     index value of a 9x9 array
     * @param y     index value of a 9x9 array
     * @param value constant of {@link wilderSudoku.Number Number}
     * @throws InitializationException    if the square is not null
     * @throws InvalidCoordinateException if the coordinates aren't index values of a 9x9 array
     * @throws InvalidSetException
     */
    public void setSquare(int x, int y, Number value) throws InvalidCoordinateException, InitializationException, InvalidSetException {
        // Checks that the coordinates are in the right range
        if (x > 8 || x < 0 || y > 8 || y < 0) {
            throw new InvalidCoordinateException("Invalid coordinates: (" + x + "," + y + ")");
        }
        // Checks that the square is already instantiated
        if (!(squares[y][x] instanceof Square)) {
            throw new InitializationException(new Coord(x, y) + " has not been set.");
        }
        // Checks that the square is null if not that means it already been set
        if (squares[y][x].isLocked() == true) {
            throw new InitializationException(new Coord(x, y) + " is Locked and can't be changed");
        }
        squares[y][x].set(value, true);
    }

    /**
     * Check to see if the whole puzzle is filled
     *
     * @return true if the whole puzzle is filled, false if it is not
     */
    public boolean checkpuzzleComplete() {
        for (int index = 0; index < 9; index++) {
            try {
                if (checkHorizontalComplete(index) == false)
                    return false;
            } catch (InvalidCoordinateException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                if (checkVerticalComplete(index) == false)
                    return false;
            } catch (InvalidCoordinateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (checkAreaComplete(Area.valueOf(index + 1)) == false)
                return false;
        }
        return true;
    }

    /**
     * Check to see if vertical unit if filled with numbers
     *
     * @param vertNumber the index value of the vertical unit to check
     * @return true if the vertical unit is filled, false if it is not
     * @throws InvalidCoordinateException
     */
    public boolean checkVerticalComplete(int vertNumber) throws InvalidCoordinateException {
        if (vertNumber < 0 || vertNumber > 8)
            throw new InvalidCoordinateException("Invalid vertNumber: " + vertNumber);
        for (int index = 1; index <= 9; index++) {
            try {
                if (checkVertical(vertNumber, Number.valueOf(index)) == false)
                    return false;
            } catch (InvalidValueException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidCoordinateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return true;
    }

    /**
     * Checks to see if an horizontal unit is filled with numbers
     *
     * @param HoriNumber the index value of the horizontal unit to check
     * @return true if the unit if is filled, false if the unit is not
     * @throws InvalidCoordinateException if the
     */
    public boolean checkHorizontalComplete(int horiNumber) throws InvalidCoordinateException {
        if (horiNumber < 0 || horiNumber > 8)
            throw new InvalidCoordinateException("Invalid vertNumber: " + horiNumber);
        for (int index = 1; index <= 9; index++) {

            try {
                if (checkHorizontal(horiNumber, Number.valueOf(index)) == false)
                    return false;
            } catch (InvalidValueException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return true;
    }

    /**
     * Checks to see if an area is completely filled with numbers
     *
     * @param areaValue the {@linkplain wilderSudoku.Area Area} to search
     * @return true if the area is complete, false if it is not
     * @throws InvalidValueException
     */
    public boolean checkAreaComplete(Area areaValue) {
        for (int index = 1; index <= 9; index++) {
            try {
                if (checkArea(areaValue, Number.valueOf(index)) == false)
                    return false;
            } catch (InvalidValueException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return true;
    }

    /**
     * Checks an Vertical for a specific Number constant that is in a
     * vertical unit
     *
     * @param vertNumber an index value of which horizontal
     * @param number     a {@linkplain wilderSudoku.Number Number}
     * @return true if the number is there, false if the number is not there
     * @throws InvalidCoordinateException if vertNumber is not of value 0-8
     */
    public boolean checkVertical(int vertNumber, Number number) throws InvalidCoordinateException {
        if (vertNumber < 0 || vertNumber > 8)
            throw new InvalidCoordinateException("Invalid vertNumber: " + vertNumber);

        for (int y = 0; y < 9; y++) {
            if (squares[y][vertNumber].getValue().equals(number))
                return true;
        }
        return false;

    }

    /**
     * Checks an horizontal for a specific Number constant that is in a
     * horizontal unit
     *
     * @param horiNumber an index value of which horizontal
     * @param number     a {@linkplain wilderSudoku.Number Number}
     * @return true if the number is there, false if the number is not there
     * @throws InvalidCoordinateException if vertNumber is not of value 0-8
     */
    public boolean checkHorizontal(int horiNumber, Number number) throws InvalidCoordinateException {
        if (horiNumber < 0 || horiNumber > 8)
            throw new InvalidCoordinateException("Invalid horiNumber: " + horiNumber);

        for (int x = 0; x < 9; x++) {
            if (squares[horiNumber][x].getValue().equals(number))
                return true;
        }
        return false;

    }

    /**
     * Checks an area for a specific for a Number constant
     *
     * @param areaNumber an {@linkplain wilderSudoku.Area Area}
     * @param number     a {@linkplain wilderSudoku.Number Number}
     * @return true if the number is there, false if the number is not there
     * @see {@link wilderSudoku.Area Area} {@link wilderSudoku.Number Number}
     */
    public boolean checkArea(Area areaNumber, Number number) {

        for (Coord index : areaNumber.coord) {

            if (squares[index.y][index.x].getValue().equals(number)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string copy of the whole puzzle
     */
    @Override
    public String toString() {

        StringBuffer puzzleString = new StringBuffer();
        for (Square[] horizontal : squares) {
            for (Square target : horizontal) {

                puzzleString.append(target.getValue());
            }
            puzzleString.append('\n');
        }
        return puzzleString.toString();
    }

    /**
     * This is used for by the copy() method it copies the Square array
     *
     * @param squares
     */
    private void copySquares(Square[][] squares) {
        this.squares = squares;
    }

    /**
     * Makes a deep copy of the puzzle
     */
    public Object clone() {
        Puzzle copyPuzzle = new Puzzle();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                copyPuzzle.squares[y][x] = (Square) this.squares[y][x].clone();
            }
        }
        return copyPuzzle;

    }

    @Override
    public boolean equals(Object compare) {
        if (!(compare instanceof Puzzle)) {
            return false;
        }
        Puzzle otherPuzzle = (Puzzle) compare;

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (this.getSquareValue(x, y).equals(otherPuzzle.getSquareValue(x, y))) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

}
