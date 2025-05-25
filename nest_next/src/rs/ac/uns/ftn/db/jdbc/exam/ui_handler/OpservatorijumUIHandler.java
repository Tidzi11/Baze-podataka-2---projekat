package rs.ac.uns.ftn.db.jdbc.exam.ui_handler;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.model.Opservatorijum;
import rs.ac.uns.ftn.db.jdbc.exam.service.OpservatorijumService;

public class OpservatorijumUIHandler {
	
private static final OpservatorijumService opservatorijumService = new OpservatorijumService();
	
	public void handleOpservatorijumMenu() throws ParseException {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad opservatorijumima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jednog opservatorijuma");
			System.out.println("4 - Unos vise opservatorijuma");
			System.out.println("5 - Izmena po identifikatoru");
			//System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja opservatorijumima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				handleSingleInsert();
				break;
			case "4":
				handleMultipleInserts();
				break;
			case "5":
				handleUpdate();
				break;
			//case "6":
				//handleDelete();
				//break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void showAll() {
		//System.out.println(Opservatorijum.getFormattedHeader());

		try {
			for (Opservatorijum th : opservatorijumService.getAll()) {
				System.out.println(th);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void showById() {
		System.out.println("ID opservatorijuma: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			Opservatorijum opservatorijum = opservatorijumService.getById(id);
			//System.out.println(Opservatorijum.getFormattedHeader());
			System.out.println(opservatorijum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleSingleInsert() throws ParseException {
		System.out.println("ID opservatorijuma: ");
		int idops = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("Naziv: ");
		String naz = MainUIHandler.sc.nextLine();

		System.out.println("ID lokacije: ");
		int lokacija_idlok = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("Vlasnik: ");
		String vlas = MainUIHandler.sc.nextLine();

		System.out.println("Datum osnivanja: ");
		//Date dat = (Date) MainUIHandler.sc.nextLine(); //?
		Date dat = (Date) new SimpleDateFormat("dd.MM.yyyy.").parse(MainUIHandler.sc.nextLine());

		System.out.println("Nadmorska visina: ");
		float vis = Float.parseFloat(MainUIHandler.sc.nextLine());


		try {
			opservatorijumService.save(new Opservatorijum(idops, naz, lokacija_idlok, vlas, dat, vis));
			System.out.println("Dodavanje uspesno.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleUpdate() throws ParseException {
		System.out.println("ID opservatorijuma: ");
		int idops = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			if (!opservatorijumService.existsById(idops)) {
				System.out.println("Uneta vrednost ne postoji!");
				return;
			}

			System.out.println("Naziv: ");
			String naz = MainUIHandler.sc.nextLine();

			System.out.println("ID lokacije: ");
			int lokacija_idlok = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Vlasnik: ");
			String vlas = MainUIHandler.sc.nextLine();

			System.out.println("Datum osnivanja: ");
			//Date dat = (Date) MainUIHandler.sc.nextLine(); //?
			Date dat = (Date) new SimpleDateFormat("dd.mm.yyyy.").parse(MainUIHandler.sc.nextLine());

			System.out.println("Nadmorska visina: ");
			float vis = Float.parseFloat(MainUIHandler.sc.nextLine());

			boolean success = opservatorijumService.save(new Opservatorijum(idops, naz, lokacija_idlok, vlas, dat, vis));
			if (success) {
				System.out.println("Opservatorijum je uspesno izmenjen.");
			} else {
				System.out.println("Izmena opservatorijuma nije uspela.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleDelete() {
		System.out.println("ID opservatorijuma: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			boolean success = opservatorijumService.deleteById(id);
			if (success) {
				System.out.println("Opservatorijum je uspesno obrisan.");
			} else {
				System.out.println("Brisanje opservatorijuma nije uspelo.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleMultipleInserts() throws ParseException {
		List<Opservatorijum> OpservatorijumList = new ArrayList<>();
		String answer;
		do {
			System.out.println("ID opservatorijuma: ");
			int idops = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Naziv: ");
			String naz = MainUIHandler.sc.nextLine();

			System.out.println("ID lokacije: ");
			int lokacija_idlok = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Vlasnik: ");
			String vlas = MainUIHandler.sc.nextLine();

			System.out.println("Datum osnivanja: ");
			//Date dat = (Date) MainUIHandler.sc.nextLine(); //?
			Date dat = (Date) new SimpleDateFormat("dd.mm.yyyy.").parse(MainUIHandler.sc.nextLine());

			System.out.println("Nadmorska visina: ");
			float vis = Float.parseFloat(MainUIHandler.sc.nextLine());

			OpservatorijumList.add(new Opservatorijum(idops, naz, lokacija_idlok, vlas, dat, vis));
			
			System.out.println("Prekinuti unos? X za potvrdu");
			answer = MainUIHandler.sc.nextLine();
		} while (!answer.equalsIgnoreCase("X"));

		try {
			int rowsSaved = opservatorijumService.saveAll(OpservatorijumList);
			System.out.println("Uspesno a�urirano " + rowsSaved + " opservatorijuma.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
