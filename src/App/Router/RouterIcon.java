package App.Router;

import java.awt.Graphics;

import javax.swing.JPanel;

public class RouterIcon extends JPanel{
	private static final long serialVersionUID = 7608367395622425600L;
	static int NumberOfPixels = 12; 
	static int MaximumDimensionY = 50;
	static int a = 10;
	
	
	private String routerName ;

	
	public String getRouterName() {
		return routerName;
	}
	public void setRouterNameName(String routerName) {
		this.routerName = routerName;
	}


	public RouterIcon(String routerName) {
		this.routerName = routerName;
	}
	
	public void paintComponent(Graphics g) {
		int y = Math.min(this.getHeight(), MaximumDimensionY);
		g.drawRect(NumberOfPixels, (y-a)/2 , a , a);
		g.drawString(routerName, 2*NumberOfPixels + a , (y + a)/2);
	}

}
