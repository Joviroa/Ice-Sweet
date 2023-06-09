package ejb.icesweet.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(schema="icesweet", name="cupom")
@Entity
public class Cupom implements Entidade{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="icesweet.sequence_cupom")
	@SequenceGenerator(name="icesweet.sequence_cupom", sequenceName="icesweet.sequence_cupom", allocationSize=1)
	@Column(name="id_cupom")
	private Long id;
	
	@Column(name="valor_desconto", nullable= false)
	private BigDecimal valorDesconto;
	
	@Column(name="valor_elegivel", nullable= false)
	private BigDecimal valorElegivel;
	
	@Column(name="ativo", nullable= false)
	private boolean ativo;
	
	@Column(name="data_inicio", nullable= false)
	private Date dataInicio;
	
	@Column(name="data_fim")
	private Date dataFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorElegivel() {
		return valorElegivel;
	}

	public void setValorElegivel(BigDecimal valorElegivel) {
		this.valorElegivel = valorElegivel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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
		Cupom other = (Cupom) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
