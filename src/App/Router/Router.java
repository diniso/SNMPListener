package App.Router;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Component;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import App.MainWindow;


public class Router extends JPanel {
	private static final long serialVersionUID = 3978293207466381005L;
	
	private MainWindow mojFrame;
	private final RouterIcon routerName;
	private final InterfaceIcon snmpIpInterface;
	private ArrayList<Interface> listaInterface = new ArrayList<>() ;
	
	class PopMenu extends JPopupMenu {
		private static final long serialVersionUID = -6159513906009832715L;
		JMenuItem item;
		
		public PopMenu(Router r) {
			item = new JMenuItem("Delete");
			item.addActionListener(e-> {
				mojFrame.izbaciIzListe(r);
			});
			this.add(item);
		}
		
	}
	
	public String getSnmpInterfaceIp() {
		return snmpIpInterface.getInterfaceName();
	}
	
	public ArrayList<Interface> getInterfaces() {
		return listaInterface;
	}
	
	public String getRouterName() {
		return routerName.getRouterName();
	}

	public MainWindow getMainWindow() {
		return mojFrame;
	}
	
	public void dodajInterface(Interface i) {
		listaInterface.add(i);
		for (Component c : i.getComponent()) 
			this.add(c);

		this.revalidate();
	}
	
	public void izbaciInterfejs(Interface i) {
		listaInterface.remove(i);
		for (Component c : i.getComponent())
			this.remove(c);

		this.revalidate();
	}
	
	public Router(String name , String InterfaceSnmp ,  MainWindow window) {
		mojFrame = window;
		routerName = new RouterIcon(name);
		snmpIpInterface = new InterfaceIcon(InterfaceSnmp);
		this.setLayout(new GridLayout(0 , 1 , 5 , 5));

		this.add(routerName);
		this.add(snmpIpInterface);
		
		Router r = this;
		this.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent e) {
			        if (e.isPopupTrigger())
			            doPop(e);
			    }

			    public void mouseReleased(MouseEvent e) {
			        if (e.isPopupTrigger())
			            doPop(e);
			    }

			    private void doPop(MouseEvent e) {
			        PopMenu menu = new PopMenu(r);
			        menu.show(e.getComponent(), e.getX(), e.getY());
			    }
		});
		
	}

}
