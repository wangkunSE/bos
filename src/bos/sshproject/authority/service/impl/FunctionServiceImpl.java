package bos.sshproject.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.authority.dao.IFunctionDao;
import bos.sshproject.authority.domain.Function;
import bos.sshproject.authority.service.IFunctionService;
import bos.sshproject.base.page.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	@Resource
	private IFunctionDao functionDao;

	@Override
	public void pageQuery(PageBean pageBean) {
		
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findAll() {
		return functionDao.list();
	}

	@Override
	public void add(Function model) {
		Function function = model.getFunction();
		if( function!=null && function.getId().equals("")){
			model.setFunction(null);
		}
		functionDao.save(model);
	}
}
