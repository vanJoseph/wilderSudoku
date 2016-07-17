package displayers;

import java.io.FileWriter;
import java.io.IOException;

import wilderSudoku.Displayer;
import wilderSudoku.Puzzle;

public class FileCreator extends Displayer {
	String filename;
	public FileCreator(String filename){
		this.filename=filename;
	}
	public void writeFile() {
		FileWriter file;
		try {
			file = new FileWriter(filename);
			for(int y=0;y<9;y++){
				for(int x=0;x<9;x++){
					file.write(puzzle.toString());
				}
			}
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void display() {
		writeFile();
		
	}

}
