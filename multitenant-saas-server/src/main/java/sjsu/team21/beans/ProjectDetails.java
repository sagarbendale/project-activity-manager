package sjsu.team21.beans;

public class ProjectDetails {

	String name;
	String description;
	String preference;
	// String estimate;
	String project_id;
	private Tasks[] tasks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public Tasks[] getTasks() {
		return tasks;
	}

	public void setTasks(Tasks[] tasks) {
		this.tasks = tasks;
	}

	
	
	
}
