package br.org.flem.fwe.util;

import br.org.flem.fwe.util.StringUtil;

public class Modulo11 {
	
	/**
	 * Calcula Digito Verificador Modulo 11 aplicando o peso informado.
	 * @param cadeia numerica sem o digito verificador.
	 * @param peso a ser aplicado no calculo do modulo (o peso deve ser sempre maior que 2).
	 * @return int digito verificador.
	 * @exception no caso de falha no calculo ou nos parâmetros informados.
	 * Ex: 
	 * +---+---+---+---+---+---+   +---+
	 * | 2 | 6 | 1 | 5 | 3 | 3 | - | 9 |
	 * +---+---+---+---+---+---+   +---+
	 *   |   |   |   |   |   |
	 *  x7  x6  x5  x4  x3  x2
	 *   |   |   |   |   |   |
	 * =14 =36  =5 =20  =9  =6
	 *   +---+---+---+---+---+-> = (90 x 10) / 11 = 81, resto 9 => DV = 9
	 */
	public static int calcularDVModulo11(String cadeiaNumerica, int peso) throws Exception {
		if (peso < 2){
			throw new Exception("Peso menor que 2.");
		}
		if (cadeiaNumerica == null)
			throw new IllegalArgumentException("Parâmetro cadeiaNumerica nulo.");
		
		try {
			cadeiaNumerica = StringUtil.removeFormatacao(cadeiaNumerica);
					
			int iPeso = 2;
			int somaFinal = 0;
			int resto = 0;
			int intermediario = 0;
			
			for ( int i = cadeiaNumerica.length(); i > 0; i--){
				intermediario = Integer.parseInt(cadeiaNumerica.substring((i-1),i));
				somaFinal += (intermediario * iPeso);
	
				if (iPeso == peso) {
					iPeso = 2;
				}
				else{
					iPeso++;
				}
			}
	
			
			somaFinal *= 10;
	
			resto = somaFinal % 11;

			// O dígito verificador é exatamente o resto da divisão
			return resto;
			
		}catch (Exception e) {
			throw new Exception("Erro no calculo do digito verificador do modulo 11.", e);
		}

	}	

}
