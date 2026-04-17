package AdvancedProgrammingBlogProject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEntry")
public class DeleteEntry extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public DeleteEntry() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String index = request.getParameter("id");
		if(index != null) {
			
			int idNumber = Integer.parseInt(index);
			try (Connection conn = DBUtil.getConnection(getServletContext())) {
	            String sql = "DELETE FROM captainsLog WHERE id = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, idNumber);
	            
	            stmt.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			response.sendRedirect("shownLogs");
		}
	}
}