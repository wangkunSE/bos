package bos.sshproject.process.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
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
}
