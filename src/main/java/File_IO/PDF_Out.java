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
import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.layout.element;
import com.itextpdf.text.FontFactory;

import Objects.Case;
import Objects.Categorized;

public class PDF_Out {
	
	public Categorized cases;
	public static void main(String[] args) {		
		PDF_Out test = new PDF_Out();
		test.outputPDF();
	}

	
	public void outputPDF() {	// ArrayList<Case>() caseList, String name, String dateCreated
		Categorized categorized = new Categorized();
		ArrayList<Case> caseList = categorized.getCaseList();
		
		/* Info that Case contains: 
		 * int caseNumber, 
		 * String caseOwner,
		 * String caseRequestor,
		 * String dateRequested, 
		 * String description,
		 * String[] tokenizedDescription, 
		 * String category
		 */
		
        PdfPTable table = new PdfPTable(7);
        Font tableFont = FontFactory.getFont(FontFactory.COURIER, 8f);
        table.addCell(new Phrase("Case Number", tableFont));
        table.addCell(new Phrase("Owner", tableFont));
        table.addCell(new Phrase("Requestor", tableFont));
        table.addCell(new Phrase("Date Requested", tableFont));
        table.addCell(new Phrase("Description", tableFont));
        table.addCell(new Phrase("Tokenized Description", tableFont));
        table.addCell(new Phrase("Category", tableFont));
        
        
        // Testing Parallel 2 column tables by making tables within a table
        Font titleFont = FontFactory.getFont(FontFactory.COURIER, 11f);
        Font catFont = FontFactory.getFont(FontFactory.COURIER, 10f);
        Paragraph paragraph1 = new Paragraph();
        PdfPCell cell = null;
        
        // Border table
        PdfPTable borderTable1 = new PdfPTable(2);
        borderTable1.setWidthPercentage(100.0f);
        
        // Left Table
        PdfPCell borderCell1 = new PdfPCell();
        borderCell1.setBorder(PdfPCell.NO_BORDER);
        PdfPCell titleCell1 = new PdfPCell(new Paragraph("Top Requestors", titleFont));
        titleCell1.setBorder(PdfPCell.NO_BORDER);
        titleCell1.setColspan(2);
        PdfPTable firstTable = new PdfPTable(2);
        firstTable.setWidthPercentage(75.0f);
        firstTable.addCell(titleCell1);
        cell = new PdfPCell(new Phrase("Name", catFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("# Cases", catFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("1. ...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("2. ...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("3. ...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("4. ...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("5. ...", tableFont));
        firstTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        firstTable.addCell(cell);

        // add tables
        borderCell1.addElement(firstTable);
        borderTable1.addCell(borderCell1);
        
        // Right table
        PdfPCell borderCell2 = new PdfPCell();
        borderCell2.setBorder(PdfPCell.NO_BORDER);
        PdfPCell titleCell2 = new PdfPCell(new Paragraph("Top Categories", titleFont));
        titleCell2.setBorder(PdfPCell.NO_BORDER);
        titleCell2.setColspan(2);
        PdfPTable secondTable = new PdfPTable(2);
        secondTable.setWidthPercentage(75.0f);
        secondTable.addCell(titleCell2);
        cell = new PdfPCell(new Phrase("Category", catFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("# Cases", catFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("1. ...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("2. ...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("3. ...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("4. ...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("5. ...", tableFont));
        secondTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        secondTable.addCell(cell);

        
        // add tables
        borderCell2.addElement(secondTable);
        borderTable1.addCell(borderCell2);
        paragraph1.add(borderTable1);
        
        
        // next round of 2 column tables
        Paragraph paragraph2 = new Paragraph();
        
        // Border table
        PdfPTable borderTable2 = new PdfPTable(2);
        borderTable2.setWidthPercentage(100.0f);
        
        // Left Table
        PdfPCell borderC1 = new PdfPCell();
        borderC1.setBorder(PdfPCell.NO_BORDER);
        PdfPCell titleC1 = new PdfPCell(new Paragraph("Categories of Top Requestor", titleFont));
        titleC1.setBorder(PdfPCell.NO_BORDER);
        titleC1.setColspan(2);
        PdfPTable leftTable = new PdfPTable(2);
        leftTable.setWidthPercentage(75.0f);
        leftTable.addCell(titleC1);
        PdfPCell requestor = new PdfPCell(new Paragraph("Top Requestor: ", catFont));	//TODO: Add requestor name
        requestor.setColspan(2);
        leftTable.addCell(requestor);
        cell = new PdfPCell(new Phrase("Category", catFont));
        leftTable.addCell(cell);
        cell = new PdfPCell(new Phrase("# Cases", catFont));
        leftTable.addCell(cell);
        cell = new PdfPCell(new Phrase("1. ...", tableFont));
        leftTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        leftTable.addCell(cell);
        cell = new PdfPCell(new Phrase("2. ...", tableFont));
        leftTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        leftTable.addCell(cell);
        cell = new PdfPCell(new Phrase("3. ...", tableFont));
        leftTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        leftTable.addCell(cell);
        
        // add table
        borderC1.addElement(leftTable);
        borderTable2.addCell(borderC1);
        
        // Right Table 
        PdfPCell borderC2 = new PdfPCell();
        borderC2.setBorder(PdfPCell.NO_BORDER);
        PdfPCell titleC2 = new PdfPCell(new Paragraph("Requestors of Top Categories", titleFont));
        titleC2.setBorder(PdfPCell.NO_BORDER);
        titleC2.setColspan(2);
        PdfPTable rightTable = new PdfPTable(2);
        rightTable.setWidthPercentage(75.0f);
        rightTable.addCell(titleC2);
        PdfPCell category = new PdfPCell(new Paragraph("Top Category: ", catFont));	//TODO: Add category name
        category.setColspan(2);
        rightTable.addCell(category);
        cell = new PdfPCell(new Phrase("Requestor", catFont));
        rightTable.addCell(cell);
        cell = new PdfPCell(new Phrase("# Cases", catFont));
        rightTable.addCell(cell);
        cell = new PdfPCell(new Phrase("1. ...", tableFont));
        rightTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        rightTable.addCell(cell);
        cell = new PdfPCell(new Phrase("2. ...", tableFont));
        rightTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        rightTable.addCell(cell);
        cell = new PdfPCell(new Phrase("3. ...", tableFont));
        rightTable.addCell(cell);
        cell = new PdfPCell(new Phrase("...", tableFont));
        rightTable.addCell(cell);
        
        // add tables
        borderC2.addElement(rightTable);
        borderTable2.addCell(borderC2);
        paragraph2.add(borderTable2);

        
        // add info from cases
//        Case tempCase = new Case();
//        for (int i = 0; i < caseList.size(); i++) {
//        	tempCase = caseList.get(i);
//        	table.addCell(String.valueOf(tempCase.getCaseNumber()));
//        	table.addCell(new Phrase(tempCase.getCaseOwner(), tableFont));
//        	table.addCell(new Phrase(tempCase.getCaseRequestor(), tableFont));
//        	table.addCell(new Phrase(tempCase.getDateRequested(), tableFont));
//        	table.addCell(new Phrase(tempCase.getDescription(), tableFont));
//        	// TODO: addCell for tokenizedDescription
//        	table.addCell(new Phrase(tempCase.getCategory(), tableFont));
//        }
        
		
		try {
			OutputStream file = new FileOutputStream(new File("PDFTest.pdf"));

			Document document = new Document();
			PdfWriter.getInstance(document, file);

			document.open();
			String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
			document.add(new Paragraph(dateTime));
			document.add(Chunk.NEWLINE);
//			document.add(table);
			document.add(paragraph1);
			document.add(Chunk.NEWLINE);
			document.add(paragraph2);

			document.close();
			file.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
