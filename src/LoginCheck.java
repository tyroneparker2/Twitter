
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

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
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
			if (con != null) {
				System.out.println("Connected to the database");
			}
			Statement pst = con.createStatement();
			String sql = "Select * from user where uname='" + uname + "' and password='" + password + "'";
			ResultSet rs = pst.executeQuery(sql);
			final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);
			if (rs.next()) {
				JOptionPane.showMessageDialog(dialog, "Login Successful");
				HttpSession session = request.getSession();
				session.setAttribute("name", rs.getString(3));
				session.setAttribute("email", rs.getString(4));
				session.setAttribute("about", rs.getString(5));
				session.setAttribute("uname", uname);
				RequestDispatcher rd = request.getRequestDispatcher("ProfileCheck");
				rd.forward(request, response);
			} else {
				JOptionPane.showMessageDialog(dialog, "Username And Password Don't Match");
				response.sendRedirect("http://localhost:8080/Twitter/login.jsp");
			}
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
