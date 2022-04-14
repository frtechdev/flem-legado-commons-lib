package br.org.flem.fwe.util;

import java.util.ArrayList;
import java.util.List;

/**
 * REVER
 * Utilitária para realizar abreviações de nomes de pessoas e logradouros.
 *  ***** LOGRADOUROS: *****
	Aeroporto, Alameda, Área, Avenida, Campo, Chácara, Colônia, Condomínio, Conjunto, Distrito,
	Esplanada, Estação, Estrada, Favela, Fazenda, Feira, Jardim, Ladeira, Lago, Lagoa, Largo,
	Loteamento, Morro, Núcleo, Parque, Passarela, Pátio, Praça, Quadra, Recanto, Residencial, Rodovia, 
	Rua, Setor, Sítio, Travessa, Trecho, Trevo, Vale, Vereda, Via, Viaduto, Viela, Vila
 *	
 * @author Grupo Framework - Componentes
 * @version 1.0, 04/01/2005
 * @since 1.0
 * 
 */
public class Abreviatura {
	
	/**
	 * Flag utilizada para indicar a retirada ou não das preposições nas abreviações. 
	 */
	private boolean retiraPreposicoes = true;
	
	/**
	 * String contendo as preposições que poderão ser excluidas nas abreviações.
	 */
	private String preposicoes = "a,e,o,da,das,de,des,di,do,dos,na,nas,ne,no,nos";
	
	/**
	 * String contendo os tipos de logradouros e suas respectivas abreviações 
	 *  separados pelo sinal de "=". Ex: Rua será substituido por R
	 */
	private String tipoLogradouroAbreviacoes = "ACAMPAMENTO=ACAMP,ACESSO=AC,AD=ROAD,AEROPORTO=AER,ALAMEDA=AL,ALTO=AT,AREA=A,AREA ESPECIAL=AE,ARTERIA=ART,ATALHO=ATL,AVENIDA=AV,AVENIDA CONTORNO=AV-CONT,"
		+"BAIXA=BX,BALAO=BLO,BALNEARIO=BAL,BECO=BC,BELVEDERE=BELV,BLOCO=BL,BOSQUE=BSQ,BOULEVARD=BVD,BURACO=BCO,"
		+"CAIS=C,CALCADA=CALC,CAMINHO=CAM,CAMPO=CPO,CANAL=CAN,CHACARA=CHAP,CHAPADAO=CHAP,CIRCULAR=CIRC,COLONIA=COL,COMPLEXO VIARIO=CMP-VR,CONDOMINIO=COND,CONJUNTO=CJ,CORREDOR=COR,CORREGO=CRG,"
		+"DESCIDA=DSC,DESVIO=DSV,DISTRITO=DT,"
		+"ELEVADA=EVD,ENTRADA PARTICULAR=ENT-PART,ENTRE QUADRA=EQ,ESCADA=ESC,ESPLANADA=ESP,ESTACAO=ETC,ESTACIONAMENTO=ESTC,ESTADIO=ETD,ESTANCIA=ETN,EST=ESTRADA,ESTRADA MUNICIPAL=EST-MUN,"
		+"FAVELA=FAV,FAZENDA=FAZ,FEIRA=FRA,FERROVIA=FER,FONTE=FNT,FORTE=FTE,"
		+"GALERIA=GAL,GRANJA=GJA,"
		+"HABITACIONAL=HAB,"
		+"ILHA=IA,"
		+"JARDIM=JD,JARDINETE=JDE,"
		+"LADEIRA=LD,LAGO=LG,LAGOA=LGA,LARGO=LRG,LOTEAMENTO=LOT,"
		+"MARINA=MNA,MODULO=MOD,MONTE=MTE,MORRO=MRO,"
		+"NUCLEO=NUC,"
		+"PARADA=PDA,PARADOURO=PDO,PARALELA=PAR,PARQUE=PRQ,PASSAGEM=PSG,PASSAGEM SUBTERRANEA=PSG-SUB,PASSARELA=PSA,PASSEIO=PAS,PATIO=PAT,PONTA=PNT,PONTE=PTE,PORTO=PTO,PRACA=PC,PRACA DE ESPORTES=PC-ESP,PRAIA=PR,PROLONGAMENTO=PRL,"
		+"QUADRA=Q,QUINTA=QTA,QUINTAS=QTAS,"
		+"RAMAL=RAM,RAMPA=RMP,RECANTO=REC,RESIDENCIAL=RES,RETA=RET,RETIRO=RER,RETORNO=RTN,RODO ANEL=ROD-AN,RODOVIA=ROD,ROTATORIA=RTT,ROTULA=ROT,RUA=R,RUA DE LIGACAO=R-LIG,RUA DE PEDESTRE=R-PED,"
		+"SERVIDAO=SRV,SETOR=ST,SITIO=SIT,SUBIDA=SUB,"
		+"TERMINAL=TER,TRAVESSA=TV,TRAVESSA PARTICULAR=TV-PART,TRECHO=TRV,TREVO=TRV,TRINCHEIRA=TCH,TUNEL=TUN,"
		+"UNIDADE=UNID,"
		+"VALA=VAL,VALE=VLE,VARIANTE=VRTE,VEREDA=VER,VIA=V,VIA DE ACESSO=V-AC,VIA DE PEDESTRE=V-PED,VIA ELEVADO=V-EVD,VIA EXPRESSA=V-EXP,VIADUTO=VD,VIELA=VLA,VILA=VL,"
		+"ZIGUE-ZAGUE=ZIG-ZAG=";

