package br.com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.dom.Document;//iText.jar
import org.w3c.tidy.Tidy;//jtidy.jar
import org.xhtmlrenderer.pdf.ITextRenderer;//core-renderer.jar

import com.lowagie.text.pdf.codec.Base64;//iText.jar

import br.com.estaticos.ITextUserAgentCache;
import br.com.estaticos.IUserAgentCallbackCache;
import br.com.estaticos.NaiveUserAgentCache;



/**
 * Classe para processar os arquivos no formato PDF
 * 
 * @author rcirino@scopus.com.br
 *
 */
public class SalvarComoArquivoPDF {

	protected static final String ISO_8859_1 = "ISO-8859-1";
	/**
	 * User Agent Callback - Para processamento de CSS
	 */
	private IUserAgentCallbackCache userAgentCallbackCache;
	
	/**
	 * Criar PDF apartir de html fornecido
	 * @param String htmlParaConversao
	 * @return ByteArrayOutputStream byteArrayOutputStream
	 */
	public ByteArrayOutputStream processarArquivoVisual(String htmlParaConversao) {
		ByteArrayOutputStream byteArrayOutputStream = null;

		this.userAgentCallbackCache = new NaiveUserAgentCache();
		this.userAgentCallbackCache.getCSSResourceBuff("style.css");

			byteArrayOutputStream = new ByteArrayOutputStream();
			try {
				// Seta atributos de tidy para criacao de documento a partir de HTML
				Tidy tidy = new Tidy();
				tidy.setShowErrors(0);
				tidy.setShowWarnings(true);
				tidy.setQuiet(true);
				tidy.setInputEncoding(ISO_8859_1);
				tidy.setOutputEncoding(ISO_8859_1);
				tidy.setPrintBodyOnly(true);
				tidy.setSmartIndent(true);
				tidy.setJoinStyles(true);

				// Cria documento a partir de HTML
				Document doc = tidy.parseDOM(new ByteArrayInputStream(htmlParaConversao.getBytes(ISO_8859_1)), null);

				ITextRenderer renderer = new ITextRenderer();
				
				renderer.getSharedContext().setUserAgentCallback(new ITextUserAgentCache(renderer.getOutputDevice(),
						renderer.getSharedContext(), userAgentCallbackCache));
				
				// Cria PDF
				renderer.setDocument(doc, null);
				renderer.layout();
				renderer.createPDF(byteArrayOutputStream);

			} catch (Exception e) {
				e.printStackTrace();
			}

		return byteArrayOutputStream;
	}

	/**
	 * Criar zip apartir de pdf fornecido
	 * @param ByteArrayOutputStream byteArrayOutputStream
	 * @return ByteArrayOutputStream byteArrayOutputStream
	 */
	public ByteArrayOutputStream gerarZIP(ByteArrayOutputStream byteArrayOutputStream) {

		// Constantes
		final File resourcesDirectory = new File("src");
		final String template = resourcesDirectory.getAbsolutePath();
		final int TAMANHO_BUFFER_BYTES = 4096; // 4kb
		try {
			// Caminho+Nome do arquivo.zip, caso seja em memoria nao precisa utilizar
			FileOutputStream arquivoZIP = new FileOutputStream(new File(template + "\\output\\comprovanteResultado.zip"));
			InputStream inputStreamEntrada = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamEntrada, TAMANHO_BUFFER_BYTES);

			BufferedOutputStream destinoArquivo = new BufferedOutputStream(arquivoZIP);
			ByteArrayOutputStream destinoBuffer = new ByteArrayOutputStream();

			// SYSOUT: Use {destinoBuffer} para gerar Internamente. Use {destinoArquivo} para visualiza-lo na pasta src.
			ZipOutputStream zipOutputStream = new ZipOutputStream(destinoBuffer);

			// Defina um nome de como o pdf vai ficar dentro do zip
			ZipEntry entry = new ZipEntry("comprovanteResultado.pdf");
			if (!entry.isDirectory()) {
				zipOutputStream.putNextEntry(entry);
			}
			int cont;
			byte[] dados = new byte[TAMANHO_BUFFER_BYTES];
			while ((cont = bufferedInputStream.read(dados, 0, TAMANHO_BUFFER_BYTES)) != -1) {
				zipOutputStream.write(dados, 0, cont);
			}
			bufferedInputStream.close();
			zipOutputStream.close();

			// LOGGGGGING
			if (destinoBuffer.size() > 0) {
				byte[] array = destinoBuffer.toByteArray();
				String pdfBase64 = Base64.encodeBytes(array);
				System.out
						.println("Tamanho: " + destinoBuffer.size() + "\n\n" + pdfBase64.replaceAll("\n", "") + "\n\n");
			} else {
				System.out.println("Foi escolhida a saida para a pasta fisica, arquivo gerado em: " + template);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

	/**
	 * Carrega na memoria o template html
	 * @param String caminhoArquivoHTML
	 * @return String bufferHTML
	 */
	public String carregarTemplateHTML(String caminhoArquivoHTML) {

		StringBuffer bufferHTML = new StringBuffer();
		try {
			File file = new File(caminhoArquivoHTML);
			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));

			String line = "";
			while ((line = bufferReader.readLine()) != null) {
				bufferHTML.append(line);
			}
			bufferReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufferHTML.toString();
	}

	/**
	 * Carrega na memoria o template em thymeleaf.
	 * @param String caminhoArquivoHTML
	 * @return String bufferHTML
	 */
	public String carregarTemplateEngine(String caminhoTomilho) {

		Context context = new Context();
		context.setVariable("name", "Valor setado na aplicação!");
		
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode("XHTML");
		resolver.setSuffix(".html");
		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);

		StringWriter writer = new StringWriter();
		engine.process(caminhoTomilho, context, writer);
		StringBuffer stringBuffer = writer.getBuffer();
		
		return stringBuffer.toString();
	}
	
}
