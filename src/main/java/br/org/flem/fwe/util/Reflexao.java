package br.org.flem.fwe.util;

import br.org.flem.fwe.util.StringUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Visa facilitar o uso de Reflection através de métodos que
 * generalizam o seu uso para beans e POJO's em geral.
 * 
 * @author Grupo Framework - Componentes
 * @version 1.1, 29/04/2005
 * @since 1.0
 */
public class Reflexao {
	
	public static final String REGEXP_METODOS_GET = "(get){1}[A-Z]+.*";
	public static final String REGEXP_METODOS_SET = "(set){1}[A-Z]+.*";
	public static final String REGEXP_TODOS	= ".*";
	
	/**
	 * Lista os métodos get de um objeto passado como parâmetro.
	 * @param Object - objeto a ser verificado
	 * @return List - lista de métodos da classe Method 
	 */
	public static List listaMetodosGet(Object o) {
		return listaMetodos(o, REGEXP_METODOS_GET);
    }
        
	/**
	 * Lista os métodos set de um objeto passado como parâmetro
	 * @param Object - objeto a ser verificado
	 * @return List - lista de métodos da classe Method 
	 */
	public static List listaMetodosSet(Object o) {
		return listaMetodos(o, REGEXP_METODOS_SET);
	}

	/**
	 * Lista os todos os métodos declarados de um objeto passado como parâmetro
	 * @param Object - objeto a ser verificado
	 * @return List - lista de métodos da classe Method 
	 */
	public static List listaMetodos(Object o) {
		return listaMetodos(o, REGEXP_TODOS);
	}
	
	/**
	 * Devolve uma lista de métodos declarados pelo programador em um objeto
	 * @param o - objeto a ser verificado
	 * @param pattern - uma expressao regular dos métodos que desejam ser listados
	 * @return List - lista de métodos da classe Method
	 * <br>
	 * Exemplo de utilização<br>
	 * List l = new ArrayList();<br>
	 * l = Util.listaMetodos(new Object(), ".*");  // todos os metodos da classe Object<br>
	 * for (int i = 0; i < l.size(); i++)<br>
	 * 		System.out.println(((Method)l.get(i)).getName());<br>
	 * 
	 */
	public static List<Method> listaMetodos(Object o, String pattern) {

		List<Method> l = new ArrayList<Method>();
    		
		Method[] m = o.getClass().getDeclaredMethods();
   		    
		for (int i = 0; i < m.length; i++) {
			if (m[i].getName().matches(pattern))
				l.add(m[i]);
		}
			
		return l;
	}

	/**
	 * Lista os todos os atributos declarados de um objeto passado como parâmetro.
	 * @param Object - objeto a ser verificado
	 * @return List - lista de atributos da classe Method 
	 */
	
	public static List listaAtributos(Object o) {
		return listaAtributos(o, REGEXP_TODOS);
	}
	
	/**
	 * Devolve uma lista de atributos declarados pelo programador em um objeto.
	 * @param o - objeto a ser verificado
	 * @param pattern - uma expressao regular dos atributos que desejam ser listados
	 * @return List - lista de atributos da classe Field
	 * <br>
	 * Exemplo de utilização<br>
	 * List l = new ArrayList();<br>
	 * l = Util.listaAtributos(new Object(), ".*");  // todos os atributos da classe Object<br>
	 * for (int i = 0; i < l.size(); i++)<br>
	 * 		System.out.println(((Field)l.get(i)).getName());<br>
	 * 
	 */
	public static List<Field> listaAtributos(Object o, String pattern) {
		List<Field> l = new ArrayList<Field>();
    		
		Field[] f = o.getClass().getDeclaredFields();
 		    
		for (int i = 0; i < f.length; i++) {
			if (f[i].getName().matches(pattern))
				l.add(f[i]);
		}
    	
		return l;
	}
	
	/**
	 * Invoca um metodo getXxxxx() para o objeto e o atributo informado.
	 * @param o - o objeto cujo metodo get será chamado
	 * @param atributo - o nome do atributo que identifica qual getter chamar
	 * @param objParams - os parametros que devem ser passados para a invocação do método - geralmente null
	 * @return Object retorno do método getXxxxx()
	 * @throws Exception caso ocorra algum erro durante a invocação
	 */
	public static Object invocaGet(Object o, String atributo, Object[] objParams) throws Exception {
	    String nomeGetter = "get" + StringUtil.primeiraLetraToUpperCase(atributo);
        try {
        	if (objParams == null)
        		return o.getClass().getMethod(nomeGetter, (Class[])null).invoke(o, (Object[])null);
        	
        	Class[] tipos = new Class[objParams.length];
        	for (int i=0; i< objParams.length; i++) {
        		tipos[i] = objParams[i].getClass();
        	}
        	
            return o.getClass().getMethod(nomeGetter, tipos).invoke(o, objParams);
        } catch (Exception e) {
        	throw e;
        }
	}

