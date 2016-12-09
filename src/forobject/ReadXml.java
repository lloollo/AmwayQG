package forobject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class ReadXml{
	//读取配置文件中，测试IOS/Android
	public static String actiononMobile =(String) readxml("action").get("platformName");
	public static String system =(String) readxml("action").get("system");
	public static Map readxml(String str)  {
		SAXReader sax = new SAXReader();
		File file = new File("conf/config.xml");
		Document doc = null;
		try {
			doc = sax.read(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();//获得根节点
		Element memberElm=root.element(str);//获得子节点
		List<Element> els = memberElm.elements();//取得某节点下名为"Android"的所有字节点并进行遍历
		Map<String, String>  nameBute = new HashMap<String, String>();
			for(Element e : els){
			//System.out.println(e.getName()+"="+e.getStringValue());
			nameBute.put(e.getName(), e.getStringValue());			
		}
	
	return nameBute;
	//重新写入到文件
//	XMLWriter output = new XMLWriter(new FileWriter(file)); 
//	output.write(doc);
//	output.close();
	}

}