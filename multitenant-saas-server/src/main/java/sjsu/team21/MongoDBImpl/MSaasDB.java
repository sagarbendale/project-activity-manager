package sjsu.team21.MongoDBImpl;

import java.net.UnknownHostException;
import java.util.Properties;
import sjsu.team21.util.PropFileUtil;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;



public class MSaasDB {
	
	final private String DATABASE_MONGO_URI = "DATABASE_MONGO_URI";
	final private String DATABASE_MONGO_DB_NAME ="DATABASE_MONGO_DB_NAME";
	private MongoClient mongoClient ;
	protected DB saasDB;
	final protected Properties properties = PropFileUtil.getProperties();
	
	public MSaasDB() {
		 try {
			mongoClient = new MongoClient(new MongoClientURI(properties.getProperty(DATABASE_MONGO_URI)));
			saasDB = mongoClient.getDB(properties.getProperty(DATABASE_MONGO_DB_NAME));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	    
	}

}
