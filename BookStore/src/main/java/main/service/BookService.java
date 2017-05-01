package main.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import main.entities.Book;
import main.entities.Selling;
import main.repository.BookRepoImpl;
import main.repository.SellingRepoImpl;

public class BookService {
	
	BookRepoImpl bookR=new BookRepoImpl();
	SellingRepoImpl sellingR=new SellingRepoImpl();
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
		
	//CSV file header
	private static final String FILE_HEADER = "isbn,title,author";
	
	public boolean save(Book b) throws JAXBException, FileNotFoundException{
		
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		int id=0;
		for(Book bookInList:list){
			if ((bookInList.getAuthor().equals(b.getAuthor())&& bookInList.getTitle().equals(b.getTitle()))) {
				return false;
			}
			id=bookInList.getId();
		}
		if (!list.isEmpty()) id++;
		
		b.setId(id);
	    list.add(b);
		bookR.marhsalBooks(list);
		return true;
	}
	
	public List<Book> getBooks() throws JAXBException{	
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks(); 
	    return list;
	}
	
	public List<Book> searchBooks(String title,String author,String genre) throws JAXBException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks(); 
		if (!title.equals("")){
			list=list.stream().filter(b->b.getTitle().contains(title)).collect(Collectors.toList());
		}
		
		if (!author.equals("")){
			list=list.stream().filter(b->b.getAuthor().contains(author)).collect(Collectors.toList());
		}
		
		if (!genre.equals("")){
			list=list.stream().filter(b->b.getGenre().toString().equals(genre)).collect(Collectors.toList());
		}

	    return list;
	}
	
	
	public void deleteBookById(int id) throws JAXBException, FileNotFoundException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
	    list.remove(id);
	    bookR.marhsalBooks(list);
	}
	
	public void deleteBookByISBN(String isbn) throws JAXBException, FileNotFoundException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		Book bookToDelete=new Book();
		for(Book b:list){
			if (b.getISBN().equals(isbn)) {
				bookToDelete=b;
				break;
			}	
		}
		
	    list.remove(bookToDelete.getId());
	    bookR.marhsalBooks(list);
	}
	public void updateBook(Integer id,Book newBook) throws JAXBException, FileNotFoundException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		newBook.setId(id);
	    list.set(id,newBook);
	    bookR.marhsalBooks(list);
	}
	
	
	public Book findBookById(int id) throws JAXBException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		Book b=list.get(id);
		return b;
	}
	
	public Book findBookByISBN(String isbn) throws JAXBException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		Book bookToDelete=null;
		for(Book b:list){
			if (b.getISBN().equals(isbn)) {
				bookToDelete=b;
				break;
			}	
		}

		return bookToDelete;
	}
	
	public List<Book> findBookByTitle(String title) throws JAXBException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		list=list.stream().filter(b->b.getTitle().contains(title)).collect(Collectors.toList());
		return list;
	}
	
	public List<Book> findBookByAuthor(String author) throws JAXBException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		list=list.stream().filter(b->b.getAuthor().contains(author)).collect(Collectors.toList());
		return list;
	}
	

	public List<Book> findBookByGenre(String genre) throws JAXBException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		list=list.stream().filter(b->b.getGenre().toString().equals(genre)).collect(Collectors.toList());
		return list;
	}
	
	
	public boolean buyBook(Book book) throws JAXBException, FileNotFoundException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();
		book=findBookByISBN(book.getISBN());
		int quantity=book.getQuantity();
		if (quantity==0) return false;
		quantity--;
		book.setQuantity(quantity);
		list.set(book.getId(), book);
		bookR.marhsalBooks(list);
		
		List<Selling> listS=new ArrayList<Selling>();
		listS=sellingR.unmarhsalSellings();
		Selling s=new Selling();
		s.setB(book);
		s.setD(new Date());
		listS.add(s);
		sellingR.marhsalSellings(listS);
		
		return true;
 	}
	
	public List<Book> getBooksOutOfStock() throws JAXBException{
		List<Book> list=new ArrayList<Book>();
		list=bookR.unmarhsalBooks();	
		list=list.stream().filter(b->b.getQuantity()==0).collect(Collectors.toList());
		return list;
	}
	
	public void generatePDF() throws JAXBException{
		List<Book> list=getBooksOutOfStock();
		
		 Document document = new Document();
		 try
		    {
		        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("BooksOutOfStock.pdf"));
		        document.open();
		        document.add(new Paragraph("Books out of stock: "));
		        PdfPTable table = new PdfPTable(3); // 3 columns.
		        table.setWidthPercentage(100); //Width 100%
		        table.setSpacingBefore(10f); //Space before table
		        table.setSpacingAfter(10f); //Space after table
		 
		        //Set Column widths
		        float[] columnWidths = {1f, 1f, 1f};
		        table.setWidths(columnWidths);
		 
		        PdfPCell cell1 = new PdfPCell(new Paragraph("ISBN"));
		        cell1.setBorderColor(BaseColor.BLACK);
		        cell1.setPaddingLeft(10);
		        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 
		        PdfPCell cell2 = new PdfPCell(new Paragraph("Title"));
		        cell2.setBorderColor(BaseColor.BLACK);
		        cell2.setPaddingLeft(10);
		        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 
		        PdfPCell cell3 = new PdfPCell(new Paragraph("Author"));
		        cell3.setBorderColor(BaseColor.BLACK);
		        cell3.setPaddingLeft(10);
		        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		 
		        table.addCell(cell1);
		        table.addCell(cell2);
		        table.addCell(cell3);
		        for(Book b:list){
		        	PdfPCell isbn = new PdfPCell(new Paragraph(b.getISBN()));
		        	isbn.setBorderColor(BaseColor.BLACK);
		        	isbn.setPaddingLeft(10);
		        	isbn.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	isbn.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 
			        PdfPCell title = new PdfPCell(new Paragraph(b.getTitle()));
			        title.setBorderColor(BaseColor.BLACK);
			        title.setPaddingLeft(10);
			        title.setHorizontalAlignment(Element.ALIGN_CENTER);
			        title.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 
			        PdfPCell author = new PdfPCell(new Paragraph(b.getAuthor()));
			        author.setBorderColor(BaseColor.BLACK);
			        author.setPaddingLeft(10);
			        author.setHorizontalAlignment(Element.ALIGN_CENTER);
			        author.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        
			        table.addCell(isbn);
			        table.addCell(title);
			        table.addCell(author);
		        }
		        document.add(table);
		 
		        document.close();
		        writer.close();
		    } catch (Exception e)
		    {
		        e.printStackTrace();
		    }
	}
	
	
	public void generateCSV(){
		FileWriter fileWriter = null;
		String fileName="BooksOutOfStock.csv";
		try {
			List<Book> list=getBooksOutOfStock();
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			for (Book b : list) {
				fileWriter.append(b.getISBN());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(b.getTitle());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(b.getAuthor());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
		
	}
}
