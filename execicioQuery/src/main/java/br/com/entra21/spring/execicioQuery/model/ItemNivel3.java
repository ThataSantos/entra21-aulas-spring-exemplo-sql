package br.com.entra21.spring.execicioQuery.model;

public class ItemNivel3 {
	private String ref;
	private String url;
	public ItemNivel3(String ref, String url) {
		super();
		this.ref = ref;
		this.url = url;
	}
	public ItemNivel3() {
		super();
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
