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
import java.util.Collections;
import java.util.Comparator;

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
	    	List<Receipe> receipesAll = Receipe.all().fetch();
		for(Receipe each:receipesAll)
		if(each.name.length()==0||each.name==null)
			each.delete();
		List<Receipe> receipes = Receipe.all().fetch();

	 	Collections.sort(receipes, new Comparator() {
		public int compare(Object o1, Object o2) {

	            Integer x1 = ((Receipe) o1).likes;
	            Integer x2 = ((Receipe) o2).likes;
	            return x2.compareTo(x1);
	            
		    }
		});
	    	render(receipes);
	  }
	  public static void search() {
	    	List<Receipe> receipesAll = Receipe.all().fetch();
		for(Receipe each:receipesAll)
		if(each.name.length()==0||each.name==null)
			each.delete();
		List<Receipe> receipes = Receipe.all().fetch();
		Collections.sort(receipes, new Comparator() {
		public int compare(Object o1, Object o2) {

	            Integer x1 = ((Receipe) o1).likes;
	            Integer x2 = ((Receipe) o2).likes;
	            return x2.compareTo(x1);
	            
		    }
		});
	    	List<Ingredient> ingredients = Ingredient.all().fetch();
		Collections.sort(ingredients, new Comparator() {
		public int compare(Object o1, Object o2) {

	            String x1 = ((Ingredient) o1).nameIng.toLowerCase();
	            String x2 = ((Ingredient) o2).nameIng.toLowerCase();
		    

               	   return x1.compareTo(x2);
	         
		    }
		});
	    	render(ingredients, receipes);
	  }
	  public static void searchIng() {
		 /* List<Ingredient> ingredients = new ArrayList<Ingredient>();
	    	Ingredient ingredient;
	    	for(int i=0;i<1;i++) {
	    	ingredient = Ingredient.findById(ingQuant[i]);
	        ingredients.add(ingredient);
	        //receipe.addIngredientRec(ingredient, "0");
	    	}
		 // Ingredient ingredient=Ingredient.findById(ingQuant);;
		 */
	    	Receipes.index();
	    	
	  }
	    public static void add(Long[] fourIngId) {
	    	List<Ingredient> ingredients = new ArrayList<Ingredient>();
	    	Ingredient ingredient;
	    	Receipe receipe = new Receipe();
	    	receipe.save();
	    	
	    	for(int i=0;i<4;i++) {
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
public static void addQuiz(Long quizId) {
	    	List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Quiz quiz = Quiz.findById(quizId);
		
	    	Ingredient ingredient;
	    	Receipe receipe = new Receipe();
		receipe.save();
		if(quiz.listOfIng1!=null) {
	    	ingredient = Ingredient.findById(quiz.listOfIng1);
	        ingredients.add(ingredient);
	        receipe.addIngredientRec(ingredient, "1");
	    	} 
		if(quiz.listOfIng2!=null) {
	    	ingredient = Ingredient.findById(quiz.listOfIng2);
	        ingredients.add(ingredient);
	        receipe.addIngredientRec(ingredient, "1");
	    	} 
		if(quiz.listOfIng3!=null) {
	    	ingredient = Ingredient.findById(quiz.listOfIng3);
	        ingredients.add(ingredient);
	        receipe.addIngredientRec(ingredient, "1");
	    	} 
		if(quiz.listOfIng4!=null) {
	    	ingredient = Ingredient.findById(quiz.listOfIng4);
	        ingredients.add(ingredient);
	        receipe.addIngredientRec(ingredient, "1");
	    	} 
	    	receipe.save();
		
	    	Receipes.editQuiz(receipe.id,quiz);
	    	/*}
    			
    		else {
    			for(play.data.validation.Error e : Validation.errors())
    				Logger.error(e.message());
    			params.flash();
    			validation.keep();
    			//add();
    		}*/
	    	
	    	
	   render(ingredients,receipe,quiz);
		   
	    }
	    
	public static void editQuiz(Long receipeId,Quiz quiz) {
	    	Receipe receipe = Receipe.findById(receipeId);
		for(RecIngredient each:receipe.recIngredients)
			each.quantity="1";
		receipe.save();
		    render(receipe,quiz);
	    }
public static void updateQuiz(Long receipeId,Quiz quiz,	
	    		String name,
	    		String link,
	    		String taste,
				String description,
				String country,
				String numofPersons,
				String firstIngQuant,
				String secondIngQuant,
				String thirdIngQuant,
				String fourthIngQuant,
				String fifthIngQuant,
				String sixthIngQuant,
				String seventhIngQuant,
				String eightIngQuant,
				String ninthIngQuant,
				String tenthIngQuant,
				String username, 
				String userid
				
				) {
	    	int lenght=0;
	    	Receipe receipe = Receipe.findById(receipeId);
	    	assert receipe != null;

		if(username!=null || username!=""){
	    	receipe.owner.name=username;
		receipe.owner.email=userid;
}
		

	    	receipe.name = name;
	    	receipe.link = link;
	    	receipe.taste = taste;
		receipe.award = "Master Chef";
	    	lenght=description.length();
	    	
	    	if(lenght<255)
	    		receipe.description=(String)description.subSequence(0,lenght);
	    	else if(255<lenght && lenght<510)
	    	{
	    		receipe.description=(String)description.subSequence(0,255);
	    		receipe.description2=(String)description.subSequence(255,lenght);
	    	}	
	    	
	    	else if(510<lenght && lenght<765)
	    	{
	    		receipe.description=(String)description.subSequence(0,255);
	    		receipe.description2=(String)description.subSequence(255,510);
	    		receipe.description3=(String)description.subSequence(510,765);
	    	}
	    	else
	    	{
	    		receipe.description=(String)description.subSequence(0,255);
	    		receipe.description2=(String)description.subSequence(255,510);
	    		receipe.description3=(String)description.subSequence(510,765);
	    		receipe.description4=(String)description.subSequence(765,1010);
	    	}
	    	
	    	receipe.country=country;
	    	receipe.numofPersons=numofPersons;
	    	receipe.save();

		if(quiz.listOfIng1 !=null){
	    	receipe.recIngredients.get(0).quantity=firstIngQuant;
	    	receipe.recIngredients.get(0).save();
		}
		if(quiz.listOfIng2 !=null && secondIngQuant!="None"){
	    	receipe.recIngredients.get(1).quantity=secondIngQuant;
	    	receipe.recIngredients.get(1).save();
		}

	    	if(quiz.listOfIng3!=null && thirdIngQuant !="None"){
	    	receipe.recIngredients.get(2).quantity=thirdIngQuant;
	    	receipe.recIngredients.get(2).save();
		}
		if(quiz.listOfIng4!=null && fourthIngQuant !="None") {
	    	receipe.recIngredients.get(3).quantity=fourthIngQuant;
	    	receipe.recIngredients.get(3).save();
		}
		
		
	    	receipe.save();
		Comments.update(receipe.id, receipe.name +" is added/edited by " + receipe.owner.name);
	    	/*if(receipe.validateAndSave()) {
	    		show(receipeId);
	    	} else {
	     		for(play.data.validation.Error e : Validation.errors())
	    			Logger.error(e.message());
	    		params.flash();
	            validation.keep();
	            edit(receipeId);
	    	}*/
		//Quiz.delete(quiz.id);
		quiz.delete();
	    	Receipes.index();
	    }
	    
	    
	    public static void show(Long id) {
	    	Receipe receipe = Receipe.findById(id);
	    	assert receipe != null;
	    //	receipe.likes=receipe.likes+2-1;
	    	receipe.likes++;
	    	if(receipe.award!="Master Chef"){
	    	if(receipe.likes==10)
	    		receipe.award= "Dishwasher";
	    		
	    	else if(receipe.likes==30)
	    		receipe.award= "Chef Assistant";
	    	
	    	else if(receipe.likes==40)
	    		receipe.award= "Chef";
	    	
	    	else if(receipe.likes==80)
	    		receipe.award= "Master Chef";
}
	    
	    	receipe.save();
	        Comment com =new Comment(receipe, receipe.name + " is viewed recently");
                 com.save();
	    	//Comments.updateShow(receipe.id, receipe.name + " is viewed recently");
	    	render(receipe);
	    }
	    public static void increaseRate(Long id) {
	    	Receipe receipe = Receipe.findById(id);
	    //	receipe.rate++;
	    	assert receipe != null;
	    	
	    	
	    	render(receipe);
	    }

	    public static void edit(Long receipeId) {
	    	Receipe receipe = Receipe.findById(receipeId);
		for(RecIngredient each:receipe.recIngredients)
			each.quantity="1";
		receipe.save();

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
				String thirdIngQuant,
				String fourthIngQuant,
				String username, 
				String userid
				
				) {
	    	int lenght=0;
	    	Receipe receipe = Receipe.findById(receipeId);
	    	assert receipe != null;

		if(username!=null || username!=""){
	    	receipe.owner.name=username;
		receipe.owner.email=userid;
}
		

	    	receipe.name = name;
	    	receipe.link = link;
	    	receipe.taste = taste;
	    	lenght=description.length();
	    	
	    	if(lenght<255)
	    		receipe.description=(String)description.subSequence(0,lenght);
	    	else if(255<lenght && lenght<510)
	    	{
	    		receipe.description=(String)description.subSequence(0,255);
	    		receipe.description2=(String)description.subSequence(255,lenght);
	    	}	
	    	
	    	else if(510<lenght && lenght<765)
	    	{
	    		receipe.description=(String)description.subSequence(0,255);
	    		receipe.description2=(String)description.subSequence(255,510);
	    		receipe.description3=(String)description.subSequence(510,765);
	    	}
	    	else
	    	{
	    		receipe.description=(String)description.subSequence(0,255);
	    		receipe.description2=(String)description.subSequence(255,510);
	    		receipe.description3=(String)description.subSequence(510,765);
	    		receipe.description4=(String)description.subSequence(765,1010);
	    	}
	    	
	    	receipe.country=country;
	    	receipe.numofPersons=numofPersons;
	    	receipe.save();
	    	receipe.recIngredients.get(0).quantity=firstIngQuant;
	    	receipe.recIngredients.get(0).save();
	    	receipe.recIngredients.get(1).quantity=secondIngQuant;	
	    	receipe.recIngredients.get(1).save();
	    	receipe.recIngredients.get(2).quantity=thirdIngQuant;
	    	receipe.recIngredients.get(2).save();
	    	receipe.recIngredients.get(3).quantity=fourthIngQuant;
	    	receipe.recIngredients.get(3).save();
	    	receipe.save();
		Comments.update(receipe.id, receipe.name +" is added/edited by " + receipe.owner.name);
	    	/*if(receipe.validateAndSave()) {
	    		show(receipeId);
	    	} else {
	     		for(play.data.validation.Error e : Validation.errors())
	    			Logger.error(e.message());
	    		params.flash();
	            validation.keep();
	            edit(receipeId);
	    	}*/
	    	Receipes.index();
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
