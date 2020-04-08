package Ruta;

import java.util.ArrayList;
import java.util.List;

public class JugadorRuta {
	
	private List<CartaRuta> mano = new ArrayList<CartaRuta>();
	private List<CartaRuta> mesa = new ArrayList<CartaRuta>();
	private boolean estadoLimite = false;
	private boolean estadoAccidente = false;
	int puntaje = 0;
	private boolean ganar = false;
	private boolean estadoSemaforo = true;
	private boolean estadoPinchazo = false; 
	
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
	
	public void verificarMales() {
		
	}
	
}