	/**
	 * Invoca um metodo getXxxxx() para o objeto sem passar parâmetros.
	 * @param o - o objeto cujo metodo get será chamado
	 * @param atributo - o nome do atributo que identifica qual getter chamar
	 * @return Object retorno do método getXxxxx()
	 * @throws Exception caso ocorra algum erro durante a invocação
	 */
	public static Object invocaGet(Object o, String atributo) throws Exception {
	    return invocaGet(o, atributo, null);
	}

	/**
	 * Invoca o metodo setXxxx para o objeto e atributo informados.
	 * @param o - o objeto a invocar o metodo set
	 * @param atributo - o atributo cujo metodo set será invocado
	 * @param objParams - um array de objetos com os parametros do setter
	 * @return Object retorno do método setXxxxx()
	 * @throws Exception
	 */
	public static Object invocaSet(Object o, String atributo, Object[] objParams) throws Exception {
	    String nomeSetter = "set" + StringUtil.primeiraLetraToUpperCase(atributo);
        try {
        	if (objParams == null)
        		return o.getClass().getMethod(nomeSetter, (Class[])null).invoke(o, (Object[])null);
        		
        	Class[] tipos = new Class[objParams.length];
        	for (int i=0; i< objParams.length; i++) {
        		tipos[i] = objParams[i].getClass();
        	}
            return o.getClass().getMethod(nomeSetter, tipos).invoke(o, objParams);
        } catch (Exception e) {
        	throw e;
        }
	}
	
	/**
	 * Recupera o valor de uma propriedade de determinado objeto utilizando Reflection.
	 * 
	 * <b> Exemplo: </b><br/>
	 * Object obj = Reflexao.invocaGetA(objetoContainer, "nomepropriedade.nomenested.maisumnome");
	 * Irá retornar:
	 * objetoContainer.getNomepropriedade().getNomenested().getMaisumnome();
	 * 
	 * @param obj Objeto contendo os valores desejados
	 * @param nomePropriedade Nome da propriedade cujo valor deve ser recuperado
	 * @return Objeto com o valor da propriedade
	 * @throws Exception
	 */
	public static Object invocaGetAninhado(Object obj, String nomePropriedade) throws Exception {
		Object result = null;
		Class classe;
		
		// se objeto for nulo, valor é nulo
		if (obj != null)
			classe = obj.getClass();
		else
			return null;
		
		String metodo = new String();
		String propriedade = new String(nomePropriedade);
		
		// verificando necessidade de chamadas recursivas
		boolean rec = false;                        
		if (propriedade.indexOf('.') > 0) { // se encontrou ponto vai precisar de recursão
			rec = true;
			metodo = propriedade.substring(0, propriedade.indexOf('.')); // parte 1: nome da propriedade que guarda o objeto
			propriedade = propriedade.substring(propriedade.indexOf('.') + 1); // parte 2: o que vem depois do ponto
		} else 
			metodo = nomePropriedade;            
		
		// procedimento normal
		metodo = "get" + metodo.substring(0,1).toUpperCase() + metodo.substring(1); // getter
		Method met = classe.getMethod(metodo, (Class[])null);
		result = met.invoke(obj, (Object[])null);
		
		return rec ? invocaGetAninhado(result, propriedade) : result;
	}
	
	/**
	 * Recupera a classe utilizando Reflection.
	 * 
	 * <b> Exemplo: </b><br/>
	 * Class classe = Reflexao.classForName("gov.pr.celepar.framework.util.Reflexao");
	 * Irá retornar um Class de onde pode-se por exemplo invocar o método newInstance() para obter um instância do objeto;
	 * 
	 * @param className String contendo o nome completo da Classe (pacote+Nome)
	 * @return Class do parâmetro passado
	 * @throws ClassNotFoundException
	 */
	public static Class classForName(String className) throws ClassNotFoundException {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            try {
                Thread thread = Thread.currentThread();
                ClassLoader threadClassLoader = thread.getContextClassLoader();
                return Class.forName(className, false, threadClassLoader);
            } catch (ClassNotFoundException ex) {
                throw ex;
            }
        }
    }
}