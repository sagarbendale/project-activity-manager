package sjsu.team21.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

import com.google.gson.Gson;

import scala.annotation.meta.setter;
import sjsu.team21.beans.ProjectDetails;
import sjsu.team21.beans.Tasks;
import sjsu.team21.beans.Users;
import sjsu.team21.models.User;
import sjsu.team21.service.ProjectDetailsService;

@SpringBootApplication
public class MultiTenantSaasApplication {

	public static void main(String[] args) {
		/*ProjectDetails d = new ProjectDetails();
		d.setDescription("This is project Desc");
		d.setEstimate("End Date");
		d.setName("My Projcet");
		d.setPreference("Waterfall");
		d.setProject_id("123");
		
		System.out.println(new Gson().toJson(d));*/
		
		/*Users u = new Users();
		u.setUser_id("1234");
		ProjectDetails projectDetails = new ProjectDetails();
		projectDetails.setDescription("This is project Desc");
		projectDetails.setName("My Projcet P");
		projectDetails.setPreference("Waterfall");
		projectDetails.setProject_id("P1234");
		
		
		Tasks t= new Tasks();
		t.setStatus("YES");
		t.setTaskName("MYTask 1");
		t.setTaskDesc("This is my task");
		
		Tasks t1= new Tasks();
		t1.setStatus("YES");
		t1.setTaskName("MYTask 2");
		t1.setTaskDesc("This is my task");
		
		Tasks t2= new Tasks();
		t2.setStatus("YES");
		t2.setTaskName("MYTask 3");
		t2.setTaskDesc("This is my task");
		
		Tasks[] tArray = {t1,t2,t};
		
		projectDetails.setTasks(tArray);
		
		
		
		ProjectDetails projectDetails2 = new ProjectDetails();
		projectDetails2.setDescription("This is project Desc");
		projectDetails2.setName("My Projcet Q");
		projectDetails2.setPreference("Waterfall");
		projectDetails2.setProject_id("Q1234");
		
		
		Tasks t20= new Tasks();
		t20.setStatus("YES");
		t20.setTaskName("MYTask 4");
		t20.setTaskDesc("This is my task");
		
		Tasks t12= new Tasks();
		t12.setStatus("YES");
		t12.setTaskName("MYTask 5");
		t12.setTaskDesc("This is my task");
		
		Tasks t22= new Tasks();
		t22.setStatus("YES");
		t22.setTaskName("MYTask 6");
		t22.setTaskDesc("This is my task");
		
		Tasks[] tArray2 = {t12,t22,t20};
		
		projectDetails2.setTasks(tArray2);
		
		ProjectDetails[] projectDetailsArr = {projectDetails,projectDetails2};
		u.setProjectDetails(projectDetailsArr);
		
		System.out.println(new Gson().toJson(u));*/
		SpringApplication.run(MultiTenantSaasApplication.class, args);
		
	}

}
