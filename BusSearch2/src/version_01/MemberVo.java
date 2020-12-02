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
	
	
	public MemberVo(String ID, String PASSWORD) { // 로그인을 위한 메소드
		this.ID = ID;
		this.PASSWORD = PASSWORD;
	}
	
	public MemberVo(String SIGN_ID, String SIGN_PASS,String EMAIL) { // 비밀번호 찾기 및 회원가입을 하기 위한 메소드
		this.SIGN_ID = SIGN_ID;
		this.SIGN_PASS = SIGN_PASS;
		this.EMAIL = EMAIL;
	}

	public String getID() { // 입력받은 ID 값을 리턴
		return ID;
	}

	public String getPASSWORD() { // 입력받은 PW 값을 리턴
		return PASSWORD;
	}
	
	public String getSINGID() { // 입력받은 SIGN_ID 값을 리턴
		return SIGN_ID;
	}
	public String getSIGNPASS() { // 입력받은 SIGN_PW 값을 리턴
		return SIGN_PASS;
	}
	public String getEMAIL() { // 입력받은 EMAIL 값을 리턴
		return EMAIL;
	}
}
