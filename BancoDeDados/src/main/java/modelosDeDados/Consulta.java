package modelosDeDados;

import java.util.Date;
import java.util.List;

public class Consulta {
	
	private int idConsulta;
	private Date dtInit;
	private Date dtFim;
	private boolean realizada;
	private boolean pago;
	private double valorPago;
	private String CPFCliente;
	private String CRMMedico;
	private List<Historico> historico;
	
	public int getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}
	public Date getDtInit() {
		return dtInit;
	}
	public void setDtInit(Date dtInit) {
		this.dtInit = dtInit;
	}
	public Date getDtFim() {
		return dtFim;
	}
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	public boolean isRealizada() {
		return realizada;
	}
	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	public String getCPFCliente() {
		return CPFCliente;
	}
	public void setCPFCliente(String cPFCliente) {
		CPFCliente = cPFCliente;
	}
	public String getCRMMedico() {
		return CRMMedico;
	}
	public void setCRMMedico(String cRMMedico) {
		CRMMedico = cRMMedico;
	}
	public List<Historico> getHistorico() {
		return historico;
	}
	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}
	
	
	

}
