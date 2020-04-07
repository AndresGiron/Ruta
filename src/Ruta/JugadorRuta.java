package Ruta;

import java.util.ArrayList;
import java.util.List;

public class JugadorRuta {
	
	private List<CartaRuta> mano = new ArrayList<CartaRuta>();
	private List<CartaRuta> mesa = new ArrayList<CartaRuta>();
	private boolean estadoSemaforo = true;
	private boolean estadoPinchazo = false; 
	private boolean estadoLimite = false;
	private boolean estadoAccidente = false;
	int puntaje = 0;
	private boolean ganar = false;
	
	
	JugadorRuta(List<CartaRuta> genericoMano){
		
		mano = genericoMano;
	}
	
	public void jugar() {
		
	}
	
	public void verificarMales() {
		
	}
	
}
