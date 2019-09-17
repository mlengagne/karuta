package eportfolium.com.karuta.model.bean;
// Generated 13 juin 2019 19:14:13 by Hibernate Tools 5.2.10.Final

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * Node generated by hbm2java
 */
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "node")
public class Node implements Serializable {

	private static final long serialVersionUID = -5618880736828912908L;

	private UUID id;
	private Node parentNode;
	private String childrenStr;
	private Set<Node> children = new TreeSet<Node>();
	private int nodeOrder;
	private String metadata;
	private String metadataWad;
	private String metadataEpm;

	/** Link to ResourceTable */
	private ResourceTable resource;
	private ResourceTable resResource;
	private ResourceTable contextResource;

	private boolean sharedRes;
	private boolean sharedNode;
	private boolean sharedNodeRes;

	private UUID sharedResUuid;
	private UUID sharedNodeUuid;
	private UUID sharedNodeResUuid;
	private String asmType;
	private String xsiType;
	private String semtag;
	private String semantictag;
	private String label;
	private String code;
	private String descr;
	private String format;

	private Long modifUserId;
	private Date modifDate;
	private Portfolio portfolio;

	public Node() {
	}

	public Node(UUID id) {
		this.id = id;
	}

	public Node(UUID id, int nodeOrder, String metadata, String metadataWad, String metadataEpm, boolean sharedRes,
			boolean sharedNode, boolean sharedNodeRes, Long modifUserId) {
		this.id = id;
		this.nodeOrder = nodeOrder;
		this.metadata = metadata;
		this.metadataWad = metadataWad;
		this.metadataEpm = metadataEpm;
		this.sharedRes = sharedRes;
		this.sharedNode = sharedNode;
		this.sharedNodeRes = sharedNodeRes;
		this.modifUserId = modifUserId;
	}

	public Node(UUID id, Node parentNode, String childrenStr, int nodeOrder, String metadata, String metadataWad,
			String metadataEpm, ResourceTable resourceTable, ResourceTable resResourceTable,
			ResourceTable resContextNodeUuid, boolean sharedRes, boolean sharedNode, boolean sharedNodeRes,
			UUID sharedResUuid, UUID sharedNodeUuid, UUID sharedNodeResUuid, String asmType, String xsiType,
			String semtag, String semantictag, String label, String code, String descr, String format, Long modifUserId,
			Date modifDate, Portfolio portfolio) {
		this.id = id;
		this.parentNode = parentNode;
		this.childrenStr = childrenStr;
		this.nodeOrder = nodeOrder;
		this.metadata = metadata;
		this.metadataWad = metadataWad;
		this.metadataEpm = metadataEpm;
		this.resource = resourceTable;
		this.resResource = resResourceTable;
		this.contextResource = resContextNodeUuid;
		this.sharedRes = sharedRes;
		this.sharedNode = sharedNode;
		this.sharedNodeRes = sharedNodeRes;
		this.sharedResUuid = sharedResUuid;
		this.sharedNodeUuid = sharedNodeUuid;
		this.sharedNodeResUuid = sharedNodeResUuid;
		this.asmType = asmType;
		this.xsiType = xsiType;
		this.semtag = semtag;
		this.semantictag = semantictag;
		this.label = label;
		this.code = code;
		this.descr = descr;
		this.format = format;
		this.modifUserId = modifUserId;
		this.modifDate = modifDate;
		this.portfolio = portfolio;
	}

