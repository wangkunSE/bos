package bos.sshproject.bussiness.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.bussiness.dao.IWorkordermanageDao;
import bos.sshproject.bussiness.domain.Workordermanage;
import bos.sshproject.bussiness.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {
	
	@Resource
	private IWorkordermanageDao workordermanageDao;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	
	@Override
	public void save(Workordermanage model) {
		
		model.setUpdatetime(new Date());
		workordermanageDao.save(model);
	}

	@Override
	public Workordermanage findByID(String id) {
		return workordermanageDao.findById(id);
	}

	@Override
	public List<Workordermanage> findListNotStart() {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Workordermanage.class);
		criteria.add(Restrictions.eq("start","0"));
		return workordermanageDao.findByCriteria(criteria);
	}

	@Override
	public void start(String id) {
		
		Workordermanage workordermanage = workordermanageDao.findById(id);
		workordermanage.setStart("1");
		String bussinessKey = id;//ҵ����������ҵ��������ֵ
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("ҵ������",workordermanage );
		runtimeService.startProcessInstanceByKey("transfer", bussinessKey, variables);
	}

	@Override
	public void checkWorkordermanage(String taskId, Integer check,String bussinessKey) {
		//�����˲�ͨ��,�޸Ĺ�����startΪ0
		Workordermanage workorder = workordermanageDao.findById(bussinessKey);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("check", check);
		//������������ѯ����ʵ��id
		String processInstanceId = task.getProcessInstanceId();
		taskService.complete(taskId,variables);
		
		if(check == 0 ){
			//��˲�ͨ��
			workorder.setStart("0");
			//ɾ����ʷ����
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}
	}

}
