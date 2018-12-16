package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	public int isMember (MemberBean member) {
		
		String sql = "SELECT thread_pw FROM thread_member WHERE thread_id = ?";
		int result = -1;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getTHREAD_ID());
			rs = pstmt.executeQuery();
			System.out.println("member.getTHREAD_ID()" + member.getTHREAD_ID());
			System.out.println("member.getTHREAD_PW()" + member.getTHREAD_PW());
			if (rs.next()) {
				if (rs.getString("THREAD_PW").equals(member.getTHREAD_PW())) {
					result = 1; // 일치
				} else {
					result = 0; // 불일치
				}
			} else {
				result = -1; // 아이디 존재하지 않음
			}
			
		} catch (Exception e) {
			System.out.println("isMember 에러 : " + e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("isMember, rs.close 에러 : " + e2);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("isMember, pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("isMember, con.close 에러 : " + e2);
				}
			}		
		}
		
		return result;
	}
	
	public boolean joinMember (MemberBean member) {
		
		String sql = "INSERT INTO thread_member VALUES (?, ?, ?, ?)";
		int result = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getTHREAD_NAME());
			pstmt.setString(2, member.getTHREAD_ID());
			pstmt.setString(3, member.getTHREAD_PW());
			pstmt.setString(4, member.getTHREAD_EMAIL());
			result = pstmt.executeUpdate();
			
			if (result != 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("joinMember 에러 : " + e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("joinMember, rs.close 에러 : " + e2);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("joinMember, pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("joinMember, con.close 에러 : " + e2);
				}
			}		
		}
		
		return false;
	}
	
	public List getMemberList() {
		
		String sql = "SELECT * FROM thread_member";
		List memberlist = new ArrayList();
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				MemberBean mb = new MemberBean();
				mb.setTHREAD_NAME(rs.getString("THREAD_NAME"));
				mb.setTHREAD_ID(rs.getString("THREAD_ID"));
				mb.setTHREAD_PW(rs.getString("THREAD_PW"));
				mb.setTHREAD_EMAIL(rs.getString("THREAD_EMAIL"));
				
				memberlist.add(mb);
			}
			
			return memberlist;
			
		} catch (Exception e) {
			System.out.println("getMemberList 에러 : " + e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("getMemberList, rs.close 에러 : " + e2);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("getMemberList, pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("getMemberList, con.close 에러 : " + e2);
				}
			}
		}
		
		return null;
	}
	
	public boolean updatePersonalInfo (MemberBean member) {
		
		String sql = "UPDATE thread_member set thread_name = ?, thread_pw = ?, thread_email = ? WHERE thread_id = ?";
		
		int result = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getTHREAD_NAME());
			pstmt.setString(2, member.getTHREAD_PW());
			pstmt.setString(3, member.getTHREAD_EMAIL());
			pstmt.setString(4, member.getTHREAD_ID());
			
			result = pstmt.executeUpdate();
			
			if (result != 0) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("개인정보 수정 실패 : " + e);
			System.out.println("개인정보 수정 실패 : " + sql);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("getDetailMember, pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("getDetailMember, con.close 에러 : " + e2);
				}
			}
		}
		return false;
	}
	
	public String getNickName (String id) {
		
		String sql = "SELECT thread_name FROM thread_member WHERE thread_id = ?";
		String nickName = "";
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				nickName = rs.getString("THREAD_NAME");
			}
			return nickName;
		} catch (Exception e) {
			System.out.println("getNickName() 에러 : " + e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("getNickName(), rs.close 에러 : " + e2);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("getNickName(), pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("getNickName(), con.close 에러 : " + e2);
				}
			}
		}
		return nickName;
	}
	
	public MemberBean getDetailMember (String id) {
		
		String sql = "SELECT * FROM thread_member WHERE thread_id = ?";
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			MemberBean mb = new MemberBean();
			mb.setTHREAD_NAME(rs.getString("THREAD_NAME"));
			mb.setTHREAD_ID(rs.getString("THREAD_ID"));
			mb.setTHREAD_PW(rs.getString("THREAD_PW"));
			mb.setTHREAD_EMAIL(rs.getString("THREAD_EMAIL"));
			
			return mb;
			
		} catch (Exception e) {
			System.out.println("getDetailMember 에러 : " + e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("getDetailMember, rs.close 에러 : " + e2);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("getDetailMember, pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("getDetailMember, con.close 에러 : " + e2);
				}
			}
		}
		
		return null;
	}
	

	public boolean deleteMember (String id) {
		
		String sql = "DELETE FROM thread_member WHERE thread_id = ?";
		int result = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
			if (result != 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("deleteMember 에러 : " + e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("deleteMember, rs.close 에러 : " + e2);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("deleteMember, pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("deleteMember, con.close 에러 : " + e2);
				}
			}		
		}
		
		return false;
	}

	/*		
	public boolean deleteMember (String id) {
		
		String sql1 = "DELETE FROM thread_board WHERE board_id = ?";
		String sql2 = "DELETE FROM thread_member WHERE thread_id = ?";
		
		boolean isSuccess = false;
		
		int result1 = 0;
		int result2 = 0;
		boolean result = false;
		System.out.println("deleteid = " + id);
		
		try {
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, id);
			result1 = pstmt.executeUpdate();

			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, id);
			result2 = pstmt.executeUpdate();
			
			if (result1 > 0 || result2 > 0) {
				result = true;
			}
			isSuccess = true;
			
		} catch (Exception e) {
			System.out.println("deleteMember 에러 : " + e);
		} finally {
			try {
				if (isSuccess) {
					con.commit();
				} else {
					con.rollback();
				}
			} catch (Exception e2) {}
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("deleteMember, rs.close 에러 : " + e2);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("deleteMember, pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("deleteMember, con.close 에러 : " + e2);
				}
			}
		}
		
		return result;
	}
*/
	public boolean IdCheck(String id)
    {
		String sql = "SELECT THREAD_ID FROM THREAD_MEMBER WHERE THREAD_ID=?";
		System.out.println("CID2="+id);
		boolean result = false;
		
        try {
            // 쿼리
        	con=ds.getConnection();
        	pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();

			if(rs.next()) result= true;
			
            return result;
            
            }catch (Exception ex) {
        	throw new RuntimeException(ex.getMessage());
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException ex) {}
			if(con!=null) try {con.close();}catch(SQLException ex) {}
		}
    } // end duplicateIdCheck()
}