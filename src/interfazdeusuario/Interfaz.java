package interfazdeusuario;

import java.util.Scanner;

import horariodeclases.Materia;
import horariodeclases.Profesor;
import horariodeclases.Pdf;
import java.util.Iterator;
import horariodeclases.Horario;
import horariodeclases.Institucion;
//generar imprimir documento


public class Interfaz {
    
	private static String nombre, proceso, continuar;
	private static Scanner in = new Scanner(System.in);         


	public Interfaz() {

	}//Constructor por default

	//Metodo para escribir en pantalla el menu principal
	public static String crearEncabezado(String nombreInstitucion){
            System.out.println("*****Bienvenido al sistema de registro de "+nombreInstitucion+"********");
            System.out.println("Favor elija la operacion que desea");
            System.out.println();
            System.out.println(" Para salir del sistema -Exit, ");
            System.out.print(  " Registro profesor -P, ");
            System.out.println(" Registro materia  -M, ");
            System.out.print(  " Consultar horario -CH, ");
            System.out.println(" Generar Horario   -H, ");
            System.out.println(" Imprimir Horario  -IH, ");
            System.out.print("Que desea hacer?: ");
            proceso = in.nextLine().trim().toUpperCase();
            return proceso;		
	}

	//	En este metodo creamos la interfaz para insertar valores a un nuevo profesor
	public static Profesor crearProfesor(){
	    	System.out.println("Favor ingrese los datos del profesor");
            System.out.print("Nombre: ");
            nombre = in.nextLine().trim();
            Profesor profesor = new Profesor(nombre);//instancio mi nuevo profesor con su nombre.
            System.out.print("Tanda: ");
            profesor.setTanda(in.nextLine().trim());
            System.out.print("Horas de Docencia: ");
            profesor.setHorasDocencia(Integer.parseInt(in.nextLine().trim()));
            System.out.println();
            return profesor;//devolvemos el objeto profesor.
	}

	//En este metodo creamos la interfaz de usuario para el registro de una materia.
	public static Materia crearMateria(){
            System.out.println("Favor ingrese los datos de la Materia");
            System.out.print("Nombre de materia: ");
            nombre = in.nextLine().trim();
            Materia materia = new Materia(nombre);//instancio mi nueva materia con su nombre.
            System.out.print("Aula: ");
            materia.setAula(in.nextLine().trim());
            System.out.print("Horas de Docencia: ");
            materia.setHorasDocencia(Integer.parseInt(in.nextLine().trim()));
            System.out.println();
            return materia;//devolvemos el objeto Materia.
	}

	//En este metodo se escribe en pantalla el final del proceso en caso de ser satisfactorio
	public static String finProceso(String proceso, boolean todoBien){
            String texto = null;
            if(proceso.equalsIgnoreCase("Exit")){
            	continuar = "Exit";
            	return continuar;
            }
            if (todoBien){
                switch (proceso){
	                case "P":{
	                        texto = "El profesor";
	                        break;
	                }
	                case "M":{
	                        texto = "La materia";
	                        break;
	                }
	                case "H":{
	                        texto = "El horario";
	                        break;
	                }

                }
                System.err.println(texto+" fue agregado/generado satisfactoriamente");
            }		
            System.out.print("Que desea hacer? Salir -Exit; Continuar -Next: ");
            continuar = in.nextLine().trim().toUpperCase();
            System.out.println();
            return continuar;
	}
        
    //Metodo para preguntar por pantalla que desea seguir haciendo
    public static String queHacer(String proceso){
        //evaluamos el proceso
        String texto = null;
        switch (proceso){
	case "P":{
                    texto = "otro profesor";
		break;
	}
	case "M":{
                    texto = "otra materia";
                    break;
	}
        }
        System.out.print("Ingrese S si desea agregar "+texto+" de lo contrario N: ");
        continuar = in.nextLine().trim().toUpperCase();
        System.out.println();
        return continuar;
    }
    
    //Consulta de horario
    public static void consultarHorario(){
        Iterator<Horario> it = Institucion.getHorario().iterator();

        while(it.hasNext()){
            Horario hor = it.next();                
            System.out.println("***Informacion de la Seccion Creada***");
            System.out.println();
            System.out.print("Profesor: ");
            System.out.println(hor.getNombreProfesor());
            System.out.print("Materia: ");
            System.out.println(hor.getNombreMateria());
            System.out.print("Tanda: ");
            System.out.println(hor.getTanda());
            System.out.print("Horas de Clase: ");
            System.out.println(hor.getHorasDocencia());
            System.out.print("Dia: ");
            System.out.println(hor.getDia());
            System.out.println();
        }
        imprimir();

    }
   
    public static void imprimir(){
        System.out.println("Desea imprimir el horario? -Si -No");
        proceso = in.nextLine().trim();
        
        if(proceso.equalsIgnoreCase("si")){
            Pdf horario = new Pdf();
            horario.imprimirHorario();
        }
    }

}
