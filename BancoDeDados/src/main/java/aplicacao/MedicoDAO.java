package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import factory.Conexao;
import modelosDeDados.Consulta;
import modelosDeDados.Especialidade;
import modelosDeDados.Medico;

public class MedicoDAO {

	// implementacao das funcoes ADD, DEL
	// funcao de busca: busca medico com especialidade e agenda

	/**
	 * adiciona um medico no BD
	 * 
	 * @param Medico objeto medico
	 */
	public void add(Medico Medico) {

		String str = "INSERT INTO Medico (CRM, nome, telefone) values(?, ?, ?)";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, Medico.getCRM());
			pstm.setString(2, Medico.getNome());
			pstm.setString(3, Medico.getTelefone());

			// executa
			pstm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
				if (cnt != null)
					cnt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * adiciona uma especialidade em um medico no bd
	 * 
	 * @param CRM     CRM do medico
	 * @param nomeEsp nome da especialidade
	 */
	public void addEspecialidade(String CRM, String nomeEsp) {

		EspecialidadeDAO dao = new EspecialidadeDAO();
		List<Especialidade> esp = dao.buscaEspecialidade(nomeEsp);
		if (esp.size() == 0)
			return;

		String str = "INSERT INTO ExerceEspecialidade (CRMMedico, codEspecialidade) values(?, ?)";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CRM);
			pstm.setInt(2, esp.get(0).getCodigo());

			// executa
			pstm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
				if (cnt != null)
					cnt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * busca medico com sua agenda e suas especialidades
	 * 
	 * @param CRM CRM do medico
	 * @return medico com agenda e especialidades
	 */
	public Medico busca(String CRM) {
		Medico Medico = null;
		String str = "SELECT * FROM  Medico WHERE CRM = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CRM);

			// pega respostas
			ResultSet resposta = pstm.executeQuery();

			// carrega as Medicos na lista
			if (resposta.next()) {
				Medico = new Medico();
				Medico.setCRM(CRM);
				Medico.setNome(resposta.getString("nome"));
				Medico.setTelefone(resposta.getString("telefone"));

				EspecialidadeDAO esp = new EspecialidadeDAO();
				AgendaDAO agend = new AgendaDAO();
				ConsultaDAO consult = new ConsultaDAO();

				Medico.setAgenda(agend.busca(CRM));
				Medico.setEspecialidade(esp.buscaEspecialidade(CRM));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
				if (cnt != null)
					cnt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return Medico;
	}

	/**
	 * deleta o medico do BD e sua agenda
	 * 
	 * @param CRM
	 */
	public void delete(String CRM) {
		String str = "DELETE FROM Medico WHERE CRM = ?";
		String str2 = "DELETE FROM ExerceEspecialidade WHERE CRMMedico = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		
		
		//deleta consultas daquele medico
		ConsultaDAO dao = new ConsultaDAO();
		
		List<Consulta> l = dao.buscaCRM(CRM);
		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			Consulta consulta = (Consulta) iterator.next();
			dao.delete(consulta.getIdConsulta());
		}
		
		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str2);
			pstm.setString(1, CRM);

			// deleta dados do medico
			AgendaDAO ag = new AgendaDAO();
			ag.delete(CRM);

			// executa
			pstm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
				if (cnt != null)
					cnt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CRM);

			// executa
			pstm.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null)
					pstm.close();
				if (cnt != null)
					cnt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