	/**
	 * Universally Unique IDentifier (UUID)
	 */
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
//	@Columns(columns = {  })
	@Column(name = "node_uuid", unique = true, nullable = false)
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "node_parent_uuid")
	public Node getParentNode() {
		return this.parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	@Column(name = "node_children_uuid")
	public String getChildrenStr() {
		return this.childrenStr;
	}

	public void setChildrenStr(String childrenStr) {
		this.childrenStr = childrenStr;
	}

	@Column(name = "node_order", nullable = false)
	public int getNodeOrder() {
		return this.nodeOrder;
	}

	public void setNodeOrder(int nodeOrder) {
		this.nodeOrder = nodeOrder;
	}

	@Column(name = "metadata", nullable = false, length = 65535)
	public String getMetadata() {
		return this.metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	@Column(name = "metadata_wad", nullable = false, length = 65535)
	public String getMetadataWad() {
		return this.metadataWad;
	}

	public void setMetadataWad(String metadataWad) {
		this.metadataWad = metadataWad;
	}

	@Column(name = "metadata_epm", nullable = false, length = 65535)
	public String getMetadataEpm() {
		return this.metadataEpm;
	}

	public void setMetadataEpm(String metadataEpm) {
		this.metadataEpm = metadataEpm;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_node_uuid", referencedColumnName = "node_uuid")
	public ResourceTable getResource() {
		return this.resource;
	}

	public void setResource(ResourceTable resource) {
		this.resource = resource;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_res_node_uuid", referencedColumnName = "node_uuid")
	public ResourceTable getResResource() {
		return this.resResource;
	}

	public void setResResource(ResourceTable resResource) {
		this.resResource = resResource;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_context_node_uuid", referencedColumnName = "node_uuid")
	public ResourceTable getContextResource() {
		return this.contextResource;
	}

	public void setContextResource(ResourceTable contextResource) {
		this.contextResource = contextResource;
	}

	@Column(name = "shared_res", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean isSharedRes() {
		return this.sharedRes;
	}

	public void setSharedRes(boolean sharedRes) {
		this.sharedRes = sharedRes;
	}

	@Column(name = "shared_node", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean getSharedNode() {
		return this.sharedNode;
	}

	public void setSharedNode(boolean sharedNode) {
		this.sharedNode = sharedNode;
	}

	@Column(name = "shared_node_res", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean isSharedNodeRes() {
		return this.sharedNodeRes;
	}

	public void setSharedNodeRes(boolean sharedNodeRes) {
		this.sharedNodeRes = sharedNodeRes;
	}

	@Column(name = "shared_res_uuid")
	public UUID getSharedResUuid() {
		return this.sharedResUuid;
	}

	public void setSharedResUuid(UUID sharedResUuid) {
		this.sharedResUuid = sharedResUuid;
	}

	@Column(name = "shared_node_uuid")
	public UUID getSharedNodeUuid() {
		return this.sharedNodeUuid;
	}

	public void setSharedNodeUuid(UUID sharedNodeUuid) {
		this.sharedNodeUuid = sharedNodeUuid;
	}

	@Column(name = "shared_node_res_uuid")
	public UUID getSharedNodeResUuid() {
		return this.sharedNodeResUuid;
	}

	public void setSharedNodeResUuid(UUID sharedNodeResUuid) {
		this.sharedNodeResUuid = sharedNodeResUuid;
	}

	@Column(name = "asm_type", length = 50)
	public String getAsmType() {
		return this.asmType;
	}

	public void setAsmType(String asmType) {
		this.asmType = asmType;
	}

	@Column(name = "xsi_type", length = 50)
	public String getXsiType() {
		return this.xsiType;
	}

	public void setXsiType(String xsiType) {
		this.xsiType = xsiType;
	}

	@Column(name = "semtag", length = 250)
	public String getSemtag() {
		return this.semtag;
	}

	public void setSemtag(String semtag) {
		this.semtag = semtag;
	}

	@Column(name = "semantictag", length = 250)
	public String getSemantictag() {
		return this.semantictag;
	}

	public void setSemantictag(String semantictag) {
		this.semantictag = semantictag;
	}

	@Column(name = "label", length = 250)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "descr", length = 250)
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "format", length = 30)
	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Column(name = "modif_user_id", nullable = false)
	public Long getModifUserId() {
		return this.modifUserId;
	}

	public void setModifUserId(Long modifUserId) {
		this.modifUserId = modifUserId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modif_date", length = 19)
	public Date getModifDate() {
		return this.modifDate;
	}

	public void setModifDate(Date modifDate) {
		this.modifDate = modifDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "portfolio_id")
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	@OneToMany(mappedBy = "parentNode", fetch = FetchType.LAZY)
	public Set<Node> getChildren() {
		return children;
	}

	public void setChildren(Set<Node> children) {
		this.children = children;
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
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void addPortfolio(Portfolio portfolio) {
		portfolio.setRootNode(this);
	}

	/**
	 * Beware - in JPA to persist this change you must merge or remove the child.
	 * Merging the parent will not cascade to the child because it no longer has a
	 * reference to the child.
	 */
	public void removePortfolio(Portfolio portfolio) {
		portfolio.setRootNode(null);
	}

}