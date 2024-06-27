drop database if exists PeekABook;
create database PeekABook;
use PeekABook;

CREATE TABLE Utente(
id int primary key auto_increment,
adm tinyint(1) not null default 0,
email varchar(40) not null,
username varchar(20) not null,
passwordHash varchar(256) not null,
nome varchar(40) not null,
cognome varchar(40) not null,
via varchar(40) not null,
civico int not null,
citta varchar(40) not null,
CAP int(5) not null);

CREATE TABLE Autore(
codiceAutore int auto_increment primary key,
nome varchar(30) not null,
cognome varchar(30) not null);

CREATE TABLE Carrello(
utente int,
totale double not null,
numeroArticoli int not null,
primary key(utente)
); 

CREATE TABLE Articolo(
codice int primary key auto_increment,
prezzo double not null,
valutazione int not null,
quantita int not null,
copertina varchar(256),
nome varchar(100) not null);

CREATE TABLE ArticoloSelezionato(
utente int,
articolo int,
quantita int not null,
foreign key(articolo) references Articolo(codice) on update cascade on delete cascade,
primary key(utente,articolo));

CREATE TABLE Ordine(
utente int,
numero int auto_increment,
via varchar(40) not null,
civico int not null,
citta varchar(40) not null,
CAP int(5) not null,
dataOrdine date not null,
dataConsegna date,
stato varchar(20) not null,
metodoPagamento varchar(30) not null,
primary key (numero));

CREATE TABLE Libro(
autore int,
articolo int auto_increment,
titolo varchar(40) not null,
ISBN long not null,
anno int(4) not null,
pagine int not null,
editore varchar(30) not null,
descrizione varchar(1000) not null,
primary key(articolo),
foreign key(articolo) references Articolo(codice) on update cascade on delete cascade,
foreign key(autore) references Autore(codiceAutore) on update cascade on delete cascade);

CREATE TABLE Genere(
nome varchar(30) primary key);

CREATE TABLE GenLibro(
libro int,
genere varchar(30),
foreign key(libro) references Libro(articolo) on update cascade on delete cascade,
foreign key(genere) references Genere(nome) on update cascade on delete cascade,
primary key(libro, genere));

INSERT INTO Autore(nome, cognome) VALUES
("Jules", "Verne"),
("Joseph", "Conrad"),
("Andrzej", "Sapkowski"), 
("Mark", "Twain"),
("Robert Louis", "Stevenson"),
("Groucho", "Marx"),
("Italo", "Calvino"),
("Ermanno", "Cavazzoni"),
("J.R.R.", "Tolkien"),
("J.K.", "Rowling"),
("Stephen", "King"),
("Mary", "Shelley"),
("Bram", "Stoker"),
("Horace", "Walpole"),
("Aldous", "Huxley"),
("Ray", "Bradbury"),
("George", "Orwell"),
("Dmitrij", "Gluchovskij"),
("Donato", "Carrisi"),
("Gianrico", "Carofiglio"),
("Robert", "Galbraith"),
("Arthur Conan", "Doyle"),
("Shirley", "Jackson");

