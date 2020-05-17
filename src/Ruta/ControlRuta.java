package Ruta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ControlRuta {
	
	List<JugadorRuta> jugadores = new ArrayList<JugadorRuta>();
	List<CartaRuta> ruta = new ArrayList<CartaRuta>();
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
			 generico = new CartaRuta(tipo = 'D', id = "src/imagenes/50.png",50);
			}else if (x < 42){
				//cartas 25
			 generico = new CartaRuta(tipo = 'D', id = "src/imagenes/25.png",25);
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
			 generico = new CartaRuta(tipo = 'G', id = "src/imagenes/asAlVolante.png",100);
				break;
			case 99 :
			 generico = new CartaRuta(tipo = 'G', id = "src/imagenes/cisterna.png",100);
				break;
			case 100:
			 generico = new CartaRuta(tipo = 'G', id = "src/imagenes/impinchable.png",100);
				break;
			case 101:
			 generico = new CartaRuta(tipo = 'G', id = "src/imagenes/prioritario.png",100);
				break;
			
			}
			
			ruta.add(generico);
			
			
				
		}
		
		Collections.shuffle(ruta);
		
	}
	
	
	public void repartir(int x) {
		
		numeroJugadores = x;
		
		for(int y = 0; y <numeroJugadores; y++ ){
			
			JugadorRuta jugadorGenerico = new JugadorRuta();
			List<CartaRuta> manoGenerica = new ArrayList<CartaRuta>();
			
			if (y>=1) {
			
				JLabel avatarGenerico = new JLabel();
				
				avatarGenerico.setIcon(new ImageIcon("src/imagenes/"+y+".jpg"));
				
				jugadorGenerico.setAvatar(avatarGenerico);
				
			}
			
			
			
			for(int z = 0; z < 6; z++) 
			{
				manoGenerica.add(ruta.get(0));
				ruta.remove(0);
			}
			
			
			
			jugadorGenerico.setMano(manoGenerica);
			
			jugadores.add(jugadorGenerico);
			
		}
		
		}
	
	public boolean jugarAutomatico(int q) {
		boolean verificar = false;
		int contador = 0;
		
		for(int x = 0;x < jugadores.size(); x++) {
			
			if (quitaPeligros(x) == true) {
				
				try {
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"Puso Seguridad");
				System.out.print("Tamaño baraja" + jugadores.get(x).getMano().size());
				continue;
				
				}
		        catch(Exception e) {
		        	System.out.print("sin cartas u.u");
					continue;
		        }
			}
			
			if (cartaDistancia(x) == true) {
				
				try {
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"Puso Seguridad");
				System.out.print("Tamaño baraja" + jugadores.get(x).getMano().size());
				
				continue;

				}
		        catch(Exception e) {
		        	System.out.print("sin cartas u.u");
					continue;
		        }
			}
			
			if (agregaPeligros(x) == true) {
				
				try {
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"Puso Seguridad");
				System.out.print("Tamaño baraja" + jugadores.get(x).getMano().size());
				
				continue;

				}
		        catch(Exception e) {
		        	System.out.print("sin cartas u.u");
					continue;
		        }
			}
		
			if (agregaSeguros(x)==true) {
				
				try {
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"Puso Seguridad");
				System.out.print("Tamaño baraja" + jugadores.get(x).getMano().size());
				continue;

				}
		        catch(Exception e) {
		        	System.out.print("sin cartas u.u");
					continue;
		        }
				
			}
			
			try {
				
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				genericoMano.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"comio");
				
				continue;

	        	
			}
	        catch(Exception e) {
	        		
	        	jugadores.get(x).setJugadorActivado(false);
	        	System.out.print("sin cartas u.u");
	        	continue;
	        	
	        }
			
		}
		
		for(int w = 0; w < jugadores.size(); w++) {
			
			if (jugadores.get(w).getJugadorActivado() == false) {
				contador++;
			}
			
		}
		
		if (contador == jugadores.size()) {
			
			verificar = true;
		}
		
		
		
		
		return verificar;
	}
	

	
	
	public void jugarAutomatico() {
		
		for(int x = 1;x < jugadores.size(); x++) {
			
			if (quitaPeligros(x) == true) {
				
				try {
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"Puso Seguridad");
				System.out.print("Tamaño baraja" + jugadores.get(x).getMano().size());
				continue;
				
				}
		        catch(Exception e) {
		        	System.out.print("sin cartas u.u");
					continue;
		        }
			}
			
			if (cartaDistancia(x) == true) {
				
				try {
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"Puso Seguridad");
				System.out.print("Tamaño baraja" + jugadores.get(x).getMano().size());
				
				continue;

				}
		        catch(Exception e) {
		        	System.out.print("sin cartas u.u");
					continue;
		        }
			}
			
			if (agregaPeligros(x) == true) {
				
				try {
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"Puso Seguridad");
				System.out.print("Tamaño baraja" + jugadores.get(x).getMano().size());
				
				continue;

				}
		        catch(Exception e) {
		        	System.out.print("sin cartas u.u");
					continue;
		        }
			}
		
			if (agregaSeguros(x)==true) {
				
				try {
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"Puso Seguridad");
				System.out.print("Tamaño baraja" + jugadores.get(x).getMano().size());
				continue;

				}
		        catch(Exception e) {
		        	System.out.print("sin cartas u.u");
					continue;
		        }
				
			}
			
			try {
				
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				
				genericoMano.add(ruta.get(0));
				
				ruta.remove(0);
				
				genericoMano.remove(0);
				
				jugadores.get(x).setMano(genericoMano);
				
				jugadores.get(x).redibujar();
				
				System.out.print(x+"comio");
				
				continue;

	        	
			}
	        catch(Exception e) {
	        		
	        	jugadores.get(x).setJugadorActivado(false);
	        	System.out.print("sin cartas u.u");
	        	continue;
	        	
	        }
			
		}
	}



	private boolean agregaSeguros(int x) {
		// TODO Auto-generated method stub
		boolean verificar = false;
		
		for(int y = 0;y < jugadores.get(x).getMano().size(); y++) {
			
			if (jugadores.get(x).getMano().get(y).getTipo() == 'G') {
				
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
				
				genericoMano = jugadores.get(x).getMano();
				genericoMesa = jugadores.get(x).getMesa();
				
				genericoMesa.add(genericoMano.get(y));
				genericoMano.remove(y);
				
				jugadores.get(x).setMano(genericoMano);
				jugadores.get(x).setMesa(genericoMesa);
				
				verificar = true;
				break;
			}
			
		}
		
		
		
		return verificar;
	}


	private boolean quitaPeligros(int x) {
		// TODO Auto-generated method stub
		boolean verificar = false;
		
		System.out.print(jugadores.get(x).getMano().size());
		
		for(int w = 0; w < jugadores.get(x).getMano().size(); w++) 
		{
			
			for(int y = 0; y < jugadores.get(x).getMesa().size(); y++) {
				
				System.out.print(jugadores.get(x).getMesa().size());
				//System.out.print("Aqui funciona");
				
				
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
				List<CartaRuta> genericoPeligro  = new ArrayList<CartaRuta>();
				
				String idGenerico = jugadores.get(x).getMano().get(w).getId();
				
				if ((idGenerico.equals("src/imagenes/gasolina.png") || idGenerico.equals("src/imagenes/cisterna.png")) && jugadores.get(x).getMesa().get(y).getId().equals("src/imagenes/sinGasolina.png")) 
				{
					genericoMano = jugadores.get(x).getMano();
					genericoMesa = jugadores.get(x).getMesa();
					genericoPeligro = jugadores.get(x).getGasolina();
					
					if (idGenerico.equals("src/imagenes/cisterna.png")) {
						
						genericoMano.get(w).setValor(300);
						
						genericoMesa.add(genericoMano.get(w));
						jugadores.get(x).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/gasolina.png",0));
						genericoMano.remove(w);
						genericoPeligro.clear();
						
						for(int i = 0; i < jugadores.get(x).getMesa().size(); i ++) {
							
							if (jugadores.get(x).getMesa().get(i).getId().equals("src/imagenes/sinGasolina.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(w));
							
						}
						
						genericoMano.remove(w);
						genericoMesa.remove(y);
						
					}
					

					
					
					
					jugadores.get(x).setMano(genericoMano);			
					jugadores.get(x).setMesa(genericoMesa);
					jugadores.get(x).setGasolina(genericoPeligro);

					
					verificar = true;
					//jugadores.get(x).redibujar();
					break;
					
					
					
				}
				if ((idGenerico.equals("src/imagenes/impinchable.png")|| idGenerico.equals("src/imagenes/ruedaDeCambio.png"))   && jugadores.get(x).getMesa().get(y).getId().equals("src/imagenes/pinchazo.png")) 
				{
					genericoMano = jugadores.get(x).getMano();
					genericoMesa = jugadores.get(x).getMesa();
					genericoPeligro = jugadores.get(x).getPinchazo();
					
					if (idGenerico.equals("src/imagenes/impinchable.png")) {
						
						genericoMano.get(w).setValor(300);
						
						
						genericoMesa.add(genericoMano.get(w));
						jugadores.get(x).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/ruedaDeCambio.png",0));
						genericoMano.remove(w);
						genericoPeligro.clear();
						
						for(int i = 0; i < jugadores.get(x).getMesa().size(); i ++) {
							
							if (jugadores.get(x).getMesa().get(i).getId().equals("src/imagenes/pinchazo.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(w));
							
						}
						
						genericoMano.remove(w);
						genericoMesa.remove(y);
						
					}
				
					jugadores.get(x).setMano(genericoMano);			
					jugadores.get(x).setMesa(genericoMesa);
					jugadores.get(x).setPinchazo(genericoPeligro);

					
					verificar = true;
					//jugadores.get(x).redibujar();
					break;
				}
				if ((idGenerico.equals("src/imagenes/asAlVolante.png") || idGenerico.equals("src/imagenes/reparaciones.png")) && jugadores.get(x).getMesa().get(y).getId().equals("src/imagenes/accidente.png")) 
				{
					genericoMano = jugadores.get(x).getMano();
					genericoMesa = jugadores.get(x).getMesa();
					genericoPeligro = jugadores.get(x).getAccidente();
					
					if (idGenerico.equals("src/imagenes/asAlVolante.png")) {
						
						genericoMano.get(w).setValor(300);
						
						
						genericoMesa.add(genericoMano.get(w));
						jugadores.get(x).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/reparaciones.png",0));
						genericoMano.remove(w);
						genericoPeligro.clear();
						
						for(int i = 0; i < jugadores.get(x).getMesa().size(); i ++) {
							
							if (jugadores.get(x).getMesa().get(i).getId().equals("src/imagenes/accidente.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(w));
							
						}
						
						genericoMano.remove(w);
						genericoMesa.remove(y);
						
					}
					

					
					
					
					jugadores.get(x).setMano(genericoMano);			
					jugadores.get(x).setMesa(genericoMesa);
					jugadores.get(x).setAccidente(genericoPeligro);

					
					verificar = true;
					//jugadores.get(x).redibujar();
					break;
					
				}
				if ((idGenerico.equals("src/imagenes/prioritario.png") || idGenerico.equals("src/imagenes/velocidad.png")) && jugadores.get(x).getMesa().get(y).getId().equals("src/imagenes/limite.png")) 
				{
					genericoMano = jugadores.get(x).getMano();
					genericoMesa = jugadores.get(x).getMesa();
					genericoPeligro = jugadores.get(x).getLimite();
					
					if (idGenerico.equals("src/imagenes/prioritario.png")) {
						
						genericoMano.get(w).setValor(300);
						
						
						genericoMesa.add(genericoMano.get(w));
						jugadores.get(x).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/velocidad.png",0));
						genericoMano.remove(w);
						genericoPeligro.clear();
						
						for(int i = 0; i < jugadores.get(x).getMesa().size(); i ++) {
							
							if (jugadores.get(x).getMesa().get(i).getId().equals("src/imagenes/limite.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(w));
							
						}
						
						genericoMano.remove(w);
						genericoMesa.remove(y);
						
					}
					
					
					jugadores.get(x).setMano(genericoMano);			
					jugadores.get(x).setMesa(genericoMesa);
					jugadores.get(x).setLimite(genericoPeligro);

					
					verificar = true;
					//jugadores.get(x).redibujar();
					break;
					
				}
				if (( idGenerico.equals("src/imagenes/prioritario.png") || idGenerico.equals("src/imagenes/libre.png")) && jugadores.get(x).getMesa().get(y).getId().equals("src/imagenes/stop.png")) 
				{
					genericoMano = jugadores.get(x).getMano();
					genericoMesa = jugadores.get(x).getMesa();
					genericoPeligro = jugadores.get(x).getSemaforo();
					
					if (idGenerico.equals("src/imagenes/prioritario.png")) {
						
						genericoMano.get(w).setValor(300);
						
						
						genericoMesa.add(genericoMano.get(w));
						jugadores.get(x).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/libre.png",0));
						genericoMano.remove(w);
						genericoPeligro.clear();
						
						for(int i = 0; i < jugadores.get(x).getMesa().size(); i ++) {
							
							if (jugadores.get(x).getMesa().get(i).getId().equals("src/imagenes/stop.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(w));
							
						}
						
						genericoMano.remove(w);
						genericoMesa.remove(y);
						
					}
					
					
					jugadores.get(x).setMano(genericoMano);			
					jugadores.get(x).setMesa(genericoMesa);
					jugadores.get(x).setSemaforo(genericoPeligro);

					
					verificar = true;
					//jugadores.get(x).redibujar();
					break;
					
				}
			
			}
			
			if (verificar == true) {
				
				break;
			}
			
			
		}
		

		
		return verificar;
	}
	
	private boolean cartaDistancia(int x) {
		// TODO Auto-generated method stub
		boolean verificar = false;
		int contadorLimites = 0;
		int contador = 0; 
		
		for (int w = 0; w < jugadores.get(x).getMesa().size(); w++) {
			
			
			if (jugadores.get(x).getMesa().get(w).getTipo() == 'P' && !(jugadores.get(x).getMesa().get(w).getId().equals("src/imagenes/limite.png"))) {
				
				contador++;
				
			}
			
		}
		
		if (contador == 0) {
			
			for (int w = 0; w < jugadores.get(x).getMesa().size(); w++) {

			if (jugadores.get(x).getMesa().get(w).getTipo() == 'P' && jugadores.get(x).getMesa().get(w).getId().equals("src/imagenes/limite.png")) {
				
				contadorLimites++;
			}
		}
			}
		
		
		if (contador == 0 && contadorLimites > 0) {
			
			for(int y = 0; y < jugadores.get(x).getMano().size(); y++) {
				
				if (jugadores.get(x).getMano().get(y).getTipo() == 'D'&& jugadores.get(x).getMano().get(y).getValor() <= 50)  {
					
					List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
					List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
					List<CartaRuta> genericoDistancia  = new ArrayList<CartaRuta>();
					
					genericoMano = jugadores.get(x).getMano();
					genericoMesa = jugadores.get(x).getMesa();
					genericoDistancia = jugadores.get(x).getDistancia();
					
					genericoMesa.add(genericoMano.get(y));
					genericoDistancia.add(genericoMano.get(y));
					genericoMano.remove(y);
					
					jugadores.get(x).setMano(genericoMano);
					jugadores.get(x).setMesa(genericoMesa);
					jugadores.get(x).setDistancia(genericoDistancia);
					
					
					//jugadores.get(x).redibujar();
					verificar = true;
					break;
				}
				
			}
		}
		
		
		if (contador == 0 && contadorLimites == 0) {
			
			for(int y = 0; y < jugadores.get(x).getMano().size(); y++) {
				
				if (jugadores.get(x).getMano().get(y).getTipo() == 'D')  {
					
					List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
					List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
					List<CartaRuta> genericoDistancia  = new ArrayList<CartaRuta>();
					
					genericoMano = jugadores.get(x).getMano();
					genericoMesa = jugadores.get(x).getMesa();
					genericoDistancia = jugadores.get(x).getDistancia();
					
					genericoMesa.add(genericoMano.get(y));
					genericoDistancia.add(genericoMano.get(y));
					genericoMano.remove(y);
					
					jugadores.get(x).setMano(genericoMano);
					jugadores.get(x).setMesa(genericoMesa);
					jugadores.get(x).setDistancia(genericoDistancia);
					//jugadores.get(x).redibujar();
					verificar = true;
					break;
				}
			
		}
			}
		
		return verificar;
	}

	private boolean agregaPeligros(int x){
		// TODO Auto-generated method stub
		boolean verificar = false;
		int jugadorAtacado;
		
		

		do {
			
			Random r = new Random(System.currentTimeMillis());
			jugadorAtacado = r.nextInt(jugadores.size());
			
		}
		while(x == jugadorAtacado);
		
		
		boolean cisterna = false;
		boolean prioritario = false;
		boolean asDelVolante = false;
		boolean impinchable = false;
		
		
		List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
		List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
		List<CartaRuta> genericoPeligros  = new ArrayList<CartaRuta>();
		
		genericoMano = jugadores.get(x).getMano();
		genericoMesa = jugadores.get(jugadorAtacado).getMesa();
		
		
		for (int w = 0; w < jugadores.get(jugadorAtacado).getMesa().size(); w++) {
			
			if (jugadores.get(jugadorAtacado).getMesa().get(w).getId().equals("src/imagenes/cisterna.png")){
				cisterna = true;
				System.out.print("Entre :v");
				break;
			}
			
		}
		
		for (int w = 0; w < jugadores.get(jugadorAtacado).getMesa().size(); w++) {

			if (jugadores.get(jugadorAtacado).getMesa().get(w).getId().equals("src/imagenes/prioritario.png")){
				prioritario = true;
				System.out.print("Entre :v");
				break;
			}
			
		}
		
		for (int w = 0; w < jugadores.get(jugadorAtacado).getMesa().size(); w++) {
			
			if (jugadores.get(jugadorAtacado).getMesa().get(w).getId().equals("src/imagenes/asAlVolante.png")){
				asDelVolante = true;
				System.out.print("Entre :v");
				break;
			}
			
		}
		
		for (int w = 0; w < jugadores.get(jugadorAtacado).getMesa().size(); w++) {
			
			if (jugadores.get(jugadorAtacado).getMesa().get(w).getId().equals("src/imagenes/impinchable.png")){
				impinchable = true;
				System.out.print("Entre :v");
				break;
			}
			
		}
		

		
		
		
		
		
		
		for (int y = 0; y < genericoMano.size(); y++){
			
			if (jugadores.get(x).getMano().get(y).getTipo() == 'P') {
				
				if(jugadores.get(x).getMano().get(y).getId().equals("src/imagenes/sinGasolina.png") && cisterna == false){
					
					genericoPeligros = jugadores.get(jugadorAtacado).getGasolina();
					genericoPeligros.add(genericoMano.get(y));
					jugadores.get(jugadorAtacado).setGasolina(genericoPeligros);
					
					genericoMesa.add(genericoMano.get(y));
					genericoMano.remove(y);
					
					jugadores.get(x).setMano(genericoMano);
					jugadores.get(jugadorAtacado).setMesa(genericoMesa);
					
					
					//jugadores.get(x).redibujar();
					jugadores.get(jugadorAtacado).redibujar();
					
					verificar = true;
					break;
					
				}else if(jugadores.get(x).getMano().get(y).getId().equals("src/imagenes/accidente.png") && asDelVolante == false) {
					
					genericoPeligros = jugadores.get(jugadorAtacado).getAccidente();
					genericoPeligros.add(genericoMano.get(y));
					jugadores.get(jugadorAtacado).setAccidente(genericoPeligros);
					
					genericoMesa.add(genericoMano.get(y));
					genericoMano.remove(y);
					
					jugadores.get(x).setMano(genericoMano);
					jugadores.get(jugadorAtacado).setMesa(genericoMesa);
					
					
					//jugadores.get(x).redibujar();
					jugadores.get(jugadorAtacado).redibujar();
					
					verificar = true;
					break;
					
				}else if(jugadores.get(x).getMano().get(y).getId().equals("src/imagenes/limite.png") && prioritario == false) {
					
					genericoPeligros = jugadores.get(jugadorAtacado).getLimite();
					genericoPeligros.add(genericoMano.get(y));
					jugadores.get(jugadorAtacado).setLimite(genericoPeligros);
					
					genericoMesa.add(genericoMano.get(y));
					genericoMano.remove(y);
					
					jugadores.get(x).setMano(genericoMano);
					jugadores.get(jugadorAtacado).setMesa(genericoMesa);
					
					
					//jugadores.get(x).redibujar();
					jugadores.get(jugadorAtacado).redibujar();
					
					verificar = true;
					break;
					
				}else if(jugadores.get(x).getMano().get(y).getId().equals("src/imagenes/pinchazo.png") && impinchable == false)  {
					
					genericoPeligros = jugadores.get(jugadorAtacado).getPinchazo();
					genericoPeligros.add(genericoMano.get(y));
					jugadores.get(jugadorAtacado).setPinchazo(genericoPeligros);
					
					genericoMesa.add(genericoMano.get(y));
					genericoMano.remove(y);
					
					jugadores.get(x).setMano(genericoMano);
					jugadores.get(jugadorAtacado).setMesa(genericoMesa);
					
					
					//jugadores.get(x).redibujar();
					jugadores.get(jugadorAtacado).redibujar();
					
					verificar = true;
					break;
					
				}else if(jugadores.get(x).getMano().get(y).getId().equals("src/imagenes/stop.png") && prioritario == false) {
					
					genericoPeligros = jugadores.get(jugadorAtacado).getSemaforo();
					genericoPeligros.add(genericoMano.get(y));
					jugadores.get(jugadorAtacado).setSemaforo(genericoPeligros);
					
					genericoMesa.add(genericoMano.get(y));
					genericoMano.remove(y);
					
					jugadores.get(x).setMano(genericoMano);
					jugadores.get(jugadorAtacado).setMesa(genericoMesa);
					
					
					//jugadores.get(x).redibujar();
					jugadores.get(jugadorAtacado).redibujar();
					
					verificar = true;
					break;
				}else {
					verificar = false;
					}
				
				
			}
		}
		
		
		
		return verificar;
	}
	

}

	