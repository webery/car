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

import weber.logistics.module.car.model.Trip;
import weber.logistics.module.car.model.TripRoute;
import weber.logistics.module.common.model.Employee;

/**
 * 用户完成的订单Excel
 * 
 * @author weber
 *
 */
public class EmpTripExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Employee> employees = (List<Employee>) model.get("employees");
		String yearMonth = (String) model.get("yearMonth");

		// 1.
		HSSFSheet sheet = workbook.createSheet("员工派单报表");
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
		c_h_2.setCellValue("出单报表");
		c_h_2.setCellStyle(headerStyle);
		// 3.内容
		// 3.1内容头部
		// 3.2内容主体
		int e_order = 0;
		int e_hconetnt_index = 0;// 保存写入emp内容的行数
		Iterator<Employee> it_emp = employees.iterator();
		while (it_emp.hasNext()) {
			Employee emp = it_emp.next();
			HSSFRow row_e_Header = sheet.createRow(index++);
			e_hconetnt_index = index++;// 留行写emp内容
			CellStyle row_e_h_Style = getEmployeeHeaderStyle(workbook);
			Integer e_salary = 0;// 统计员工的salary
			Integer e_cash = 0;// 统计员工的出车费
			Integer e_roadToll = 0;// 统计员工总过路费
			Integer e_oilMoney = 0;// 统计员工总
			Integer e_trafficTicket = 0;// 统计员工总
			Integer e_maintenanceCosts = 0;// 统计员工总

			int e_index = 0;// emp列号
			Cell c_e_h_order = row_e_Header.createCell(e_index++);
			c_e_h_order.setCellValue("序号");
			c_e_h_order.setCellStyle(row_e_h_Style);
			Cell c_e_h_id = row_e_Header.createCell(e_index++);
			c_e_h_id.setCellValue("员工编号");
			c_e_h_id.setCellStyle(row_e_h_Style);
			Cell c_e_h_name = row_e_Header.createCell(e_index++);
			c_e_h_name.setCellValue("员工姓名");
			c_e_h_name.setCellStyle(row_e_h_Style);
			Cell c_e_h_salary = row_e_Header.createCell(e_index++);
			c_e_h_salary.setCellValue("总工资");
			c_e_h_salary.setCellStyle(row_e_h_Style);
			Cell c_e_h_cash = row_e_Header.createCell(e_index++);
			c_e_h_cash.setCellValue("总出车费");
			c_e_h_cash.setCellStyle(row_e_h_Style);
			Cell c_e_h_roadToll = row_e_Header.createCell(e_index++);
			c_e_h_roadToll.setCellValue("总过路费");
			c_e_h_roadToll.setCellStyle(row_e_h_Style);
			Cell c_e_h_oilMoney = row_e_Header.createCell(e_index++);
			c_e_h_oilMoney.setCellValue("总油耗");
			c_e_h_oilMoney.setCellStyle(row_e_h_Style);
			Cell c_e_h_trafficTicket = row_e_Header.createCell(e_index++);
			c_e_h_trafficTicket.setCellValue("总违章罚款");
			c_e_h_trafficTicket.setCellStyle(row_e_h_Style);
			Cell c_e_h_maintenanceCosts = row_e_Header.createCell(e_index++);
			c_e_h_maintenanceCosts.setCellValue("总维修费用");
			c_e_h_maintenanceCosts.setCellStyle(row_e_h_Style);

			// 派单头部
			int t_index = 1;// 列下标
			HSSFRow row_t_Header = sheet.createRow(index++);
			CellStyle row_t_h_Style = getTripHeaderStyle(workbook);
			Cell c_t_h_order = row_t_Header.createCell(t_index++);
			c_t_h_order.setCellValue("单号");
			c_t_h_order.setCellStyle(row_t_h_Style);
			Cell c_t_h_startDate = row_t_Header.createCell(t_index++);
			c_t_h_startDate.setCellValue("开始");
			c_t_h_startDate.setCellStyle(row_t_h_Style);
			Cell c_t_h_finishDate = row_t_Header.createCell(t_index++);
			c_t_h_finishDate.setCellValue("完成");
			c_t_h_finishDate.setCellStyle(row_t_h_Style);
			Cell c_t_h_trailer = row_t_Header.createCell(t_index++);
			c_t_h_trailer.setCellValue("车辆");
			c_t_h_trailer.setCellStyle(row_t_h_Style);
			Cell c_t_h_container = row_t_Header.createCell(t_index++);
			c_t_h_container.setCellValue("货柜");
			c_t_h_container.setCellStyle(row_t_h_Style);
			Cell c_t_h_bracket = row_t_Header.createCell(t_index++);
			c_t_h_bracket.setCellValue("托架");
			c_t_h_bracket.setCellStyle(row_t_h_Style);
			Cell c_t_h_oilcard = row_t_Header.createCell(t_index++);
			c_t_h_oilcard.setCellValue("油卡");
			c_t_h_oilcard.setCellStyle(row_t_h_Style);
			Cell c_t_h_salary = row_t_Header.createCell(t_index++);
			c_t_h_salary.setCellValue("工资");
			c_t_h_salary.setCellStyle(row_t_h_Style);
			Cell c_t_h_cash = row_t_Header.createCell(t_index++);
			c_t_h_cash.setCellValue("出车费");
			c_t_h_cash.setCellStyle(row_t_h_Style);
			Cell c_t_h_roadToll = row_t_Header.createCell(t_index++);
			c_t_h_roadToll.setCellValue("过路费");
			c_t_h_roadToll.setCellStyle(row_t_h_Style);
			Cell c_t_h_oilMoney = row_t_Header.createCell(t_index++);
			c_t_h_oilMoney.setCellValue("油耗");
			c_t_h_oilMoney.setCellStyle(row_t_h_Style);
			Cell c_t_h_trafficTicket = row_t_Header.createCell(t_index++);
			c_t_h_trafficTicket.setCellValue("违章罚款");
			c_t_h_trafficTicket.setCellStyle(row_t_h_Style);
			Cell c_t_h_maintenanceCosts = row_t_Header.createCell(t_index++);
			c_t_h_maintenanceCosts.setCellValue("维修费用");
			c_t_h_maintenanceCosts.setCellStyle(row_t_h_Style);

			// 派单内容
			Iterator<Trip> it_t = emp.getTrips().iterator();
			int t_order = 1;
			while (it_t.hasNext()) {
				t_index = 1;
				Trip trip = it_t.next();
				//
				e_salary += trip.getSalary();
				e_cash += trip.getCash();
				e_roadToll += trip.getRoadToll();
				e_oilMoney += trip.getOilMoney();
				e_trafficTicket += trip.getTrafficTicket();
				e_maintenanceCosts += trip.getMaintenanceCosts();
				//
				HSSFRow row_t = sheet.createRow(index++);
				CellStyle row_t_Style = getCommonStyle(workbook);

				Cell c_t_id = row_t.createCell(t_index++);
				c_t_id.setCellValue(trip.getId());
				c_t_id.setCellStyle(row_t_Style);
				Cell c_t_startDate = row_t.createCell(t_index++);
				c_t_startDate.setCellValue(trip.getStartDate());
				c_t_startDate.setCellStyle(dateStyle);
				Cell c_t_finishDate = row_t.createCell(t_index++);
				c_t_finishDate.setCellValue(trip.getFinishDate());
				c_t_finishDate.setCellStyle(dateStyle);
				Cell c_t_trailer = row_t.createCell(t_index++);
				c_t_trailer.setCellValue(trip.getTrailer().getPlateNum());
				c_t_trailer.setCellStyle(row_t_Style);
				Cell c_t_container = row_t.createCell(t_index++);
				c_t_container.setCellValue(trip.getContainer().getCode());
				c_t_container.setCellStyle(row_t_Style);
				Cell c_t_bracket = row_t.createCell(t_index++);
				c_t_bracket.setCellValue(trip.getBracket().getPlateNum());
				c_t_bracket.setCellStyle(row_t_Style);
				Cell c_t_oilcard = row_t.createCell(t_index++);
				c_t_oilcard.setCellValue(trip.getOilcard().getCode());
				c_t_oilcard.setCellStyle(row_t_Style);
				Cell c_t_salary = row_t.createCell(t_index++);
				c_t_salary.setCellValue(trip.getSalary());
				c_t_salary.setCellStyle(row_t_Style);
				Cell c_t_cash = row_t.createCell(t_index++);
				c_t_cash.setCellValue(trip.getCash());
				c_t_cash.setCellStyle(row_t_Style);
				Cell c_t_roadToll = row_t.createCell(t_index++);
				c_t_roadToll.setCellValue(trip.getRoadToll());
				c_t_roadToll.setCellStyle(row_t_Style);
				Cell c_t_oilMoney = row_t.createCell(t_index++);
				c_t_oilMoney.setCellValue(trip.getOilMoney());
				c_t_oilMoney.setCellStyle(row_t_Style);
				Cell c_t_trafficTicket = row_t.createCell(t_index++);
				c_t_trafficTicket.setCellValue(trip.getTrafficTicket());
				c_t_trafficTicket.setCellStyle(row_t_Style);
				Cell c_t_maintenanceCosts = row_t.createCell(t_index++);
				c_t_maintenanceCosts.setCellValue(trip.getMaintenanceCosts());
				c_t_maintenanceCosts.setCellStyle(row_t_Style);

				// 单路线头
				int tr_index = 2;
				HSSFRow row_tr_Header = sheet.createRow(index++);
				CellStyle row_tr_h_Style = getTripRouteHeaderStyle(workbook);
				Cell c_tr_h_route = row_tr_Header.createCell(tr_index++);
				c_tr_h_route.setCellValue("路线");
				c_tr_h_route.setCellStyle(row_tr_h_Style);
				Cell c_tr_h_startDate = row_tr_Header.createCell(tr_index++);
				c_tr_h_startDate.setCellValue("开始");
				c_tr_h_startDate.setCellStyle(row_tr_h_Style);
				Cell c_tr_h_finishDate = row_tr_Header.createCell(tr_index++);
				c_tr_h_finishDate.setCellValue("完成");
				c_tr_h_finishDate.setCellStyle(row_tr_h_Style);
				//
				int tr_order = 1;
				Iterator<TripRoute> it_tr = trip.getTripRoutes().iterator();
				while (it_tr.hasNext()) {
					tr_index = 2;
					TripRoute tripRoute = it_tr.next();
					HSSFRow row_tr = sheet.createRow(index++);
					CellStyle row_tr_Style = getCommonStyle(workbook);
					Cell c_tr_route = row_tr.createCell(tr_index++);
					c_tr_route.setCellValue(tripRoute.getRoute().getName());
					c_tr_route.setCellStyle(row_tr_Style);
					Cell c_tr_startDate = row_tr.createCell(tr_index++);
					c_tr_startDate.setCellValue(tripRoute.getStartDate());
					c_tr_startDate.setCellStyle(dateStyle);
					Cell c_tr_finishDate = row_tr.createCell(tr_index++);
					c_tr_finishDate.setCellValue(tripRoute.getFinishDate());
					c_tr_finishDate.setCellStyle(dateStyle);
				}
			}

			// 4.写入emp内容
			e_index = 0;
			HSSFRow row_emp = sheet.createRow(e_hconetnt_index);
			CellStyle row_e_Style = getCommonStyle(workbook);
			Cell c_e_order = row_emp.createCell(e_index++);
			c_e_order.setCellValue(e_order++);
			c_e_order.setCellStyle(row_e_Style);
			Cell c_e_id = row_emp.createCell(e_index++);
			c_e_id.setCellValue(emp.getId());
			c_e_id.setCellStyle(row_e_Style);
			Cell c_e_name = row_emp.createCell(e_index++);
			c_e_name.setCellValue(emp.getName());
			c_e_name.setCellStyle(row_e_Style);
			Cell c_e_salary = row_emp.createCell(e_index++);
			c_e_salary.setCellValue(e_salary);
			c_e_salary.setCellStyle(row_e_Style);
			Cell c_e_cash = row_emp.createCell(e_index++);
			c_e_cash.setCellValue(e_cash);
			c_e_cash.setCellStyle(row_e_Style);
			Cell c_e_roadToll = row_emp.createCell(e_index++);
			c_e_roadToll.setCellValue(e_roadToll);
			c_e_roadToll.setCellStyle(row_e_Style);
			Cell c_e_oilMoney = row_emp.createCell(e_index++);
			c_e_oilMoney.setCellValue(e_oilMoney);
			c_e_oilMoney.setCellStyle(row_e_Style);
			Cell c_e_trafficTicket = row_emp.createCell(e_index++);
			c_e_trafficTicket.setCellValue(e_trafficTicket);
			c_e_trafficTicket.setCellStyle(row_e_Style);
			Cell c_e_maintenanceCosts = row_emp.createCell(e_index++);
			c_e_maintenanceCosts.setCellValue(e_maintenanceCosts);
			c_e_maintenanceCosts.setCellStyle(row_e_Style);
			//

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
	private CellStyle getEmployeeHeaderStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setFontName("宋体");
		font.setColor(HSSFColor.BRIGHT_GREEN.index);
		style.setFont(font);

		return style;
	}

	// 派单头部
	private CellStyle getTripHeaderStyle(HSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setFontName("宋体");
		font.setColor(HSSFColor.GREEN.index);

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
