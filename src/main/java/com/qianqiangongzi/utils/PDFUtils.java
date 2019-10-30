package com.qianqiangongzi.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * PDF操作
 * 
 * @author 谦谦公子爱编程
 *
 */
public class PDFUtils {
	/**
	 * 获取默认字体
	 * 
	 * @return
	 */
	public static PdfFont getDefaultFont() {
		try {
			return PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
		} catch (IOException e) {
			// 记录日志
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建PDF文档
	 * 
	 * @param dest
	 *            pdf文档存放目标位置
	 * @throws IOException
	 */
	public static Document createPdf(String dest) throws IOException {
		PdfWriter writer = new PdfWriter(dest);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, PageSize.A4); // .rotate()表示横向
		return document;
	}

	/**
	 * 替换PDF文本表单域变量
	 * 
	 * @param templatePdfPath
	 *            要替换的pdf全路径
	 * @param params
	 *            替换参数
	 * @param destPdfPath
	 *            替换后保存的PDF全路径
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static final void replaceTextFieldPdf(String templatePdfPath, String destPdfPath, Map<String, String> params)
			throws FileNotFoundException, IOException {
		PdfDocument pdf = new PdfDocument(new PdfReader(templatePdfPath), new PdfWriter(destPdfPath));

		if (params != null && !params.isEmpty()) {// 有参数才替换
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
			Map<String, PdfFormField> fields = form.getFormFields(); // 获取所有的表单域
			for (String param : params.keySet()) {
				PdfFormField formField = fields.get(param); // 获取某个表单域
				if (formField != null) {
					formField.setFont(getDefaultFont()).setValue(params.get(param)); // 替换值
				}
			}
			// form.flattenFields();// 锁定表单，不让修改
		}
		pdf.close();
	}

	/**
	 * 替换PDF图片表单域（文本）变量，1、获取表单域的大小；2、根据表单域的位置，确定图片的位置；3、如果图片的宽或者高大于表单域，则等比压缩图片。
	 * 
	 * @param templatePdfPath
	 *            要替换的pdf全路径
	 * @param params
	 *            替换参数
	 * @param destPdfPath
	 *            替换后保存的PDF全路径
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static final void replaceImageFieldPdf(String templatePdfPath, String destPdfPath,
			Map<String, String> params) throws FileNotFoundException, IOException {
		PdfDocument pdf = new PdfDocument(new PdfReader(templatePdfPath), new PdfWriter(destPdfPath));

		if (params != null && !params.isEmpty()) {// 有参数才替换
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
			Map<String, PdfFormField> fields = form.getFormFields(); // 获取所有的表单域
			for (String param : params.keySet()) {
				PdfFormField formField = fields.get(param);
				if (formField != null) {
					replaceFieldImage(params, pdf, param, formField); // 替换图片
				}
			}
			form.flattenFields();// 锁定表单，不让修改
		}
		pdf.close();
	}

	/**
	 * 替换域中的图片
	 * 
	 * @param params
	 * @param pdf
	 * @param param
	 * @param formField
	 * @throws MalformedURLException
	 */
	private static void replaceFieldImage(Map<String, String> params, PdfDocument pdf, String param,
			PdfFormField formField) throws MalformedURLException {
		String value = params.get(param);
		String[] values = value.split("\\|");
		Rectangle rectangle = formField.getWidgets().get(0).getRectangle().toRectangle(); // 获取表单域的xy坐标
		PdfCanvas canvas = new PdfCanvas(pdf.getPage(Integer.parseInt(values[0])));
		ImageData image = ImageDataFactory.create(values[1]);
		float imageWidth = image.getWidth();
		float imageHeight = image.getHeight();
		float rectangleWidth = rectangle.getWidth();
		float rectangleHeight = rectangle.getHeight();

		float tempWidth = 0;
		float tempHeight = 0;

		int result = 1; // 压缩宽度
		if (imageWidth > rectangleWidth) {
			tempHeight = imageHeight * rectangleWidth / imageWidth;
			if (tempHeight > rectangleHeight) {
				tempHeight = rectangleHeight;
				result = 2; // 压缩高度
			} else {
				tempWidth = rectangleWidth;
				tempHeight = imageHeight * rectangleWidth / imageWidth;
			}
		} else {
			if (imageHeight > rectangleHeight) {
				tempHeight = rectangleHeight;
				result = 2;
			} else {
				result = 3;
			}
		}

		float y = 0;

		if (result == 1) { // 压缩宽度
			y = rectangleHeight - tempHeight;
		} else if (result == 3) { // 不压缩
			y = rectangleHeight - imageHeight;
		}

		// y/=2; // 如果想要图片在表单域的上下对齐，这个值除以2就行。同理可以计算x的偏移

		if (result == 1) {
			canvas.addImage(image, rectangle.getX(), rectangle.getY() + y, tempWidth, false);
		} else if (result == 2) {
			canvas.addImage(image, rectangle.getX(), rectangle.getY(), tempHeight, false, false);
		} else if (result == 3) {
			canvas.addImage(image, rectangle.getX(), rectangle.getY() + y, false);
		}
	}

	/**
	 * 添加水印
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static final void addWatermark(String srcPdfPath, String destPdfPath, String watermarkText)
			throws FileNotFoundException, IOException {
		PdfDocument pdfDoc = new PdfDocument(new PdfReader(srcPdfPath), new PdfWriter(destPdfPath));

		pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new IEventHandler() {
			@Override
			public void handleEvent(Event event) {
				PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
				PdfDocument pdfDoc = docEvent.getDocument();
				PdfPage page = docEvent.getPage();
				PdfFont font = null;
				try {
					font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD); // 要显示中文水印的话，需要设置中文字体，这里可以动态判断
				} catch (IOException e) {
					e.printStackTrace();
				}
				PdfCanvas canvas = new PdfCanvas(page);
				PdfExtGState gs1 = new PdfExtGState();
				gs1.setFillOpacity(0.5f); // 水印透明度
				canvas.setExtGState(gs1);
				new Canvas(canvas, pdfDoc, page.getPageSize()).setFontColor(ColorConstants.LIGHT_GRAY).setFontSize(60)
						.setFont(font).showTextAligned(new Paragraph(watermarkText), 298, 421,
								pdfDoc.getPageNumber(page), TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
			}
		});
		pdfDoc.close();
	}

	public static void main(String[] args) throws Exception {
		String templatePath = "./src/main/resources/static/test.pdf";
		String replaceTextFieldPath = "./src/main/resources/static/test_text.pdf";
		// 替换文本域
		Map<String, String> params = new HashMap<>();
		params.put("name", "张三");
		params.put("gender", "男性");
		params.put("age", "28");
		params.put("education", "博士后");
		params.put("self-evaluation",
				"张三，中国人最耳熟能详的名字。张三可能真有其人，但更多时候与李四、王五一起指代不特定的某个人，揶揄或者概括常用。例如古代说书人常说：那张三的李四的都来了。也常被用在文学影视作品中。因此名平常普通，进来也被用来指代一个普通人群体，即“张三族”。");
		replaceTextFieldPdf(templatePath, replaceTextFieldPath, params);

		// 替换图片域
		params.clear();
		params.put("image", "1|./src/main/resources/static/image.jpg"); // 1表示图片插入PDF的第几页，后面是图片路径
		params.put("image2", "1|./src/main/resources/static/image2.jpg");
		String replaceImageFieldPath = "./src/main/resources/static/test_image.pdf";
		replaceImageFieldPdf(replaceTextFieldPath, replaceImageFieldPath, params);

		// 添加水印
		String watermarkPath = "./src/main/resources/static/test_watermark.pdf";
		addWatermark(replaceImageFieldPath, watermarkPath, "GERENJIANLI");

	}
}