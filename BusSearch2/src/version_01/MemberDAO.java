package version_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "egoing";
	String password = "111111";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<MemberVo> login(String ID, String PASSWORD) { // 로그인을 위한 데이터 베이스 연동부
		ArrayList<MemberVo> login = new ArrayList<MemberVo>();
		if (ID == null || PASSWORD == null) { //입력받은 값이 없을 경우 NULL 리턴
			return null;
		} else {
			try {
				connDB(); //데이터베이스 연결

				String query = "SELECT ID, PASS FROM ID_INFO "; //ID_INFO 내의 모든 정보를 얻는 쿼리
				if (ID != null) {
					query += "where ID = '" + ID + "'"; //입력받은 아이디와 같은 정보를 전부 출력
				}
				rs = stmt.executeQuery(query); //쿼리 입력부
				rs.last();
				System.out.println("rs.getRow()" + rs.getRow());
				if (rs.getRow() == 0) { // 입력받은 ID와 같은 ID 정보가 없을 경우 NULL 리턴
					return null;
				} else { // 정보가 있을 경우 다음 로직 실행
					System.out.println(rs.getRow() + "rows selected.........");
					rs.previous();
					while (rs.next()) { // 입력받은 ID와 같은 ID를 가진 DB내의 정보의 ID와 PW를 저장
						String id = rs.getString("ID");
						String PASS = rs.getString("PASS");

						System.out.println(id + " : " + ID);
						System.out.println(PASS + " : " + PASSWORD);
						if (ID.equals(id) && PASSWORD.equals(PASS)) {
							MemberVo data = new MemberVo(id, PASS); //MemberVo 인스턴스에 해당 내용을 저장하여, 리스트에 저장.
							login.add(data);
						} else {
							return null;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return login;
	}

	public ArrayList<MemberVo> signup(String ID, String PASSWORD, String MAIL) { //회원가입을 위한 DB 연동부
		ArrayList<MemberVo> signup = new ArrayList<MemberVo>();
		if (ID.equals("") || PASSWORD.equals("") || MAIL.equals("")) { //입력받은 정보가 1개라도 없을 경우 NULL 리턴
			return null;
		} else {
			try {
				connDB();

				String query = "SELECT ID, PASS FROM ID_INFO "; //입력받은 ID와 같은 ID가 있는지 확인
				query += "where ID = '" + ID + "'";

				rs = stmt.executeQuery(query);
				rs.last();
				System.out.println("rs.getRow()" + rs.getRow()); // 검사 결과를 출력

				if (rs.getRow() != 0) {// 1개라도 있을 경우 NULL 리턴
					return null;
				} else { // 없을 경우 다음 로직 실행

					query = "INSERT INTO ID_INFO(ID,PASS,EMAIL) VALUES("; // DB에 입력하기 위한 쿼리
					query += "'" + ID + "'," + "'" + PASSWORD + "'," + "'" + MAIL + "')"; //입력받은 ID, PW, EMAIL을 DB에 저장
					rs = stmt.executeQuery(query);

					MemberVo data = new MemberVo(ID, PASSWORD, MAIL); //입력한 데이터를 data 인스턴스에 저장
					signup.add(data);
					return signup;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return signup;
	}

	public ArrayList<MemberVo> idcheck(String ID) { // 입력받은 ID가 DB에 저장되어 있는지 확인하기 위한 연동부
		ArrayList<MemberVo> check = new ArrayList<MemberVo>();
		if (ID.equals("")) { // 입력받은 아이디가 없을 경우 NULL 리턴
			return null;
		} else { //없을 경우 다음 로직 실행
			try {
				connDB();

				String query = "SELECT ID, PASS FROM ID_INFO "; // ID를 이용하여 검색하는 쿼리
				if (ID != null) {
					query += "where ID = '" + ID + "'";
				}
				rs = stmt.executeQuery(query);
				rs.last();
				System.out.println("rs.getRow()" + rs.getRow()); //검색결과 출력
				if (rs.getRow() != 0) { // 1개라도 있을 경우 NULL 리턴
					return null;
				}
				MemberVo data = new MemberVo(ID); // 없을 경우 data 인스턴스에 저장후 리턴
				check.add(data);
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}
	
	public ArrayList<MemberVo> passFind(String ID, String MAIL) { // ID와 EMAIL을 입력받아 PW를 출력하기 위한 연동부
		ArrayList<MemberVo> passFind = new ArrayList<MemberVo>();
		if(ID == null || MAIL == null) {// ID와 EMAIL 중 1개라도 입력되지 않으면 NULL리턴
			return null;
		}else { // 그렇지 않을 경우 다음 로직 실행
			try {
				connDB();
				
				String query = "SELECT ID, PASS, EMAIL FROM ID_INFO "; // ID,PW,EMAIL 전체를 검색하기 위한 쿼리
				query += "where ID = '" + ID + "' AND EMAIL = '"+MAIL+"'";
				rs = stmt.executeQuery(query);
				rs.last();
				System.out.println("rs.getRow()" + rs.getRow());
				if (rs.getRow() == 0) {// 검색결과가 없을 경우 NULL 리턴
					return null;
				}else { //1개 이상일 경우 다음 로직 실행
					rs.previous();
					while(rs.next()) { // DB상의 ID, EMAIL, PW를 해당 변수들에 저장
						String id = rs.getString("ID");
						String pass = rs.getString("PASS");
						String mail = rs.getString("EMAIL");
						System.out.println(pass+" "+id+" "+mail);
						if(ID.equals(id)&&MAIL.equals(mail)) { // 입력값의 ID, EMAIL과 DB상의 정보가 같을 경우 값을 data인스턴스에 저장
							MemberVo data = new MemberVo(id, pass, mail);
							passFind.add(data);
						}else { // 다를 경우 NULL리턴
							return null;
						}
					}
				}	
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return passFind;
	}

	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