INSERT INTO Articolo(prezzo, valutazione, quantita, copertina, nome) VALUES
(9.49, 4, 30, "viaggioalcentrodellaterra.jpg", "Viaggio al centro della Terra"),
(9.49, 4, 30, "lalineadombra.jpg", "La linea d'ombra" ),
(9.49, 5, 30, "leavventureditomsawyer.jpg", "Le avventure di Tom Sawyer" ),
(9.49, 5, 30, "lisoladeltesoro.jpg", "L'isola del tesoro" ),
(7.99, 4, 30, "leletteredigrouchomarx.jpg", "Le lettere di Groucho Marx" ),
(8.49, 4, 40, "lecosmicomiche.jpg", "Le cosmicomiche" ),
(7.99, 3, 40, "ilpensatoresolitario.jpg", "Il pensatore solitario" ),
(9.49, 4, 30, "ilguardianodegliinnocenti.jpg", "Il guardiano degli innocenti" ),
(9.49, 4, 30, "ilbattesimodelfuoco.jpg", "Il battesimo del fuoco" ),
(9.49, 4, 30, "latorredellarondine.jpg", "La torre della rondine" ),
(35.99, 4, 30, "ilsignoredeglianelli.jpg", "Il signore degli anelli" ),
(9.49, 5, 30, "harrypotterelapietrafilosofalelibro.jpg", "Harry Potter e la pietra filosofale" ),
(9.49, 5, 20, "it.jpg", "It" ),
(9.99, 5, 20, "frankenstein.jpg", "Frankenstein" ),
(8.99, 3, 20, "lincubodihillhouse.jpg", "L'incubo di Hill House" ),
(8.99, 5, 20, "dracula.jpg", "Dracula" ),
(8.99, 3, 20, "ilcastellodiotranto.jpg", "Il castello di Otranto" ),
(8.99, 5, 20, "ilmondonuovo.jpg", "Il mondo nuovo" ),
(9.49, 5, 30, "fahrenheit451.jpg", "Fahrenheit 451" ),
(9.49, 5, 30, "1984.jpg", "1984" ),
(9.49, 5, 20, "cronachemarziane.jpg", "Cronache marziane" ),
(8.99, 4, 20, "metro2033.jpg", "Metro 2033" ),
(7.99, 4, 20, "lacasadellevoci.jpg", "La casa delle voci" ),
(7.99, 3, 20, "ladisciplinadipenelope.jpg", "La disciplina di Penelope" ),
(8.99, 5, 20, "sangueinquieto.jpg", "Sangue inquieto" ),
(8.99, 5, 20, "unostudioinrosso.jpg","Uno studio in rosso");

INSERT INTO Genere VALUES
("Avventura"), ("Commedia"), ("Fantasy"), ("Horror"), ("Sci-Fi"), ("Thriller");



