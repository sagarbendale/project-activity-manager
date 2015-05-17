package sjsu.team21.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import sjsu.team21.MongoDBImpl.ProjectDetailsDAO;
import sjsu.team21.beans.KanbanStatus;
import sjsu.team21.beans.WaterFallPhaseStatus;
import sjsu.team21.beans.WaterFallTotalStatus;
import sjsu.team21.models.User;
import sjsu.team21.mongoModels.MProjectDetails;
import sjsu.team21.mongoModels.MUser;

public class ProjectDetailsService {

	ProjectDetailsDAO projectDetailsDAO = new ProjectDetailsDAO();

	public void insertProjectDetails(String projectDetails, String user_id,
			String project_id) {
		// TODO Auto-generated method stub
		projectDetailsDAO.insertProjectDetails(projectDetails, user_id,
				project_id);
	}

	public void editProjectDetails(String projectDetails, String user_id,
			String project_id) {
		// TODO Auto-generated method stub
		projectDetailsDAO.editProjectDetails(projectDetails, user_id,
				project_id);
	}

	/*
	 * public void insertTaskDetails(String taskDetails) { // TODO
	 * Auto-generated method stub
	 * projectDetailsDAO.insertTaskDetails(taskDetails); }
	 */
	public String getTaskDetails(String project_id, String user_id) {
		// TODO Auto-generated method stub
		return projectDetailsDAO.getTaskDetails(project_id, user_id);
	}

	public String getProjectDetails(String user_id, String project_id) {
		// TODO Auto-generated method stub
		return projectDetailsDAO.getProjectDetails(user_id, project_id);
	}

	public String getProjectsDetails(String user_id) {
		// TODO Auto-generated method stub
		return projectDetailsDAO.getProjectsDetails(user_id);
	}

	public void createDocument(User userModel) {
		// TODO Auto-generated method stub
		MUser mUser = new MUser();
		mUser.setUser_id(String.valueOf(userModel.getId()));
		// MProjectDetails mProjectDetails = new MProjectDetails();
		MProjectDetails[] mProjectDetailsArray = {};
		mUser.setProjectDetails(mProjectDetailsArray);
		new ProjectDetailsDAO().createDocument(mUser);

	}

	public Object getProjectStatus(String user_id, String project_id,
			String preference_id) {
		Object result = null;
		switch (preference_id) {

		case "1":
			result = getWaterFallStatus(user_id, project_id);
			break;
		case "2":
			break;
		case "3":
			result = getKanbannStatus(user_id, project_id);
			break;
		}
		return result;
	}

	private KanbanStatus getKanbannStatus(String user_id, String project_id) {

		// get task count from db
		int total_count = projectDetailsDAO.getTaskCount("T", user_id,
				project_id);
		// get requested count from db
		int requested_count = projectDetailsDAO.getTaskCount("Requested",
				user_id, project_id);
		// get in progress count from db
		int inProg_count = projectDetailsDAO.getTaskCount("In Progress",
				user_id, project_id);
		// get completed count from db
		int compd_count = projectDetailsDAO.getTaskCount("Completed", user_id,
				project_id);

		/*
		 * float perct_requested = (requested_count*100)/total_count; float
		 * perct_inProg = (inProg_count*100)/total_count; float perct_compd =
		 * (compd_count*100)/total_count;
		 */

		float perct_requested = 20;
		float perct_inProg = 30;
		float perct_compd = 50;

		KanbanStatus kanbanStatus = new KanbanStatus();
		kanbanStatus.setCompleted(perct_compd);
		kanbanStatus.setInProgress(perct_inProg);
		kanbanStatus.setRequested(perct_requested);

		return kanbanStatus;
	}

	private Object getWaterFallStatus(String user_id, String project_id) {
		List<Object> l = new ArrayList<Object>();
		//get task count from db
		int total_count =  projectDetailsDAO.getTaskCount("T",user_id, project_id);
		//get incomplete count from db
		int inComp_count =  projectDetailsDAO.getTaskCount("Incomplete",user_id, project_id);
		//get complete count from db
		int comp_count =  projectDetailsDAO.getTaskCount("Complete",user_id, project_id);
		
		/*float perct_inComp = (inComp_count*100)/total_count;
		float perct_comp = (comp_count*100)/total_count;*/
		
		//delete this for submission
		float perct_inComp = 30;
		float perct_comp = 70;
		
		
		WaterFallTotalStatus waterFallTotalStatus = new WaterFallTotalStatus();
		waterFallTotalStatus.setComplete(perct_comp);
		waterFallTotalStatus.setIncomplete(perct_inComp);
		l.add(waterFallTotalStatus);
		//Requirement Design Implementation Testing Maintenance
		
		/*int tot_req_task = projectDetailsDAO.getTaskCount("Requirement Total",user_id, project_id);
		int comp_req_task = projectDetailsDAO.getTaskCount("Requirement",user_id, project_id);
		float perct_req = (comp_req_task*100)/tot_req_task;
		
		int tot_des_task = projectDetailsDAO.getTaskCount("Design Total",user_id, project_id);
		int comp_des_task = projectDetailsDAO.getTaskCount("Design",user_id, project_id);
		float perct_des =   (comp_des_task*100)/tot_des_task;
		
		int tot_impl_task = projectDetailsDAO.getTaskCount("Implementation Total",user_id, project_id);
		int comp_impl_task = projectDetailsDAO.getTaskCount("Implementation",user_id, project_id);
		float perct_impl = (comp_impl_task*100)/tot_impl_task;
		
		int tot_test_task = projectDetailsDAO.getTaskCount("Testing Total",user_id, project_id);
		int comp_test_task = projectDetailsDAO.getTaskCount("Testing",user_id, project_id);
		float perct_test = (comp_test_task*100)/tot_test_task;
		
		int tot_main_task = projectDetailsDAO.getTaskCount("Maintenance Total",user_id, project_id);
		int comp_main_task = projectDetailsDAO.getTaskCount("Maintenance",user_id, project_id);
		float perct_main = (comp_main_task*100)/tot_main_task;*/
		
		
		
		//delete this for submission
		int tot_req_task = 2;
		int comp_req_task = 2;
		float perct_req = (comp_req_task*100)/tot_req_task;
		
		int tot_des_task = 2;
		int comp_des_task = 2;
		float perct_des =   (comp_des_task*100)/tot_des_task;
		
		int tot_impl_task = 2;
		int comp_impl_task = 2;
		float perct_impl = (comp_impl_task*100)/tot_impl_task;
		
		int tot_test_task = 2;
		int comp_test_task = 1;
		float perct_test = (comp_test_task*100)/tot_test_task;
		
		int tot_main_task = 2;
		int comp_main_task = 0;
		float perct_main = (comp_main_task*100)/tot_main_task;
		
		
		WaterFallPhaseStatus fallPhaseStatus = new WaterFallPhaseStatus();
		fallPhaseStatus.setRequirement(perct_req);
		fallPhaseStatus.setDesign(perct_des);
		fallPhaseStatus.setImplementation(perct_impl);
		fallPhaseStatus.setTesting(perct_test);
		fallPhaseStatus.setMaintenance(perct_main);
		l.add(fallPhaseStatus);
		
		return l;
	}

}
