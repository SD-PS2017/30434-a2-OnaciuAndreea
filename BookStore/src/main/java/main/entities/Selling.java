package main.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="selling")
public class Selling {
	Book b;
	Date d;
	
   public Selling(){
	   
   }

@XmlElement(name="book")   
public Book getB() {
	return b;
}

public void setB(Book b) {
	this.b = b;
}

@XmlElement
public Date getD() {
	return d;
}

public void setD(Date d) {
	this.d = d;
}
   
   
	
}
