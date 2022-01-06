package factory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Conexao {
	
	private static Config config = null;
	
	public static void carregaParam() {
		// le as configuracoes do arquivo param.yaml
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				File file = new File(classLoader.getResource("param.yaml").getFile());
				
				ObjectMapper om = new ObjectMapper(new YAMLFactory());
				om.findAndRegisterModules();
				try {
					config = om.readValue(file, Config.class);
				} catch (StreamReadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DatabindException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	public static Connection connectSQL() {
		if(config ==null)
			carregaParam();

		// conecta ao driver do postgresql
		try {
			Class.forName("org.postgresql.Driver");

			String urlcompleta = config.getURL() + "/" + config.getNomeBD();
			Connection cnt = (Connection) DriverManager.getConnection(urlcompleta, config.getUser(),
					config.getPassword());
			return cnt;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

}
