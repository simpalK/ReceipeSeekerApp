package controllers;
import java.util.ArrayList;
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

public class Recipes extends Controller {

	  public static void index() {
	    	List<Recipe> recipes = Recipe.all().fetch();
	    	List<Ingredient> ingredients = Ingredient.all().fetch();

	    	render(recipes,ingredients);
	  }

	    public static void add() {
	    	List<Ingredient> ingredients = Ingredient.all().fetch();

		   render(ingredients);
	    }
	    
	    public static void show(Long id) {
	    	Recipe recipe = Recipe.findById(id);
	    	assert recipe != null;
	    
	    	
	    	render(recipe);
	    }

	    public static void edit(Long recipeId) {
	    	Recipe recipe = Recipe.findById(recipeId);
		    render(recipe);
	    }
	    
	    public static void delete(Long recipeId) {
	    	Recipe recipe = Recipe.findById(recipeId);
	    	    	
	    	recipe.delete();
	    	index();	
	    }
	    
	    public static void update(	Long recipeId,
									@Required String nameRec,
									@Required String description,
									String link
									) {
	    	
	    	Recipe recipe = Recipe.findById(recipeId);
	    	assert recipe != null;
	    	
	    	recipe.nameRec = nameRec;
	    	recipe.description = description;
	    	recipe.link=link;
	    	
	    	if(recipe.validateAndSave()) {
	    		show(recipeId);
	    	} else {
	     		for(play.data.validation.Error e : Validation.errors())
	    			Logger.error(e.message());
	    		params.flash();
	            validation.keep();
	            edit(recipeId);
	    	}
	    }
	    
	    public static void addRecipe(String name, String des, User own, Integer like, String award, Integer rate, String link, String country, String taste)
		{
			
			
	    								
	    		Recipe recipe = new Recipe(name, des, own, like, award, rate, link, country, taste);
	    		
	  
	    		if (recipe.validateAndSave())
	    			Recipes.index();
	    		else {
	    			for(play.data.validation.Error e : Validation.errors())
	    				Logger.error(e.message());
	    			params.flash();
	    			validation.keep();
	    			add();
	    		}
	    	} 
	    	
}
