import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testroot")
/**
 * 必须有个空的构造函数否则报错
 * @author zhangk
 *
 */
public class TestXMLObject {

	private String name;

	private String description;

	private TestXMLSubObject testXMLSubObject;
	
	private List<TestXMLSubObject> testXMLSubObjects;
	
	private Map<String,String> testmaps;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "testsub")
	public TestXMLSubObject getTestXMLSubObject() {
		return testXMLSubObject;
	}

	public void setTestXMLSubObject(TestXMLSubObject testXMLSubObject) {
		this.testXMLSubObject = testXMLSubObject;
	}

	@XmlElement(name = "testsub")
	@XmlElementWrapper(name="testsubList") 
	public List<TestXMLSubObject> getTestXMLSubObjects() {
		return testXMLSubObjects;
	}

	public void setTestXMLSubObjects(List<TestXMLSubObject> testXMLSubObjects) {
		this.testXMLSubObjects = testXMLSubObjects;
	}

	public Map<String, String> getTestmaps() {
		return testmaps;
	}

	public void setTestmaps(Map<String, String> testmaps) {
		this.testmaps = testmaps;
	}
}
