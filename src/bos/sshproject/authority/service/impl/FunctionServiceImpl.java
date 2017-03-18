package bos.sshproject.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.authority.dao.IFunctionDao;
import bos.sshproject.authority.domain.Function;
import bos.sshproject.authority.service.IFunctionService;
import bos.sshproject.base.page.PageBean;
import bos.sshproject.user.domain.User;
import bos.sshproject.utils.BOScontext;

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

	@Override
	public List<Function> findMenu() {
		User user = BOScontext.getLoginUser();
		List<Function> list = null;
		if(user.getUsername().equals("admin")){
			//查询所有菜单
			list = functionDao.findAllMenu(); 
		}else{
			list = functionDao.findMenuByUserid(user.getId());
		}
		return list;
	}
}
