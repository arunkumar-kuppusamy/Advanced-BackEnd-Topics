

Saber que a nova API esta tudo dentro do java.time package.
import java.time.*;
LocalDateTime
ChronoField.Day_OF_WEEK 1 A 7 - NAO É ZEROOOOOOOO

LocalDate é a data logo não tem a hora.
o objeto criado é imutável logo não podemos usar um set, e sim um with
d.withMonth(4); esse valor precisa retornar e guardar em outro, senão não vai mudar nada

metodos With pode ser encadeados - d.withMonth(1).withYear(2015);

para adicionar dias ou meses, anos usamos plusDays, plusMonths.
temos o minus também.

=========================================
já conhecida java.util.Date, agora tem um novo metodo chamado

Sempre convertemos um Date para LocalDateTime, para depois pegar a data ou somente a hora.
ZoneId.systemDefault();

toInstant - é converter num Instant e este serve para
transformar num LocalDateTime que tem todas as funcionalidades
novas da API java 8.

Date.from converte um Instant em Date

Duration é um periodo de uma unidade de tempo
tipo 10 segundos, 10 meses.

Instant tem um método plus, minus.
Logo podemos fazer operacaoes usando a classe Instant.

Instant m1 = Instant.EPOCH// data em milisegundos desde 1/1/1970
long s = Duration.between(m1, Instant.now()).getSeconds();

Para datas usamos ChronoUnit ou Period

ChronoUnit mostra algo como 32 anos, 165meses, 1milhão de dias.
ChronoUnit.YEARS.between(LocalDate.of(1985,9,2), LocalDate.now());

Periodo é tipo 32 anos, 8 meses, 1 dia
Period.between(LocalDate.of(1985,9,2), LocalDate.now()).getYears();


Para formatar datas

DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
formatter.format(agora); ou
agora.format(formatter);

String s = LocalDate.parse("23/01/1990", formatter);


-------------------
07
Crie e manipule dados de calendários usando as classes do pacote java.time
PRÓXIMA ATIVIDADE

Java 8 - Trabalhando com algumas classes da Java API
Crie e manipule dados de calendários usando as classes java.time.LocalDateTime, java.time.LocalDate, java.time.LocalTime, java.time.format.DateTimeFormatter?, java.time.Period

As classes que serão cobradas são:

	LocalDate: representa uma data sem hora no formato yyyy-MM-dd (ano-mês-dia).
	LocalTime: representa uma hora no formato hh:mm:ss.zzz (hora:minuto:segundo.milissegundo).
	LocalDateTime: representa uma data com hora no formato yyyy-MM-dd-HH-mm-ss.zzz (ano-mês-dia-hora-minuto-segundo.milissegundo).
	MonthDay: representa um dia e mês, sem o ano.
	YearMonth: representa um mês e ano, sem o dia.
	Period: representa um período de tempo, em dia, mês e ano.
	DateTimeFormatter: classe que possui vários métodos para formatação.
	Para utilizar essas classes, é necessário conhecer uma ou outra classe da API de java.time que não estão na lista da seção, sendo que também as veremos aqui.

Todas as classes do pacote java.time são imutáveis, ou seja, após serem instanciadas, seus valores não podem ser alterados, assim como a classe String. Portanto, lembre-se que todos os métodos que parecem modificar os valores das datas retornam novas instâncias com os valores alterados, enquanto o objeto original segue inalterado.


LocalTime currentTime = LocalTime.now(); // 09:05:03.244
LocalDate today = LocalDate.now(); // 2014-12-10
LocalDateTime now = LocalDateTime.now(); 
                        // 2014-12-10-09-05-03.244
LocalTime time = LocalTime.now(ZoneId.of("America/Chicago")); 
LocalDate date = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
LocalDateTime dateTime = 
    LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
LocalTime noon = LocalTime.of(12, 0);
LocalDate christmas2014 = LocalDate.of(2014, 12, 25);
LocalDate christmas2015 = LocalDate.of(2015, Month.DECEMBER, 25);
LocalDate christmas2014 = LocalDate.of(2014, 12, 25);
LocalDate christmas2015 = LocalDate.of(2015, Month.DECEMBER, 25);
//Podemos representar qualquer natal:
MonthDay someChristmas = MonthDay.of(Month.DECEMBER, 31);
//Ainda com o método of, podemos criar um momento exato no tempo:
LocalDateTime someDate = 
            LocalDateTime.of(2017, Month.JANUARY, 25, 13, 45);
LocalDate christmas2014 = LocalDate.of(2014, 12, 25);
LocalDateTime christmasAtNoon = 
    LocalDateTime.of(christmas2014, meioDia);


