package ie.gmit.sw;

import java.util.Map;
import java.util.Scanner;

public class Runner{

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Throwable {
		Menu m = new Menu();
		m.displayMenu();

		Scanner console = new Scanner(System.in);
		String textData;
		String queryFile;
		int ngram;

		do {
			ngram = console.nextInt();
		} while (ngram < 1 && ngram > 4);// if ngram is out of range(1-5) then keep asking for another number
		m.enterDataLocation();
		textData = console.next();
		try {
			Database db = new Database();
			Parser p = new Parser(textData, ngram);
			p.setDb(db);

			Thread t1 = new Thread(p);
			t1.start();
			t1.join();
			m.enterQueryFile();
			queryFile = console.next();
			Map<Integer, LanguageEntry> q = p.queryParse(ngram, queryFile);
			m.resultOutput(db, q);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
		//Irish
		//Sinne Fianna Fáil, atá faoi gheall ag Éirinn, Buíon dár slua thar toinn do ráinig chugainn, Faoi mhóid bheith saor, Seantír ár sinsear feasta, Ní fhágfar faoin tíorán ná faoin tráill. Anocht a théam sa bhearna bhaoil, Le gean ar Ghaeil, chun báis nó saoil, Le gunna-scréach faoi lámhach na bpiléar, Seo libh canaig' amhrán na bhFiann.
		//Swedish
		//Du gamla, Du fria, Du fjällhöga nord Du tysta, Du glädjerika sköna! Jag hälsar Dig, vänaste land uppå jord, Din sol, Din himmel, Dina ängder gröna. Du tronar på minnen från fornstora dar, då ärat Ditt namn flög över jorden. Jag vet att Du är och förblir vad du var. Ja, jag vill leva jag vill dö i Norden.
		//Russian
		//Лучше один раз увидеть, чем сто раз услышать.
		//Japanese
		//お会いできて うれしいです。
