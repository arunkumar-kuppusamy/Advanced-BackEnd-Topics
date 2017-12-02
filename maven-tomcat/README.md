# MAVEN + TOMCAT EMBARCADO


Comentários: [README.MD](https://guides.github.com/features/mastering-markdown/)

Utilize este tutorial como howtodo

http://o7planning.org/en/10133/run-maven-java-web-application-in-tomcat-maven-plugin

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
> A Dependência
```html
<dependency>
	<groupId>org.apache.tomcat.maven</groupId>
	<artifactId>tomcat7-maven-plugin</artifactId>
	<version>2.2</version>
	<scope>provided</scope>
</dependency>
```
> O plugin
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





