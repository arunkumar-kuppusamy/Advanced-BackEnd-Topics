web.xml = 
```xml	<resource-ref> 
		<description>My Connection</description> 
		<res-ref-name>jdbc/TestDB</res-ref-name> 
		<res-type>javax.sql.DataSource</res-type> 
		<res-auth>Container</res-auth> 
		<res-sharing-scope>Shareable</res-sharing-scope> 
	</resource-ref> 
```
Usando SPRING 3 sem web.xml, produz o mesmo efeito do <resource-ref>
```xml	<jee:jndi-lookup id="customerDataSource"
		jndi-name="jdbc/customer_db"
		expected-type="javax.sql.DataSource" />	
```
Usando beans do Spring Framework
```xml	<!-- JNDI DataSource for J2EE environments -->
  	<bean id="PACLDataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
		<property name="jndiName"><value>jdbc/db_prevManutencao</value></property>
		<property name="resourceRef"><value>true</value></property>
	</bean>
```
context.xml =  
```xml	<Resource auth="Container" name="jdbc/TestDB" driverClassName="oracle.jdbc.driver.OracleDriver" type="javax.sql.DataSource" maxActive="20" maxIdle="10" maxWait="-1" 
		url="<some db url>" username="aa" password="aa" /> 
```
JAVA acessando um JNDI resource
```java	import javax.naming.InitialContext;
	import javax.sql.DataSource;
	InitialContext ctx = new InitialContext(); 
	DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB"); 
	conn = ds.getConnection();
```
