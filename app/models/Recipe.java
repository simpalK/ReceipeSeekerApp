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
@Table(name="Recipe")
public class Recipe extends Model{
	@Required
	private String nameRec;
	
	@Required
	private String description;
	
	@Required
	public User owner;
	
	@Required
	public Integer likes;
	
	@Required
	public String award;
	
	@Required
	public Integer rating;
	
	@Required 
	public String link;

	@Required
	@OneToMany
	public List<RecIngredient> listOfIng;
	
	

	public Recipe(String name, String des, User own, Integer like, String award, Integer rate, String link,List<RecIngredient> recIng)
	{
		this.nameRec = name;
		this.description = des;
		this.owner = own;
		this.likes = 0;
		this.award= "no";
		this.link = link;
		this.listOfIng.addAll(recIng);
		
	}
	
	public List<Recipe> listRec()
	{

		Query query = JPA.em().createQuery("SELECT * FROM Recipe");
		return query.getResultList();
	}
	
	public List<Recipe> selectedRec(List<Ingredient> nameIng)
	{
		List<Recipe> rec= new ArrayList<Recipe>();
		Query query = JPA.em().createQuery("SELECT e FROM Recipe e " +
				"WHERE e.listOfIng.get(0).toString() = ?1");
		for(Ingredient ing:nameIng)
		{
			query.setParameter(1, ing.nameIng);	
			rec.add((Recipe) query.getSingleResult());
		}
		
		return rec;
		
	}

	public List<Recipe> ownerRec(User own)
	{
		Query query = JPA.em().createQuery("SELECT e FROM Recipe e " +
				"WHERE e.owner = ?1");
    		query.setParameter(1, own.name);	
		return query.getResultList();
		
	}
	public User ownerRec(Recipe rec)
	{
		Query query = JPA.em().createQuery("SELECT e FROM User e " +
				"WHERE e.name = ?1");
    		query.setParameter(1, rec.owner);	
		return (User) query.getSingleResult();
		
	}
}
