package bos.sshproject.process.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bos.sshproject.bussiness.domain.Workordermanage;
import bos.sshproject.bussiness.service.IWorkordermanageService;
import bos.sshproject.utils.BOScontext;

@Controller
@Scope("prototype")
public class TaskAction extends ActionSupport {

	@Resource
	private TaskService taskService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private IWorkordermanageService workordermanageService;
	
	public String findGroupTask(){
		
		TaskQuery query = taskService.createTaskQuery();
		String candidateUser = BOScontext.getLoginUser().getId();
		
		query.taskCandidateUser(candidateUser);
		List<Task> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);

		return "grouptasklist";
	}
	public String findPersonalTask(){
		
		TaskQuery query = taskService.createTaskQuery();
		String assignUser = BOScontext.getLoginUser().getId();
		
		query.taskAssignee(assignUser);
		List<Task> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "personaltasklist";
	}
	
	String taskId;
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskId() {
		return taskId;
	}
	
	public String showData() throws IOException{
		Map<String, Object> variables = taskService.getVariables(taskId);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(variables.toString());
		return NONE;
	}
	
	public String takeTask(){
		
		
		String userId = BOScontext.getLoginUser().getId();
		taskService.claim(taskId, userId );
		
		return "toGrouptasklist";
	}
	
	
	private Integer check;
	public void setCheck(Integer check) {
		this.check = check;
	}
	public Integer getCheck() {
		return check;
	}
	/**
	 * 办理审核工作单
	 * @return
	 */
	public String checkWorkOrderManage(){
		//跳转审核页面
		//跳转到一个工作单页面
		//根据任务ID查询任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//根据任务对象查询流程实例id
		String processInstanceId = task.getProcessInstanceId();
		//根据流程实例id查询流程实例
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		
		String businessKey = processInstance.getBusinessKey();
		
		Workordermanage workorder = workordermanageService.findByID(businessKey);
		
		if( check == null){
			ActionContext.getContext().getValueStack().set("map", workorder);
			return "check";
		}else{
			//办理审核工作单
			workordermanageService.checkWorkordermanage(taskId,check,businessKey);
			return "topersonaltasklist";
		}
		
	}
	public String outStore(){
		
		taskService.complete(taskId);
		return  "topersonaltasklist";
	}
	public String transferGoods(){
		
		taskService.complete(taskId);
		return  "topersonaltasklist";
	}
	public String receive(){
		
		taskService.complete(taskId);
		return  "topersonaltasklist";
	}
}
