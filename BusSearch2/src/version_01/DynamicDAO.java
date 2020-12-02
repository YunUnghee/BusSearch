//package version_01;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//public class DynamicDAO {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521/xe";
//	String user = "egoing";
//	String password = "111111";
//
//	String[] Start;
//	String[] add;
//	String[] Goal;
//	String[] check;
//
//	String an;
//	String State;
//
//	int number;
//	private Connection con;
//	private Statement stmt;
//	private ResultSet rs;
//
//	public ArrayList<BusVo> transinfo(String info, String start, String goal) { // �α����� ���� ������ ���̽� ������
//		ArrayList<BusVo> transinfo = new ArrayList<BusVo>();
//		if (info.equals("")) {
//			return null;
//		} else {
//			try {
//				connDB();
//				// �������� �����ϴ� ������ Ȯ��
//				String query = "select bus_number from bus_line where staiton = '" + goal + "'";
//
//				rs = stmt.executeQuery(query);
//				rs.last();
//				int N = rs.getRow();
//				if (rs.getRow() == 0) {
//					return null;
//				}
//
//				for (int i = 0; i < N; i++) {
//					rs.previous();
//				}
//				int cnt = 0;
//				Goal = new String[N]; // �������� �����ϴ� �������� ����
//				while (rs.next()) {
//					String State = rs.getString("STATION_NUMBER");
//					Goal[cnt++] = State;
//				}
//
//				query = "select bus_number from bus_line where staiton = '" + start + "'"; // ������� �����ϴ� �������� �˻�
//
//				rs = stmt.executeQuery(query);
//				rs.last();
//				N = rs.getRow();
//				if (rs.getRow() == 0) { // �ΰ��� �ƴ� ȯ�� ������ Ȯ���Ͼ˰����� ����.
//					return null;
//				}
//
//				for (int i = 0; i < N; i++) {
//					rs.previous();
//				}
//				cnt = 0;
//				Start = new String[N]; // ������� �����ϴ� �������� ����
//				add = new String[N]; // �޸� ���� �迭
//				while (rs.next()) {
//					String State = rs.getString("STATION_NUMBER");
//					Start[cnt] = State;
//					add[cnt++] = State;
//				}
//				int idx = 0;
//				cnt = 0;
//				int n = 0;
//				while (!an.equals("")) { // ������� �����ϴ� �������� ���ʴ�� �˻�.
//					// �迭 1�� ������ ���� ������ ��ȣ�� �˻�
//					query = "select STATION_NUMBER from bus_line where station = '" + Start[idx] + "'";
//					rs = stmt.executeQuery(query);
//
//					while (rs.next()) {
//						String State = rs.getString("STATION_NUMVER");
//						number = Integer.parseInt(State); // �ش� ������ ��ȣ + 1, 1�������� ������ �������� �ش��ϴ� �����̼ǳѹ���
//					} 										// �˰��ֱ� ������ ������ �ݺ��� �ʿ䰡 ����.
//					while (true) {
//						try {
//						number++; // ������ 1ĭ �̵�, �� �ش������� �̸��� �𸣱� ������ �˻��ؾ��Ѵ�.
//						query = "select STATION from bus_line where station = '" + Start[idx]
//								+ "' and STATION_NUMBER = '" + number + "'";
//						State = "";
//						while (rs.next()) {
//							State = rs.getString("STATION");
//						}
//						query = "select bus_number from bus_line where station = '"+State+"'";
//						rs = stmt.executeQuery(query);
//						int ct = 0;
//						while(rs.next()) { // �̵��� �������� �����ϴ� �������� ����
//							String bus_num = rs.getString("BUS_NUMBER");
//							if (!State.equals(add[n++])) { // �̹� �˻��� ���������� Ȯ���ؼ�, Ȯ�� �� �� ������ ����
//								check[ct++] = bus_num;
//								add[n-1] = bus_num;
//							}
//						}
//						an = this.SelfCall(check, Goal);
//						if(an.equals("")) {
//							continue;
//						}else {
//							break;
//						}
//						}catch(Exception e) {
//							idx++;
//							break;
//						}
//					}
//				}
//				
//				// ������ȣ an ����. an�� ź ���������� State. ã�� ���� ��� �� ź �������� state������ State���� ������� ���
//				query = "SELECT STATION FROM BUS_LINE WHERE BUS_NUMBER = '"+Start[idx]+"'";
//				rs = stmt.executeQuery(query);
//				
//				rs.last();
//				N = rs.getRow();
//				if (rs.getRow() == 0) { // �ΰ��� �ƴ� ȯ�� ������ Ȯ���Ͼ˰����� ����.
//					return null;
//				}
//
//				for (int i = 0; i < N; i++) {
//					rs.previous();
//				}
//
//				BusVo data = new BusVo();
//				transinfo.add(data);
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return null;
//	}
//
//	public void connDB() {
//		try {
//			Class.forName(driver);
//			System.out.println("jdbc driver loading success.");
//			con = DriverManager.getConnection(url, user, password);
//			System.out.println("oracle connection success");
////			stmt = con.createStatement();
//			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			System.out.println("statement create success");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	// X ��ŸƮ, Y ��
//	String SelfCall(String[] X, String[] Y) {
//		String anser = "";
//		for (int i = 0; i < X.length; i++) {
//			for (int y = 0; y < Y.length; y++) {
//				if (X[i].equals(Y[y])) {
//					anser += X[i];
//					break;
//				}
//			}
//		}
//		return anser;
//	}
//}
