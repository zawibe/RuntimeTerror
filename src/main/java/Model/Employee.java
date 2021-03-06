package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Employee implements Cloneable{

    private List<Task> taskList = new ArrayList<Task>();
    private SortedSet <String> projects = new TreeSet<String>();
    private String name;
    private String surname;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Employee(java.lang.String name, java.lang.String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Employee() {
	
	}

	public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
    	this.projects = new TreeSet<String>();
        this.taskList = taskList;
        for (Task task : taskList) {
			this.projects.add(task.getProjectName());
		}
        
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        this.projects.add(task.getProjectName());
    }
    
    public void removeTask(Task task) {
        this.taskList.remove(task);
        boolean removeProjectName = true;
        for (Task tsk : taskList) {
			if(tsk.getProjectName().equals(task.getProjectName())) {
				removeProjectName = false;
			}
		}
        if (removeProjectName) {
        	this.projects.remove(task.getProjectName());
        }
    }
    
    
    public void addTasks(List<Task> tasks) {
        this.taskList.addAll(tasks);
        for (Task task : tasks) {
			this.projects.add(task.getProjectName());
		}
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	    
	    public double getTotalHours() {
	        double sum=0;
	        for(Task task:taskList) {
	                sum+=task.getHours();
	        }
	        return sum;
	    }

		@Override
		public String toString() {
			return "Employee [taskList=" + taskList + ", name=" + name + ", surname=" + surname + "]";
		}

		public String getNameAndSurname() {
			return this.getName() + " " + this.getSurname();
		}

		public Set<String> getProjects(){
			return this.projects;
		}
		
		public Double getProjectHours(String project) {
			Double sum = 0.0;
			
			for (Task task : this.taskList) {
				if(task.getProjectName().equals(project)) {
					sum += task.getHours();
				}
			}
			
			return sum;
		}
		
		@Override
		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {

				Employee employee = new Employee(this.getName(), this.getSurname());
				employee.setTaskList(this.getTaskList());
				return employee;
			}
		}

}
