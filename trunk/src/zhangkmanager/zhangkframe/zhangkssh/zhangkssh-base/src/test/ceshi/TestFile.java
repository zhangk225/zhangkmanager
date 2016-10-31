import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TestFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		getFileRow("E://log -.2014-06-10");
//		String yy=CommonUtil.dtos(new Date());
//		System.out.println(yy);
	  String[] ss=new String[]{"asdsa","2423","gfhg"};
	  System.out.println();
	}

	public static void getFileRow(String fileName) {
		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;
		OutputStream outputStream = null;
		try {
			InputStream inputStream = new FileInputStream(fileName);
			inputReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputReader);
			// 读取一行
			String line = null;
			StringBuffer strBuffer = new StringBuffer();
			while ((line = bufferReader.readLine()) != null) {
//				strBuffer.append(line);
//			  line=line.replaceAll("\\s", "#=@=#");
				System.out.println(line);
//				String[] s = line.split("#=@=#");
//				System.out.println(s.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