	/**
	 * String contendo os tipos de Sobrenomes que podem ser abreviados e suas respectivas abreviações 
	 *  separados pelo sinal de "=". Ex: Rua será substituido por R
	 */
	private String tipoSobrenomeAbreviacoes = "Junior=Jr,Neto=Nt,Filho=Fo";

	/**
	 * String contendo as titulações de logradouros e suas respectivas abreviações 
	 *  separados pelo sinal de "=". Ex: Padre será substituido por Pe
	 */
	private String titulacaoLogradouroAbreviacoes = "ACADEMICO=ACAD,ALMIRANTE=ALM,ARQUITETO=ARQT,"+
		"BACHAREL=BEL,BARAO=BR,BARONESA=BARON,BRIGADEIRO=BRG,"+
		"CABO=CB,CAPITAO=CAP,CARDEAL=CARD,CIENTISTA=CIENT,COMANDANTE=CMTE,COMENDADOR=CDADOR,COMISSARIO=CMS,COMPOSITOR=COMP,CONDE=CDE,CONDESSA=CDES,CONEGO=CON,CONSELHEIRO=CONS,CORONEL=CEL,CORRETOR=CORR,"+
		"DELEGADO=DEL,DEPUTADO=DEP,DESEMBARGADOR=DES,DOM=D,DONA=DA,DOUTOR=DR,DOUTORA=DRA,DUQUE=DQ,DUQUESA=DQS," +
		"EMBAIXADOR=EMBAIX,EMBAIXATRIZ=EMBAIX,ENGENHEIRO=ENG,ESCRITOR=ESCR,ESTUDANTE=ESTUD,EXPEDICIONARIO=EXP," +
		"FARMACEUTICO=FARM,FREI=FR,"+
		"GENERAL=GAL,"+
		"HISTORIADOR=HISTOR,"+
		"IMPERADOR=IMPER,IMPERATRIZ=IMPER,INTENDENTE=INTD,"+
		"JARDIM=JD,JORNALISTA=JORN," +
		"MADAME=MME,MAESTRO=MTO,MAJOR=MAJ,MARECHAL=MAL,MARQUES=MARQ,MARQUESA=MAQSA,MEDICO=MED,MESTRE=MEST,MINISTRO=MIN,MISSIONARIO=MISSION,MONSENHOR=MONS,"+
		"NOSSA SENHORA=NS," +
		"OPERARIO=OPER,"+
		"PADRE=PE,PINTOR=PINT,POETA=POE,PRESIDENTE=PRES,PRINCESA=PRINCS,PRINCIPE=PRINC,PROFESSOR=PROF,PROFESSORA=PROF,PUBLICITARIO=PUBL,"+
		"QUIMICO=QUIM," +
		"REGENTE=REG,REVERENDO=REV,"+
		"SANTO=STO,SAO=S,SARGENTO=SGT,SENADOR=SEN,SERVIDAO=SERV,SERVIDOR=SRV,SERVIDORA=SRV,SOLDADO=SOLD,"+
		"TENENTE=TEN," +
		"VEREADOR=VER,VIGARIO=VIG,VISCONDE=VISC";
		
