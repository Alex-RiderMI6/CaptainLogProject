package AdvancedProgrammingBlogProject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
/**
 * Servlet implementation class CaptainLog
 */
@WebServlet("/CaptainLog")
public class CaptainLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaptainLog() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
        try (Connection conn = DBUtil.getConnection(getServletContext())) {

            String sql = "CREATE TABLE IF NOT EXISTS CaptainsLog (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "name TEXT, " +
                         "rank TEXT, " +
                         "log TEXT)";

            Statement stmt = conn.createStatement();
            stmt.execute(sql);

            System.out.println("✅ Database & table initialized");

        } catch (Exception e) {
            throw new ServletException("DB Initialization Failed", e);
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<html><body style=\"background-color: lightblue\">");

		out.println("<h2>Create Log</h2>");

		out.println("<form method='post' action='CaptainLog' id='myForm'>");

		out.println("Name: <input type='text' required name='name'/><br><br>");
		
		out.println("Rank: <input type='text' required name='rank'/><br><br>");
		
		out.println("log: <textarea name='log' row='6' cols='60' required> </textarea><br><br>");
		
		out.println("<input style=\"border-radius: 10px; background-color: DarkGoldenRod;\" type='submit' value='Record Log'/>");

		out.println("</form>");
		
		out.println("<a href=\"shownLogs\"><button style=\"border-radius: 10px; background-color: DarkGoldenRod;\">Logs</button><a/>");

		out.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String rank = request.getParameter("rank");
		String log = request.getParameter("log");

		try (Connection conn = DBUtil.getConnection(getServletContext())) {

            String sql = "INSERT INTO captainsLog(name, rank, log) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, rank);
            stmt.setString(3, log);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		response.sendRedirect("CaptainLog");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
