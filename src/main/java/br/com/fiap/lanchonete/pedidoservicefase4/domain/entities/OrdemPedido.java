package br.com.fiap.lanchonete.pedidoservicefase4.domain.entities;

public class OrdemPedido {
	private Long idExterno;
	private String status;

	public OrdemPedido(Long idExterno) {
		this.idExterno = idExterno;
	}

	public OrdemPedido(Long idExterno, String status) {
		this.idExterno = idExterno;
		this.status = status;
	}

	public Long getIdExterno() {
		return idExterno;
	}

	public void setIdExterno(Long idExterno) {
		this.idExterno = idExterno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
