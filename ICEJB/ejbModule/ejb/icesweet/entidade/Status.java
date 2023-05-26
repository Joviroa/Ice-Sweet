package ejb.icesweet.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(schema="icesweet", name="status")
@Entity
public class Status implements Entidade{

	public static final String Disponivel = "1";
	public static final String Ocupado = "2";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_status")
	private Long id;
	
	@Column(name="status", nullable= false)
	private String status;
	
	@Column(name="descricao", nullable= false)
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Boolean isAtivo() {
		if(getStatus().equals(Status.Ocupado)) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
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
		Status other = (Status) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
