package Ruta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class JugadorRuta extends JPanel{
	
	private JLabel avatar = new JLabel();
	private List<CartaRuta> mano = new ArrayList<CartaRuta>();
	private List<CartaRuta> mesa = new ArrayList<CartaRuta>();

	private GridBagConstraints constraints = new GridBagConstraints();
	private JTextPane puntajeTexto = new JTextPane();
	private String nombre = "";
	private JTextField jugadorNombre = new JTextField();
	int puntajeGuardado = 0;
	private boolean jugadorActivado = true;
	private boolean reverso = false;
	

	private  List<CartaRuta> distancia  = new ArrayList<CartaRuta>();
	private  List<CartaRuta> semaforo = new ArrayList<CartaRuta>();
	private  List<CartaRuta> pinchazo = new ArrayList<CartaRuta>();
	private  List<CartaRuta> accidente = new ArrayList<CartaRuta>();
	private  List<CartaRuta> limite = new ArrayList<CartaRuta>();
	private  List<CartaRuta> gasolina = new ArrayList<CartaRuta>();
	
	
	public boolean isReverso() {
		return reverso;
	}

	public void setReverso(boolean reverso) {
		this.reverso = reverso;
	}
	
	public boolean getJugadorActivado() {
		return jugadorActivado;
	}

	public void setJugadorActivado(boolean jugadorActivado) {
		this.jugadorActivado = jugadorActivado;
	}

	public List<CartaRuta> getDistancia() {
		return distancia;
	}

	public void setDistancia(List<CartaRuta> distancia) {
		this.distancia = distancia;
	}

	public List<CartaRuta> getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(List<CartaRuta> semaforo) {
		this.semaforo = semaforo;
	}

	public List<CartaRuta> getPinchazo() {
		return pinchazo;
	}

	public void setPinchazo(List<CartaRuta> pinchazo) {
		this.pinchazo = pinchazo;
	}

	public List<CartaRuta> getAccidente() {
		return accidente;
	}

	public void setAccidente(List<CartaRuta> accidente) {
		this.accidente = accidente;
	}

	public List<CartaRuta> getLimite() {
		return limite;
	}

	public void setLimite(List<CartaRuta> limite) {
		this.limite = limite;
	}

	public List<CartaRuta> getGasolina() {
		return gasolina;
	}

	public void setGasolina(List<CartaRuta> gasolina) {
		this.gasolina = gasolina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public JLabel getAvatar() {
		return avatar;
	}

	public void setAvatar(JLabel avatar) {
		this.avatar = avatar;
	}


	
	public List<CartaRuta> getMano() {
		return mano;
	}
	
	public void setMano(List<CartaRuta> mano) {
		this.mano = mano;
	}

	public List<CartaRuta> getMesa() {
		return mesa;
	}

	public void setMesa(List<CartaRuta> mesa) {
		this.mesa = mesa;
	}

	public int getPuntaje() {
		return puntajeGuardado;
	}

	public void setPuntaje(int puntaje) {
		this.puntajeGuardado = puntaje;
	}
	
	
	
	public void reiniciar() {
		
		int puntaje = 0;
		int contador = 0;
		
		for (int x = 0; x < distancia.size(); x++) {
			
			puntaje = puntaje + distancia.get(x).getValor();
			
		}
		
		if (puntaje >= 1000) {
			
			puntaje=puntaje + 300;
			
		}
			
		
		for(int y = 0; y < mesa.size(); y++){
			
			if (mesa.get(y).getTipo() == 'G') {
				puntaje = puntaje + mesa.get(y).getValor();
				contador++;
			}
			
		}
		
		if(contador>=4){
			puntaje = puntaje + 300;
		}
		
		puntajeGuardado = puntajeGuardado + puntaje;
		
		
		mesa.clear();
		mano.clear();
		
		distancia.clear();
		semaforo.clear();
		pinchazo .clear();
		accidente .clear();
		limite .clear();
		gasolina .clear();
		
		jugadorActivado = true;
		
		this.removeAll();
		
		
	}
	
	public void configuracion() {
		
	if (reverso == false) {
		
		for(int n = 0; n < mano.size(); n++){
			
			mano.get(n).setIcon(new ImageIcon("src/imagenes/reverso.jpg"));
		}
		
	}	
	
	mesa.add(new CartaRuta('P', "src/imagenes/stop.png", 0));
	semaforo.add(new CartaRuta('P', "src/imagenes/stop.png", 0));
	
	this.setLayout(new GridBagLayout());
		
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.fill = GridBagConstraints.BOTH;
	this.add(avatar,constraints);
	
	
	for(int x = 0; x<mano.size();x++) {
		
		constraints.gridx = x+1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.CENTER;
		
		
		mano.get(x).setBorder(null);
		this.add(mano.get(x),constraints);
		
	}
	
	constraints.gridx = 0;
	constraints.gridy = 1;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.fill = GridBagConstraints.BOTH;
	
	Border bordeTexto = BorderFactory.createLineBorder(Color.lightGray);
	puntajeTexto.setText("0KM");
	puntajeTexto.setBackground(null);
	puntajeTexto.setBorder(bordeTexto);
	puntajeTexto.setEditable(false);
	puntajeTexto.setPreferredSize(new Dimension(50,50));
	puntajeTexto.setFont(new Font("ComicSans", 0, 12));
	
	this.add(puntajeTexto,constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 2;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.fill = GridBagConstraints.BOTH;
	
	jugadorNombre.setText(nombre);
	jugadorNombre.setEditable(false);
	jugadorNombre.setPreferredSize(new Dimension(50,50));
	jugadorNombre.setFont(new Font("ComicSans", 0, 12));
	
	this.add(jugadorNombre,constraints);
	
	for(int y = 1; y<7; y++) 
	{
		switch(y) 
		{
		case 1:
			Border bordeGenerico = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+distancia.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
			JLabel generico = new JLabel();
			generico.setIcon(new ImageIcon("src/imagenes/distanciaEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.CENTER;
			
			generico.setBorder(bordeGenerico);
			this.add(generico,constraints);
			
			break;
		case 2:
			Border bordeGenerico1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+semaforo.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
			JLabel generico1 = new JLabel();
			generico1.setIcon(new ImageIcon("src/imagenes/stop.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.CENTER;
			
			generico1.setBorder(bordeGenerico1);
			this.add(generico1,constraints);
			
			break;
		case 3:
			Border bordeGenerico11 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+pinchazo.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
			JLabel generico11 = new JLabel();
			generico11.setIcon(new ImageIcon("src/imagenes/pinchazoEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.CENTER;
			
			generico11.setBorder(bordeGenerico11);
			this.add(generico11,constraints);
			break;
		case 4:
			Border bordeGenerico111 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+accidente.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
			JLabel generico111 = new JLabel();
			generico111.setIcon(new ImageIcon("src/imagenes/choquesEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.CENTER;
			
			generico111.setBorder(bordeGenerico111);
			this.add(generico111,constraints);
			break;
		case 5:
			Border bordeGenerico2 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+limite.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
			JLabel generico2 = new JLabel();
			generico2.setIcon(new ImageIcon("src/imagenes/limitesEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.CENTER;
			
			generico2.setBorder(bordeGenerico2);
			this.add(generico2,constraints);
			break;
		case 6:
			Border bordeGenerico21 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+gasolina.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
			JLabel generico21 = new JLabel();
			generico21.setIcon(new ImageIcon("src/imagenes/gasolinaEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.CENTER;
			

			generico21.setBorder(bordeGenerico21);
			this.add(generico21,constraints);
			break;
		}
	}
	
	for(int x = 1; x < 5;x++) {
		
		JLabel generico = new JLabel();
		generico.setIcon(new ImageIcon("src/imagenes/poderEspacio.png"));
		
		constraints.gridx = x;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.CENTER;
		
		this.add(generico,constraints);
		
	}
	
	}
	
	public JTextPane getPuntajeTexto() {
		return puntajeTexto;
	}

	public void setPuntajeTexto(JTextPane puntajeTexto) {
		this.puntajeTexto = puntajeTexto;
	}

	public void redibujar() {
		
		if (reverso == false) {
			
			for(int n = 0; n < mano.size(); n++){
				
				mano.get(n).setIcon(new ImageIcon("src/imagenes/reverso.jpg"));
			}
			
		}	
		
		this.removeAll();
		// TODO Auto-generated method stub}
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		this.add(avatar,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		
		int puntaje = 0;
		
		for (int x = 0; x < distancia.size(); x++) {
			
			puntaje = puntaje + distancia.get(x).getValor();
			
		}
		
		int puntaje2=0;
		
		puntaje2 = puntajeGuardado+puntaje;
		
		puntajeTexto.setBackground(null);
		puntajeTexto.setText(puntaje+"KM en ronda\n"+puntaje2+"KM en total");
		puntajeTexto.setEditable(false);
		puntajeTexto.setPreferredSize(new Dimension(50,50));
		puntajeTexto.setFont(new Font("ComicSans", 0, 12));
		
		this.add(puntajeTexto,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		
		jugadorNombre.setText(nombre);
		jugadorNombre.setEditable(false);
		jugadorNombre.setPreferredSize(new Dimension(50,50));
		jugadorNombre.setFont(new Font("ComicSans", 0, 12));
		
		this.add(jugadorNombre,constraints);
		
		System.out.print("Tamaño baraja " + mano.size() + "\n");
		System.out.print("distancia " + distancia.size() + "\n");
		System.out.print("gasolina " + gasolina.size() + "\n");
		System.out.print("pinchazo " + pinchazo.size() + "\n");
		System.out.print("semaforo " + semaforo.size() + "\n");
		System.out.print("accidente " + accidente.size() + "\n");
		System.out.print("limite " + limite.size() + "\n");
		
		for(int x = 0; x<mano.size();x++) {
			
			
			constraints.gridx = x+1;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.CENTER;
			
			
			mano.get(x).setBorder(null);
			this.add(mano.get(x),constraints);
		}
		
		
		for(int y = 1; y<7; y++) 
		{
			switch(y) 
			{
			case 1:

				int contador = 0; 
				CartaRuta cartaGenerica = null;
				
				for(int x = 0;x < mesa.size(); x++) {
					
					if (mesa.get(x).getTipo() == 'D') {
						
						cartaGenerica = mesa.get(x);
						contador++;
						
					}
					
				}
				
				if (contador == 0) {
					
					Border bordeGenerico = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+distancia.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					
					JLabel generico = new JLabel();
					generico.setIcon(new ImageIcon("src/imagenes/distanciaEspacio.png"));
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					generico.setBorder(bordeGenerico);
					this.add(generico,constraints);
					
				}else {
					
					Border bordeGenerico = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+distancia.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					cartaGenerica.setIcon(new ImageIcon(cartaGenerica.getId()));
					
					cartaGenerica.setBorder(bordeGenerico);
					this.add(cartaGenerica,constraints);
					
				}
				
				break;
			case 2:
				
				int contador2 = 0;
				CartaRuta cartaGenerica2 = null;
				
				for(int x = 0;x < mesa.size(); x++) {
					
					if (mesa.get(x).getId().equals("src/imagenes/stop.png") || mesa.get(x).getId().equals("src/imagenes/libre.png") ) {
						
						cartaGenerica2 = mesa.get(x);
						contador2++;
						
					}
					
				}
				
				
				
				if (contador2 == 0) {
					
				Border bordeGenerico1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+semaforo.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
				JLabel generico1 = new JLabel();
				generico1.setIcon(new ImageIcon("src/imagenes/stop.png"));
				
				constraints.gridx = y;
				constraints.gridy = 1;
				constraints.gridwidth = 1;
				constraints.gridheight = 1;
				constraints.fill = GridBagConstraints.CENTER;
				
				generico1.setBorder(bordeGenerico1);
				this.add(generico1,constraints);
				
				}else 
				{
					Border bordeGenerico1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+semaforo.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					cartaGenerica2.setIcon(new ImageIcon(cartaGenerica2.getId()));
					cartaGenerica2.setBorder(bordeGenerico1);
					this.add(cartaGenerica2,constraints);
					
				}
				
				break;
			case 3:
				int contador3=0;
				CartaRuta cartaGenerica3 = null;
				
				for(int x = 0;x < mesa.size(); x++) {
					
					if (mesa.get(x).getId().equals("src/imagenes/pinchazo.png") || mesa.get(x).getId().equals("src/imagenes/ruedaDeCambio.png")) {
						
						cartaGenerica3 = mesa.get(x);
						contador3++;
						
					}
					
				}
				
				if (contador3 == 0) {
					
					Border bordeGenerico11 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+pinchazo.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					JLabel generico11 = new JLabel();
					generico11.setIcon(new ImageIcon("src/imagenes/pinchazoEspacio.png"));
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					generico11.setBorder(bordeGenerico11);
					this.add(generico11,constraints);
					
				}else {
					Border bordeGenerico11 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+pinchazo.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					cartaGenerica3.setIcon(new ImageIcon(cartaGenerica3.getId()));
					cartaGenerica3.setBorder(bordeGenerico11);
					this.add(cartaGenerica3,constraints);
					
				}
				
				break;
			case 4:
				int contador4 = 0;
				CartaRuta cartaGenerica4 = null;
				
				for(int x = 0;x < mesa.size(); x++) {
					
					if (mesa.get(x).getId().equals("src/imagenes/accidente.png") || mesa.get(x).getId().equals("src/imagenes/reparaciones.png")) {
						
						cartaGenerica4 = mesa.get(x);
						contador4++;
						
					}
					
				}
				
				if (contador4 == 0){
					Border bordeGenerico111 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+accidente.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					JLabel generico111 = new JLabel();
					generico111.setIcon(new ImageIcon("src/imagenes/choquesEspacio.png"));
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					generico111.setBorder(bordeGenerico111);
					this.add(generico111,constraints);	
				}else {
					Border bordeGenerico111 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+accidente.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
				
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					cartaGenerica4.setIcon(new ImageIcon(cartaGenerica4.getId()));
					cartaGenerica4.setBorder(bordeGenerico111);
					this.add(cartaGenerica4,constraints);
				}
				
				break;
			case 5:
				int contador5 = 0;
				CartaRuta cartaGenerica5 = null;
				
				for(int x = 0;x < mesa.size(); x++) {
					
					if (mesa.get(x).getId().equals("src/imagenes/limite.png") || mesa.get(x).getId().equals("src/imagenes/velocidad.png")) {
						
						cartaGenerica5 = mesa.get(x);
						contador5++;
						
					}
					
				}
				
				if (contador5 == 0){
					
					Border bordeGenerico2 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+limite.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					JLabel generico2 = new JLabel();
					generico2.setIcon(new ImageIcon("src/imagenes/limitesEspacio.png"));
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					generico2.setBorder(bordeGenerico2);
					this.add(generico2,constraints);
					
				}else {
					
					Border bordeGenerico2 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+limite.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					cartaGenerica5.setIcon(new ImageIcon(cartaGenerica5.getId()));
					cartaGenerica5.setBorder(bordeGenerico2);
					this.add(cartaGenerica5,constraints);
				
				}
				
				break;
			case 6:
				int contador6 = 0;
				CartaRuta cartaGenerica6 = null;
				
				for(int x = 0;x < mesa.size(); x++) {
					
					if (mesa.get(x).getId().equals("src/imagenes/sinGasolina.png") || mesa.get(x).getId().equals("src/imagenes/gasolina.png")) {
						
						cartaGenerica6 = mesa.get(x);
						contador6++;
						
					}
					
				}
				
				if(contador6 == 0){
					
					Border bordeGenerico21 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+gasolina.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					JLabel generico21 = new JLabel();
					generico21.setIcon(new ImageIcon("src/imagenes/gasolinaEspacio.png"));
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					

					generico21.setBorder(bordeGenerico21);
					this.add(generico21,constraints);
					
				}else {
					
					Border bordeGenerico21 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "X"+gasolina.size(), TitledBorder.CENTER, TitledBorder.BOTTOM);
					
					
					constraints.gridx = y;
					constraints.gridy = 1;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					cartaGenerica6.setIcon(new ImageIcon(cartaGenerica6.getId()));
					cartaGenerica6.setBorder(bordeGenerico21);
					this.add(cartaGenerica6,constraints);
					
				}
				
				break;
			}
		}
		
		int contador = 0;
			
			for (int x = 0; x < mesa.size(); x++){
				
				if (mesa.get(x).getTipo() == 'G') {
					
					contador++;
					
					CartaRuta generico = mesa.get(x);
					generico.setBorder(null);
					
					constraints.gridx = contador;
					constraints.gridy = 2;
					constraints.gridwidth = 1;
					constraints.gridheight = 1;
					constraints.fill = GridBagConstraints.CENTER;
					
					generico.setIcon(new ImageIcon(generico.getId()));
					this.add(generico,constraints);
				}
				
			}
			
			for(int x = 1; x < 5;x++) {
				
				JLabel generico = new JLabel();
				generico.setIcon(new ImageIcon("src/imagenes/poderEspacio.png"));
				
				constraints.gridx = x;
				constraints.gridy = 2;
				constraints.gridwidth = 1;
				constraints.gridheight = 1;
				constraints.fill = GridBagConstraints.CENTER;
				
				this.add(generico,constraints);
				
			}
			
		
	
		revalidate();
		

		
	}
	
	
}
