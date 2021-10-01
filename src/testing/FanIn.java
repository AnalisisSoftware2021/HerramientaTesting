package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FanIn {

	String texto;

	public FanIn(String texto) {
		this.texto = texto;
	}

	public int calcularFanIn(String metodo) {
		int contador = 0;
		String regex = "(new |[.])" + metodo + "\\(";
		
		//if(texto.matches("(public|private|protected) (int|boolean|String|float|double|long) "+metodo)) 
			
		Pattern pat = Pattern.compile(regex);
		
		Matcher mat = pat.matcher(texto);
		while (mat.find())
			contador++;
		
		return contador;
	}
}
