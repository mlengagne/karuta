package eportfolium.com.karuta.consumer.contract.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import eportfolium.com.karuta.model.bean.GroupRightInfo;
import eportfolium.com.karuta.model.bean.Portfolio;
import eportfolium.com.karuta.model.exception.DoesNotExistException;

/**
 * @author mlengagne
 *
 */
public interface GroupRightInfoDao {

	void persist(GroupRightInfo transientInstance);

	void remove(GroupRightInfo persistentInstance);

	GroupRightInfo merge(GroupRightInfo detachedInstance);

	GroupRightInfo findById(Serializable id) throws DoesNotExistException;

	/**
	 * @return Tous les rôles disponibles
	 */
	List<GroupRightInfo> findAll();

	/**
	 * Obtenir les roles reliés à un portfolio
	 * 
	 * @param portfolioUuid
	 * @return
	 */
	List<GroupRightInfo> getByPortfolioID(UUID portfolioUuid);

	/**
	 * Obtenir les roles reliés à un portfolio
	 * 
	 * @param portfolioUuid
	 * @return
	 */

	List<GroupRightInfo> getByPortfolioID(String portfolioUuid);

	/**
	 * Intersection d'un portfolio et d'un role
	 * 
	 * @param portfolioUuid
	 * @param label
	 * @return
	 */
	GroupRightInfo getByPortfolioAndLabel(String portfolioUuid, String label);

	/**
	 * Intersection d'un portfolio et d'un role
	 * 
	 * @param portfolioUuid
	 * @param label
	 * @return
	 */
	GroupRightInfo getByPortfolioAndLabel(UUID portfolioUuid, String label);

	/**
	 * Vérifie si le role existe
	 * 
	 * @param grid
	 * @return
	 */
	boolean groupRightInfoExists(Long grid);

	List<GroupRightInfo> getDefaultByPortfolio(UUID portfolioUuid);

	List<GroupRightInfo> getDefaultByPortfolio(String portfolioUuid);

	Long getIdByNodeAndLabel(String nodeUuid, String label);

	List<Long> getByNodeAndLabel(UUID nodeUuid, List<String> labels);

	List<Long> getByNodeAndLabel(String nodeUuid, List<String> labels);

	/**
	 * Crée le role pour ce portfolio.
	 * 
	 * @param portfolio
	 * @param role
	 * @return
	 */
	Long add(Portfolio portfolio, String role);

	/**
	 * Crée le role pour ce portfolio.
	 * 
	 * @param portfolioUuid
	 * @param role
	 * @return
	 */
	Long add(String portfolioUuid, String role);

	void removeById(Long groupRightInfoId) throws Exception;

	boolean isOwner(Long userId, Long rrgId);

	/**
	 * Intersection d'un portfolio et d'un utilisateur
	 * 
	 * @param portfolioUuid
	 * @param userId
	 * @return liste des roles de l'utilisateur pour ce portfolio.
	 */
	List<GroupRightInfo> getByPortfolioAndUser(String portfolioUuid, Long userId);

	/**
	 * Intersection d'un portfolio et d'un utilisateur
	 * 
	 * @param portfolioUuid
	 * @param userId
	 * @return liste des roles de l'utilisateur pour ce portfolio.
	 */
	List<GroupRightInfo> getByPortfolioAndUser(UUID portfolioUuid, Long userId);

	/**
	 * Obtenir les roles reliés à une personne
	 * 
	 * @param userId
	 * @return
	 */
	List<GroupRightInfo> getByUser(Long userId);

	/**
	 * Obtenir les roles reliés à un noeud
	 * 
	 * @param nodeUuid
	 * @return
	 */
	List<GroupRightInfo> getByNode(String nodeUuid);

	/**
	 * Obtenir les roles reliés à un noeud
	 * 
	 * @param nodeUuid
	 * @return
	 */
	List<GroupRightInfo> getByNode(UUID nodeUuid);

	Map<String, Object> getRolesToBeNotified(Long groupId, Long userId, String uuid);

	ResultSet getMysqlGroupRightsInfos(Connection con) throws SQLException;

	void removeAll();

}