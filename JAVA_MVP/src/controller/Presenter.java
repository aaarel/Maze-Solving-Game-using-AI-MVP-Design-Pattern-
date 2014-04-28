package controller;

import java.util.Observable;
import java.util.Observer;
import model.Game2048Model;
import model.Model;
import view.Game2048View;
import view.View;

public class Presenter implements Observer {
	//data members of View and Model type that will be initialized in the 'Main program'
	View ui;
	Model model;
	
	public Presenter (Model m, View ui){
		this.ui = new Game2048View();
		this.model = new Game2048Model();
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
