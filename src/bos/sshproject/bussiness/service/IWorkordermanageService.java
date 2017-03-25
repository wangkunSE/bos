package bos.sshproject.bussiness.service;

import java.util.List;

import bos.sshproject.bussiness.domain.Workordermanage;

public interface IWorkordermanageService {

	void save(Workordermanage model);

	Workordermanage findByID(String id);

	List<Workordermanage> findListNotStart();

	void start(String id);

	void checkWorkordermanage(String taskId, Integer check, String businessKey);

}
