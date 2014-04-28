package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Tile extends Canvas{

	public Tile(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void redraw(){
		
	}
	
	private void ChangeColor()
	{
		
	}
	

}
