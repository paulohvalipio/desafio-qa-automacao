package restricoes;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import setup.Setup;

public class RestricaoController {
	    
    //Retorna a mensagem "O CPF x tem problema" ao inv�s de "O CPF x possui restri��o"
	public void consultaCPFComRestricao() {
		when().
			get(Setup.urlRestricoes + "/" + Setup.cpfRestrito).
		then().
			statusCode(200).
		    body("mensagem",is("O CPF "+Setup.cpfRestrito+" possui restri��o"));
	}
	
	public void consultaCPFSemRestricao() {
		when().
			get(Setup.urlRestricoes + "/" + Setup.cpfNaoRestrito).
		then().
			statusCode(204);
	}
	
	public void consultaRestricaoCPFInexistente() {
		when().
			get(Setup.urlRestricoes + "/" + Setup.cpfInexistente).
		then().
			statusCode(204);
	}
	
	public void consultaRestricaoSemCPF() {
		when().
			get(Setup.urlRestricoes).
		then().
			statusCode(404);
	}
}
