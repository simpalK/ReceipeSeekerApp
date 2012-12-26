package controllers;
import java.util.List;

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
import models.*;

//@With(Secure.class)
public class RecIngredients extends Controller{

	  public static void index() {
	    	List<RecIngredient> recIngredients = RecIngredient.all().fetch();
	    	render(recIngredients);
	  }

	    public static void add() {
	    	List<Ingredient> ingredients = Ingredient.all().fetch();
	    	
			   render(ingredients);
		   
	    }
	    
	    public static void show(Long id) {
	    	RecIngredient recIngredient = RecIngredient.findById(id);
	    	assert recIngredient != null;
	    
	    	
	    	render(recIngredient);
	    }

	    public static void edit(Long recIngredientId) {
	    	RecIngredient recIngredient = RecIngredient.findById(recIngredientId);
		    render(recIngredient);
	    }
	    
	    public static void delete(Long recIngredientId) {
	    	RecIngredient recIngredient = RecIngredient.findById(recIngredientId);
	    	    	
	    	recIngredient.delete();
	    	index();	
	    }
	    
	    public static void update(	Long recIngredientId,
									@Required String nameRecIng,
									@Required String quantity
									) {
	    	
	    	/*RecIngredient recIngredient = RecIngredient.findById(recIngredientId);
	    	assert recIngredient != null;
	    	
	    	recIngredient.nameRecIng = nameRecIng;
	    	recIngredient.quantity = quantity;
	    	
	    	if(recIngredient.validateAndSave()) {
	    		show(recIngredientId);
	    	} else {
	     		for(play.data.validation.Error e : Validation.errors())
	    			Logger.error(e.message());
	    		params.flash();
	            validation.keep();
	            edit(recIngredientId);
	    	}*/
	    }
	    
	    public static void addRecipeIngredient(String nameRecIng,
	    								String quantity
	    								) {
	    	
	    	/*	RecIngredient recIngredient = new RecIngredient(nameRecIng, quantity);
	    		
	  
	    		if (recIngredient.validateAndSave())
	    			RecIngredients.index();
	    		else {
	    			for(play.data.validation.Error e : Validation.errors())
	    				Logger.error(e.message());
	    			params.flash();
	    			validation.keep();
	    			add();
	    		}*/
	    	} 
	    
	    
	
	
}

