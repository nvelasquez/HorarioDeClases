/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horariodeclases;

import excepciones.IsHorasValidas;

public class Profesor implements Docencia {

	private String nombre, tanda;
	private int horasDocencia;
		
	//constructor por default
	public Profesor(){
		
	}	
	
	//Constructor que recibe un nombre para instanciar el objeto
	public Profesor(String nombre){
		this.nombre = nombre;
	}	
	
	@Override//El metodo que le dice al profesor donde impartir clases
	public void impartir() {
		System.out.println("Voy a impartir clases");

	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTanda() {
		return tanda;
	}
	
	public void setTanda(String tanda) {
		this.tanda = tanda;
	}
	
	public int getHorasDocencia() {
		return horasDocencia;
	}
	
	public void setHorasDocencia(int horasDocencia){
                this.horasDocencia = horasDocencia;
            
	}
	
	@Override//Sobreescribimos este metodo para que no se dupliquen profesores.
	public boolean equals(Object obj){
		if (obj instanceof Profesor){
			Profesor profesor = (Profesor) obj;
			if(nombre.equalsIgnoreCase(profesor.getNombre())){
				return true;
			}
		}
		return false;
	}
	
	@Override//Sobreescribimos este metodo para que me devuelva el hashCode del objeto que cree
	public int hashCode(){
		return nombre.hashCode();
	}

}

