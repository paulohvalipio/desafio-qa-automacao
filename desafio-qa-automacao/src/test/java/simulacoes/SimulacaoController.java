package simulacoes;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.*;
import setup.Setup;

public class SimulacaoController {

	public void consultaTodasSimulacoes() {
		when().
			get(Setup.urlSimulacoes).
		then().
			statusCode(200);
	}

	public void consultaUmaSimulacao(String cpf) {
		given().
			pathParam("cpf", cpf).
		when().
			get(Setup.urlSimulacoes + "/{cpf}").
		then().statusCode(200).
			body("cpf",
				is(cpf));
	}

	public void consultaUmaSimulacaoNaoCadastrada(String cpf) {
		given().
			pathParam("cpf", cpf).
		when().
			get(Setup.urlSimulacoes + "/{cpf}").
		then().statusCode(404).
			body("mensagem", is("CPF "+cpf+" não encontrado"));
	}

	@SuppressWarnings("unchecked")
	public void cadastraSimulacao() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("nome", Setup.nomeAleatorio(5));
		requestParams.put("cpf", Setup.cpfAtual= Setup.geraCPF());
		requestParams.put("email", Setup.nomeAleatorio(5) + "@email.com");
		requestParams.put("valor", Setup.valorAleatorio(1000, 4000));
		requestParams.put("parcelas", 5);
		requestParams.put("seguro", true);

		
		setup.Setup.idSimulacaoAtual = 
		given().
			header("Content-Type", "application/json;charset=UTF-8").
			header("Accept", "application/json").
			body(requestParams.toJSONString()).
		when().
			post(Setup.urlSimulacoes).
		then().
			statusCode(201).
			extract().
			path("id").toString();
	}
	
	@SuppressWarnings("unchecked")
	public void tentarCadastrarSimulacaoParaCPFExistente() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("nome", Setup.nomeAleatorio(5));
		requestParams.put("cpf", Setup.cpfDuplicado);
		requestParams.put("email", Setup.nomeAleatorio(5) + "@email.com");
		requestParams.put("valor", Setup.valorAleatorio(1000, 4000));
		requestParams.put("parcelas", 5);
		requestParams.put("seguro", true);

		given().
			header("Content-Type", "application/json;charset=UTF-8").
			header("Accept", "application/json").
			body(requestParams.toJSONString()).
		when().
			post(Setup.urlSimulacoes).
		then().
			statusCode(400).
			body("mensagem", is("CPF duplicado"));
	}

	@SuppressWarnings("unchecked")
	public void alteraSimulacao(String cpf) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("nome", setup.Setup.nomeAleatorio(5)+ " alterado");
		requestParams.put("cpf", setup.Setup.geraCPF());
		requestParams.put("email", setup.Setup.nomeAleatorio(5) + "@email.com");
		requestParams.put("valor", setup.Setup.valorAleatorio(1000, 4000));
		requestParams.put("parcelas", 10);
		requestParams.put("seguro", false);

		setup.Setup.idSimulacaoAtual = 
		given().
			pathParam("cpf", cpf).
			header("Content-Type", "application/json;charset=UTF-8").
			header("Accept", "application/json").
			body(requestParams.toJSONString()).
		when().
			put(Setup.urlSimulacoes + "/{cpf}").
		then().
			statusCode(200).
			extract().
			path("id").toString();
	}
	

	@SuppressWarnings("unchecked")
	public void tentaAlterarSimulacao(String cpf) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("nome", setup.Setup.nomeAleatorio(5)+ " alterado");
		requestParams.put("cpf", setup.Setup.geraCPF());
		requestParams.put("email", setup.Setup.nomeAleatorio(5) + "@email.com");
		requestParams.put("valor", setup.Setup.valorAleatorio(1000, 4000));
		requestParams.put("parcelas", 10);
		requestParams.put("seguro", false);
 
		given().
			pathParam("cpf", cpf).
			header("Content-Type", "application/json;charset=UTF-8").
			header("Accept", "application/json").
			body(requestParams.toJSONString()).
		when().
			put(Setup.urlSimulacoes + "/{cpf}").
		then().
			statusCode(404).
			body("mensagem", is("CPF " + cpf + " não encontrado"));
	}
	
	public void removeSimulacao(String idSimulacao) {
		given().
		    pathParam("idSimulacao", idSimulacao).
		when().
			delete(Setup.urlSimulacoes + "/{idSimulacao}").
		then().
			statusCode(200).
			body(is("OK"));
	}

	//Não retorna a resposta 404, sempre retorna 200
	public void tentarRemoverSimulacaoInexistente(int idSimulacao) {
		given().
	    	pathParam("idSimulacao", idSimulacao).
		when().
			delete(Setup.urlSimulacoes + "/{idSimulacao}").
		then().
			statusCode(404);
	}
}

