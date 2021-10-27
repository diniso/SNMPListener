package App.SNMPHandler;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ireasoning.protocol.snmp.SnmpGauge32;
import com.ireasoning.protocol.snmp.SnmpInt;
import com.ireasoning.protocol.snmp.SnmpOctetString;
import com.ireasoning.protocol.snmp.SnmpSession;
import com.ireasoning.protocol.snmp.SnmpTableModel;
import com.ireasoning.protocol.snmp.SnmpTarget;

import App.MainWindow;
import App.Router.Interface;
import App.Router.Router;

public class SNMPHandler extends Thread {
	
	private static int port = 161;
	private static String community = "si2019";
	private static String startingPoint = ".1.3.6.1.2.1.2.2";
	private static int cekaj = 10000;
	private static int ID=1;
	private static SimpleDateFormat formatter= new SimpleDateFormat("(yyyy-MM-dd HH:mm:ss) : ");
	
	
	private boolean radi = true;
	private Router mojRuter;
	private String IpAdress;
	private MainWindow window;
	
	public SNMPHandler(String IpAdresa , MainWindow m) {
		IpAdress = IpAdresa;
		window = m;
	}
	
	public void zaustavi() {
		radi = false;
		this.interrupt();
	}
	public void run() {

		try {
				SnmpTarget target = new SnmpTarget(IpAdress , port , community , community);
				SnmpSession session = new SnmpSession(target);

				SnmpSession.loadMib2();
				SnmpTableModel tabela = session.snmpGetTable(startingPoint);
				
				mojRuter = new Router("Router" + (ID++), IpAdress ,window);
				
				
				for (int i = 0 ; i < tabela.getRowCount();i++) {
					
					String interfejsName = "Interface" + (i + 1);
					
					String opis = ((SnmpOctetString)tabela.get(i , 1).getValue()).toString();
					
					String tip =  ((SnmpInt)tabela.get(i , 2).getValue()).toString();
					
					int mtu = ((SnmpInt)tabela.get(i , 3).getValue()).getValue();
					
					long speed = ((SnmpGauge32)tabela.get(i , 4).getValue()).getValue();
					
					String fizickaAdresa = ((SnmpOctetString)tabela.get(i , 5).getValue()).toHexString2();
					fizickaAdresa = fizickaAdresa.substring(2);				
					char[] niz = fizickaAdresa.toCharArray();
					if (niz.length == 17) {
						for (int j = 0 ; j < niz.length ; j++)
							if (j % 3 == 2) niz[j] = '.';
					}			
					fizickaAdresa = String.valueOf(niz);
					
					int administrativniStatus = ((SnmpInt)tabela.get(i , 6).getValue()).getValue();
					if (administrativniStatus != 1)  administrativniStatus = 0;
					
					int operativniStatus = ((SnmpInt)tabela.get(i , 7).getValue()).getValue();
					if (operativniStatus != 1)  operativniStatus = 0;
				
					
					mojRuter.dodajInterface(new Interface(interfejsName , opis , tip , mtu , speed , fizickaAdresa , administrativniStatus , operativniStatus));
					
					
				}
					
				
				window.dodajUListu(mojRuter, this);
				
				while(!Thread.interrupted() && radi) {
					
					Thread.sleep(cekaj);
					
					SnmpSession.loadMib2();
					tabela = session.snmpGetTable(startingPoint);

					int i = 0;
					for (Interface in : mojRuter.getInterfaces()) {
						
						in.setOpis(((SnmpOctetString)tabela.get(i , 1).getValue()).toString());
						
						in.setTip(((SnmpInt)tabela.get(i , 2).getValue()).toString());
						
						in.setMTU(((SnmpInt)tabela.get(i , 3).getValue()).getValue());
						
						in.setSpeed(((SnmpGauge32)tabela.get(i , 4).getValue()).getValue());
						
						String fizickaAdresa = ((SnmpOctetString)tabela.get(i , 5).getValue()).toHexString2();
						fizickaAdresa = fizickaAdresa.substring(2);				
						char[] niz = fizickaAdresa.toCharArray();
						if (niz.length == 17) {
							for (int j = 0 ; j < niz.length ; j++)
								if (j % 3 == 2) niz[j] = '.';
						}			
						fizickaAdresa = String.valueOf(niz);
						in.setFizickaAdresa(fizickaAdresa);					
						
						int administrativniStatus = ((SnmpInt)tabela.get(i , 6).getValue()).getValue();
						if (administrativniStatus != 1)  administrativniStatus = 0;
						in.setAdministrativniStatus(administrativniStatus);
						
						int operativniStatus = ((SnmpInt)tabela.get(i , 7).getValue()).getValue();
						if (operativniStatus != 1)  operativniStatus = 0;
						in.setOperativniStatus(operativniStatus);
						
						i++;
						
					}
					
					window.repaint();
					
					System.out.println(formatter.format(new Date()) + "Provera na 10sek: "+ IpAdress);
				}
	
			
			
			
			session.close();
			
		} catch (IOException | InterruptedException  e) {
			// TODO Auto-generated catch block
		}
		
		System.out.println(formatter.format(new Date()) +"Gotovo:  "+ IpAdress);
	}
}
