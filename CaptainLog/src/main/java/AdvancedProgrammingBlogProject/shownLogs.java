package AdvancedProgrammingBlogProject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

@WebServlet("/shownLogs")
public class shownLogs extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shownLogs() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();

		out.println("<html><style>");
		
		out.println("</style><body style=\"background-color: lightblue\">");
		
		String sql = "SELECT id, name, rank, log FROM CaptainsLog ORDER BY id DESC";
		
		try (Connection conn = DBUtil.getConnection(getServletContext())) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String rank = rs.getString("rank");
                String log = rs.getString("log");
                
                out.println("<div style=\"border: 5px outset #FFD700; margin-bottom: 5%; margin-right: 25%; background-color: purple;\">");
                
                out.println("<h2 style=\"background-color: orange; margin-right: 50%; padding-left: 5%;\">" + name + "</h2>");
                out.println("<p style=\"background-color: yellow; margin-right: 50%; padding-left: 5%;\"><i><b>" + rank + "</b></i></p>"); //add rank to name up top and replace this one with a date
                
                out.println("<p  style=\"padding-left: 10%; padding-bottom: 10%; padding-right: 10%; margin-right: 5%; background-color: red; word-wrap: break-word;\">" + log + "</p>");

                out.println("<form method=\"post\" action=\"DeleteEntry\">");
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + id + "\">");
                out.println("<input type='submit' value='Delete Log'/>");
                out.println("</form>");
                out.println("</div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

		out.println("<a href=\"CaptainLog\"><button style=\"border-radius: 10px; background-color: DarkGoldenRod;\">Create Log</button><a/>"); //style the button, then add a delete button
		out.println("</body></html>");
	}
}
