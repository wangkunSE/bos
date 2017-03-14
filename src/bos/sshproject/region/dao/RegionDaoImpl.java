package bos.sshproject.region.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import bos.sshproject.base.dao.impl.BaseDaoImpl;
import bos.sshproject.base.page.PageBean;
import bos.sshproject.region.domain.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@Override
	public List<Region> findByQ(String q) {
		String hql = "FROM Region WHERE province LIKE ? OR city LIKE ? OR district LIKE ?";
		
		return this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%");
	}

}
