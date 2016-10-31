package com.zhangkssh.baseframe.common.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

class ExcellUtil {
	private final static Logger s_logger = Logger.getLogger(ExcellUtil.class);

	/**
	 * 根据指定数据生成excell文件
	 * 
	 * @param filename
	 *            文件名(完整路径)
	 * @param sheetname
	 *            sheet名称
	 * @param filednames
	 *            字段名称组
	 * @param datas
	 *            数据组
	 * @return
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	File fromList(String filename, String sheetname,
			String[] filednames, List<String[]> datas) throws IOException,
			RowsExceededException, WriteException {
		File f = null;
		WritableWorkbook book = null;
		WritableSheet sheet = null;
		try {
			f = new File(filename);
			// 打开文件
			book = Workbook.createWorkbook(f);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			sheet = book.createSheet(sheetname, 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			CellView cv = new CellView();
			cv.setAutosize(true);
			int hangnum = 0;
			int lienum = 0;
			for (String filedname : filednames) {
				sheet.addCell(new Label(lienum, hangnum, filedname));
				sheet.setColumnView(lienum, cv);
				lienum++;
			}
			if (datas != null && datas.size() > 0) {
				hangnum = 1;
				lienum = 0;
				for (String[] data : datas) {
					if (data != null && data.length > 0) {
						for (String filed : data) {
							sheet.addCell(new Label(lienum, hangnum, filed));
							lienum++;
						}
						hangnum++;
						lienum = 0;
					}
				}
			}
			// 写入数据并关闭文件
			book.write();
			book.close();
			return f;

		} catch (IOException e) {
			s_logger.error(
					"excell create error (IOException) ExcellUtil.fromList("
							+ filename + "," + sheetname + ","
							+ filednames.toString() + "," + datas + ") ", e);
			throw e;
		} catch (RowsExceededException e) {
			s_logger.error(
					"excell create error (RowsExceededException) ExcellUtil.fromList("
							+ filename + "," + sheetname + ","
							+ filednames.toString() + "," + datas + ") ", e);
			throw e;
		} catch (WriteException e) {
			s_logger.error(
					"excell create error (WriteException) ExcellUtil.fromList("
							+ filename + "," + sheetname + ","
							+ filednames.toString() + "," + datas + ") ", e);
			throw e;
		}
	}

}
