package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineasEnBlanco {
	
	private String metodo;
	
	public LineasEnBlanco(String metodo) {
		this.metodo = metodo;
	}
	
	public int calcular() {
		metodo = metodo.replaceAll("\t{1,}","");
		Pattern regex = Pattern.compile("^(\n)",Pattern.MULTILINE);
		
		Matcher mat = regex.matcher(this.metodo);
		
		return (int)mat.results().count();
	}
}
