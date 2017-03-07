package bos.sshproject.region.service;

import java.util.List;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.region.domin.Region;

public interface IRegionService {

	void saveBatch(List<Region> list);

	void pageQuery(PageBean pageBean);

	List<Region> findAll();

}
