package sjsu.team21.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sjsu.team21.MongoDBImpl.MSaasTestDAO;
import sjsu.team21.MySqlDBImpl.SchemaDao;
import sjsu.team21.MySqlDBImpl.UserDAO;
import sjsu.team21.beans.ReturnMessage;
import sjsu.team21.models.User;
import sjsu.team21.service.ProjectDetailsService;

import com.google.gson.Gson;

@ComponentScan("sjsu.team21")
@RestController
public class MultiTenantSaasAppController {
	Gson g = new Gson();

	@RequestMapping(value = "/testController", method = RequestMethod.GET)
	public String testController() {
		User u = new User();
		u.setEmail("amogh@gmail.com");
		u.setName("AMOGH");
		u.setPassword("123456");
		u.setPreference("1");
		Gson g = new Gson();
		System.out.println(g.toJson(u));
		return g.toJson(u);
	}

	@RequestMapping(value = "/testMongoDB", method = RequestMethod.GET)
	public String testMongoDB() {
		new MSaasTestDAO().testDBData();
		return "Success from Mongo";
	}

	@Autowired
	private UserDAO _userDao;
	@Autowired
	private SchemaDao _SchemaDao;

	@RequestMapping(value = "/save")
	@ResponseBody
	public String create(String email, String name) {
		try {
			// User user = new User(email, name);
			// _userDao.save(user);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "User succesfully saved!";
	}

	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public User authUser(@RequestBody String user) {
		ReturnMessage returnMessage = new ReturnMessage();
		returnMessage.setMessage("Fail");
		String s;
		try {
			s = g.toJson(returnMessage);
			System.out.println(s);
			System.out.println(user);
			User userModel = g.fromJson(user, User.class);
			User u = _userDao.getByEmail(userModel.getEmail());
			if (u != null && u.getPassword().equals(userModel.getPassword())) {
				returnMessage.setMessage("Success");
				returnMessage.setId(String.valueOf(u.getId()));
				s = g.toJson(u);
				System.out.println(s);
				return u;
			}
		} catch (Exception ex) {
			returnMessage.setMessage("Fail");
			s = g.toJson(returnMessage);
			return null;
		}
		return null;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@RequestParam String user_id) {
		ReturnMessage returnMessage = new ReturnMessage();
		returnMessage.setMessage("Fail");
		User u = _userDao.getById(Integer.parseInt(user_id));
		return u;
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	@ResponseBody
	public String registerUser(@RequestBody String user) {
		ReturnMessage returnMessage = new ReturnMessage();
		try {
			returnMessage.setMessage("Fail");
			System.out.println(user);
			Gson g = new Gson();
			User userModel = g.fromJson(user, User.class);
			_userDao.save(userModel);
			new ProjectDetailsService().createDocument(userModel);
			returnMessage.setMessage("Success");
			return g.toJson(returnMessage);
		} catch (Exception ex) {
			returnMessage.setMessage("Fail");
			return g.toJson(returnMessage);
		}
	}

	@RequestMapping(value = "/project/new", method = RequestMethod.POST)
	@ResponseBody
	public String saveProjectDetails(
			@RequestParam(value = "user_id") String user_id,
			@RequestParam(value = "project_id") String project_id,
			@RequestBody String projectDetails) {

		try {
			System.out.println(projectDetails);
			// insert this JSON in corresponding USERs document
			new ProjectDetailsService().insertProjectDetails(projectDetails,user_id,project_id);

			String s = g.toJson(new String("{message:Success}"));
			return s;
		} catch (Exception ex) {
			String s = g.toJson(new String("{message:Fail}"));
			return s;
		}
	}

	/*
	 * @RequestMapping(value="/project/task", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String saveTask(@RequestBody String taskDetails) {
	 * try { System.out.println(taskDetails); new
	 * ProjectDetailsService().insertTaskDetails(taskDetails); return "Success";
	 * } catch(Exception ex) { return "Fail"; } }
	 */

	@RequestMapping(value = "/project/task", method = RequestMethod.GET)
	@ResponseBody
	public String getTask(
			@RequestParam(value = "project_id") String project_id,
			@RequestParam(value = "user_id") String user_id) {
		String taskDetails = "";
		try {
			System.out.println(project_id);
			taskDetails = new ProjectDetailsService().getTaskDetails(
					project_id, user_id);
			return taskDetails;
		} catch (Exception ex) {
			return taskDetails;
		}
	}

	@RequestMapping(value = "/project/edit", method = RequestMethod.POST)
	@ResponseBody
	public String editPRojectDetails(
			@RequestParam(value = "user_id") String user_id,
			@RequestParam(value = "project_id") String project_id,
			@RequestBody String projectDetails) {
		try {
			System.out.println(projectDetails);
			// update this JSON in corresponding USERs document
			new ProjectDetailsService().editProjectDetails(projectDetails,
					user_id, project_id);
			return "Success";
		} catch (Exception ex) {
			return "Fail";
		}
	}

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	@ResponseBody
	public String getProjectDetails(
			@RequestParam(value = "user_id") String user_id) {
		ReturnMessage returnMessage = new ReturnMessage();
		try {
			System.out.println(user_id);
			returnMessage.setId(user_id);
			// insert this JSON in corresponding USERs document
			String result = new ProjectDetailsService()
					.getProjectsDetails(user_id);
			returnMessage.setMessage(result);
			String s = g.toJson(returnMessage);
			return result;
		} catch (Exception ex) {
			returnMessage.setMessage("Fail");
			String s = g.toJson(returnMessage);
			return s;
		}
	}

	@RequestMapping(value = "/user/addproject", method = RequestMethod.GET)
	@ResponseBody
	public String getProjectFields(
			@RequestParam(value = "preference_id") String preference) {
		System.out.println("Request Received Pref : " + preference);
		String jsonResult = _SchemaDao.getPreferenceColumns(preference);
		return jsonResult;
	}
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	@ResponseBody
	public String getProjectDetails(
			@RequestParam(value = "user_id") String user_id,
			@RequestParam(value = "project_id") String project_id) {
		try {
			System.out.println(user_id + " " +project_id);
			// update this JSON in corresponding USERs document
			String projectDetails = new ProjectDetailsService().getProjectDetails(user_id, project_id);
			return projectDetails;
		} catch (Exception ex) {
			return "Fail";
		}
	}
	
	@RequestMapping(value = "/project/status", method = RequestMethod.GET)
	@ResponseBody
	public Object getProjectStatus(
			@RequestParam(value = "user_id") String user_id,
			@RequestParam(value = "project_id") String project_id,
			@RequestParam(value = "preference_id") String preference_id) {
		try {
			System.out.println(user_id + " " +project_id+" "+preference_id);
			Object projectStatus = new ProjectDetailsService().getProjectStatus(user_id, project_id,preference_id);
			return projectStatus;
		} catch (Exception ex) {
			return "Fail";
		}
	}

}
