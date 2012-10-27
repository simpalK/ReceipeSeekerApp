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

public class RecIngredient extends Model {
@Required
public Ingredient ing;

public Integer quantity1;

public Integer quantity2;

public Integer quantity4;

public RecIngredient(Ingredient ing,Integer quant1, Integer quant2,Integer quant4)
{
	this.ing = ing;
	this.quantity1 = quant1;
	this.quantity2 = quant2;
	this.quantity4 = quant4;
}
}
