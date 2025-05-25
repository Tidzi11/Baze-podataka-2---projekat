package rs.ac.uns.ftn.db.jdbc.exam.ui_handler;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import rs.ac.uns.ftn.db.jdbc.exam.service.AstronomService;

public class MainUIHandler {
	
	public static Scanner sc = new Scanner(System.in);

	private final AstronomUIHandler astronomUIHandler = new AstronomUIHandler();
	private final OpservatorijumUIHandler opservatorijumUIHandler = new OpservatorijumUIHandler();
	private final TeleskopUIHandler teleskopUIHandler = new TeleskopUIHandler();
	private final ComplexQueryUIHandler complexQueryUIHandler = new ComplexQueryUIHandler();

	public void handleMainMenu() throws ParseException, SQLException {

		String answer;
		do {
			System.out.println("\nOdaberite opciju:");
			System.out.println("1 - Rukovanje astronomima");
			System.out.println("2 - Rukovanje opservatorijumima");
			System.out.println("3 - Rukovanje teleskopima");
			System.out.println("4 - Kompleksni upiti");
			System.out.println("X - Izlazak iz programa");

			answer = sc.nextLine();

			switch (answer) {
			case "1":
				astronomUIHandler.handleAstronomMenu();
				break;
			case "2":
				opservatorijumUIHandler.handleOpservatorijumMenu();
				break;
			case "3":
				teleskopUIHandler.handleTeleskopMenu();
				break;
			case "4":
				complexQueryUIHandler.handleComplexQueryMenu();
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));

		sc.close();
	}
}
