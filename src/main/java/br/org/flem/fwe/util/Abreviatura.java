package br.org.flem.fwe.util;

import java.util.ArrayList;
import java.util.List;

/**
 * REVER
 * Utilit�ria para realizar abrevia��es de nomes de pessoas e logradouros.
 *  ***** LOGRADOUROS: *****
	Aeroporto, Alameda, �rea, Avenida, Campo, Ch�cara, Col�nia, Condom�nio, Conjunto, Distrito,
	Esplanada, Esta��o, Estrada, Favela, Fazenda, Feira, Jardim, Ladeira, Lago, Lagoa, Largo,
	Loteamento, Morro, N�cleo, Parque, Passarela, P�tio, Pra�a, Quadra, Recanto, Residencial, Rodovia, 
	Rua, Setor, S�tio, Travessa, Trecho, Trevo, Vale, Vereda, Via, Viaduto, Viela, Vila
 *	
 * @author Grupo Framework - Componentes
 * @version 1.0, 04/01/2005
 * @since 1.0
 * 
 */
public class Abreviatura {
	
	/**
	 * Flag utilizada para indicar a retirada ou n�o das preposi��es nas abrevia��es. 
	 */
	private boolean retiraPreposicoes = true;
	
	/**
	 * String contendo as preposi��es que poder�o ser excluidas nas abrevia��es.
	 */
	private String preposicoes = "a,e,o,da,das,de,des,di,do,dos,na,nas,ne,no,nos";
	
	/**
	 * String contendo os tipos de logradouros e suas respectivas abrevia��es 
	 *  separados pelo sinal de "=". Ex: Rua ser� substituido por R
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
	 * String contendo os tipos de Sobrenomes que podem ser abreviados e suas respectivas abrevia��es 
	 *  separados pelo sinal de "=". Ex: Rua ser� substituido por R
	 */
	private String tipoSobrenomeAbreviacoes = "Junior=Jr,Neto=Nt,Filho=Fo";

	/**
	 * String contendo as titula��es de logradouros e suas respectivas abrevia��es 
	 *  separados pelo sinal de "=". Ex: Padre ser� substituido por Pe
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
	 * Seta quais as titula��es de logradouros que ser�o abreviados e suas 
	 *  respectivas abrevia��es. Exemplo de sintaxe esperado pelo 
	 *  m�todo: "Padre=Pe,Visconde=Visc"
	 * Obs: Qualquer informa��o passada que fuja deste padr�o de sintaxe
	 *  far� com que a abrevia��o n�o funcione corretamente.
	 * 
	 * @param nomeLogradouroAbreviacoes - String contendo as titula��es de
	 *  logradouros e suas respectivas abrevia��es
	 */
	public void setTitulacaoLogradouroAbreviacoes(String titulacaoLogradouroAbreviacoes) {
		this.titulacaoLogradouroAbreviacoes = titulacaoLogradouroAbreviacoes;
	}
	
	/**
	 * Seta quais os tipos de logradouros que ser�o abreviados e suas 
	 *  respectivas abrevia��es. Exemplo de sintaxe esperado pelo 
	 *  m�todo: "Rua=R,Avenida=Av,Pra�a=P"
	 * Obs: Qualquer informa��o passada que fuja deste padr�o de sintaxe
	 *  far� com que a abrevia��o n�o funcione corretamente.
	 * 
	 * @param tipoLogradouroAbreviacoes - String contendo os tipos de
	 *  logradouros e suas respectivas abrevia��es
	 */
	public void setTipoLogradouroAbreviacoes(String tipoLogradouroAbreviacoes) {
		this.tipoLogradouroAbreviacoes = tipoLogradouroAbreviacoes;
	}

