package modelosDeDados;

import java.util.List;

public class Medico {
	
	private String CRM;
	private String nome;
	private String telefone;
	private List<Especialidade> especialidade;
	private List<Consulta> consultas;
	private List<Agenda> agenda;
	
	
	public String getCRM() {
		return CRM;
	}
	public void setCRM(String cRM) {
		CRM = cRM;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Especialidade> getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(List<Especialidade> especialidade) {
		this.especialidade = especialidade;
	}
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	public List<Agenda> getAgenda() {
		return agenda;
	}
	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}
	
	
	
}
