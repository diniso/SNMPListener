package App.Router;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StringIcon extends JPanel{
	private static final long serialVersionUID = 1596458254518436546L;
	static int NumberOfPixels = InterfaceIcon.r + 2*InterfaceIcon.NumberOfPixels; 
	static int MaximumDimensionY = InterfaceIcon.MaximumDimensionY;
	static int a = 8;
	
	private String val;
	
	
	public String getVal() {
		return val;
	}
	public void setVal(String val) {		
		this.val = val;
	}

	
	public StringIcon(String val) {
		this.val = val;
	}
	
	public void paintComponent(Graphics g) {
		int y = Math.min(this.getHeight(), MaximumDimensionY);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(NumberOfPixels, (y-a)/2 , a , a);
		g.drawString(val, NumberOfPixels + a + InterfaceIcon.NumberOfPixels , (y + a)/2);
	}
	
	
	

}
