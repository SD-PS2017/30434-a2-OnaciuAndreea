package main.presentation;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.entities.Book;
import main.service.BookService;

@Controller
public class BookController {

	private BookService bookService = new BookService();

	@RequestMapping(value = "/seeBooks", method = RequestMethod.GET)
	public String seeBooks(Model model) {
		try {
			model.addAttribute("books", bookService.getBooks());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			model.addAttribute("books", new ArrayList<Book>());
			e.printStackTrace();
		}
		return "seeBooks";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public String addBook(Model model) {
		model.addAttribute("bookForm", new Book());
		return "addBook";
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String deleteBook(Model model) {
		model.addAttribute("bookForm", new Book());
		return "deleteBook";
	}

	@RequestMapping(value = "/seeReports", method = RequestMethod.GET)
	public String generateReports(Model model) {
		model.addAttribute("bookForm", new Book());
		return "generateReports";
	}
	
	@RequestMapping(value = "/seeReports", method = RequestMethod.POST)
	public String generateReport(HttpServletRequest request, 
	        HttpServletResponse response,Model model) {
		if (request.getParameter("pdf")!=null) {
			
			try {
				bookService.generatePDF();
				model.addAttribute("success","PDF generate successfully! Look for the BooksOutToStock.pdf file.");
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		if (request.getParameter("csv")!=null) {
			model.addAttribute("success","CSV generate successfully!"+"\n"+"Look for the BooksOutToStock.csv file.");
			bookService.generateCSV();
		};
		return "generateReports";
	}
	
	@RequestMapping(value = "/updateBook", method = RequestMethod.GET)
	public String updateBook(Model model) {
		model.addAttribute("bookForm", new Book());
		model.addAttribute("bookUpdate", new Book());
		return "updateBook";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("bookForm") Book book, Model model) {
		if (book == null) {
			return "addBook";
		}
	
		if (book.getPrice() < 0 || book.getQuantity() < 0 || book.getAuthor().equals("") || book.getTitle().equals("")
				|| book.getISBN().length() != 16) {
			model.addAttribute("error", "Invalid fields.");
		} else {
			try {
				if (bookService.save(book)) {
					model.addAttribute("success", "The book has been saved successfully!");
				} else {
					model.addAttribute("error", "The book is already saved.");
				}
			} catch (FileNotFoundException | JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "addBook";

			}
		}
		return "addBook";

	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
	public String deleteBook(@ModelAttribute("bookForm") Book book, Model model) {
		try {
			Book b=bookService.findBookByISBN(book.getISBN());
			if (b==null){
				model.addAttribute("error", "The book is not in the system.");
			}
			else {
				bookService.deleteBookById(b.getId());
				model.addAttribute("success", "The book has been deleted successfully!");
			}
		} catch (FileNotFoundException | JAXBException e) {
			e.printStackTrace();
			return "deleteBook";
		}
		
		return "deleteBook";
	}

	@RequestMapping(value = "/updateBook", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute("bookForm") Book book, @ModelAttribute("bookUpdate") Book bookUpdate, Model model) {
		try {
			Book b=bookService.findBookByISBN(book.getISBN());
			if (b==null){
				model.addAttribute("errorMessage", "The book is not in the system.");
			}
			else {
                if (bookUpdate.getAuthor().equals("")){			
				model.addAttribute("bookUpdate",b);
                }
                else {
                	if (book.getPrice() < 0 || book.getQuantity() < 0 || book.getAuthor().equals("") || book.getTitle().equals("")
            				|| book.getISBN().length() != 16) {
            			model.addAttribute("error", "Invalid fields.");
                	}
                	else {
                		bookService.updateBook(b.getId(), bookUpdate);	
                		model.addAttribute("success", "The book has been updated successfully!");
                	}
                }
			}
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
			return "deleteBook";
		}
		return "updateBook";
	}

}
