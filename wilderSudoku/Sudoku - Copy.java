package wilderSudoku;

import java.util.ArrayList;

import exceptions.DuplicateException;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidSetException;
import exceptions.LoadException;

/**
 * 
 * @author Donovan J. Wilder
 * @since 2016-07-08
 */
public class Sudoku {
	Puzzle puzzle;


	public void set(int x, int y, Number number, boolean isLocked)
			throws DuplicateException, InvalidCoordinateException, InvalidSetException {
		// checks if coordinates are valid
		if (x > 8 || x < 0 || y > 8 || y < 0) {
			throw new InvalidCoordinateException("Invalid coordinates: (" + x + "," + y + ")");
		}

		try {

			square[x][y].set(number, isLocked);
		} catch (InvalidSetException cause) {
			InvalidSetException e = new InvalidSetException();
			e.initCause(cause);
			throw e;
		}
	}
	Number get(int x, int y) throws InvalidCoordinateException {
		if (x > 8 || x < 0 || y > 8 || y < 0) {
			throw new InvalidCoordinateException("Invalid coordinates: (" + x + "," + y + ")");
		}
		return square[x][y].get();
	}
	
	//Loader constructorr
	public Sudoku(Loader loader) throws LoadException {
		square=loader.loadPuzzle();
	}
	
	//Output adapters
	public void setDisplayer(Displayable displayer) {
		displayer.connect(this.square);
	}
	public void setSolver(Solver solver){
		solver.connect(this.square);
	}

	// Check for complete units
	public boolean checkpuzzleComplete() {
		for (int index = 0; index < 9; index++) {
			if (checkHorizontalComplete(index) == false)
				return false;
			if (checkVerticalComplete(index) == false)
				return false;
			if (checkAreaComplete(Area.valueOf(index + 1)) == false)
				return false;
		}
		return true;
	}
	public boolean checkVerticalComplete(int vertNumber){
		for(int index=1;index<=9;index++){
			if(this.checkVertical(vertNumber,Number.valueOf(index))==false)
				return false;
				
		}
		return true;
	}
	public boolean checkHorizontalComplete(int HoriNumber){
		for(int index=1;index<=9;index++){
			if(this.checkHorizontal(HoriNumber,Number.valueOf(index))==false)
				return false;
				
		}
		return true;
	}
	public boolean checkAreaComplete(Area areaValue){
		for(int index=1;index<=9;index++){
			if(this.checkArea(areaValue,Number.valueOf(index))==false)
				return false;
				
		}
		return true;
	}
	// Tools for checking for complete units
	public static boolean checkpuzzleComplete(Square[][]puzzle) {
		for (int index = 0; index < 9; index++) {
			if (Puzzle.checkHorizontalComplete(index,puzzle) == false)
				return false;
			if (Puzzle.checkVerticalComplete(index,puzzle) == false)
				return false;
			if (Puzzle.checkAreaComplete(Area.valueOf(index + 1),puzzle) == false)
				return false;
		}
		return true;
	}
	public static boolean checkVerticalComplete(int vertNumber, Square[][]puzzle){
		for(int index=1;index<=9;index++){
			if(Puzzle.checkVertical(vertNumber,Number.valueOf(index),puzzle)==false)
				return false;
				
		}
		return true;
	}
	public static boolean checkHorizontalComplete(int HoriNumber, Square[][]puzzle){
		for(int index=1;index<=9;index++){
			if(Puzzle.checkHorizontal(HoriNumber,Number.valueOf(index),puzzle)==false)
				return false;
				
		}
		return true;
	}
	public static boolean checkAreaComplete(Area areaValue, Square[][]puzzle){
		for(int index=1;index<=9;index++){
			if(Puzzle.checkArea(areaValue,Number.valueOf(index),puzzle)==false)
				return false;
				
		}
		return true;
	}
	//Checks for numbers inside various units
	public boolean checkVertical(int vertNumber,Number number) throws InvalidCoordinateException{
		if(vertNumber<0||vertNumber>8)
			throw new InvalidCoordinateException("Invalid vertNumber: " + vertNumber);
		
		for(int y=0; y<0;y++){
			if(square[vertNumber][y].get().equals(number))
				return true;
		}
		return false;
		
	}
	public boolean checkHorizontal(int horiNumber,Number number) throws InvalidCoordinateException{
		if(horiNumber<0||horiNumber>8)
			throw new InvalidCoordinateException("Invalid horiNumber: " + horiNumber);
		
		for(int x=0; x<0;x++){
			if(square[x][horiNumber].get().equals(number))
				return true;
		}
		return false;
		
	}
	public boolean checkArea(Area areaNumber,Number number) {
		for(Coord index: areaNumber.coord){
			if(square[index.x][index.y].get().equals(number)){
				return true;
			}
		}
		return false;
	}
	//Tools for checking numbers inside various units
	//Returns true if the number is there
	public static boolean checkVertical(int vertNumber,Number number, Square[][]puzzle) throws InvalidCoordinateException{
		if(vertNumber<0||vertNumber>8)
			throw new InvalidCoordinateException("Invalid vertNumber: " + vertNumber);
		
		for(int y=0; y<0;y++){
			if(puzzle[vertNumber][y].get().equals(number))
				return true;
		}
		return false;
		
	}
	public static boolean checkHorizontal(int horiNumber,Number number, Square[][]puzzle) throws InvalidCoordinateException{
		if(horiNumber<0||horiNumber>8)
			throw new InvalidCoordinateException("Invalid horiNumber: " + horiNumber);
		
		for(int x=0; x<0;x++){
			if(puzzle[x][horiNumber].get().equals(number))
				return true;
		}
		return false;
		
	}
	public static boolean checkArea(Area areaNumber,Number number, Square[][]puzzle) {
		
		
		for(Coord index: areaNumber.coord){
			
			if(puzzle[index.x][index.y].get().equals(number)){
				return true;
			}
		}
		return false;
	}
	
