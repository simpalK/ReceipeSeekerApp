package controllers;


import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Game extends Controller {

public static void index() {
		render();
	}
public static void introduction() {
		render();
	}
    public static void game() {
    	List<Ingredient> ingredients = Ingredient.all().fetch();
    	ArrayList<Ingredient> four_ingr = new ArrayList<Ingredient>();
    	Random randomGenerator = new Random();
    	ArrayList<Integer> generatedNumb = new ArrayList<Integer>();
    	if(ingredients.size()>0)
    	{
    	generatedNumb.add(randomGenerator.nextInt(ingredients.size()));
    	
        int rdm;
    	for( int i = 0; i < 5; i++ ) {
            while( generatedNumb.contains(rdm = randomGenerator.nextInt(ingredients.size())) ) {	
            }
            generatedNumb.add(rdm);
        	four_ingr.add(ingredients.get(rdm));
        }
    	
    	}	
    		 render(four_ingr);       

    }

public static void gamecomplex() {
    		List<Quiz> quizToPut = Quiz.all().fetch();
		List<Ingredient> ingredients = Ingredient.all().fetch();
    		ArrayList<Ingredient> four_ingr = new ArrayList<Ingredient>();
    		String listOfIng1="";
		String listOfIng2="";
		String listOfIng3="";
		String listOfIng4="";
    		if(quizToPut.size()>0)
    			{
    		Collections.shuffle(quizToPut);
		Quiz q = quizToPut.get(0);
		if(q.listOfIng1!=null){
		Ingredient ingredient1=Ingredient.findById(q.listOfIng1);
		listOfIng1=ingredient1.nameIng;
		}
		if(q.listOfIng2!=null){
		Ingredient ingredient2=Ingredient.findById(q.listOfIng2);
		listOfIng2=ingredient2.nameIng;
		}
		if(q.listOfIng3!=null){		
		Ingredient ingredient3=Ingredient.findById(q.listOfIng3);
		listOfIng3=ingredient3.nameIng;
		}		
		if(q.listOfIng4!=null){
		Ingredient ingredient4=Ingredient.findById(q.listOfIng4);
		listOfIng4=ingredient4.nameIng;
		}
   	        render(q,listOfIng1,listOfIng2,listOfIng3,listOfIng4);       
	} else {
		game();
		

    }
}

    }
    
