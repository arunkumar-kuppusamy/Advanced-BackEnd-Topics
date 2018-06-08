package br.com.estaticos;

import java.io.ByteArrayInputStream;

import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.resource.CSSResource;


/**
 * Nome: ITextUserAgentCache.java<br/>
 * Prop�sito: <p>UserAgentCallback customizado com cache de css</p>
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

public class ITextUserAgentCache extends ITextUserAgent {

    /**
     * UserAgentCallback comum para efeito de cache.
     */
    IUserAgentCallbackCache userAgentCallbackCache;
    /**
     * Construtor com os valores exigidos.
     * @param outputDevice Dispositivo de sa�da.
     * @param sharedContext Contexto compartilhado.
     * @param userAgentCallbackCache UserAgentCallback com cache.
     */
    public ITextUserAgentCache(ITextOutputDevice outputDevice, SharedContext sharedContext, 
            IUserAgentCallbackCache userAgentCallbackCache) {
        super(outputDevice);
        this.setSharedContext(sharedContext);
        this.userAgentCallbackCache = userAgentCallbackCache;
    }
    /**
     * @see org.xhtmlrenderer.swing.NaiveUserAgent#getCSSResource(java.lang.String)
     */
    @Override
    public CSSResource getCSSResource(String uri) {
        byte[] buff;
        buff = userAgentCallbackCache.getCSSResourceBuff(uri);
        return new CSSResource(new ByteArrayInputStream(buff));
    }
}
