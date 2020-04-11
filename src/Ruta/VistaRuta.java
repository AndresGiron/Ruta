package Ruta;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaRuta extends JFrame {
	
	
	JPanel juego = new JPanel();
	ControlRuta controlador = new ControlRuta(); 
	
	
	public VistaRuta()
	{
		//iniciarVista();

		this.setTitle("Ocho Loco");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void iniciarVista(){
		
		Container container = this.getContentPane();
		
		acomodar();
		container.add(juego);
		
	}
	
	public void acomodar(){
		
		
		
		
	}

}
