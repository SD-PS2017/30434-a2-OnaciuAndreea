package main.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import main.entities.Selling;
import main.entities.Sellings;

public class SellingRepoImpl {
	public void marhsalSellings(List<Selling> sellings) throws JAXBException, FileNotFoundException {
		JAXBContext contextObj = JAXBContext.newInstance(Sellings.class);

		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		FileOutputStream f= new FileOutputStream("Sellings.xml");
		Sellings sellingObj=new Sellings();
		sellingObj.setSellings(sellings);
		marshallerObj.marshal(sellingObj,f);
        
	}

	public List<Selling> unmarhsalSellings() throws JAXBException {
		
		File file = new File("Sellings.xml");
		System.out.println(file.length());
		if (file.length()<10) { return new ArrayList(); }
		JAXBContext jaxbContext = JAXBContext.newInstance(Sellings.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Sellings books = (Sellings) jaxbUnmarshaller.unmarshal(file);
	    return books.getSellings();
	}

}
