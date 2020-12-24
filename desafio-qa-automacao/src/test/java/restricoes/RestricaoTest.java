package restricoes;

import org.junit.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runners.MethodSorters;

@TestMethodOrder(OrderAnnotation.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestricaoTest {
	
	RestricaoController restcon = new RestricaoController();

	@Test
	@Order(1)
	public void consultaCPFComRestricaoTest() {
		restcon.consultaCPFComRestricao();
	}

	@Test
	@Order(2)
	public void consultaCPFSemRestricaoTest() {
		restcon.consultaCPFSemRestricao();
	}

	@Test
	@Order(3)
	public void consultaRestricaoCPFInexistenteTest() {
		restcon.consultaRestricaoCPFInexistente();
	}

	@Test
	@Order(4)
	public void consultaRestricaoSemCPFTest() {
		restcon.consultaRestricaoSemCPF();
	}
}
