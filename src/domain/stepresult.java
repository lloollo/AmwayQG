package domain;
public class stepresult {
		String caseID;
		String stepID;
		String stepActiom;
		String stepAcResult;
		testcase tec;
		public String getStepAcResult() {
			return stepAcResult;
		}
		public void setStepAcResult(String stepAcResult) {
			this.stepAcResult = stepAcResult;
		}
		public testcase getTec() {
			return tec;
		}
		public void setTec(testcase tec) {
			this.tec = tec;
		}
		String stepResult;
		String stepLog;
		public String getCaseID() {
			return caseID;
		}
		public void setCaseID(String caseID) {
			this.caseID = caseID;
		}
		public String getStepID() {
			return stepID;
		}
		public void setStepID(String stepID) {
			this.stepID = stepID;
		}
		public String getStepActiom() {
			return stepActiom;
		}
		public void setStepActiom(String stepActiom) {
			this.stepActiom = stepActiom;
		}
		public String getStepResult() {
			return stepResult;
		}
		public void setStepResult(String stepResult) {
			this.stepResult = stepResult;
		}
		public String getStepLog() {
			return stepLog;
		}
		public void setStepLog(String stepLog) {
			this.stepLog = stepLog;
		}
		@Override
		public String toString() {
			return "stepresult [caseID=" + caseID + ", stepID=" + stepID + ", stepActiom=" + stepActiom
					+ ", stepAcResult=" + stepAcResult + ", tec=" + tec + ", stepResult=" + stepResult + ", stepLog="
					+ stepLog + "]";
		}
		
		
}
