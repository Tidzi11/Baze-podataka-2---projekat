package rs.ac.uns.ftn.db.jdbc.exam.ui_handler;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.model.Astronom;
import rs.ac.uns.ftn.db.jdbc.exam.service.AstronomService;

public class AstronomUIHandler {
private static final AstronomService astronomService = new AstronomService();
	
	public void handleAstronomMenu() throws ParseException {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad astronomima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jednog astronoma");
			System.out.println("4 - Unos vise astronoma");
			System.out.println("5 - Izmena po identifikatoru");
			//System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja astronomima");

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
		//System.out.println(Astronom.getFormattedHeader());

		try {
			for (Astronom th : astronomService.getAll()) {
				System.out.println(th);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void showById() {
		System.out.println("ID astronoma: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			Astronom astronom = astronomService.getById(id);
			//System.out.println(Astronom.getFormattedHeader());
			System.out.println(astronom);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleSingleInsert() throws ParseException {
		System.out.println("ID astronoma: ");
		int idast = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("Ime: ");
		String ime = MainUIHandler.sc.nextLine();

		System.out.println("Prezime: ");
		String prz = MainUIHandler.sc.nextLine();

		System.out.println("Specijalizacija: ");
		String spec = MainUIHandler.sc.nextLine();
		
		System.out.println("Godina rodjenja: ");
		Date god = (Date) new SimpleDateFormat("dd.MM.yyyy.").parse(MainUIHandler.sc.nextLine());

		System.out.println("Godine iskustva: ");
		int isk = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("Email: ");
		String eml = MainUIHandler.sc.nextLine();
		
		System.out.println("Broj telefona: ");
		String tel = MainUIHandler.sc.nextLine();

		
		try {
			astronomService.save(new Astronom(idast, ime, prz, spec, god, isk, eml, tel));
			System.out.println("Dodavanje uspesno.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleUpdate() throws ParseException, NumberFormatException  {
		System.out.println("ID astronoma: ");
		int idast = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			if (!astronomService.existsById(idast)) {
				System.out.println("Uneta vrednost ne postoji!");
				return;
			}

			System.out.println("Ime: ");
			String ime = MainUIHandler.sc.nextLine();

			System.out.println("Prezime: ");
			String prz = MainUIHandler.sc.nextLine();

			System.out.println("Specijalizacija: ");
			String spec = MainUIHandler.sc.nextLine();
			
			System.out.println("Godina rodjenja: ");
			Date god = (Date) new SimpleDateFormat("dd.mm.yyyy.").parse(MainUIHandler.sc.nextLine());

			System.out.println("Godine iskustva: ");
			int isk = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Email: ");
			String eml = MainUIHandler.sc.nextLine();
			
			System.out.println("Broj telefona: ");
			String tel = MainUIHandler.sc.nextLine();
			
			boolean success = astronomService.save(new Astronom(idast, ime, prz, spec, god, isk, eml, tel));
			if (success) {
				System.out.println("Astronom je uspesno izmenjen.");
			} else {
				System.out.println("Izmena astronoma nije uspela.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleDelete() {
		System.out.println("ID astronoma: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			boolean success = astronomService.deleteById(id);
			if (success) {
				System.out.println("Astronom je uspesno obrisan.");
			} else {
				System.out.println("Brisanje astronoma nije uspelo.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleMultipleInserts() throws ParseException {
		List<Astronom> AstronomList = new ArrayList<>();
		String answer;
		do {
			System.out.println("ID astronoma: ");
			int idast = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Ime: ");
			String ime = MainUIHandler.sc.nextLine();

			System.out.println("Prezime: ");
			String prz = MainUIHandler.sc.nextLine();

			System.out.println("Specijalizacija: ");
			String spec = MainUIHandler.sc.nextLine();
			
			System.out.println("Godina rodjenja: ");
			Date god = (Date) new SimpleDateFormat("dd.mm.yyyy.").parse(MainUIHandler.sc.nextLine());

			System.out.println("Godine iskustva: ");
			int isk = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Email: ");
			String eml = MainUIHandler.sc.nextLine();
			
			System.out.println("Broj telefona: ");
			String tel = MainUIHandler.sc.nextLine();
			
			AstronomList.add(new Astronom(idast, ime, prz, spec, god, isk, eml, tel));
			
			System.out.println("Prekinuti unos? X za potvrdu");
			answer = MainUIHandler.sc.nextLine();
		} while (!answer.equalsIgnoreCase("X"));

		try {
			int rowsSaved = astronomService.saveAll(AstronomList);
			System.out.println("Uspesno a�urirano " + rowsSaved + " astronoma.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
