package testeAplicacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aplicacao.EspecialidadeDAO;
import aplicacao.MedicoDAO;
import modelosDeDados.Especialidade;
import modelosDeDados.Medico;

public class TestaEspecialidade {

	@BeforeClass
	public static void addMedico() {

		MedicoDAO dao = new MedicoDAO();
		EspecialidadeDAO dao2 = new EspecialidadeDAO();

		
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
		
		Especialidade esp = new Especialidade();
		esp.setNome("2");
		esp.setIndice(2);
		dao2.add(esp);
		
		Especialidade esp2 = new Especialidade();
		esp2.setNome("1");
		esp2.setIndice(1);
		dao2.add(esp2);
		
		dao.addEspecialidade("A000000", "1");
		dao.addEspecialidade("A000000", "2");
		dao.addEspecialidade("B000000", "1");

	}

	@Test
	public void TestaAdd() {
		Especialidade esp = new Especialidade();
		esp.setNome("0");
		esp.setIndice(0);

		EspecialidadeDAO dao = new EspecialidadeDAO();
		dao.add(esp);
		Especialidade l = dao.busca("0");
		assertEquals(l.getIndice(), esp.getIndice());

	}

	@Test
	public void TestaBusca() {
		EspecialidadeDAO dao = new EspecialidadeDAO();
		Especialidade l = dao.busca("0");
		assertEquals(l.getNome(), "0");

	}

	@Test
	public void TestaBuscaComCodigo() {
		EspecialidadeDAO dao = new EspecialidadeDAO();
		List<Medico> m = dao.buscaMed("2");
		assertTrue(m.get(0).getCRM().equals("A000000"));

	}

	@Test
	public void TestaBuscaComCRM() {
		EspecialidadeDAO dao = new EspecialidadeDAO();
		List<Especialidade> l = dao.buscaEspecialidadeDoMed("B000000");
		assertEquals(l.get(0).getNome(), "1");

	}

	@Test
	public void TestaDelete() {

		EspecialidadeDAO dao = new EspecialidadeDAO();
		Especialidade l = dao.busca("2");
		dao.delete(l.getCodigo());
		Especialidade test = dao.busca("2");
		assertEquals(test, null);
	}

	@AfterClass
	public static void deletaMedicos() {
		MedicoDAO dao = new MedicoDAO();

		dao.delete("B000000");
		dao.delete("A000000");
		
		EspecialidadeDAO dao2 = new EspecialidadeDAO();
		
		Especialidade l1 = dao2.busca("1");
		dao2.delete(l1.getCodigo());
		
		Especialidade l2 = dao2.busca("0");
		dao2.delete(l2.getCodigo());
		
	}

}
