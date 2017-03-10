package bos.sshproject.decidezone.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.decidezone.dao.IDecidedzoneDao;
import bos.sshproject.decidezone.domin.Decidezone;
import bos.sshproject.decidezone.service.IDecidedzoneService;
import bos.sshproject.subarea.dao.ISubareaDao;
import bos.sshproject.subarea.domin.Subarea;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

	@Resource
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private ISubareaDao subareaDao;
	@Override
	public void add(Decidezone model, String[] subareaid) {
		for (String sid : subareaid) {
			Subarea subarea = subareaDao.findById(sid);
			subarea.setDecidedzone(model);
		}
		decidedzoneDao.save(model);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		
		 decidedzoneDao.pageQuery(pageBean);
	}
	

}
