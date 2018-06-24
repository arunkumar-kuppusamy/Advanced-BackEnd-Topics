## Testes de Unidade usando JUNIT 5.


##### JUNIT esta no Eclipse por padrão, nao precisa baixar JAR

1 - Botao direito no projeto > Add Libraries > JUNIT 5. pronto!!

2 - O metodo deve ser public void {}, e nao pode ter argumentos

3 - O metodo principal deve ser anotado com @org.junit.Test

4 - Como boa pratica o metodo Test deve conter um nome que faça sentido, exemplo verificaSeEstourouLimite

5 - Assert.assertEquals tambem vem de org.junit. e pode ser importado static.

