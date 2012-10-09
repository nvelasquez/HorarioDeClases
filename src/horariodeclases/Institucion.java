package horariodeclases;

//Interfaces
import interfazdeusuario.Interfaz;

//Utils
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//Excepciones
import excepciones.IsExisteObjeto;
import excepciones.IsFaltaProfesorEx;
import excepciones.IsProcesoInvalido;
import excepciones.IsTandaValida;
import excepciones.HorasValidas;

public class Institucion {

    private Set<Materia>  materias   = new HashSet<Materia>();
    private Set<Profesor> profesores = new HashSet<Profesor>();
    private static List<Horario> horario = new ArrayList<Horario>();
    private List<String> dias = new ArrayList<String>();//Se podia hacer con un array pero para ampliar el uso de las listas se uso asi.
    private static String nombreInstitucion = "Itla School";

    public static void main(String[] args) throws IsExisteObjeto, IsFaltaProfesorEx, IsProcesoInvalido{
        //Inicio el programa con el metodo main
        Institucion institucion = new Institucion(nombreInstitucion);
        institucion.iniciar();
    }

    //Creo el constructor por default que da inicio a la aplicacion
    public Institucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;        

    }//Teermina el constructor

    //Aqui iniciamos el programa e instanciamos los objetos necesarios para su ejecucion.
    public void iniciar() {
            String continuar = "S", proceso;
            boolean todoBien = false;

            //Inicializamos el programa por consola.
            while(!continuar.equalsIgnoreCase("Exit")){
                proceso = Interfaz.crearEncabezado(this.nombreInstitucion);                
                try {
                    continuar = "s";
                    switch(proceso){
                    // En este punto evaluamos la operacion deseada.
                    case "P": {
                        //si es un profesor instanciamos el objeto y almacenamos en el set profesores
                        while(continuar.equalsIgnoreCase("s")){
                            if (!profesores.add(Interfaz.crearProfesor())){
                                todoBien = false;
                                throw new IsExisteObjeto("Ya existe un profesor con estos criterios.");
                            }
                            continuar = Interfaz.queHacer(proceso);
                            todoBien = true;
                        }
                        break;
                    }
                    case "M": {				
                        //si es una materia instanciamos el objeto y almacenamos en el set materias
                        while(continuar.equalsIgnoreCase("s")){
                            if (!materias.add(Interfaz.crearMateria())){
                                todoBien = false;
                                throw new IsExisteObjeto("Ya existe una materia con estos criterios.");
                            }
                            continuar = Interfaz.queHacer(proceso);
                            todoBien = true;
                        }
                        break;
                    }
                    case "H": {
                        if (profesores.isEmpty()){
                            throw new IsExisteObjeto("No hay profesores registrados, favor registrar.");
                        }
                        if (materias.isEmpty()){
                            throw new IsExisteObjeto("No hay materias registradas, favor registrar.");
                        }
                        if (!generarHorario(profesores, materias)){                            
                            todoBien = false;
                            horario.clear();//Limpiar lo que se haya agregado
                            throw new IsFaltaProfesorEx("Faltan profesores para impartir el total de materias");
                        }
                        todoBien = true;                        
                        break;
                    }
                    case "CH": {
                        Interfaz.consultarHorario();
                        todoBien = false;//Esto es para que no imprima ningun mensaje del proceso.
                        break;
                    }

                    case "IH": {
                        Interfaz.imprimir();
                        todoBien = true;
                    }
                    case "EXIT": {
                    	break;
                    }

                    default: {
                        todoBien = false;
                        throw new IsProcesoInvalido("Favor seleccione un proceso valido");                        
                    }

                    }	
                }
                catch (IsTandaValida tanda){
                    System.err.println(tanda.getMessage());
                }
                catch(IsExisteObjeto existe){
                    System.err.println(existe.getMessage());
                }
                catch(IsFaltaProfesorEx falta){
                    System.err.println(falta.getMessage());
                }
                catch(IsProcesoInvalido inv){
                    System.err.println(inv.getMessage());
                }                
                catch(HorasValidas badNum){
                    System.err.println(badNum.getMessage());
                }                

                finally {
                    continuar = Interfaz.finProceso(proceso,todoBien);                                     
                }			
            }
    }
    
    public static String getNombreInstitucion(){
        return nombreInstitucion;
    }

    //Devuelve el set de las materias
    public Set<Materia> getMaterias() {
        return materias;
    }

    //Se agrega un materia al set
    public void setMaterias(Materia materia) {
        materias.add(materia);
    }

    //Devuelve el set de los profesores
    public Set<Profesor> getProfesores() {
        return profesores;
    }

    //En este punto agregamos un nuevo profesor.
    public void setProfesores(Profesor profesor) {
        profesores.add(profesor);
    }

    //Consultar horario
    public static List<Horario> getHorario(){
        //regresa el horario generado
        return horario;
    }
    
    //Metodo para generar el Horario de la institucion.
    public boolean generarHorario(Set<Profesor> profesores, Set<Materia> materias) {

        Iterator<Materia>  iMaterias   = materias.iterator();        
        Materia materia;
        cargarDias();//Cargamos los dias de la semana
        int conteo = 0;        
        boolean seAsigno;
        int conteoProfesores = 0;
        
        //Mientras exista una materia se le debes asignar un profesor		
        while(iMaterias.hasNext()){
            seAsigno = false;//Se hace falso para que no deje una materia sin asignar.
            materia = iMaterias.next();// Se inicia la primera materia
            
            //conteo = 0;//Para que regrese los dias al lunes.
            if (conteoProfesores > profesores.size()){
                return false;//Aqui evitamos que no hayan profesores que puedan cubrir todas las materias.
            }
            for (Profesor a: profesores){
                if(seAsigno){
                    materia = iMaterias.next();// Instanciamos la siguiente materia si la que estaba antes se asigno.
                }
                if (conteo>=6){
                	conteo = 0; //reiniciamos el valor de la variable para reiniciar los dias cuando llegue al ultimo.
                }
                if((a.getHorasDocencia() - materia.getHorasDocencia())>=0){
                    horario.add(new Horario(a.getNombre(),
                                            materia.getNombre(),
                                            a.getTanda(),
                                            materia.getHorasDocencia(),
                                            dias.get(conteo)));

                    //aqui le restamos las horas que ya este profesor tiene asignadas
                    
                    a.setHorasDocencia(a.getHorasDocencia()- materia.getHorasDocencia());
                    
                    if (!iMaterias.hasNext()){
                    	return true;
                    }
                    seAsigno = true;
                    
                }else {
                    conteoProfesores++; //Para saber cuantos profesores no estan disponibles.
                    seAsigno = false;
                }
                conteo++;
            }
            //iProfesores.next();
        }
        return true;

    }
    
    //Cargar dias
    public void cargarDias(){
        dias.add(0, "Lunes");
        dias.add(1, "Martes");
        dias.add(2, "Miercoles");
        dias.add(3, "Jueves");
        dias.add(4, "Viernes");
        dias.add(5, "Sabado");    
    }

}
