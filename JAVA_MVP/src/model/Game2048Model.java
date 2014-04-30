package game2048;

import java.awt.Point;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

import model.Model;
//import model.Observer;

public class Game2048Model extends Observable implements Model {
	
	private int[][] board;
	@SuppressWarnings("unused")
	private int sizeOfBoard = 4;
	// data members used to keep track of free cells in order to randomize them
	private Point[] freeCells;
	private int lastFreeCell;
	final int[] Choices = { 2,4 };
	private int currentScore;
	
	// add a method to check if there are no neighbor matches - should be used when freeCells gets empty
	
	// private ArrayList int[][] freeCells  // holds the freeCells
	
	// private boolean insertRandomCell() {
	// randomizes 2 or 4 and inserts into random free cell
	// removes the cell from freeCells

	@Override
	public void moveUp() {
		System.out.println("move up");
		for(int t=0; t<sizeOfBoard; t++)
			//consolidateUpMatches(sizeOfBoard-1,t);
			consolidateUp(t);
		
		/*for (int j=0;j<sizeOfBoard;j++){
			int i = 0; int k = 1;
			
			while ((i<sizeOfBoard-1) && (k<sizeOfBoard)){
				if(board[i][j]==0){
					while(board[k][j]==0)
						k++;
					board[i][j]=board[k][j];
					board[k][j]=0;
				}
				i++; k++;	
			}
		}*/
	}

	@Override
	public void moveDown() {
		for(int i=0; i<sizeOfBoard; i++)
			consolidateDownMatches(0,i);
		
		for (int j=0;j<sizeOfBoard;j++){
			int i = 0;
			while (board[i][j] == 0){
				
			}
		}		
	}

	@Override
	public void moveRight() {
		
	}

	@Override
	public void moveLeft() {
		
	}

	@Override
	public int[][] getData() {
		return null;
	}

	public int[][] getBoard() {
		return board;
	}
	 
	public void setBoard(int[][] board) {
		this.board = board;
	}

	public void initBoard() {
		board = new int[sizeOfBoard][sizeOfBoard];
		currentScore = 0;
		freeCells = new Point[(int)Math.pow(sizeOfBoard,2)];
		initFreeCells();
		/*
		for(int i=0;i<sizeOfBoard;i++)
			for(int j=0;j<sizeOfBoard;j++) {
				
					board[i][j] = 0;
				
			}*/
		
		initialFillCell();
		//printBoard();
		lastFreeCell = 0;
		//fillRandomFreeCell(2);
		
		
		//lastFreeCell = 2;
		// make 2 random cells full and update in freeCells
		
		/*for(int i=0;i<sizeOfBoard;i++)
			for(int j=0;j<sizeOfBoard;j++) {
				if (board[i][j] == 0)
				{
					freeCells[lastFreeCell].x = i;
					freeCells[lastFreeCell].x = j;					
					lastFreeCell++;
				}
			}*/
		
		for(int i=0;i<sizeOfBoard;i++)
			for(int j=0;j<sizeOfBoard;j++) {
				if ((board[i][j] != 2) && (board[i][j] != 4))
				{
					board[i][j] = 0;
					freeCells[lastFreeCell].x = i;
					freeCells[lastFreeCell].x = j;					
					lastFreeCell++;
				}
			}
		//System.out.println(lastFreeCell);
		
	}
	
	private int randomize(int limit) {
		Random rn = new Random();
		return rn.nextInt(limit-1);
	}
	
	/*private int[] randomPoint() {
		
		int[] arr = new int[2];
		arr[0] = randomize();
		arr[1] = randomize();
		
		return arr;
	} */
	
	void consolidateUpMatches(int i,int j) {	
		while (i>0)
		{
			System.out.println(i+" "+j);
			if (board[i][j]!=0){
				if (board[i][j]==board[i-1][j])
				{
					board[i][j]=0;
					// insert cell to freeCell list
					board[i-1][j]=board[i-1][j]*2;
					// remove cell from freeCell list
					i=i-2;
				}
			}
			else i=i--;
		}
	}
	
