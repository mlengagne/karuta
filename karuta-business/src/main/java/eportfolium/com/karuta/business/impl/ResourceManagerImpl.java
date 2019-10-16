package eportfolium.com.karuta.business.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeType;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import eportfolium.com.karuta.business.contract.NodeManager;
import eportfolium.com.karuta.business.contract.ResourceManager;
import eportfolium.com.karuta.consumer.contract.dao.PortfolioDao;
import eportfolium.com.karuta.consumer.contract.dao.ResourceTableDao;
import eportfolium.com.karuta.consumer.util.DomUtils;
import eportfolium.com.karuta.model.bean.GroupRights;
import eportfolium.com.karuta.model.bean.Node;
import eportfolium.com.karuta.model.bean.ResourceTable;
import eportfolium.com.karuta.model.exception.BusinessException;
import eportfolium.com.karuta.model.exception.DoesNotExistException;
import eportfolium.com.karuta.model.exception.GenericBusinessException;

public class ResourceManagerImpl extends BaseManager implements ResourceManager {

	@Autowired
	private PortfolioDao portfolioDao;

	@Autowired
	private NodeManager nodeManager;

	@Autowired
	private ResourceTableDao resourceTableDao;

	public String getResource(MimeType outMimeType, String nodeParentUuid, Long userId, Long groupId)
			throws BusinessException {

		if (!hasRight(userId, groupId, nodeParentUuid, GroupRights.READ)) {
			throw new GenericBusinessException("403 FORBIDDEN : No READ credential");
		}

		ResourceTable rt = resourceTableDao.getResourceByNodeParentUuid(nodeParentUuid);

		String result = "<asmResource id=\"" + rt.getId().toString() + "\" contextid=\"" + nodeParentUuid + "\"  >"
				+ rt.getXsiType() + "</asmResource>";

		return result;
	}

	/** Récupère le noeud, et assemble les ressources, s'il y en a */
	public String getResource(UUID nodeUuid) {
		return getResource(nodeUuid.toString());
	}

	/** Récupère le noeud, et assemble les ressources, s'il y en a */
	public String getResource(String nodeUuid) {
		String result = "";

		try {
			Node resNode = nodeDao.findById(UUID.fromString(nodeUuid));
			String m_epm = resNode.getMetadataEpm();
			if (m_epm == null)
				m_epm = "";
			result += "<" + resNode.getAsmType() + " id='" + resNode.getId().toString() + "'>";
			result += "<metadata " + resNode.getMetadata() + "/>";
			result += "<metadata-epm " + m_epm + "/>";
			result += "<metadata-wad " + resNode.getMetadataWad() + "/>";

			ResourceTable resResource = resNode.getResource();
			if (resResource != null && resResource.getId() != null) {
				result += "<asmResource id='" + resResource.getId().toString() + "' contextid='"
						+ resNode.getId().toString() + "' xsi_type='" + resResource.getXsiType() + "'>";
				result += resResource.getContent();
				result += "</asmResource>";
			}

			resResource = resNode.getResResource();
			if (resResource != null && resResource.getId() != null) {
				result += "<asmResource id='" + resResource.getId().toString() + "' contextid='"
						+ resNode.getId().toString() + "' xsi_type='" + resResource.getXsiType() + "'>";
				result += resResource.getContent();
				result += "</asmResource>";

			}

			resResource = resNode.getContextResource();
			if (resResource != null && resResource.getId() != null) {
				result += "<asmResource id='" + resResource.getId().toString() + "' contextid='"
						+ resNode.getId().toString() + "' xsi_type='" + resResource.getXsiType() + "'>";
				result += resResource.getContent();
				result += "</asmResource>";

			}
			result += "</" + resNode.getAsmType() + ">";
		} catch (DoesNotExistException e) {
		}

		return result;
	}

