package sjsu.team21.mongoModels;


public class MUser {
	private String user_id;
	private MProjectDetails[] projectDetails;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public MProjectDetails[] getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(MProjectDetails[] projectDetails) {
		this.projectDetails = projectDetails;
	}
}
