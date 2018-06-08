package br.com.estaticos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xhtmlrenderer.resource.CSSResource;
import org.xhtmlrenderer.swing.NaiveUserAgent;


/**
 * Nome: NaiveUserAgentCache.java<br/>
 * Prop�sito: <p>UserAgentCallback com cache de css.</p>
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

public class NaiveUserAgentCache extends NaiveUserAgent implements IUserAgentCallbackCache {

    /**
     * @see br.com.estaticos.bradesco.web.ibpj.service.business.sei.salvarcomoarquivo.IUserAgentCallbackCache
     * #getCSSResourceBuff(java.lang.String)
     */
    public byte[] getCSSResourceBuff(String uri) {
        byte[] retorno = null;
        /*CSSResource css = super.getCSSResource(uri);
        retorno = obterBuff(css.getResourceInputSource().getByteStream());*/

        String css = "body {background-color:#FAEBD7;} h1{color: red;} h2{color: purple;}";
        retorno = css.getBytes();
        return retorno;
    }

    /**
     * Nome: obterBuff<br/>
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
     * @param ins Stream de entrada.
     * @return Buffer obtido.
     * @throws IOException 
     */
    protected byte[] obterBuff(InputStream ins){
        try {
            byte[] buffer = new byte[32 * 1024]; // Adjust if you want
            int bytesRead;        
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((bytesRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }        
            return baos.toByteArray();
        } catch (IOException ioe) {	
        	ioe.printStackTrace();        	
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
            } catch (Exception except) {
            }
        }
		return null;
    }
}
