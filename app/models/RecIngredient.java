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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class RecIngredient extends Model {
	
	@Required
	@OneToOne
	public Ingredient recIng;
	
	@Required 
	public String quantity;
	
	@Required
    @ManyToOne
	public Receipe receipe;
	//public Image img;
	
	public RecIngredient(Ingredient recIng,String quantity, Receipe receipe)
	{
		this.recIng = recIng;
		this.quantity = quantity;
		this.receipe = receipe;
		//this.img = img;	
	}
	@Override
	public String toString() {
		return recIng.nameIng;
	}
}