	/**
	 * Seta quais as titulações de logradouros que serão abreviados e suas 
	 *  respectivas abreviações. Exemplo de sintaxe esperado pelo 
	 *  método: "Padre=Pe,Visconde=Visc"
	 * Obs: Qualquer informação passada que fuja deste padrão de sintaxe
	 *  fará com que a abreviação não funcione corretamente.
	 * 
	 * @param nomeLogradouroAbreviacoes - String contendo as titulações de
	 *  logradouros e suas respectivas abreviações
	 */
	public void setTitulacaoLogradouroAbreviacoes(String titulacaoLogradouroAbreviacoes) {
		this.titulacaoLogradouroAbreviacoes = titulacaoLogradouroAbreviacoes;
	}
	
	/**
	 * Seta quais os tipos de logradouros que serão abreviados e suas 
	 *  respectivas abreviações. Exemplo de sintaxe esperado pelo 
	 *  método: "Rua=R,Avenida=Av,Praça=P"
	 * Obs: Qualquer informação passada que fuja deste padrão de sintaxe
	 *  fará com que a abreviação não funcione corretamente.
	 * 
	 * @param tipoLogradouroAbreviacoes - String contendo os tipos de
	 *  logradouros e suas respectivas abreviações
	 */
	public void setTipoLogradouroAbreviacoes(String tipoLogradouroAbreviacoes) {
		this.tipoLogradouroAbreviacoes = tipoLogradouroAbreviacoes;
	}

	/**
	 * Seta quais os tipos de Sobrenomes que poderão ser abreviados e suas 
	 *  respectivas abreviações. Exemplo de sintaxe esperado pelo 
	 *  método: "Junior=Jr,Neto=Nt,Sobrinho=So,Filho=Fo"
	 * Obs: Qualquer informação passada que fuja deste padrão de sintaxe
	 *  fará com que a abreviação não funcione corretamente.
	 * 
	 * @param tipoSobrenomeAbreviacoes - String contendo os tipos de
	 *  sobrenome e suas respectivas abreviações
	 */
	public void setTipoSobrenomeAbreviacoes(String tipoSobrenomeAbreviacoes) {
		this.tipoSobrenomeAbreviacoes = tipoSobrenomeAbreviacoes;
	}
	
	
	/**
	 * Seta as preposições que poderão ser excluídas na abreviação.
	 *  Exemplo de sintaxe esperado pelo método: "a,e,das,de,nos";
	 * Obs: Qualquer informação passada que fuja deste padrão de sintaxe
	 *  fará com que a abreviação não funcione corretamente.
	 * 
	 * @param preposicoes - String contendo as preposições.
	 */
	public void setPreposicoes(String preposicoes) {
		this.preposicoes = preposicoes;
	}
	
	/**
	 * Possibilita ou não a exclusão das preposições na abreviação.
	 * 
	 * @param retiraPreposicoes - boolean
	 *  true = retirar as preposições (default)
	 *  false = não retirar as preposições
	 */
	public void setRetiraPreposicoes(boolean retiraPreposicoes) {
		this.retiraPreposicoes = retiraPreposicoes;
	}
	
