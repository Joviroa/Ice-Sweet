package ejb.icesweet.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Table(schema="icesweet", name="cliente")
@Entity
public class Cliente implements Entidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="icesweet.sequence_cliente")
	@SequenceGenerator(name="icesweet.sequence_cliente", sequenceName="icesweet.sequence_cliente", allocationSize=1)
	@Column(name="id_cliente")
	private Long id;
	
	@Column(name="nome", nullable= false)
	private String nome;
	
	@Column(name="cpf", nullable= false)
	private String cpf;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="valor_cupom_acumulado")
	private BigDecimal valorCupomAcumulado;
	
	@Column(name="saldo_cupom")
	private BigDecimal saldoCupom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getValorCupomAcumulado() {
		return valorCupomAcumulado;
	}

	public void setValorCupomAcumulado(BigDecimal valorCupomAcumulado) {
		this.valorCupomAcumulado = valorCupomAcumulado;
	}
	
	public BigDecimal getSaldoCupom() {
		return saldoCupom;
	}

	public void setSaldoCupom(BigDecimal saldoCupom) {
		this.saldoCupom = saldoCupom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
}
