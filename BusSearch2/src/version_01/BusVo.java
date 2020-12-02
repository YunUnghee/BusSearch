package version_01;

public class BusVo {
	private String[] Result;
	
	public BusVo(String start, String goal, String a) {
		
	}
	
	public BusVo(String[] Result) {
		this.Result = Result;
	}
	
	public String[] getResult() {
		return Result;
	}
}
