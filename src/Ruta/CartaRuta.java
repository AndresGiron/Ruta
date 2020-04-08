package Ruta;

import javax.swing.ImageIcon;

public class CartaRuta extends ImageIcon {

	//ImageIcon imagen;
	private char tipo; 
	private String id;
	int valor;
	
	CartaRuta(char tipoGenerico, String idGenerico, int valorGenerico){
		
		tipo = tipoGenerico;
		id = idGenerico;
		valor = valorGenerico;
		
	}

	/*public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}*/

	public char getTipo() {
		return tipo;
	}

	public String getId() {
		return id;
	}
	public int getValor() 
	{
		return valor;
	}


	
	
}
