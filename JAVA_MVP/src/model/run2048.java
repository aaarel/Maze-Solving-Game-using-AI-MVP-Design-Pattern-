package model;

public class run2048 {

	public static void main(String[] args) {
		Game2048Model board1 = new Game2048Model();
		board1.initBoard();
		board1.printBoard();
		board1.moveUp();
		board1.printBoard();

	}

}
