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

import java.awt.Image;
import org.joda.time.DateTime;
import org.joda.time.Days;


import play.db.jpa.JPA;
import play.data.validation.Required;
import play.db.jpa.Model;
import com.google.gson.JsonObject;
import play.mvc.Scope.Session;


@Entity
@Table(name="Users")
public class User extends Model {
	public String name;
	public String email;
	
	public User(String name, String userid){
		this.name=name;
		this.email=userid;
	}
	
	
	public static User findByUserId(String userId)
	{
		Query query = JPA.em().createQuery("SELECT e FROM Users e " +
				"WHERE e.email = ?1");
    		query.setParameter(1, userId);	
		return (User) query.getSingleResult();
	}
	
}