	public String getResources(MimeType outMimeType, String portfolioUuid, Long userId, Long groupId) throws Exception {
		List<ResourceTable> res = resourceTableDao.getResourcesByPortfolioUUID(portfolioUuid);
		String returnValue = "";
		if (outMimeType.getSubtype().equals("xml")) {
			returnValue += "<resources>";
			for (ResourceTable rt : res) {
				returnValue += "<resource " + DomUtils.getXmlAttributeOutput("id", rt.getNode().getId().toString())
						+ " />";
			}
			returnValue += "</resources>";
		} else {
			returnValue += "{";
			boolean firstNode = true;
			for (ResourceTable rt : res) {
				if (firstNode)
					firstNode = false;
				else
					returnValue += " , ";
				returnValue += "resource: { " + DomUtils.getJsonAttributeOutput("id", rt.getNode().getId().toString())
						+ " } ";
			}
			returnValue += "}";
		}
		return returnValue;
	}

	public Integer changeResource(MimeType inMimeType, String nodeParentUuid, String in, Long userId, Long groupId)
			throws BusinessException, Exception {

		in = DomUtils.filterXmlResource(in);

		int retVal = -1;
		ResourceTable data = resourceTableDao.getResourceByNodeParentUuid(nodeParentUuid);
		String nodeUuid = data.getId().toString();

		Document doc = DomUtils.xmlString2Document(in, new StringBuffer());
		// Puis on le recrée
		org.w3c.dom.Node node;

		node = (doc.getElementsByTagName("asmResource")).item(0);

		if (!hasRight(userId, groupId, nodeParentUuid, GroupRights.WRITE))
			throw new GenericBusinessException("403 FORBIDDEN : No WRITE credential");

		portfolioDao.updateTimeByNode(nodeParentUuid);

		retVal = resourceTableDao.updateResource(nodeUuid, null, DomUtils.getInnerXml(node), userId);

		return retVal;

	}

	public String addResource(MimeType inMimeType, String nodeParentUuid, String in, Long userId, Long groupId)
			throws BusinessException, Exception {
		if (!credentialDao.isAdmin(userId))
			throw new GenericBusinessException("403 FORBIDDEN : No ADMIN right");

		in = DomUtils.filterXmlResource(in);

		if (!hasRight(userId, groupId, nodeParentUuid, GroupRights.WRITE)) {
			throw new GenericBusinessException("403 FORBIDDEN : No WRITE credential");
		} else
			nodeManager.addNode(inMimeType, nodeParentUuid, in, userId, groupId, true);

		return "";
	}

	public void deleteResource(String resourceUuid, Long userId, Long groupId)
			throws DoesNotExistException, BusinessException {
		if (!credentialDao.isAdmin(userId))
			throw new GenericBusinessException("403 FORBIDDEN : No admin right");

		if (getRights(userId, groupId, resourceUuid).isDelete()) {
			resourceTableDao.removeById(UUID.fromString(resourceUuid));
		}
	}

	public void changeResourceByXsiType(String nodeUuid, String xsiType, String content, Long userId) throws Exception {
		if (StringUtils.equals(xsiType, "nodeRes")) {
			resourceTableDao.updateResResource(nodeUuid, content, userId);
			/// Interprétation du code XML.
			Document doc = DomUtils.xmlString2Document(
					"<?xml version='1.0' encoding='UTF-8' standalone='no'?><res>" + content + "</res>",
					new StringBuffer());
			NodeList nodes = doc.getElementsByTagName("code");
			org.w3c.dom.Node code = nodes.item(0);
			if (code != null) {
				org.w3c.dom.Node codeContent = code.getFirstChild();

				String codeVal;
				if (codeContent != null) {
					codeVal = codeContent.getNodeValue();
					// Vérifier si le code existe déjà.
					if (nodeDao.isCodeExist(codeVal, nodeUuid)) {
						throw new GenericBusinessException("CONFLICT : code already exists.");
					} else {
						if (nodeDao.updateNodeCode(nodeUuid, codeVal) > 0) {
							throw new GenericBusinessException("Cannot update node code");
						}
					}
				}
			}
		} else if (StringUtils.equals(xsiType, "context")) {
			resourceTableDao.updateContextResource(nodeUuid, content, userId);

		} else {
			resourceTableDao.updateResource(nodeUuid, content, userId);
		}

	}

}