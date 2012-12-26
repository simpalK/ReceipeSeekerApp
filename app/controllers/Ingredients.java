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
import java.util.Collections;
import java.util.Comparator;
//@With(Secure.class)
public class Ingredients extends Controller{

	  public static void index() {
	    	List<Ingredient> ingredients = Ingredient.all().fetch();
		Collections.sort(ingredients, new Comparator() {
		public int compare(Object o1, Object o2) {

	            String x1 = ((Ingredient) o1).nameIng.toLowerCase();
	            String x2 = ((Ingredient) o2).nameIng.toLowerCase();
		    

               	   return x1.compareTo(x2);
	         
		    }
		});
	    	render(ingredients);
	  }

	    public static void add() {
		   render();
	    }
	    
	    public static void show(Long id) {
	    	Ingredient ingredient = Ingredient.findById(id);
	    	assert ingredient != null;
	    
	    	
	    	render(ingredient);
	    }

	    public static void edit(Long ingredientId) {
	    	Ingredient ingredient = Ingredient.findById(ingredientId);
		    render(ingredient);
	    }
	    
	    public static void delete(Long ingredientId) {
	    	Ingredient ingredient = Ingredient.findById(ingredientId);
	    	    	
	    	ingredient.delete();
	    	index();	
	    }
	    
	    public static void update(	Long ingredientId,
									@Required String nameIng,
									@Required String unit
									) {
	    	
	    	Ingredient ingredient = Ingredient.findById(ingredientId);
	    	assert ingredient != null;
	    	
	    	ingredient.nameIng = nameIng;
	    	ingredient.unit = unit;
	    	
	    	if(ingredient.validateAndSave()) {
	    		show(ingredientId);
	    	} else {
	     		for(play.data.validation.Error e : Validation.errors())
	    			Logger.error(e.message());
	    		params.flash();
	            validation.keep();
	            edit(ingredientId);
	    	}
	    }
	    
	    public static void addIngredient(String nameIng,
	    								String unit
	    								) {
	    	
	    		Ingredient ingredient = new Ingredient(nameIng, unit);
	    		
	  
	    		if (ingredient.validateAndSave())
	    			Ingredients.index();
	    		else {
	    			for(play.data.validation.Error e : Validation.errors())
	    				Logger.error(e.message());
	    			params.flash();
	    			validation.keep();
	    			add();
	    		}
	    	} 
	    	
	    
	
	
}