	/**
	 * Seta quais os tipos de Sobrenomes que poder�o ser abreviados e suas 
	 *  respectivas abrevia��es. Exemplo de sintaxe esperado pelo 
	 *  m�todo: "Junior=Jr,Neto=Nt,Sobrinho=So,Filho=Fo"
	 * Obs: Qualquer informa��o passada que fuja deste padr�o de sintaxe
	 *  far� com que a abrevia��o n�o funcione corretamente.
	 * 
	 * @param tipoSobrenomeAbreviacoes - String contendo os tipos de
	 *  sobrenome e suas respectivas abrevia��es
	 */
	public void setTipoSobrenomeAbreviacoes(String tipoSobrenomeAbreviacoes) {
		this.tipoSobrenomeAbreviacoes = tipoSobrenomeAbreviacoes;
	}
	
	
	/**
	 * Seta as preposi��es que poder�o ser exclu�das na abrevia��o.
	 *  Exemplo de sintaxe esperado pelo m�todo: "a,e,das,de,nos";
	 * Obs: Qualquer informa��o passada que fuja deste padr�o de sintaxe
	 *  far� com que a abrevia��o n�o funcione corretamente.
	 * 
	 * @param preposicoes - String contendo as preposi��es.
	 */
	public void setPreposicoes(String preposicoes) {
		this.preposicoes = preposicoes;
	}
	
	/**
	 * Possibilita ou n�o a exclus�o das preposi��es na abrevia��o.
	 * 
	 * @param retiraPreposicoes - boolean
	 *  true = retirar as preposi��es (default)
	 *  false = n�o retirar as preposi��es
	 */
	public void setRetiraPreposicoes(boolean retiraPreposicoes) {
		this.retiraPreposicoes = retiraPreposicoes;
	}
	
