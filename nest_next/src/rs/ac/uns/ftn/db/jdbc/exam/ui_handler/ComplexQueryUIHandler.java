package rs.ac.uns.ftn.db.jdbc.exam.ui_handler;
import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dto.AkcijaDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.PosmatranjaZaNebObj;
import rs.ac.uns.ftn.db.jdbc.exam.dto.PosmatranjePoNebeskomObjektuDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.TeleskopIzvestajDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Akcija;
import rs.ac.uns.ftn.db.jdbc.exam.model.NebeskiObjekat;
import rs.ac.uns.ftn.db.jdbc.exam.model.Posmatranje;
import rs.ac.uns.ftn.db.jdbc.exam.service.ComplexFuncionalityService;


public class ComplexQueryUIHandler {
	
		private static final ComplexFuncionalityService complexQueryService = new ComplexFuncionalityService();

	    public void handleComplexQueryMenu() throws SQLException {
	        String answer;
	        do {
	            System.out.println("\nOdaberite kompleksni upit:");
	            
	            System.out.println("1 - Jednostavan upit 1 - Za svako nebesko telo prikazati posmatranja koja su radjena. Ukoliko nebesko telo nema posmatranje ispisati: NEMA POSMATRANJE");
	            System.out.println("2 - Kompleksan upit 1  - Izvestaj o broju posmatranja teleskopa po opservatorijumu i prosecno trajanje posmatranja");
	            System.out.println("3 - Kompleksan upit 2  - Izveštaj o broju posmatranja po nebeskom objektu i opservatorijumu");
	            System.out.println("4 - Transakcija        - brisanje akcija koje nemaju zabelezene vremenske uslove, pa samim ti i celo to posmatranje i sve njegove druge akcije");
	            
	            System.out.println("X - Povratak na glavni meni");

	            answer = MainUIHandler.sc.nextLine();

	            switch (answer) {
	                case "1":
	                	prikazatiPosmatranjaZaNebeskiObjekat();
	                    break;
	                case "2":
	                	prikazatiIzvestajOBrojPosmatranjaTeleskopa();
	                    break;
	                case "3":
	                	prikazatiPosmatranjaPoNebeskomObjektu();
	                    break;
	                case "4":
	                    prikazatiAkcijeZaBrisanje();
	                    break;
                  
	            }
	        } while (!answer.equalsIgnoreCase("X"));
	    }
	    

	    


	    private void prikazatiPosmatranjaPoNebeskomObjektu() throws SQLException {
	        System.out.println("Izveštaj o broju posmatranja po nebeskom objektu i opservatorijumu:");
	        System.out.println("--------------------------------------------------------");
	        
	        List<PosmatranjePoNebeskomObjektuDTO> dtos = complexQueryService.getPosmatranjaPoNebeskomObjektu();
	        if (!dtos.isEmpty()) {
	            for (PosmatranjePoNebeskomObjektuDTO dto : dtos) {
	                System.out.println(dto);
	            }
	        } else {
	            System.out.println("\t\tNema podataka.");
	        }
	    }





		private void prikazatiIzvestajOBrojPosmatranjaTeleskopa() throws SQLException {
			List<TeleskopIzvestajDTO> izvestaji = complexQueryService.dobaviTeleskopIzvestaj();
		    if (izvestaji.isEmpty()) {
		        System.out.println("Nema podataka za izveštaj.");
		    } else {
		        System.out.println("Izveštaj o broju posmatranja teleskopa po opservatorijumu:");
		        System.out.println("--------------------------------------------------------");
		        // Ispisivanje zaglavlja
		        //System.out.println(TeleskopIzvestajDTO.getFormattedHeader());
		        
		        for (TeleskopIzvestajDTO dto : izvestaji) {
		            System.out.println(dto); // Pretpostavljamo da je u DTO-u odgovarajući `toString()` metod
		        }
		    }
		}





		private void prikazatiPosmatranjaZaNebeskiObjekat() throws SQLException {
	    	//System.out.println(NebeskiObjekat.getFormattedHeader());

			List<PosmatranjaZaNebObj> dtos = complexQueryService.getPosmatranjaZaNebObj();
			if (!dtos.isEmpty()) {
				for (PosmatranjaZaNebObj dto : dtos) {
					System.out.println(dto.getNebeskiObjekat());
					System.out.println("\t\t------------------------------- POSMATRANJA -------------------------------");

					if (!dto.getPosmatranja().isEmpty()) {
						System.out.println("\t\t");
						for (Posmatranje pos : dto.getPosmatranja()) {
							System.out.println("\t\t" + pos);
						}
					} else {
						System.out.println("\t\tNEMA POSMATRANJA");
					}
					System.out.println("\t\t---------------------------------------------------------------------");
					System.out.println();
				}
			} else {
				System.out.println("\t\tNEMA NEBESKOG OBJEKTA.");
			}
		}

		


		private void prikazatiAkcijeZaBrisanje() throws SQLException {
	    	List<AkcijaDTO> forDeleting = complexQueryService.deleteAkcije();
			if (forDeleting.isEmpty()) {
				System.out.println("Nema akcija za brisanje.");
			} else {
				for (AkcijaDTO pd : forDeleting) {
					System.out.println(AkcijaDTO.getFormattedHeader());
					System.out.println(pd);					
				}
				System.out.println("--------------------Nakon brisanja:---------------------");
				System.out.println(Akcija.getFormattedHeader());
				for (Akcija p : complexQueryService.getAllAkcije()) {
					System.out.println(p);
				}
			}
		}

	

}
