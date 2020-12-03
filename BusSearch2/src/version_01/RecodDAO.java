package version_01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecodDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "egoing";
	String password = "111111";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<RecodVO> rec(String ID) { // 로그인을 위한 데이터 베이스 연동부
		ArrayList<RecodVO> rec = new ArrayList<RecodVO>();
		try {
			connDB();
			String query = "SELECT NO, START_S, GOAL_S, REC_DATE FROM RECOD WHERE ID = '" + ID + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			int N = rs.getRow();
			if (N == 0) {
				return null;
			} else {
				for (int i = 0; i < N; i++) {
					rs.previous();
				}
			}

			int n = 0;
			int numbering = N;
			String[][] Result = new String[N][4];
			SimpleDateFormat fomat = new SimpleDateFormat("yyyy-mm-dd");
			while (rs.next()) {
				String NO = String.valueOf(numbering--);
				String start = rs.getString("START_S");
				String goal = rs.getString("GOAL_S");
				Date time = rs.getDate(4);
				System.out.println(time);
				String date = fomat.format(time);
				System.out.println(date);

				Result[n][0] = NO;
				Result[n][1] = start;
				Result[n][2] = goal;
				Result[n++][3] = date;
			}
			RecodVO data = new RecodVO(Result);
			rec.add(data);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return rec;
	}

	public ArrayList<RecodVO> rec2(String ID) { // 로그인을 위한 데이터 베이스 연동부
		ArrayList<RecodVO> rec2 = new ArrayList<RecodVO>();
		try {
			connDB();
			String query = "SELECT START_S, GOAL_S, CREATE_DATE FROM favarit WHERE ID = '" + ID + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			int N = rs.getRow();
			if (N == 0) {
				return null;
			} else {
				for (int i = 0; i < N; i++) {
					rs.previous();
				}
			}
			int n = 0;
			int numbering = N;
			String[][] Result = new String[N][4];
			SimpleDateFormat fomat = new SimpleDateFormat("yyyy-mm-dd");
			while (rs.next()) {
				String NO = String.valueOf(numbering--);
				String start = rs.getString("START_S");
				String goal = rs.getString("GOAL_S");
				Date time = rs.getDate(3);
				System.out.println(time);
				String date = fomat.format(time);
				System.out.println(date);

				Result[n][0] = NO;
				Result[n][1] = start;
				Result[n][2] = goal;
				Result[n++][3] = date;
			}
			RecodVO data = new RecodVO(Result);
			rec2.add(data);

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return rec2;
	}

	void Insert(String start, String goal, String id) {
		try {
			connDB();
			String query = "SELECT NO FROM(SELECT NO FROM FAVARIT ORDER BY NO DESC) WHERE ROWNUM <= 1";
			rs = stmt.executeQuery(query);
			rs.next();
			int n = 0;
			if (rs.getRow() == 0) { // 검색결과가 없으면 기록되지 않은 것.
				n = 1;
			}
			n = Integer.parseInt(rs.getString("NO")) + 1;
			String NO = String.valueOf(n);
			query = "insert into FAVARIT(NO, start_S, GOAL_S,ID) values(";
			query += "'" + NO + "','" + start + "','" + goal + "','" + id + "')";
			boolean b = stmt.execute(query);
			if (!b) {
				System.out.println("Insert success.\n");
			} else
				System.out.println("Insert fail.\n");

			String sql = "SELECT NO, START_S, GOAL_S, ID FROM RECOD";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.print(rs.getString("NO"));
				System.out.print(rs.getString("ID"));
				System.out.print(rs.getString("START_S"));
				System.out.print(rs.getString("GOAL_S"));
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	void Delite(String start, String goal, String id, String time) {
		try {
			connDB();

			String query = "Delete From Favarit where ID = '" + id + "' and start_s = '" + start + "' and"
					+ " goal_s = '" + goal + "' and create_date = to_date('" + time + "')";
			rs = stmt.executeQuery(query);

			query = "Select * from favarit where ID =  '" + id + "' and start_s = '" + start + "' and" + " goal_s = '"
					+ goal + "' and create_date = to_date('" + time + "')";
			rs = stmt.executeQuery(query);
			rs.next();
			if (rs.getRow() == 0) { // 검색결과가 없으면 기록되지 않은 것.
				return;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	void Delall() {
		try {
			connDB();

			String query = "TRUNCATE TABLE RECOD";
			rs = stmt.executeQuery(query);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	void Delall2() {
		try {
			connDB();
			
			String query = "TRUNCATE TABLE FAVARIT";
			rs = stmt.executeQuery(query);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success");
//			stmt = con.createStatement();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
