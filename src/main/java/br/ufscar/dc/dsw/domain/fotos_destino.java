package br.ufscar.dc.dsw.domain;

public class fotos_destino {
	private Long id_fotos;
	private String url_foto;
	private destino destino;
	public fotos_destino(Long id_foto, String url_foto, br.ufscar.dc.dsw.domain.destino destino) {
		super();
		this.id_fotos = id_foto;
		this.url_foto = url_foto;
		this.destino = destino;
	}
	public Long getId_foto() {
		return id_fotos;
	}
	public void setId_foto(Long id_foto) {
		this.id_fotos = id_foto;
	}
	public String getUrl_foto() {
		return url_foto;
	}
	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}
	public destino getDestino() {
		return destino;
	}
	public void setDestino(destino destino) {
		this.destino = destino;
	}
	
	
}
