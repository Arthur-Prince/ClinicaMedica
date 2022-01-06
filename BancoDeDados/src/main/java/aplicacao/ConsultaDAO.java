package aplicacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import modelosDeDados.Consulta;

public class ConsultaDAO {

	// implementacao das funcoes ADD, DEL
	// funcao de UPDT: UPDT datas, UPDT realizada(valor pago)
	// funcao de busca: busca paciente da consulta, buscar historico da consulta

	public void add(Consulta consulta) {
		String str = "INSERT INTO Consulta (realizada, dtInit, dtFim, pago,"
				+ " valorPago, CPFCliente, CRMMedico) values(?, ?, ?, ?, ?, ?, ?)";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setBoolean(1, consulta.isRealizada());
			pstm.setDate(2, new Date(consulta.getDtInit().getTime()));
			pstm.setDate(3, new Date(consulta.getDtFim().getTime()));
			pstm.setBoolean(4, consulta.isPago());
			pstm.setDouble(5, consulta.getValorPago());
			pstm.setString(6, consulta.getCPFCliente());
			pstm.setString(7, consulta.getCRMMedico());

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

	public void update(int idConsulta, Consulta consulta) {
		String str = "UPDATE Consulta SET realizada = ?, dtInit = ?, dtFim = ?,"
				+ " pago = ?, valorPago = ? WHERE idConsulta = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);

			pstm.setBoolean(1, consulta.isRealizada());
			pstm.setDate(2, new Date(consulta.getDtInit().getTime()));
			pstm.setDate(3, new Date(consulta.getDtFim().getTime()));
			pstm.setBoolean(4, consulta.isPago());
			pstm.setDouble(5, consulta.getValorPago());
			pstm.setInt(6, idConsulta);

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

	public List<Consulta> busca(String CPF) {
		List<Consulta> rtn = null;
		String str = "SELECT * FROM Consulta WHERE CPFCliente = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CPF);

			// pega respostas
			ResultSet resposta = pstm.executeQuery();
			rtn = new ArrayList<Consulta>();
			// carrega as Agendas na lista
			while (resposta.next()) {
				Consulta consulta = new Consulta();

				consulta.setIdConsulta(resposta.getInt("idConsulta"));
				consulta.setRealizada(resposta.getBoolean("realizada"));
				consulta.setDtInit(resposta.getDate("dtInit"));
				consulta.setDtFim(resposta.getDate("dtFim"));
				consulta.setPago(resposta.getBoolean("pago"));
				consulta.setValorPago(resposta.getDouble("valorPago"));
				consulta.setCPFCliente(resposta.getString("CPFCliente"));
				consulta.setCRMMedico(resposta.getString("CRMMedico"));
				
				consulta.setHistorico((new HistoricoDAO()).busca(consulta.getIdConsulta()));

				rtn.add(consulta);
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
	
	List<Consulta> buscaCRM(String CRM) {
		List<Consulta> rtn = null;
		String str = "SELECT * FROM Consulta WHERE CRMMedico = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CRM);

			// pega respostas
			ResultSet resposta = pstm.executeQuery();
			rtn = new ArrayList<Consulta>();
			// carrega as Agendas na lista
			while (resposta.next()) {
				Consulta consulta = new Consulta();

				consulta.setIdConsulta(resposta.getInt("idConsulta"));
				consulta.setRealizada(resposta.getBoolean("realizada"));
				consulta.setDtInit(resposta.getDate("dtInit"));
				consulta.setDtFim(resposta.getDate("dtFim"));
				consulta.setPago(resposta.getBoolean("pago"));
				consulta.setValorPago(resposta.getDouble("valorPago"));
				consulta.setCPFCliente(resposta.getString("CPFCliente"));
				consulta.setCRMMedico(resposta.getString("CRMMedico"));
				
				consulta.setHistorico((new HistoricoDAO()).busca(consulta.getIdConsulta()));

				rtn.add(consulta);
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
	

	public void delete(int idConsulta) {
		String str = "DELETE FROM Consulta WHERE idConsulta = ?";
		String str2 = "DELETE FROM Historico WHERE idConsulta = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str2);
			pstm.setInt(1, idConsulta);

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
			pstm.setInt(1, idConsulta);

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
