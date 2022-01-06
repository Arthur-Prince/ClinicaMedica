package testeAplicacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import aplicacao.AgendaDAO;
import aplicacao.MedicoDAO;
import modelosDeDados.Agenda;
import modelosDeDados.Medico;

@FixMethodOrder
public class TestaAgenda {
	
	@BeforeClass
	public static void addMedico() {
		
		MedicoDAO dao = new MedicoDAO();
		
		Medico med = dao.busca("A000000");
		Medico med2 = dao.busca("B000000");
		if(med == null) {
			med = new Medico();
			med.setCRM("A000000");
			med.setNome("0");
			med.setTelefone("0");
			dao.add(med);
		}
		if(med2==null) {
			med2 = new Medico();
			med2.setCRM("B000000");
			med2.setNome("0");
			med2.setTelefone("0");
			dao.add(med2);
		}
	}
	
	

	@Test
	public void testaAdd() {
		Agenda Agenda = new Agenda();
		Agenda.setDiaSemana("terca");
		Agenda.setHoraInit(new Date(1, 1, 1));
		Agenda.setHoraFim(new Date(2021, 12, 31));
		Agenda.setCRMMedico("A000000");
		
		Agenda Agenda2 = new Agenda();
		Agenda2.setDiaSemana("segunda");
		Agenda2.setHoraInit(new Date(2021, 1, 1, 0, 0));
		Agenda2.setHoraFim(new Date(2021, 12, 31, 23, 59));
		Agenda2.setCRMMedico("B000000");

		AgendaDAO dao = new AgendaDAO();
		dao.add(Agenda);
		dao.add(Agenda2);
		
		List<Agenda> l = dao.busca("A000000");
		assertEquals(l.get(l.size() - 1).getHoraInit(), Agenda.getHoraInit());
	}
	@Test
	public void testaUpdate() {
		AgendaDAO dao = new AgendaDAO();
		List<Agenda> l = dao.busca("A000000");
		Agenda ag = l.get(l.size()-1);
		ag.setDiaSemana("quarta");
		ag.setHoraInit(new Date(1900, 1, 1));
		ag.setHoraFim(new Date(121, 12, 31));
		
		dao.update(ag.getIdAgenda(), ag);
		
		l = dao.busca("A000000");
		Agenda ag2 = l.get(l.size()-1);
		
		boolean teste = (ag.getDiaSemana().equals(ag2.getDiaSemana())) &&
				(ag.getHoraInit().getTime() == ag2.getHoraInit().getTime()) &&
				(ag.getHoraFim().getTime() == ag2.getHoraFim().getTime());

		assertTrue(teste);
	}
	

	@Test
	public void TestaBusca() {
		AgendaDAO dao = new AgendaDAO();
		List<Agenda> l = dao.busca("A000000");
		assertNotEquals(l.get(l.size() - 1), null);
		

	}

	@Test
	public void testaDeletePorID() {

		AgendaDAO dao = new AgendaDAO();

		List<Agenda> l = dao.busca("A000000");

		List<Integer> codDeletados = new LinkedList<Integer>();
		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			Agenda Agenda = (Agenda) iterator.next();
			codDeletados.add(Agenda.getIdAgenda());

		}
		for (Iterator iterator = codDeletados.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			dao.delete(integer.intValue());
		}

		l = dao.busca("A000000");
		assertEquals(l.size(), 0);
	}
	
	@Test
	public void testaDeletePorCRM() {

		AgendaDAO dao = new AgendaDAO();
		
		dao.delete("B000000");
		
		List<Agenda> l = dao.busca("B000000");
		assertEquals(l.size(), 0);
	}
	
	@AfterClass
	public static void deletaMedicos() {
		MedicoDAO dao = new MedicoDAO();
		
		dao.delete("B000000");
		dao.delete("A000000");
	}
	
}
