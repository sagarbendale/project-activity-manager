package sjsu.team21.MongoDBImpl;



public class MSaasTestDAO extends MSaasDB{

	final private String DATABASE_MONGO_COLLECTION_NAME= "DATABASE_MONGO_COLLECTION_NAME";
	
	public void testDBData(){
		System.out.println(saasDB.getCollection(properties.getProperty(DATABASE_MONGO_COLLECTION_NAME)).findOne());
	}
}
