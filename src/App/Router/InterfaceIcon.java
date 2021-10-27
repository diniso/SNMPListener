package App.Router;

import java.awt.Graphics;
import javax.swing.JPanel;

public class InterfaceIcon extends JPanel{
	private static final long serialVersionUID = 7552502887775996626L;	
	static int r = 4;
	static int NumberOfPixels = 30; 
	static int MaximumDimensionY = 50;
	
	
	
	private String interfaceName ;

	
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}


	public InterfaceIcon(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	
	public void paintComponent(Graphics g) {
		int y = Math.min(this.getHeight(), MaximumDimensionY);
		g.drawOval(NumberOfPixels, (y-r)/2 , r , r);
		g.drawString(interfaceName, 2*NumberOfPixels + r , y/2 + r);
	}

}
