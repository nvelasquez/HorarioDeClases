/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horariodeclases;

import excepciones.IsHorasValidas;

public class Materia implements Docencia {
	
	private String nombre, aula;
	private int horasDocencia;
	
	//Constructor por defecto
	public Materia(){
		
	}
	
	//Constructor para instanciar una materia por el nombre;
	public Materia(String nombre){
		this.nombre = nombre;
	}
	
	@Override//Indica a la materia que le toca ser impartida
	public void impartir() {
		System.out.println("Me toca ser impartida");

	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getAula() {
		return aula;
	}
	
	public void setAula(String aula) {
		this.aula = aula;
	}
	
	public int getHorasDocencia() {
		return horasDocencia;
	}
	
	public void setHorasDocencia(int horasDocencia){
	    this.horasDocencia = horasDocencia;
            
	}
	
	@Override//Sobreescribimos este metodo para que no se dupliquen materias.
	public boolean equals(Object obj){
		if (obj instanceof Materia){
			Materia materia = (Materia) obj;
			if(nombre.equalsIgnoreCase(materia .getNombre())){
				return true;
			}
		}
		return false;
	}
	
	@Override//Sobreescribimos este metodo para que me devuelva el hashCode del nombre del objeto creado
	public int hashCode(){
		return nombre.hashCode();
	}

}
