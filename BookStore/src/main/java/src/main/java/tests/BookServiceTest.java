package src.main.java.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.entities.Book;
import main.entities.Genre;
import main.service.BookService;

public class BookServiceTest {
	private Book book;
	private Book book2;
	
	private BookService bookService;

	
	@Before
	public void bookTestSetUp() {
		
		bookService = new BookService();
		this.book = new Book("123-458-145-1234","Programming", "Chis Eugeniu", Genre.FICTION,12,13.0f);
		this.book2 = new Book("123-458-125-1234","Programm", "Chis Eueniu", Genre.FICTION,12,13.0f);
	}

	/*
	 * Testing the insert statement.
	 * The id used for the insert statement is used to get a test object
	 * and to check if the book object has the same attributes as the inserted object.
	 */
	@Test
	public void insertbookTest() throws ParseException, SQLException {

		try {
			bookService.save(book);
			Book insertedBook= bookService.findBookByISBN(book.getISBN());
			assertEquals(book.getAuthor(), insertedBook.getAuthor());
			assertEquals(book.getTitle(), insertedBook.getTitle());
           
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	/*
	 * Testing the update statement.
	 * Get the last book in the database and change it with another book object.
	 * Verify if 'newbook' exists in the db and that old book 'c' does not exist in the db.
	 */
	@Test
	public void updatebookTest() throws ParseException, SQLException {

		try {
			bookService.save(book);
			Book b = bookService.findBookByISBN(book.getISBN());
			bookService.updateBook(b.getId(), book2);
			Book newbook = bookService.findBookByISBN(book2.getISBN());
			assertNotNull(newbook);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	/*
	 * Testing the delete statement.
	 * Get the last book, apply delete, then check if the book does exist in the db.
	 */
	@Test
	public void deletebookTest() throws ParseException, SQLException {
	    try {
			bookService.save(book);
			bookService.deleteBookByISBN(book.getISBN());
			Book c1 = bookService.findBookByISBN(book.getISBN());
			assertNull(c1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@After
	public void afterReset(){
		Book b;
		try {
			b = bookService.findBookByISBN(book.getISBN());
			if (b!=null) bookService.deleteBookById(b.getId());
			Book b2=bookService.findBookByISBN(book2.getISBN());
			if (b2!=null) bookService.deleteBookById(b2.getId());
		} catch (JAXBException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
