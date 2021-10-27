package App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import App.Router.*;
import App.SNMPHandler.SNMPHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -3947095195059101841L;
	private static SimpleDateFormat formatter= new SimpleDateFormat("(yyyy-MM-dd HH:mm:ss) : ");
	public static int x = 200 , y = 100 , Width = 800 , Height = 600;
	
	
	private HashMap<Router, SNMPHandler> mapaRutera = new HashMap<>();
	private JPanel listaRutera;
	private String trenutnaIpAdresa;
	private JTextField textPolje;
	private JLabel labela;
	private JButton ubaciRuter;
	
	
	public void dodajUListu(Router r , SNMPHandler h) {
		listaRutera.add(r);
		mapaRutera.put(r, h);
		listaRutera.revalidate();
		this.revalidate();
	}
	
	public void izbaciIzListe(Router r) {
		if(mapaRutera.get(r) != null) mapaRutera.get(r).zaustavi();
		listaRutera.remove(r);
		mapaRutera.remove(r);
		listaRutera.revalidate();
		this.revalidate();
	}
	
	
	public MainWindow() {
		super("SNMP simulation");
		this.setBackground(new Color(250 ,250 , 250));

		this.setBounds(x,y,Width,Height);
		
		dodaj();
		dodajOsluskivace();
				
		this.setVisible(true);
		
	}

	private void dodaj() {
		listaRutera = new JPanel(new GridLayout(0 , 2 , 5 , 5));
		
		JPanel gore = new JPanel(new GridLayout(1 , 4 ));
		gore.setPreferredSize(new Dimension(50 , 35));
		
		labela = new JLabel("Uneti ip adresu: ");
		labela.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		textPolje = new JTextField();
		ubaciRuter = new JButton("UBACI RUTER");
		
		gore.add(labela);
		gore.add(textPolje);
		gore.add(ubaciRuter);
		gore.add(new JPanel());
		
	
		JScrollPane pane = new JScrollPane(listaRutera);
		pane.setPreferredSize(new Dimension(Width - 50 , Height - 35));
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(gore , BorderLayout.NORTH);
		this.add(pane);
		
		
	}

	private void dodajOsluskivace() {
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				for (SNMPHandler h : mapaRutera.values()) {
					if (h != null) h.zaustavi();
				}
				
				dispose();
			}
		});
		
		textPolje.getDocument().addDocumentListener(new DocumentListener() {
			 @Override
			    public void insertUpdate(DocumentEvent e) {
				 trenutnaIpAdresa = textPolje.getText();
			    }

			    @Override
			    public void removeUpdate(DocumentEvent e) {
			    	trenutnaIpAdresa = textPolje.getText();
			    }

			    @Override
			    public void changedUpdate(DocumentEvent e) {
			    	trenutnaIpAdresa = textPolje.getText();
			    }
		});
		
		
		ubaciRuter.addActionListener(e-> {
			for (Router r : mapaRutera.keySet()) {
				if (r.getSnmpInterfaceIp().equals(trenutnaIpAdresa)) return;
			}
			
			SNMPHandler h = new SNMPHandler(trenutnaIpAdresa , this);
			h.setDaemon(true);
			h.start();
			System.out.println(formatter.format(new Date()) + "Napravio nit: " + trenutnaIpAdresa);
		});
				
	}
        
        public void test() {
		Router r1 = new Router("Router1" , "", this);
		r1.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100  , "AA.BB.CC.DD" , 1 , 1));
		r1.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100  , "AA.BB.CC.DD" , 1 , 1));
		r1.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100  , "AA.BB.CC.DD" , 1 , 1));
		
		Router r2 = new Router("Router2", "" , this);
		r2.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100  , "AA.BB.CC.DD" , 1 , 1));
		r2.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100 , "AA.BB.CC.DD" , 1 , 1));
		
		Router r3 = new Router("Router3", "" , this);
		r3.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100 , "AA.BB.CC.DD" , 1 , 1));
		r3.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100  , "AA.BB.CC.DD" , 1 , 1));
		r3.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100 , "AA.BB.CC.DD" , 1 , 1));
		r3.dodajInterface(new Interface("Interface1" , "Nema tip" , "fa0/0" , 5 , 100 , "AA.BB.CC.DD" , 1 , 1));
		
		this.dodajUListu(r1, null);
		this.dodajUListu(r2, null);
		this.dodajUListu(r3, null);

	}

	

	public static void main(String[] args) {
		new MainWindow();
		
			
	}
}
