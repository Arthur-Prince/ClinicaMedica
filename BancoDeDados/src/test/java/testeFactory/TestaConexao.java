package testeFactory;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import factory.Conexao;

public class TestaConexao {
	
	@Test
	public void TestaConexao() {
		Conexao.carregaParam();
		assertNotEquals(Conexao.connectSQL(), null);
	}
}
