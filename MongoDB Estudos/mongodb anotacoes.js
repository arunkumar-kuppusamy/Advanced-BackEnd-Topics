//ETAPAS PARA INSTALACAO
https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/

	"C:\Program Files\MongoDB\Server\3.6\bin\mongo.exe"
	mkdir c:\data\db
	mkdir c:\data\log

	C:\Program Files\MongoDB\Server\3.6\mongod.cfg
		systemLog:
			destination: file
			path: c:\data\log\mongod.log
		storage:
			dbPath: c:\data\db

	"C:\Program Files\MongoDB\Server\3.6\bin\mongod.exe" --config "C:\Program Files\MongoDB\Server\3.6\mongod.cfg" --install

	net start MongoDB
		[initandlisten] waiting for connections on port 27017

	net stop MongoDB

	To remove the MongoDB service use the following command:
		"C:\Program Files\MongoDB\Server\3.6\bin\mongod.exe" --remove

//CRIAR UMA COLECAO
	db.createColletion("alunos");
	
//SE NAO EXISTIR E QUISER FAZER ALTER TABLE ADD OU DROP COLUMN
//SET==Criar uma nova coluna, UNSET=Drop uma coluna
	db.people.updateMany(
		{ },
		{ $set: { join_date: new Date() } }
	)
	db.people.updateMany(
		{ },
		{ $unset: { "join_date": "" } }
	)

//INSERIR UM REGISTRO
	db.alunos.insert({
		"nome": "Felipe",
		"data_nascimento": new Date(1994, 02, 26),
		"notas": [10, 9, 4.5],
		"curso": {
			"nome": "Sistemas de informação"
		},
		"habilidades": [
			{
				"nome": "Inglês",
				"nível": "Avançado"
			}
		] 
	})
	db.alunos.insert({
		nome : "Fernando",
		data_nascimento : new Date(1994, 03, 26),
		notas : [ 10, 4.5, 7],
		curso : {
			nome : "Sistema de informação"
		}
	})

	//Automaticamente cria uma collection
	db.people.insertOne( {
		user_id: "abc123",
		age: 55,
		status: "A"
	 } )
 
//REMOVER UM REGISTRO
	db.alunos.remove({
		"_id" : ObjectId ("56cb0002b6d75ec12f75d3b5")
	})

//PESQUISAR UM REGISTRO
	db.alunos.find(); //listar todos
	db.alunos.find().pretty();//formatar a saida

	//OR
	db.alunos.find({
		$or : [
			{"curso.nome" : "Sistemas de informação"},
			{"curso.nome" : "Engenharia Química"}    
		]
	})
	//OR junto com AND
	db.alunos.find({
		$or : [
			{"curso.nome" : "Sistemas de informação"},
			{"curso.nome" : "Engenharia Química"}    
		],
		"nome" : "Daniela"
	})
	
	AND é só colocar fora fora de or ou no padrao que ja e AND.
	IN=&in - Ou isso Ou aquilo

	//para abrir com JOIN entre duas tabelas usando o operador ponto
	db.alunos.find({ "habilidades.nome" : "inglês" }).pretty()
		//Equivalente a este select relacional
		SELECT a.(*)
			FROM habilidades as h
			JOIN alunos as a ON a.id = h.aluno_id
			WHERE h.nome="inglês"
			AND a.nome = "Felipe";

	//> e <
	db.alunos.find({//findOne TRAZ APENAS O PRIMEIRO
		notas : { $gt : 5 } //GREATER THAN
	})
	db.alunos.find({"notas":{$lt:5}})//LOWER THAN

	//SORT ORDEM ALFABETICA
	db.alunos.find().sort({"nome" : 1})//1=CRESCENTE
	db.alunos.find().sort({"nome" : -1})//-1=DESCRECENTE
	
	//LIMIT
	db.alunos.find().sort({"nome" : 1}).limit(3);//limite aos 3 primeiros registros.


//ATUALIZE UM DADO NA TABELA
//UPDATE cursos SET nome = "Sistemas de informação" WHERE nome = "Sistema de informação"
	db.alunos.update(
		{"curso.nome" : "Sistemas de informação"},
		{
			$set : 
			   {"curso.nome" : "Sistemas de Informação"}  
			}, 
		  {
			multi : true //ATUALIZE NÃO APENAS O PRIMEIRO COMO E O PADRAO
		  }
	)

	//PUSH - ADICIONA UM ELEMENTO
	db.alunos.update(
		{"_id" : ObjectId("56cb0002b6d75ec12f75d3b5")},
		{
			$push : {
				"notas" : {$each : [8.5, 3] }
			}    
		}
	)

//GEOLOCALIZACAO
//GEO NEAR indentifica pelas chaves coordinates e type, precisa ser este nome.
	db.alunos.update(
	{ "_id" : ObjectId("56cb0139b6d75ec12f75d3b6") },
	{
		$set : {
		localizacao : {
			endereco : "Rua Vergueiro, 3185",
			cidade : "São Paulo",
			coordinates : [-23.588213, -46.632356],
			type : "Point"
			}
		}
	}
	)

	//Importamos/Inserindo uma lista de alunos de um arquivo para uma colecao
	mongoimport -c alunos --jsonArray < alunos.json

	db.alunos.aggregate([
	{
		$geoNear : { //API DO MONGO PARA PESQUISAR DISTANCIAS GEOREFERENCIAIS
			near : {
				coordinates: [-23.5640265, -46.6527128], //USE ESTE PONTO COMO REFERENCIA
				type : "Point"
			},
			distanceField : "distancia.calculada",//CRIA UM CAMPO PARA ARMAZENAR A DISTANCIA
			spherical : true, 
			num : 4; //SOMENTE OS 4 PRIMEIROS
		}
	},
	{ $skip :1 }//PULA O PRIMEIRO QUE SOU EU MESMO
	])

	db.alunos.createIndex({
		localizacao : "2dsphere" //ANALISE COMO UMA SPERA EM NAO EM LINHA RETA
	})


