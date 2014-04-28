package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Tile extends Canvas{

	public int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		ChangeBackgroundColor(); //when value is changed - background color will change also
		redraw();
	}

	public Tile(Composite parent, int style) {
		super(parent, style);
		value=0;
		Font f=getFont();
		Font nf=new Font(getDisplay(),f.getFontData()[0].getName(),16,SWT.BOLD);
		setFont(nf);
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				if(value==0)
					setBackground(getDisplay().getSystemColor(SWT.COLOR_CYAN));
				else
					setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
			}
			
		});
	}
	
	public void redraw(){
		
	}
	
	private void ChangeBackgroundColor()
	{
		
	}
	

}
