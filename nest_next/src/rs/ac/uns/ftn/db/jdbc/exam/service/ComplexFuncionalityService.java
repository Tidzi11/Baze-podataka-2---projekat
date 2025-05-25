package rs.ac.uns.ftn.db.jdbc.exam.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.exam.dao.AkcijaDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.AstronomDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.NebeskiObjekatDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.OpservatorijumDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.PosmatranjeDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.TeleskopDAO;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.AkcijaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.AstronomDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.NebeskiObjekatDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.OpservatorijumDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.PosmatranjeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.dao.impl.TeleskopDAOImpl;
import rs.ac.uns.ftn.db.jdbc.exam.dto.AkcijaDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.PosmatranjaZaNebObj;
import rs.ac.uns.ftn.db.jdbc.exam.dto.PosmatranjePoNebeskomObjektuDTO;
import rs.ac.uns.ftn.db.jdbc.exam.dto.TeleskopIzvestajDTO;
import rs.ac.uns.ftn.db.jdbc.exam.model.Akcija;
import rs.ac.uns.ftn.db.jdbc.exam.model.NebeskiObjekat;

public class ComplexFuncionalityService {
	
	private static final AkcijaDAO akcijaDAO = new AkcijaDAOImpl();
	private static final AstronomDAO astronoomDAO = new AstronomDAOImpl();
	private static final NebeskiObjekatDAO nebeskiObjekatDAO = new NebeskiObjekatDAOImpl();
	private static final OpservatorijumDAO opservatorijumDAO = new OpservatorijumDAOImpl();
	private static final PosmatranjeDAO posmatranjeDAO = new PosmatranjeDAOImpl();
	private static final TeleskopDAO teleskopDAO = new TeleskopDAOImpl();
	
	
	

	public List<PosmatranjaZaNebObj> getPosmatranjaZaNebObj() throws SQLException {
		List<PosmatranjaZaNebObj> ret = new ArrayList<PosmatranjaZaNebObj>();
		try {
			for(NebeskiObjekat no: nebeskiObjekatDAO.findAll()) {
				PosmatranjaZaNebObj dto = new PosmatranjaZaNebObj();
				dto.setNebeskiObjekat(no);
				dto.setPosmatranja(posmatranjeDAO.findPosmatranjeByNebeskiObjekat(no.getIdnebobj()));
				ret.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}




	public List<AkcijaDTO> deleteAkcije() throws SQLException {
		return akcijaDAO.deleteNullVremenskeUslove();
	}




	public List<Akcija> getAllAkcije() {
	    List<Akcija> akcijeList = new ArrayList<>();
	    try {
	        // Pozivanje metode findAll iz AkcijaDAO
	        for (Akcija akcija : akcijaDAO.findAll()) {
	            akcijeList.add(akcija);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error fetching all Akcije", e);
	    }
	    return akcijeList;
	}
	
	public List<TeleskopIzvestajDTO> dobaviTeleskopIzvestaj() {
        return teleskopDAO.fetchTeleskopIzvestaj();
    }





	public List<PosmatranjePoNebeskomObjektuDTO> getPosmatranjaPoNebeskomObjektu() throws SQLException{
		return posmatranjeDAO.getPosmatranja();
	}

}
