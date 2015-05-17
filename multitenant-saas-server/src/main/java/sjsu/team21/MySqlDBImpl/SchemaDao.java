package sjsu.team21.MySqlDBImpl;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sjsu.team21.util.Utilities;
import com.google.gson.Gson;

@Repository
@Transactional
public class SchemaDao {
	static String TenantTypeColumnTable = "TenantTypeColumn";
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public String getPreferenceColumns(String preference) {
		Query query = getSession().createSQLQuery(
				"select ColumnName, ColumnClassifier from TenantTypeColumn where "
						+ "PreferenceId = :preference").setParameter(
				"preference", preference);
		List result = query.list();
		String jsonResult = Utilities.getJsonFromList(result);
		return jsonResult;
	}
}