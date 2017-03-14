package bos.sshproject.bussiness.service;

import bos.sshproject.bussiness.domain.Workordermanage;

public interface IWorkordermanageService {

	void save(Workordermanage model);

	Workordermanage findByID(String id);

}
