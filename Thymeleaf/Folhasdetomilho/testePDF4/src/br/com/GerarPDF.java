package br.com;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GerarPDF {

	private static File resourcesDirectory = new File("src");
	private static final String template = resourcesDirectory.getAbsolutePath();
	private static final SalvarComoArquivoPDF salvar = new SalvarComoArquivoPDF();

	public static void main(String[] args) throws IOException {

		try {
			//Carregar o template ThymeLeaf
			final String caminhoTomilhoSemExtensaoSemRaiz = "\\templates\\tomilho\\hello";
			String htmlConverterThymeLeaf = salvar.carregarTemplateEngine(caminhoTomilhoSemExtensaoSemRaiz);
			
			//Carregar template FIXO
			final String caminhoArquivoHTML = template + "\\templates\\APP\\APP - Comprovante de Contratação.html";
			String htmlConverterFIXO = salvar.carregarTemplateHTML(caminhoArquivoHTML);
			
			//Gerar O PDF a parte do fixo ou do thymeleaf
			final FileOutputStream fileOutput = new FileOutputStream(new File(template + "\\output\\comprovanteResultado.pdf"));
			ByteArrayOutputStream outputStream = salvar.processarArquivoVisual(htmlConverterFIXO);
			
			//Gerar O ZIP
			ByteArrayOutputStream zipOutStream = salvar.gerarZIP(outputStream);
			zipOutStream.writeTo(fileOutput);
	
			//Fecha os streams
			outputStream.flush();
			outputStream.close();
			zipOutStream.flush();
			zipOutStream.close();
			System.out.println("ok, saindo de gerar pdf.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
