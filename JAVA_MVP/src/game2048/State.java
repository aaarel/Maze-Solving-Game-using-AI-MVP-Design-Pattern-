package game2048;

public class State {	// meant to keep track of states of the boards - for the undo button 
	
	private int sizeOfBoard = 4;
	
	public State(int[][] board, int score) {
		super();
		this.board = new int[sizeOfBoard][sizeOfBoard];
		for (int i = 0; i < sizeOfBoard; i++) 
	        System.arraycopy(board[i], 0, this.board[i], 0, sizeOfBoard);
		this.score = score;
	}
	
	public State() {
		this.board = new int[sizeOfBoard][sizeOfBoard];
		this.score = 0;
	}
	
	private int[][] board;
	private int score;
	
	public int[][] getBoard() {
		return board;
	}
	public void setBoard(int[][] board) {
		this.board = board;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
