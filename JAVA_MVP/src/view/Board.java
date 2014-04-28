package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class Board extends Composite {

	int N; 				//represents the size of the board N over N
	int [][] BoardData; //represents the data of the board
	public Board(Composite parent, int style) {
		super(parent, style);
		N=4;
		BoardData = new int[N][N];
		setLayout( new GridLayout(N, true) );
		setLayoutData( new GridLayout(N,true) );
		
		//Drawing a board
		Tile tiles[][] = new Tile[N][N];
		BoardData[0][0] = 2;
		BoardData[0][1] = 4;
		BoardData[0][2] = 8;
		BoardData[0][3] = 16;
		BoardData[0][4] = 2048;
		BoardData[0][5] = 24;
		BoardData[1][0] = 16;
		BoardData[2][2] = 8;
	
	for(int i=0;i<N; i++)
		for(int j=0 ;j<N; j++){
			tiles[i][j]=new Tile(this,SWT.BORDER);
			tiles[i][j].setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true) );
			tiles[i][j].setValue(BoardData[i][j]);
		}
	}
			
}
