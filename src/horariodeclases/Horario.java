/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horariodeclases;

public class Horario {

	private String nombreProfesor, nombreMateria, tanda, dia;
	private int horasDocencia;


	public Horario() {

	}//Constructor por Default

	//Constructor recibiendo parametros.
	public Horario(String nombreProfesor, String nombreMateria, String tanda, int horasDocencia, String dia){
		this.setNombreProfesor(nombreProfesor);
		this.setNombreMateria(nombreMateria);
		this.setTanda(tanda);
		this.setHorasDocencia(horasDocencia);
		this.setDia(dia);		
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public String getTanda() {
		return tanda;
	}

	public void setTanda(String tanda) {
		this.tanda = tanda;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public int getHorasDocencia() {
		return horasDocencia;
	}

	public void setHorasDocencia(int horasDocencia) {
		this.horasDocencia = horasDocencia;
	}
	
	@Override//Sobreescribimos este metodo para que no se dupliquen materias.
	public boolean equals(Object obj){
		if (obj instanceof Horario){
			Horario horario = (Horario) obj;
			if(nombreProfesor.equalsIgnoreCase(horario.getNombreProfesor())&& dia == horario.getDia()){
				return true;
			}
		}
		return false;
	}
	
	@Override//Sobreescribimos este metodo para que me devuelva el hashCode del nombre del objeto creado
	public int hashCode(){
		return dia.hashCode();
	}


}

