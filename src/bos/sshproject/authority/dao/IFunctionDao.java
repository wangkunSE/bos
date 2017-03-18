package bos.sshproject.authority.dao;

import java.util.List;

import bos.sshproject.authority.domain.Function;
import bos.sshproject.base.dao.IBaseDao;

public interface IFunctionDao extends IBaseDao<Function> {

	List<Function> findListByUserid(String id);

	List<Function> findAllMenu();

	List<Function> findMenuByUserid(String id);

}