	/**
	 * Abrevia nome até a quantidade de caracteres informada, seguindo
	 *  as regras abaixo:
	 *  - O primeiro e o último nome nunca são abreviados;
	 *  - Caso o nome original não ultrapasse a quantidade máxima 
	 *    de caracteres o nome original é retornado se nenhuma alteração;
	 *  - Se mesmo depois de feita a abreviação o nome continuar a exceder 
	 *    quantidade máxima de caracteres são feitas remoções dos sobrenomes
	 *    internos, sem alterar o último nome.
	 *  
	 * @param nome - String contendo nome a ser abreviado
	 * @param qtdMaxCaracteres - int contendo a quantidade máxima 
	 *  de caracteres para o nome informado 
	 * @return String contendo o nome abreviado
	 * @throws Exception - quando mesmo abreviado, o nome continuar a 
	 *  exceder a quantidade máxima de caracteres  
	 */
	public String abreviarNome(String nome, int qtdMaxCaracteres) throws Exception {
		if(nome == null)
			return null;
		StringBuffer sbNome = new StringBuffer(nome);
		
		if (nome.length() > qtdMaxCaracteres) {
			String arrayNomes[] = nome.trim().split(" ");
			if (arrayNomes.length > 2) {
				String sobreNomes = "";
				for (int i=arrayNomes.length-2; i > 0; i--) {
					sbNome = new StringBuffer();
					sobreNomes = this.abreviarString(arrayNomes[i]) + sobreNomes;
					for (int j = 0; j < arrayNomes.length; j++) {
						if (i == j) {
							sbNome.append(sobreNomes + arrayNomes[arrayNomes.length-1]);
							break;
						}
						sbNome.append(arrayNomes[j] + " ");
					}
					
					if (sbNome.toString().length() < qtdMaxCaracteres) {
						break;
					} else if (i == 1) {						

						// Se mesmo abreviado o nome continuar a ultrapassar a qtdMaxCaracteres deve-se retirar os sobrenomes do meio do nome
						String arrayNomeAbreviado[] = sbNome.toString().split(" ");

						// Caso o Ultimo nome seja Junior, Neto, Sobrinho, Filho, pode ser abreviado
						arrayNomeAbreviado[arrayNomeAbreviado.length-1] = this.abreviarSobrenome(arrayNomeAbreviado[arrayNomeAbreviado.length-1]);
						
						for (int k = arrayNomeAbreviado.length-2; k > 0; k--) {							
							sbNome = new StringBuffer();
							for (int y = 0; y < arrayNomeAbreviado.length; y++) {
								if (y == k) {
									sbNome.append("");
								}else{
									sbNome.append(arrayNomeAbreviado[y] + " ");
								}								
							}
							arrayNomeAbreviado = sbNome.toString().split(" ");
							if (sbNome.toString().trim().length() <= qtdMaxCaracteres) {
								break;
							} else if (k == 1) {
								throw new Exception("Não foi possível abreviar o nome: " + nome + ". Pois mesmo abreviado ultrapassa a quantidade de " + qtdMaxCaracteres + " caracteres.");
							}
						}
						
					}
				}			
				
			} else {
				throw new Exception("Não foi possível abreviar o nome: " + nome + ". Pois mesmo abreviado ultrapassa a quantidade de " + qtdMaxCaracteres + " caracteres.");
			}	
		}
		return sbNome.toString().trim();
	}
	
	private String[] quebraPalavras(String nome){
		String[] retorno = null;
		if(nome != null){
			List<String> nomes = new ArrayList<String>();
			while(nome.indexOf("(")>=0){
				int indice1 = nome.indexOf("(");
				String temp = nome.substring(0, indice1);
				if(temp != null && temp.trim().length() > 0){
					String[] tempArray = temp.split(" ");
					for(String s : tempArray){
						nomes.add(s);
					}
				}
				int indice2 = nome.indexOf(")");
				if(indice2 > indice1){
					String nomeParenteses = nome.substring(indice1, indice2+1);
					if(nomeParenteses != null);{
						nomeParenteses = nomeParenteses.replace("(", "").replace(")","");
						nomes.add("("+nomeParenteses+")");
					}
					nome = nome.substring(indice2+1);
				}else{
					nome = nome.substring(indice1);
				}
			}
			if(nome != null && nome.trim().length() > 0){
				String[] tempArray = nome.split(" ");
				for(String s : tempArray){
					nomes.add(s);
				}
			}
			retorno = new String[nomes.size()];
			for(int i=0; i<nomes.size(); i++){
				retorno[i] = nomes.get(i);
			}
		}
		return retorno;
	}
	
