package demonstracao;

import java.util.Date;

import aplicacao.ConsultaDAO;
import modelosDeDados.Consulta;

public class MarcaConsulta {

	
	public static void main(String[] args) {
		
		
		//inicializa as variaveis que interagem com o banco de dados
		ConsultaDAO dao = new ConsultaDAO();
		
		/*
		Consulta consulta = new Consulta();
		//adiciona consulta
		consulta.setRealizada(false);
		consulta.setDtInit(new Date(1, 1, 1));
		consulta.setDtFim(new Date(2021, 12, 31));
		consulta.setPago(false);
		consulta.setValorPago(1.1);
		consulta.setCRMMedico("A999991");
		consulta.setCPFCliente("1234567891");
		
		dao.add(consulta);
		*/
		

		
		
		//faz update na consulta
		/*
		Consulta consulta2 = dao.busca("1234567891").get(0);
		consulta2.setPago(true);
		dao.update(consulta2.getIdConsulta(), consulta2);
		*/
		
		//deleta a consulta
		
		Consulta c1 = dao.busca("1234567891").get(0);
		dao.delete(c1.getIdConsulta());
		
		
	}
}
