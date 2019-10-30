package com.qianqiangongzi.utils;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.PatternColor;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfPatternCanvas;
import com.itextpdf.kernel.pdf.colorspace.PdfPattern;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.BoxSizingPropertyValue;
import com.itextpdf.layout.property.Leading;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.TableRenderer;

/**
 * PDF表格处理
 * 
 * @author 谦谦公子爱编程
 *
 */
public class PDFTableUtils {
	public static final String IMG = "./src/main/resources/static/image.jpg";
	public static final String IMG1 = "./src/main/resources/static/image.jpg";
	public static final String IMG2 = "./src/main/resources/static/image2.jpg";
	public static final String QQ = "./src/main/resources/static/QQ.jpg";

	/**
	 * 创建一个表格
	 * 
	 * @param destPath
	 * @throws FileNotFoundException
	 */
	public static void createTable1(String destPath) throws FileNotFoundException {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(destPath));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();
		for (int i = 0; i < 16; i++) {
			table.addCell("hi");
		}
		doc.add(table);
		doc.close();
	}

	// 表格添加背景色，设置是否有边框
	public static void createTable2(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table;
		Cell cell;
		PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		table = new Table(UnitValue.createPercentArray(16)).useAllAvailableWidth();
		for (int aw = 0; aw < 16; aw++) {
			cell = new Cell().add(new Paragraph("hi").setFont(font).setFontColor(ColorConstants.WHITE));
			cell.setBackgroundColor(ColorConstants.BLUE);
			cell.setBorder(Border.NO_BORDER);
			cell.setTextAlignment(TextAlignment.CENTER);
			table.addCell(cell);
		}
		doc.add(table);

		doc.close();
	}

	// 表格添加文字，添加图片到Cell当背景
	public void createTable3(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
		table.setWidth(400);
		Cell cell = new Cell();
		PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		Paragraph p = new Paragraph("A cell with an image as background color.").setFont(font)
				.setFontColor(DeviceGray.WHITE);
		cell.add(p);
		Image img = new Image(ImageDataFactory.create(IMG));
		cell.setNextRenderer(new ImageBackgroundCellRenderer(cell, img));
		cell.setHeight(600 * img.getImageHeight() / img.getImageWidth());
		table.addCell(cell);
		doc.add(table);

		doc.close();
	}

	private class ImageBackgroundCellRenderer extends CellRenderer {
		protected Image img;

		public ImageBackgroundCellRenderer(Cell modelElement, Image img) {
			super(modelElement);
			this.img = img;
		}

		@Override
		public void draw(DrawContext drawContext) {
			img.scaleToFit(getOccupiedAreaBBox().getWidth(), getOccupiedAreaBBox().getHeight());
			drawContext.getCanvas().addXObject(img.getXObject(), getOccupiedAreaBBox());
			super.draw(drawContext);
		}
	}

	// 合并单元格，添加背景色
	protected void createTable4(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
		Cell sn = new Cell(2, 1).add(new Paragraph("S/N"));
		sn.setBackgroundColor(ColorConstants.YELLOW);
		table.addCell(sn);
		Cell name = new Cell(1, 3).add(new Paragraph("Name"));
		name.setBackgroundColor(ColorConstants.CYAN);
		table.addCell(name);
		Cell age = new Cell(2, 1).add(new Paragraph("Age"));
		age.setBackgroundColor(ColorConstants.GRAY);
		table.addCell(age);
		Cell surname = new Cell().add(new Paragraph("SURNAME"));
		surname.setBackgroundColor(ColorConstants.BLUE);
		table.addCell(surname);
		Cell firstname = new Cell().add(new Paragraph("FIRST NAME"));
		firstname.setBackgroundColor(ColorConstants.RED);
		table.addCell(firstname);
		Cell middlename = new Cell().add(new Paragraph("MIDDLE NAME"));
		middlename.setBackgroundColor(ColorConstants.GREEN);
		table.addCell(middlename);
		Cell f1 = new Cell().add(new Paragraph("1"));
		f1.setBackgroundColor(ColorConstants.PINK);
		table.addCell(f1);
		Cell f2 = new Cell().add(new Paragraph("James"));
		f2.setBackgroundColor(ColorConstants.MAGENTA);
		table.addCell(f2);
		Cell f3 = new Cell().add(new Paragraph("Fish"));
		f3.setBackgroundColor(ColorConstants.ORANGE);
		table.addCell(f3);
		Cell f4 = new Cell().add(new Paragraph("Stone"));
		f4.setBackgroundColor(ColorConstants.DARK_GRAY);
		table.addCell(f4);
		Cell f5 = new Cell().add(new Paragraph("17"));
		f5.setBackgroundColor(ColorConstants.LIGHT_GRAY);
		table.addCell(f5);
		doc.add(table);

		doc.close();
	}

	protected void createTable5(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
		Cell cell = new Cell();
		ImageData image = ImageDataFactory.create(IMG1);
		cell.setNextRenderer(new TiledImageBackgroundCellRenderer(cell, image));
		cell.setProperty(Property.BOX_SIZING, BoxSizingPropertyValue.BORDER_BOX);
		cell.setHeight(770).setBorder(Border.NO_BORDER);
		table.addCell(cell);
		cell = new Cell();
		image = ImageDataFactory.create(IMG2);
		cell.setNextRenderer(new TiledImageBackgroundCellRenderer(cell, image));
		cell.setProperty(Property.BOX_SIZING, BoxSizingPropertyValue.BORDER_BOX);
		cell.setHeight(770).setBorder(Border.NO_BORDER);
		table.addCell(cell);
		doc.add(table);

		doc.close();
	}

	private class TiledImageBackgroundCellRenderer extends CellRenderer {
		protected ImageData img;

		public TiledImageBackgroundCellRenderer(Cell modelElement, ImageData img) {
			super(modelElement);
			this.img = img;
		}

		public void colorRectangle(PdfCanvas canvas, Color color, float x, float y, float width, float height) {
			canvas.saveState().setFillColor(color).rectangle(x, y, width, height).fillStroke().restoreState();
		}

		@Override
		public void draw(DrawContext drawContext) {
			PdfPattern.Tiling img_pattern = new PdfPattern.Tiling(img.getWidth(), img.getHeight(), img.getWidth(),
					img.getHeight());
			new PdfPatternCanvas(img_pattern, drawContext.getDocument()).addImage(img, 0, 0, false);
			PdfCanvas canvas = drawContext.getCanvas();
			canvas.saveState();
			colorRectangle(canvas, new PatternColor(img_pattern), getOccupiedAreaBBox().getX(),
					getOccupiedAreaBBox().getY(), getOccupiedAreaBBox().getWidth(), getOccupiedAreaBBox().getHeight());
			canvas.setFillColor(new PatternColor(img_pattern));
			canvas.stroke();
			canvas.restoreState();
		}
	}

	// 图片覆盖在表格上面
	protected void createTable6(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
		table.setNextRenderer(
				new OverlappingImageTableRenderer(table, new Table.RowRange(0, 25), ImageDataFactory.create(QQ)));
		Cell cell;
		for (int r = 'A'; r <= 'Z'; r++) {
			for (int c = 1; c <= 5; c++) {
				cell = new Cell();
				cell.add(new Paragraph(String.valueOf((char) r) + String.valueOf(c)));
				table.addCell(cell);
			}
		}
		doc.add(table);

		doc.close();
	}

	private class OverlappingImageTableRenderer extends TableRenderer {
		private ImageData image;

		public OverlappingImageTableRenderer(Table modelElement, Table.RowRange rowRange, ImageData img) {
			super(modelElement, rowRange);
			this.image = img;
		}

		public OverlappingImageTableRenderer(Table modelElement, ImageData img) {
			super(modelElement);
			this.image = img;
		}

		@Override
		public void drawChildren(DrawContext drawContext) {
			super.drawChildren(drawContext);
			float x = Math.max(
					this.getOccupiedAreaBBox().getX() + this.getOccupiedAreaBBox().getWidth() / 3 - image.getWidth(),
					0);
			float y = Math.max(
					this.getOccupiedAreaBBox().getY() + this.getOccupiedAreaBBox().getHeight() / 3 - image.getHeight(),
					0);
			drawContext.getCanvas().addImage(image, x, y, false);
		}

		@Override
		public IRenderer getNextRenderer() {
			return new OverlappingImageTableRenderer((Table) modelElement, image);
		}
	}

	// Cell中插入图片
	protected void createTable7(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(new float[] { 10, 90 }));
		Image img = new Image(ImageDataFactory.create(QQ));
		table.addCell(img.setAutoScale(true)); // 自动适应大小
		table.addCell("A light bulb icon");
		doc.add(table);

		doc.close();
	}

	public static Cell createImageCell(String path) throws MalformedURLException {
		Image img = new Image(ImageDataFactory.create(path));
		img.setWidth(UnitValue.createPercentValue(100));
		Cell cell = new Cell().add(img);
		cell.setBorder(null);
		return cell;
	}

	public static Cell createTextCell(String text) {
		Cell cell = new Cell();
		Paragraph p = new Paragraph(text);
		p.setTextAlignment(TextAlignment.RIGHT);
		cell.add(p).setVerticalAlignment(VerticalAlignment.BOTTOM);
		cell.setBorder(Border.NO_BORDER);
		return cell;
	}

	protected void createTable8(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);
		Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 2 }));
		table.addCell(createImageCell(IMG1));
		table.addCell(
				createTextCell("This picture was taken at Java One.\nIt shows the iText crew at Java One in 2013."));
		doc.add(table);

		doc.close();
	}

	protected void createTable9(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);
		Image image = new Image(ImageDataFactory.create(QQ));
		Table table = new Table(new float[] { 120 });
		Paragraph listOfDots = new Paragraph();
		for (int i = 0; i < 40; i++) {
			listOfDots.add(image);
			listOfDots.add(new Text(" "));
		}
		table.addCell(listOfDots);
		doc.add(table);
		doc.close();
	}

	public static Cell createImageCell2(String path) throws MalformedURLException {
		Image img = new Image(ImageDataFactory.create(path));
		return new Cell().add(img.setAutoScale(true).setWidth(UnitValue.createPercentValue(100)));
	}

	protected void createTable10(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
		table.addCell(createImageCell2(IMG1));
		table.addCell(createImageCell2(IMG2));
		table.addCell(createImageCell2(IMG2));
		doc.add(table);

		doc.close();
	}

	protected void createTable11(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Image img1 = new Image(ImageDataFactory.create(QQ));
		Image img2 = new Image(ImageDataFactory.create(QQ));
		Image img3 = new Image(ImageDataFactory.create(QQ));

		Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
		table.setWidth(UnitValue.createPercentValue(50));
		table.addCell("Different images, one after the other vertically:");
		Cell cell = new Cell();

		// There's no image autoscaling by default
		cell.add(img1.setAutoScale(true));
		cell.add(img2.setAutoScale(true));
		cell.add(img3.setAutoScale(true));
		table.addCell(cell);
		doc.add(table);
		doc.add(new AreaBreak());

		// In the snippet after this autoscaling is not needed
		// Notice that we do not need to create new Image instances since the images had
		// been already flushed
		img1.setAutoScale(false);
		img2.setAutoScale(false);
		img3.setAutoScale(false);

		table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
		table.addCell("Different images, one after the other vertically, but scaled:");
		cell = new Cell();
		img1.setWidth(UnitValue.createPercentValue(20));
		cell.add(img1);
		img2.setWidth(UnitValue.createPercentValue(20));
		cell.add(img2);
		img3.setWidth(UnitValue.createPercentValue(20));
		cell.add(img3);
		table.addCell(cell);
		table.addCell("Different images, one after the other horizontally:");

		// Notice that the table is not flushed yet so it's strictly forbidden to change
		// image properties yet
		img1 = new Image(ImageDataFactory.create(QQ));
		img2 = new Image(ImageDataFactory.create(QQ));
		img3 = new Image(ImageDataFactory.create(QQ));

		Paragraph p = new Paragraph();
		img1.scale(0.3f, 0.3f);
		p.add(img1);
		p.add(img2);
		p.add(img3);
		table.addCell(p);
		table.addCell("Text and images (mixed):");

		img2 = new Image(ImageDataFactory.create(QQ));
		img3 = new Image(ImageDataFactory.create(QQ));

		p = new Paragraph("The quick brown ");
		p.add(img3);
		p.add(" jumps over the lazy ");
		p.add(img2);
		cell = new Cell();
		cell.add(p);
		table.addCell(cell);

		doc.add(table);

		doc.close();
	}

	protected void createTable12(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Image img1 = new Image(ImageDataFactory.create(IMG1));
		Image img2 = new Image(ImageDataFactory.create(IMG2));
		Image img3 = new Image(ImageDataFactory.create(QQ));

		Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
		table.setWidth(UnitValue.createPercentValue(20));

		img1.setAutoScale(true);
		img2.setAutoScale(true);
		img3.setAutoScale(true);

		table.addCell(img1);
		table.addCell("Brazil");
		table.addCell(img2);
		table.addCell("Dog");
		table.addCell(img3);
		table.addCell("Fox");

		doc.add(table);

		doc.close();
	}

	protected void createTable13(String dest) throws Exception {
		// 1. Create a Document which contains a table:
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);
		Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		Cell cell3 = new Cell();
		Cell cell4 = new Cell();
		// 2. Inside that table, make each cell with specific height:
		cell1.setHeight(50);
		cell2.setHeight(50);
		cell3.setHeight(50);
		cell4.setHeight(50);
		// 3. Each cell has the same background image
		// 4. Add text in front of the image at specific position
		cell1.setNextRenderer(new ImageAndPositionRenderer(cell1, new Image(ImageDataFactory.create(IMG)), "Top left",
				POSITION.TOP_LEFT));
		cell2.setNextRenderer(new ImageAndPositionRenderer(cell2, new Image(ImageDataFactory.create(IMG)), "Top right",
				POSITION.TOP_RIGHT));
		cell3.setNextRenderer(new ImageAndPositionRenderer(cell3, new Image(ImageDataFactory.create(IMG)),
				"Bottom left", POSITION.BOTTOM_LEFT));
		cell4.setNextRenderer(new ImageAndPositionRenderer(cell4, new Image(ImageDataFactory.create(IMG)),
				"Bottom right", POSITION.BOTTOM_RIGHT));
		// Wrap it all up!
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		doc.add(table);

		doc.close();
	}

	private class ImageAndPositionRenderer extends CellRenderer {
		private Image img;
		private String content;
		private POSITION position;

		public ImageAndPositionRenderer(Cell modelElement, Image img, String content, POSITION position) {
			super(modelElement);
			this.img = img;
			this.content = content;
			this.position = position;
		}

		@Override
		public void draw(DrawContext drawContext) {
			super.draw(drawContext);
			img.scaleToFit(getOccupiedAreaBBox().getWidth(), getOccupiedAreaBBox().getHeight());

			drawContext.getCanvas().addXObject(img.getXObject(),
					getOccupiedAreaBBox().getX() + (getOccupiedAreaBBox().getWidth()
							- img.getImageWidth() * (float) img.getProperty(Property.HORIZONTAL_SCALING)) / 2,
					getOccupiedAreaBBox().getY() + (getOccupiedAreaBBox().getHeight()
							- img.getImageHeight() * (float) img.getProperty(Property.VERTICAL_SCALING)) / 2,
					img.getImageWidth() * (float) img.getProperty(Property.HORIZONTAL_SCALING));
			drawContext.getCanvas().stroke();

			Paragraph p = new Paragraph(content);
			Leading leading = p.getDefaultProperty(Property.LEADING);
			UnitValue defaultFontSizeUV = new DocumentRenderer(new Document(drawContext.getDocument()))
					.getPropertyAsUnitValue(Property.FONT_SIZE);
			float defaultFontSize = defaultFontSizeUV.isPointValue() ? defaultFontSizeUV.getValue() : 12f;
			float x;
			float y;
			TextAlignment alignment;
			switch (position) {
			case TOP_LEFT:
				x = getOccupiedAreaBBox().getLeft() + 3;
				y = getOccupiedAreaBBox().getTop() - defaultFontSize * leading.getValue();
				alignment = TextAlignment.LEFT;
				break;
			case TOP_RIGHT:
				x = getOccupiedAreaBBox().getRight() - 3;
				y = getOccupiedAreaBBox().getTop() - defaultFontSize * leading.getValue();
				alignment = TextAlignment.RIGHT;
				break;
			case BOTTOM_LEFT:
				x = getOccupiedAreaBBox().getLeft() + 3;
				y = getOccupiedAreaBBox().getBottom() + 3;
				alignment = TextAlignment.LEFT;
				break;
			case BOTTOM_RIGHT:
				x = getOccupiedAreaBBox().getRight() - 3;
				y = getOccupiedAreaBBox().getBottom() + 3;
				alignment = TextAlignment.RIGHT;
				break;
			default:
				x = 0;
				y = 0;
				alignment = TextAlignment.CENTER;
			}
			new Canvas(drawContext.getCanvas(), drawContext.getDocument(), getOccupiedAreaBBox()).showTextAligned(p, x,
					y, alignment);
		}
	}

	protected void createTable14(String dest) throws Exception {
		// 1. Create a Document which contains a table:
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);
		Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		Cell cell3 = new Cell();
		Cell cell4 = new Cell();
		Cell cell5 = new Cell();
		Cell cell6 = new Cell();
		Cell cell7 = new Cell();
		Cell cell8 = new Cell();
		// 2. Inside that table, make each cell with specific height:
		cell1.setHeight(50);
		cell2.setHeight(50);
		cell3.setHeight(50);
		cell4.setHeight(50);
		cell5.setHeight(50);
		cell6.setHeight(50);
		cell7.setHeight(50);
		cell8.setHeight(50);
		// 3. Each cell has the same background image
		// 4. Add text in front of the image at specific position
		cell1.setNextRenderer(new ImageAndPositionRenderer1(cell1, 0, 1, new Image(ImageDataFactory.create(IMG)),
				"Top left", TextAlignment.LEFT));
		cell2.setNextRenderer(new ImageAndPositionRenderer1(cell2, 1, 1, new Image(ImageDataFactory.create(IMG)),
				"Top right", TextAlignment.RIGHT));
		cell3.setNextRenderer(new ImageAndPositionRenderer1(cell3, 0.5f, 1, new Image(ImageDataFactory.create(IMG)),
				"Top center", TextAlignment.CENTER));
		cell4.setNextRenderer(new ImageAndPositionRenderer1(cell4, 0.5f, 0, new Image(ImageDataFactory.create(IMG)),
				"Bottom center", TextAlignment.CENTER));
		cell5.setNextRenderer(new ImageAndPositionRenderer1(cell5, 0.5f, 0.5f, new Image(ImageDataFactory.create(IMG)),
				"Middle center", TextAlignment.CENTER));
		cell6.setNextRenderer(new ImageAndPositionRenderer1(cell6, 0.5f, 0.5f, new Image(ImageDataFactory.create(IMG)),
				"Middle center", TextAlignment.CENTER));
		cell7.setNextRenderer(new ImageAndPositionRenderer1(cell7, 0, 0, new Image(ImageDataFactory.create(IMG)),
				"Bottom left", TextAlignment.LEFT));
		cell8.setNextRenderer(new ImageAndPositionRenderer1(cell8, 1, 0, new Image(ImageDataFactory.create(IMG)),
				"Bottom right", TextAlignment.RIGHT));
		// Wrap it all up!
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);
		table.addCell(cell7);
		table.addCell(cell8);
		doc.add(table);

		doc.close();
	}

	private class ImageAndPositionRenderer1 extends CellRenderer {
		private Image img;
		private String content;
		private TextAlignment alignment;
		private float wPct;
		private float hPct;

		public ImageAndPositionRenderer1(Cell modelElement, float wPct, float hPct, Image img, String content,
				TextAlignment alignment) {
			super(modelElement);
			this.img = img;
			this.content = content;
			this.alignment = alignment;
			this.wPct = wPct;
			this.hPct = hPct;
		}

		@Override
		public void draw(DrawContext drawContext) {
			super.draw(drawContext);

			drawContext.getCanvas().addXObject(img.getXObject(), getOccupiedAreaBBox());
			drawContext.getCanvas().stroke();

			UnitValue fontSizeUV = getPropertyAsUnitValue(Property.FONT_SIZE);
			float x = getOccupiedAreaBBox().getX() + wPct * getOccupiedAreaBBox().getWidth();
			float y = getOccupiedAreaBBox().getY() + hPct * (getOccupiedAreaBBox().getHeight()
					- (fontSizeUV.isPointValue() ? fontSizeUV.getValue() : 12f) * 1.5f);
			new Document(drawContext.getDocument()).showTextAligned(content, x, y, alignment);

		}
	}

	protected void manipulatePdf(String dest) throws Exception {
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		Document doc = new Document(pdfDoc);

		Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
		PdfReader reader = new PdfReader("./src/main/resources/static/test_table1.pdf");
		PdfDocument srcDoc = new PdfDocument(reader);
		PdfFormXObject header = srcDoc.getFirstPage().copyAsFormXObject(pdfDoc);
		Cell cell = new Cell(1, 3)
				.add(new Image(header).setWidth(UnitValue.createPercentValue(100)).setAutoScale(true));
		table.addCell(cell);
		for (int row = 1; row <= 50; row++) {
			for (int column = 1; column <= 3; column++) {
				table.addCell(String.format("row %s, column %s", row, column));
			}
		}
		reader = new PdfReader("./src/main/resources/static/test_table2.pdf");
		srcDoc = new PdfDocument(reader);
		PdfFormXObject footer = srcDoc.getFirstPage().copyAsFormXObject(pdfDoc);
		cell = new Cell(1, 3).add(new Image(footer).setWidth(UnitValue.createPercentValue(100)).setAutoScale(true));
		table.addCell(cell);
		doc.add(table);

		doc.close();
	}

	public static void main(String[] args) throws Exception {
		new PDFTableUtils().createTable14("./src/main/resources/static/test_table.pdf");
	}

	public enum POSITION {
		TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
	}
}