	/**
	 * Abrevia logradouro até a quantidade de caracteres informada seguindo
	 *  as regras abaixo:
	 *  - Caso o logradouro original não ultrapasse a quantidade máxima 
	 *    de caracteres o nome original é retornado se nenhuma alteração;
	 *  - O Tipo identificador de logradouro sempre é abreviado 
	 *  - O primeiro e o último nome do logradouro nunca são abreviados, 
	 *    exceção feita de acordo com o parâmetro nomeLogradouroAbreviacoes;
	 *  - Se mesmo depois de feita a abreviação o logradouro continuar a 
	 *    exceder quantidade máxima de caracteres são feitas remoções dos 
	 *    nomes internos ao logradouro, sem alterar o último nome.
	 *  
	 * @param nome - String contendo logradouro a ser abreviado
	 * @param qtdMaxCaracteres - int contendo a quantidade máxima 
	 *  de caracteres para o logradouro informado 
	 * @return String contendo o logradouro abreviado
	 * @throws Exception - quando mesmo abreviado, o logradouro continuar a 
	 *  exceder a quantidade máxima de caracteres  
	 */
	public String abreviarLogradouro(String logradouro, int qtdMaxCaracteres) throws Exception {
		if(logradouro == null)
			return null;
		String retorno = null;
		//StringBuffer sbNome = new StringBuffer();
		String arrayLogradouro[] = quebraPalavras(logradouro.trim());
		
		int quantidadeTitulacao = 0;
		int quantidadeTipo = 0;
		int quantidadeParenteses = 0;
		// Abrevia o identificador do logradouro (Rua, Avenida, Praça)
		
		//Verifica tipo Logradouro, titulação, nomes entre () e vai abreviando quando encontra
		//caso o tamanho seja desejado retorna o resultado
		for(int i=0; i<arrayLogradouro.length; i++){
			if(verificarTipoLogradouro(arrayLogradouro[i])){
				quantidadeTipo++;
				arrayLogradouro[i] = this.abreviarTipoLogradouro(arrayLogradouro[i]);
			}else if(verificarTitulacao(arrayLogradouro[i])){
				quantidadeTitulacao++;
				arrayLogradouro[i] = this.abreviarTitulacaoLogradouro(arrayLogradouro[i]);
			}else if(verificarParenteses(arrayLogradouro[i])){
				quantidadeParenteses++;
				String tmp = arrayLogradouro[i];
				tmp = tmp.replace("(", "").replace(")","");
				tmp = tentarAteAbreviarLogradouro(tmp, tmp.length() / 3);
				tmp = "(" + tmp + ")";
				arrayLogradouro[i] = tmp;
			}else{
				arrayLogradouro[i] = arrayLogradouro[i];
			}
		}
		logradouro = montaNome(arrayLogradouro);
		
		arrayLogradouro = quebraPalavras(logradouro);
		//procura por preposições e as retira caso indicado para isso
		//caso o tamanho seja desejado retorna o resultado
		if(logradouro.length() > qtdMaxCaracteres){
			for (int j = 0; j < arrayLogradouro.length; j++) {
				logradouro = montaNome(arrayLogradouro);
				if(logradouro.length() <= qtdMaxCaracteres)
					return logradouro;
					
				if(this.verificarPreposicao(arrayLogradouro[j]) && this.retiraPreposicoes){
					arrayLogradouro[j] = "";
				}
			}
			arrayLogradouro = quebraPalavras(logradouro);
		}
		//caso o último nome seja muito curto tenta manter o anterior
		if(arrayLogradouro[arrayLogradouro.length-1].length() < 4)
			quantidadeParenteses ++;
		
		int quantidadePalavras = (quantidadeTipo +  2 ) + quantidadeParenteses;
		int indiceInicialPalavra = quantidadeTipo + quantidadeTitulacao;
				
		if(logradouro.length() > qtdMaxCaracteres){
			arrayLogradouro = quebraPalavras(logradouro);
			
			
			if (arrayLogradouro.length > quantidadePalavras) {
				for (int i = arrayLogradouro.length-(2 + quantidadeParenteses); i > indiceInicialPalavra; i--) {
				//abrevia palavra por palavra até conseguir alcaçar o tamanho desejado
					String abreviado = arrayLogradouro[i];
					if((abreviado.trim().length() > 2)
							&& (!isTipoLogradouroAbreviado(abreviado))
							&& (!isTitulacaoAbreviada(abreviado))){
						abreviado = this.abreviarString(abreviado);
					}else{
						abreviado = "";
					}
					arrayLogradouro[i] = abreviado;
					logradouro = montaNome(arrayLogradouro);
					if(logradouro.length() <= qtdMaxCaracteres)
						return logradouro;
					else{
						String[] arrayTemp = new String[arrayLogradouro.length];
						for(int j=0; j<arrayLogradouro.length; j++){
							arrayTemp[j] = arrayLogradouro[j];
						}
						// Se mesmo abreviando a palavra continuar a ultrapassar o tamanho
						// tenta retirar os nomes do meio 
						for (int k = arrayLogradouro.length-(2 + quantidadeParenteses); k >= i; k--) {							
							if (i == k) 
								arrayLogradouro[i] = "";
							arrayTemp[k] = "";
							logradouro = montaNome(arrayTemp);
							if(logradouro.length() <= qtdMaxCaracteres)
								return logradouro;
							//}
						}
						logradouro = montaNome(arrayLogradouro);
						if(logradouro.length() <= qtdMaxCaracteres)
							return logradouro;
						
					}
					logradouro = montaNome(arrayLogradouro);
				}				
				
			} else {
				throw new Exception("Não foi possível abreviar o nome: " + logradouro + ". Pois mesmo abreviado ultrapassa a quantidade de " + logradouro + " caracteres.");
			}
		}
		retorno = montaNome(arrayLogradouro);
		return retorno;
	}
	
