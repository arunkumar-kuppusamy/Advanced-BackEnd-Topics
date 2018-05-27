
LAMBDA

Interface com apenas UNICO METODO são interfaces funcionais
no Java8 temos uma nova anotacao
se voce adicionar e não for ele vai dar erro ao acessar
@FunctionalInterface

Predicate é generico e vem padrao no Java
Funciona da mesma forma do Matcher
Predicat tem a funcao test
Matcher tem a funcao match

Sinxtaxe do Lambda é
(parametros) -> {código}

Não precisa passar o nome do método que ira retornar
porque lambda somente existe com interface funcional
e ela somente tem um unico metodo, logo anote com como @F..

Se o lambda cnosegue descobrir qual a classe não precisa colocar
a classe, podemos omitir, mas se tiver duas classes não dá.
Sempre que for somente uma classe podemos assumir que já sabe.

Se tiver um único parametro no método, nem mesmo os paranteses precisa

Se  a linha de código for o retorno do metodo, mas precisa colocar nem o return.

Predicate<Pessoa> criterio = p -> idade>=18;


Se o método NÃO RECEBE NADA, voce e obrigado a passar um parametro vazio sem preencher assim ().




02
Escreva uma expressão Lambda simples que consuma uma expressão Lambda Predicate
PRÓXIMA ATIVIDADE

Escreva uma expressão Lambda simples que consuma uma expressão Lambda Predicate


class Person {
    private String name;
    private int age;
    //...
}

interface Matcher<T>{
    boolean test(T t);
}

class PersonFilter{

    public List<Person> filter(List<Person> input, 
                               Matcher<Person> matcher){
        List<Person> output = new ArrayList<>();
        for (Person person : input) {
            if(matcher.test(person)){
                output.add(person);
            }
        }    
        return output;
    }
}

class AgeOfMajority implements Matcher<Person>{
    @Override
    public boolean test(Person p) {
        return p.getAge() >= 18;
    }
}

//E usar esta classe em nosso código:
PersonFilter pf = new PersonFilter();
List<Person> adults = pf.filter(persons, new AgeOfMajority());


List<Person> adults = pf.filter(persons, new Matcher<Person>() {
    @Override
    public boolean test(Person p) {
        return p.getAge() >= 18;
    }
});

//Para usar um ::lambda:: em Java, precisamos de uma interface funcional. Interfaces funcionais são interfaces normais, mas com apenas um método. Nossa interface Matcher pode ser considerada funcional. É possível checar se uma interface é funcional usando a ::annotation:: FunctionalInterface, se não for funcional, o código não compila:


@FunctionalInterface 
interface Matcher<T>{
    boolean test(T t);
}

import java.util.function.Predicate;

class PersonFilter{

    public List<Person> filter(List<Person> input,
                               Predicate<Person> matcher){
        List<Person> output = new ArrayList<>();
        for (Person person : input) {
            if(matcher.test(person)){
                output.add(person);
            }
        }    
        return output;
    }
}

//E para fazer o filtro:
Predicate<Person> matcher = new Predicate<Person>() {
    @Override
    public boolean test(Person p) {
        return p.getAge() >= 18;
    }
};
List<Person> adults = pf.filter(persons, matcher);


Podemos remover mais código ainda. Repare que a variável matcher é do tipo Predicate<Person>. Aqui podemos inferir o tipo do parâmetro pelo tipo ::generics:: da interface. o código fica:


Predicate<Person> matcher = (p) -> {return p.getAge() >= 18;};
Se temos apenas um argumento, podemos ainda remover os parênteses:


Predicate<Person> matcher = p -> {return p.getAge() >= 18;};
Se temos apenas uma linha de código dentro do ::lambda::, podemos omitir as chaves. Se esta linha for o retorno, podemos omitir a palavra return também:


Predicate<Person> matcher = p -> p.getAge() >= 18;
Pronto, já retiramos bastante código, está bem mais limpo. Nosso código no final fica assim:


Predicate<Person> matcher = p -> p.getAge() >= 18;
List<Person> adults = pf.filter(persons, matcher);
Não somos obrigados a armazenar o ::lambda:: em uma variável, podemos passá-lo diretamente como parâmetro do método:


List<Person> adults = pf.filter(persons, p -> p.getAge() >= 18);
Vamos entender qual a vantagem desta abordagem. Se agora precisarmos de um outro filtro, que retorna apenas as pessoas cujo nome comece com a letra "A", podemos simplesmente fazer:


List<Person> namesStartingWithA =
    pf.filter(persons, p -> p.getName().startsWith("A"));
