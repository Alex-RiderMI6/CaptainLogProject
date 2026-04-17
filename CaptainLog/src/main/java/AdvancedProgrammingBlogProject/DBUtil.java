package AdvancedProgrammingBlogProject;
import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.ServletContext;
public class DBUtil {

	private static final String URL = "jdbc:sqlite:C:/data/CaptainsLog.db";

	public static Connection getConnection(ServletContext context) throws Exception {
		String path = context.getRealPath("/WEB-INF/CaptainsLog.db");
	    Class.forName("org.sqlite.JDBC");
	    return DriverManager.getConnection("jdbc:sqlite:" + path);
	}
}