	private String montaNome(String[] pedacos){
		StringBuffer nome = new StringBuffer();
		if(pedacos != null){
			for(int i=0; i<pedacos.length; i++){
				String pedaco = pedacos[i];
				if(i > 0)
					nome.append(" ");
				if(pedaco != null && !pedaco.trim().equals(""))
					nome.append(pedacos[i]);
			}
		}
		String retorno = nome.toString();
		while(retorno.contains("  "))
			retorno = retorno.replace("  ", " ");
		return retorno;
	}
	
	/**
	 * Abrevia a String passada como parametro verificando ela não é 
	 *  uma preposição. Se for preposição retorna uma string vazia, senão
	 *  retorna a primeira letra da string informada seguida de um ponto.
	 *  Ex: str="Marcos" - return="M" 
	 * 
	 * @param str - String que se deseja abreviar 
	 * @return String abreviada
	 */
	private String abreviarString(String str) {
		if (this.verificarPreposicao(str) == false && (!str.equals("")) ) {
			str = str.substring(0, 1) + "";
		} else {
			if (retiraPreposicoes) {
				return "";
			}
		}
		return str + " ";
	}
	
	/**
	 * Verifica se a string passada como parâmetro é uma titulação de
	 *  logradouro. Se for a titulação do logradouro é abreviado da forma informada no 
	 *  atributo titulacaoLogradouroAbreviacoes. 
	 * 
	 * @param str - String a ser verificada e abreviada
	 * @return - String abreviada ou a própria string informada caso 
	 *  esta não seja uma titulação identificada de logradouro. 	 * 
	 */
	private String abreviarTitulacaoLogradouro(String str){
		String arrayAbreviaturas[] = this.titulacaoLogradouroAbreviacoes.split(",");
		for (int i=0; i < arrayAbreviaturas.length; i++) {
			String arrayTipoAbreviado[] = arrayAbreviaturas[i].split("=");
			if (str.equalsIgnoreCase(arrayTipoAbreviado[0])) {
				str = arrayTipoAbreviado[1];
				break;
			}
		}
		return str;
	}

	
	/**
	 * Verifica se a string passada como parâmetro é um sobrenome abreviável. 
	 * Se for, o sobrenome é abreviado de acordo com o atributo tipoSobrenomeAbreviacoes. 
	 * 
	 * @param str - String a ser verificada e abreviada
	 * @return - String abreviada ou a própria string informada caso 
	 *  esta não seja um sobrenome abreviável * 
	 */
	private String abreviarSobrenome(String str){
		String arrayAbreviaturas[] = this.tipoSobrenomeAbreviacoes.split(",");
		for (int i=0; i < arrayAbreviaturas.length; i++) {
			String arrayTipoAbreviado[] = arrayAbreviaturas[i].split("=");
			if (str.equalsIgnoreCase(arrayTipoAbreviado[0])) {
				str = arrayTipoAbreviado[1];
				break;
			}
		}
		return str;
	}

	
	
