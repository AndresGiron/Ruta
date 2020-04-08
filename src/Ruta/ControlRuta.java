package Ruta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlRuta {
	
	List<JugadorRuta> jugadores = new ArrayList<JugadorRuta>();
	List<CartaRuta> ruta = new ArrayList<CartaRuta>();
	boolean estadoGanar = false;
	int numeroJugadores = 2; 
	
	public void crearCartas() {
		
		//102 , el numero de cartas de 100 es seis
		
		for(int x = 0; x < 102; x++){
			
			char tipo = ' ';
			String id = "";
			
			CartaRuta generico = null;
			

			if (x < 4) 
			{
				//cartas 200
			 generico = new CartaRuta(tipo = 'D', id = "src/imagenes/200.png",200);
			}else if (x < 10){
				//cartas 100
			 generico = new CartaRuta(tipo = 'D', id = "src/imagenes/100.png",100);
			}else if (x < 22){
				//cartas 75
			 generico = new CartaRuta(tipo = 'D', id = "src/imagenes/75.png",75);
			}else if (x < 32){
				//cartas 50
			 generico = new CartaRuta(tipo = 'D', id = "src/imagenes/200.png",50);
			}else if (x < 42){
				//cartas 25
			 generico = new CartaRuta(tipo = 'D', id = "src/imagenes/200.png",25);
			}else if (x < 45 ){
				//cartas sin gasolina
			 generico = new CartaRuta(tipo = 'P', id = "src/imagenes/sinGasolina.png",0);
			}else if (x < 48){
				//cartas pinchazos
			 generico = new CartaRuta(tipo = 'P', id = "src/imagenes/pinchazo.png",0);
			}else if (x < 51){
				//cartas accidentes
			 generico = new CartaRuta(tipo = 'P', id = "src/imagenes/accidente.png",0);
			}else if (x < 55){
				//cartas limite velocidad
			 generico = new CartaRuta(tipo = 'P', id = "src/imagenes/limite.png",0);
			}else if (x < 60){
				//cartas stop
			 generico = new CartaRuta(tipo = 'P', id = "src/imagenes/stop.png",0);
			}else if (x < 66){
				//cartas gasolina
			 generico = new CartaRuta(tipo = 'S', id = "src/imagenes/gasolina.png",0);
			}else if (x < 72){
				//cartas llanta repuestp
			 generico = new CartaRuta(tipo = 'S', id = "src/imagenes/ruedaDeCambio.png",0);
			}else if (x < 78){
				//cartas reparaciones
			 generico = new CartaRuta(tipo = 'S', id = "src/imagenes/reparaciones.png",0);
			}else if (x < 84){
				//cartas fin limite de velocidad
			 generico = new CartaRuta(tipo = 'S', id = "src/imagenes/velocidad.png",0);
			}else if (x < 98){
				//cartas siga
			 generico = new CartaRuta(tipo = 'S', id = "src/imagenes/libre.png",0);
			}
			

			
			
			switch(x) 
			{
			case 98 :
			 generico = new CartaRuta(tipo = 'G', id = "src/imagenes/asAlVolante.png",0);
				break;
			case 99 :
			 generico = new CartaRuta(tipo = 'G', id = "src/imagenes/cisterna.png",0);
				break;
			case 100:
			 generico = new CartaRuta(tipo = 'G', id = "src/imagenes/impinchable.png",0);
				break;
			case 101:
			 generico = new CartaRuta(tipo = 'G', id = "src/imagenes/prioritario.png",0);
				break;
			
			}
			
			ruta.add(generico);
			
			
				
		}
		
		Collections.shuffle(ruta);
		
	}
	
	
	public void repatir(int x) {
		
		numeroJugadores = x;
		
		for(int y = 0; y <numeroJugadores; y++ ){
			
			JugadorRuta generico = new JugadorRuta();
			
			for(int z = 0; z < 7; z++) 
			{
				generico.getMano().add(ruta.get(0));
				ruta.remove(0);
			}
			
		}
		
		}
		
	}
	
	
	
	
	
	
	


