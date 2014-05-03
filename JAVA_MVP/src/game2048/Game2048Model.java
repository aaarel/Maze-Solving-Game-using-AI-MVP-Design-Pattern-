package game2048;

import java.awt.Point;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import model.Model;

public class Game2048Model extends Observable implements Model {
	
	private int[][] board;
	@SuppressWarnings("unused")
	private int sizeOfBoard = 4;
	private Point[] freeCells; 		 // array data member used to keep track of free cells in order to randomize them
	// reminder to self - switch the array of freeCells into arrayList
	private int lastFreeCell;		 // data member used in order to keep track of last zeroed cell (and number of free cells) 
	final int[] Choices = { 2,4 };   // array used to randomize the numbers (in this case 2 or 4)
	private int currentScore;	     // keeps track of the score (updated by the move methods)
	
	private boolean isGameOver = false;
	private boolean boardChanged = false;
	
	private Stack<State> states;
	
	// add a method to check if there are no neighbor matches - should be used when freeCells gets empty
	

	@Override
	public void moveUp() {
		
		boardChanged = false;
		System.out.println("move up");
		for(int t=0; t<sizeOfBoard; t++)
			consolidateUp(t);
		updateFreeCells();
		if (lastFreeCell == 0)
			checkIfGameOver();
		else if(fillRandomFreeCell())
			boardChanged = true;
		if (boardChanged) {
			notifyObservers(); /////
			pushState();
		}
	}

	@Override
	public void moveDown() {
		boardChanged = false;
		System.out.println("move down");
		for(int t=0; t<sizeOfBoard; t++)
			consolidateDown(t);
		updateFreeCells();
		if (lastFreeCell == 0)
			checkIfGameOver();
		else if(fillRandomFreeCell())
			boardChanged = true;
		if (boardChanged) {
			notifyObservers(); /////
			pushState();
		}
	}

	@Override
	public void moveRight() {
		boardChanged = false;
		System.out.println("move right");
		for(int t=0; t<sizeOfBoard; t++)
			consolidateRight(t);
		updateFreeCells();
		if (lastFreeCell == 0)
			checkIfGameOver();
		else if(fillRandomFreeCell())
			boardChanged = true;
		if (boardChanged) {
			notifyObservers(); /////
			pushState();
		}
	}

	@Override
	public void moveLeft() {
		boardChanged = false;
		System.out.println("move left");
		for(int t=0; t<sizeOfBoard; t++)
			consolidateLeft(t);
		updateFreeCells();
		if (lastFreeCell == 0)
			checkIfGameOver();
		else if(fillRandomFreeCell())
			boardChanged = true;
		if (boardChanged) {
			notifyObservers(); /////
			pushState();
		}
	}

	public void initBoard() {
		board = new int[sizeOfBoard][sizeOfBoard];
		states = new Stack<State>();
		currentScore = 0;
		freeCells = new Point[(int)Math.pow(sizeOfBoard,2)];
		initFreeCells();
		
		lastFreeCell = (int)Math.pow(sizeOfBoard,2)-1;
		fillRandomFreeCell();
		fillRandomFreeCell();
		
		boardChanged = true;
		notifyObservers(); /////
		pushState();
		
	}
	
