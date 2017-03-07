package bos.sshproject.region.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.region.dao.IRegionDao;
import bos.sshproject.region.domin.Region;
import bos.sshproject.region.service.IRegionService;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Resource
	private IRegionDao regionDao;
	@Override
	public void saveBatch(List<Region> list) {
		
		for (Region region : list) {
			regionDao.saveOrUpdate(region);
		}
		
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		
		regionDao.pageQuery(pageBean);
	}

}
