package domain;

public class testcase {
		String caseID;
		String caseTitle;
		String caseStep;
		String caseExpected;
		String caseAction;
		String caseElement;
		String caseresult;
		public String getCaseID() {
			return caseID;
		}
		public void setCaseID(String caseID) {
			this.caseID = caseID;
		}
		public String getCaseTitle() {
			return caseTitle;
		}
		public void setCaseTitle(String caseTitle) {
			this.caseTitle = caseTitle;
		}
		public String getCaseStep() {
			return caseStep;
		}
		public void setCaseStep(String caseStep) {
			this.caseStep = caseStep;
		}
		public String getCaseExpected() {
			return caseExpected;
		}
		public void setCaseExpected(String caseExpected) {
			this.caseExpected = caseExpected;
		}
		public String getCaseAction() {
			return caseAction;
		}
		public void setCaseAction(String caseAction) {
			this.caseAction = caseAction;
		}
		public String getCaseElement() {
			return caseElement;
		}
		public void setCaseElement(String caseElement) {
			this.caseElement = caseElement;
		}
		public String getCaseresult() {
			return caseresult;
		}
		public void setCaseresult(String caseresult) {
			this.caseresult = caseresult;
		}
		@Override
		public String toString() {
			return "testcase [caseID=" + caseID + ", caseTitle=" + caseTitle + ", caseStep=" + caseStep
					+ ", caseExpected=" + caseExpected + ", caseAction=" + caseAction + ", caseElement=" + caseElement
					+ ", caseresult=" + caseresult + "]";
		}
		
		
		
}
