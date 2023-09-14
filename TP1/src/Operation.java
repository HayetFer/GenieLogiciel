
public enum Operation {
	PLUS('+') {public int calc(int a, int b) {return a + b;}},
	MOINS('-') {public int calc(int a, int b) {return a - b;}},
	FOIS('*') {public int calc(int a, int b) {return a * b;}},
	DIV('/') {public int calc(int a, int b) {return a / b;}};
	public abstract int calc(int a, int b);
	private char symbole;
	
	Operation(char leSymbole) {
		symbole = leSymbole;
	}
	
	public char getSymbole() {
		return symbole;
	}
}