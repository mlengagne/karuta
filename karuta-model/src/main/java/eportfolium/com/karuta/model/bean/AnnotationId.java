package eportfolium.com.karuta.model.bean;
// Generated 13 juin 2019 19:14:13 by Hibernate Tools 5.2.10.Final

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AnnotationId generated by hbm2java
 */
@Embeddable
public class AnnotationId implements Serializable {

	private static final long serialVersionUID = 6961270590302840044L;
	private byte[] nodeid;
	private int rank;

	public AnnotationId() {
	}

	public AnnotationId(byte[] nodeid, int rank) {
		this.nodeid = nodeid;
		this.rank = rank;
	}

	@Column(name = "nodeid", nullable = false)
	public byte[] getNodeid() {
		return this.nodeid;
	}

	public void setNodeid(byte[] nodeid) {
		this.nodeid = nodeid;
	}

	@Column(name = "rank", nullable = false)
	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AnnotationId))
			return false;
		AnnotationId castOther = (AnnotationId) other;

		return ((this.getNodeid() == castOther.getNodeid()) || (this.getNodeid() != null
				&& castOther.getNodeid() != null && Arrays.equals(this.getNodeid(), castOther.getNodeid())))
				&& (this.getRank() == castOther.getRank());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getNodeid() == null ? 0 : Arrays.hashCode(this.getNodeid()));
		result = 37 * result + this.getRank();
		return result;
	}

}