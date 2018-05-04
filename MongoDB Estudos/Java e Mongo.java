JAVA E MONGODB


//------------------------------------------
//				PARTE 1
//------------------------------------------

//MAVEN
	<dependencies>
		<dependency>
			<groupId>org.mongodb</groupId>
			<artifactId>mongodb-driver</artifactId>
			<version>3.4.3</version>
		</dependency>
	</dependencies>

//CONETAR COM O DRIVER
//ABRIR UMA COLLECTION
	public class Principal {
		public static void main(String[] args){
			MongoClient cliente = new MongoClient(); //CONECETA
			MongoDatabase bancoDeDados = cliente.getDatabase("test");//ABRI A DATABASE
			MongoCollection<Document> alunos = bancoDeDados.getCollection("alunos");//ABRE A COLECAO
			Document aluno = alunos.find().first();//PEGA O PRIMEIRO SOMENTE
			System.out.println(aluno);//PRINTA O JSON
			cliente.close();//FECHA A CONEXAO
		}
	}
	//Result seria como
	Document{{_id=58fb5f0f9823dc9c0ffccd95, nome=Felipe, data_nascimento=Sat Mar 26 00:00:00 BRT 1994, curso=Document{{nome=Sistemas de informação}}, notas=[10.0, 9.0, 4.0], habilidades=[Document{{nome=inglês, nivel=avançado}}, Document{{nome=taekwondo, nivel=básico}}], contato=Document{{endereco=R. Dona Avelina, 10 - Vila Mariana, São Paulo - SP, 04111-010, coordinates=[-23.586917, -46.633484], type=Point}}}}


//INSERIR
Document novoAluno = new Document("nome", "Joao")
                .append("data_nascimento", new Date(2003, 10, 10))
                .append("curso", new Document("nome", "Historia"))
                .append("notas", Arrays.asList(10, 9, 8))
                .append("habilidades", Arrays.asList(
                        new Document().append("nome", "Ingles").append("nível", "Básico"),
                        new Document().append("nome", "Espanhol").append("nível", "Básico")));
        alunos.insertOne(novoAluno);


//ATUALIZA com FILTRO
	//procure pelo PRIMEIRO nome=Joao, e altere o nome=Joao Silva.
	alunos.updateOne(Filters.eq("nome", "Joao"),
		new Document("$set", new Document("nome", "Joao Silva")));


//DELETE
	//procure o PRIMEIRO nome joao silva e delete do banco
	alunos.deleteOne(Filters.eq("nome", "Joao Silva"));


//------------------------------------------
//				PARTE 2
//------------------------------------------
Utilizamos o Spring Initializer
	http://start.spring.io/

	Adicione WEB + THYMELIF + DEVTOOLS

//Classe basica
	package br.com.alura.escolalura.controllers;

	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.GetMapping;

	@Controller
	public class AdminController {

		@GetMapping("/")
		public String index(){
			return "index";
		}

	}

//HTML Basico
	<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8"/>
		<title>Insert title here</title>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css"  media="screen,projection"/>
	</head>
	<body>
		<h1>Bem-vindo ao Sistema Escolalura</h1>

		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
	</body>
	</html>

//Outra URl de pagina
	//Na URL
		@GetMapping("/aluno/cadastrar") //http://localhost:8080/aluno/cadastrar
		public String cadastrar(Model model){

			model.addAttribute("aluno", new Aluno());

			return "aluno/cadastrar";
		}

	//Cria os getters and setters
		public class Curso {
		  private String nome;
		}
		public class Nota {
		  private Double valor;
		}
		public class Habilidade {
		  private String nome;
		  private String nivel;
		}
		public class Aluno {
		  private ObjectId id;
		  private String nome;
		  private Date dataNascimento;
		  private Curso curso;
		  private List<Nota> notas;
		  private List<Habilidade> habilidades;
		}

	//Crie uma pasta alunos com um arquivo cadastrar.jsp
	//themeleaf foi adicionado na jsp desta forma
		th:field="*{contato.endereco}")








































