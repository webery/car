package weber.logistics.module.car.doc;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import weber.logistics.module.car.model.Client;
import weber.logistics.module.car.model.TripRoute;

public class ClientTripRouteExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Client> clients = (List<Client>) model.get("clients");
		String yearMonth = (String) model.get("yearMonth");

		// 1.sheet头部
		HSSFSheet sheet = workbook.createSheet("客户路线报表");
		sheet.setDefaultColumnWidth(12);
		sheet.setDefaultRowHeightInPoints(24);
		CellStyle dateStyle = getDataFormatStyle(workbook);// 日期样式
		int index = 1;// 整个sheet的行数纪录

		// 2.sheet头部
		HSSFRow headerRow = sheet.createRow(index++);
		CellStyle headerStyle = getHeaderStyle(workbook);
		Cell c_h_1 = headerRow.createCell(0);
		c_h_1.setCellValue(yearMonth);
		c_h_1.setCellStyle(headerStyle);
		Cell c_h_2 = headerRow.createCell(1);
		c_h_2.setCellValue("客户路线报表");
		c_h_2.setCellStyle(headerStyle);

		Iterator<Client> it_c = clients.iterator();
		int c_order = 1;
		int c_hconetnt_index = 0;// 保存写入emp内容的行数
		while (it_c.hasNext()) {
			Client client = it_c.next();
			HSSFRow row_c_Header = sheet.createRow(index++);
			c_hconetnt_index = index++;// 留行写emp内容
			CellStyle row_c_h_Style = getClientHeaderStyle(workbook);
			Integer c_earning = 0;
			Integer c_payment = 0;
			Integer c_arrearage = 0;
			Integer c_oilPayment = 0;

			// 头
			int c_index = 0;// emp列号
			Cell c_c_h_order = row_c_Header.createCell(c_index++);
			c_c_h_order.setCellValue("序号");
			c_c_h_order.setCellStyle(row_c_h_Style);
			Cell c_c_h_code = row_c_Header.createCell(c_index++);
			c_c_h_code.setCellValue("客户编号");
			c_c_h_code.setCellStyle(row_c_h_Style);
			Cell c_c_h_name = row_c_Header.createCell(c_index++);
			c_c_h_name.setCellValue("客户名称");
			c_c_h_name.setCellStyle(row_c_h_Style);
			Cell c_c_h_earning = row_c_Header.createCell(c_index++);
			c_c_h_earning.setCellValue("总货款");
			c_c_h_earning.setCellStyle(row_c_h_Style);
			Cell c_c_h_payment = row_c_Header.createCell(c_index++);
			c_c_h_payment.setCellValue("总已付");
			c_c_h_payment.setCellStyle(row_c_h_Style);
			Cell c_c_h_arrearage = row_c_Header.createCell(c_index++);
			c_c_h_arrearage.setCellValue("总未付");
			c_c_h_arrearage.setCellStyle(row_c_h_Style);
			Cell c_c_h_oilPayment = row_c_Header.createCell(c_index++);
			c_c_h_oilPayment.setCellValue("总油卡充值");
			c_c_h_oilPayment.setCellStyle(row_c_h_Style);

			// 单路线头部
			int tr_index = 1;// 列下标
			HSSFRow row_tr_Header = sheet.createRow(index++);
			CellStyle row_tr_h_Style = getTripRouteHeaderStyle(workbook);
			Cell c_tr_h_order = row_tr_Header.createCell(tr_index++);
			c_tr_h_order.setCellValue("序号");
			c_tr_h_order.setCellStyle(row_tr_h_Style);
			Cell c_tr_h_route = row_tr_Header.createCell(tr_index++);
			c_tr_h_route.setCellValue("路线");
			c_tr_h_route.setCellStyle(row_tr_h_Style);
			Cell c_tr_h_trip = row_tr_Header.createCell(tr_index++);
			c_tr_h_trip.setCellValue("单号");
			c_tr_h_trip.setCellStyle(row_tr_h_Style);
			Cell c_tr_h_startDate = row_tr_Header.createCell(tr_index++);
			c_tr_h_startDate.setCellValue("出发时间");
			c_tr_h_startDate.setCellStyle(row_tr_h_Style);
			Cell c_tr_h_finishDate = row_tr_Header.createCell(tr_index++);
			c_tr_h_finishDate.setCellValue("完成时间");
			c_tr_h_finishDate.setCellStyle(row_tr_h_Style);
			Cell c_tr_h_earning = row_tr_Header.createCell(tr_index++);
			c_tr_h_earning.setCellValue("货款");
			c_tr_h_earning.setCellStyle(row_tr_h_Style);
			Cell c_tr_h_payment = row_tr_Header.createCell(tr_index++);
			c_tr_h_payment.setCellValue("已支付");
			c_tr_h_payment.setCellStyle(row_tr_h_Style);
			Cell c_tr_h_arrearage = row_tr_Header.createCell(tr_index++);
			c_tr_h_arrearage.setCellValue("待付");
			c_tr_h_arrearage.setCellStyle(row_tr_h_Style);
			Cell c_tr_h_oilPayment = row_tr_Header.createCell(tr_index++);
			c_tr_h_oilPayment.setCellValue("油卡充值");
			c_tr_h_oilPayment.setCellStyle(row_tr_h_Style);

			int tr_order = 1;
			Iterator<TripRoute> it_tr = client.getTripRoutes().iterator();
			while (it_tr.hasNext()) {
				TripRoute tripRoute = it_tr.next();
				c_earning += tripRoute.getEarning();
				c_payment += tripRoute.getPayment();
				c_arrearage += tripRoute.getArrearage();
				c_oilPayment += tripRoute.getOilPayment();

				tr_index = 1;
				HSSFRow row_tr = sheet.createRow(index++);
				CellStyle row_tr_Style = getCommonStyle(workbook);

				Cell c_tr_order = row_tr.createCell(tr_index++);
				c_tr_order.setCellValue(tr_order);
				c_tr_order.setCellStyle(row_tr_Style);
				Cell c_tr_route = row_tr.createCell(tr_index++);
				c_tr_route.setCellValue(tripRoute.getRoute().getName());
				c_tr_route.setCellStyle(row_tr_Style);
				Cell c_tr_trip = row_tr.createCell(tr_index++);
				c_tr_trip.setCellValue(tripRoute.getTrip().getId());
				c_tr_trip.setCellStyle(row_tr_Style);
				Cell c_tr_startDate = row_tr.createCell(tr_index++);
				c_tr_startDate.setCellValue(tripRoute.getStartDate());
				c_tr_startDate.setCellStyle(dateStyle);
				Cell c_tr_finishDate = row_tr.createCell(tr_index++);
				c_tr_finishDate.setCellValue(tripRoute.getFinishDate());
				c_tr_finishDate.setCellStyle(dateStyle);
				Cell c_tr_earning = row_tr.createCell(tr_index++);
				c_tr_earning.setCellValue(tripRoute.getEarning());
				c_tr_earning.setCellStyle(row_tr_Style);
				Cell c_tr_payment = row_tr.createCell(tr_index++);
				c_tr_payment.setCellValue(tripRoute.getPayment());
				c_tr_payment.setCellStyle(row_tr_Style);
				Cell c_tr_arrearage = row_tr.createCell(tr_index++);
				c_tr_arrearage.setCellValue(tripRoute.getArrearage());
				c_tr_arrearage.setCellStyle(row_tr_Style);
				Cell c_tr_oilPayment = row_tr.createCell(tr_index++);
				c_tr_oilPayment.setCellValue(tripRoute.getOilPayment());
				c_tr_oilPayment.setCellStyle(row_tr_Style);

			}
			// 写入总汇
			c_index = 0;
			HSSFRow row_c = sheet.createRow(c_hconetnt_index);
			CellStyle row_c_Style = getCommonStyle(workbook);

			Cell c_c_order = row_c.createCell(c_index++);
			c_c_order.setCellValue(c_order++);
			c_c_order.setCellStyle(row_c_Style);
			Cell c_c_code = row_c.createCell(c_index++);
			c_c_code.setCellValue(client.getCode());
			c_c_code.setCellStyle(row_c_Style);
			Cell c_c_name = row_c.createCell(c_index++);
			c_c_name.setCellValue(client.getName());
			c_c_name.setCellStyle(row_c_Style);
			Cell c_c_earning = row_c.createCell(c_index++);
			c_c_earning.setCellValue(c_earning);
			c_c_earning.setCellStyle(row_c_Style);
			Cell c_c_payment = row_c.createCell(c_index++);
			c_c_payment.setCellValue(c_payment);
			c_c_payment.setCellStyle(row_c_Style);
			Cell c_c_arrearage = row_c.createCell(c_index++);
			c_c_arrearage.setCellValue(c_arrearage);
			c_c_arrearage.setCellStyle(row_c_Style);
			Cell c_c_oilPayment = row_c.createCell(c_index++);
			c_c_oilPayment.setCellValue(c_oilPayment);
			c_c_oilPayment.setCellStyle(row_c_Style);
		}
		// 5.返回客户端
		// 设置下载时客户端Excel的名称
		String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ ".xls";
		// 处理中文文件名
		// filename = MyUtils.encodeFilename(filename, request);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="
				+ filename);
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}

	// 头部样式
	private CellStyle getCommonStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setFontName("宋体");
		style.setFont(font);

		return style;
	}

	// 头部样式
	private CellStyle getHeaderStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 14);// 设置字体大小
		font.setColor(Font.COLOR_RED);
		style.setFont(font);

		return style;
	}

	// 员工头部
	private CellStyle getClientHeaderStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setFontName("宋体");
		font.setColor(HSSFColor.BRIGHT_GREEN.index);
		style.setFont(font);

		return style;
	}

	// 路线头部
	private CellStyle getTripRouteHeaderStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setFontName("宋体");
		font.setColor(HSSFColor.BLUE_GREY.index);
		style.setFont(font);

		return style;
	}

	// 路线头部
	private CellStyle getDataFormatStyle(HSSFWorkbook workbook) {
		HSSFDataFormat dataFormat = workbook.createDataFormat();
		short dataformat = dataFormat.getFormat("yyyy-mm-dd");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		style.setDataFormat(dataformat);

		return style;
	}

}
