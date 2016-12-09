package domain;

public class customEle {
	String Step;
	String Expected;
	String action;
	String Element;
	public String getStep() {
		return Step;
	}
	public void setStep(String step) {
		Step = step;
	}
	public String getExpected() {
		return Expected;
	}
	public void setExpected(String expected) {
		Expected = expected;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getElement() {
		return Element;
	}
	public void setElement(String element) {
		Element = element;
	}
	@Override
	public String toString() {
		return "customEle [Step=" + Step + ", Expected=" + Expected + ", action=" + action + ", Element=" + Element
				+ "]";
	}
	
	
}
