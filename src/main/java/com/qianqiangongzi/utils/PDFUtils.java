package com.qianqiangongzi.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * PDF操作
 * 
 * @author Lin
 *
 */
public class PDFUtils {

	public static final float topMargin = 20; // 上边距
	public static final float rightMargin = 20;// 右边距
	public static final float bottomMargin = 20;// 下边距
	public static final float leftMargin = 20;// 左边距

	public static final int fontSize = 14; // 默认字体大小

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
		document.setMargins(topMargin, rightMargin, bottomMargin, leftMargin);
		return document;
	}

	/**
	 * 插入文字
	 * 
	 * @param document
	 * @param text
	 */
	public static void insertText(Document document, String text) {
		document.add(new Paragraph(text).setFont(getDefaultFont()));
	}

	/**
	 * 插入图片
	 * 
	 * @param document
	 * @param imagePath
	 * @throws MalformedURLException
	 */
	public static void insertImage(Document document, String imagePath) throws MalformedURLException {
		Image image = new Image(ImageDataFactory.create(imagePath));
		if (image.getImageWidth() > 500) {
			image.setWidth(550);
		}
		image.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		Paragraph p = new Paragraph().add(image);
		document.add(p);
	}

	/**
	 * 初始化填充PDF的数据
	 */
	public static final Map<String, String> initFieldData() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("${heritage_apply_batch}", "5");
		params.put("${project_type}", "杂技与竞技");
		params.put("${project_code}", "1011011241412");
		params.put("${project_name}", "杂技与竞技的研究与发展");
		params.put("${heritage_name}", "傅文刚");
		params.put("${heritage_unit}", "深圳市智骏数据科技有限公司");
		params.put("${heritage_province}", "广东省深圳市福田区");
		params.put("${heritage_sex}", "男");
		params.put("${heritage_nation}", "汉族");
		params.put("${heritage_birth_years}", "19890221");
		params.put("${heritage_id_card}", "522229199612455562");
		params.put("${heritage_cltural_degree}", "博士");
		params.put("${heritage_occupation}", "杂技与竞技");
		params.put("${heritage_job_title}", "杂技与竞技博主");
		params.put("${heritage_contact_phone}", "13686422282");
		params.put("${heritage_email}", "646125978@qq.com");
		params.put("${heritage_communication_address}", "广东省深圳市福田区大庆大厦#深圳市智骏数据科技有限公司");
		params.put("${heritage_postcode}", "554100");
		params.put("${heritage_start_year_for_art}", "2014");
		params.put("${heritage_provincial_representative_time}", "2015-05");
		params.put("${heritage_resume}",
				"傅文刚，1951年生出生于三秦大地周原厚土，自幼受周秦汉唐古文化熏陶，执着追求传统文化艺术精髓，耐得住寂寞，甘于清苦，孜孜不倦。70年代考入西安美术学院，在校受恩师栽培，画艺渐进。毕业后分配到宝鸡群众艺术馆，潜心丹青苦苦探索艺术真谛。其艺术成果，为书画界同仁所瞩目。80年代，为盛唐佛院、世界佛都法门寺倡导，设计了一尊佛高48米，堪称世界之最的法门寺四面大佛。");
		params.put("${heritage_lineage}",
				"创作的中国人物画作品，曾在法国、美国、澳大利亚、日本、新加坡、韩国等国家及台湾、香港等地区展出，受到国内外收藏界的青睐。中国画《马兰花》曾在法国巴黎国际艺术博览会展出并被收藏。作品曾多次入选全国性美展、全国体育美展、全国纪念鲁迅诞生120周年“孺子牛”美展、全国扇面画展、海峡两岸美术联展、97香港回归祖国美展、99澳门回归祖国美展、全国第四届工笔画美展，中国画《马球图》荣获中国文联举办的全国画展金奖，中国画《文成公主入藏图》获文化部群星奖，中国画《春风得意丽人行》被北京大学百年校庆收藏，中国画《唐人诗意》、《云淡风轻》两幅作品被鸦片战争博物馆收藏，中国画《秋色幽月》被陕西省图书馆收藏。作品参加全国第八届美展。部分作品分别曾获全国展览金、银、铜奖或被收藏，现任炎黄画院院长，中国美协会员。（行走他乡）");
		params.put("${heritage_technical_characteristics}",
				"创作的中国人物画作品，曾在法国、美国、澳大利亚、日本、新加坡、韩国等国家及台湾、香港等地区展出，受到国内外收藏界的青睐。中国画《马兰花》曾在法国巴黎国际艺术博览会展出并被收藏。作品曾多次入选全国性美展、全国体育美展、全国纪念鲁迅诞生120周年“孺子牛”美展、全国扇面画展、海峡两岸美术联展、97香港回归祖国美展、99澳门回归祖国美展、全国第四届工笔画美展，中国画《马球图》荣获中国文联举办的全国画展金奖，中国画《文成公主入藏图》获文化部群星奖，中国画《春风得意丽人行》被北京大学百年校庆收藏，中国画《唐人诗意》、《云淡风轻》两幅作品被鸦片战争博物馆收藏，中国画《秋色幽月》被陕西省图书馆收藏。作品参加全国第八届美展。");
		params.put("${heritage_AFP_one_right_person}", "田林1");
		params.put("${heritage_AFP_one_phone_number}", "13686422281");
		params.put("${heritage_AFP_one_description}",
				"图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1图片描述1");
		params.put("${heritage_AFP_two_right_person}", "田林2");
		params.put("${heritage_AFP_two_phone_number}", "13686422282");
		params.put("${heritage_AFP_two_description}",
				"图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2图片描述2");
		params.put("${heritage_AFP_three_right_person}", "田林3");
		params.put("${heritage_AFP_three_phone_number}", "13686422283");
		params.put("${heritage_AFP_three_description}",
				"图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3图片描述3");
		params.put("${heritage_AFP_four_right_person}", "田林4");
		params.put("${heritage_AFP_four_phone_number}", "13686422284");
		params.put("${heritage_AFP_four_description}",
				"图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4图片描述4");
		params.put("${heritage_authorization_letter}",
				"这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信这是一封信");
		// 头像及附件图片
		params.put("${heritage_AFP_one}", "4|D:/test/20191027/1.jpg");
		params.put("${heritage_AFP_two}", "5|D:/test/20191027/2.jpg");
		params.put("${heritage_AFP_three}", "6|D:/test/20191027/3.jpg");
		params.put("${heritage_AFP_four}", "7|D:/test/20191027/4.jpg");
		params.put("${heritage_photo}", "2|D:/test/20191027/5.jpg");
		return params;
	}

	// 图片域
	private static final List<String> imageFields = Arrays.asList("${heritage_AFP_one}", "${heritage_AFP_two}",
			"${heritage_AFP_three}", "${heritage_AFP_four}", "${heritage_photo}");

	/**
	 * 替换PDF表单域变量
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
	public static final void replacePdf(String templatePdfPath, String destPdfPath, Map<String, String> params)
			throws FileNotFoundException, IOException {
		PdfDocument pdf = new PdfDocument(new PdfReader(templatePdfPath), new PdfWriter(destPdfPath));

		if (params != null && !params.isEmpty()) {// 有参数才替换
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
			Map<String, PdfFormField> fields = form.getFormFields(); // 获取所有的表单域
			for (String param : params.keySet()) {
				PdfFormField formField = fields.get(param);
				if (imageFields.contains(param)) {// 替换图片
					replaceFieldImage(params, pdf, param, formField);
				} else {// 替换文本域
					formField.setValue(params.get(param), getDefaultFont(), fontSize);
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
	public static final void addWatermark(String srcPdfPath, String destPdfPath)
			throws FileNotFoundException, IOException {
		PdfDocument pdfDoc = new PdfDocument(new PdfReader(srcPdfPath), new PdfWriter(destPdfPath));
		int number = pdfDoc.getNumberOfPages();
		for (int i = 1; i <= number; i++) {
			PdfPage page = pdfDoc.getPage(i);
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
		pdfDoc.close();
	}

	public static void main(String[] args) throws IOException {
		String templatePdfPath = "D:/test/20191027/HeritageApply.pdf";
		String destPdfPath = "D:/test/20191027/HeritageApply-replace.pdf";
		String destPdfPath1 = "D:/test/20191027/HeritageApply-replace1.pdf";
		replacePdf(templatePdfPath, destPdfPath, initFieldData());
		addWatermark(destPdfPath, destPdfPath1);

	}

	// /**
	// * 替换变量
	// *
	// * @throws IOException
	// * @throws FileNotFoundException
	// */
	// public static final void replacePdf(Map<String, String> params) throws
	// FileNotFoundException, IOException {
	//
	// PdfDocument pdf = new PdfDocument(new
	// PdfReader("D:/test/20191025/HeritageApply1.pdf"),
	// new PdfWriter("D:/test/20191025/113.pdf"));
	//
	// if (params != null && !params.isEmpty()) {
	// PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
	//
	// Map<String, PdfFormField> fields = form.getFormFields();
	// for (String param : params.keySet()) {
	// PdfFormField formField = fields.get(param);
	// Rectangle rectangle =
	// formField.getWidgets().get(0).getRectangle().toRectangle();
	// System.out.println(param + "==" + rectangle.getX() + "," + rectangle.getY());
	// if (formField != null) {
	// if ("${heritage_photo}".equals(param)) { // 处理图片
	// // PdfButtonFormField buttonFormField =
	// // (PdfButtonFormField) formField;// 获取模板中的图片域
	// // 把图片转成base64字符串
	// // String str =
	// // Base64.encodeBytes(image2Bytes("D:/test/20191025/image1.jpg"));
	// // buttonFormField.setValue(str);// 把base64字符串赋值给图片域
	// // buttonFormField.setImage("D:/test/20191025/image1.jpg");
	// } else {
	// formField.setValue(params.get(param), getDefaultFont(), fontSize);
	// }
	// }
	// }
	//
	// form.flattenFields();// 锁定表单，不让修改
	// }
	//
	// PdfCanvas canvas = new PdfCanvas(pdf.getPage(2));
	// ImageData image = ImageDataFactory.create("D:/test/20191025/image1.jpg");
	// canvas.addImage(image, 469.995f, 634.304f, true);
	//
	// // pdf.addEventHandler(PdfDocumentEvent.END_PAGE, handler);
	//
	// pdf.close();
	// }
}