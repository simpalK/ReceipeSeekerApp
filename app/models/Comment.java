package models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
public class Comment extends Model {
	
	/**
	 * Author of the comment.
	 */
	
	@ManyToOne
    public User author;
	
	/**
	 * Date at which the comment was posted.
	 */
	//@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    public DateTime postedAt;
    
	/**
	 * Content of the comment.
	 */
    @Lob
    public String content;
    
    /**
     * Receipe which belongs to the comment.
     */
    @Required
    @ManyToOne
    public Receipe receipe;
    
public Comment(Receipe receipe,String content) {
    	
        this.receipe = receipe;
        this.postedAt = new DateTime();
this.content=content;
    }
    
    public Comment(User author, Receipe receipe) {
    	this.author = author;
        this.receipe = receipe;
        this.postedAt = new DateTime();
    }
 public Comment(User author, Receipe receipe,String tweet) {
    	this.author = author;
        this.receipe = receipe;
        this.postedAt = new DateTime();
        this.content=tweet;
    }
}
