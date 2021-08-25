
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
 * Servlet implementation class RegisterCheck
 */
@WebServlet("/RegisterCheck")
public class RegisterCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCheck() {
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
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String about = request.getParameter("about");
		String name = request.getParameter("name");
		if(about.compareTo("")==0) {
			about="none";
		}
		final JDialog dialog = new JDialog();
		dialog.setAlwaysOnTop(true);
		if (uname.compareTo("") == 0) {
			JOptionPane.showMessageDialog(dialog, "User Name cannot be empty.");
			response.sendRedirect("http://localhost:8080/Twitter/register.jsp");
		} else if (password.compareTo("") == 0) {
			JOptionPane.showMessageDialog(dialog, "Password cannot be empty.");
			response.sendRedirect("http://localhost:8080/Twitter/register.jsp");
		} else if (email.compareTo("") == 0) {
			JOptionPane.showMessageDialog(dialog, "Email cannot be empty.");
			response.sendRedirect("http://localhost:8080/Twitter/register.jsp");
		} else if (name.compareTo("") == 0) {
			JOptionPane.showMessageDialog(dialog, "Name cannot be empty.");
			response.sendRedirect("http://localhost:8080/Twitter/register.jsp");
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
				if (con != null) {
					System.out.println("Connected to the database");
				}
				Statement pst = con.createStatement();
				String sql = "Select * from user where uname='" + uname + "'";
				ResultSet rs = pst.executeQuery(sql);
				if (rs.next()) {
					JOptionPane.showMessageDialog(dialog, "User name is taken.");
					response.sendRedirect("http://localhost:8080/Twitter/register.jsp");

				} else {
					JOptionPane.showMessageDialog(dialog, "Register was successful");
					String sql1 = "Insert into user values('" + uname + "','" + password + "','" + name + "','" + email
							+ "','" + about + "',Null,Null)";
					pst.executeUpdate(sql1);
					HttpSession session = request.getSession();
					session.setAttribute("name", name);
					session.setAttribute("email", email);
					session.setAttribute("about", about);
					session.setAttribute("uname", uname);
					RequestDispatcher rd = request.getRequestDispatcher("ProfileCheck");
					rd.forward(request, response);
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
}
