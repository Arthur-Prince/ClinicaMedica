package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import modelosDeDados.Doenca;

public class DoencaDAO {

	// implementacao das funcoes ADD, DEL, busca

	public void add(Doenca doenca) {

		String str = "INSERT INTO Doenca (nome) values(?)";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, doenca.getNome());

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

	public Doenca busca(String nome) {
		String str = "SELECT * FROM  Doenca WHERE nome = ?";

		Doenca doenca = null;
		
		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, nome);

			// pega respostas
			ResultSet resposta = pstm.executeQuery();
			// carrega as Doencas na lista
			if (resposta.next()) {
				doenca = new Doenca();

				doenca.setNome(resposta.getString("nome"));
				doenca.setIdDoenca(resposta.getInt("idDoenca"));

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

		return doenca;
	}
	
	public Doenca busca(int idDoenca) {
		Doenca doenca = null;
		String str = "SELECT * FROM  Doenca WHERE idDoenca = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			
			pstm.setInt(1, idDoenca);

			// pega respostas
			ResultSet resposta = pstm.executeQuery();
			// carrega as Doencas na lista
			if (resposta.next()) {
				doenca = new Doenca();

				doenca.setNome(resposta.getString("nome"));
				doenca.setIdDoenca(resposta.getInt("idDoenca"));

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

		return doenca;
	}

	public void delete(String nome) {
		String str = "DELETE FROM Doenca WHERE nome = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, nome);

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
