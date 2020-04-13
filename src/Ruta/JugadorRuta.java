package Ruta;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JugadorRuta extends JPanel{
	
	private JLabel avatar = new JLabel();
	private List<CartaRuta> mano = new ArrayList<CartaRuta>();
	private List<CartaRuta> mesa = new ArrayList<CartaRuta>();
	private boolean estadoLimite = false;
	private boolean estadoAccidente = false;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JTextField puntajeTexto = new JTextField();
	private JTextField jugadorNombre = new JTextField();
	int puntaje = 0;
	private boolean ganar = false;
	private boolean estadoSemaforo = true;
	private boolean estadoPinchazo = false; 
	
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
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public void jugar() {
		
	}
	
	public void configuracion() {
	
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
		constraints.fill = GridBagConstraints.BOTH;
		
		this.add(mano.get(x),constraints);
		
	}
	
	constraints.gridx = 0;
	constraints.gridy = 1;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.fill = GridBagConstraints.BOTH;
	
	puntajeTexto.setText("0000KM");
	puntajeTexto.setEditable(false);
	puntajeTexto.setPreferredSize(new Dimension(50,50));
	puntajeTexto.setFont(new Font("ComicSans", 0, 12));
	
	this.add(puntajeTexto,constraints);
	
	constraints.gridx = 0;
	constraints.gridy = 2;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.fill = GridBagConstraints.BOTH;
	
	jugadorNombre.setText("Jugador");
	jugadorNombre.setEditable(false);
	jugadorNombre.setPreferredSize(new Dimension(50,50));
	jugadorNombre.setFont(new Font("ComicSans", 0, 12));
	
	this.add(jugadorNombre,constraints);
	
	for(int y = 1; y<7; y++) 
	{
		switch(y) 
		{
		case 1:
			JLabel generico = new JLabel();
			generico.setIcon(new ImageIcon("src/imagenes/distanciaEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			
			this.add(generico,constraints);
			
			break;
		case 2:
			JLabel generico1 = new JLabel();
			generico1.setIcon(new ImageIcon("src/imagenes/semaforoDistancia.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			
			this.add(generico1,constraints);
			
			break;
		case 3:
			JLabel generico11 = new JLabel();
			generico11.setIcon(new ImageIcon("src/imagenes/pinchazoEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			
			this.add(generico11,constraints);
			break;
		case 4:
			JLabel generico111 = new JLabel();
			generico111.setIcon(new ImageIcon("src/imagenes/choquesEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			
			this.add(generico111,constraints);
			break;
		case 5:
			JLabel generico2 = new JLabel();
			generico2.setIcon(new ImageIcon("src/imagenes/limitesEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			
			this.add(generico2,constraints);
			break;
		case 6:
			JLabel generico21 = new JLabel();
			generico21.setIcon(new ImageIcon("src/imagenes/gasolinaEspacio.png"));
			
			constraints.gridx = y;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.fill = GridBagConstraints.BOTH;
			
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
		constraints.fill = GridBagConstraints.BOTH;
		
		this.add(generico,constraints);
		
	}
	
	
	
	
		
	}
	
	public void verificarMales() {
		
	}
	
}
