package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {

	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// 1.
	public BoardDAO() {

		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (NamingException e) {
			System.out.println("DB 연결 실패  : " + e);
			return;
		}
	}

	public String getReplyDate (int postnum) {
		
		String sql = "SELECT TO_CHAR(thread_r_date, 'yyyy-mm-dd hh24:mi:ss') FROM (SELECT thread_r_date FROM thread_reply WHERE thread_r_boardnum = ? ORDER BY thread_r_date DESC) WHERE rownum <= 1";

		String result = "";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postnum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getString(1);
				
				return result;
			}
		} catch (Exception e) {
			System.out.println("getReplyDate() 에러 : " + e);
			System.out.println("getReplyDate() 에러 : " + sql);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 게시판마다의 글 갯수 구하기
	public int getEachListCount (int bnum) {
		int x = 0;
		
		String sql = "SELECT COUNT(*) FROM thread_board WHERE thread_b_boardnum like ";
		
		if (bnum == 10) {
			sql += "'1%'";
		} else if (bnum == 20) {
			sql += "'2%'";
		} else if (bnum == 30) {
			sql += "'3%'";
		} else if (bnum == 40) {
			sql += "'4%'";
		} else {
			sql += "'"+bnum+"'";
		}
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("getEachListCount() 에러 : " + e);
			System.out.println("getEachListCount() 에러 : " + sql);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return x;
	}
	
	// 2. 글의 개수 구하기
	public int getListCount(int bnum, String cond) {

		int x = 0;

		String sql = "SELECT COUNT(*) FROM thread_board WHERE thread_b_boardnum like ";

		if (bnum == 10) {
			sql += "'1%'";
		} else if (bnum == 20) {
			sql += "'2%'";
		} else if (bnum == 30) {
			sql += "'3%'";
		} else if (bnum == 40) {
			sql += "'4%'";
		} else {
			sql += "'"+bnum+"'";
		}

		if (cond != null && !cond.equals("")) {
			sql = sql + " AND " + cond;
		}
		
		try {
			con = ds.getConnection();
			
			System.out.println("getConnection()");
			System.out.println("getListCount() : " + sql);

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("getListCount() 에러 : " + e);
			System.out.println("getListCount() 에러 : " + sql);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return x;
	}
	
	// 덧글 개수 구하기
	public int getReplyCount(int num) {
		int x = 0;
		String sql = "SELECT COUNT(*) FROM THREAD_REPLY WHERE THREAD_R_BOARDNUM="+num;	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("getReplyCount() 에러 : " + e);
			System.out.println("getReplyCount() 에러 : " + sql);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("덧글 개수 x : "+x);
		return x;
	}
		
	// 3. 글 목록 보기
	public List<BoardBean> getBoardList(int bnum, int page, int limit, String cond) {

		String board_list_sql = "SELECT * FROM " + "(SELECT rownum rnum, thread_b_name, thread_b_subject, thread_b_content, "
				+ "thread_b_date, thread_b_file, thread_b_readcount, thread_b_boardnum, thread_b_postnum "
				+ "FROM (SELECT * FROM thread_board "; 

		String board_list_sql_fmt = "SELECT * FROM " + "(SELECT rownum rnum, thread_b_name, thread_b_subject, thread_b_content, "
				+ "thread_b_date, thread_b_file, thread_b_readcount, thread_b_boardnum, thread_b_postnum "
				+ "FROM (SELECT * FROM thread_board WHERE %s "; 
		
		String sql = " ORDER BY thread_b_date DESC) WHERE thread_b_boardnum like %s) WHERE rnum >= ? and rnum < = ?";
		
		if (cond != null && !cond.equals("")) {
			board_list_sql = String.format(board_list_sql_fmt, cond);
		}

		List<BoardBean> list = new ArrayList<BoardBean>();

		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호
		
		System.out.println("startrow" + startrow);
		System.out.println("endrow" + endrow);
		
		if (bnum == 10) {
			sql = String.format(sql, "'1%'");
		} else if (bnum == 20) {
			sql = String.format(sql, "'2%'");
		} else if (bnum == 30) {
			sql = String.format(sql, "'3%'");
		} else if (bnum == 40) {
			sql = String.format(sql, "'4%'");
		} else {
			sql = String.format(sql, "'"+bnum+"'");
		}
		
		board_list_sql += sql;

		try {
			con = ds.getConnection();

			// for debug
			System.out.println("getBoardList() : " + board_list_sql);

			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setTHREAD_B_NAME(rs.getString("THREAD_B_NAME"));
				board.setTHREAD_B_SUBJECT(rs.getString("THREAD_B_SUBJECT"));
				board.setTHREAD_B_CONTENT(rs.getString("THREAD_B_CONTENT"));
				board.setTHREAD_B_DATE(rs.getDate("THREAD_B_DATE"));
				board.setTHREAD_B_FILE(rs.getString("THREAD_B_FILE"));
				board.setTHREAD_B_READCOUNT(rs.getInt("THREAD_B_READCOUNT"));
				board.setTHREAD_B_BOARDNUM(rs.getInt("THREAD_B_BOARDNUM"));
				board.setTHREAD_B_POSTNUM(rs.getInt("THREAD_B_POSTNUM"));

				list.add(board);
			}
			return list;

		} catch (Exception e) {
			System.out.println("getBoardList() 에러 : " + e);
			System.out.println("getBoardList() 에러 : " + board_list_sql);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// 덧글 내용 보기
	public List<ReplyBean> getReplyList(int num) {
		String reply_list_sql = "select * from THREAD_REPLY where THREAD_R_BOARDNUM="+num;
		List<ReplyBean> list = new ArrayList<ReplyBean>();
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(reply_list_sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReplyBean reply = new ReplyBean();
				reply.setTHREAD_R_NAME(rs.getString("THREAD_R_NAME"));
				reply.setTHREAD_R_CONTENT(rs.getString("THREAD_R_CONTENT"));
				reply.setTHREAD_R_DATE(rs.getDate("THREAD_R_DATE"));
				reply.setTHREAD_R_BOARDNUM(rs.getInt("THREAD_R_BOARDNUM"));
				reply.setTHREAD_R_POSTNUM(rs.getInt("THREAD_R_POSTNUM"));
				
				list.add(reply);			
			}
			return list;

		} catch (Exception e) {
			System.out.println("getReplyList() 에러 : " + e);
			System.out.println("getReplyList() 에러 : " + reply_list_sql);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	// 4. 글 내용 보기
	public BoardBean getDetail(int num) throws Exception {
		
		BoardBean board = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement("SELECT * FROM thread_board WHERE thread_b_postnum = ?");
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				board = new BoardBean();
				
				board.setTHREAD_B_NAME(rs.getString("THREAD_B_NAME"));
				board.setTHREAD_B_SUBJECT(rs.getString("THREAD_B_SUBJECT"));
				board.setTHREAD_B_CONTENT(rs.getString("THREAD_B_CONTENT"));
				board.setTHREAD_B_DATE(rs.getDate("THREAD_B_DATE"));
				board.setTHREAD_B_FILE(rs.getString("THREAD_B_FILE"));
				board.setTHREAD_B_READCOUNT(rs.getInt("THREAD_B_READCOUNT"));
				board.setTHREAD_B_BOARDNUM(rs.getInt("THREAD_B_BOARDNUM"));
				board.setTHREAD_B_POSTNUM(rs.getInt("THREAD_B_POSTNUM"));
			}
			return board;
			
		} catch (Exception e) {
			System.out.println("getDetail() 에러 : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// 5. 글 등록
	public boolean boardInsert (BoardBean board) {
		
		String sql = "INSERT INTO thread_board (thread_b_name, thread_b_subject, thread_b_content, "
				+ "thread_b_date, thread_b_file, thread_b_readcount, thread_b_boardnum, thread_b_postnum) "
				+ "VALUES (?, ?, ?, sysdate, ?, ?, ?, seq_postnum.Nextval)";
		int result = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTHREAD_B_NAME());
			pstmt.setString(2, board.getTHREAD_B_SUBJECT());
			pstmt.setString(3, board.getTHREAD_B_CONTENT());
			pstmt.setString(4, board.getTHREAD_B_FILE());
			pstmt.setInt(5, board.getTHREAD_B_READCOUNT());
			pstmt.setInt(6, board.getTHREAD_B_BOARDNUM());

			result = pstmt.executeUpdate();
			
			if (result == 0) {
				return false;
			}
			return true;
			
		} catch (Exception e) {
			System.out.println("boardInsert() 에러 : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;		
	}
	
	//덧글 등록
	public boolean replyInsert (ReplyBean reply) {
		
		String sql = "INSERT INTO thread_reply (thread_r_name, thread_r_content, "
				+ "thread_r_date, thread_r_boardnum, thread_r_postnum) "
				+ "VALUES (?, ?, sysdate, ?, seq_replynum.Nextval)";
		int result = 0;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);		//1
			pstmt.setString(1, reply.getTHREAD_R_NAME());
			pstmt.setString(2, reply.getTHREAD_R_CONTENT());
			pstmt.setInt(3, reply.getTHREAD_R_BOARDNUM());
			
			result = pstmt.executeUpdate();
			
			if (result == 0) {
				return false;
			}
			return true;
			
		} catch (Exception e) {
			System.out.println("replyInsert() 에러 : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;		
	}
	
	// 7. 글 수정
	public boolean boardModify (BoardBean modifyBoard) {
		
		String sql = "UPDATE thread_board SET thread_b_subject = ?, thread_b_content = ?, thread_b_date = sysdate WHERE thread_b_postnum = ?";
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyBoard.getTHREAD_B_SUBJECT());
			pstmt.setString(2, modifyBoard.getTHREAD_B_CONTENT());
			pstmt.setInt(3, modifyBoard.getTHREAD_B_POSTNUM());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("boardModify() 에러 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	// 8. 글 삭제
	public boolean boardDelete (int num) {
		
		String delete_sql1 = "DELETE FROM thread_board WHERE thread_b_postnum = ?";
		String delete_sql2 = "DELETE FROM thread_reply WHERE thread_r_boardnum = ?";
		
		boolean isSuccess = false;
		int result1 = 0;
		int result2 = 0;
		boolean result = false;
		
		System.out.println("boardDelete 내에 삭제할 게시물 번호 : " +num);
		
		try {
		
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(delete_sql1);
			pstmt.setInt(1, num);
			result1 = pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(delete_sql2);
			pstmt.setInt(1, num);
			result2 = pstmt.executeUpdate();
			
			if (result1 > 0 || result2 > 0) {
				result = true;
			}
			isSuccess = true;
			
		} catch (Exception e) {
			System.out.println("boardDelete() 에러 : " + e);
		} finally {
			try {
				if (isSuccess) {
					con.commit();
				} else {
					con.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					System.out.println("boardDelete, rs.close 에러 : " + e2);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					System.out.println("boardDelete, pstmt.close 에러 : " + e2);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					System.out.println("boardDelete, con.close 에러 : " + e2);
				}
			}
		}
		
		return result;
	}
	
	// 8. 덧글
		public boolean replyDelete (int num, int rnum) {
			
			String sql = "DELETE FROM THREAD_REPLY WHERE thread_r_boardnum = ? and thread_r_postnum = ? ";
			int result = 0;
			
			try {
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.setInt(2, rnum);
				result = pstmt.executeUpdate();
				
				if (num == 0) {
					return false;
				}
				return true;
				
			} catch (Exception e) {
				System.out.println("replyDelete() 에러 : " + e);
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}

	// 9. 조회 수 업데이트
	public void setReadCountUpdate (int num) throws Exception {
		
		String sql = "UPDATE thread_board SET thread_b_readcount = thread_b_readcount + 1"
				+ "WHERE thread_b_postnum = ?";
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("setReadCount() 에러 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 10. 글쓴이인지 확인
	public boolean isBoardWriter (int num, String id) {
		
		System.out.println("isBoardWriter 의 id = " + id);
		String sql = "SELECT thread_b_name FROM thread_board WHERE thread_b_postnum = ?";
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();			
			System.out.println("rs.getString : " + rs.getString("THREAD_B_NAME"));
			
			if (id.equals(rs.getString("THREAD_B_NAME"))) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("isBoardWriter() 에러 : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean isReplyWriter (int rnum, String id) {
		
		System.out.println("isBoardWriter 의 id = " + id);
		String sql = "SELECT thread_r_name FROM thread_reply WHERE thread_r_postnum = ?";
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			rs = pstmt.executeQuery();
			rs.next();			
			System.out.println("rs.getString : " + rs.getString("THREAD_R_NAME"));
			
			if (id.equals(rs.getString("THREAD_R_NAME"))) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("isReplyWriter() 에러 : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	// 게시판 번호에 따른 게시판 이름 불러오기
	public String getBoardName (int bnum) {
		
		String bname = "";
		System.out.println("dao안에 bnum : " + bnum);
		String sql = "SELECT board_name FROM thread_board_name WHERE board_num = "+bnum;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			// pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bname = rs.getString("board_name");
			} else {
				bname = "에러...........";
			}
			
			System.out.println("DAO 에서 bname : " +bname);
			return bname;
			
		} catch (Exception e) {
			System.out.println("getBoardName() 에러 : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bname;
	}
}
