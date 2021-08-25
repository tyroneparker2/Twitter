
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class CommentCheck
 */
@WebServlet("/CommentCheck")
public class CommentCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		int id = Integer.valueOf(request.getParameter("id"));
		String comment = JOptionPane.showInputDialog(dialog, "Enter a Comment");
		if (comment==null) {
			response.sendRedirect("http://localhost:8080/Twitter/home.jsp");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
				Statement pst = con.createStatement();
				HttpSession session = request.getSession(false);
				String sql = "Insert into comment(user_uname,post_id,message) values('" + session.getAttribute("uname")
						+ "'," + id + ",'" + comment + "')";
				pst.executeUpdate(sql);
				RequestDispatcher rd = request.getRequestDispatcher("HomeCheck");
				rd.forward(request, response);
				con.close();
			} catch (ClassNotFoundException ex) {
				System.out.println("Could not find database driver class");
				ex.printStackTrace();
			} catch (SQLException ex) {
				System.out.println("An error occurred. Maybe user/password is invalid");
				ex.printStackTrace();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

		}
	}
}
