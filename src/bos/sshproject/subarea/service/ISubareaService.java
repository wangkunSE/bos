package bos.sshproject.subarea.service;

import java.util.List;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.subarea.domain.Subarea;

public interface ISubareaService {

	void save(Subarea model);

	void pageQuery(PageBean pageBean);

	List<Subarea> findAll();

	List<Subarea> findListNotAssociation();

}
