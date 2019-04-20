package File_IO;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
//import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.util.ArrayList;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;

import Objects.Case;
import Objects.Categorized;


public class PDF_Out {

	public Categorized cases;
	public static void main(String[] args) {		
		PDF_Out test = new PDF_Out();
		Categorized categorized = Categorized_In.readFromDatabase("cats/data1.cat");
		test.outputPDF(categorized);
	}


	public void outputPDF(Categorized categorized) {	// ArrayList<Case>() caseList, String name, String dateCreated
		//		Categorized categorized = new Categorized();
		ArrayList<Case> caseList = categorized.getCaseList();
		ArrayList<Case> temp = null;
		ArrayList<String> topRequestors = Categorized.findTopRequestors(caseList, 5);
		ArrayList<String> topCategories = Categorized.findTopCategories(caseList, 5);
		ArrayList<String> topPerRequestor = new ArrayList<String>();
		ArrayList<String> topPerCategory = new ArrayList<String>();

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
		firstTable.setWidthPercentage(90.0f);
		firstTable.addCell(titleCell1);
		cell = new PdfPCell(new Phrase("Name", catFont));
		firstTable.addCell(cell);
		cell = new PdfPCell(new Phrase("# Cases", catFont));
		firstTable.addCell(cell);

		int numCase = 0;
		int size = 5;
		if (topRequestors.size() < 5) {
			size = topRequestors.size();
		}
		for (int i = 0; i < size; i++) {
			String requestor = topRequestors.get(i);
			cell = new PdfPCell(new Phrase(requestor, tableFont));
			firstTable.addCell(cell);
			temp = Categorized.allRequestorCases(caseList, requestor);
			numCase = temp.size();
			cell = new PdfPCell(new Phrase("" + numCase, tableFont));
			firstTable.addCell(cell);
		}

		borderCell1.addElement(firstTable);
		borderTable1.addCell(borderCell1);


		// Top Categories table (Top Right)
		PdfPCell borderCell2 = new PdfPCell();
		borderCell2.setBorder(PdfPCell.NO_BORDER);
		PdfPCell titleCell2 = new PdfPCell(new Paragraph("Top Categories", titleFont));
		titleCell2.setBorder(PdfPCell.NO_BORDER);
		titleCell2.setColspan(2);
		PdfPTable secondTable = new PdfPTable(2);
		secondTable.setWidthPercentage(90.0f);
		secondTable.addCell(titleCell2);
		cell = new PdfPCell(new Phrase("Category", catFont));
		secondTable.addCell(cell);
		cell = new PdfPCell(new Phrase("# Cases", catFont));
		secondTable.addCell(cell);

		if (topCategories.size() < 5) {
			size = topCategories.size();
		}
		for (int i = 0; i < size; i++) {
			String category = topCategories.get(i);
			cell = new PdfPCell(new Phrase(category, tableFont));
			secondTable.addCell(cell);
			temp = Categorized.allCategoryCases(caseList, category);
			numCase = temp.size();
			cell = new PdfPCell(new Phrase("" + numCase, tableFont));
			secondTable.addCell(cell);
		}

		borderCell2.addElement(secondTable);
		borderTable1.addCell(borderCell2);
		paragraph1.add(borderTable1);     


		// Next row of parallel tables
		Paragraph paragraph2 = new Paragraph();

		// Outer table
		PdfPTable borderTable2 = new PdfPTable(2);
		borderTable2.setWidthPercentage(100.0f);

		// Categories of Top Requestor Table (Bottom Left)
		String name = topRequestors.get(0);
		PdfPCell borderC1 = new PdfPCell();
		borderC1.setBorder(PdfPCell.NO_BORDER);
		PdfPCell titleC1 = new PdfPCell(new Paragraph("Categories of Top Requestor", titleFont));
		titleC1.setBorder(PdfPCell.NO_BORDER);
		titleC1.setColspan(2);
		PdfPTable leftTable = new PdfPTable(2);
		leftTable.setWidthPercentage(90.0f);
		leftTable.addCell(titleC1);
		PdfPCell requestor = new PdfPCell(new Paragraph("Top Requestor: " + name, catFont));
		requestor.setColspan(2);
		leftTable.addCell(requestor);
		cell = new PdfPCell(new Phrase("Category", catFont));
		leftTable.addCell(cell);
		cell = new PdfPCell(new Phrase("# Cases", catFont));
		leftTable.addCell(cell);

		topPerRequestor = Categorized.getTopPerRequestor(caseList, name, 3);
		int totalCases = 0;
		size = 3;
		if (topPerRequestor.size() < 3) {
			size = topPerRequestor.size();
		}
		for (int i = 0; i < size; i++) {
			String category = topPerRequestor.get(i);
			cell = new PdfPCell(new Phrase(category, tableFont));
			leftTable.addCell(cell);
			totalCases = Categorized.getNumCases(caseList, name, category);
			cell = new PdfPCell(new Phrase("" + totalCases, tableFont));
			leftTable.addCell(cell);
		}	

		borderC1.addElement(leftTable);
		borderTable2.addCell(borderC1);

		
		// Requestors of Top Categories Table (Bottom Right)
		String catName = topCategories.get(0);
		PdfPCell borderC2 = new PdfPCell();
		borderC2.setBorder(PdfPCell.NO_BORDER);
		PdfPCell titleC2 = new PdfPCell(new Paragraph("Requestors of Top Categories", titleFont));
		titleC2.setBorder(PdfPCell.NO_BORDER);
		titleC2.setColspan(2);
		PdfPTable rightTable = new PdfPTable(2);
		rightTable.setWidthPercentage(90.0f);
		rightTable.addCell(titleC2);
		PdfPCell category = new PdfPCell(new Paragraph("Top Category: " + catName, catFont));
		category.setColspan(2);
		rightTable.addCell(category);
		cell = new PdfPCell(new Phrase("Requestor", catFont));
		rightTable.addCell(cell);
		cell = new PdfPCell(new Phrase("# Cases", catFont));
		rightTable.addCell(cell);
		
		topPerCategory = Categorized.getTopPerCategory(caseList, catName, 3);
		size = 3;
		if (topPerCategory.size() < 3) {
			size = topPerCategory.size();
		}
		for (int i = 0; i < size; i++) {
			String aRequestor = topPerCategory.get(i);
			cell = new PdfPCell(new Phrase(aRequestor, tableFont));
			rightTable.addCell(cell);
			totalCases = Categorized.getNumCases(caseList, aRequestor, topCategories.get(0));
			cell = new PdfPCell(new Phrase("" + totalCases, tableFont));
			rightTable.addCell(cell);
		}


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
		JFreeChart pieChart = ChartFactory.createPieChart("Top Categories", testPieData, true, true, false);
		int w = 500;	// Width of chart 
		int h = 400;	// Height of chart 


		try {	// create the pdf 
			String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String dt = new SimpleDateFormat("yyy-MM-dd HH.mm.ss").format(new Date());
			OutputStream file = new FileOutputStream(new File(dt + ".pdf"));
			Document document = new Document();
			PdfWriter write = PdfWriter.getInstance(document, file);

			document.open(); 
			document.add(new Paragraph(dateTime));
			document.add(Chunk.NEWLINE);
			document.add(paragraph1);
			document.add(Chunk.NEWLINE);
			document.add(paragraph2);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			// adds pie chart
			PdfContentByte writePieChart = write.getDirectContent();
			PdfTemplate tempChartHolder = writePieChart.createTemplate(w, h); 
			Graphics2D chartGraphics = new PdfGraphics2D(tempChartHolder, w, h);
			Rectangle2D chartRegion = new Rectangle2D.Double(0, 0, w, h);
			pieChart.draw(chartGraphics, chartRegion); 
			chartGraphics.dispose();      
			Image pieImage = Image.getInstance(tempChartHolder);
			pieImage.setAlignment(Image.MIDDLE);
			document.add(pieImage); 

			document.close();
			file.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
