
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
import javax.swing.JOptionPane;

/**
 * Servlet implementation class HomeCheck
 */
@WebServlet("/HomeCheck")
public class HomeCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeCheck() {
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
			Statement pst9 = con.createStatement();
			Statement pst10 = con.createStatement();
			Statement pst11 = con.createStatement();
			HttpSession session = request.getSession(false);
			ArrayList<String> Fmessage = new ArrayList<>();
			ArrayList<Integer> FnumComment = new ArrayList<>();
			ArrayList<Integer> FnumTweets = new ArrayList<>();
			ArrayList<Integer> FnumLikes = new ArrayList<>();
			ArrayList<String> Fname = new ArrayList<>();
			ArrayList<String> Funame = new ArrayList<>();
			ArrayList<Integer> rId = new ArrayList<>();
			ArrayList<ArrayList<String>> Fcomment=new ArrayList<>();
			ArrayList<ArrayList<Integer>> Fcommentid=new ArrayList<>();
			String sql = "Select target_uname from follow where user_uname='" + session.getAttribute("uname")
					+ "' Order by id Desc";
			ResultSet rs = pst.executeQuery(sql);
			while (rs.next()) {
				sql="Select id from post where user_uname='" + rs.getString(1) + "' Order by id Desc";
				ResultSet rs2 = pst11.executeQuery(sql);
				while(rs2.next()) {
					rId.add(rs2.getInt(1));
					sql="Select message from post where id=" + rs2.getInt(1);
					ResultSet rs1=pst6.executeQuery(sql);
					rs1.next();
					Fmessage.add(rs1.getString(1));
					sql="Select user_uname from post where id=" + rs2.getInt(1);
					rs1=pst7.executeQuery(sql);
					rs1.next();
					Funame.add(rs1.getString(1));
					sql="Select name from user where uname='" + rs1.getString(1) + "'";
					rs1=pst8.executeQuery(sql);
					rs1.next();
					Fname.add(rs1.getString(1));
					sql="SELECT count(id) FROM comment where post_id= " + rs2.getInt(1);
					rs1=pst9.executeQuery(sql);
					rs1.next();
					FnumComment.add(rs1.getInt(1));
					sql="SELECT count(id) FROM retweet where post_id= " + rs2.getInt(1) ;
					rs1=pst2.executeQuery(sql);
					rs1.next();
					FnumTweets.add(rs1.getInt(1));
					sql="SELECT count(id) FROM likes where post_id= " + rs2.getInt(1);
					rs1=pst1.executeQuery(sql);
					rs1.next();
					FnumLikes.add(rs1.getInt(1));
					ArrayList<String> com=new ArrayList<>();
					ArrayList<Integer> comid=new ArrayList<>();
					sql="SELECT id FROM comment where post_id= " + rs2.getInt(1) + " Order by id Desc";
					ResultSet rs5=pst5.executeQuery(sql); 
					while(rs5.next()) {
						sql="SELECT user_uname FROM comment where id= " + rs5.getInt(1);
						ResultSet rs6=pst4.executeQuery(sql);
						rs6.next();
						sql="SELECT message FROM comment where id= " + rs5.getInt(1);
						ResultSet rs7=pst3.executeQuery(sql);
						rs7.next();
						com.add("@"+rs6.getString(1)+" says "+rs7.getString(1));
						comid.add(rs5.getInt(1));
					}
					Fcomment.add(com);
					Fcommentid.add(comid);
				}
				sql = "Select post_id from retweet where user_uname='" + rs.getString(1) + "' Order by id Desc";
				rs2 = pst10.executeQuery(sql);
				while (rs2.next()) {
					rId.add(rs2.getInt(1));
					sql = "Select message from post where id=" + rs2.getInt(1);
					ResultSet rs1 = pst11.executeQuery(sql);
					rs1.next();
					Fmessage.add(rs1.getString(1));
					sql="Select user_uname from post where id=" + rs2.getInt(1);
					rs1=pst7.executeQuery(sql);
					rs1.next();
					Funame.add(rs1.getString(1));
					sql = "Select name from user where uname='" + rs1.getString(1) + "'";
					rs1 = pst2.executeQuery(sql);
					rs1.next();
					Fname.add(rs1.getString(1));
					sql = "SELECT count(id) FROM comment where post_id= " + rs2.getInt(1);
					rs1 = pst3.executeQuery(sql);
					rs1.next();
					FnumComment.add(rs1.getInt(1));
					sql = "SELECT count(id) FROM retweet where post_id= " + rs2.getInt(1);
					rs1 = pst4.executeQuery(sql);
					rs1.next();
					FnumTweets.add(rs1.getInt(1));
					sql = "SELECT count(id) FROM likes where post_id= " + rs2.getInt(1);
					rs1 = pst5.executeQuery(sql);
					rs1.next();
					FnumLikes.add(rs1.getInt(1));
					ArrayList<String> com=new ArrayList<>();
					ArrayList<Integer> comid=new ArrayList<>();
					sql="SELECT id FROM comment where post_id= " + rs2.getInt(1) + " Order by id Desc";
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
					Fcomment.add(com);
					Fcommentid.add(comid);
				}
			}
			session.setAttribute("Fcomment", Fcomment); 
			session.setAttribute("Fcommentid", Fcommentid); 
			session.setAttribute("Fmessage", Fmessage);
			session.setAttribute("FnumComment", FnumComment);
			session.setAttribute("FnumTweets", FnumTweets);
			session.setAttribute("FnumLikes", FnumLikes);
			session.setAttribute("Fname", Fname);
			session.setAttribute("Funame", Funame);
			session.setAttribute("rId", rId);
			response.sendRedirect("http://localhost:8080/Twitter/home.jsp");
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
