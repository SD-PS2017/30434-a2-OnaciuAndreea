package main.entities;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="book")
public class Book {

	private Integer id;
	private String ISBN;
	private String title;
	private String author;
	private Genre genre;	
	private int quantity;
	private float price;
	
	public Book(int id,String ISBN, String title, String author,Genre genre, int quantity, float price) {
		this.id = id;
		this.ISBN=ISBN;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	public Book(String ISBN, String title, String author,Genre genre, int quantity, float price) {
		this.ISBN=ISBN;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.quantity = quantity;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", ISBN=" + ISBN + ", title=" + title + ", author=" + author + ", genre=" + genre
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}


	public Book(){
		
	}
	
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlAttribute
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@XmlElement
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	@XmlElement
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@XmlElement
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

}
