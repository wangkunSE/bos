package bos.sshproject.authority.service;

import java.util.List;

import bos.sshproject.authority.domain.Function;
import bos.sshproject.base.page.PageBean;

public interface IFunctionService {

	void pageQuery(PageBean pageBean);

	List<Function> findAll();

	void add(Function model);

	List<Function> findMenu();

}
