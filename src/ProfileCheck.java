

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
 * Servlet implementation class ProfileCheck
 */
@WebServlet("/ProfileCheck")
public class ProfileCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
			Statement pst=con.createStatement();
			Statement pst1=con.createStatement();
			Statement pst2=con.createStatement();
			Statement pst3=con.createStatement();
			Statement pst4=con.createStatement();
			Statement pst5=con.createStatement();
			Statement pst6=con.createStatement();
			Statement pst7=con.createStatement();
			Statement pst8=con.createStatement();
			Statement pst9=con.createStatement();
			Statement pst10=con.createStatement();
			Statement pst11=con.createStatement();
			HttpSession session=request.getSession(false); 
			ArrayList<String> message=new ArrayList<>();
			ArrayList<ArrayList<String>> comment=new ArrayList<>();
			ArrayList<ArrayList<Integer>> commentid=new ArrayList<>();
			ArrayList<ArrayList<String>> fcomment=new ArrayList<>();
			ArrayList<ArrayList<Integer>> fcommentid=new ArrayList<>();
			ArrayList<ArrayList<String>> lcomment=new ArrayList<>();
			ArrayList<ArrayList<Integer>> lcommentid=new ArrayList<>();
			ArrayList<Integer> numComment=new ArrayList<>();
			ArrayList<Integer> numTweets=new ArrayList<>();
			ArrayList<Integer> numLikes=new ArrayList<>();
			ArrayList<String> fname=new ArrayList<>();
			ArrayList<String> funame=new ArrayList<>();
			ArrayList<String> fmessage=new ArrayList<>();
			ArrayList<Integer> fnumComment=new ArrayList<>();
			ArrayList<Integer> fnumTweets=new ArrayList<>();
			ArrayList<Integer> fnumLikes=new ArrayList<>();
			ArrayList<String> following=new ArrayList<>();
			ArrayList<String> followingname=new ArrayList<>();
			ArrayList<String> follower=new ArrayList<>();
			ArrayList<String> followername=new ArrayList<>();
			ArrayList<String> lmessage = new ArrayList<>();
			ArrayList<Integer> lnumComment = new ArrayList<>();
			ArrayList<Integer> lnumTweets = new ArrayList<>();
			ArrayList<Integer> lnumLikes = new ArrayList<>();
			ArrayList<String> lname = new ArrayList<>();
			ArrayList<String> luname = new ArrayList<>();
			ArrayList<Integer> id=new ArrayList<>();
			ArrayList<Integer> lId=new ArrayList<>();
			ArrayList<Integer> fId=new ArrayList<>();
			String sql="Select message from post where user_uname='" +session.getAttribute("uname") + "' Order by id Desc";
			ResultSet rs=pst.executeQuery(sql); 
			while(rs.next()) {
				message.add(rs.getString(1));
			}
			sql="Select id from post where user_uname='" +session.getAttribute("uname") + "' Order by id Desc";
			rs=pst1.executeQuery(sql);
			while(rs.next()) {
				id.add(rs.getInt(1));
				String sql2="SELECT count(id) FROM comment where post_id= " + rs.getInt(1);
				String sql3="SELECT count(id) FROM retweet where post_id= " + rs.getInt(1) ;
				String sql4="SELECT count(id) FROM likes where post_id= " + rs.getInt(1);
				String sql5="SELECT id FROM comment where post_id= " + rs.getInt(1) + " Order by id Desc";
				ResultSet rs2=pst2.executeQuery(sql2);
				ResultSet rs3=pst3.executeQuery(sql3); 
				ResultSet rs4=pst4.executeQuery(sql4); 
				ResultSet rs5=pst5.executeQuery(sql5); 
				rs2.next();
				rs3.next();
				rs4.next();
				ArrayList<String> com=new ArrayList<>();
				ArrayList<Integer> comid=new ArrayList<>();
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
				comment.add(com);
				commentid.add(comid);
				numComment.add(rs2.getInt(1));
				numTweets.add(rs3.getInt(1));
				numLikes.add(rs4.getInt(1));
			}	
			sql="Select target_uname from follow where user_uname='" +session.getAttribute("uname") + "' Order by id Desc";
			rs=pst1.executeQuery(sql);
			while(rs.next()) {
				String sql2="Select name from user where uname='" + rs.getString(1)+ "'";
				ResultSet rs2=pst2.executeQuery(sql2); 
				rs2.next();
				followingname.add(rs2.getString(1));
				following.add(rs.getString(1));
			}
			sql="Select user_uname from follow where target_uname='" +session.getAttribute("uname") + "' Order by id Desc";
			rs=pst1.executeQuery(sql);
			while(rs.next()) {
				String sql2="Select name from user where uname='" + rs.getString(1)+ "'";
				ResultSet rs2=pst2.executeQuery(sql2); 
				rs2.next();
				followername.add(rs2.getString(1));
				follower.add(rs.getString(1));
			}
			sql="Select post_id from retweet where user_uname='" + session.getAttribute("uname") + "' Order by id Desc";
			rs=pst1.executeQuery(sql);
			while(rs.next()) {
				fId.add(rs.getInt(1));
				sql="Select message from post where id=" + rs.getInt(1);
				ResultSet rs1=pst6.executeQuery(sql);
				rs1.next();
				fmessage.add(rs1.getString(1));
				sql="Select user_uname from post where id=" + rs.getInt(1);
				rs1=pst7.executeQuery(sql);
				rs1.next();
				funame.add(rs1.getString(1));
				sql="Select name from user where uname='" + rs1.getString(1) + "'";
				rs1=pst8.executeQuery(sql);
				rs1.next();
				fname.add(rs1.getString(1));
				sql="SELECT count(id) FROM comment where post_id= " + rs.getInt(1);
				rs1=pst9.executeQuery(sql);
				rs1.next();
				fnumComment.add(rs1.getInt(1));
				sql="SELECT count(id) FROM retweet where post_id= " + rs.getInt(1) ;
				rs1=pst10.executeQuery(sql);
				rs1.next();
				fnumTweets.add(rs1.getInt(1));
				sql="SELECT count(id) FROM likes where post_id= " + rs.getInt(1);
				rs1=pst11.executeQuery(sql);
				rs1.next();
				fnumLikes.add(rs1.getInt(1));
				ArrayList<String> com=new ArrayList<>();
				ArrayList<Integer> comid=new ArrayList<>();
				sql="SELECT id FROM comment where post_id= " + rs.getInt(1) + " Order by id Desc";
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
				fcomment.add(com);
				fcommentid.add(comid);
			}
			sql="Select post_id from likes where user_uname='" + session.getAttribute("uname") + "' Order by id Desc";
			rs=pst11.executeQuery(sql);
			while(rs.next()) {
				lId.add(rs.getInt(1));
				sql="Select message from post where id=" + rs.getInt(1);
				ResultSet rs1=pst6.executeQuery(sql);
				rs1.next();
				lmessage.add(rs1.getString(1));
				sql="Select user_uname from post where id=" + rs.getInt(1);
				rs1=pst7.executeQuery(sql);
				rs1.next();
				luname.add(rs1.getString(1));
				sql="Select name from user where uname='" + rs1.getString(1) + "'";
				rs1=pst8.executeQuery(sql);
				rs1.next();
				lname.add(rs1.getString(1));
				sql="SELECT count(id) FROM comment where post_id= " + rs.getInt(1);
				rs1=pst9.executeQuery(sql);
				rs1.next();
				lnumComment.add(rs1.getInt(1));
				sql="SELECT count(id) FROM retweet where post_id= " + rs.getInt(1) ;
				rs1=pst2.executeQuery(sql);
				rs1.next();
				lnumTweets.add(rs1.getInt(1));
				sql="SELECT count(id) FROM likes where post_id= " + rs.getInt(1);
				rs1=pst1.executeQuery(sql);
				rs1.next();
				lnumLikes.add(rs1.getInt(1));
				ArrayList<String> com=new ArrayList<>();
				ArrayList<Integer> comid=new ArrayList<>();
				sql="SELECT id FROM comment where post_id= " + rs.getInt(1) + " Order by id Desc";
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
				lcomment.add(com);
				lcommentid.add(comid);
			}
			session.setAttribute("message", message); 
			session.setAttribute("comment", comment); 
			session.setAttribute("commentid", commentid); 
			session.setAttribute("numComment", numComment); 
			session.setAttribute("numTweets", numTweets); 
			session.setAttribute("numLikes", numLikes); 
			session.setAttribute("fcomment", fcomment); 
			session.setAttribute("fcommentid", fcommentid); 
			session.setAttribute("fname", fname); 
			session.setAttribute("funame", funame); 
			session.setAttribute("fmessage", fmessage); 
			session.setAttribute("fnumComment", fnumComment); 
			session.setAttribute("fnumTweets", fnumTweets); 
			session.setAttribute("fnumLikes", fnumLikes); 
			session.setAttribute("following", following); 
			session.setAttribute("followingname", followingname); 
			session.setAttribute("follower", follower); 
			session.setAttribute("followername", followername);
			session.setAttribute("lcomment", lcomment); 
			session.setAttribute("lcommentid", lcommentid); 
			session.setAttribute("lname", lname); 
			session.setAttribute("luname", luname); 
			session.setAttribute("lmessage", lmessage); 
			session.setAttribute("lnumComment", lnumComment); 
			session.setAttribute("lnumTweets", lnumTweets); 
			session.setAttribute("lnumLikes",lnumLikes); 
			session.setAttribute("id",id); 
			session.setAttribute("lId",lId); 
			session.setAttribute("fId",fId); 
			response.sendRedirect("http://localhost:8080/Twitter/profile.jsp");
			con.close();
		}catch (ClassNotFoundException ex) {
	            System.out.println("Could not find database driver class");
	            ex.printStackTrace();
	        } catch (SQLException ex) {
	            System.out.println("An error occurred. Maybe user/password is invalid");
	            ex.printStackTrace();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}
