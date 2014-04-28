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
	
	public Presenter (View ui, Model m){
		this.ui = new Game2048View();	//add C'tor with arg ui 
		this.model = new Game2048Model(); //add C'tor with arg m
		
 	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o==ui){
			int input = ui.getUserCommand();
			switch(input){
			 case 0: {model.moveDown();
			 break;}
			 case 1: {model.moveUp();
			 break;}
			 case 2: {model.moveRight();
			 break;}
			 case 3: {model.moveLeft();
			 break;}
			} 
		}
		if(o==model){
			ui.displayData(model.getData());
			
		}
	}

}