	/**
	 * Verifica se a String passada como parâmetro é uma preposição.
	 *  
	 * @param str - String a ser analisada
	 * @return boolean
	 *  true = a string passada como parâmetro É uma preposição 
	 *  false - a string passada como parâmetro NÃO é uma preposição
	 */
	private boolean verificarPreposicao(String str) {
		boolean encontrouPreposicao = false;
		if(this.preposicoes != null){
			String arrayPreposicoes[] = this.preposicoes.split(",");
			for (int i=0; i < arrayPreposicoes.length; i++) {
				if (str.equalsIgnoreCase(arrayPreposicoes[i])) {
					encontrouPreposicao = true;
					break;
				}
			}
		}
		return encontrouPreposicao;
	}
	
	private boolean verificarParenteses(String str){
		boolean encontrouParenteses = false;
		if(str != null){
			int indice1 = str.indexOf("(");
			int indice2 = str.indexOf(")");
			if(indice1 >= 0 && indice2 >= 0 && indice2 > indice1)
				encontrouParenteses = true;
		}
		return encontrouParenteses;
	}
	
	/**
	 * 
	 */
	private boolean verificarTitulacao(String str){
		boolean encontrouTitulacao = false;
		if(this.titulacaoLogradouroAbreviacoes != null){
			String arrayTitulacoes[] = this.titulacaoLogradouroAbreviacoes.split(",");
			for (int i=0; i < arrayTitulacoes.length; i++) {
				String arrayTitulacaoAbreviado[] = arrayTitulacoes[i].split("=");
				if (str.equalsIgnoreCase(arrayTitulacaoAbreviado[0])) {
					encontrouTitulacao = true;
					break;
				}
			}
		}
		return encontrouTitulacao;
	}
	
	private boolean verificarTipoLogradouro(String str){
		boolean encontrouTipo = false;
		if(this.tipoLogradouroAbreviacoes != null){
			String arrayTipos[] = this.tipoLogradouroAbreviacoes.split(",");
			for (int i=0; i < arrayTipos.length; i++) {
				String arrayTipoAbreviado[] = arrayTipos[i].split("=");
				if (str.equalsIgnoreCase(arrayTipoAbreviado[0])) {
					encontrouTipo = true;
					break;
				}
			}
		}
		return encontrouTipo;
	}
	
	private boolean isTitulacaoAbreviada(String str){
		boolean encontrou = false;
		if(this.titulacaoLogradouroAbreviacoes != null){
			String arrayTitulacoes[] = this.titulacaoLogradouroAbreviacoes.split(",");
			for (int i=0; i < arrayTitulacoes.length; i++) {
				String arrayTitulacaoAbreviado[] = arrayTitulacoes[i].split("=");
				if (str.equalsIgnoreCase(arrayTitulacaoAbreviado[1])) {
					encontrou = true;
					break;
				}
			}
		}
		return encontrou;
	}
	
