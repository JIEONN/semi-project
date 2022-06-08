package trendy.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import common.JDBCTemplate;
import trendy.member.vo.Member;

public class MemberDao {

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT into member_tbl values(?, ?, ?, ?, ?, ?, ?,?, sysdate, ?, ?, ? )";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberPw());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getGrade());
			pstmt.setString(9, m.getGender());
			pstmt.setInt(10, m.getPoint());
			pstmt.setInt(11, m.getAdmin());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));				
				m.setPhone(rset.getString("phone"));
				m.setGrade(rset.getString("grade"));
				m.setEnrolldate(rset.getDate("enrolldate"));
				m.setGender(rset.getString("gender"));
				m.setPoint(rset.getInt("point"));
				m.setAdmin(rset.getInt("admin"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public Member selectOneMember(Connection conn, String selectId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member_tbl where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selectId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));				
				m.setPhone(rset.getString("phone"));
				m.setGrade(rset.getString("grade"));
				m.setEnrolldate(rset.getDate("enrolldate"));
				m.setGender(rset.getString("gender"));
				m.setPoint(rset.getInt("point"));
				m.setAdmin(rset.getInt("admin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return m;
	}
	public Member selectOneMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member_tbl where member_id=? and member_pw=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));				
				m.setPhone(rset.getString("phone"));
				m.setGrade(rset.getString("grade"));
				m.setEnrolldate(rset.getDate("enrolldate"));
				m.setGender(rset.getString("gender"));
				m.setPoint(rset.getInt("point"));
				m.setAdmin(rset.getInt("admin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return m;
	}

	public int deleteMember(Connection conn, String deleteId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member_tbl where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deleteId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}		
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;		
		int result = 0;
		String query = "update member_tbl set member_pw = ?, member_name = ?, address=?, phone=?, email=? where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}	
		return result;
	}

	public Member selectOneMember(Connection conn, String name, String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member_tbl where member_name=? and email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));				
				m.setPhone(rset.getString("phone"));
				m.setGrade(rset.getString("grade"));
				m.setEnrolldate(rset.getDate("enrolldate"));
				m.setGender(rset.getString("gender"));
				m.setPoint(rset.getInt("point"));
				m.setAdmin(rset.getInt("admin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return m;
	}

	public int pwUpdate(Connection conn, String memberId) {
		PreparedStatement pstmt = null;		
		int result = 0;
	      Random r = new Random();
	      StringBuilder sb = new StringBuilder();
	      for(int i=0;i<9;i++) {
	         //숫자, 영어소문자, 영어대문자를 랜덤으로 조합하여 9글자를 만들기위해 반복 6회
	         int flag = r.nextInt(3);// 0:0~9사이에서 랜덤 // 1:A-Z 사이에서 랜덤 // 2:a-z사이에서 랜덤
	         if(flag == 0) {
	            int randomNum = r.nextInt(10);
	            sb.append(randomNum);
	         }else if(flag == 1) {
	            //A~Z
	            char randomChar = (char)(r.nextInt(26)+65);
	            sb.append(randomChar);
	         }else if(flag == 2) {
	            //a~z
	            char randomChar = (char)(r.nextInt(26)+97);
	            sb.append(randomChar);
	         }
	         
	      }
	      System.out.println(sb.toString());
		String query = "update member_tbl set member_pw= ? where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sb.toString());
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
			System.out.println(memberId);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}	
		return result;
	}

	public Member selectOneMember2(Connection conn, String memberId, String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member_tbl where member_id=? and email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setAddress(rset.getString("address"));				
				m.setPhone(rset.getString("phone"));
				m.setGrade(rset.getString("grade"));
				m.setEnrolldate(rset.getDate("enrolldate"));
				m.setGender(rset.getString("gender"));
				m.setPoint(rset.getInt("point"));
				m.setAdmin(rset.getInt("admin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return m;
	}
	
}
