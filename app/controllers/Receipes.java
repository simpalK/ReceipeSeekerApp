package controllers;
import java.util.List;
import java.util.ArrayList;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import play.Logger;
import play.data.validation.Check;
import play.data.validation.Match;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.With;
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
import models.*;

//@With(Secure.class)
public class Receipes extends Controller{
	public static List<RecIngredient> tempIng= new ArrayList<RecIngredient>();
	

	  public static void index() {
	    	List<Receipe> receipes = Receipe.all().fetch();
	    	render(receipes);
	  }

	    public static void add(Long ... fourIngId) {
	    	List<Ingredient> ingredients = new ArrayList<Ingredient>();
	    	Ingredient ingredient;
	    	Receipe receipe = new Receipe();
	    	receipe.save();
	    	
	    	for(int i=0;i<3;i++) {
	    	ingredient = Ingredient.findById(fourIngId[i]);
	        ingredients.add(ingredient);
	        receipe.addIngredientRec(ingredient, "0");
	    	}
	    	Receipes.edit(receipe.id);
	    	/*}
    			
    		else {
    			for(play.data.validation.Error e : Validation.errors())
    				Logger.error(e.message());
    			params.flash();
    			validation.keep();
    			//add();
    		}*/
	    	
	    	
	   render(ingredients,receipe);
		   
	    }
	    
	    public static void show(Long id) {
	    	Receipe receipe = Receipe.findById(id);
	    	assert receipe != null;
	    
	    	
	    	render(receipe);
	    }

	    public static void edit(Long receipeId) {
	    	Receipe receipe = Receipe.findById(receipeId);
		    render(receipe);
	    }
	    
	    public static void delete(Long receipeId) {
	    	Receipe receipe = Receipe.findById(receipeId);
	    	    	
	    	receipe.delete();
	    	index();	
	    }
	    
	    public static void update(Long receipeId,	
	    		String name,
	    		String link,
	    		String taste,
				String description,
				String country,
				String numofPersons,
				String firstIngQuant,
				String secondIngQuant,
				String thirdIngQuant
				) {
	    	
	    	Receipe receipe = Receipe.findById(receipeId);
	    	assert receipe != null;
	    	
	    	receipe.name = name;
	    	receipe.link = link;
	    	receipe.taste = taste;
	    	receipe.description= description;
	    	
	    	receipe.country=country;
	    	receipe.numofPersons=numofPersons;
	    	receipe.save();
	    	receipe.recIngredients.get(0).quantity=firstIngQuant;
	    	receipe.recIngredients.get(0).save();
	    	receipe.recIngredients.get(1).quantity=secondIngQuant;	
	    	receipe.recIngredients.get(1).save();
	    	receipe.recIngredients.get(2).quantity=thirdIngQuant;
	    	receipe.recIngredients.get(2).save();
	    	receipe.save();
	    	/*if(receipe.validateAndSave()) {
	    		show(receipeId);
	    	} else {
	     		for(play.data.validation.Error e : Validation.errors())
	    			Logger.error(e.message());
	    		params.flash();
	            validation.keep();
	            edit(receipeId);
	    	}*/
	    	Receipes.show(receipe.id);
	    }
	    
	    public static void addReceipe(String name,
	    								String taste,
	    								String description,
	    								String link,
	    								String country,
	    								String numofPersons
	    					
	    								) {
	    //int num=Integer.parseInt(numofPersons);
	    /*	Receipe receipe = new Receipe(name, taste,description,link,country,numofPersons);
	    		
	  
	    		if (receipe.validateAndSave())
	    			Receipes.index();
	    		else {
	    			for(play.data.validation.Error e : Validation.errors())
	    				Logger.error(e.message());
	    			params.flash();
	    			validation.keep();
	    			//add();
	    		}*/
	    	} 
	    	
	    public void addRecIngredients(String nameIng, String quantity)
		{
	    	//int quant= Integer.parseInt(quantity);
			/*Query query = JPA.em().createQuery("SELECT e FROM Ingredient e " +
					"WHERE e.name = ?1");
			query.setParameter(1, nameIng);
			RecIngredient newIng = new RecIngredient((Ingredient)query.getSingleResult(), quantity);
			newIng.save();
			tempIng.add(newIng);*/
		}
		
	
	
}
