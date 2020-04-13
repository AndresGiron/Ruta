package Ruta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
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
        cantidadJugadores = (Integer)JOptionPane.showInputDialog(null, "Seleccione numero de jugadores", 
                "Numero de jugadores", JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);
        
        controlador.crearCartas();
        controlador.repartir(cantidadJugadores);
        
        
        //ImageIcon icono2 = new ImageIcon("src/imagenes/selector.jpg");
        sexo = (String)JOptionPane.showInputDialog(null, "¿usted es?", 
                "genero", JOptionPane.QUESTION_MESSAGE, icono, genero, genero[0]);
        
        avatar.setIcon(new ImageIcon("src/imagenes/"+sexo+".jpg"));
        
        controlador.jugadores.get(0).setAvatar(avatar);
        
        
        
        
		
		acomodar();
		container.add(juego);
		
	}
	
	public void acomodar(){ 
        
        juego.setLayout(new BorderLayout());

        norte.setLayout(new FlowLayout());
        
        JScrollPane barritaNorte = new JScrollPane(norte);
        barritaNorte.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barritaNorte.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		barritaNorte.setBackground(Color.getColor("#0c683c"));
		barritaNorte.setPreferredSize(new Dimension(750,370));
		barritaNorte.getViewport().setBackground(Color.getColor("#0c683c"));
		barritaNorte.setBorder(null);
        
        juego.add(barritaNorte,BorderLayout.NORTH);

        for(int x = 0; x< controlador.jugadores.size(); x++) {
        	
        	
        	if (x==0){
        		
        		controlador.jugadores.get(0).configuracion();
        		juego.add(controlador.jugadores.get(0),BorderLayout.SOUTH);

        		
        	}else {
        		
        		controlador.jugadores.get(x).configuracion();
        		norte.add(controlador.jugadores.get(x));
        		
        		           	
        	}
        	
        	
        }
		
	}

}
