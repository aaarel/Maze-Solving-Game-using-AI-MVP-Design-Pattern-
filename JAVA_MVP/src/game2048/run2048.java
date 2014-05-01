package game2048;

import game2048.Game2048Model;


public class run2048 {

	public static void main(String[] args) {
		Game2048Model board1 = new Game2048Model();
		board1.initBoard();
		board1.printBoard();
		board1.moveUp();
		board1.printBoard();
		board1.fillRandomFreeCell();
		board1.printBoard();
		board1.moveRight();
		board1.printBoard();
		board1.fillRandomFreeCell();
		board1.printBoard();
		board1.moveLeft();
		board1.printBoard();
		board1.fillRandomFreeCell();
		board1.printBoard();
		board1.moveDown();
		board1.printBoard();
		board1.fillRandomFreeCell();
		board1.printBoard();
		board1.moveRight();
		board1.printBoard();

		board1.fillRandomFreeCell();
		board1.printBoard();
		board1.moveLeft();
		board1.printBoard();
	}

}
