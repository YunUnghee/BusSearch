package version_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BusDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "egoing";
	String password = "111111";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public ArrayList<BusVo> stationAll(String Station) { // �α����� ���� ������ ���̽� ������
		ArrayList<BusVo> stationAll = new ArrayList<BusVo>();
		if (Station.equals("")) {
			return null;
		} else {
			try {
				connDB();
				// ������θ� Ȯ���ϴ� ����.
				String query = "select STATION from bus_line where bus_number = '"+Station+"'";
				rs = stmt.executeQuery(query);
				rs.last();
				int N = rs.getRow();
				if (rs.getRow() == 0) { // �ΰ��� �ƴ� ȯ�� ������ Ȯ���Ͼ˰����� ����.
					return null;
				}
				for (int i = 0; i < N; i++) {
					rs.previous();
				}
				int cnt = 0;
				String[] Result = new String[N];
				while (rs.next()) {
					String BusN = rs.getString("STATION");
					Result[cnt++] = BusN;
				}
				BusVo data = new BusVo(Result);
				stationAll.add(data);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return stationAll;
	}
	
	public ArrayList<BusVo> find(String start, String goal) { // �α����� ���� ������ ���̽� ������
		ArrayList<BusVo> find = new ArrayList<BusVo>();
		if (start.equals("") || goal.equals("")) {
			return null;
		} else {
			try {
				connDB();
				// ������θ� Ȯ���ϴ� ����.
				String query = "select bus_number from bus_line where bus_number in ("
						+ "select bus_number from bus_line where station = '" + start + "') ";
				query += "and station = '" + goal + "'";
				rs = stmt.executeQuery(query);
				rs.last();
				int N = rs.getRow();
				if (rs.getRow() == 0) { // �ΰ��� �ƴ� ȯ�� ������ Ȯ���Ͼ˰����� ����.
					return null;
				}
				for (int i = 0; i < N; i++) {
					rs.previous();
				}
				int cnt = 0;
				String[] Result = new String[N];
				while (rs.next()) {
					String BusN = rs.getString("BUS_NUMBER");
					Result[cnt++] = BusN;
				}
				BusVo data = new BusVo(Result);
				find.add(data);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return find;
	}

	public ArrayList<BusVo> station(String station) { // �α����� ���� ������ ���̽� ������
		ArrayList<BusVo> Station = new ArrayList<BusVo>();
		if (station.equals("")) {
			return null;
		} else {
			try {
				connDB();
				// ������θ� Ȯ���ϴ� ����.
				String query = "select bus_number from bus_line where station = '";
				query += station + "'";
				rs = stmt.executeQuery(query);
				rs.last();
				int N = rs.getRow();
				if (rs.getRow() == 0) { // �ΰ��� �ƴ� ȯ�� ������ Ȯ���Ͼ˰����� ����.
					return null;
				}
				for (int i = 0; i < N; i++) {
					rs.previous();
				}
				int cnt = 0;
				String[] Result = new String[N];
				while (rs.next()) {
					String BusN = rs.getString("BUS_NUMBER");
					Result[cnt++] = BusN;
				}
				BusVo data = new BusVo(Result);
				Station.add(data);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return Station;
	}

	public ArrayList<BusVo> list() { // �α����� ���� ������ ���̽� ������
		ArrayList<BusVo> list = new ArrayList<BusVo>();
		try {
			connDB();
			// ������θ� Ȯ���ϴ� ����.
			String query = "select station from bus_line";

			rs = stmt.executeQuery(query);
			rs.last();
			int N = rs.getRow();
			if (rs.getRow() == 0) { // �ΰ��� �ƴ� ȯ�� ������ Ȯ���Ͼ˰����� ����.
				return null;
			}
			for (int i = 0; i < N; i++) {
				rs.previous();
			}
			int cnt = 0;
			String[] List = new String[N];
			while (rs.next()) {
				String BusN = rs.getString("STATION");
				List[cnt++] = BusN;
			}
			BusVo data = new BusVo(List);
			list.add(data);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return list;
	}
	//transinfo
	
	
	
	public ArrayList<BusVo> information(String info, String start, String goal) { // �α����� ���� ������ ���̽� ������
		ArrayList<BusVo> information = new ArrayList<BusVo>();
		if (info.equals("")) {
			return null;
		} else {
			try {
				connDB();
				// ������θ� Ȯ���ϴ� ����.
				String query = "select station_number from bus_line where bus_number = '" + info + "' and station = '"
						+ start + "' or station = '" + goal + "'";
				rs = stmt.executeQuery(query);
				rs.last();
				int N = rs.getRow();
				if (rs.getRow() == 0) { // �ΰ��� �ƴ� ȯ�� ������ Ȯ���Ͼ˰����� ����.
					return null;
				}

				for (int i = 0; i < N; i++) {
					rs.previous();
				}
				int cnt = 0;
				String[] Result = new String[N];
				while (rs.next()) {
					String BusN = rs.getString("STATION_NUMBER");
					Result[cnt++] = BusN;
				}
				if (Integer.parseInt(Result[0]) < Integer.parseInt(Result[cnt-1])) {
					String temp = Result[0];
					Result[0] = Result[1];
					Result[1] = temp;
				}

				query = "select station from bus_line where bus_number = '" + info + "' and station_number >= "
						+ Result[cnt-1] + " and station_number <= " + Result[0];

				rs = stmt.executeQuery(query);
				rs.last();
				N = rs.getRow();
				if (rs.getRow() == 0) { // �ΰ��� �ƴ� ȯ�� ������ Ȯ���Ͼ˰����� ����.
					return null;
				}

				for (int i = 0; i < N; i++) {
					rs.previous();
				}
				cnt = 0;
				Result = new String[N];
				while (rs.next()) {
					String BusN = rs.getString("STATION");
					Result[cnt++] = BusN;
				}

				BusVo data = new BusVo(Result);
				information.add(data);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return information;
	}
	
	void Recoding(String ID, String start, String goal) {
		try {
			connDB();
			// ���ڵ����̺��� ��ȣ�� �˾ƿ��� ���̺�//
			String query = "SELECT NO FROM(SELECT NO FROM RECOD ORDER BY NO DESC) WHERE ROWNUM <= 1";
			rs = stmt.executeQuery(query);
			rs.next();
			int n = 0;
			if (rs.getRow() == 0) { //�˻������ ������ ��ϵ��� ���� ��.
				n = 1;
			}
			n = Integer.parseInt(rs.getString("NO"))+1;
			String NO = String.valueOf(n);
			
			query = "insert into recod(NO, start_S, GOAL_S,ID) values(";
			query += "'"+NO+"','"+start+"','"+goal+"','"+ID+"')";
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
			JOptionPane.showMessageDialog(null, "��Ͽ� �����Ͽ����ϴ�.", "��Ͻ���", JOptionPane.ERROR_MESSAGE);
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
