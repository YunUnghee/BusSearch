package version_01;

public class MemberVo {
	private String ID;
	private String PASSWORD;
	private String SIGN_ID;
	private String SIGN_PASS;
	private String EMAIL;
	
	public MemberVo() {}
	
	
	public MemberVo(String ID) {
		this.ID = ID;
	}
	
	
	public MemberVo(String ID, String PASSWORD) { // �α����� ���� �޼ҵ�
		this.ID = ID;
		this.PASSWORD = PASSWORD;
	}
	
	public MemberVo(String SIGN_ID, String SIGN_PASS,String EMAIL) { // ��й�ȣ ã�� �� ȸ�������� �ϱ� ���� �޼ҵ�
		this.SIGN_ID = SIGN_ID;
		this.SIGN_PASS = SIGN_PASS;
		this.EMAIL = EMAIL;
	}

	public String getID() { // �Է¹��� ID ���� ����
		return ID;
	}

	public String getPASSWORD() { // �Է¹��� PW ���� ����
		return PASSWORD;
	}
	
	public String getSINGID() { // �Է¹��� SIGN_ID ���� ����
		return SIGN_ID;
	}
	public String getSIGNPASS() { // �Է¹��� SIGN_PW ���� ����
		return SIGN_PASS;
	}
	public String getEMAIL() { // �Է¹��� EMAIL ���� ����
		return EMAIL;
	}
}
