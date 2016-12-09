package domain;

public class theresoult {
        String caseID;
        String caseName;
        String describe;
        String caseactionresult;
        String caseresult;
		public String getCaseID() {
			return caseID;
		}
		public String getCaseactionresult() {
			return caseactionresult;
		}
		public void setCaseactionresult(String caseactionresult) {
			this.caseactionresult = caseactionresult;
		}
		public void setCaseID(String caseID) {
			this.caseID = caseID;
		}
		public String getCaseName() {
			return caseName;
		}
		public void setCaseName(String caseName) {
			this.caseName = caseName;
		}
		public String getDescribe() {
			return describe;
		}
		public void setDescribe(String describe) {
			this.describe = describe;
		}
		public String getCaseresult() {
			return caseresult;
		}
		public void setCaseresult(String caseresult) {
			this.caseresult = caseresult;
		}
		@Override
		public String toString() {
			return "theresoult [caseID=" + caseID + ", caseName=" + caseName + ", describe=" + describe
					+ ", caseactionresult=" + caseactionresult + ", caseresult=" + caseresult + "]";
		}
        
}
