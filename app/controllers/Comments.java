package controllers;

import play.mvc.Controller;
import play.mvc.With;
import models.*;


public class Comments extends Controller {
    public static void add(Long id){
    	Receipe receipe = Receipe.findById(id);
        render(receipe);
    }
	
    public static void delete(Long receipeId, Long commentId){
    	Receipe receipe = Receipe.findById(receipeId);
    	Comment comment = Comment.findById(commentId);
    	assert receipe != null;
    	assert comment != null;
    	
    	comment.delete();
    	flash.success("Comment sucessfully deleted.");
    	add(receipe.id);
    }
public static void updateShow(Long receipeId, String content ) {
    	Receipe receipe = Receipe.findById(receipeId);

    	assert receipe != null;
    	
	Comment comment = new Comment(receipe,content);
        comment.save();
receipe.comments.add(comment);
receipe.save();
        //flash.success("Thanks for posting %s", receipe.owner.name);
		Receipes.show(receipe.id);
    	/*if(comment.validateAndSave()) {
    		flash.success("Thanks for posting %s", author.toString());
    		add(receipe.id);
    	} else {
    		params.flash();
    		validation.keep();
			add(receipe.id);
    	}*/
    
}
    public static void update(Long receipeId, String content ) {
    	Receipe receipe = Receipe.findById(receipeId);
User author=new User("test","test");
author.save();
    	assert receipe != null;
    	
	Comment comment = new Comment(author, receipe);
    	comment.content = content;
        comment.save();
receipe.comments.add(comment);
receipe.save();
        //flash.success("Thanks for posting %s", receipe.owner.name);
		Receipes.show(receipe.id);
    	/*if(comment.validateAndSave()) {
    		flash.success("Thanks for posting %s", author.toString());
    		add(receipe.id);
    	} else {
    		params.flash();
    		validation.keep();
			add(receipe.id);
    	}*/
    }
}
