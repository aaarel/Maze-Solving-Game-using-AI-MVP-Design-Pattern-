package controller;

import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer {
	//data members of View and Model type that will be initialized in the 'Main program'
	View ui;
	Model model;
	
	public Presenter (Model m, View ui){
		this.ui = new View(ui);
		this.model = new Model(m);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}