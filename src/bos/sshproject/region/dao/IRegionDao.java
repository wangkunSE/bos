package bos.sshproject.region.dao;

import java.util.List;

import bos.sshproject.base.dao.IBaseDao;
import bos.sshproject.region.domin.Region;

public interface IRegionDao extends IBaseDao<Region> {

	List<Region> findByQ(String q);

}
