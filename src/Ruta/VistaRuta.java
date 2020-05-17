package Ruta;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
 

public class VistaRuta extends JFrame {
	
	private Integer[] opciones = {2, 3, 4, 5, 6};
	private String[] genero = {"hombre", "mujer"}; 
	private JPanel juego = new JPanel();
	private ControlRuta controlador = new ControlRuta();
	private int cantidadJugadores = 2;
	private String sexo = "";
	private JPanel norte = new JPanel();
	private JLabel avatar = new JLabel();
	private Listener listener = new Listener();
	private JButton comer = new JButton();
	private JButton parar = new JButton();
	private boolean tuTurno = true;
	private JPanel centro = new JPanel();
	private boolean reiniciando = false;
	private FileReader fileRead;
	private BufferedReader input;
	private FileWriter fileWriter;
	private BufferedWriter output;
	
	public VistaRuta()
	{
		iniciarVista();

		this.setTitle("Ruta");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void iniciarVista(){
		
		
		Container container = this.getContentPane();
		ImageIcon icono = new ImageIcon("src/imagenes/selector.jpg");
		
		int input = JOptionPane.showConfirmDialog(null,
                "¿Deseas cargar partida?", "Elige una opcion",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/imagenesflechita.jpg"));
		
		
		
		
		if(input == 0) {
			
			String genericoPuntaje = "";
			int contador = 0; 
			
			for(int l = 0; l < cargar().length(); l++) {
				
				if(cargar().charAt(l) == '-') {
						
						cantidadJugadores = cargar().charAt(l+2) - '0';
						System.out.print(cantidadJugadores+"         ");
						controlador.crearCartas();
						controlador.repartir(cantidadJugadores);
					
				}
			}
			
			for(int l = 0; l < cargar().length(); l++){
				
				if (cargar().charAt(l) != ' ') {
					
					genericoPuntaje =  genericoPuntaje + cargar().charAt(l)+"";
					
				}else {
					
					System.out.print(genericoPuntaje);
					
					int gen = Integer.parseInt(genericoPuntaje);
					controlador.jugadores.get(contador).setPuntaje(gen);
					genericoPuntaje = "";
					contador++;
					
				}
				
				if (controlador.jugadores.size() == contador) {
					break;
				}
				
			}
			
			
			
			
		}else {
			

			
	   
	        try {
	           
	            cantidadJugadores = (Integer)JOptionPane.showInputDialog(null, "Seleccione numero de jugadores", 
	                    "Numero de jugadores", JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);
	            
	            controlador.crearCartas();
	            controlador.repartir(cantidadJugadores);
	            
	        	}
	        	catch(Exception e) {
	        	  cantidadJugadores = 2;
	        	  controlador.crearCartas();
	        	  controlador.repartir(cantidadJugadores);
	        	}
			
		}
		
        //ImageIcon icono2 = new ImageIcon("src/imagenes/selector.jpg");
        sexo = (String)JOptionPane.showInputDialog(null, "¿usted es?", 
                "genero", JOptionPane.QUESTION_MESSAGE, icono, genero, genero[0]);
        
        avatar.setIcon(new ImageIcon("src/imagenes/"+sexo+".jpg"));
        
        if (sexo == null) {
        	avatar.setIcon(new ImageIcon("src/imagenes/hombre.jpg"));
        }
        
        controlador.jugadores.get(0).setAvatar(avatar);
        controlador.jugadores.get(0).setReverso(true);
        
        
        
        
        
        
		
		acomodar();
		container.add(juego);
		
	}
	
	public void acomodar(){ 
        
        juego.setLayout(new BorderLayout());
        centro.setLayout(new FlowLayout());
        norte.setLayout(new FlowLayout());
        
        JScrollPane barritaNorte = new JScrollPane(norte);
        barritaNorte.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barritaNorte.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		barritaNorte.setBackground(Color.getColor("#0c683c"));
		barritaNorte.setPreferredSize(new Dimension(750,300));
		barritaNorte.getViewport().setBackground(Color.getColor("#0c683c"));
		barritaNorte.setBorder(null);
        
        juego.add(barritaNorte,BorderLayout.NORTH);

        for(int x = 0; x< controlador.jugadores.size(); x++) {
        	
        	
        	if (x==0){
        		
        		controlador.jugadores.get(x).setNombre("Tu");
        		controlador.jugadores.get(0).configuracion();
        		juego.add(controlador.jugadores.get(0),BorderLayout.SOUTH);

        		
        	}else {
        		
        		controlador.jugadores.get(x).setNombre("Jugador #"+x);
        		controlador.jugadores.get(x).configuracion();
        		norte.add(controlador.jugadores.get(x));	           	
        	}
        }
        
        comer.setBorder(null);
        comer.setIcon(new ImageIcon("src/imagenes/reverso.jpg"));

        centro.add(comer);
        
        juego.add(centro, BorderLayout.CENTER);
        
        asignarEscuchas();
	}
	
	public void asignarEscuchas(){
		
		for(int x = 0; x < controlador.jugadores.get(0).getMano().size(); x++ ) {
			
			controlador.jugadores.get(0).getMano().get(x).addActionListener(listener);
			
		}
		
		comer.addActionListener(listener);
		
	}
	
	public String cargar() {
		String texto="";
		try {
			fileRead = new FileReader("src/guardados/puntaje.txt");
			input = new BufferedReader(fileRead);
			
			String linea =input.readLine();
			while(linea!=null) {
				texto+=linea;
				texto+="\n";
				linea=input.readLine();
			}	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 return texto;
	}
	
	public void guardar(String linea) {
		try {
			fileWriter = new FileWriter("src/guardados/puntaje.txt",false);
			output = new BufferedWriter(fileWriter);			
			output.write(linea);
			output.newLine();	 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	
	
	public void turnosComputadora() {
		
		controlador.jugarAutomatico();
		
		revalidate();
		repaint();
		tuTurno = true;
		
	}
	
	
	public void comer() {
		
		try {
			
			List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
			
			genericoMano = controlador.jugadores.get(0).getMano();
			
			controlador.ruta.get(0).addActionListener(listener);
			
			genericoMano.add(controlador.ruta.get(0));
			
			controlador.ruta.remove(0);
			
			controlador.jugadores.get(0).setMano(genericoMano);
        	
		}
        catch(Exception e) {
        	
        	for(int x = 0;x < controlador.jugadores.size(); x++) {
        		
        		System.out.print(controlador.jugadores.get(x).getJugadorActivado());
        		
        	}
        	
        
        	
        	while(controlador.jugarAutomatico(0) == false) {
        		
        		System.out.print("juego en curso");
        		controlador.jugarAutomatico(0);
        		
        	}
        	
        	if (controlador.jugarAutomatico(0) == true) {
        		System.out.print("juego Finalizado");
        		reiniciar();
        		reiniciando = true;
        	}
        	
        }
		
	}
	
	public void jugar(int x) {
		
		char tipo =  controlador.jugadores.get(0).getMano().get(x).getTipo();
		
		
		
		if (tipo == 'D') {
			System.out.print("Hizo Distancia");
			
			int contadorLimites = 0;
			int contador = 0; 
			
			
			for (int w = 0; w < controlador.jugadores.get(0).getMesa().size(); w++) {
				
				
				if (controlador.jugadores.get(0).getMesa().get(w).getTipo() == 'P' && !(controlador.jugadores.get(0).getMesa().get(w).getId().equals("src/imagenes/limite.png"))) {
					
					contador++;
					
				}
				
			}
			
			if (contador == 0) {
				
				for (int w = 0; w < controlador.jugadores.get(0).getMesa().size(); w++) {

				if (controlador.jugadores.get(0).getMesa().get(w).getTipo() == 'P' && controlador.jugadores.get(0).getMesa().get(w).getId().equals("src/imagenes/limite.png")) {
					
					contadorLimites++;
				}
			}
				}
			
			if (contador == 0 && contadorLimites > 0) {
				
				if (controlador.jugadores.get(0).getMano().get(x).getValor() > 50) {
					
					JOptionPane.showMessageDialog(juego,"Tienes un limite de velocidad","Advertencia", 0, new ImageIcon("src/imagenes/limite.png"));
					tuTurno = true;
					
					
				}else {
					
					List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
					List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
					List<CartaRuta> genericoDistancia  = new ArrayList<CartaRuta>();
					
					genericoMesa = controlador.jugadores.get(0).getMesa();
					genericoMano = controlador.jugadores.get(0).getMano();
					genericoDistancia =controlador.jugadores.get(0).getDistancia();
					
					genericoMesa.add(controlador.jugadores.get(0).getMano().get(x));
					genericoDistancia.add(controlador.jugadores.get(0).getMano().get(x));
					genericoMano.remove(x);
					
					controlador.jugadores.get(0).setMesa(genericoMesa);
					controlador.jugadores.get(0).setMano(genericoMano);
					controlador.jugadores.get(0).setDistancia(genericoDistancia);
					
					comer();
				}
				
				
			}
			
			if (contador == 0 && contadorLimites == 0) {
				
				List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				List<CartaRuta> genericoDistancia  = new ArrayList<CartaRuta>();
				
				genericoMesa = controlador.jugadores.get(0).getMesa();
				genericoMano = controlador.jugadores.get(0).getMano();
				genericoDistancia =controlador.jugadores.get(0).getDistancia();
				
				genericoMesa.add(controlador.jugadores.get(0).getMano().get(x));
				genericoDistancia.add(controlador.jugadores.get(0).getMano().get(x));
				genericoMano.remove(x);
				
				controlador.jugadores.get(0).setMesa(genericoMesa);
				controlador.jugadores.get(0).setDistancia(genericoDistancia);
				controlador.jugadores.get(0).setMano(genericoMano);
				
				comer();
				
				
			}else {
				
				if (contador == 0 && contadorLimites > 0) {
					
				}else {
					
					JOptionPane.showMessageDialog(juego,"No puedes Jugar esta carta ahora mismo","Advertencia", 0, new ImageIcon("src/imagenes/paro.png"));
					tuTurno = true;
					
				} 
				
				
			}
			
			
		}else if (tipo == 'P') {
			System.out.print("Tiro peligro");
			
			Integer atacando;

			Integer[] adversarios = new Integer[controlador.jugadores.size()-1];
			
			for(int z = 0;z < controlador.jugadores.size()-1; z++ ) {
				
				adversarios[z] = z+1;
				
			}
			
			try {
		           
	            Integer atacando2 = (Integer)JOptionPane.showInputDialog(null, "Seleccione jugador para atacar", 
	                    "Atacando", JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/imagenes/reverso.jpg"),adversarios, adversarios[0]);
	            
	            atacando = atacando2;
	            
	        	
			}
	        	catch(Exception e) {
	        	  int atacando2 = 0;
	        	  
	        	  atacando = atacando2;

	        	}
			
			boolean cisterna = false;
			boolean prioritario = false;
			boolean asDelVolante = false;
			boolean impinchable = false;
			
			List<CartaRuta> genericoMesaAdversario  = new ArrayList<CartaRuta>();
			List<CartaRuta> genericoManoJugador  = new ArrayList<CartaRuta>();
			List<CartaRuta> genericoPeligros  = new ArrayList<CartaRuta>();
			
			genericoMesaAdversario =  controlador.jugadores.get(atacando).getMesa();
			genericoManoJugador = controlador.jugadores.get(0).getMano(); 
					
			for (int w = 0; w < genericoMesaAdversario.size(); w++) {
				
				if (genericoMesaAdversario.get(w).getId().equals("src/imagenes/cisterna.png")){
					cisterna = true;
				}
				
			}
			
			for (int w = 0; w <genericoMesaAdversario.size(); w++) {

				if (genericoMesaAdversario.get(w).getId().equals("src/imagenes/prioritario.png")){
					prioritario = true;
				}
				
			}
			
			for (int w = 0; w < genericoMesaAdversario.size(); w++) {
				
				if (genericoMesaAdversario.get(w).getId().equals("src/imagenes/asAlVolante.png")){
					asDelVolante = true;
				}
				
			}
			
			for (int w = 0; w < genericoMesaAdversario.size(); w++) {
				
				if (genericoMesaAdversario.get(w).getId().equals("src/imagenes/impinchable.png")){
					impinchable = true;
				}
				
			}
			
					
					if(controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/sinGasolina.png") && cisterna == false){
						
						genericoPeligros = controlador.jugadores.get(atacando).getGasolina();
						genericoPeligros.add(genericoManoJugador.get(x));
						controlador.jugadores.get(atacando).setGasolina(genericoPeligros);
						
						genericoMesaAdversario.add(genericoManoJugador.get(x));
						genericoManoJugador.remove(x);
						
						controlador.jugadores.get(0).setMano(genericoManoJugador);
						controlador.jugadores.get(atacando).setMesa(genericoMesaAdversario);
						
						comer();
						
					}else if(controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/accidente.png") && asDelVolante == false) {
						
						genericoPeligros = controlador.jugadores.get(atacando).getAccidente();
						genericoPeligros.add(genericoManoJugador.get(x));
						controlador.jugadores.get(atacando).setAccidente(genericoPeligros);
						
						genericoMesaAdversario.add(genericoManoJugador.get(x));
						genericoManoJugador.remove(x);
						
						controlador.jugadores.get(0).setMano(genericoManoJugador);
						controlador.jugadores.get(atacando).setMesa(genericoMesaAdversario);
						
						comer();
						
					}else if(controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/limite.png") && prioritario == false) {
						
						genericoPeligros = controlador.jugadores.get(atacando).getLimite();
						genericoPeligros.add(genericoManoJugador.get(x));
						controlador.jugadores.get(atacando).setLimite(genericoPeligros);
						
						genericoMesaAdversario.add(genericoManoJugador.get(x));
						genericoManoJugador.remove(x);
						
						controlador.jugadores.get(0).setMano(genericoManoJugador);
						controlador.jugadores.get(atacando).setMesa(genericoMesaAdversario);
						
						comer();
						
					}else if(controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/pinchazo.png") && impinchable == false)  {
						
						genericoPeligros = controlador.jugadores.get(atacando).getPinchazo();
						genericoPeligros.add(genericoManoJugador.get(x));
						controlador.jugadores.get(atacando).setPinchazo(genericoPeligros);
						
						genericoMesaAdversario.add(genericoManoJugador.get(x));
						genericoManoJugador.remove(x);
						
						controlador.jugadores.get(0).setMano(genericoManoJugador);
						controlador.jugadores.get(atacando).setMesa(genericoMesaAdversario);
						
						comer();
						
					}else if(controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/stop.png") && prioritario == false) {
						
						genericoPeligros = controlador.jugadores.get(atacando).getSemaforo();
						genericoPeligros.add(genericoManoJugador.get(x));
						controlador.jugadores.get(atacando).setSemaforo(genericoPeligros);
						
						genericoMesaAdversario.add(genericoManoJugador.get(x));
						genericoManoJugador.remove(x);
						
						controlador.jugadores.get(0).setMano(genericoManoJugador);
						controlador.jugadores.get(atacando).setMesa(genericoMesaAdversario);
						
						comer();
					}else{
						
						
						
						ImageIcon icono =  new ImageIcon();
						
						if (cisterna == true){
							
							icono =  new ImageIcon("src/imagenes/paro.png");
							
						}
						else if (prioritario == true) {
							
							icono =  new ImageIcon("src/imagenes/paro.png");
							
						}
						else if (asDelVolante == true){
							
							icono =  new ImageIcon("src/imagenes/paro.png");
							
						}
						else if (impinchable == true) {
							
							icono =  new ImageIcon("src/imagenes/paro.png");
							
						}
						
						JOptionPane.showMessageDialog(juego,"Este jugador tiene un poder","Advertencia", 0,icono);
						tuTurno = true;
						
					}
					

					
					
					//jugadores.get(x).redibujar();
					controlador.jugadores.get(atacando).redibujar();
					

					
				
			
			
			
		}else if (tipo == 'S' || tipo == 'G') {
			
			int contador = 0;
			
			System.out.print("Tiro salvacion");
				
				if(controlador.jugadores.get(0).getSemaforo().size() == 0 && controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/libre.png")){
					
					JOptionPane.showMessageDialog(juego,"No puedes Jugar esta carta ahora mismo","Advertencia", 0, new ImageIcon("src/imagenes/libre.png"));
					tuTurno = true;
					
				}else if(controlador.jugadores.get(0).getGasolina().size() == 0 && controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/gasolina.png")){
					
					JOptionPane.showMessageDialog(juego,"No puedes Jugar esta carta ahora mismo","Advertencia", 0, new ImageIcon("src/imagenes/gasolina.png"));
					tuTurno = true;
					
				}else if(controlador.jugadores.get(0).getPinchazo().size() == 0 && controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/ruedaDeCambio.png")){
					
					JOptionPane.showMessageDialog(juego,"No puedes Jugar esta carta ahora mismo","Advertencia", 0, new ImageIcon("src/imagenes/ruedaDeCambio.png"));
					tuTurno = true;
					
				}else if(controlador.jugadores.get(0).getAccidente().size() == 0 && controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/reparaciones.png")){
					
					JOptionPane.showMessageDialog(juego,"No puedes Jugar esta carta ahora mismo","Advertencia", 0, new ImageIcon("src/imagenes/reparaciones.png"));
					tuTurno = true;
					
				}else if(controlador.jugadores.get(0).getLimite().size() == 0 && controlador.jugadores.get(0).getMano().get(x).getId().equals("src/imagenes/velocidad.png")){
					
					JOptionPane.showMessageDialog(juego,"No puedes Jugar esta carta ahora mismo","Advertencia", 0, new ImageIcon("src/imagenes/velocidad.png"));
					tuTurno = true;
					
				}else {
					
				
			
			

			
			String idGenerico = controlador.jugadores.get(0).getMano().get(x).getId();		
			
			for (int w = 0; w < controlador.jugadores.get(0).getMesa().size();w++){
				
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
				List<CartaRuta> genericoPeligro  = new ArrayList<CartaRuta>();
				
				if ((idGenerico.equals("src/imagenes/gasolina.png") || idGenerico.equals("src/imagenes/cisterna.png")) && controlador.jugadores.get(0).getMesa().get(w).getId().equals("src/imagenes/sinGasolina.png")) 
				{
					genericoMano = controlador.jugadores.get(0).getMano();
					genericoMesa = controlador.jugadores.get(0).getMesa();
					genericoPeligro = controlador.jugadores.get(0).getGasolina();
					
					if (idGenerico.equals("src/imagenes/cisterna.png")) {
						
						genericoMano.get(x).setValor(300);
						
						genericoMesa.add(genericoMano.get(x));
						controlador.jugadores.get(0).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/gasolina.png",0));
						genericoMano.remove(x);
						genericoPeligro.clear();
						
						for(int i = 0; i < controlador.jugadores.get(0).getMesa().size(); i ++) {
							
							if (controlador.jugadores.get(0).getMesa().get(i).getId().equals("src/imagenes/sinGasolina.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(x));
							
						}
						
						genericoMano.remove(x);
						genericoMesa.remove(w);
						
					}
					

					
					
					
					controlador.jugadores.get(0).setMano(genericoMano);			
					controlador.jugadores.get(0).setMesa(genericoMesa);
					controlador.jugadores.get(0).setGasolina(genericoPeligro);
					
					comer();
					contador++;

					//jugadores.get(x).redibujar();
					break;
				}
				
				if ((idGenerico.equals("src/imagenes/ruedaDeCambio.png") || idGenerico.equals("src/imagenes/impinchable.png")) && controlador.jugadores.get(0).getMesa().get(w).getId().equals("src/imagenes/pinchazo.png")) 
				{
					genericoMano = controlador.jugadores.get(0).getMano();
					genericoMesa = controlador.jugadores.get(0).getMesa();
					genericoPeligro = controlador.jugadores.get(0).getPinchazo();
					
					if (idGenerico.equals("src/imagenes/impinchable.png")) {
						
						genericoMano.get(x).setValor(300);
						
						genericoMesa.add(genericoMano.get(x));
						controlador.jugadores.get(0).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/ruedaDeCambio.png",0));
						genericoMano.remove(x);
						genericoPeligro.clear();
						
						for(int i = 0; i < controlador.jugadores.get(0).getMesa().size(); i ++) {
							
							if (controlador.jugadores.get(0).getMesa().get(i).getId().equals("src/imagenes/pinchazo.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(x));
							
						}
						
						genericoMano.remove(x);
						genericoMesa.remove(w);
						
					}
				
					controlador.jugadores.get(0).setMano(genericoMano);			
					controlador.jugadores.get(0).setMesa(genericoMesa);
					controlador.jugadores.get(0).setPinchazo(genericoPeligro);
					
					comer();
					contador++;

					//jugadores.get(x).redibujar();
					break;
					
					
				}
				
				if ((idGenerico.equals("src/imagenes/reparaciones.png") || idGenerico.equals("src/imagenes/asAlVolante.png")) && controlador.jugadores.get(0).getMesa().get(w).getId().equals("src/imagenes/accidente.png")) 
				{
					genericoMano = controlador.jugadores.get(0).getMano();
					genericoMesa = controlador.jugadores.get(0).getMesa();
					genericoPeligro = controlador.jugadores.get(0).getAccidente();
					
					if (idGenerico.equals("src/imagenes/asAlVolante.png")) {
						
						genericoMano.get(x).setValor(300);
						
						genericoMesa.add(genericoMano.get(x));
						controlador.jugadores.get(0).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/reparaciones.png",0));
						genericoMano.remove(x);
						genericoPeligro.clear();
						
						for(int i = 0; i < controlador.jugadores.get(0).getMesa().size(); i ++) {
							
							if (controlador.jugadores.get(0).getMesa().get(i).getId().equals("src/imagenes/accidente.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(x));
							
						}
						
						genericoMano.remove(x);
						genericoMesa.remove(w);
						
					}
				
					controlador.jugadores.get(0).setMano(genericoMano);			
					controlador.jugadores.get(0).setMesa(genericoMesa);
					controlador.jugadores.get(0).setAccidente(genericoPeligro);
					
					comer();
					contador++;

					//jugadores.get(x).redibujar();
					break;
					
				}
				
				if ((idGenerico.equals("src/imagenes/velocidad.png") || idGenerico.equals("src/imagenes/prioritario.png") ) && controlador.jugadores.get(0).getMesa().get(w).getId().equals("src/imagenes/limite.png")) 
				{
					genericoMano = controlador.jugadores.get(0).getMano();
					genericoMesa = controlador.jugadores.get(0).getMesa();
					genericoPeligro = controlador.jugadores.get(0).getLimite();
					
					if (idGenerico.equals("src/imagenes/prioritario.png")) {
						
						genericoMano.get(x).setValor(300);
						
						genericoMesa.add(genericoMano.get(x));
						controlador.jugadores.get(0).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/velocidad.png",0));
						genericoMano.remove(x);
						genericoPeligro.clear();
						
						for(int i = 0; i < controlador.jugadores.get(0).getMesa().size(); i ++) {
							
							if (controlador.jugadores.get(0).getMesa().get(i).getId().equals("src/imagenes/limite.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(x));
							
						}
						
						genericoMano.remove(x);
						genericoMesa.remove(w);
						
					}
				
					controlador.jugadores.get(0).setMano(genericoMano);			
					controlador.jugadores.get(0).setMesa(genericoMesa);
					controlador.jugadores.get(0).setLimite(genericoPeligro);
					
					comer();
					contador++;

					//jugadores.get(x).redibujar();
					break;
					
				}
				
				if ((idGenerico.equals("src/imagenes/libre.png") || idGenerico.equals("src/imagenes/prioritario.png")) && controlador.jugadores.get(0).getMesa().get(w).getId().equals("src/imagenes/stop.png")) 
				{
					genericoMano = controlador.jugadores.get(0).getMano();
					genericoMesa = controlador.jugadores.get(0).getMesa();
					genericoPeligro = controlador.jugadores.get(0).getSemaforo();
					
					if (idGenerico.equals("src/imagenes/prioritario.png")) {
						
						genericoMano.get(x).setValor(300);
						
						genericoMesa.add(genericoMano.get(x));
						controlador.jugadores.get(0).redibujar();
						genericoMesa.add(new CartaRuta('S',"src/imagenes/libre.png",0));
						genericoMano.remove(x);
						genericoPeligro.clear();
						
						for(int i = 0; i < controlador.jugadores.get(0).getMesa().size(); i ++) {
							
							if (controlador.jugadores.get(0).getMesa().get(i).getId().equals("src/imagenes/stop.png")) {
								
								genericoMesa.remove(i);
								
							}
						}
						
					}else {
						
						genericoPeligro.remove(0);
						
						if (genericoPeligro.size() == 0) {
						
							genericoMesa.add(genericoMano.get(x));
							
						}
						
						genericoMano.remove(x);
						genericoMesa.remove(w);
						
					}
				
					controlador.jugadores.get(0).setMano(genericoMano);			
					controlador.jugadores.get(0).setMesa(genericoMesa);
					controlador.jugadores.get(0).setSemaforo(genericoPeligro);
					
					comer();
					contador++;

					//jugadores.get(x).redibujar();
					break;
					
					
				}
				
			}
			


			
			if (tipo == 'G' && contador == 0) {
				
				List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
				List<CartaRuta> genericoMesa  = new ArrayList<CartaRuta>();
				
				
				genericoMesa = controlador.jugadores.get(0).getMesa();
				genericoMano = controlador.jugadores.get(0).getMano();
				
				
				
				genericoMesa.add(genericoMano.get(x));
				genericoMano.remove(x); 
				
				
				controlador.jugadores.get(0).setMano(genericoMano);
				controlador.jugadores.get(0).setMesa(genericoMesa);
				
				
				comer();
			}		
		}
				}
	}
	
	private void reiniciar() {
		
		controlador.ruta.clear();
		controlador.crearCartas();
		
		for (int z = 0; z < controlador.jugadores.size(); z++){
			
			controlador.jugadores.get(z).reiniciar();
			
		}
		
		String puntajes="";
		
		for(int k = 0; k < controlador.jugadores.size();k++){
			
			puntajes = puntajes + controlador.jugadores.get(k).getPuntaje()+ " ";
			
		}
		
		puntajes = puntajes+"- "+ controlador.jugadores.size();
		
		guardar(puntajes);
		
		System.out.print(puntajes);
		
		
		int input = JOptionPane.showConfirmDialog(null,
                "Quieres jugar otra ronda?", "Elige una opcion",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/imagenesflechita.jpg"));
		
		
		if (input == 0) {
			
		
		for(int j = 0; j < controlador.jugadores.size(); j++ ) {
			
			List<CartaRuta> manoGenerica = new ArrayList<CartaRuta>();
			
			for(int z = 0; z < 6; z++) 
			{
			manoGenerica.add(controlador.ruta.get(0));
			controlador.ruta.remove(0);
			}
			
			controlador.jugadores.get(j).setMano(manoGenerica);
		}
		
		System.out.print(controlador.jugadores.get(0).getMano().size() +" tamaño mano");
		
		for (int z = 0; z < controlador.jugadores.size(); z++){
			
			controlador.jugadores.get(z).configuracion();
			
		}
		
		for(int x = 0; x < controlador.jugadores.get(0).getMano().size(); x++ ) {
			
			controlador.jugadores.get(0).getMano().get(x).addActionListener(listener);
			
		}
		
		tuTurno=true;
		
		}else {
			
			System.exit(0);
		}
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			// TODO Auto-generated method stub
			
			for(int x = 0; x < controlador.jugadores.get(0).getMano().size(); x++ ) {
				
				if (evento.getSource() == controlador.jugadores.get(0).getMano().get(x) && tuTurno == true) {
					
					System.out.print("Escucha asignado");
					
					tuTurno = false;
					jugar(x);
					
					
					controlador.jugadores.get(0).redibujar();
					repaint();
					revalidate();
					
					 
					
					if (tuTurno == false) {
						
						if(reiniciando == false) {
						
							turnosComputadora();
							
						}
					}
					
					reiniciando = false;
						
				}
			}
			
			if (evento.getSource() == comer) {
				
				if ( controlador.jugadores.get(0).getMano().size()>= 6 && tuTurno == true ) {
					
					
					tuTurno = false;
					
					List<CartaRuta> genericoMano  = new ArrayList<CartaRuta>();
					
					Integer[] cartas = {1,2,3,4,5,6};
					
					genericoMano = controlador.jugadores.get(0).getMano();
					
					try {
						
						controlador.ruta.get(0).addActionListener(listener);
						genericoMano.add(controlador.ruta.get(0));
						
						controlador.ruta.remove(0);
						
					}catch(Exception e) {
						
						comer();
						System.out.print("sin cartas");
						
					}

					if(reiniciando == false) {
					
					try {
				           
			            Integer descarte = (Integer)JOptionPane.showInputDialog(null, "Seleccione una carta para descartar", 
			                    "Descarte", JOptionPane.QUESTION_MESSAGE, new ImageIcon("src/imagenes/reverso.jpg"),cartas, cartas[0]);
			            
			            genericoMano.remove(descarte-1);
			        	
					}
			        	catch(Exception e) {
			        	  int descarte = 0;
			        	  
			        	  genericoMano.remove(descarte);
			        	
			        	}
					
					
					
					controlador.jugadores.get(0).setMano(genericoMano);
					
					controlador.jugadores.get(0).redibujar();

					
					turnosComputadora();
					
					}
					
					reiniciando = false;
					
					
					
				}
			}
			
			
		}

	}



}
