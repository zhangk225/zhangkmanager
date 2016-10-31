package com.zhangkssh.frame.service.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkssh.baseframe.common.util.CommonUtil;
import com.zhangkssh.externalservice.commonobject.BaseException;
import com.zhangkssh.frame.util.Frame_Exception;

@Service
public class Frame_ExcellTools {

	private static final Logger s_logger = Logger
			.getLogger(Frame_ExcellTools.class);

	@Autowired
	private Frame_ExceptionTools exceptionTools;

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
	public File fromList(String filename, String sheetname,
			String[] filednames, List<String[]> datas) throws Frame_Exception {
		try {
			return CommonUtil.excell_FromList(filename, sheetname, filednames,
					datas);
		} catch (IOException e) {
			s_logger.error(
					"excell create error (IOException) in method Frame_ExcellTools.fromList("
							+ filename + "," + sheetname + ","
							+ filednames.toString() + "," + datas + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_IOEXCEPTION3);
		} catch (RowsExceededException e) {
			s_logger.error(
					"excell create error (RowsExceededException) in method Frame_ExcellTools.fromList("
							+ filename
							+ ","
							+ sheetname
							+ ","
							+ filednames.toString() + "," + datas + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_ROWSEXCEEDEDEXCEPTION6);
		} catch (WriteException e) {
			s_logger.error(
					"excell create error (WriteException) in method Frame_ExcellTools.fromList("
							+ filename + "," + sheetname + ","
							+ filednames.toString() + "," + datas + ") ", e);
			throw exceptionTools
					.getBaseExceptionByErrorCode(BaseException.ERRORCODE_WRITEEXCEPTION7);
		}
	}
}
