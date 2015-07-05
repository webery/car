package weber.logistics.module.car.doc;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import weber.logistics.module.common.model.Nation;

public class NationExcelView extends AbstractExcelView {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	protected void buildExcelDocument(Map<String, Object> obj,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// map的key，在对应的controller中设置
		List<Nation> list = (List<Nation>) obj.get("list");
		HSSFSheet sheet = workbook.createSheet("list");
		sheet.setDefaultColumnWidth((short) 12);
		HSSFCell cell = getCell(sheet, 0, 0);
		setText(cell, "ID");
		cell = getCell(sheet, 0, 1);
		setText(cell, "name");

		for (short i = 0; i < list.size(); i++) {
			HSSFRow sheetRow = sheet.createRow(i + 1);
			Nation entity = list.get(i);
			sheetRow.createCell(0).setCellValue(entity.getId());
			sheetRow.createCell(1).setCellValue(entity.getName());
		}
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

}