Não há necessidade de criar classes, nem mesmo anônimas. A inclusão dos ::lambdas:: nos permite escrever código altamente adaptável e ainda reduzir muito a verbosidade comum do Java.




Antes de passar para o próximo exemplo, vamos entendera regras para se escrever um ::lambda::.

::Lambdas:: podem ter vários argumentos, como um método. Basta separá-los por ,.
O tipo dos parâmetros pode ser inferido e, assim, omitido da declaração.
Se não houver nenhum parâmetro, é necessário incluir parênteses vazios, como em:

Runnable r = () -> System.out.println("a runnable object!");
Se houver apenas um parâmetro, podemos omitir os parênteses, como em:

Predicate<Person> matcher = p -> p.getAge() >= 18;
O corpo do ::lambda:: pode conter várias instruções, assim como um método.
Se houver apenas uma instrução, podemos omitir as chaves, como em :

Predicate<Person> matcher = p -> p.getAge() >= 18;
Se houver mais de uma instrução, é necessário delimitar o corpo do ::lambda:: com chaves, como em:

Runnable r = () ->  { 
    int a = 10;
    int b = 20;
    System.out.println(a + b);
}
Acessando variáveis do objeto com ::lambdas::
::Lambdas:: podem interagir com as variáveis de instância dos objetos onde foram declarados. Temos apenas que tomar cuidado com variáveis marcadas como final:


public class LambdaScopeTest {

    public int instanceVar = 1;
    public final int instanceVarFinal = 2;

    public static void main(String[] args) {
        new LambdaScopeTest().test();
    }

    private void test() {
        instanceVar++; // ok
        new Thread(() -> {
            System.out.println(instanceVar); // ok
            instanceVar++; // ok

            System.out.println(instanceVarFinal); // ok
            instanceVarFinal++; // compile error
        }).start();
    }
Já com variáveis locais de método e parâmetros, as regras são um pouco mais complexas. ::Lambdas:: só podem interagir com variáveis locais caso estas estejam marcadas como final (uma referência imutável) ou que sejam efetivamente final (não são final, mas não são alteradas). Não é possível alterar o valor de nenhuma variável local dentro de um ::lambda:::


    private void test() {
        int unchangedLocalVar = 3;   // effectively final
        final int localVarFinal = 4; // final
        int simpleLocalVar = 0;
        simpleLocalVar = 9; // updated the value

        new Thread(() -> {
            System.out.println(unchangedLocalVar);   // can read 
            System.out.println(localVarFinal);       // can read
            System.out.println(simpleLocalVar); // compile error
        }).start();
    }
Conflitos de nomes com ::lambdas::
As variáveis do ::lambda:: são do mesmo escopo que o método onde ele foi declarado, portanto, não podemos declarar nenhuma variável, como parâmetro ou dentro do corpo, cujo nome conflite com alguma variável local do método:


private void test(String param) {
    String methodVar = "method"; //not final

    Predicate<String> a = 
        param -> param.length() > 0; //compile error
    Predicate<String> b = 
        methodVar -> methodVar.length() > 0; //compile error
    Predicate<String> c = 
        newVar -> newVar.length() > 0; // ok
}


// --------- exercicios ----------------------

interface Printer{
    void printMessage();
}

class A {
    public static void main(String[] args) {
        Printer p = null;
        p = () -> System.out.println("Hello World");
        p.printMessage();
    }
}

//Essa está errada:
Predicate<Integer> big = list -> list.size() > 100
Integer nao tem um método chamado size(), entao nao compila. São as collections (filhas e implementadores de java.util.Collection) que são famosas por possuírem um método chamado size().

//Essa está errada:
interface Calculator<T>{
    T function(T a, T b);
}
Calculator<Integer> divide = (int a, int b) -> {return (Integer) a / b;}

//Erro de compilação
  class A {
        public static void main(String[] args) {
            for(int i = 0; i < 10; i++){
                new Thread(() -> System.out.println(i)).start();
            }
        }
    }



//Compila e imprime 1
import java.util.function.Predicate;

class A{
    int a = 0;

    public static void main(String[] args) {
        new A().method(1, a -> a > 0); // A
    }        

    private void method(int a, Predicate<Integer> d) {
        if(d.test(a)){ // B
            System.out.println(a);
        }
    }
}

//Não compila por erro na linha A
//Isso se deve ao fato de a variável a já ter sido definida como parâmetro do método method, não podendo então ser definida no predicado d uma variável com o mesmo nome.
class A{
    int a = 0;

    public static void main(String[] args) {
        new A().method(1);
    }        

    private void method(int a) {
        Predicate<Integer> d = a -> a > 0; // A

        if(d.test(a)){ // B
            System.out.println(a);
        }
    }
}

