INSERT INTO Libro(autore, titolo, ISBN, anno, pagine, editore, descrizione) VALUES
(1, "Viaggio al centro della Terra", 8809766013, 1874, 352, "Giunti Junior", "Viaggio al centro della Terra è un romanzo scientifico d'avventura del 1864 di Jules Verne. Appartenente al sottogenere della fantascienza sotterranea, in esso si narra del viaggio immaginario di uno scienziato tedesco e dei suoi collaboratori in un mondo sotto la superficie terrestre, che l'autore lascia intuire si trattasse di quello un tempo esistente alla luce del sole; per tale ragione è uno dei primi romanzi che si inscrive nel filone del mondo perduto." ),
(2, "La linea d'ombra", 8807901471, 1917, 192, "Feltrinelli", "La linea d'ombra (The Shadow Line: A Confession) è un romanzo di formazione breve dello scrittore Joseph Conrad, scritto alla fine del 1916 ma pubblicato nel 1917 per l'editore Joseph Malaby Dent." ),
(4, "Le avventure di Tom Sawyer", 8807902281, 1876, 295, "Feltrinelli", "Le avventure di Tom Sawyer è un romanzo per ragazzi dello scrittore statunitense Mark Twain pubblicato nel 1876; si tratta della prima di quattro opere collegate tra loro, la seconda delle quali, sorta di seguito ideale, è Le avventure di Huckleberry Finn, la terza e la quarta (molto meno note) Tom Sawyer Detective e Tom Sawyer Abroad."),
(5, "L'isola del tesoro", 8807901390, 1883, 287, "Feltrinelli", "L'isola del tesoro (Treasure Island, 1883), di Robert Louis Stevenson, è uno dei più celebri romanzi per ragazzi di tutti i tempi. Pubblicato per la prima volta a puntate nella rivista per ragazzi Young Folks negli anni 1881-1882 con il titolo di Sea Cook, or Treasure Island racconta una storia di pirati e tesori e ha certamente contribuito in modo significativo all'immaginario popolare su questi argomenti (a partire dallo stereotipo del pirata nella forma classica in cui appare, per esempio, in Peter Pan e Pirati dei Caraibi)." ),
(6, "Le lettere di Groucho Marx", 8845913015, 1967, 373, "Adelphi", "Le lettere di Groucho Marx è una raccolta di lettere dell'attore comico statunitense Groucho Marx. Le lettere furono donate nel 1964 alla Library of Congress e pubblicate nel 1967 a New York e Londra, successivamente tradotte in varie lingue; in Italia uscirono nel 1992 pubblicate da Adelphi."),
(7, "Le cosmicomiche", 8804667982, 1965, 200, "Mondadori", "Le Cosmicomiche è una raccolta di 12 racconti scritti da Italo Calvino tra il 1963 e il 1964, in origine pubblicati per la maggior parte sui periodici Il Caffè e Il Giorno tra il 1964 e 1965, successivamente ripubblicati sotto forma di raccolta da Einaudi nel 1965."),
(8, "Il pensatore solitario", 8823509566, 2015, 176, "Guanda", "Un esercito di pensionati in guerra con la Grecia, un eremita mancato perseguitato da Equitalia, un Dio distratto che crea l’universo per sbaglio, e poi un sindaco-madre, uno zio-vigna, un Poeta da Gabinetto... L’Italia di Cavazzoni è davvero un Paese delle meraviglie."),
(3, "Il guardiano degli innocenti", 8842932418, 1993, 372, "Nord", "Il guardiano degli innocenti è una raccolta di racconti scritta dallo scrittore polacco Andrzej Sapkowski. La prima edizione polacca è stata pubblicata nel 1993, quella inglese nel 2007 e quella italiana nel 2010. Il libro è stato tradotto in diverse altre lingue con una particolarità: l'autore ha sempre preteso che la traduzione venisse fatta direttamente dal polacco alla lingua di destinazione, senza l'intermediazione dell'inglese, per non perdere le particolarità e le sfumature della sua scrittura. L'opera è un'antologia di sette racconti di cui uno, La voce della ragione, funge da cornice narrativa."),
(3, "Il battesimo del fuoco", 8842922242, 1996, 468, "Nord", "Dopo i drammatici eventi dell'isola di Thanedd, Triss Merigold ha portato Geralt nella foresta del Brokilon, dove le guaritrici delle Driadi hanno soccorso lo strigo, gravemente ferito dopo lo scontro con il mago Vilgefortz. Geralt affronta una lunga degenza, durante la quale fa la conoscenza dell'arciera Milva, che da mesi aiuta i commando Scoia'tael a sfuggire alle grinfie delle milizie degli uomini."),
(3, "La torre della rondine", 8842932779, 1997, 516, "Nord", "Durante la notte dell'equinozio d'autunno, in tutto il mondo, accadono eventi soprannaturali e inspiegabili. Sogni, allucinazioni e incubi svegliano di soprassalto una moltitudine di persone, ma solo coloro che hanno conosciuto Ciri, la Leoncina di Cintra, arrivano a capire che qualcosa di terribile è successo alla Bambina del Sangue Antico. La ragazza, gravemente ferita, viene salvata dall'anziano eremita Vysogota di Corvo, che la trova accanto al suo cavallo nelle paludi che circondano la sua casupola solitaria."),
(9, "Il signore degli anelli", 8830104711, 1955, 1364, "Bompiani", "Il Signore degli Anelli è un romanzo high fantasy epico scritto da J. R. R. Tolkien e ambientato alla fine della Terza Era dell'immaginaria Terra di Mezzo. Scritto a più riprese tra il 1937 e il 1949, fu pubblicato in tre volumi tra il 1954 e il 1955 nonché tradotto in trentotto lingue ed ha venduto oltre 150 milioni di copie che lo rendono una delle opere letterarie di maggior successo del XX secolo."),
(10, "Harry Potter e la pietra filosofale", 8831003380, 1997, 310, "Salani", "Harry Potter e la pietra filosofale è il primo romanzo della saga high fantasy Harry Potter, scritta da J. K. Rowling e ambientata principalmente nell'immaginario Mondo magico durante gli anni novanta del XX secolo. Ideato proprio nei primi anni novanta, Harry Potter e la Pietra Filosofale fu pubblicato poi nel 1997. Tradotto in 77 lingue, tra cui il latino e il greco antico, resta una delle più popolari opere letterarie del XX secolo con una vendita globale di 120 milioni di copie."),
(11, "It", 8868365626, 1986, 1216, "Sperling & Kupfer", "It è un romanzo horror scritto da Stephen King e pubblicato nel 1986. It è una lunga e sinistra saga corale che si espande tra orrori inquietanti e drammi umani senza speranza, trattando i temi che in seguito diventeranno il simbolo dell'autore: la forza soverchiante della memoria, la profonda incisività dei traumi infantili, il prezzo della violenza occultata dietro una fragile maschera di felicità, la grettezza e la bassezza umana nascosta dietro le apparenze di una ridente e piccola cittadina."),
(12, "Frankenstein", 8807900718, 1818, 320, "Feltrinelli", "Uno spettro si aggira per l'immaginario collettivo, e questo spettro si chiama Frankenstein. Mary Shelley ha indubbiamente creato un capolavoro, ma anche una sorta di icona pop, divenuta proverbiale e versatile, tale da essere evocata nelle situazioni più impensate."),
(23, "L'incubo di Hill House", 8845930955, 1959, 233, "Adelphi", "L'incubo di Hill House è il più celebre romanzo della scrittrice statunitense Shirley Jackson, e uno dei più famosi racconti di fantasmi della letteratura del XX secolo. Travalicando i confini del genere horror, il romanzo della Jackson si avvicina per eleganza a capolavori della ghost story come Il giro di vite di Henry James. Autori come Ray Bradbury e Stephen King hanno riconosciuto la sua influenza."),
(13, "Dracula", 8807901838, 1897, 535, "Feltrinelli", "Dracula è un romanzo epistolare scritto dall'irlandese Bram Stoker nel 1897, ispirato alla figura di Vlad III, principe di Valacchia, ed è uno degli ultimi esempi di romanzi gotici. Riprendendo il mito del vampiro aristocratico, distante dall'immagine del vampiro presente nel folklore, lanciato nella letteratura da John William Polidori, Stoker realizza un romanzo dalle atmosfere cupe, in cui l'orrore e la minaccia assillano i protagonisti, in un crescendo di emozioni che conduce alla scoperta dell'orrore rappresentato dal tetro vampiro."),
(14, "Il castello di Otranto", 8895966775, 1764, 96, "Selino's", "Il castello di Otranto è un romanzo di Horace Walpole del 1764, considerato il primo romanzo gotico. Ambientata nella città salentina di Otranto, nell'Italia Meridionale, è l'opera che diede l'avvio al genere letterario poi diffusosi tra il tardo Settecento e l'inizio dell'Ottocento."),
(15, "Il mondo nuovo", 8804735821, 1932, 384, "Mondadori", "Il mondo nuovo è un romanzo di fantascienza di genere distopico scritto nel 1932 da Aldous Huxley. È il suo romanzo più famoso e ne sono stati tratti alcuni adattamenti televisivi. Il libro anticipa temi quali lo sviluppo delle tecnologie della riproduzione, l'eugenetica e il controllo mentale, usati per forgiare un nuovo modello di società, tratteggiando una distopia in cui l'uomo vive in un drammatico limbo esistenziale. Il ritratto tracciato dall'autore di questo mondo nuovo è freddo e distaccato, ma vi traspare una cinica esaltazione degli aspetti grotteschi del dramma, sui quali Huxley si sofferma."),
(16, "Fahrenheit 451", 8804665297, 1953, 177, "Mondadori", "Fahrenheit 451 è un romanzo di fantascienza del 1953, scritto da Ray Bradbury. Ambientato in un imprecisato futuro posteriore al 1960, vi si descrive una società distopica in cui leggere o possedere libri è considerato un reato, per contrastare il quale è stato istituito un apposito corpo di vigili del fuoco impegnato a bruciare ogni tipo di volume."),
(17, "1984", 8804668237, 1949, 333, "Mondadori", "1984 è uno dei più celebri romanzi di George Orwell, pubblicato nel 1949 ma iniziato a scrivere nel 1948 (anno da cui deriva il titolo, ottenuto appunto dall'inversione delle ultime due cifre). Le Monde lo posiziona al 22º posto della classifica dei 100 migliori libri mai scritti."),
(16, "Cronache marziane", 8804668169, 1950, 285, "Mondadori", "Cronache marziane è il resoconto della conquista e della colonizzazione di Marte tra il 1999 e il 2026: anno in cui lo scoppio di una guerra atomica richiama i terrestri sul proprio pianeta. Marte, pianeta antichissimo, resta nuovamente abbandonato. Sui suoi immensi mari di sabbia privi di vita passano i grandi velieri degli ultimi marziani, creature simili a fantasmi, ombre e larve di una civiltà che i terrestri, esseri ingombranti venuti da un mondo sordo e materialista, non hanno saputo vedere né comprendere."),
(18, "Metro 2033", 8863550972, 2005, 779, "MPlayer", "L'anno è il 2033. Il mondo è ridotto ad un cumulo di macerie. L'umanità è vicina all'estinzione. Le città mezze distrutte sono diventate inagibili a causa delle radiazioni. Al di fuori dei loro confini, si dice, solo deserti e foreste bruciate. I sopravvissuti ancora narrano la passata grandezza dell'umanità. Ma gli ultimi barlumi della civiltà fanno già parte di una memoria lontana, a cavallo tra realtà e mito. L'uomo è stato sostituito da altre forme di vita, mutate dalle radiazioni e più idonee a vivere nella nuova arida terra. Il tempo dell'uomo è finito. Poche migliaia di esseri umani sopravvivono ignorando il destino degli altri."),
(19, "La casa delle voci", 8850260202, 2019, 400, "TEA", "Pietro Gerber non è uno psicologo come gli altri. La sua specializzazione è l'ipnosi e i suoi pazienti hanno una cosa in comune: sono bambini. Spesso traumatizzati, segnati da eventi drammatici o in possesso di informazioni importanti sepolte nella loro fragile memoria, di cui polizia e magistrati si servono per le indagini. Pietro è il migliore di tutta Firenze, dove è conosciuto come l'addormentatore di bambini. Ma quando riceve una telefonata dall'altro capo del mondo da parte di una collega australiana che gli raccomanda una paziente, Pietro reagisce con perplessità e diffidenza."),
(20, "La disciplina di Penelope", 8804726733, 2021, 192, "Mondadori","Penelope si sveglia nella casa di uno sconosciuto, dopo l'ennesima notte sprecata. Va via silenziosa e solitaria, attraverso le strade livide dell'autunno milanese. Faceva il pubblico ministero, poi un misterioso incidente ha messo drammaticamente fine alla sua carriera. Un giorno si presenta da lei un uomo che è stato indagato per l'omicidio della moglie. Il procedimento si è concluso con l'archiviazione ma non ha cancellato i terribili sospetti da cui era sorto. L'uomo le chiede di occuparsi del caso, per recuperare l'onore perduto, per sapere cosa rispondere alla sua bambina quando, diventata grande, chiederà della madre. Penelope, dopo un iniziale rifiuto, si lascia convincere dall'insistenza di un suo vecchio amico, cronista di nera. Comincia così un'appassionante investigazione che si snoda fra vie sconosciute della città e ricordi di una vita che non torna."),
(21, "Sangue inquieto", 8831005928, 2020, 1104, "Salani", "Il nuovo caso arriva nelle mani di Cormoran Strike in una buia serata d'agosto, davanti al mare della Cornovaglia, mentre è fuori servizio e sta cercando una scusa per telefonare a Robin, la sua socia. In quel momento tutto desidera tranne che parlare con una sconosciuta che gli chiede di indagare sulla scomparsa della madre, Margot Bamborough, avvenuta per giunta quarant'anni prima. Un cold case più complesso del previsto, con un serial killer tra i piedi e un'indagine della polizia a suo tempo molto controversa, fra predizioni dei tarocchi, testimoni sfuggenti e piste scuramente intrecciate."),
(22, "Uno studio in rosso", 8807901655, 1887, 176, "Feltrinelli", "In una disabitata casa nel cuore di Londra viene rinvenuto un corpo. Nessuna ferita, solo alcune macchie di sangue sul pavimento. Per Scotland Yard il delitto è inspiegabile. C'è un'unica una persona in grado di risolvere il mistero: l'insopportabile ma celeberrimo investigatore privato Sherlock Holmes. A lui basta osservare la scena del crimine per annusare le tracce dell'assassino: la sua mente logico-matematica è infallibile. Così, in compagnia del fedele collaboratore, il dottor Watson, Holmes si prepara a risolvere un caso pieno di emozionanti colpi di scena.");

INSERT INTO GenLibro(libro, genere) VALUES
(1, "Avventura"),
(2, "Avventura"),
(3, "Avventura"),
(4, "Avventura"),
(5, "Commedia"),
(6, "Commedia"),
(7, "Commedia"),
(8, "Fantasy"),
(9, "Fantasy"),
(10, "Fantasy"),
(11, "Fantasy"),
(12, "Fantasy"),
(13, "Horror"),
(14, "Horror"),
(15, "Horror"),
(16, "Horror"),
(17, "Horror"),
(18, "Sci-Fi"),
(19, "Sci-Fi"),
(20, "Sci-Fi"),
(21, "Sci-Fi"),
(22, "Sci-Fi"),
(23, "Thriller"),
(24, "Thriller"),
(25, "Thriller"),
(26, "Thriller");