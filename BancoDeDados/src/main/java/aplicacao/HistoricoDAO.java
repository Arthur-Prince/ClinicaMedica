package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import modelosDeDados.Consulta;
import modelosDeDados.Doenca;
import modelosDeDados.Historico;

public class HistoricoDAO {
	
	//implementacao das funcoes ADD e busca dado a consulta(retorna a doenca tbm)
	
	public void add(Historico hist, Doenca doenca, int idConsulta) {
		String str = "INSERT INTO Historico (tratamentoRecomendado, remediosReceitados,"
				+ " Observacoes, idDoenca, idConsulta)"
				+ " values(?, ?, ?, ?, ?)";

		Connection cnt = null;
		PreparedStatement pstm = null;

		
		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, hist.getTratamentoRecomendado());
			pstm.setString(2, hist.getRemediosReceitados());
			pstm.setString(3, hist.getObservacoes());
			if(doenca != null)
				pstm.setInt(4, doenca.getIdDoenca());
			else
				pstm.setInt(4, -1);
			pstm.setInt(5, idConsulta);
			

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
	
	List<Historico> busca(int idConsulta){
		List<Historico> rtn = null;
		String str = "SELECT * FROM Historico WHERE idConsulta = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setInt(1, idConsulta);
			
			// pega respostas
			ResultSet resposta = pstm.executeQuery();
			
			rtn = new ArrayList<Historico>();
			while (resposta.next()) {
				Historico hist = new Historico();
				
				hist.setIdHistorico(resposta.getInt("idHistorico"));
				hist.setTratamentoRecomendado(resposta.getString("tratamentoRecomendado"));
				hist.setRemediosReceitados(resposta.getString("remediosReceitados"));
				hist.setObservacoes(resposta.getString("observacoes"));
				if(resposta.getInt("idDoenca")!=-1)
					hist.setDoenca((new DoencaDAO()).busca(resposta.getInt("idDoenca")));
			
				rtn.add(hist);
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

}
