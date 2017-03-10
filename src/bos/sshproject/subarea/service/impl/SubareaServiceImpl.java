package bos.sshproject.subarea.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.subarea.dao.ISubareaDao;
import bos.sshproject.subarea.domin.Subarea;
import bos.sshproject.subarea.service.ISubareaService;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

	@Resource
	private ISubareaDao subareaDao;

	@Override
	public void save(Subarea model) {
		
		subareaDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return subareaDao.list();
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(detachedCriteria);
	}
}
