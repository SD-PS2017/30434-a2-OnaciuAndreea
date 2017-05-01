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

import main.entities.Book;
import main.entities.Books;


public class BookRepoImpl {

	public void marhsalBooks(List<Book> books) throws JAXBException, FileNotFoundException {
		JAXBContext contextObj = JAXBContext.newInstance(Books.class);

		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		FileOutputStream f= new FileOutputStream("Books.xml");
        Books booksObj=new Books();
        booksObj.setBooks(books);
		marshallerObj.marshal(booksObj,f);
        
	}

	public List<Book> unmarhsalBooks() throws JAXBException {
		
		File file = new File("Books.xml");
		if (file.length()<10) { return new ArrayList(); }
		JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Books books = (Books) jaxbUnmarshaller.unmarshal(file);
	    return books.getBooks();
	}

}
