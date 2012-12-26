package controllers;

import play.*;
import play.mvc.*;
import play.mvc.results.*;
import play.libs.OAuth2;
import play.libs.WS;
import com.google.gson.JsonObject;

import org.joda.time.DateTime;
import java.util.*;

import models.*;

public class Application extends Controller {
	

    public static OAuth2 FACEBOOK = new OAuth2(
            "https://graph.facebook.com/oauth/authorize",
            "https://graph.facebook.com/oauth/access_token",
            "95341411595",
            "8eff1b488da7fe3426f9ecaf8de1ba54"
    );

    public static void index() {
    	
        List<Comment> commentsall=Comment.all().fetch();
        List<Comment> comments = new ArrayList<Comment>();
		Collections.sort(commentsall, new Comparator() {
			public int compare(Object o1, Object o2) {

		            DateTime x1 = ((Comment) o1).postedAt;
		            DateTime x2 = ((Comment) o2).postedAt;

	               	   return x2.compareTo(x1);
		         
			    }
			});
	if(commentsall.size()>5){
		for(int i=0;i<6;i++){
			comments.add(commentsall.get(i));

		} 
		render(comments);
	} else {
		for(int i=0;i<commentsall.size();i++) {
		comments.add(commentsall.get(i));
		}
		render(comments);
	}
	}
}
