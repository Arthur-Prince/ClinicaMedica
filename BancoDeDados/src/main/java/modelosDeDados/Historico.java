package modelosDeDados;

public class Historico {
	
	private int idHistorico;
	private String tratamentoRecomendado;
	private String remediosReceitados;
	private String observacoes;
	private Doenca doenca;
	private Consulta consulta;
	
	public int getIdHistorico() {
		return idHistorico;
	}
	public void setIdHistorico(int idHistorico) {
		this.idHistorico = idHistorico;
	}
	public String getTratamentoRecomendado() {
		return tratamentoRecomendado;
	}
	public void setTratamentoRecomendado(String tratamentoRecomendado) {
		this.tratamentoRecomendado = tratamentoRecomendado;
	}
	public String getRemediosReceitados() {
		return remediosReceitados;
	}
	public void setRemediosReceitados(String remediosReceitados) {
		this.remediosReceitados = remediosReceitados;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public Doenca getDoenca() {
		return doenca;
	}
	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
}
