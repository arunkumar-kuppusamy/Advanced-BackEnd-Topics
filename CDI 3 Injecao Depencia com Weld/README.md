
-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------
	ESTUDOS DA CASA DO CODIGO
	CURSO CDI INJECAO DE DEPENDENCIAS
-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

@ManagedBean
 - Permite que a EL utilize o nome da classe como padrao de acesso direto aos atributos
 ex. abc.var - se refere diretamente a classe Abc com o atributo public getVar()
é um bean que pode ser gerenciado de forma serializada

@Named - removemos o @ManagedBean e colocamos no seu lugar o @Named
	Com isso ele tem todas as funcionalidades de um managed, porém agora é gerenciado pelo CDI e não pelo JSF
		porem o JSF recebe o seu controle no momento certo, só nao esta delegado a criar dependencias

@Dependent ou @RequestScope - Importe definir qual o scope que o @Named terá , em @ManagedBean já e Request, no CDI não há um default, há o Dependent.


-- pom.xml
<dependency>
	 <groupId>org.jboss.weld.servlet</groupId>
	 <artifactId>weld-servlet</artifactId>
	 <version>2.3.5.Final</version>
</dependency>

-- src/main/webapp/META-INF/context.xml
<!DOCTYPE Configure PUBLIC "-//Jetty//Configure//EN" "http://www.eclipse.org/jetty/configure.dtd">
<Context>
<Resource name="BeanManager"
   auth="Container"
   type="javax.enterprise.inject.spi.BeanManager"
   factory="org.jboss.weld.resources.ManagerObjectFactory"/>
</Context>

-- /WEB-INF/web.xml
<listener>
	<listener-class>org.jboss.weld.environment.servlet.Listener</listener-class>
</listener>

<resource-env-ref>
	<resource-env-ref-name>BeanManager</resource-env-ref-name>
	<resource-env-ref-type>
		javax.enterprise.inject.spi.BeanManager
	</resource-env-ref-type>
</resource-env-ref>

-- /WEB-INF/beans.xml
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
     version="1.2" bean-discovery-mode="all">

</beans>

-- Modifique as chamadas @ManagedBean para javax.enterprise.context.@Named


-- PART 1
	-- Na aula ele pegou este exemplo para injetar uma dependencias
		boolean existe = new UsuarioDao().existe(this.usuario);
	-- substituiu
		@Inject
		private UsuarioDao usuarioDao;
		boolean existe = usuarioDao.existe(this.usuario);

	-- Rodando o codigo ele funciona igual, acredito que foi somente a anotacao extra @Named e @RequestScope já necessaria para o @Inject trabalhar, ainda nao sei

	-- segunda opcao via CONSTRUTOR
		@Inject
		public LoginBean(UsuarioDao usuarioDao) {
			this.usuarioDao = usuarioDao;
		}
	-- terceira opcao via METODO
		private UsuarioDao usuarioDao;
		@Inject
		public void inicializador(UsuarioDao usuarioDao) {
			this.usuarioDao = usuarioDao;
		}

-- PART 2
	-- Agora vamos retirar a dependencia de Dao<T>.

	-- Utilizamos isso
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);

	-- Substituimos por isso
		private DAO<Autor> autorDao;
		@Inject
		public AutorBean(DAO<Autor> autorDao) {
			this.autorDao = autorDao;

		}

	-- vai dar erro porque DAO nao tem um construtor sem parametros, logo vamos ter que produzir um bean ao nosso estilo

	-- Como estamos trabalhando com Generics, precisamos pegaros tipos e o nome da classe ou mais coisa
		@Produces
		public <T> DAO<T> factory(InjectionPoint point) {

			//InjectionPoint - indica onde estamos, qual classe, qual parametro injetado, qual escopo.
			ParameterizedType type = (ParameterizedType) point.getType();

			//Faz um cast forcado, pegando o unico parametro, que é o primeiro <T>, que aqui será o Autor
			Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];

			return new DAO<T>(classe);
		}

	-- @Produces - Carregado no load da aplicacao e torna disponivel um construtor padronizado para um bean especifico.
		Produces indica que se alguem precisar de um objeto serializado do tipo Dao, pode pegar este daqui
			que já entrega um dao instanciado por uma classe bem especifica
		Logo se precisamos de um bean de vem de uma classe que não tem construtor/inicializador padrao, vamos precisar produzir uma instanciado
			especifica para este bean, Produces é carregado no load da aplicacao.
		Ocorre muito erro quando é anotado @Inject, mas o CDI não encontra um construtor/inicializador que atenda os requisitos
			colocando o bean nullable, com valor nulo, porque precisa de um construtor/inicializador padronizado.

-- PART 3

	--Se em alguma parte do meu codigo eu tiver, ele vai chamar o produces abaixo
		@Inject
		private EntityManager abcdefghijklmnopqrstuvwxyz;

	-- Eliminou a dependencia de EntityManager
	public class JPAUtil {

		private static EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("livraria");

		//Repare que aqui temos um método que produz o instancia, e não um construtor.
		@Produces
		@RequestScoped //permite identificar qual injecao esta tratando
		public EntityManager getEntityManager() {
			return emf.createEntityManager();
		}

		//@Disposes - quando o CDI nao precisar mais, feche/elimine, usando este metodo
		public void close(@Disposes EntityManager em) {
			em.close();
		}
	}