Manipulando datas
	Uma das decisões de ::design:: que guia a nova API de datas é a padronização dos nomes de métodos que têm o mesmo comportamento. Os nomes mais comuns são:

	get: obtém o valor de algo
	is: verifica se algo é verdadeiro
	with: lembra um ::setter::, mas retorna um novo objeto com o valor alterado
	plus: soma alguma unidade ao objeto, retorna um novo objeto com o valor alterado
	minus: subtrai alguma unidade do objeto, retorna um novo objeto com o valor alterado
	to: converte um objeto de um tipo para outro
	at: combina um objeto com outro


LocalDateTime now = LocalDateTime.of(2014,12,15,13,0);
System.out.println(now.getDayOfMonth()); // 15
System.out.println(now.getDayOfYear());  // 349
System.out.println(now.getHour());       // 13
System.out.println(now.getMinute());     // 0
System.out.println(now.getYear());       // 2014
System.out.println(now.getDayOfWeek());  // MONDAY
System.out.println(now.getMonthValue()); // 12
System.out.println(now.getMonth());      // DECEMBER
LocalDateTime now = LocalDateTime.of(2014,12,15,13,0);
// 15
System.out.println(now.get(ChronoField.DAY_OF_MONTH));  
// 349 
System.out.println(now.get(ChronoField.DAY_OF_YEAR));
// 13    
System.out.println(now.get(ChronoField.HOUR_OF_DAY));  
// 0  
System.out.println(now.get(ChronoField.MINUTE_OF_HOUR));
// 2014
System.out.println(now.get(ChronoField.YEAR));   
// 1 (MONDAY)        
System.out.println(now.get(ChronoField.DAY_OF_WEEK));    
// 12
System.out.println(now.get(ChronoField.MONTH_OF_YEAR));

LocalDate d = LocalDate.now();
d.getHour(); //compile error, method not found.
//Comparações entre datas
MonthDay day1 = MonthDay.of(1, 1); //01/jan
MonthDay day2 = MonthDay.of(1, 2); //02/jan
System.out.println(day1.isAfter(day2)); //false
System.out.println(day1.isBefore(day2)); //true

//Além de métodos de comparação, também existem aqueles para indicar se alguma porção da data é suportada pelo objeto:
LocalDate aprilFools = LocalDate.of(2015, 4, 1);
LocalDate foolsDay = LocalDate.of(2015, 4, 1);
// são equals?
System.out.println(aprilFools.isEqual(foolsDay)); //true
// este objeto suporta dias?
System.out.println(aprilFools.isSupported(
    ChronoField.DAY_OF_MONTH)); //true
// este objeto suporta horas?
System.out.println(aprilFools.isSupported(
    ChronoField.HOUR_OF_DAY)); //false
// posso fazer operações com dias?
System.out.println(aprilFools.isSupported(ChronoUnit.DAYS)); 
//true
// posso fazer operações com horas?
System.out.println(aprilFools.isSupported(ChronoUnit.HOURS)); 
//false



//Alterando as datas
//Todos os objetos da nova API de datas são imutáveis, ou seja, não podem ter o seu valor alterado após a criação. Mas existem alguns que podem ser utilizados para obter versões modificadas destes objetos. Vamos começar com o método with, que é como um ::setter::, mas retornando um novo objeto em vez de alterar o valor do objeto atual:


LocalDate d = LocalDate.of(2015, 4, 1); //2014-04-01
d = d.withDayOfMonth(15).withMonth(3); //chaining
System.out.println(d); //2015-03-15

LocalDate d = LocalDate.of(2013, 9, 7);
System.out.println(d); // 2013-09-07
d.withMonth(12);
System.out.println(d); // 2013-09-07
LocalTime d = LocalTime.now();
d.withDayOfMonth(15); // compile error
//Caso o objetivo seja incrementar ou decrementar alguma parte da data, temos os métodos plus e minus:
LocalDate d = LocalDate.of(2013, 9, 7);
d = d.plusDays(1).plusMonths(3).minusYears(2);
System.out.println(d); // 2011-12-08
LocalDate d = LocalDate.of(2013, 9, 7);
d = d.plusWeeks(3).minus(3, ChronoUnit.WEEKS);
System.out.println(d); // 2011-12-08
LocalDate d = LocalDate.of(2013, 9, 7);
                        // UnsupportedTemporalTypeException
                        //LocalDate não suporta horas!
d = d.plus(3, ChronoUnit.HOURS); 
System.out.println(d);

//Convertendo entre os diversos tipos de datas
//A classe LocalDateTime possui métodos para converter esta data/hora em objetos que só possuem a data (LocalDate) ou que só possuem a hora (LocalTime):
LocalDateTime now = LocalDateTime.now();
LocalDate dateNow = now.toLocalDate(); // de DateTime para Date
LocalTime timeNow = now.toLocalTime(); // de DateTime para Time

