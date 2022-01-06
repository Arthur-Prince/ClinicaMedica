package testeAplicacao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import aplicacao.ConsultaDAO;
import aplicacao.DoencaDAO;
import aplicacao.HistoricoDAO;
import aplicacao.MedicoDAO;
import aplicacao.PacienteDAO;
import modelosDeDados.Consulta;
import modelosDeDados.Doenca;
import modelosDeDados.Historico;
import modelosDeDados.Medico;
import modelosDeDados.Paciente;

public class TestaHistorico {

	@BeforeClass
	public static void addDados() {
		
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
		
		PacienteDAO dao2 = new PacienteDAO();
		
		Paciente p1 = dao2.busca("1234567891");
		Paciente p2 = dao2.busca("1234567892");
		
		if(p1 == null) {
			p1 = new Paciente();
			
			p1.setCPF("1234567891");
			p1.setEndereco("rua");
			p1.setNome("maria");
			p1.setSexo("f");
			p1.setTelefone("000000000");
			p1.setIdade(40);
			
			dao2.add(p1);
		}
		if(p2 == null) {
			p2 = new Paciente();
			
			p2.setCPF("1234567892");
			p2.setEndereco("rua");
			p2.setNome("joao");
			p2.setSexo("m");
			p2.setTelefone("000000001");
			p2.setIdade(20);
			
			dao2.add(p2);
		}
		
		Consulta consulta = new Consulta();
		
		consulta.setRealizada(true);
		consulta.setDtInit(new Date(1, 1, 1));
		consulta.setDtFim(new Date(2021, 12, 31));
		consulta.setPago(false);
		consulta.setValorPago(1.1);
		consulta.setCRMMedico("B000000");
		consulta.setCPFCliente("1234567892");
		
		ConsultaDAO dao3 = new ConsultaDAO();
		dao3.add(consulta);
	}
	
	@AfterClass
	public static void deletaDadosInseridos() {
		MedicoDAO dao = new MedicoDAO();
		PacienteDAO dao2 = new PacienteDAO();
		DoencaDAO dao3 = new DoencaDAO();
		
		dao.delete("B000000");
		dao.delete("A000000");
		dao2.delete("1234567891");
		dao2.delete("1234567892");
		dao3.delete("programador");
	}
	
	@Test
	public void testAdd() {
		ConsultaDAO dao = new ConsultaDAO();
		DoencaDAO dao2 = new DoencaDAO();
		HistoricoDAO dao3 = new HistoricoDAO();
		
		Doenca d = new Doenca();
		d.setNome("programador");
		dao2.add(d);
		d = dao2.busca("programador");
		
		Consulta consulta = dao.busca("1234567892").get(0);
		
		Historico hist = new Historico();
		hist.setTratamentoRecomendado("aaaaaaaaa");
		hist.setRemediosReceitados("bbbbbbbbbbbb");
		hist.setObservacoes("cccccccc");
		
		dao3.add(hist, d, consulta.getIdConsulta());
		
		consulta = dao.busca("1234567892").get(0);
		
		assertEquals(consulta.getHistorico().get(0).getObservacoes(), "cccccccc");
		
	}
}
