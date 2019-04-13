package File_IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.layout.element;
import com.itextpdf.text.FontFactory;

import Objects.Case;
import Objects.Categorized;

public class PDF_Out {
	
	//public Categorized cases;
//	public static void main(String[] args) {		
//		PDF_Out test = new PDF_Out();
//		test.outputPDF();
//	}

	
	public void outputPDF() {	// ArrayList<Case>() caseList, String name, String dateCreated
		Categorized categorized = new Categorized();
		ArrayList<Case> cases = categorized.getCaseList();
		
		/* Info that Case contains: 
		 * int caseNumber, 
		 * String caseOwner,
		 * String caseRequestor,
		 * String dateRequested, 
		 * String description,
		 * String[] tokenizedDescription, 
		 * String category
		 */
		
		float fntSize = 8f;
        PdfPTable table = new PdfPTable(7);
        Font tableFont = FontFactory.getFont(FontFactory.COURIER, fntSize);
        table.addCell(new Phrase("Case Number", tableFont));
        table.addCell(new Phrase("Owner", tableFont));
        table.addCell(new Phrase("Requestor", tableFont));
        table.addCell(new Phrase("Date Requested", tableFont));
        table.addCell(new Phrase("Description", tableFont));
        table.addCell(new Phrase("Tokenized Description", tableFont));
        table.addCell(new Phrase("Category", tableFont));
        
		
		try {
			OutputStream file = new FileOutputStream(new File("PDFTest.pdf"));

			Document document = new Document();
			PdfWriter.getInstance(document, file);

			document.open();
			String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
			document.add(new Paragraph(dateTime));
			document.add(Chunk.NEWLINE);
			document.add(table);

			document.close();
			file.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
