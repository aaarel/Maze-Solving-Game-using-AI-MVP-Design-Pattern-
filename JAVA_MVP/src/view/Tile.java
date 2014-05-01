package view;

public class Tile extends Canvas{

	private int value;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		ChangeBackgroundColor(); //when value is changed - background color will change also
		redraw(); //invokes the method paintControl(..) which "draws" again the data
	}
	public Tile(Composite parent, int style) {
		super(parent, style);
		
		Font f=getFont();
		Font nf=new Font(getDisplay(),f.getFontData()[0].getName(),16,SWT.BOLD);
		setFont(nf);
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {			
				FontMetrics fm = e.gc.getFontMetrics();
				int width=fm.getAverageCharWidth();
				int mx = getSize().x/2 - (""+value).length() * width/2 ;
				int my = getSize().y/2 - fm.getHeight()/2-fm.getDescent(); 
				if(value>0)
					e.gc.drawString(" "+value, mx, my);
			}
		});
	}
	private void ChangeBackgroundColor()
	{
		if(value==0)
			setBackground(getDisplay().getSystemColor(SWT.COLOR_CYAN));
		else
			setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
	}
}