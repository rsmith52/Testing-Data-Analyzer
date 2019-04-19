package File_IO;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.ArrayList;

//import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.awt.PdfGraphics2D;
//import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
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
           
        Font titleFont = FontFactory.getFont(FontFactory.COURIER, 11f);	// font for table titles
        Font catFont = FontFactory.getFont(FontFactory.COURIER, 10f);	// categories font
        Font tableFont = FontFactory.getFont(FontFactory.COURIER, 8f);	// regular table info font
      
        // Make parallel tables by making tables within a table
        Paragraph paragraph1 = new Paragraph();
        PdfPCell cell = null;
        
        // Outer table
        PdfPTable borderTable1 = new PdfPTable(2);
        borderTable1.setWidthPercentage(100.0f);
        
        // Top Requestors Table (Top Left)
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

        borderCell1.addElement(firstTable);
        borderTable1.addCell(borderCell1);
             
        // Top Categories table (Top Right)
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

        borderCell2.addElement(secondTable);
        borderTable1.addCell(borderCell2);
        paragraph1.add(borderTable1);     
        
        // Next row of parallel tables
        Paragraph paragraph2 = new Paragraph();
        
        // Outer table
        PdfPTable borderTable2 = new PdfPTable(2);
        borderTable2.setWidthPercentage(100.0f);
        
        // Categories of Top Requestor Table (Bottom Left)
        PdfPCell borderC1 = new PdfPCell();
        borderC1.setBorder(PdfPCell.NO_BORDER);
        PdfPCell titleC1 = new PdfPCell(new Paragraph("Categories of Top Requestor", titleFont));
        titleC1.setBorder(PdfPCell.NO_BORDER);
        titleC1.setColspan(2);
        PdfPTable leftTable = new PdfPTable(2);
        leftTable.setWidthPercentage(75.0f);
        leftTable.addCell(titleC1);
        PdfPCell requestor = new PdfPCell(new Paragraph("Top Requestor: ", catFont));
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
        
        borderC1.addElement(leftTable);
        borderTable2.addCell(borderC1);
        
        // Requestors of Top Categories Table (Bottom Right)
        PdfPCell borderC2 = new PdfPCell();
        borderC2.setBorder(PdfPCell.NO_BORDER);
        PdfPCell titleC2 = new PdfPCell(new Paragraph("Requestors of Top Categories", titleFont));
        titleC2.setBorder(PdfPCell.NO_BORDER);
        titleC2.setColspan(2);
        PdfPTable rightTable = new PdfPTable(2);
        rightTable.setWidthPercentage(75.0f);
        rightTable.addCell(titleC2);
        PdfPCell category = new PdfPCell(new Paragraph("Top Category: ", catFont));
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
        
        borderC2.addElement(rightTable);
        borderTable2.addCell(borderC2);
        paragraph2.add(borderTable2);
        
        
        // TESTING PIE CHARTS!
        DefaultPieDataset testPieData = new DefaultPieDataset();
        testPieData.setValue("Phishing", 45);
        testPieData.setValue("O365", 37);
        testPieData.setValue("Printer", 10);
        testPieData.setValue("Malware", 47);
        testPieData.setValue("Adobe", 20);
        
        /* Specify chart title, dataset, legend, tooltip and URLs in this method as input */
        JFreeChart pieChart = ChartFactory.createPieChart("Test Pie Chart", testPieData, true, true, false);
        int w = 640;	// Width of chart 
        int h = 480;	// Height of chart 
        
		
		try {	// create the pdf 
			OutputStream file = new FileOutputStream(new File("PDFTest.pdf"));

			Document document = new Document();
			PdfWriter write = PdfWriter.getInstance(document, file);

			document.open();
			String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
			document.add(new Paragraph(dateTime));
			document.add(Chunk.NEWLINE);
			document.add(paragraph1);
			document.add(Chunk.NEWLINE);
			document.add(paragraph2);
			
			PdfContentByte writePieChart = write.getDirectContent();
			PdfTemplate tempChartHolder = writePieChart.createTemplate(w, h); 
			Graphics2D chartGraphics = new PdfGraphics2D(tempChartHolder, w, h);
            Rectangle2D chartRegion = new Rectangle2D.Double(0, 0, w, h);
            pieChart.draw(chartGraphics, chartRegion);           
            chartGraphics.dispose();
            writePieChart.addTemplate(tempChartHolder, 0, 0);
			
			document.close();
			file.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}