	/**
	 * Abrevia nome at� a quantidade de caracteres informada, seguindo
	 *  as regras abaixo:
	 *  - O primeiro e o �ltimo nome nunca s�o abreviados;
	 *  - Caso o nome original n�o ultrapasse a quantidade m�xima 
	 *    de caracteres o nome original � retornado se nenhuma altera��o;
	 *  - Se mesmo depois de feita a abrevia��o o nome continuar a exceder 
	 *    quantidade m�xima de caracteres s�o feitas remo��es dos sobrenomes
	 *    internos, sem alterar o �ltimo nome.
	 *  
	 * @param nome - String contendo nome a ser abreviado
	 * @param qtdMaxCaracteres - int contendo a quantidade m�xima 
	 *  de caracteres para o nome informado 
	 * @return String contendo o nome abreviado
	 * @throws Exception - quando mesmo abreviado, o nome continuar a 
	 *  exceder a quantidade m�xima de caracteres  
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
								throw new Exception("N�o foi poss�vel abreviar o nome: " + nome + ". Pois mesmo abreviado ultrapassa a quantidade de " + qtdMaxCaracteres + " caracteres.");
							}
						}
						
					}
				}			
				
			} else {
				throw new Exception("N�o foi poss�vel abreviar o nome: " + nome + ". Pois mesmo abreviado ultrapassa a quantidade de " + qtdMaxCaracteres + " caracteres.");
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
	 * Abrevia logradouro at� a quantidade de caracteres informada seguindo
	 *  as regras abaixo:
	 *  - Caso o logradouro original n�o ultrapasse a quantidade m�xima 
	 *    de caracteres o nome original � retornado se nenhuma altera��o;
	 *  - O Tipo identificador de logradouro sempre � abreviado 
	 *  - O primeiro e o �ltimo nome do logradouro nunca s�o abreviados, 
	 *    exce��o feita de acordo com o par�metro nomeLogradouroAbreviacoes;
	 *  - Se mesmo depois de feita a abrevia��o o logradouro continuar a 
	 *    exceder quantidade m�xima de caracteres s�o feitas remo��es dos 
	 *    nomes internos ao logradouro, sem alterar o �ltimo nome.
	 *  
	 * @param nome - String contendo logradouro a ser abreviado
	 * @param qtdMaxCaracteres - int contendo a quantidade m�xima 
	 *  de caracteres para o logradouro informado 
	 * @return String contendo o logradouro abreviado
	 * @throws Exception - quando mesmo abreviado, o logradouro continuar a 
	 *  exceder a quantidade m�xima de caracteres  
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
		// Abrevia o identificador do logradouro (Rua, Avenida, Pra�a)
		
		//Verifica tipo Logradouro, titula��o, nomes entre () e vai abreviando quando encontra
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
		//procura por preposi��es e as retira caso indicado para isso
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
		//caso o �ltimo nome seja muito curto tenta manter o anterior
		if(arrayLogradouro[arrayLogradouro.length-1].length() < 4)
			quantidadeParenteses ++;
		
		int quantidadePalavras = (quantidadeTipo +  2 ) + quantidadeParenteses;
		int indiceInicialPalavra = quantidadeTipo + quantidadeTitulacao;
				
		if(logradouro.length() > qtdMaxCaracteres){
			arrayLogradouro = quebraPalavras(logradouro);
			
			
			if (arrayLogradouro.length > quantidadePalavras) {
				for (int i = arrayLogradouro.length-(2 + quantidadeParenteses); i > indiceInicialPalavra; i--) {
				//abrevia palavra por palavra at� conseguir alca�ar o tamanho desejado
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
				throw new Exception("N�o foi poss�vel abreviar o nome: " + logradouro + ". Pois mesmo abreviado ultrapassa a quantidade de " + logradouro + " caracteres.");
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
	 * Abrevia a String passada como parametro verificando ela n�o � 
	 *  uma preposi��o. Se for preposi��o retorna uma string vazia, sen�o
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
	 * Verifica se a string passada como par�metro � uma titula��o de
	 *  logradouro. Se for a titula��o do logradouro � abreviado da forma informada no 
	 *  atributo titulacaoLogradouroAbreviacoes. 
	 * 
	 * @param str - String a ser verificada e abreviada
	 * @return - String abreviada ou a pr�pria string informada caso 
	 *  esta n�o seja uma titula��o identificada de logradouro. 	 * 
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
	 * Verifica se a string passada como par�metro � um sobrenome abrevi�vel. 
	 * Se for, o sobrenome � abreviado de acordo com o atributo tipoSobrenomeAbreviacoes. 
	 * 
	 * @param str - String a ser verificada e abreviada
	 * @return - String abreviada ou a pr�pria string informada caso 
	 *  esta n�o seja um sobrenome abrevi�vel * 
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
	 * Verifica se a String passada como par�metro � uma preposi��o.
	 *  
	 * @param str - String a ser analisada
	 * @return boolean
	 *  true = a string passada como par�metro � uma preposi��o 
	 *  false - a string passada como par�metro N�O � uma preposi��o
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
	 * Verifica se a string passada como par�metro � um tipo identificador de
	 *  logradouro. Se for o logradouro � abreviado da forma informada no 
	 *  atributo tipoLogradouroAbreviacoes. 
	 * 
	 * @param str - String a ser verificada e abreviada
	 * @return - String abreviada ou a pr�pria string informada caso 
	 *  esta n�o seja um tipo identificador de logradouro. 
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
	 * Tenta abreviar o logradouro apartir da quantidade de caracteres informada at� conseguir se utilizando do m�todo
	 *  abreviarLogradouro(). Invoca o m�todo abreviarLogradouro() repassando os par�metros recebidos, caso receba a Exception 
	 *  lan�ada pelo m�todo abreviarLogradouro() incrementa o tamanho em um e vai repetindo esse procedimento at�
	 *  conseguir receber uma abreviatura para poder retornar.
	 *
	 * @param logradouro - String contendo o logradouro a ser abreviado.
	 * @param size - int contendo a quantidade de caracteres inicial a ser tentada para abreviatura. 
	 * @return String contendo a menor abreviatura poss�vel para o logradouro com tamanho >= ao par�metro size.
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
	 * Tenta abreviar o nome apartir da quantidade de caracteres informada at� conseguir se utilizando do m�todo
	 *  abreviarNome(). Invoca o m�todo abreviarNome() repassando os par�metros recebidos, caso receba a Exception 
	 *  lan�ada pelo m�todo abreviarNome() incrementa o tamanho em um e vai repetindo esse procedimento at�
	 *  conseguir receber uma abreviatura para poder retornar.
	 *  
	 * @param nome - String contendo nome a ser abreviado.
	 * @param size - int contendo a quantidade de caracteres inicial a ser tentada para abreviatura. 
	 * @return String contendo a menor abreviatura poss�vel para o nome com tamanho >= ao par�metro size.
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