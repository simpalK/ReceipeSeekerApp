package models;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.joda.time.DateTime;
import org.joda.time.Days;

import play.db.jpa.JPA;
import play.data.validation.Required;
import play.db.jpa.Model;
@Entity
@Table(name="Ingredient")
public class Ingredient extends Model {
	
	@Required
	public String nameIng;
	
	@Required 
	public String unit;
	
	//public Image img;
	
	public Ingredient(String name, String unit)
	{
		this.nameIng = name;
		this.unit = unit;
		//this.img = img;	
	}
	
	@Override 
	public Ingredient delete()
	{
	return super.delete();
	}
	
	public List<Ingredient> listIng()
	{

		Query query = JPA.em().createQuery("SELECT * FROM Ingredient");
		return query.getResultList();
	}
	
	public Ingredient singleIng(String name)
	{

		Query query = JPA.em().createQuery("SELECT e FROM Ingredient e " +
				"WHERE e.nameIng = ?1");
		query.setParameter(1, name);
		
		return (Ingredient) query.getSingleResult();
		
	}
	@Override
	public String toString() {
		return nameIng;
	}

}
