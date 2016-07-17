package loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

import exceptions.InitializationException;
import exceptions.InvalidCharacterException;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidFormatException;
import exceptions.InvalidValueException;
import wilderSudoku.Coord;
import wilderSudoku.Loader;
import wilderSudoku.Number;
import wilderSudoku.Puzzle;
import wilderSudoku.Square;
/**
 * FileLoader is used to read puzzles from a file that is in the Sudoku format.
 * It extends the {@link wilderSudoku.Loader Loader } interface.
 * @author Donovan J. Wilder
 * @since 2016-07-16
 * @see Loader
 *
 */
public class FileLoader extends Loader {
	private FileReader file;
	
	/**
	 * Constructs the puzzle
	 * @param filename the path where the puzzle file is located
	 * @throws FileNotFoundException
	 */
	public FileLoader(String filename) throws FileNotFoundException {

		setFile(filename);
		puzzle = new Puzzle();

	}
	/**
	 * Reads the puzzle from the file 
	 * @throws InvalidCoordinateException
	 * @throws InitializationException
	 * @throws InvalidValueException
	 */
	void readPuzzle() throws InvalidCoordinateException, InitializationException, InvalidValueException {

		int y = 0;
		String dataline;
		BufferedReader fileBuffer = new BufferedReader(file);
		try {

			while (fileBuffer.ready()) {
				dataline = fileBuffer.readLine();
				for (int x = 0; x < 9; x++) {
					char value = dataline.charAt(x);
					if (value == '0') {
						puzzle.setEmptySquare(x, y);
					} else {
						Number numValue = Number.valueOf(value);
						puzzle.setInitialSquare(x, y, numValue);
					}
				}
				y++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Sets the file that the loader will load the sudoku data from
	 * @param filename is the path where the file is located
	 * @throws FileNotFoundException
	 */
	public void setFile(String filename) throws FileNotFoundException {
		puzzle = new Puzzle();

		file = new FileReader(filename);

	}

	@Override
	public Puzzle load() {
		try {
			readPuzzle();
		} catch (InvalidCoordinateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return puzzle;
	}

}
