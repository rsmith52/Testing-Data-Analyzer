package File_IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Objects.Case;
//import Objects.Categorized;
import Objects.CategorizedTest;

public class PDF_Out {
	
	//public Categorized cases;
	public static void main(String[] args) {		
		PDF_Out test = new PDF_Out();
		test.outputPDF();
	}

	
	public void outputPDF() {	// ArrayList<Case>() caseList, String name, String dateCreated
		CategorizedTest categorizedTest = new CategorizedTest();
		ArrayList<Case> cases = categorizedTest.getCaseList();
		System.out.println(cases.size());
		
		/* Info that Case contains: 
		 * int caseNumber, 
		 * String caseOwner,
		 * String caseRequestor,
		 * String dateRequested, 
		 * String description,
		 * String[] tokenizedDescription, 
		 * String category
		 */
		
		try {
			OutputStream file = new FileOutputStream(new File("PDFTest.pdf"));

			Document document = new Document();
			PdfWriter.getInstance(document, file);

			document.open();
			String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
			document.add(new Paragraph(dateTime));
			document.add(Chunk.NEWLINE);
			document.add(new Paragraph("Hello World, iText"));

			document.close();
			file.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
