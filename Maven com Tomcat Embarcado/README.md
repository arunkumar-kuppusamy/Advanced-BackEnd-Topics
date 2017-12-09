# MAVEN + TOMCAT EMBARCADO


Comentários: [README.MD - https://guides.github.com/features/mastering-markdown/](https://guides.github.com/features/mastering-markdown/)

Utilize este tutorial como howtodo

***http://o7planning.org/en/10133/run-maven-java-web-application-in-tomcat-maven-plugin***

Saiba que:
* A versão do Java e do Tomcat, precisa ter suporte aos plugins e dependecias do Maven
ou seja eu tentei usar Java 9 e Tomcat 9, porém só existe plugin para Java 7 e Tomcat 7 até o momento.

* Não precisa criar um Server no Eclipse ele vai usar o próprio que existe duplicado para cada projeto
coloque a dependencia como <scope>provided</> para subir o projeto mais rápido

* Toda vez que faz um Update Project se ele altera a versão do JRE/JDK do seu projeto?
é porque seu pom esta configurado de forma errada por duas razões:

	Vc colocou uma versão muito mais recente que a suportada pelos plugins e dependencias, dai ele pega 
	a versao menor possível, eu coloquei versao java 9 e ele setava java 5

	Vc colocou no Facets versão incompativel com o java configurado no Pom, 
	se for java 7 o facets deve ser 2.5

Esta configuração atende Java 7 e Tomcat 7
```xml
  <source>1.8</source>
  <target>1.8</target>
  <compilerVersion>1.8</compilerVersion>
```

* Deu um erro para min dizendo que ele não conseguia unzipar o jar para ler as classes

	Error: error reading *.jar invalid LOC header (bad signature)
	Correção foi APAGAR A PASTA .m2/repositories/ e deixar o Maven baixar tudo de novo
	provavel que tenha jar duplicados e pela quantidade o Maven se perdeu no Ruindows.
	
* Para funcionar o TOMCAT EMBARCADO COM MAVEN
> **A Dependência**
```html
<dependency>
	<groupId>org.apache.tomcat.maven</groupId>
	<artifactId>tomcat7-maven-plugin</artifactId>
	<version>2.2</version>
	<scope>provided</scope>
</dependency>
```
> **O plugin**
```xml
<build>
	<plugins>
		<plugin>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.7.0</version>
			<configuration>
				<source>1.8</source>
				<target>1.8</target>
				<compilerVersion>1.8</compilerVersion>
			</configuration>
		</plugin>
		<plugin>
			<groupId>org.apache.tomcat.maven</groupId>
			<artifactId>tomcat7-maven-plugin</artifactId>
			<version>2.2</version>
		</plugin>
	</plugins>
</build>
```


No tutorial acima ele da algumas dicas importantes como

> Run Configurations - Criar um Build Maven customizado, para o ***tomcat7:run -X***, ao clicar em ***Browse Workspace*** ele busque o projeto e ele cria a url do campo **Base Directory**

![tomcat7:run -X](http://o7planning.org/en/10133/cache/images/i/8542.png)

> Console Eclipse - Veja a url e a porta que ele gerou, teste!

![Uri raiz do projeto](http://o7planning.org/en/10133/cache/images/i/8548.png)

> Atalho foi criado para rodar a aplicação, use!

![Atalho para Run](http://o7planning.org/en/10133/cache/images/i/8560.png)



----------------------------------------------

#### *MAIS ANOTACOES SOBRE MAVEN*

- Criar um dynamic

- Converter para maven project ( ***groupId é as pastas***, ***artifactId é o contexto***, geralmente o nome do projeto )
	
	_buildName_ da para mudar apenas o nome do war que ele gera, e é chato porque tem que reinicar o eclipse para ele regerar
	
- Ele criou um pom, edite-o como preferir, se o pom nao comecar a baixar as dependencias automaticamente a cada build

	de um Maven Install e veja os erros comigo, ocorreu porque escrevi depende**N***cies errado, e indicou no Install somente

- Para rodar o projeto nao esqueca de configurar com ***tomcat7:run -X*** e ***apontar para o projeto workspace***, o maven nao configura sozinho somente dando um Run As.

- Pode ocorrer erros porque jar duplicado _apague a pasta _.m2/repository_ e reinicie o IDE

	baixando todas as dependecias novamente, funcionou novamente para min fazer isso.

- Aplicacao somente estará disponivel no Run As, e nao no _Maven Install que apenas cria o pacote_

	para se certificar ele tem que ter criado a url procure por
	
		 _[INFO] Running war on http://localhost:8089/cxf/_

	para que ele log tudo certo tambem é preciso adicionar o junit no contexto
	
----------------------------------------------