	private boolean isTipoLogradouroAbreviado(String str){
		boolean encontrou = false;
		if(this.tipoLogradouroAbreviacoes != null){
			String arrayTipos[] = this.tipoLogradouroAbreviacoes.split(",");
			for (int i=0; i < arrayTipos.length; i++) {
				String arrayTipoAbreviado[] = arrayTipos[i].split("=");
				if (str.equalsIgnoreCase(arrayTipoAbreviado[1])) {
					encontrou = true;
					break;
				}
			}
		}
		return encontrou;
	}


	/**
	 * Verifica se a string passada como parâmetro é um tipo identificador de
	 *  logradouro. Se for o logradouro é abreviado da forma informada no 
	 *  atributo tipoLogradouroAbreviacoes. 
	 * 
	 * @param str - String a ser verificada e abreviada
	 * @return - String abreviada ou a própria string informada caso 
	 *  esta não seja um tipo identificador de logradouro. 
	 */
	private String abreviarTipoLogradouro(String str) {
		if(this.tipoLogradouroAbreviacoes != null){
			String arrayTipoLogradouro[] = this.tipoLogradouroAbreviacoes.split(",");
			for (int i=0; i < arrayTipoLogradouro.length; i++) {
				String arrayTipoAbreviado[] = arrayTipoLogradouro[i].split("=");
				if (str.equalsIgnoreCase(arrayTipoAbreviado[0])) {
					str = arrayTipoAbreviado[1];
					break;
				}
			}
		}
		return str;
	}
	
	/**
	 * Tenta abreviar o logradouro apartir da quantidade de caracteres informada até conseguir se utilizando do método
	 *  abreviarLogradouro(). Invoca o método abreviarLogradouro() repassando os parâmetros recebidos, caso receba a Exception 
	 *  lançada pelo método abreviarLogradouro() incrementa o tamanho em um e vai repetindo esse procedimento até
	 *  conseguir receber uma abreviatura para poder retornar.
	 *
	 * @param logradouro - String contendo o logradouro a ser abreviado.
	 * @param size - int contendo a quantidade de caracteres inicial a ser tentada para abreviatura. 
	 * @return String contendo a menor abreviatura possível para o logradouro com tamanho >= ao parâmetro size.
	 * @see abreviarLogradouro()
	 */
	public String tentarAteAbreviarLogradouro(String logradouro, int size) {
		String retorno = null;
		try {
			if(logradouro != null && size>0)
				while(true){
					try{					
						retorno = abreviarLogradouro(logradouro, size);
						break;
					} catch(Exception e){
						size++;
					}
				}
		} catch (Exception e) {	}
		return retorno;
	}
	
	/**
	 * Tenta abreviar o nome apartir da quantidade de caracteres informada até conseguir se utilizando do método
	 *  abreviarNome(). Invoca o método abreviarNome() repassando os parâmetros recebidos, caso receba a Exception 
	 *  lançada pelo método abreviarNome() incrementa o tamanho em um e vai repetindo esse procedimento até
	 *  conseguir receber uma abreviatura para poder retornar.
	 *  
	 * @param nome - String contendo nome a ser abreviado.
	 * @param size - int contendo a quantidade de caracteres inicial a ser tentada para abreviatura. 
	 * @return String contendo a menor abreviatura possível para o nome com tamanho >= ao parâmetro size.
	 * @see abreviarNome()
	 * */
	public String tentarAteAbreviarNome(String nome, int size) {
		String retorno = null;
		try {		
			if(nome != null && size>0)
				while(true){
					try{					
						retorno = abreviarNome(nome, size);
						break;
					} catch(Exception e){
						size++;
					}
				}
		} catch (Exception e) {	}
		return retorno;
	}	
}