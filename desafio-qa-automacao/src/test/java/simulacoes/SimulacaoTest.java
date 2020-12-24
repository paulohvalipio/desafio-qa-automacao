package simulacoes;

import org.junit.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runners.MethodSorters;

import setup.Setup;

@TestMethodOrder(OrderAnnotation.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SimulacaoTest {
	
	SimulacaoController simcon = new SimulacaoController();

	@Test
	@Order(1)
	public void consultaTodasSimulacoesTest() {
		simcon.consultaTodasSimulacoes();
	}

	@Test
	@Order(2)
	public void consultaUmaSimulacaoCadastradaTest() {
		simcon.consultaUmaSimulacao(Setup.cpfDuplicado);
	}

	@Test
	@Order(3)
	public void consultaSimulacaoNaoCadastradaTest() {
		simcon.consultaUmaSimulacaoNaoCadastrada(Setup.cpfNaoCadastrado);
	}

	@Test
	@Order(4)
	public void tentarCadastrarSimulacaoParaCPFExistenteTest() {
		simcon.tentarCadastrarSimulacaoParaCPFExistente();
	}

	@Test
	@Order(5)
	public void cadastraSimulacaoTest() {
		simcon.cadastraSimulacao();
	}

	@Test
	@Order(6)
	public void removeSimulacaoTest() {
		simcon.removeSimulacao(setup.Setup.idSimulacaoAtual);
	}

	@Test
	@Order(7)
	public void tentarRemoverSimulacaoInexistenteTest() {
		simcon.tentarRemoverSimulacaoInexistente(999);
	}

	@Test
	@Order(8)
	public void fAlteraSimulacaoTest() {
		simcon.alteraSimulacao(setup.Setup.cpfAtual);
	}
	
	@Test
	@Order(9)
	public void tentaAlterarSimulacaoTest() {
		simcon.tentaAlterarSimulacao(setup.Setup.geraCPF());
	}

}
