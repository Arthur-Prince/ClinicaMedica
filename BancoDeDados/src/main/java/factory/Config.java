package factory;

public class Config {
	private String user;
	private String password;
	private String url;
	private String nomeBD;
	
	public Config(String user, String password, String url, String nomeBD) {
		this.user = user;
		this.password = password;
		this.url = url;
		this.nomeBD = nomeBD;
	}
	public Config() {}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getURL() {
		return url;
	}

	public String getNomeBD() {
		return nomeBD;
	}
	
}
