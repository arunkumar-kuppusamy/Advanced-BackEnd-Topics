 /**
  * Nome: br.com.bradesco.web.ibpj.service.business.sei.salvarcomoarquivo<br/>
  * Prop�sito: <p></p>
  * Data da Cria��o: 13/05/2013
  * Registro de Manuten��o: 
  * <ol>
  * <li>Data: 13/05/2013
  *      <ul>
  *          <li>Autor: peng
  *          <li>Respons�vel: Scopus
  *      </ul>
  * </li>
  * </ol>
  * Par�metro de compila��o -d
  */

package br.com.estaticos;

/**
 * Nome: IUserAgentCallbackCache.java<br/>
 * Prop�sito: <p></p>
 * Registro de Manuten��o: 
 * <ol>
 * <li>Data: 13/05/2013
 *      <ul>
 *          <li>Autor: peng
 *          <li>Respons�vel: Scopus
 *          <li>Descri��o: vers�o inicial
 *      </ul>
 * </li>
 * </ol>
 * Par�metro de compila��o -d
 * @author peng<br/>
 * Equipe: Scopus <BR>
 * @version: 1.0
 *
 * @see 
 */

public interface IUserAgentCallbackCache {
    /**
     * Nome: getCSSResourceBuff<br/>
     * Prop�sito: <p>Obten��o de buffer do css</p>
     * Registro de Manuten��o: 
     * <ol>
     * <li>Data: 13/05/2013
     *      <ul>
     *          <li>Autor: peng
     *          <li>Respons�vel: Scopus
     *          <li>Descri��o: vers�o inicial
     *      </ul>
     * </li>
     * </ol>
     * @param uri URL do recurso.
     * @return Buffer do CSS.
     */
    byte[] getCSSResourceBuff(String uri);    
}
