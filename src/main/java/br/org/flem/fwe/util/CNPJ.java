package br.org.flem.fwe.util;


import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;


public class CNPJ {
	
	private static Logger logger = Logger.getLogger(CNPJ.class);
	
	private static final String MASK_CNPJ = "^\\d{14}$";	
	private static final String MASK_FORMATED_CNPJ = "^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$";
	
	/**
	 * M�todo que realmente valida se determinado valor passado como par�metro �
	 * um CNPJ v�lido.
	 * 
	 * @param cnpj
	 * @return
	 */
	public static boolean validarCNPJ(String cnpj) {
		try {
			if (!GenericValidator.isBlankOrNull(cnpj)) {
				if (!GenericValidator.matchRegexp(cnpj,
						CNPJ.MASK_CNPJ)
						&& !GenericValidator.matchRegexp(cnpj,
								CNPJ.MASK_FORMATED_CNPJ)) {
					return false;
				}
				int soma = 0, dig;
				
				String unformatedCnpj = StringUtil.removeFormatacao(cnpj);
				
				if(StringUtil.isSameCharacter(unformatedCnpj)){
					return false;
				}
				
				String cnpj_calc = unformatedCnpj.substring(0, 12);
				
				char[] chr_cnpj = unformatedCnpj.toCharArray();
				
				/* Primeira parte */
				for (int i = 0; i < 4; i++)
					if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
						soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
				for (int i = 0; i < 8; i++)
					if (chr_cnpj[i + 4] - 48 >= 0
							&& chr_cnpj[i + 4] - 48 <= 9)
						soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
				dig = 11 - (soma % 11);
				
				cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer
						.toString(dig);
				
				/* Segunda parte */
				soma = 0;
				for (int i = 0; i < 5; i++)
					if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
						soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
				for (int i = 0; i < 8; i++)
					if (chr_cnpj[i + 5] - 48 >= 0
							&& chr_cnpj[i + 5] - 48 <= 9)
						soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
				dig = 11 - (soma % 11);
				cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer
						.toString(dig);
				
				if (unformatedCnpj.equals(cnpj_calc)) {
					return true;
				}
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.error("Erro na valida��o do CNPJ = " + cnpj, e);
			return false;
		}
	}
	
	
	/** 
     * Recebe um CNPJ e o retorna com m�scara correta.
     * @param String CNPJ a ser mascarado
     * @return String CNPJ mascarada
     */
    public static String formataCNPJ(String strCNPJ){
    	if (strCNPJ==null) return null;
    	if (strCNPJ.length()==18) return strCNPJ;
    	if (strCNPJ.length()!=18 && !Valores.isInteger(strCNPJ)) return "";
    	if (strCNPJ.length()>18 || strCNPJ.length() < 13) return "";
    	if (strCNPJ.length()==13)
    		return "0" + strCNPJ.substring(0,1) + "." + strCNPJ.substring(1,4) + "." + strCNPJ.substring(4,7) + "/" + strCNPJ.substring(7,11) + "-" + strCNPJ.substring(11);
    	if (strCNPJ.length()==14)
    		return strCNPJ.substring(0,2) + "." + strCNPJ.substring(2,5) + "." + strCNPJ.substring(5,8) + "/" + strCNPJ.substring(8,12) + "-" + strCNPJ.substring(12);
    	return "";
    }

}
