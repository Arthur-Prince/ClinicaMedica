package modelosDeDados;

import java.util.Date;

public class Agenda {
	
	private int idAgenda;
	private String diaSemana;
	private Date horaInit;
	private Date horaFim;
	private String CRMMedico;
	
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Date getHoraInit() {
		return horaInit;
	}
	public void setHoraInit(Date horaInit) {
		this.horaInit = horaInit;
	}
	public Date getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}
	public String getCRMMedico() {
		return CRMMedico;
	}
	public void setCRMMedico(String cRMMedico) {
		CRMMedico = cRMMedico;
	}
	
	

}
