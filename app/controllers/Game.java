package controllers;


import play.*;
import play.mvc.*;

import java.util.*;

import models.Ingredient;

public class Game extends Controller {


    public static void game() {
    	List<Ingredient> ingredients = Ingredient.all().fetch();
    	ArrayList<Ingredient> four_ingr = new ArrayList<Ingredient>();
    	Random randomGenerator = new Random();
    	ArrayList<Integer> generatedNumb = new ArrayList<Integer>();
    	if(ingredients.size()>0)
    	{
    	generatedNumb.add(randomGenerator.nextInt(ingredients.size()));
    	
        int rdm;
    	for( int i = 0; i < 4; i++ ) {
            while( generatedNumb.contains(rdm = randomGenerator.nextInt(ingredients.size())) ) {	
            }
            generatedNumb.add(rdm);
        	four_ingr.add(ingredients.get(rdm));
        }
    	
    	}	
    		 render(four_ingr);       

    }

    }
    