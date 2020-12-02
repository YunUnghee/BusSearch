package version_01;


public class recod {
	
	private String ID;
	private String start;
	private String goal;
	
	recod(){}
	

	recod(String start, String goal, String ID){
		this.start = start;
		this.goal = goal;
		this.ID = ID;
	}
	
	String[] getInfo() {
		String[] info = new String[2];
		info[1] = start;
		info[2] = goal;
		
		return info;
	}
	void setinfo(String start, String goal, String ID) {
		this.start = start;
		this.goal = goal;	
		this.ID = ID;
		this.conDB();
	}
	
	void conDB() {
		try {
			BusDAO dao = new BusDAO();
			dao.Recoding(ID, start, goal);
			TabClass2 t = new TabClass2(ID);
			t.bt_re.doClick();
		}catch(Exception e){}
	}
}
