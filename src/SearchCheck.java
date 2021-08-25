
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class SearchCheck
 */
@WebServlet("/SearchCheck")
public class SearchCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCheck() {
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
		String search = request.getParameter("search");
		ArrayList<String> message = new ArrayList<>();
		ArrayList<String> uname = new ArrayList<>();
		ArrayList<String> name = new ArrayList<>();
		ArrayList<Integer> id = new ArrayList<>();
		ArrayList<Integer> numComment = new ArrayList<>();
		ArrayList<Integer> numTweets = new ArrayList<>();
		ArrayList<Integer> numLikes = new ArrayList<>();
		ArrayList<ArrayList<String>> scomment=new ArrayList<>();
		ArrayList<ArrayList<Integer>> scommentid=new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
			Statement pst = con.createStatement();
			Statement pst1 = con.createStatement();
			Statement pst2 = con.createStatement();
			Statement pst3 = con.createStatement();
			Statement pst4 = con.createStatement();
			Statement pst5 = con.createStatement();
			Statement pst6 = con.createStatement();
			Statement pst7 = con.createStatement();
			Statement pst8 = con.createStatement();
			HttpSession session = request.getSession(false);
			final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true);
			if (search!="") {
				String sql = "Select message from post where user_uname='" + search + "' Order by id Desc";
				ResultSet rs = pst.executeQuery(sql);
				while (rs.next()) {
					message.add(rs.getString(1));
					sql = "Select name from user where uname='" + search + "'";
					ResultSet rs1 = pst1.executeQuery(sql);
					rs1.next();
					name.add(rs1.getString(1));
				}
				if (message.size() == 0) {
					JOptionPane.showMessageDialog(dialog, "Username is Invaild");
					response.sendRedirect("http://localhost:8080/Twitter/profile.jsp");
				} else {
					sql = "Select id from post where user_uname='" + search + "' Order by id Desc";
					rs = pst2.executeQuery(sql);
					while (rs.next()) {
						id.add(rs.getInt(1));
						String sql2 = "SELECT count(id) FROM comment where post_id= " + rs.getInt(1);
						String sql3 = "SELECT count(id) FROM retweet where post_id= " + rs.getInt(1);
						String sql4 = "SELECT count(id) FROM likes where  post_id= " + rs.getInt(1);
						ResultSet rs2 = pst2.executeQuery(sql2);
						ResultSet rs3 = pst3.executeQuery(sql3);
						ResultSet rs4 = pst4.executeQuery(sql4);
						rs2.next();
						rs3.next();
						rs4.next();
						numComment.add(rs2.getInt(1));
						numTweets.add(rs3.getInt(1));
						numLikes.add(rs4.getInt(1));
						ArrayList<String> com=new ArrayList<>();
						ArrayList<Integer> comid=new ArrayList<>();
						sql="SELECT id FROM comment where post_id= " + rs.getInt(1) + " Order by id Desc";
						ResultSet rs5=pst5.executeQuery(sql); 
						while(rs5.next()) {
							sql="SELECT user_uname FROM comment where id= " + rs5.getInt(1);
							ResultSet rs6=pst6.executeQuery(sql);
							rs6.next();
							sql="SELECT message FROM comment where id= " + rs5.getInt(1);
							ResultSet rs7=pst7.executeQuery(sql);
							rs7.next();
							com.add("@"+rs6.getString(1)+" says "+rs7.getString(1));
							comid.add(rs5.getInt(1));
						}
						scomment.add(com);
						scommentid.add(comid);
					}
				}
			} else {
				String sql = "Select message from post Order by id Desc";
				ResultSet rs = pst.executeQuery(sql);
				while (rs.next()) {
					message.add(rs.getString(1));
				}
				sql = "Select user_uname from post Order by id Desc";
				rs = pst1.executeQuery(sql);
				while (rs.next()) {
					uname.add(rs.getString(1));
					sql = "Select name from user where uname='" + rs.getString(1) + "'";
					ResultSet rs2 = pst2.executeQuery(sql);
					rs2.next();
					name.add(rs2.getString(1));
				}
				sql = "Select id from post Order by id Desc";
				rs = pst2.executeQuery(sql);
				while (rs.next()) {
					id.add(rs.getInt(1));
					String sql2 = "SELECT count(id) FROM comment where post_id=" + rs.getString(1);
					String sql3 = "SELECT count(id) FROM retweet where  post_id=" + rs.getString(1);
					String sql4 = "SELECT count(id) FROM likes where   post_id=" + rs.getString(1);
					ResultSet rs2 = pst3.executeQuery(sql2);
					ResultSet rs3 = pst4.executeQuery(sql3);
					ResultSet rs4 = pst5.executeQuery(sql4);
					rs2.next();
					rs3.next();
					rs4.next();
					numComment.add(rs2.getInt(1));
					numTweets.add(rs3.getInt(1));
					numLikes.add(rs4.getInt(1));
					ArrayList<String> com=new ArrayList<>();
					ArrayList<Integer> comid=new ArrayList<>();
					sql="SELECT id FROM comment where post_id= " + rs.getInt(1) + " Order by id Desc";
					ResultSet rs5=pst6.executeQuery(sql); 
					while(rs5.next()) {
						sql="SELECT user_uname FROM comment where id= " + rs5.getInt(1);
						ResultSet rs6=pst7.executeQuery(sql);
						rs6.next();
						sql="SELECT message FROM comment where id= " + rs5.getInt(1);
						ResultSet rs7=pst8.executeQuery(sql);
						rs7.next();
						com.add("@"+rs6.getString(1)+" says "+rs7.getString(1));
						comid.add(rs5.getInt(1));
					}
					scomment.add(com);
					scommentid.add(comid);
				}
			}
			session.setAttribute("scomment", scomment); 
			session.setAttribute("scommentid", scommentid); 
			session.setAttribute("sMessage", message);
			session.setAttribute("sUname", uname);
			session.setAttribute("sName", name);
			session.setAttribute("sComment", numComment);
			session.setAttribute("sTweets", numTweets);
			session.setAttribute("sLikes", numLikes);
			session.setAttribute("sId", id);
			response.sendRedirect("http://localhost:8080/Twitter/search.jsp");
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
