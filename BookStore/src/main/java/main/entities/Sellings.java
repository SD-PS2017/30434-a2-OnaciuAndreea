package main.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="sellings")
@XmlAccessorType (XmlAccessType.FIELD)
public class Sellings {
	
	@XmlElement(name = "selling")
	private List<Selling> sellings=null;
	
	public List<Selling> getSellings(){
		return sellings;
	}
	
	public void setSellings(List<Selling> sellings){
		this.sellings=sellings;
	}
}
