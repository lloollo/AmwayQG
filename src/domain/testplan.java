package domain;

public class testplan {
	String caseNo;
	String caseName;
	String caseState;
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseState() {
		return caseState;
	}
	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}
	@Override
	public String toString() {
		return "testplan [caseNo=" + caseNo + ", caseName=" + caseName + ", caseState=" + caseState + "]";
	}
	
}
