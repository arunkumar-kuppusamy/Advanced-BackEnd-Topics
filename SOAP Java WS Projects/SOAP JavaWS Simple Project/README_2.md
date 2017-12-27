
@WebMethod(operationName="todosOsItens") cria um servidor de acesso com base nome indicado

<soapenv:Envelope ...>
   <soapenv:Header/>
   <soapenv:Body>
      <ws:todosOsItens/>
   </soapenv:Body>
</soapenv:Envelope>


PortType: Podemos ver esse elemento operation que faz parte do portType definido no WSDL:
ou seja a porta de entrada é a operacao que queremos executar.

Repare que o elemento portType está parecido com uma interface Java: declara um nome (EstoqueWS) e define as operações com cada entrada e saída.

A mensagem SOAP se baseia no input ou output das operações do portType.

portType name="EstoqueWS">
	<operation name="getItens">
		<input  message="tns:getItens" />
		<output  message="tns:getItensResponse" />
	</operation>
</portType>

@WebResult(name="item")
Cada resultado estará dentro de uma tag item, vários blocos de resultados.


Anotamos a classe de mapeamento como Root element, e também podemos renomear cada item novamente

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaItens {

    @XmlElement(name="item")
    private List<Item> itens;


Código final
	@WebService()
	public class EstoqueWS {

		private ItemDao dao = new ItemDao(); 

		@WebMethod(operationName="todosOsItens")
		@WebResult(name="itens")
		public ListaItens getItens() {
			System.out.println("Chamando getItens()");
			return new ListaItens(dao.todosItens()); //criando uma ListaItens
		}

	}

Saida final
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <ns2:todosOsItensResponse xmlns:ns2="http://ws.estoque.caelum.com.br/">
         <itens>
            <item>
               <codigo>SEO</codigo>
               <nome>SEO na Prática</nome>
               <quantidade>4</quantidade>
               <tipo>Livro</tipo>
            </item>

            <!-- outros itens omitidos -->
         </itens>
      </ns2:todosOsItensResponse>
   </S:Body>
</S:Envelope>

Assim como a saída podemos definir a nomenclatura da entrada via
@RequestWrapper(localName="listaItens")

O que você aprendeu nesse capítulo?
os métodos Java se tornam operations no WSDL
as operations fazem parte do portType
as anotações do JAX-WS servem para personalizar o WSDL
a especificação JAX-B gera o XML por baixo dos panos

-------------------------------------------------
## FILTROS

@WebMethod(operationName="todosOsItens")
    @WebResult(name="itens")
    public ListaItens getItens(Filtros filtros) { //cuidado, plural
        System.out.println("Chamando getItens()");
        List<Filtro> lista = filtros.getLista();
        List<Item> itensResultado = dao.todosItens(lista);
        return new ListaItens(itensResultado);
    }
	
	
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Filtros {

    @XmlElement(name="filtro")
    private List<Filtro> filtros;

    //construtores

    public List<Filtro> getLista() {
        return filtros;
    }

}


<ws:todosOsItens>
 <!--Optional:-->
 <arg0>
	<!--Zero or more repetitions:-->
	<filtro>
		<!--Optional:-->
		<tipo>Celular</tipo>
		<!--Optional:-->
		<nome>Moto</nome>
	</filtro>
 </arg0>
</ws:todosOsItens>


-- Criando um novo item de agrupamento

@WebMethod(operationName="todosOsItens") 
    @WebResult(name="itens")
    public ListaItens getItens(@WebParam(name="filtros") Filtros filtros) {
        System.out.println("Chamando getItens()");
        List<Filtro> lista = filtros.getLista();
        List<Item> itensResultado = dao.todosItens(lista);
        return new ListaItens(itensResultado);
    }
	
	
<ws:todosOsItens>
         <!--Optional:-->
         <filtros>
            <!--Zero or more repetitions:-->
            <filtro>
               <tipo>?</tipo>
               <nome>?</nome>
            </filtro>
         </filtros>
      </ws:todosOsItens>
	  
	  
## CABEÇALHOS - HEADER SOAP










	
	
	
	
	
	
	





