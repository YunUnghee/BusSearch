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

	public ArrayList<MemberVo> login(String ID, String PASSWORD) { // �α����� ���� ������ ���̽� ������
		ArrayList<MemberVo> login = new ArrayList<MemberVo>();
		if (ID == null || PASSWORD == null) { //�Է¹��� ���� ���� ��� NULL ����
			return null;
		} else {
			try {
				connDB(); //�����ͺ��̽� ����

				String query = "SELECT ID, PASS FROM ID_INFO "; //ID_INFO ���� ��� ������ ��� ����
				if (ID != null) {
					query += "where ID = '" + ID + "'"; //�Է¹��� ���̵�� ���� ������ ���� ���
				}
				rs = stmt.executeQuery(query); //���� �Էº�
				rs.last();
				System.out.println("rs.getRow()" + rs.getRow());
				if (rs.getRow() == 0) { // �Է¹��� ID�� ���� ID ������ ���� ��� NULL ����
					return null;
				} else { // ������ ���� ��� ���� ���� ����
					System.out.println(rs.getRow() + "rows selected.........");
					rs.previous();
					while (rs.next()) { // �Է¹��� ID�� ���� ID�� ���� DB���� ������ ID�� PW�� ����
						String id = rs.getString("ID");
						String PASS = rs.getString("PASS");

						System.out.println(id + " : " + ID);
						System.out.println(PASS + " : " + PASSWORD);
						if (ID.equals(id) && PASSWORD.equals(PASS)) {
							MemberVo data = new MemberVo(id, PASS); //MemberVo �ν��Ͻ��� �ش� ������ �����Ͽ�, ����Ʈ�� ����.
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

	public ArrayList<MemberVo> signup(String ID, String PASSWORD, String MAIL) { //ȸ�������� ���� DB ������
		ArrayList<MemberVo> signup = new ArrayList<MemberVo>();
		if (ID.equals("") || PASSWORD.equals("") || MAIL.equals("")) { //�Է¹��� ������ 1���� ���� ��� NULL ����
			return null;
		} else {
			try {
				connDB();

				String query = "SELECT ID, PASS FROM ID_INFO "; //�Է¹��� ID�� ���� ID�� �ִ��� Ȯ��
				query += "where ID = '" + ID + "'";

				rs = stmt.executeQuery(query);
				rs.last();
				System.out.println("rs.getRow()" + rs.getRow()); // �˻� ����� ���

				if (rs.getRow() != 0) {// 1���� ���� ��� NULL ����
					return null;
				} else { // ���� ��� ���� ���� ����

					query = "INSERT INTO ID_INFO(ID,PASS,EMAIL) VALUES("; // DB�� �Է��ϱ� ���� ����
					query += "'" + ID + "'," + "'" + PASSWORD + "'," + "'" + MAIL + "')"; //�Է¹��� ID, PW, EMAIL�� DB�� ����
					rs = stmt.executeQuery(query);

					MemberVo data = new MemberVo(ID, PASSWORD, MAIL); //�Է��� �����͸� data �ν��Ͻ��� ����
					signup.add(data);
					return signup;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return signup;
	}

	public ArrayList<MemberVo> idcheck(String ID) { // �Է¹��� ID�� DB�� ����Ǿ� �ִ��� Ȯ���ϱ� ���� ������
		ArrayList<MemberVo> check = new ArrayList<MemberVo>();
		if (ID.equals("")) { // �Է¹��� ���̵� ���� ��� NULL ����
			return null;
		} else { //���� ��� ���� ���� ����
			try {
				connDB();

				String query = "SELECT ID, PASS FROM ID_INFO "; // ID�� �̿��Ͽ� �˻��ϴ� ����
				if (ID != null) {
					query += "where ID = '" + ID + "'";
				}
				rs = stmt.executeQuery(query);
				rs.last();
				System.out.println("rs.getRow()" + rs.getRow()); //�˻���� ���
				if (rs.getRow() != 0) { // 1���� ���� ��� NULL ����
					return null;
				}
				MemberVo data = new MemberVo(ID); // ���� ��� data �ν��Ͻ��� ������ ����
				check.add(data);
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}
	
	public ArrayList<MemberVo> passFind(String ID, String MAIL) { // ID�� EMAIL�� �Է¹޾� PW�� ����ϱ� ���� ������
		ArrayList<MemberVo> passFind = new ArrayList<MemberVo>();
		if(ID == null || MAIL == null) {// ID�� EMAIL �� 1���� �Էµ��� ������ NULL����
			return null;
		}else { // �׷��� ���� ��� ���� ���� ����
			try {
				connDB();
				
				String query = "SELECT ID, PASS, EMAIL FROM ID_INFO "; // ID,PW,EMAIL ��ü�� �˻��ϱ� ���� ����
				query += "where ID = '" + ID + "' AND EMAIL = '"+MAIL+"'";
				rs = stmt.executeQuery(query);
				rs.last();
				System.out.println("rs.getRow()" + rs.getRow());
				if (rs.getRow() == 0) {// �˻������ ���� ��� NULL ����
					return null;
				}else { //1�� �̻��� ��� ���� ���� ����
					rs.previous();
					while(rs.next()) { // DB���� ID, EMAIL, PW�� �ش� �����鿡 ����
						String id = rs.getString("ID");
						String pass = rs.getString("PASS");
						String mail = rs.getString("EMAIL");
						System.out.println(pass+" "+id+" "+mail);
						if(ID.equals(id)&&MAIL.equals(mail)) { // �Է°��� ID, EMAIL�� DB���� ������ ���� ��� ���� data�ν��Ͻ��� ����
							MemberVo data = new MemberVo(id, pass, mail);
							passFind.add(data);
						}else { // �ٸ� ��� NULL����
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
