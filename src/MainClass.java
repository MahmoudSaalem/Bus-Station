import java.text.ParseException;

public class MainClass {
	
	public static void main(String[] args) throws ParseException {
		
		Parser parse = new Parser();
		parse.filesCheck();
		parse.loadData();
		Mainscreen ms = new Mainscreen();
		ms.setLocationRelativeTo(null);
		ms.setVisible(true);
	}
}
