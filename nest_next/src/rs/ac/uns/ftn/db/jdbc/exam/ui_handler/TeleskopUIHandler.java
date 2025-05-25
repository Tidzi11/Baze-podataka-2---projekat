package rs.ac.uns.ftn.db.jdbc.exam.ui_handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.model.Teleskop;
import rs.ac.uns.ftn.db.jdbc.exam.service.TeleskopService;

public class TeleskopUIHandler {
	
private static final TeleskopService TeleskopService = new TeleskopService();
	
	public void handleTeleskopMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad teleskopima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jednog teleskopa");
			System.out.println("4 - Unos vise teleskopa");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja teleskopima");

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
			case "6":
				handleDelete();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void showAll() {
		//System.out.println(Teleskop.getFormattedHeader());

		try {
			for (Teleskop th : TeleskopService.getAll()) {
				System.out.println(th);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void showById() {
		System.out.println("ID teleskopa: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			Teleskop Teleskop = TeleskopService.getById(id);
			//System.out.println(Teleskop.getFormattedHeader());
			System.out.println(Teleskop);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleSingleInsert() {
		System.out.println("ID teleskopa: ");
		int tel_idtel = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("Naziv: ");
		String tel_naz = MainUIHandler.sc.nextLine();

		System.out.println("Tip: ");
		String tel_type = MainUIHandler.sc.nextLine();

		System.out.println("Precnik: ");
		Float tel_prec = Float.parseFloat(MainUIHandler.sc.nextLine());
		
		System.out.println("Kvalitet slike: ");
		Float tel_kvalsl = Float.parseFloat(MainUIHandler.sc.nextLine());
		
		System.out.println("Domet: ");
		Float tel_dmt = Float.parseFloat(MainUIHandler.sc.nextLine());
		
		System.out.println("Rezolucija: ");
		Float tel_rezl = Float.parseFloat(MainUIHandler.sc.nextLine());
		

		
		try {
			TeleskopService.save(new Teleskop(tel_idtel, tel_naz, tel_type, tel_prec, tel_kvalsl, tel_dmt, tel_rezl));
			System.out.println("Dodavanje uspesno.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleUpdate() {
		System.out.println("ID teleskopa: ");
		int tel_idtel = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			if (!TeleskopService.existsById(tel_idtel)) {
				System.out.println("Uneta vrednost ne postoji!");
				return;
			}

			System.out.println("Naziv: ");
			String tel_naz = MainUIHandler.sc.nextLine();

			System.out.println("Tip: ");
			String tel_type = MainUIHandler.sc.nextLine();

			System.out.println("Precnik: ");
			Float tel_prec = Float.parseFloat(MainUIHandler.sc.nextLine());
			
			System.out.println("Kvalitet slike: ");
			Float tel_kvalsl = Float.parseFloat(MainUIHandler.sc.nextLine());
			
			System.out.println("Domet: ");
			Float tel_dmt = Float.parseFloat(MainUIHandler.sc.nextLine());
			
			System.out.println("Rezolucija: ");
			Float tel_rezl = Float.parseFloat(MainUIHandler.sc.nextLine());
			
			boolean success = TeleskopService.save(new Teleskop(tel_idtel, tel_naz, tel_type, tel_prec, tel_kvalsl, tel_dmt, tel_rezl));
			if (success) {
				System.out.println("Teleskop uspesno izmenjen.");
			} else {
				System.out.println("Izmena teleskopa nije uspela.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleDelete() {
		System.out.println("ID teleskopa: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		try {
			boolean success = TeleskopService.deleteById(id);
			if (success) {
				System.out.println("Teleskop uspesno obrisan.");
			} else {
				System.out.println("Brisanje teleskopa nije uspelo.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleMultipleInserts() {
		List<Teleskop> TeleskopList = new ArrayList<>();
		String answer;
		do {
			System.out.println("ID teleskopa: ");
			int tel_idtel = Integer.parseInt(MainUIHandler.sc.nextLine());

			System.out.println("Naziv: ");
			String tel_naz = MainUIHandler.sc.nextLine();

			System.out.println("Tip: ");
			String tel_type = MainUIHandler.sc.nextLine();

			System.out.println("Precnik: ");
			Float tel_prec = Float.parseFloat(MainUIHandler.sc.nextLine());
			
			System.out.println("Kvalitet slike: ");
			Float tel_kvalsl = Float.parseFloat(MainUIHandler.sc.nextLine());
			
			System.out.println("Domet: ");
			Float tel_dmt = Float.parseFloat(MainUIHandler.sc.nextLine());
			
			System.out.println("Rezolucija: ");
			Float tel_rezl = Float.parseFloat(MainUIHandler.sc.nextLine());
			
			TeleskopList.add(new Teleskop(tel_idtel, tel_naz, tel_type, tel_prec, tel_kvalsl, tel_dmt, tel_rezl));

			System.out.println("Prekinuti unos? X za potvrdu");
			answer = MainUIHandler.sc.nextLine();
		} while (!answer.equalsIgnoreCase("X"));

		try {
			int rowsSaved = TeleskopService.saveAll(TeleskopList);
			System.out.println("Uspesno a�urirano " + rowsSaved + " teleskopa.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
