package main.presentation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.entities.Book;
import main.entities.User;
import main.service.BookService;
import main.service.SecurityService;
import main.service.UserService;
import main.validator.UserValidator;

@Controller
public class UserController  {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    private BookService bookService = new BookService();
    

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model, Authentication authentication,HttpServletRequest request, 
    	      HttpServletResponse response) throws IOException {
    	String url=determineTargetUrl(authentication);
    	model.addAttribute("bookForm", new Book());
    	model.addAttribute("userForm",new User());
    	if (url.equals("welcome")) return "welcome";
    	else
        return "redirect:/"+url;
    }

    
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String addBook(Model model) {
		model.addAttribute("bookForm", new Book());
		Book b=new Book();
		b.setISBN("");
		model.addAttribute("bookToBuy", b);
		return "employee";
	}
    
    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities
         = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }
 
        if (isUser) {
            return "employee";
        } else if (isAdmin) {
            return "welcome";
        } else {
            throw new IllegalStateException();
        }
    }
    
    
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
	public String employee(HttpServletRequest request, 
	        HttpServletResponse response,@ModelAttribute("bookForm") Book book, @ModelAttribute("bookToBuy") Book bookToBuy, Model model) {
		try {
			List<Book> books=new ArrayList<Book>();
			if (book==null) {
				books=bookService.searchBooks("","","");
			}
			else {
			
				if (book.getGenre()==null){
				books=bookService.searchBooks(book.getTitle(),book.getAuthor(),"");
				}
				else{
					books=bookService.searchBooks(book.getTitle(),book.getAuthor(),book.getGenre().toString());
				}
			}
			model.addAttribute("books",books);
			if (!bookToBuy.getAuthor().equals("")){
					bookService.buyBook(bookToBuy);
				    model.addAttribute("message","Book selled successfully!");
				    return "employee";
		    }
				
		
		} catch (JAXBException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "employee";
	}
}
