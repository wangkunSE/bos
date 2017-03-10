package bos.sshproject.decidezone.service;

import java.util.List;

import bos.sshproject.base.page.PageBean;
import bos.sshproject.decidezone.domin.Decidezone;

public interface IDecidedzoneService {

	void add(Decidezone model, String[] subareaid);

	void pageQuery(PageBean pageBean);

}
