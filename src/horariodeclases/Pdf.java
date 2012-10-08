/*
 * Paquete horariodeclases
 */
package horariodeclases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Chunk;

/**
 * Esta clase crea un pdf con el horario generado de la instucion.
 * @author nvelasquez
 * 
 */
public class Pdf {
    private String file = "/home/nvelasquez/Horario.pdf";
    private String imageURL = "/home/nvelasquez/imgITLA.jpg";
    private String user = System.getProperty("user.name");
    private Font   redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private Font   titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLUE);
    private Font userDateFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
    private Font cellFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
    /*
    * El constructor por defecto crea un objeto sin distincion.
    */
   
    public Pdf(){

    }
   
    public void  imprimirHorario() {
        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(file));
            documento.open();
            crearTitulo(documento);
            crearTabla(documento);
            documento.close();
        }
        catch(Exception e){
            System.err.println("Favor cierre el documento o coloquelo en una ruta accesible");
        }
    }

    public void crearTitulo(Document doc) throws DocumentException{
    	try {
	    	Paragraph title = new Paragraph("Horarios de clases de "+Institucion.getNombreInstitucion(),titleFont);
	    	@SuppressWarnings("deprecation")
	    	Paragraph userDate =  new Paragraph();
	    	Date date = new Date();
	    	Image image = Image.getInstance(imageURL);
	    	Paragraph setImg = new Paragraph();
	    	
	    	//Agregar data.
	    	setImg.add(new Chunk(image, 5f, 5f));
	        userDate.add(new Paragraph("Creado por "+user+" en fecha "+ date.getDate()+"/"+date.getMonth()+"/"+(date.getYear()+1900), userDateFont));
	        
	        //Alineamos 
	        userDate.setAlignment(Element.ALIGN_RIGHT);
	        title.setAlignment(Element.ALIGN_CENTER);
			
			//Agregar linea
			agregarLinea(title, 3);
	        agregarLinea(userDate,2);
	        
	        //Agregar al Documento
	        doc.add(userDate);
	        doc.add(setImg);
	        doc.add(title);  
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
    
    public void agregarCuerpo(){
        
    }
    
    public void crearTabla(Document doc) throws BadElementException{
        Iterator<Horario> horario = Institucion.getHorario().iterator();
        
        PdfPTable tabla = new PdfPTable(5);
        
        
        PdfPCell c1 = new PdfPCell(new Phrase("Dia",redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Profesor",redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Materia",redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Tanda",redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Horas de Clase",redFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(c1);
        
        while(horario.hasNext()){
            
            Horario hor = horario.next();
                        
            c1 = new PdfPCell(new Phrase(hor.getDia(), cellFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(hor.getNombreProfesor(), cellFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(hor.getNombreMateria(), cellFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(hor.getTanda(), cellFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(Integer.toString(hor.getHorasDocencia()), cellFont));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
        }
        
        try{
            doc.add(tabla);
        }catch(DocumentException e){
            e.printStackTrace();
        }
        
    }
    
    private static void agregarLinea(Paragraph parrafo, int cuantas) {
        for (int i = 0; i < cuantas; i++) {
          parrafo.add(new Paragraph(" "));
        }
  }

    
}
