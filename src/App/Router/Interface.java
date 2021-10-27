package App.Router;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Interface extends JPanel {
	private static final long serialVersionUID = 5745134792461456352L;
	
	private ArrayList<Component> komponente = new ArrayList<>();
	
	private InterfaceIcon interfaceName;
	private StringIcon opis;
	private StringIcon tip;
	private StringIcon MTU;
	private StringIcon speed;
	private StringIcon fizickaAdresa;
	private StringPictureIcon administrativniStatus;
	private StringPictureIcon operativniStatus;
	
	public String getOpis() {
		return opis.getVal().substring(6);
	}
	public void setOpis(String opis) {
		this.opis.setVal("Opis: " + opis);
	}
	public String getTip() {
		return tip.getVal().substring(5);
	}
	public void setTip(String tip) {
		this.tip.setVal("Tip: " + tip);
	}
	public int getMTU() {
		return Integer.parseInt(MTU.getVal().substring(5));
	}
	public void setMTU(int mTU) {
		MTU.setVal("MTU: " + mTU);
	}
	public long getSpeed() {
		return Integer.parseInt(speed.getVal().substring(12));
	}
	public void setSpeed(long speed) {
		this.speed.setVal("Speed(Mbs): " + (speed*1.0/1000000));
	}
	public String getFizickaAdresa() {
		return fizickaAdresa.getVal().substring(16);
	}
	public void setFizickaAdresa(String fizickaAdresa) {
		this.fizickaAdresa.setVal("Fizicka adresa: " + fizickaAdresa);
	}
	public String getAdministrativniStatus() {
		int ret = administrativniStatus.getStatus();
		if (ret != 0) return "Up";
		
		return "Down";
	}
	public void setAdministrativniStatus(int administrativniStatus) {
		this.administrativniStatus.setStatus(administrativniStatus);
	}
	public String getOperativniStatus() {
		int ret = operativniStatus.getStatus();
		if (ret != 0) return "Up";
		
		return "Down";
	}
	public void setOperativniStatus(int operativniStatus) {
		this.operativniStatus.setStatus(operativniStatus);
	}
	public String getInterfaceName() {
		return interfaceName.getInterfaceName();
	}
	public void setInterfaceName(String name) {
		interfaceName.setInterfaceName(name);
	}
	
	
	public Interface(String ime , String opis , String tip , int MTU , long speed, String fizickaAdresa , int administrativniStatus , int operativniStatus) {
		this.interfaceName = new InterfaceIcon(ime);
		this.opis = new StringIcon("Opis: " + opis);
		this.tip = new StringIcon("Tip: " + tip);
		this.MTU = new StringIcon("MTU: " + MTU);
		this.speed = new StringIcon("Speed(Mbs): " + (speed*1.0/1000000));
		this.fizickaAdresa = new StringIcon("Fizicka adresa: " + fizickaAdresa);
		this.administrativniStatus = new StringPictureIcon("Administrativni status: " ,administrativniStatus);
		this.operativniStatus = new StringPictureIcon("Operativni status: " , operativniStatus);
		dodaj();
	}
	
	public ArrayList<Component> getComponent() {
		return komponente;
	}
	
	private void dodaj() {
		komponente.add(interfaceName);
		komponente.add(opis);
		komponente.add(tip);
		komponente.add(MTU);
		komponente.add(speed);
		komponente.add(fizickaAdresa);		
		komponente.add(administrativniStatus);
		komponente.add(operativniStatus);
	}

	

}
