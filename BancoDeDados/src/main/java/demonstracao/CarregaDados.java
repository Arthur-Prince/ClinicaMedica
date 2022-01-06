package demonstracao;

import modelosDeDados.*;
import aplicacao.*;

public class CarregaDados {

	public static void main(String[] args) {
		
		//inicializa as variaveis que interagem com o banco de dados
		DoencaDAO doencaDAO = new DoencaDAO();
		MedicoDAO medicoDAO = new MedicoDAO();
		PacienteDAO pacienteDAO = new PacienteDAO();
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		
		
		
		//especialidades
		Especialidade especialidade1 = new Especialidade();
		especialidade1.setIndice(1);
		especialidade1.setNome("especialidade1");
		
		Especialidade especialidade2 = new Especialidade();
		especialidade2.setIndice(1);
		especialidade2.setNome("especialidade2");
		
		Especialidade especialidade3 = new Especialidade();
		especialidade3.setIndice(1);
		especialidade3.setNome("especialidade3");
		
		Especialidade especialidade4 = new Especialidade();
		especialidade4.setIndice(1);
		especialidade4.setNome("especialidade4");
		
		Especialidade especialidade5 = new Especialidade();
		especialidade5.setIndice(1);
		especialidade5.setNome("especialidade5");
		
		Especialidade especialidade6 = new Especialidade();
		especialidade6.setIndice(1);
		especialidade6.setNome("especialidade6");
		
		Especialidade especialidade7 = new Especialidade();
		especialidade7.setIndice(1);
		especialidade7.setNome("especialidade7");
		
		Especialidade especialidade8 = new Especialidade();
		especialidade8.setIndice(1);
		especialidade8.setNome("especialidade8");
		
		Especialidade especialidade9 = new Especialidade();
		especialidade9.setIndice(1);
		especialidade9.setNome("especialidade9");
		
		//adiciona especialidades no banco de dados
		
		if(especialidadeDAO.busca("especialidade1")==null) {
			especialidadeDAO.add(especialidade1);
			especialidadeDAO.add(especialidade2);
			especialidadeDAO.add(especialidade3);
			especialidadeDAO.add(especialidade4);
			especialidadeDAO.add(especialidade5);
			especialidadeDAO.add(especialidade6);
			especialidadeDAO.add(especialidade7);
			especialidadeDAO.add(especialidade8);
			especialidadeDAO.add(especialidade9);
		}
		
		//doencas
		
		Doenca doenca1 = new Doenca();
		doenca1.setNome("doenca1");
		
		Doenca doenca2 = new Doenca();
		doenca2.setNome("doenca2");

		Doenca doenca3 = new Doenca();
		doenca3.setNome("doenca3");
		
		Doenca doenca4 = new Doenca();
		doenca4.setNome("doenca4");
		
		Doenca doenca5 = new Doenca();
		doenca5.setNome("doenca5");
		
		//adiciona doencas no banco de dados
		if(doencaDAO.busca("doenca1")==null) {
			doencaDAO.add(doenca1);
			doencaDAO.add(doenca2);
			doencaDAO.add(doenca3);
			doencaDAO.add(doenca4);
			doencaDAO.add(doenca5);
		}
		
		//medicos
		
		Medico medico1 = new Medico();
		medico1.setCRM("A999991");
		medico1.setNome("medico1");
		medico1.setTelefone("999999991");
		
		Medico medico2 = new Medico();
		medico2.setCRM("A999992");
		medico2.setNome("medico2");
		medico2.setTelefone("999999992");
		
		Medico medico3 = new Medico();
		medico3.setCRM("A999993");
		medico3.setNome("medico3");
		medico3.setTelefone("999999993");
		
		
		//adiciona medicos no banco de dados
		if(medicoDAO.busca("A999991")==null) {
			medicoDAO.add(medico1);
			medicoDAO.add(medico2);
			medicoDAO.add(medico3);
			
			//adiciona especialidades nos medicos
			medicoDAO.addEspecialidade("A999991", "especialidade1");
			medicoDAO.addEspecialidade("A999991", "especialidade2");
			medicoDAO.addEspecialidade("A999991", "especialidade3");
			medicoDAO.addEspecialidade("A999991", "especialidade4");
			
			medicoDAO.addEspecialidade("A999992", "especialidade4");
			medicoDAO.addEspecialidade("A999992", "especialidade5");
			medicoDAO.addEspecialidade("A999992", "especialidade6");
			medicoDAO.addEspecialidade("A999992", "especialidade7");
			medicoDAO.addEspecialidade("A999992", "especialidade8");
			
			medicoDAO.addEspecialidade("A999993", "especialidade1");
			medicoDAO.addEspecialidade("A999993", "especialidade4");
			medicoDAO.addEspecialidade("A999993", "especialidade9");
			
		}
		
		//adiciona pacientes
		Paciente paciente1 = new Paciente();
		paciente1.setCPF("1234567891");
		paciente1.setEndereco("rua1");
		paciente1.setIdade(11);
		paciente1.setNome("paciente1");
		paciente1.setSexo("m");
		paciente1.setTelefone("999999994");
		
		

		Paciente paciente2 = new Paciente();
		paciente2.setCPF("1234567892");
		paciente2.setEndereco("rua2");
		paciente2.setIdade(22);
		paciente2.setNome("paciente2");
		paciente2.setSexo("f");
		paciente2.setTelefone("999999995");
		
		
		if(pacienteDAO.busca("1234567892")==null) {
			pacienteDAO.add(paciente1);
			pacienteDAO.add(paciente2);
		}

	}

}