	void consolidateUp(int col) {	
		int row = 0;
		while (row < sizeOfBoard-1){
			/*if ((board[row][col]!=0)&&(board[row][col]!=board[row+1][col]))
				row++;
			else*/ 
			if ((board[row][col]!=0)&&(board[row][col]==board[row+1][col])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row+1][col] = 0;
				//row++;
			}
			else if (board[row][col]==0) {
				int temp = row+1;
				while ((temp < sizeOfBoard)&&(board[temp][col]==0)){
					temp++;
				}
				if (temp != sizeOfBoard) {
					board[row][col] = board[temp][col];
					board[temp][col] = 0;
					row--;
				}
			}
			else if ((board[row][col]!=0)&&(board[row+1][col]==0)){
				int temp = row+1;
				while ((temp < sizeOfBoard)&&(board[temp][col]==0)){
					temp++;
				}
				if (temp < sizeOfBoard) {
					if (board[row][col] == board[temp][col]){
						board[row][col] = board[row][col]*2;
						currentScore+=board[row][col];
						board[temp][col] = 0;
					}
				}
			}
			
			row++;
		}
		System.out.println(currentScore);
	}
	
	
	void consolidateDownMatches(int i,int j) {	
		while (i>0)
		{
			if (board[i][j]!=0)
				if (board[i][j]==board[i-1][j])
				{
					board[i][j]=0;
					board[i-1][j]=board[i-1][j]*2;
					i=i-2;
				}
			else i=i--;
		}
	}
	
	void consolidateRightMatches(int i,int j) {	
		while (i>0)
		{
			if (board[i][j]!=0)
				if (board[i][j]==board[i-1][j])
				{
					board[i][j]=0;
					board[i-1][j]=board[i-1][j]*2;
					i=i-2;
				}
			else i=i--;
		}
	}
	
	void consolidateLeftMatches(int i,int j) {	
		while (i>0)
		{
			if (board[i][j]!=0)
				if (board[i][j]==board[i-1][j])
				{
					board[i][j]=0;
					board[i-1][j]=board[i-1][j]*2;
					i=i-2;
				}
			else i=i--;
		}
	}
	
	void initFreeCells() {
		for (int i=0 ; i < sizeOfBoard ; i++)
			for (int j=0 ; j < sizeOfBoard ; j++) {
				freeCells[i*sizeOfBoard+j] = new Point();
				//System.out.println(i*sizeOfBoard)+j].x);
				freeCells[(i*sizeOfBoard)+j].x = i;
				freeCells[(i*sizeOfBoard)+j].y = j;
			}
		//return true;
	}
	
	boolean fillRandomFreeCell(int amount) {
		Random random = new Random();
		
		if (lastFreeCell == (int)Math.pow(sizeOfBoard,2))
			return false;
		else {
			int p1 = randomize(lastFreeCell);
			
			
				int choice = Choices[random.nextInt(Choices.length)];
				board[freeCells[p1].x][freeCells[p1].y] = choice;
			
		}
		return true;
	}
	
	void printBoard() {
		/*for(int i=0;i<sizeOfBoard;i++)
			for(int j=0;j<sizeOfBoard;j++)
				System.out.println(board[i][j]);*/
		
		for (int[] arr : board) {
            System.out.println(Arrays.toString(arr)); // comment
        }
	}
	
	
	void initialFillCell() {
		Random random = new Random();
		int p1 = random.nextInt((int)Math.pow(sizeOfBoard,2));
		int choice = Choices[random.nextInt(Choices.length)];
		board[freeCells[p1].x][freeCells[p1].y] = choice;
		currentScore+=choice;
		int p2 = random.nextInt((int)Math.pow(sizeOfBoard,2));
		while (p2 == p1)
			p2 = random.nextInt((int)Math.pow(sizeOfBoard,2));
		
		choice = Choices[random.nextInt(Choices.length)];
		board[freeCells[p2].x][freeCells[p2].y] = choice;
		currentScore+=choice;
	}

	@Override
	public void addObs(Observer observer) {
		// TODO Auto-generated method stub
		
	}
	
	// getScore() { // returns the score of the current board
	
}
