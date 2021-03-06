package eportfolium.com.karuta.model.bean;
// Generated 13 juin 2019 19:14:13 by Hibernate Tools 5.2.10.Final

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * CredentialSubstitutionId generated by hbm2java
 */
@Embeddable
public class CredentialSubstitutionId implements Serializable {

	private static final long serialVersionUID = 4934561055454598443L;

	private Long id;
	private Credential credential;
	private String type;

	public CredentialSubstitutionId() {
	}

	public CredentialSubstitutionId(Credential credential, Long id, String type) {
		this.credential = credential;
		this.id = id;
		this.type = type;
	}

	@ManyToOne
	public Credential getCredential() {
		return this.credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "type", nullable = false, length = 5)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credential == null) ? 0 : credential.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		CredentialSubstitutionId other = (CredentialSubstitutionId) obj;
		if (credential == null) {
			if (other.credential != null)
				return false;
		} else if (!credential.equals(other.credential))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
