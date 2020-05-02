
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		Crop crop1 = new Crop("example", 1,2,3);
		
		//System.out.println(crop1.toString());
		try
		{
			test.testXmlToObj();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

        
	}
	
	public void testXmlToObj() throws JAXBException, FileNotFoundException
	{		
		File file = new File("config/test.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        MerchWrapper merchList = (MerchWrapper) unmarshaller.unmarshal(file);
        
        for(Merchandise merch : merchList.getMerchList())
        {
        	System.out.println(merch.toString());
        }
        
	}

}