	private void consolidateUp(int col) {	
		int row = 0;
		while (row < sizeOfBoard-1){
			if ((board[row][col]!=0)&&(board[row][col]==board[row+1][col])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row+1][col] = 0;
				boardChanged = true;
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
					boardChanged = true;
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
						boardChanged = true;
					}
				}
			}
			
			row++;
		}
		System.out.println(currentScore);
	}
	
	
	private void consolidateDown(int col) {	
		int row = sizeOfBoard-1;
		while (row > 0){
			if ((board[row][col]!=0)&&(board[row][col]==board[row-1][col])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row-1][col] = 0;
				boardChanged = true;
			}
			else if (board[row][col]==0) {
				int temp = row-1;
				while ((temp >= 0)&&(board[temp][col]==0)){
					temp--;
				}
				if (temp != -1) {
					board[row][col] = board[temp][col];
					board[temp][col] = 0;
					boardChanged = true;
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
						boardChanged = true;
					}
				}
			}
			
			row--;
		}
		System.out.println(currentScore);
	}
	
	private void consolidateRight(int row) {	
		int col = sizeOfBoard-1;
		while (col > 0){
			if ((board[row][col]!=0)&&(board[row][col]==board[row][col-1])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row][col-1] = 0;
				boardChanged = true;
			}
			else if (board[row][col]==0) {
				int temp = col-1;
				while ((temp >= 0)&&(board[row][temp]==0)){
					temp--;
				}
				if (temp != -1) {
					board[row][col] = board[row][temp];
					board[row][temp] = 0;
					boardChanged = true;
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
						boardChanged = true;
					}
				}
			}
			
			col--;
		}
		System.out.println(currentScore);
	}
	
	private void consolidateLeft(int row) {	
		int col = 0;
		while (col < sizeOfBoard-1){
			if ((board[row][col]!=0)&&(board[row][col]==board[row][col+1])){
				board[row][col] = board[row][col]*2;
				currentScore+=board[row][col];
				board[row][col+1] = 0;
				boardChanged = true;
			}
			else if (board[row][col]==0) {
				int temp = col+1;
				while ((temp < sizeOfBoard)&&(board[row][temp]==0)){
					temp++;
				}
				if (temp != sizeOfBoard) {
					board[row][col] = board[row][temp];
					board[row][temp] = 0;
					boardChanged = true;
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
						boardChanged = true;
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
				freeCells[(i*sizeOfBoard)+j].x = i;
				freeCells[(i*sizeOfBoard)+j].y = j;
			}
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
		updateFreeCells();
		return true;
	}
	
	public void printBoard() {
		for (int[] arr : board) {
            System.out.println(Arrays.toString(arr));
        }
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
	

	public int[][] getBoard() {
		return board;
	}
	 
	public void setBoard(int[][] board) {
		this.board = board;
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}

	@Override
	protected synchronized void setChanged() {
		// TODO Auto-generated method stub
		super.setChanged();
	}
	
	public int getScore() { // returns the score of the current board
		return currentScore;
	}
	
	private boolean checkIfGameOver() {
		for (int i=1 ; i<sizeOfBoard-1 ; i++)
			for (int j=1 ; j<sizeOfBoard-1 ; j++) {
				if (board[i][j]==board[i][j+1] || board[i][j]==board[i][j-1]
						|| board[i][j]==board[i+1][j] || board[i-1][j]==board[i][j])
					return true;
			}
		for (int i=0 ; i<sizeOfBoard-1 ; i++)
			if (board[i][0]==board[i+1][0] || board[i][sizeOfBoard-1]==board[i+1][sizeOfBoard-1])
				return true;
		for (int j=0 ; j<sizeOfBoard-1 ; j++)
			if (board[0][j]==board[0][j+1] || board[sizeOfBoard-1][j]==board[sizeOfBoard-1][j+1])
				return true;
		notifyObservers(); // add something to notify the game is over
		isGameOver = true;
		return false;
	}
	
	private void pushState() {
		State s2 = new State();
		boolean test = false;
		if (!states.empty()){
			test = true;
			
			s2 = states.peek();
		}
		State s1 = new State(board,currentScore);
		/*s1.setBoard(board);
		s1.setScore(currentScore);*/
		states.push(s1);
		// test
		System.out.println("pushing score:"+currentScore);
		if (test) {
		System.out.println(s2.getScore());
		System.out.println(s2.getBoard());
		}
	}
	
	public boolean undoMove() {
		/*State s1,s2,s3 = new State();
		s2 = new State(states.pop());
		s3 = states.pop();
		s1 = states.pop();
		*/
		State s2 = states.pop();
		State s1 = states.pop();
		//State s1 = new State(states.pop().getBoard(),states.pop().getScore());
		board = s1.getBoard();
		currentScore = s1.getScore();
		boardChanged = true;
		notifyObservers();
		updateFreeCells();
		return true;
		
	}

	@Override
	public int[][] getData() {
		return board;
	}

	
	/*
	 public Stack<State> exportStateStack() {
	 	return states;
	 }
	 
	 public boolean setStateStack(Stack<State> importedState) {
	 	states = importedState;
	 	pop
	 	board =
	 	currentState = ....
	 }
	 */
}
