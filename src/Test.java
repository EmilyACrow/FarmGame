
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
//		ArrayList<Merchandise> merchList = new ArrayList<Merchandise>();
//		merchList.add(crop1);
//		merchList.add(new Item());
//		for(Merchandise merch : merchList)
//		{
//			System.out.println(merch.toString());
//		}
        
	}
	
	public void testXmlToObj() throws JAXBException, FileNotFoundException
	{		
		File file = new File("config/test.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchContainer.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        MerchContainer merchList = (MerchContainer) unmarshaller.unmarshal(file);
        
        for(Merchandise merch : merchList.getMerchList())
        {
        	System.out.println(merch.toString());
        }
        
	}

}
