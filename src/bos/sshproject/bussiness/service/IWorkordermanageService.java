package bos.sshproject.bussiness.service;

import bos.sshproject.bussiness.domin.Workordermanage;

public interface IWorkordermanageService {

	void save(Workordermanage model);

	Workordermanage findByID(String id);

}