LocalDateTime now = LocalDateTime.now();
LocalDate dateNow = now.toLocalDate(); // de DateTime para Date
LocalTime timeNow = now.toLocalTime(); // de DateTime para Time

// de Date para DateTime
LocalDateTime nowAtTime1 = dateNow.atTime(timeNow); 
// de Time para DateTime
LocalDateTime nowAtTime2 = timeNow.atDate(dateNow);

Date d = new Date();
Instant i = d.toInstant();
LocalDateTime ldt1 = 
    LocalDateTime.ofInstant(i, ZoneId.systemDefault());


Calendar c = Calendar.getInstance();
Instant i = c.toInstant();
LocalDateTime ldt2 = LocalDateTime.ofInstant(i, 
                                        ZoneId.systemDefault());

Date d = new Date();
Instant i = d.toInstant();
LocalDateTime ldt1 = 
    LocalDateTime.ofInstant(i, ZoneId.systemDefault());

Instant instant = ldt1.toInstant(ZoneOffset.UTC);
Date date = Date.from(instant);


Instant now = Instant.now(); // agora
Duration tenSeconds = Duration.ofSeconds(10); // 10 segundos
Instant t = now.plus(tenSeconds); // agora mais 10 segundos

//O próximo exemplo mostra como pegar o intervalo em segundos entre dois instantes:
Instant t1 = Instant.EPOCH; // 01/01/1970 00:00:00
Instant t2 = Instant.now();
long secondsSinceEpoch = Duration.between(t1, t2).getSeconds();

LocalDate birthday = LocalDate.of(1983, 7, 22);
LocalDate base = LocalDate.of(2014, 12, 25);

// 31 anos no total
System.out.println(ChronoUnit.YEARS.between(birthday, base)); 
// 377 meses no total
System.out.println(ChronoUnit.MONTHS.between(birthday, base)); 
// 11479 dias no total
System.out.println(ChronoUnit.DAYS.between(birthday, base));

LocalDate birthday = LocalDate.of(1983, 7, 22);
LocalDate base = LocalDate.of(2014, 12, 25);

Period lifeTime = Period.between(birthday, base);

System.out.println(lifeTime.getYears()); // 31 anos
System.out.println(lifeTime.getMonths()); // 5 meses
System.out.println(lifeTime.getDays()); // 3 dias


//Formatando e convertendo em texto
//Para formatar a impressão de nossas datas, usamos a classe DateTimeFormatter, do pacote java.time.format. Ele segue o mesmo padrão da clássica SimpleDateFormat.
LocalDate birthday = LocalDate.of(1983, 7, 22);
DateTimeFormatter formatter = 
    DateTimeFormatter.ofPattern("yyyy MM dd");
System.out.println(formatter.format(birthday)); // 1983 07 22

LocalDate birthday = LocalDate.of(1983, 7, 22);
DateTimeFormatter formatter =
    DateTimeFormatter.ofPattern("yyyy MM dd");
System.out.println(birthday.format(formatter)); // 1983 07 22
//Já para transformar um texto em uma data, usamos o DateTimeFormater juntamente com o método parse da classe que desejamos instanciar:

DateTimeFormatter formatter = 
    DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate d = LocalDate.parse("23/04/1986",formatter);
System.out.println(formatter.format(d)); // 23/04/1986


DateTimeFormatter formatter = 
    DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate d = 
    LocalDate.parse("23/15/1986",formatter); 
    // throws DateTimeParseException
System.out.println(formatter.format(d)); // 23/04/1986

//O método MonthDay.isSupported não aceita parâmetros do tipo ChronoUnit, apenas ChronoField.
   System.out.println(YearMonth.now().isSupported(
        ChronoField.DAY_OF_MONTH));
    System.out.println(MonthDay.now().isSupported(
        ChronoUnit.DAYS));
    System.out.println(LocalDate.now().isSupported(
        ChronoUnit.DAYS));
    System.out.println(LocalDateTime.now().isSupported(
        ChronoField.DAY_OF_MONTH));


//A resposta certa é que não compila. Não existe um valor ChronoUnit.YEAR, e sim ChronoUnit.YEARS.
System.out.println(YearMonth.now().isSupported(
        ChronoUnit.MONTHS));
    System.out.println(YearMonth.now().isSupported(
        ChronoField.DAY_OF_MONTH));
    System.out.println(MonthDay.now().isSupported(
        ChronoField.DAY_OF_MONTH));
    System.out.println(LocalDate.now().isSupported(
        ChronoUnit.DAYS));
    System.out.println(LocalDate.now().isSupported(
        ChronoUnit.YEAR)); 
    System.out.println(LocalDateTime.now().isSupported(
        ChronoField.HOUR_OF_AMPM));
    System.out.println(LocalDateTime.now().isSupported(
        ChronoField.YEAR));


