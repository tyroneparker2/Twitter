

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
import javax.swing.JOptionPane;

/**
 * Servlet implementation class LikeCheck
 */
@WebServlet("/LikeCheck")
public class LikeCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
			Statement pst = con.createStatement();
			Statement pst1 = con.createStatement();
			Statement pst2 = con.createStatement();
			HttpSession session = request.getSession(false);
			String sql = "Select user_uname from post where id= " + id;
			ResultSet rs = pst.executeQuery(sql);
			rs.next();
			String uname=(String) session.getAttribute("uname");
			int i=0;
			if (uname.compareTo(rs.getString(1))==0) {
				response.sendRedirect("http://localhost:8080/Twitter/home.jsp");
			} else if (uname.compareTo(rs.getString(1))!=0) {
				i++;
				sql = "Select user_uname from likes where post_id= " + id;
				rs = pst1.executeQuery(sql);
				while(rs.next()) {
					if (uname.compareTo(rs.getString(1))==0) {
						sql="Delete from likes where (user_uname='" + uname + "' and post_id= " + id +")";
						pst2.execute(sql);
						RequestDispatcher rd = request.getRequestDispatcher("HomeCheck");
						rd.forward(request, response);
						i++;
						break;
					}
				}
				if(i==1){
					sql = "Insert into likes(user_uname,post_id) values('" + session.getAttribute("uname") + "',"
							+ id + ")";
					pst2.executeUpdate(sql);
					RequestDispatcher rd = request.getRequestDispatcher("HomeCheck");
					rd.forward(request, response);}
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
