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
	
	// private boolean insertRandomCell() {
	// randomizes 2 or 4 and inserts into random free cell
	// removes the cell from freeCells

	@Override
	public void moveUp() {
		System.out.println("move up");
		for(int t=0; t<sizeOfBoard; t++)
			consolidateUp(t);
		updateFreeCells();
	}

	@Override
	public void moveDown() {
		System.out.println("move down");
		for(int t=0; t<sizeOfBoard; t++)
			consolidateDown(t);
		updateFreeCells();
	}

	@Override
	public void moveRight() {
		System.out.println("move right");
		for(int t=0; t<sizeOfBoard; t++)
			consolidateRight(t);
		updateFreeCells();
	}

	@Override
	public void moveLeft() {
		System.out.println("move left");
		for(int t=0; t<sizeOfBoard; t++)
			consolidateLeft(t);
		updateFreeCells();
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
		
		initialFillCell();
		lastFreeCell = 0;
		
		
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
		
	}
	
	void consolidateUp(int col) {	
		int row = 0;
		while (row < sizeOfBoard-1){
			if ((board[row][col]!=0)&&(board[row][col]==board[row+1][col])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row+1][col] = 0;
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
	
	
	void consolidateDown(int col) {	
		int row = sizeOfBoard-1;
		while (row > 0){
			if ((board[row][col]!=0)&&(board[row][col]==board[row-1][col])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row-1][col] = 0;
			}
			else if (board[row][col]==0) {
				int temp = row-1;
				while ((temp >= 0)&&(board[temp][col]==0)){
					temp--;
				}
				if (temp != -1) {
					board[row][col] = board[temp][col];
					board[temp][col] = 0;
					row++;
				}
			}
			else if ((board[row][col]!=0)&&(board[row-1][col]==0)){
				int temp = row-1;
				while ((temp >= 0)&&(board[temp][col]==0)){
					temp--;
				}
				if (temp >= 0) {
					if (board[row][col] == board[temp][col]){
						board[row][col] = board[row][col]*2;
						currentScore+=board[row][col];
						board[temp][col] = 0;
					}
				}
			}
			
			row--;
		}
		System.out.println(currentScore);
	}
	
	void consolidateRight(int row) {	
		int col = sizeOfBoard-1;
		while (col > 0){
			if ((board[row][col]!=0)&&(board[row][col]==board[row][col-1])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row][col-1] = 0;
			}
			else if (board[row][col]==0) {
				int temp = col-1;
				while ((temp >= 0)&&(board[row][temp]==0)){
					temp--;
				}
				if (temp != -1) {
					board[row][col] = board[row][temp];
					board[row][temp] = 0;
					col++;
				}
			}
			else if ((board[row][col]!=0)&&(board[row][col-1]==0)){
				int temp = col-1;
				while ((temp >= 0)&&(board[row][temp]==0)){
					temp--;
				}
				if (temp >= 0) {
					if (board[row][col] == board[row][temp]){
						board[row][col] = board[row][col]*2;
						currentScore+=board[row][col];
						board[row][temp] = 0;
					}
				}
			}
			
			col--;
		}
		System.out.println(currentScore);
	}
	
	void consolidateLeft(int row) {	
		int col = 0;
		while (col < sizeOfBoard-1){
			if ((board[row][col]!=0)&&(board[row][col]==board[row][col+1])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row][col+1] = 0;
			}
			else if (board[row][col]==0) {
				int temp = col+1;
				while ((temp < sizeOfBoard)&&(board[row][temp]==0)){
					temp++;
				}
				if (temp != sizeOfBoard) {
					board[row][col] = board[row][temp];
					board[row][temp] = 0;
					col--;
				}
			}
			else if ((board[row][col]!=0)&&(board[row][col+1]==0)){
				int temp = col+1;
				while ((temp < sizeOfBoard)&&(board[row][temp]==0)){
					temp++;
				}
				if (temp < sizeOfBoard) {
					if (board[row][col] == board[row][temp]){
						board[row][col] = board[row][col]*2;
						currentScore+=board[row][col];
						board[row][temp] = 0;
					}
				}
			}
			
			col++;
		}
		System.out.println(currentScore);
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
	
	boolean fillRandomFreeCell() {
		Random random = new Random();
		
		if (lastFreeCell == (int)Math.pow(sizeOfBoard,2))
			return false;
		else {
			int p1 = random.nextInt(lastFreeCell);	
			int choice = Choices[random.nextInt(Choices.length)];
			board[freeCells[p1].x][freeCells[p1].y] = choice;
			updateFreeCells();
			currentScore += choice;
		}
		
		return true;
	}
	
	void printBoard() {
		for (int[] arr : board) {
            System.out.println(Arrays.toString(arr));
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
		
		updateFreeCells();
	}

	@Override
	public void addObs(Observer observer) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateFreeCells() {
		lastFreeCell = 0;
		for (int i = 0; i < sizeOfBoard ; i++)
			for (int j = 0; j < sizeOfBoard ; j++) {
				if (board[i][j] == 0) {
					freeCells[lastFreeCell].x = i;
					freeCells[lastFreeCell].y = j;
					lastFreeCell++;
				}					
			}
	}
	
	// getScore() { // returns the score of the current board
	
}
