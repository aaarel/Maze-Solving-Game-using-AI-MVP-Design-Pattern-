package controller;

import view.Game2048View;
import view.View;
import model.Game2048Model;
import model.Model;

public class Game2048Run {

	public static void main(String[] args) {
		Model m = new Game2048Model();
		View ui = new Game2048View();
		Presenter p = new Presenter(m, ui); 
		m.addObserver(p);
		ui.addObserver(p);


	}

}
