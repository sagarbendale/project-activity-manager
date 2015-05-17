package sjsu.team21.beans;

public class Users {

	private String user_id;
	private ProjectDetails[] projectDetails;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public ProjectDetails[] getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(ProjectDetails[] projectDetails) {
		this.projectDetails = projectDetails;
	}
	
	
}

