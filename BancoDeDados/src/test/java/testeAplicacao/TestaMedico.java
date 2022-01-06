package testeAplicacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aplicacao.EspecialidadeDAO;
import aplicacao.MedicoDAO;
import modelosDeDados.Especialidade;
import modelosDeDados.Medico;

public class TestaMedico {

	@BeforeClass
	public static void preparaTest() {
		Especialidade esp = new Especialidade();
		esp.setNome("0");
		esp.setIndice(0);

		EspecialidadeDAO dao = new EspecialidadeDAO();
		dao.add(esp);

		MedicoDAO dao2 = new MedicoDAO();

		Medico med2 = dao2.busca("B000000");
		if (med2 == null) {
			med2 = new Medico();
			med2.setCRM("B000000");
			med2.setNome("0");
			med2.setTelefone("0");
			dao2.add(med2);
		}
	}

	@AfterClass
	public static void LimpaBD() {
		EspecialidadeDAO dao = new EspecialidadeDAO();
		List<Especialidade> l = dao.buscaEspecialidadeDoMed("A000000");

		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			Especialidade especialidade = (Especialidade) iterator.next();
			dao.delete(especialidade.getCodigo());
		}
		MedicoDAO dao2 = new MedicoDAO();
		dao2.delete("B000000");
	}

	@Test
	public void TestaAddMedico() {
		MedicoDAO dao = new MedicoDAO();
		Medico med = dao.busca("A000000");
		if (med == null) {
			med = new Medico();
			med.setCRM("A000000");
			med.setNome("0");
			med.setTelefone("0");

			dao.add(med);
		}
		Medico m = dao.busca("A000000");
		assertEquals(m.getTelefone(), med.getTelefone());
	}

	@Test
	public void testaAddEspecialidade() {
		MedicoDAO dao = new MedicoDAO();
		EspecialidadeDAO dao2 = new EspecialidadeDAO();
		List<Especialidade> l = dao2.buscaEspecialidadeDoMed("B000000");
		int ant = l.size();
		dao.addEspecialidade("B000000", "0");
		l = dao2.buscaEspecialidadeDoMed("B000000");
		assertNotEquals(l.size(), ant);
	}

	@Test
	public void testaBuscaCRM() {
		MedicoDAO dao = new MedicoDAO();
		Medico m = dao.busca("A000000");
		assertNotEquals(m, null);
	}

	@Test
	public void Testadelete() {
		MedicoDAO dao = new MedicoDAO();
		dao.delete("A000000");
		assertEquals(dao.busca("A000000"), null);
	}

}
