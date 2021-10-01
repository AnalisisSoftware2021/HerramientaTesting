package testing;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FanOut {
	
	String texto;

	public FanOut(String texto) {
		this.texto = texto;
	}

	public int calcularFanOut(String metodo) {
		String bodyMetodo = separarMetodo(metodo);
		ArrayList<String> nombresFunciones = nombresFunciones();
		Pattern regex2 = Pattern.compile("([a-zA-Z_{1}][a-zA-Z0-9_]+)(?=\\()");
		Matcher mat2 = regex2.matcher(bodyMetodo);
		int contador = 0;
		while(mat2.find()) {
			//System.out.println(mat2.group());
			if(nombresFunciones.contains(mat2.group()))
				contador++;
		}
		
		return (contador == 0) ? contador : contador - 1;
	}
	
	public String separarMetodo(String metodo) {
		Pattern regex = Pattern.compile("(" + metodo + "\\s?)([^\\.])([\\w|,|\\s|-|_|\\$]*)(.+?\\{)([^\\.][\\s|\\S]*(?=\\}))");
		Matcher mat = regex.matcher(texto);
		mat.find();
		String resultadoPrevio = mat.group();		
		String resultadoBody = resultadoPrevio.split("protected|public|private")[0];
		return resultadoBody;
	}
	
	public ArrayList<String> nombresFunciones() {
		Pattern regex = Pattern.compile("(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])");
		Pattern regex2 = Pattern.compile("([a-zA-Z_{1}][a-zA-Z0-9_]+)(?=\\()");
		Matcher mat = regex.matcher(texto);
		ArrayList<String> nombres = new ArrayList();
		while(mat.find()) {
			String resultadoPrevio = mat.group();	
			Matcher mat2 = regex2.matcher(resultadoPrevio);
			if (mat2.find())
				nombres.add(mat2.group());
			//System.out.println(mat2.group());
		}
		return nombres;
	}
	
}
