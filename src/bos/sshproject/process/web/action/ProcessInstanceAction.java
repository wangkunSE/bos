package bos.sshproject.process.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ProcessInstanceAction extends ActionSupport{

	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	
	/**
	 * 查看流程实例
	 * @return
	 */
	public String list(){
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
		query.orderByProcessDefinitionId().desc();
		List<ProcessInstance> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "list";
	}
	
	private String id;
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 根据流程实例id查询对应的流程变量
	 * @throws IOException 
	 */
	public String findData() throws IOException{
		Map<String, Object> variables = runtimeService.getVariables(id);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(variables.toString());
		return NONE;
	}
	
	/**
	 * 根据流程实例ID查询坐标,部署ID,图片名称
	 * @return
	 */
	public String showPng(){
		//根据流程id查询流程实例
		ProcessInstance singleResult = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
		//根据流程实例对象查询流程定义ID
		String processDefinitionId = singleResult.getProcessDefinitionId();
		//根据流程定义id查询流程定义对象
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		//根据流程定义对象查询流程部署id
		deploymentId = processDefinition.getDeploymentId();
		imageName = processDefinition.getDiagramResourceName();
		
		//查询坐标
		//1获得当前流程实例进行到哪个节点
		String activityId = singleResult.getActivityId();
		//2加载bpmn文件,获得流程定义对象
		ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		//3分局activityid获取含有坐标信息的对象
		ActivityImpl findActivity = pd.findActivity(activityId);
		int x = findActivity.getX();
		int y = findActivity.getY();
		int width = findActivity.getWidth();
		int height = findActivity.getHeight();
		
		ActionContext.getContext().getValueStack().set("x",x );
		ActionContext.getContext().getValueStack().set("y", y);
		ActionContext.getContext().getValueStack().set("width",width );
		ActionContext.getContext().getValueStack().set("height", height);
		
		return "showPng";
	}
	
	private String deploymentId;
	private String imageName;
	
	/**
	 * 获取png的输入流
	 * @return
	 */
	public String viewImage(){
		InputStream pngStream = repositoryService.getResourceAsStream(deploymentId, imageName);
		ActionContext.getContext().getValueStack().set("pngStream", pngStream);
		return "viewImage";
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
