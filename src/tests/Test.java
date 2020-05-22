package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import gameLogic.*;
import gameScreens.*;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		Crop crop1 = new Crop("example", 1,2,3);
		
		//System.out.println(crop1.toString());
		try
		{
			//test.testXmlToObj();
			System.out.println("Testing GeneralStore screen");
			test.testGeneralStoreScreen();
			//test.testGeneralStore();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

        
	}
	
	public void testXmlToObj() throws JAXBException, FileNotFoundException
	{		
		File file = new File("config/store.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(MerchandiseWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        MerchandiseWrapper merchList = (MerchandiseWrapper) unmarshaller.unmarshal(file);
        
        for(Merchandise merch : merchList.getMerchList())
        {
        	System.out.println(merch.toString());
        }
        
	}
	
	public void testGeneralStore() throws Exception
	{
		GeneralStore store = new GeneralStore();

		System.out.println(store.getAnimals().size());
		for(Animal a : store.getAnimals())
		{
			System.out.println(a);
		}
		store.getMerchandise().remove((Merchandise)store.getAnimals().get(0));
		System.out.println(store.getAnimals().size());
		for(Animal a : store.getAnimals())
		{
			System.out.println(a);
		}
	}
	
	public void testGeneralStoreScreen()  throws Exception
	{
		GeneralStore store = new GeneralStore();
		GeneralStoreScreen storefront = new GeneralStoreScreen(store);
		storefront.frame.setVisible(true);

	}
	
	public void testWelcomScreen() throws Exception
	{
		//WelcomScreen
	}

}
