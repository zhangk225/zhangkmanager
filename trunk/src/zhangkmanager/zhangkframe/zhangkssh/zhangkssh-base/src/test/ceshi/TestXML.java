import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import com.zhangk.baseframe.common.util.xml.XmlUtil;

public class TestXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestXMLObject tob = new TestXMLObject();
		XmlUtil xmlUtil = new XmlUtil();
		tob.setDescription("11111111111");
		tob.setName("222222222222222");
		// String
		// pp="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><testroot><description>11111111111</description><name>222222222222222</name><testsub subAttribute=\"33333333333\"><subname>uuuuuuuuuuuuuuu</subname></testsub><testsubList subAttribute=\"33333333333\"><subname>uuuuuuuuuuuuuuu</subname></testsubList><testsubList subAttribute=\"33333333333\"><subname>uuuuuuuuuuuuuuu</subname></testsubList><testsubList subAttribute=\"33333333333\"><subname>uuuuuuuuuuuuuuu</subname></testsubList></testroot>";
		TestXMLSubObject testXMLSubObject = new TestXMLSubObject();
		testXMLSubObject.setSubAttribute("33333333333");
		testXMLSubObject.setSubname("uuuuuuuuuuuuuuu");
		List<TestXMLSubObject> yy = new ArrayList<TestXMLSubObject>();
		yy.add(testXMLSubObject);
		yy.add(testXMLSubObject);
		yy.add(testXMLSubObject);
		tob.setTestXMLSubObject(testXMLSubObject);
		tob.setTestXMLSubObjects(yy);
		Map<String, String> map = new HashMap<String, String>();
		map.put("map1", "value1");
		map.put("map2", "value2");
		tob.setTestmaps(map);
		try {
			String ss = xmlUtil.objectToString(tob);
			System.out.println(ss);

			TestXMLObject mb = (TestXMLObject) xmlUtil.StringToObject(ss,
					TestXMLObject.class);
			System.out.println(mb.getTestXMLSubObject().getSubname());
			System.out.println(mb.getTestXMLSubObjects().get(1)
					.getSubAttribute());
			System.out.println(mb.getTestmaps().get("map1"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
