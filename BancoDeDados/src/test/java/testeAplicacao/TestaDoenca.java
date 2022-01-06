package testeAplicacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import aplicacao.DoencaDAO;
import modelosDeDados.Doenca;

public class TestaDoenca {
	
	@BeforeClass
	public static void carregaBD() {
		Doenca doenca = new Doenca();
		doenca.setNome("1");
		
		DoencaDAO dao = new DoencaDAO();
		dao.add(doenca);

	}
	
	@AfterClass
	public static void deletaDadosCarregados() {
		DoencaDAO dao = new DoencaDAO();
		dao.delete("0");
	}
	
	@Test
	@Before
	public void TestaAdd() {
		Doenca doenca = new Doenca();
		doenca.setNome("0");
		
		DoencaDAO dao = new DoencaDAO();
		dao.add(doenca);
		Doenca l = dao.busca("0");
		assertEquals(l.getNome(), doenca.getNome());
		
	}
	
	@Test
	@After
	public void TestaBuscaComNome() {
		DoencaDAO dao = new DoencaDAO();
		Doenca l = dao.busca("0");
		assertNotEquals(l, null);

	}
	
	@Test
	@After
	public void TestaBuscaComID() {
		DoencaDAO dao = new DoencaDAO();
		Doenca d = dao.busca("0");
		d = dao.busca(d.getIdDoenca());
		assertNotEquals(d, null);

	}
	
	@Test
	public void TestaDelete() {
		
		DoencaDAO dao = new DoencaDAO();
		
		dao.delete("1");
		
		Doenca l = dao.busca("1");
		assertEquals(l, null);
	}
}
