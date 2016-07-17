package displayers;

import java.util.TreeSet;

import wilderSudoku.Coord;
import wilderSudoku.Displayer;
import wilderSudoku.Number;
import wilderSudoku.Puzzle;
import wilderSudoku.Square;

public class ConsoleDisplayer extends Displayer {

	 
	public void connect(Puzzle puzzle) {
		this.puzzle=puzzle;

	}

	public void printPossibles() {
		for(int y=0; y<9; y++){
			for(int x=0;x<9;x++){
				Coord coord=new Coord(x,y);
				StringBuilder dataUnit;
				
					 dataUnit=new StringBuilder(coord+": \tval:"+ puzzle.getSquareValue(x, y)+"\t"+puzzle.getSquarePossibles(x, y)+"\t");
					 if(puzzle.getSquarePossibles( x, y)instanceof TreeSet)
						 dataUnit.append(" size: "+puzzle.getSquarePossibles(x, y).size());
					
				
				
				System.out.print(dataUnit.toString()+"\t");
			}
			System.out.print("\n");
		}
	}
	public void printPuzzle() {
		System.out.println(puzzle);
	}

	@Override
	public void display() {
		
		printPuzzle();
		
	}

}
