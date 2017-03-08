package bos.sshproject.subarea.service;

import java.util.List;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.subarea.domin.Subarea;

public interface ISubareaService {

	void save(Subarea model);

	void pageQuery(PageBean pageBean);

	List<Subarea> findAll();

}
