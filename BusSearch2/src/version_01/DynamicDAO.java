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
//	public ArrayList<BusVo> transinfo(String info, String start, String goal) { // 로그인을 위한 데이터 베이스 연동부
//		ArrayList<BusVo> transinfo = new ArrayList<BusVo>();
//		if (info.equals("")) {
//			return null;
//		} else {
//			try {
//				connDB();
//				// 목적지를 경유하는 버스를 확인
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
//				Goal = new String[N]; // 목적지를 경유하는 버스들을 저장
//				while (rs.next()) {
//					String State = rs.getString("STATION_NUMBER");
//					Goal[cnt++] = State;
//				}
//
//				query = "select bus_number from bus_line where staiton = '" + start + "'"; // 출발지를 경유하는 버스들을 검색
//
//				rs = stmt.executeQuery(query);
//				rs.last();
//				N = rs.getRow();
//				if (rs.getRow() == 0) { // 널값이 아닌 환승 정보를 확인하알고리즘을 구현.
//					return null;
//				}
//
//				for (int i = 0; i < N; i++) {
//					rs.previous();
//				}
//				cnt = 0;
//				Start = new String[N]; // 출발지를 경유하는 버스들을 저장
//				add = new String[N]; // 메모를 위한 배열
//				while (rs.next()) {
//					String State = rs.getString("STATION_NUMBER");
//					Start[cnt] = State;
//					add[cnt++] = State;
//				}
//				int idx = 0;
//				cnt = 0;
//				int n = 0;
//				while (!an.equals("")) { // 출발지를 경유하는 버스들을 차례대로 검색.
//					// 배열 1번 버스의 현재 정류장 번호를 검색
//					query = "select STATION_NUMBER from bus_line where station = '" + Start[idx] + "'";
//					rs = stmt.executeQuery(query);
//
//					while (rs.next()) {
//						String State = rs.getString("STATION_NUMVER");
//						number = Integer.parseInt(State); // 해당 정류장 번호 + 1, 1번쿼리를 실행한 다음에는 해당하는 스테이션넘버를
//					} 										// 알고있기 때문에 쿼리를 반복할 필요가 없다.
//					while (true) {
//						try {
//						number++; // 정류장 1칸 이동, 단 해당정류장 이름을 모르기 때문에 검색해야한다.
//						query = "select STATION from bus_line where station = '" + Start[idx]
//								+ "' and STATION_NUMBER = '" + number + "'";
//						State = "";
//						while (rs.next()) {
//							State = rs.getString("STATION");
//						}
//						query = "select bus_number from bus_line where station = '"+State+"'";
//						rs = stmt.executeQuery(query);
//						int ct = 0;
//						while(rs.next()) { // 이동한 정류장을 경유하는 버스들을 저장
//							String bus_num = rs.getString("BUS_NUMBER");
//							if (!State.equals(add[n++])) { // 이미 검색한 버스인지를 확인해서, 확인 안 한 버스만 저장
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
//				// 버스번호 an 도출. an을 탄 버스정류장 State. 찾을 것은 출발 시 탄 버스에서 state까지와 State에서 골까지의 경로
//				query = "SELECT STATION FROM BUS_LINE WHERE BUS_NUMBER = '"+Start[idx]+"'";
//				rs = stmt.executeQuery(query);
//				
//				rs.last();
//				N = rs.getRow();
//				if (rs.getRow() == 0) { // 널값이 아닌 환승 정보를 확인하알고리즘을 구현.
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
//	// X 스타트, Y 골
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
