package ejb.icesweet.entidade;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name="icesweet.sequence_comanda",sequenceName="sequence_comanda", allocationSize=1)
@Table(schema="icesweet", name="comanda")
@Entity
public class Comanda implements Entidade{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_comanda")
	private Long id;
	
	@Column(name="nome_cliente")
	private String nomeCliente;
	
	@Column(name="numero", nullable= false)
	private Integer numero;
	
	@Column(name="valor_total")
	private BigDecimal valorTotal;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="id_cliente", nullable = true)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_cupom" , nullable = true)
	private Cupom cupom;
	
	@ManyToOne
	@JoinColumn(name="id_status" , nullable = false)
	private Status status;

	
	public Boolean clienteCadastrado() {
		if(getCliente() != null) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}
	
	public Boolean cupomAtivo() {
		return cupom.isAtivo();
	}
	
	public Boolean comandaAtiva() {
		return getStatus().isAtivo();
	}
	
	public String exibirNomeCliente() {
		if(getCliente()!= null) {
			return getCliente().getNome();
		}else if(getNomeCliente() != null) {
			return getNomeCliente();
		}else {
			return null;
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comanda other = (Comanda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