	//Get the Numbers inside various units
	public ArrayList<Number> getHorizontal(int horiNumber) {
		ArrayList<Number> numbers= new ArrayList<Number>();
		for(int index=0; index<=8;index++){
			numbers.add(square[index][horiNumber].get());
		}
		return numbers;
		
	}
	public ArrayList<Number> getVertical(int vertNumber) {
		ArrayList<Number> numbers= new ArrayList<Number>();
		for(int index=0; index<=8;index++){
			numbers.add(square[vertNumber][index].get());
		}
		return numbers;
		
	}
	public ArrayList<Number> getArea(Area areaNumber){
		ArrayList<Number> numbers= new ArrayList<Number>();
		for(Coord index: areaNumber.coord){
			numbers.add(square[index.x][index.y].get());
		}
		return numbers;
	}
	//Tools to get the numbers inside variou units
	public static ArrayList<Number> getHorizontal(int horiNumber,Square[][]puzzle) {
		ArrayList<Number> numbers= new ArrayList<Number>();
		for(int index=0; index<=8;index++){
			numbers.add(puzzle[index][horiNumber].get());
		}
		return numbers;
		
	}
	public static ArrayList<Number> getVertical(int vertNumber,Square[][]puzzle) {
		ArrayList<Number> numbers= new ArrayList<Number>();
		for(int index=0; index<=8;index++){
			numbers.add(puzzle[vertNumber][index].get());
		}
		return numbers;
		
	}
	public static ArrayList<Number> getArea(Area areaNumber,Square[][]puzzle){
		ArrayList<Number> numbers= new ArrayList<Number>();
		for(Coord index: areaNumber.coord){
			numbers.add(puzzle[index.x][index.y].get());
		}
		return numbers;
	}
	/**
	 * Return a Array number of missing based on the numbers that are missing from the number parameter
	 * @param numbers
	 * @return An ArrayList<Number> of the missing numbers
	 */
	public static ArrayList<Number> getMissing(ArrayList<Number> numbers){
		ArrayList<Number> missingNumbers= new ArrayList<Number>();
		missingNumbers.add(Number.ONE);
		missingNumbers.add(Number.TWO);
		missingNumbers.add(Number.THREE);
		missingNumbers.add(Number.FOUR);
		missingNumbers.add(Number.FIVE);
		missingNumbers.add(Number.SIX);
		missingNumbers.add(Number.SEVEN);
		missingNumbers.add(Number.EIGHT);
		missingNumbers.add(Number.NINE);
		
		for(Number num: numbers	){
			if(missingNumbers.contains(num)){
				missingNumbers.remove(num);
			}
		}
		return missingNumbers;
	}
}