package sjsu.team21.MongoDBImpl;


import sjsu.team21.mongoModels.MUser;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class ProjectDetailsDAO extends MSaasDB{

final private String DATABASE_MONGO_PROJECT_COLLECTION= "DATABASE_MONGO_PROJECT_COLLECTION";
final private String DATABASE_MONGO_USER_COLLECTION= "DATABASE_MONGO_USER_COLLECTION";
	
	public void testDBData(){
		System.out.println(saasDB.getCollection(properties.getProperty(DATABASE_MONGO_PROJECT_COLLECTION)).findOne());
	}
	/*public void insertProjectDetails(String projectDetails){
		DBObject dbObject = (DBObject)JSON.parse(projectDetails);
		DBCollection projectDetailsCollection = saasDB.getCollection(properties.getProperty(DATABASE_MONGO_USER_COLLECTION));
		projectDetailsCollection.insert(dbObject);
	}*/
	public void insertProjectDetails(String projectDetails,String user_id, String project_id) {
		DBObject dbObject = (DBObject)JSON.parse(projectDetails);
		System.out.println(user_id);
		DBCollection userCollection = saasDB.getCollection(properties.getProperty(DATABASE_MONGO_USER_COLLECTION));
		BasicDBObject searchQuery = new BasicDBObject().append("user_id", user_id);
		userCollection.update(searchQuery, new BasicDBObject("$push",new BasicDBObject("projectDetails",dbObject)),false,false);
	}
	public String getTaskDetails(String project_id,String user_id) {
		String result = "";
		//DBObject dbObject = (DBObject)JSON.parse(taskDetails);
		DBCollection taskDetailsCollection = saasDB.getCollection(properties.getProperty(DATABASE_MONGO_USER_COLLECTION));
		//taskDetailsCollection.findOne(obj);
	return result;
	}
	public void editProjectDetails(String projectDetails, String user_id, String project_id) {
		DBCollection userCollection = saasDB.getCollection(properties.getProperty(DATABASE_MONGO_USER_COLLECTION));
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject().append("projectDetails.$", (DBObject)JSON.parse(projectDetails)));
		BasicDBObject searchQuery = new BasicDBObject().append("user_id", user_id).append("projectDetails.project_id", project_id);
		userCollection.update(searchQuery, newDocument);
	}
	public String getProjectsDetails(String user_id) {
		DBCollection taskDetailsCollection = saasDB.getCollection(properties.getProperty(DATABASE_MONGO_USER_COLLECTION));
		DBCursor dbCursor =taskDetailsCollection.find(new BasicDBObject("user_id", user_id));
		String msg ="";
		while (dbCursor.hasNext()) {
		      msg+=dbCursor.next().toString();
		    }
		System.out.println("Project Details :"+msg);
		return msg;
	}
	public void createDocument(MUser mUser) {
		// TODO Auto-generated method stub
		DBCollection userCollection = saasDB.getCollection(properties.getProperty(DATABASE_MONGO_USER_COLLECTION));
		Gson g = new Gson();
		
		DBObject dbObject = (DBObject)JSON.parse(g.toJson(mUser));
		userCollection.insert(dbObject);
		
	}
	public String getProjectDetails(String user_id, String project_id) {
		DBCollection userCollection = saasDB.getCollection(properties.getProperty(DATABASE_MONGO_USER_COLLECTION));
		BasicDBObject whereAll	 = new BasicDBObject(new BasicDBObject("projectDetails",
				new BasicDBObject("$elemMatch",
						new BasicDBObject("project_id",project_id))
							.append("user_id", new BasicDBObject("projectDetails", new BasicDBObject("$elemMatch",
									new BasicDBObject("project_id",project_id))))));
		
		BasicDBObject where	 = new BasicDBObject(new BasicDBObject("projectDetails",
				new BasicDBObject("$elemMatch",
						new BasicDBObject("project_id",project_id)))
							.append("user_id", user_id));
		
		
		BasicDBObject project = 
									new BasicDBObject("projectDetails",new BasicDBObject(
											"$elemMatch",new BasicDBObject("project_id",project_id)));
		DBObject d  = userCollection.findOne(where,project );
		System.out.println(d.toString());
		return d.toString();
	}
	public int getTaskCount(String queryField, String user_id, String project_id) {
		int result = 0;
		switch(queryField){
		case "T":
			
			
			break;
			default:
				
				
		}
		return result;
	}
	
}
