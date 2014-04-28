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
		
	}

}
