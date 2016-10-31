import javax.xml.bind.annotation.XmlAttribute;

public class TestXMLSubObject {

	private String subname;
	
	private String subAttribute;

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	@XmlAttribute(name="subAttribute") 
	public String getSubAttribute() {
		return subAttribute;
	}

	public void setSubAttribute(String subAttribute) {
		this.subAttribute = subAttribute;
	}
}
