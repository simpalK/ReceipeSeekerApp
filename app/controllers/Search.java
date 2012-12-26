package controllers;

import java.util.List;
import java.util.LinkedList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.With;
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

import java.util.Collections;
import java.util.Comparator;
import play.db.jpa.JPA;
import play.data.validation.Required;
import play.db.jpa.Model;
import models.*;
import models.*;
import java.util.Collections;
import java.util.Comparator;


public class Search extends Controller{
	
	public static void index(){
		List<Receipe> receipes = Receipe.all().fetch();
    	List<Ingredient> ingredients =Ingredient.all().fetch();
	
	Collections.sort(ingredients, new Comparator() {
		public int compare(Object o1, Object o2) {

	            String x1 = ((Ingredient) o1).nameIng.toLowerCase();
	            String x2 = ((Ingredient) o2).nameIng.toLowerCase();
		    

               	   return x1.compareTo(x2);
	         
		    }
	});

    	render(ingredients, receipes);
	}
	
	public static void search(String nameIng1, String nameIng2,
			String nameIng3,String nameIng4,
			String nameIng5,String nameIng6,
			String nameIng7,String nameIng8,
			String nameIng9,String nameIng10
			){
		List<Ingredient> ingredients =new ArrayList<Ingredient>();
		List<Receipe> receipes = Receipe.all().fetch();
		List<Receipe> receipesFound = new ArrayList<Receipe>();
		List<Receipe> receipesFoundAll = new ArrayList<Receipe>();
		List<RecIngredient> recIng = new ArrayList<RecIngredient>();
		boolean check=false;
		Query query;
		Ingredient ingredient;
		
		if(nameIng1!=null && !nameIng1.equals("None") && !nameIng1.isEmpty()){
		long idIng1 = Long.parseLong(nameIng1);
		ingredient=Ingredient.findById(idIng1);
		ingredients.add(ingredient);
			
		if(nameIng2!=null && !nameIng2.equals("None") && !nameIng2.isEmpty()){
		long idIng2 = Long.parseLong(nameIng2);
		ingredient=Ingredient.findById(idIng2);
		ingredients.add(ingredient);
			
		}
		if(nameIng3!=null && !nameIng3.equals("None") && !nameIng3.isEmpty()){
		long idIng3 = Long.parseLong(nameIng3);
		ingredient=Ingredient.findById(idIng3);
		ingredients.add(ingredient);
		}
		if(nameIng4!=null && !nameIng4.equals("None") && !nameIng4.isEmpty()){
		long idIng4 = Long.parseLong(nameIng4);
		ingredient=Ingredient.findById(idIng4);
		ingredients.add(ingredient);
		}
		if(nameIng5!=null && !nameIng5.equals("None") && !nameIng5.isEmpty()){
			long idIng5 = Long.parseLong(nameIng5);
			ingredient=Ingredient.findById(idIng5);
			ingredients.add(ingredient);
			}
		if(nameIng6!=null && !nameIng6.equals("None") && !nameIng6.isEmpty()){
			long idIng6 = Long.parseLong(nameIng6);
			ingredient=Ingredient.findById(idIng6);
			ingredients.add(ingredient);
			}
		if(nameIng7!=null && !nameIng7.equals("None") && !nameIng7.isEmpty()){
			long idIng7 = Long.parseLong(nameIng7);
			ingredient=Ingredient.findById(idIng7);
			ingredients.add(ingredient);
			}
		if(nameIng8!=null && !nameIng8.equals("None") && !nameIng8.isEmpty()){
			long idIng8 = Long.parseLong(nameIng8);
			ingredient=Ingredient.findById(idIng8);
			ingredients.add(ingredient);
			}
		if(nameIng9!=null && !nameIng9.equals("None") && !nameIng9.isEmpty()){
			long idIng9 = Long.parseLong(nameIng9);
			ingredient=Ingredient.findById(idIng9);
			ingredients.add(ingredient);
			}
		if(nameIng10!=null && !nameIng10.equals("None") && !nameIng10.isEmpty()){
			long idIng10 = Long.parseLong(nameIng10);
			ingredient=Ingredient.findById(idIng10);
			ingredients.add(ingredient);
			}
			for(Receipe receipe:receipes){
				for(RecIngredient rec:receipe.recIngredients){
					for(Ingredient each:ingredients){
					if(each.id==rec.recIng.id){
						if(!receipesFound.contains(receipe))
						receipesFound.add(receipe);
						
					}
				}
			}
				
		}
		for(Receipe receipe:receipes){
                for(RecIngredient ingRec:receipe.recIngredients)
                    recIng.add(ingRec);
                   if(recIng.size()==4){
                    for(Ingredient each:ingredients){       
                            if(each.id==recIng.get(0).recIng.id ||each.id==recIng.get(1).recIng.id || each.id==recIng.get(2).recIng.id ||each.id==recIng.get(3).recIng.id){
                                check=true;
                            }else{
                                check=false;
                                break;
                            }
                    }
                    if(check==true){
                        if(!receipesFoundAll.contains(receipe))
                        receipesFoundAll.add(receipe);
                        recIng.clear();

                    }
                    check=false;
		recIng.clear();
		} else if(recIng.size()==3){
			for(Ingredient each:ingredients){       
                            if(each.id==recIng.get(0).recIng.id ||each.id==recIng.get(1).recIng.id || each.id==recIng.get(2).recIng.id ){
                                check=true;
                            }else{
                                check=false;
                                break;
                            }
                    }
                    if(check==true){
                        if(!receipesFoundAll.contains(receipe))
                        receipesFoundAll.add(receipe);
                        recIng.clear();

                    }
                    check=false;
		    recIng.clear();
		    } else if(recIng.size()==2){
			for(Ingredient each:ingredients){       
                            if(each.id==recIng.get(0).recIng.id ||each.id==recIng.get(1).recIng.id ){
                                check=true;
                            }else{
                                check=false;
                                break;
                            }
                    }
                    if(check==true){
                        if(!receipesFoundAll.contains(receipe))
                        receipesFoundAll.add(receipe);
                        recIng.clear();

                    }
                    check=false;
		recIng.clear();
		} else if(recIng.size()==1){
			for(Ingredient each:ingredients){       
                            if(each.id==recIng.get(0).recIng.id){
                                check=true;
                            }else{
                                check=false;
                                break;
                            }
                    }
                    if(check==true){
                        if(!receipesFoundAll.contains(receipe))
                        receipesFoundAll.add(receipe);
                        recIng.clear();

                    }
                    check=false;
		    recIng.clear();
		}
            }
		Quiz q;
		if(!(receipesFoundAll.size()>0)){
		q = new Quiz(ingredients);
		q.save();
		}
		

            
		
		Collections.sort(receipesFoundAll, new Comparator() {
		public int compare(Object o1, Object o2) {

	            Integer x1 = ((Receipe) o1).likes;
	            Integer x2 = ((Receipe) o2).likes;
	            return x2.compareTo(x1);
	            
		    }
		});
		Collections.sort(receipesFound, new Comparator() {
		public int compare(Object o1, Object o2) {

	            Integer x1 = ((Receipe) o1).likes;
	            Integer x2 = ((Receipe) o2).likes;
	            return x2.compareTo(x1);
	            
		    }
		});
		render(ingredients,receipesFoundAll,receipesFound);
		} else {
			index();
		}
	}
	
	
}
