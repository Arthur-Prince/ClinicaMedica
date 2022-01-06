package aplicacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import modelosDeDados.Agenda;

public class AgendaDAO {

	// implementacao das funcoes ADD, DEL, UPDT, busca
	// UPDT dado idAgenda
	// del dado: idAgenda ou CRM
	// busca agenda dado CRM

	/**
	 * adiciona Agenda no BD
	 * 
	 * @param Agenda
	 */
	public void add(Agenda Agenda) {

		String str = "INSERT INTO Agenda (diaSemana, horaInit, horaFim, CRMMedico) values(?, ?, ?, ?)";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, Agenda.getDiaSemana());
			pstm.setDate(2, new Date(Agenda.getHoraInit().getTime()));
			pstm.setDate(3, new Date(Agenda.getHoraFim().getTime()));
			pstm.setString(4, Agenda.getCRMMedico());

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
	 * atualiza agenda com um id definido. atualiza apenas datas.
	 * 
	 * @param idAgenda id da agenda
	 * @param agenda   agenda modificada
	 */
	public void update(int idAgenda, Agenda Agenda) {
		String str = "UPDATE Agenda SET diaSemana = ?, horaInit = ?, horaFim = ?"
	+ " WHERE idAgenda = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);

			pstm.setString(1, Agenda.getDiaSemana());
			pstm.setDate(2, new Date(Agenda.getHoraInit().getTime()));
			pstm.setDate(3, new Date(Agenda.getHoraFim().getTime()));
			pstm.setInt(4, idAgenda);
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
	 * busca por CRM
	 * 
	 * @param CRM
	 * @return Agendas
	 */
	public List<Agenda> busca(String CRM) {
		List<Agenda> rtn = null;
		String str = "SELECT * FROM Agenda WHERE CRMMedico = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CRM);

			// pega respostas
			ResultSet resposta = pstm.executeQuery();
			rtn = new ArrayList<Agenda>();
			// carrega as Agendas na lista
			while (resposta.next()) {
				Agenda Agenda = new Agenda();

				Agenda.setIdAgenda(resposta.getInt("idAgenda"));
				Agenda.setDiaSemana(resposta.getString("diaSemana"));
				Agenda.setHoraInit(resposta.getDate("horaInit"));
				Agenda.setHoraFim(resposta.getDate("horaFim"));
				Agenda.setCRMMedico(resposta.getString("CRMMedico"));

				rtn.add(Agenda);
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

		return rtn;
	}

	/**
	 * deleta por idAgenda
	 * 
	 * @param idAgenda
	 */
	public void delete(int idAgenda) {
		String str = "DELETE FROM Agenda WHERE idAgenda = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setInt(1, idAgenda);

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
	 * delete por CRM
	 * 
	 * @param CRM
	 */
	public void delete(String CRM) {
		String str = "DELETE FROM Agenda WHERE CRMMedico = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

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
