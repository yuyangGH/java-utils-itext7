package com.qianqiangongzi.utils;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.ReaderProperties;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

public class Watermarking {
	public static final String DATA = "./src/test/resources/data/united_states.csv";
	public static final String DEST = "D:/test/20191027/5555.pdf";

	public static void main(String[] args) throws Exception {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new Watermarking().manipulatePdf(DEST);
	}

	public void process(Table table, String line, PdfFont font, boolean isHeader) {
		StringTokenizer tokenizer = new StringTokenizer(line, ";");
		int c = 0;
		while (tokenizer.hasMoreTokens() && c++ < 3) {
			if (isHeader) {
				table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
			} else {
				table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
			}
		}
	}

	protected void manipulatePdf(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
		Document doc = new Document(pdfDoc);
		pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new WatermarkingEventHandler());

//		PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
//		PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
//
//		Table table = new Table(UnitValue.createPercentArray(new float[] { 4, 1, 3 })).useAllAvailableWidth();
//
//		BufferedReader br = new BufferedReader(new FileReader(DATA));
//		String line = br.readLine();
//		process(table, line, bold, true);
//		while ((line = br.readLine()) != null) {
//			process(table, line, font, false);
//		}
//		br.close();

		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		doc.add(new Paragraph("First page"));
		
		doc.close();
		
	}

	protected class WatermarkingEventHandler implements IEventHandler {
		@SuppressWarnings("resource")
		@Override
		public void handleEvent(Event event) {
			PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
			PdfDocument pdfDoc = docEvent.getDocument();
			PdfPage page = docEvent.getPage();
			PdfFont font = null;
			try {
				font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
			new Canvas(canvas, pdfDoc, page.getPageSize()).setFontColor(ColorConstants.LIGHT_GRAY).setFontSize(60)
					.setFont(font).showTextAligned(new Paragraph("WATERMARK"), 298, 421, pdfDoc.getPageNumber(page),
							TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
		}
	}
}
