package setup;

import java.util.Random;

public class Setup {

	public static String urlBase = "http://localhost:8080";
	public static String urlRestricoes = urlBase + "/api/v1/restricoes";
	public static String urlSimulacoes = urlBase + "/api/v1/simulacoes";

	public static String cpfRestrito = "60094146012";
	public static String cpfNaoRestrito = "09201501951";
	public static String cpfAleatorio = "80692579036";
	public static String cpfInexistente = "12345678912";
	public static String cpfDuplicado = "66414919004";
	public static String cpfNaoCadastrado = "62648716050";
	public static String cpfAtual;
	public static String idSimulacaoAtual;
	public static String idSimulacaoNaoCadastrada;
	

	private static Random rand = new Random();
	private static char[] letras = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static String nomeAleatorio(int nCaracteres) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < nCaracteres; i++) {
			int ch = rand.nextInt(letras.length);
			sb.append(letras[ch]);
		}
		return sb.toString();
	}

	public static int valorAleatorio(int minimo, int maximo) {
		Random random = new Random();
		return random.nextInt((maximo - minimo) + 1) + minimo;
	}

	private static String calcDigitoVericador(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();
	}

	public static String geraCPF() {
		String iniciais = "";
		Integer numero;
		for (int i = 0; i < 9; i++) {
			numero = new Integer((int) (Math.random() * 10));
			iniciais += numero.toString();
		}
		return iniciais + calcDigitoVericador(iniciais);
	}
}
