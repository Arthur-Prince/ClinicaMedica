package aplicacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import factory.Conexao;
import modelosDeDados.Agenda;
import modelosDeDados.Consulta;
import modelosDeDados.Paciente;

public class PacienteDAO {
	
	//implementacao das funcoes ADD, DEL, UPDT
	//funcao de busca: busca paciente com consultas

	
	public void add(Paciente paciente) {

		String str = "INSERT INTO Paciente (CPF, nome, sexo, idade, telefone, endereco)"
				+ " values(?, ?, ?, ?, ?, ?)";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			
			pstm.setString(1, paciente.getCPF());
			pstm.setString(2, paciente.getNome());
			pstm.setString(3, paciente.getSexo());
			pstm.setInt(4, paciente.getIdade());
			pstm.setString(5, paciente.getTelefone());
			pstm.setString(6, paciente.getEndereco());

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
	
	
	public void update(String CPF, Paciente paciente) {

		String str = "UPDATE Paciente SET idade = ?, telefone = ?, endereco = ?"
				+ " WHERE CPF = ?";

		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			
			pstm.setInt(1, paciente.getIdade());
			pstm.setString(2, paciente.getTelefone());
			pstm.setString(3, paciente.getEndereco());
			pstm.setString(4, CPF);
			
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
	
	public Paciente busca(String CPF) {
		String str = "SELECT * FROM Paciente WHERE CPF = ?";
		
		Paciente p = null;
		
		Connection cnt = null;
		PreparedStatement pstm = null;

		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CPF);

			// pega respostas
			ResultSet resposta = pstm.executeQuery();
			
			
			if(resposta.next()) {
				p = new Paciente();
				
				p.setCPF(resposta.getString("CPF"));
				p.setNome(resposta.getString("nome"));
				p.setSexo(resposta.getString("sexo"));
				p.setIdade(resposta.getInt("idade"));
				p.setTelefone(resposta.getString("telefone"));
				p.setEndereco(resposta.getString("endereco"));
				
				ConsultaDAO dao = new ConsultaDAO();
				p.setConsultas(dao.busca(CPF));
				
				
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
		return p;
	}
	
	public void delete(String CPF) {
		String str = "DELETE FROM Paciente WHERE CPF = ?";
		

		Connection cnt = null;
		PreparedStatement pstm = null;

		// deleta consultas daquele paciente
		ConsultaDAO dao = new ConsultaDAO();
		List<Consulta> l = dao.busca(CPF);
		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			Consulta consulta = (Consulta) iterator.next();
			dao.delete(consulta.getIdConsulta());
		}
		
		try {
			cnt = (Connection) Conexao.connectSQL();

			// prepara mensagem
			pstm = cnt.prepareStatement(str);
			pstm.setString(1, CPF);

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
