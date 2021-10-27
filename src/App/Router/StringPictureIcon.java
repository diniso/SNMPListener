package App.Router;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StringPictureIcon extends JPanel{
	private static final long serialVersionUID = 5570498376010383157L;
	static int NumberOfPixels = InterfaceIcon.r + 2*InterfaceIcon.NumberOfPixels; 
	static int MaximumDimensionY = InterfaceIcon.MaximumDimensionY;
	static int a = 8;
	static int LetterPerPixel = 6;
	
	private String val;
	private int status;
	
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public StringPictureIcon(String val, int status) {
		this.val = val;
		this.status = status;
	}
	
	
	public void paintComponent(Graphics g) {
		int y = Math.min(this.getHeight(), MaximumDimensionY);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(NumberOfPixels, (y-a)/2 , a , a);
		g.drawString(val, NumberOfPixels + a + InterfaceIcon.NumberOfPixels , (y + a)/2);
		
		int lenPixels = val.length() * LetterPerPixel;
		
		if (status != 0) g.setColor(Color.GREEN);
		else g.setColor(Color.RED);
		
		g.fillOval(NumberOfPixels + a + InterfaceIcon.NumberOfPixels + lenPixels ,(y-a)/2 , a, a);
	}
}
