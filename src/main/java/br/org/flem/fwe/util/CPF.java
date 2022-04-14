package br.org.flem.fwe.util;


import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;


public class CPF {

	private static Logger logger = Logger.getLogger(CPF.class);

	private static final String MASK_CPF = "^\\d{11}$";
	private static final String MASK_FORMATED_CPF = "^\\d{3}.\\d{3}.\\d{3}-\\d{2}$";

	/**
	 * Método que realmente valida se determinado valor passado como parâmetro é
	 * um CPF válido.
	 *
	 * @param cpf
	 * @return
	 */
	public static boolean validarCPF(String cpf) {
		try {
			if (!GenericValidator.isBlankOrNull(cpf)) {
				if (!GenericValidator
						.matchRegexp(cpf, CPF.MASK_CPF)
						&& !GenericValidator.matchRegexp(cpf,
								CPF.MASK_FORMATED_CPF)) {

					return false;
				}
				int d1, d2;
				int digito1, digito2, resto;
				int digitoCPF;
				String nDigResult;

				String unformatedCpf = StringUtil.removeFormatacao(cpf);

				if(StringUtil.isSameCharacter(unformatedCpf)){
					return false;
				}

				d1 = d2 = 0;
				digito1 = digito2 = resto = 0;

				for (int nCount = 1; nCount < unformatedCpf.length() - 1; nCount++) {
					digitoCPF = Integer.valueOf(
							unformatedCpf.substring(nCount - 1, nCount))
							.intValue();

					// multiplique a ultima casa por 2 a seguinte por 3 a
					// seguinte por 4
					// e assim por diante.
					d1 = d1 + (11 - nCount) * digitoCPF;

					// para o segundo digito repita o procedimento incluindo
					// o primeiro
					// digito calculado no passo anterior.
					d2 = d2 + (12 - nCount) * digitoCPF;
				}

				// Primeiro resto da divisão por 11.
				resto = (d1 % 11);

				// Se o resultado for 0 ou 1 o digito é 0 caso contrário o
				// digito é 11
				// menos o resultado anterior.
				if (resto < 2)
					digito1 = 0;
				else
					digito1 = 11 - resto;

				d2 += 2 * digito1;

				// Segundo resto da divisão por 11.
				resto = (d2 % 11);

				// Se o resultado for 0 ou 1 o digito é 0 caso contrário o
				// digito é 11
				// menos o resultado anterior.
				if (resto < 2)
					digito2 = 0;
				else
					digito2 = 11 - resto;

				// Digito verificador do CPF que está sendo validado.
				String nDigVerific = unformatedCpf.substring(unformatedCpf
						.length() - 2, unformatedCpf.length());

				// Concatenando o primeiro resto com o segundo.
				nDigResult = String.valueOf(digito1)
				+ String.valueOf(digito2);

				// comparar o digito verificador do cpf com o primeiro resto
				// + o segundo
				// resto.
				if (nDigVerific.equals(nDigResult)) {
					return true;
				}
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("Erro na validação do CPF = " + cpf, e);
			return false;
		}
	}


	/** Recebe somente os números de um CPF e o retorna com a máscara correta.
     * @param String CPF a ser mascarado
     * @return String CPF mascarado
     */
    public static String formataCPF(String strCPF) {
    	if (strCPF==null) return null;
    	if (strCPF.length()==14) return strCPF;
    	if (strCPF.length()!=14 && !Valores.isInteger(strCPF)) return "";
    	if (strCPF.length()>14 || strCPF.length()<10) return "";
    	if (strCPF.length()==10)
    		return "0" + strCPF.substring(0,2) + "." + strCPF.substring(2,5) + "." + strCPF.substring(6,8) + "-" + strCPF.substring(8);
    	if (strCPF.length()==11)
    		return strCPF.substring(0,3) + "." + strCPF.substring(3,6) + "." + strCPF.substring(6,9) + "-" + strCPF.substring(9);
    	return "";
    }

    public static String completaComZeroeFormataCPF(String cpf) throws ParseException {
        int complementoZero = 11 - cpf.length();
        StringBuffer cpfComZeros = new StringBuffer();
        if (complementoZero > 0) {
            for (int i=0; i<complementoZero;i++) {
                cpfComZeros.append("0");
            }
        }
        cpfComZeros.append(cpf);

        String strCPF = cpfComZeros.toString();
        return strCPF.substring(0,3) + "." + strCPF.substring(3,6) + "." + strCPF.substring(6,9) + "-" + strCPF.substring(9);
    }

}
