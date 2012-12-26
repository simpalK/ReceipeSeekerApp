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
import com.google.gson.JsonObject;
import play.mvc.Scope.Session;

@Entity
@Table(name="Receipe")
public class Receipe extends Model {
	
	@Required
	
	public String name;
	
	@Required 
	public String description;
	
	public String description2;
	public String description3;
	public String description4;
	
	@Required 
	public String taste;
	
    public String link;
	
	public String country;
	
	public int rate;
	
	@Required
	public User owner;
	
	public String currentUser="*****";
	
	@Required
	public Integer likes;
	
	@Required
	public String award;
	
	@Required
	public Integer rating;
	
	@Required
	public String numofPersons;
	
	@OneToMany(mappedBy="receipe", cascade=CascadeType.ALL)
	public List<RecIngredient> recIngredients;
	
	@OneToMany(mappedBy="receipe", cascade=CascadeType.ALL)
	public List<Comment> comments;
	//public Image img;
	
	public Receipe()
	{
		this.name = "";
		this.taste = "salty";
		this.description= "short description for your Recipe";
		this.description2="";
		this.description3="";
		this.description4="";
		this.link="provide a link if any";
		this.country="Select the country";
		this.likes=0;
		this.award="no";
		this.rating=0;
		this.owner=new User("not specified","not identified");
		this.numofPersons = "4";
		this.recIngredients = new LinkedList<RecIngredient>();
		this.rate=0;
		this.comments=new LinkedList<Comment>();
		//this.owner="no-session@gmail.com";
		//this.currentUser=Session.current().get("currentUser");
		/*if(Session.current() != null) {
		//	this.owner=Session.current().get("currentUser");
			this.owner="fdjfldjfldfjl";
		} else {

			this.owner="no-session@gmail.com";

      }*/
		
		
	}
	
	@Override 
	public Receipe delete()
	{
		for(RecIngredient p : RecIngredient.find("byReceipe", this).<RecIngredient>fetch()) {
		    p.delete();
		    JPA.em().merge(this);
		}
	return super.delete();
	}
	
	public static List<Receipe> listIng()
	{

		Query query = JPA.em().createQuery("SELECT e FROM Receipe e Order By likes++");
		return query.getResultList();
	}
	
	public Receipe singleIng(String name)
	{

		Query query = JPA.em().createQuery("SELECT e FROM Receipe e " +
				"WHERE e.name = ?1");
		query.setParameter(1, name);
		
		return (Receipe) query.getSingleResult();
		
	}
	public User getOwner(String name)
	{

		Query query = JPA.em().createQuery("SELECT e FROM User e " +
				"WHERE e.name = ?1");
		query.setParameter(1, name);
		
		return (User) query.getSingleResult();
		
	}
	
	@Override
	public String toString() {
		return name;
	}
	public void addIngredientRec(Ingredient ingredient, String quantity) {
	    
		RecIngredient recIngredient = new RecIngredient(ingredient,quantity,this);
        
		recIngredient.save();
	    this.recIngredients.add(recIngredient);
	    this.save();
	}
	
}
