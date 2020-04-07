package Ruta;

import javax.swing.ImageIcon;

public class CartaRuta extends ImageIcon {

	//ImageIcon imagen;
	private char tipo; 
	private String id;
	
	CartaRuta(char tipoGenerico, String idGenerico){
		
		tipo = tipoGenerico;
		id = idGenerico;
		
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


	
	
}
