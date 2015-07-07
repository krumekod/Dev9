package dev9Todo;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final AtomicLong counter = new AtomicLong(1);
    
    //TODO:  to be replaced by a database
    private HashMap<Long, Task> tasks = new HashMap<Long, Task>();
    
    @RequestMapping(value="/task", method=RequestMethod.GET)
    public Task getTask(@RequestParam(value="id") long id){
    	return tasks.get(id);
    }
    
    @RequestMapping(value="/task", method=RequestMethod.PUT)
    public long putTask(Task newTask){
    	
    	//Controller manages task id (must be unique)
    	long id = counter.addAndGet(0);
    	newTask.setId(id);
    	
    	//Map key is task id
    	tasks.put(id, newTask);
    	
    	return id;
    }
    
    @RequestMapping(value="/task", method=RequestMethod.DELETE)
    public void deleteTask(@RequestParam(value="id") long id){
    	
    	tasks.remove(id);
    }

    @RequestMapping(value="/task", method=RequestMethod.POST)
    public void updateTask(
    		@RequestParam(value="id") long id,
    		@RequestParam(value="isCompleted") boolean isCompleted){
    	
    	Task t = tasks.get(id);
    	t.setIsCompleted(isCompleted);
    }
}
