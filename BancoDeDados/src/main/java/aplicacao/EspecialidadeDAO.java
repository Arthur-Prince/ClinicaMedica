package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.core.ConnectionFactory;

import factory.Conexao;
import modelosDeDados.Especialidade;
import modelosDeDados.Medico;

public class EspecialidadeDAO {

	// implementacao das funcoes ADD, DEL, Busca
	// busca: todos as especialidades, todos os medicos com tal especialidade,
	// as especialidades de tal medicos

	/**
	 * adiciona especialidade no BD
	 * 
	 * @param esp especialidade que se vai adicionar
	 */
	public void add(Especialidade esp) {

		String str = "INSERT INTO Especialidade (nome, indice) values(?, ?)";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, esp.getNome());
			pstm.setInt(2, esp.getIndice());

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
	 * busca todas as especialidade registradas no BD
	 * 
	 * @return lista de especialidades
	 */
	public Especialidade busca(String nomeEsp) {
		String str = "SELECT * FROM  Especialidade WHERE nome = ?";
		Especialidade esp = null;

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, nomeEsp);

			// pega resposta
			ResultSet resposta = pstm.executeQuery();
			// carrega as especialidades na lista
			if (resposta.next()) {
				esp = new Especialidade();

				esp.setNome(resposta.getString("nome"));
				esp.setIndice(resposta.getInt("indice"));
				esp.setCodigo(resposta.getInt("codigo"));

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

		return esp;
	}

	/**
	 * retorna todos os medicos com certa especialidade
	 * 
	 * @param nomeEsp nome da especialidade.
	 * @return lista de medicos
	 */
	public List<Medico> buscaMed(String nomeEsp) {
		List<Medico> rtn = null;
		String str = "SELECT CRM, med.nome, telefone"
				+ " FROM  (Medico natural join ExerceEspecialidade)med"
				+ " LEFT OUTER JOIN Especialidade esp"
				+ " ON med.codEspecialidade = esp.codigo"
				+ " WHERE CRM = CRMMedico AND esp.nome = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, nomeEsp);

			// pega resposta
			ResultSet resposta = pstm.executeQuery();
			rtn = new ArrayList<Medico>();
			// carrega as especialidades na lista
			while (resposta.next()) {
				Medico med = new Medico();

				med.setCRM(resposta.getString("CRM"));
				med.setNome(resposta.getString("nome"));
				med.setTelefone(resposta.getString("telefone"));

				rtn.add(med);
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
	 * busca todas as especialidades de certo medico
	 * 
	 * @param CRM CRM do medico
	 * @return lista de especialidades
	 */
	public List<Especialidade> buscaEspecialidadeDoMed(String CRM) {
		List<Especialidade> rtn = null;
		String str = "SELECT esp.nome, indice, codigo"
				+ " FROM  (Especialidade natural join ExerceEspecialidade) esp"
				+ " LEFT OUTER JOIN Medico med ON med.CRM = esp.CRMMedico"
				+ " WHERE codigo = codEspecialidade AND med.CRM = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CRM);

			// pega resposta
			ResultSet resposta = pstm.executeQuery();
			rtn = new ArrayList<Especialidade>();
			// carrega as especialidades na lista
			while (resposta.next()) {
				Especialidade esp = new Especialidade();

				esp.setNome(resposta.getString("nome"));
				esp.setIndice(resposta.getInt("indice"));
				esp.setCodigo(resposta.getInt("codigo"));

				rtn.add(esp);
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
	 * busca a especialidade pelo nome dela(usada para conseguir codigo)
	 * 
	 * @param nome nome da especialidade
	 * @return lista de especialidades com aquele nome
	 */
	List<Especialidade> buscaEspecialidade(String nome) {
		List<Especialidade> rtn = null;
		String str = "SELECT nome, indice, codigo FROM  Especialidade WHERE nome = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, nome);
			// pega resposta
			ResultSet resposta = pstm.executeQuery();
			rtn = new ArrayList<Especialidade>();
			// carrega as especialidades na lista
			while (resposta.next()) {
				Especialidade esp = new Especialidade();

				esp.setNome(resposta.getString("nome"));
				esp.setIndice(resposta.getInt("indice"));
				esp.setCodigo(resposta.getInt("codigo"));

				rtn.add(esp);
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
	 * deleta certa especialidade do bd
	 * 
	 * @param codigo codigo da especialidade
	 */
	public void delete(int codigo) {
		String str = "DELETE FROM Especialidade WHERE codigo = ?";
		String str2 = "DELETE FROM ExerceEspecialidade WHERE codEspecialidade = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str2);
			pstm.setInt(1, codigo);

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
			pstm.setInt(1, codigo);

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
