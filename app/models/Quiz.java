package models;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.SimpleTimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.ElementCollection;

import java.awt.Image;

import org.joda.time.DateTime;
import org.joda.time.Days;


import play.db.jpa.JPA;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="Quiz")
public class Quiz extends Model{
	
	
	
	public Long listOfIng1;
	public Long listOfIng2;
	public Long listOfIng3;
	public Long listOfIng4;



	public Quiz(List<Ingredient> ingredients)
	{
		if(ingredients.size()>=4){
		listOfIng1=ingredients.get(0).id;
		listOfIng2=ingredients.get(1).id;
		listOfIng3=ingredients.get(2).id;
		listOfIng4=ingredients.get(3).id;
		} else if(ingredients.size()==3) {
		listOfIng1=ingredients.get(0).id;
		listOfIng2=ingredients.get(1).id;
		listOfIng3=ingredients.get(2).id;		
		}
		else if(ingredients.size()==2) {
		listOfIng1=ingredients.get(0).id;
		listOfIng2=ingredients.get(1).id;	
		}else if(ingredients.size()==1) {
		listOfIng1=ingredients.get(0).id;
		}
	}
	public void addQuizIng(List<Ingredient> ingredients)
	{
		
}
	
	@Override
	public Quiz delete() {
	return super.delete();
	}
	 
	
	
}
