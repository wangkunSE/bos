package bos.sshproject.bussiness.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.bussiness.dao.IWorkordermanageDao;
import bos.sshproject.bussiness.domain.Workordermanage;
import bos.sshproject.bussiness.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {
	
	@Resource
	private IWorkordermanageDao workordermanageDao;

	@Override
	public void save(Workordermanage model) {
		
		model.setUpdatetime(new Date());
		workordermanageDao.save(model);
	}

	@Override
	public Workordermanage findByID(String id) {
		return workordermanageDao.findById(id);
	}

	@Override
	public List<Workordermanage> findListNotStart() {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Workordermanage.class);
		criteria.add(Restrictions.eq("start","0"));
		return workordermanageDao.findByCriteria(criteria);
	}

}
