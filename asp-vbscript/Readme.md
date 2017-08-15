[http://www.macoratti.net/12/04/vb_pbd1.htm][http://www.macoratti.net/aspado.htm]
<br />
[http://www.macoratti.net/ado.htm#adoprati] - Como criar um driver mdb.
<br />
> Se for openrecordset é ODBC do jeito antigo, com arquivos mdb e tem que criar uma instancia no servidor com entrada para servidores OLE (mdb-Microsoft Access Driver)
> <br /><br />Se for recordset é ADO, parecido com o JDBC onde vc cria uma instancia passando a url de conexao a porta usuarioe senha, sem precisar de uma instancia fisica do banco de dados.

```xml
  <HTML>
			<HEAD>
				<TITLE>incluir2.asp - Exemplo</TITLE>
			</HEAD>
				<BODY>
					<H1> Incluindo um novo cliente </H1>
					<%
					Dim con
					Dim rst
					Const adOpenKeyset = 1
					Const adLockOptimistic = 3

					' Objects created with this method have page scope. Seria a mesma coisa que dar criar uma classe para guardar a instancias do banco de dados e fazer um new aqui.
					Set con = Server.CreateObject("ADODB.Connection")
					Set rst = Server.CreateObject("ADODB.Recordset")

					' Abre a conexão com o banco de dados agenda.mdb
					con.Open "DBQ=C:\asp_db\agenda.mdb;DRIVER={Microsoft Access Driver (*.mdb)}" 'BANCO DE DADOS LOCAL

					' Cria um Recordset do tipo keyset baseado na tabela clientes
					' usando o bloqueio otimista
					rst.Open "SELECT * FROM clientes", con, adOpenKeyset, adLockOptimistic

					' Cria um registro em branco
					rst.AddNew

					  ' Recebe os dados do formulário e os atribui aos campos da tabela cliente
					  rst("Nome")= Request.Form("Nome")
					  rst("Endereco")= Request.Form("Endereco")
					  rst("Cidade")= Request.Form("Cidade")
					  rst("Estado")= Request.Form("Estado")
					  rst("Cep")= Request.Form("Cep")

					' Salva o registro 
					rst.Update

					Response.Write "<B>Registro salvo com sucesso !</B>"

					rst.Close
					con.Close
					%>
					<BR><A HREF="menu.html">Retorna a página principal</A>
				</BODY>
			</HTML>
```
