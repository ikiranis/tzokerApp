// Κλάση που δημιουργεί pdf αρχείο
//
// Παράδειγμα χρήσης:
//
//  CreatePdf pdf = new CreatePdf();
//  pdf.setDateRange(dateRange);
//  pdf.setNumbersStats(numbersStats);
//  pdf.setTzokersStats(tzokersStats);
//  pdf.create();

package controller;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.ArrayList;
import service.DateRange;

public class CreatePdf {
    private static final String FILE = "statistics.pdf";
    private Font titleFont;
    private Font defaultFont;
    private BaseFont baseFont;
    private ArrayList<Object[]> numbersStats = new ArrayList<>();
    private ArrayList<Object[]> tzokersStats = new ArrayList<>();
    private DateRange dateRange;

    public CreatePdf() {
        try {
            baseFont = BaseFont.createFont("font/arial.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            baseFont.setSubset(true);
            titleFont = new Font(baseFont, 16, Font.BOLD);
            defaultFont = new Font(baseFont, 12);
        } catch (DocumentException | IOException ex) {
            System.out.println(ex);
        } 
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public void setNumbersStats(ArrayList<Object[]> numbersStats) {
        this.numbersStats = numbersStats;
    }

    public void setTzokersStats(ArrayList<Object[]> tzokersStats) {
        this.tzokersStats = tzokersStats;
    }
    
    // Κύρια μέθοδος που δημιουργεί το pdf
    public void create() {
        try {
            // Αρχικοποιήσεις
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            
            // Προσθέτει το περιεχόμενο
            addTitle(document);
            addContent(document);
            
            // Κλείσιμο
            document.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    // Προσθέτει τίτλο στο έγγραφο
    private void addTitle(Document document) throws DocumentException, IOException {
        Paragraph preface = new Paragraph();
        
        addEmptyLine(preface, 1);
        
        Paragraph paragraph = new Paragraph("Στατιστικά αριθμών και τζόκερ", titleFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        
        preface.add(paragraph);
        
        paragraph = new Paragraph("Από: " + dateRange.getFromString() + ", μέχρι: " + dateRange.getToString(), titleFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        
        preface.add(paragraph);

        addEmptyLine(preface, 2);

        document.add(preface);
    }
    
    // Προσθέτει το κυρίως περιεχόμενο του εγγράφου
    private void addContent(Document document) throws DocumentException, IOException {
        Paragraph content = new Paragraph();
        
        content.add(new Paragraph("Στατιστικά των αριθμών", defaultFont));
        
        addEmptyLine(content, 1);
        
        Paragraph table1 = new Paragraph();
        createTable(table1, numbersStats);
        content.add(table1);
        
        addEmptyLine(content, 2);

        content.add(new Paragraph("Στατιστικά των τζόκερ", defaultFont));

        addEmptyLine(content, 1);

        Paragraph table2 = new Paragraph();
        createTable(table2, tzokersStats);
        content.add(table2);
        
        document.add(content);
    }
    
    // Προσθέτει πίνακα με τα στατιστικά
    private void createTable(Paragraph paragraph, ArrayList<Object[]> data) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        
        // Γεμίζει τις επικεφαλίδες του πίνακα
        PdfPCell head = new PdfPCell(new Phrase("Αριθμός", defaultFont));
        head.setHorizontalAlignment(Element.ALIGN_CENTER);
        head.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(head);

        head = new PdfPCell(new Phrase("Συχνότητα εμφάνισης", defaultFont));
        head.setHorizontalAlignment(Element.ALIGN_CENTER);
        head.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(head);

        head = new PdfPCell(new Phrase("Καθυστέρηση εμφάνισης", defaultFont));
        head.setHorizontalAlignment(Element.ALIGN_CENTER);
        head.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(head);
        table.setHeaderRows(1);

        // Γεμίζει τα κελιά με το περιεχόμενο
        PdfPCell cell;
        for (Object[] number : data) {
            for (int i=0;i<=2;i++) {
                cell = new PdfPCell(new Phrase(number[i].toString()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        }

        paragraph.add(table);
    }
    
    // Προσθέτει number γραμμές
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
