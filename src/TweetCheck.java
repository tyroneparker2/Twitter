
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
 * Servlet implementation class TweetCheck
 */
@WebServlet("/TweetCheck")
public class TweetCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TweetCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		String tweet = JOptionPane.showInputDialog(dialog, "Enter a Tweet");
		if (tweet == null) {
			response.sendRedirect("http://localhost:8080/Twitter/profile.jsp");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
				Statement pst = con.createStatement();
				HttpSession session = request.getSession(false);
				String sql = "Insert into post(message,user_uname) values('" + tweet + "','"
						+ session.getAttribute("uname") + "')";
				pst.executeUpdate(sql);
				RequestDispatcher rd = request.getRequestDispatcher("ProfileCheck");
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
