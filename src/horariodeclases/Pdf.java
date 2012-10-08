/*
 * Paquete horariodeclases
 */
package horariodeclases;

/**
 * Imports necesarios 
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

/**
 * Esta clase crea un pdf con el horario generado de la instucion.
 * @author nvelasquez
 * 
 */
public class Pdf {
    private String file = "/home/nvelasquez/horarioclases.pdf";
    private String user = System.getProperty("user.name");
    private Font   redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
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
            e.printStackTrace();
        }
    }

    public void crearTitulo(Document doc) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        parrafo.add(new Paragraph("Horarios de clases de "+Institucion.getNombreInstitucion()));
        parrafo.add(new Paragraph("Creado por "+user+" en fecha "+ new Date()));
        agregarLinea(parrafo,5);
        doc.add(parrafo);       
        
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
                        
            c1 = new PdfPCell(new Phrase(hor.getDia()));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(hor.getNombreProfesor()));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(hor.getNombreMateria()));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(hor.getTanda()));
            c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tabla.addCell(c1);
            
            c1 = new PdfPCell(new Phrase(Integer.toString(hor.getHorasDocencia())));
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
