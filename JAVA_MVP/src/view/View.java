package view;

public interface View {
	//displays the data it has	
	public void displayData(int [][] data);
	//returns an int per action that occurred
	public int getUserCommand();
}
