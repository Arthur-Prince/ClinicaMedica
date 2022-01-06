package testeAplicacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aplicacao.PacienteDAO;
import modelosDeDados.Paciente;

public class TestaPaciente {

	@BeforeClass
	public static void carregaDados() {
		PacienteDAO dao2 = new PacienteDAO();

		Paciente p1 = dao2.busca("1234567891");

		if (p1 == null) {
			p1 = new Paciente();

			p1.setCPF("1234567891");
			p1.setEndereco("rua");
			p1.setNome("maria");
			p1.setSexo("f");
			p1.setTelefone("000000000");
			p1.setIdade(40);

			dao2.add(p1);
		}

	}

	@AfterClass
	public static void deletaDados() {
		PacienteDAO dao = new PacienteDAO();
		dao.delete("1234567892");
	}

	@Test
	@Before
	public void testaAdd() {
		PacienteDAO dao = new PacienteDAO();
		Paciente p = dao.busca("1234567892");
		if (p == null) {
			p = new Paciente();

			p.setCPF("1234567892");
			p.setEndereco("rua");
			p.setNome("joao");
			p.setSexo("m");
			p.setTelefone("000000001");
			p.setIdade(20);

			dao.add(p);
		}
		Paciente test = dao.busca("1234567892");
		assertEquals(test.getNome(), p.getNome());
	}

	@Test
	public void testaupdate() {
		PacienteDAO dao = new PacienteDAO();
		Paciente test = dao.busca("1234567892");

		test.setTelefone("000000002");
		dao.update("1234567892", test);
		Paciente p = dao.busca("1234567892");

		assertEquals(p.getTelefone(), test.getTelefone());

	}

	@Test
	public void testaBusca() {
		PacienteDAO dao = new PacienteDAO();
		Paciente test = dao.busca("1234567892");
		assertNotEquals(test, null);
	}

	@Test
	public void testaDelete() {
		PacienteDAO dao = new PacienteDAO();
		dao.delete("1234567891");
		Paciente test = dao.busca("1234567891");
		assertEquals(test, null);
	}

}
