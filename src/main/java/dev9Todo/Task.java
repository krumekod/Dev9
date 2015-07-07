/*
 * Simple object representing Task data.  
 * TODO: Replace with ORM
 */

package dev9Todo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/*
 * Simple object representing task data
 */
public class Task {
	
	/*
	 * Privates
	 */
    private long id;
    private String taskName;
    private String priority;  //TODO:Make this an enum
    private boolean isCompleted;
    private final String notes;
	private DateTime dateCreated;

	/*
	 * Constructor:
	 * All field initialization is done via contructor
	 */
    public Task(long id, String taskName, String priority,  boolean isCompleted, String notes) {
    	
    	this.id = id;
    	this.taskName=taskName;
    	this.priority=priority;
    	this.isCompleted=isCompleted;
    	this.notes=notes;
    	
        this.dateCreated=DateTime.now();
    	 
    }

    /*
     * Identifier for Task
     */
    public long getId() {
        return id;
    }
    
    /*
     * There is no enforcement of unique id at this point
     * TODO: When hooked up to DB make unique contraint.
     */
    public void setId(long id) {
    	this.id=id;
    }
    
    /*
     * Easy to remember name for task
     */
    public String getName(){
    	return taskName;
    }
    
    /*
     * Task name can be updated
     */
    public void setName(String name){
    	this.taskName=name;
    }
    
    /*
     * Get task priority
     */
    public String getPriority() {
    	return priority;
    }
    
    public void setPriority(String priority){
    	String lp = priority.toLowerCase();
    	
    	switch(lp){
    	case "low": {this.priority=Priority.LOW; break;}
    	case "medium": {this.priority=Priority.MEDIUM; break;}
    	case "high":{this.priority=Priority.HIGH; break;}
    	default:{}
    	}
    		
    	
    	
    	this.priority=priority;
    }

    public boolean getIsComplete() {
    	return isCompleted;
    }
    
    public String getNotes() {
        return notes;
    }

	public String getDateCreated() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("MMMM d, yyyy");
		return dateCreated.toString(fmt);
		
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted= isCompleted;
		
	}
}