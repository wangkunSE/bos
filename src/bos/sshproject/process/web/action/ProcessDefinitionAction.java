package bos.sshproject.process.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {

	
	@Resource
	private RepositoryService repositoryService;
	/**
	 * ��ѯ���°汾���̶����б�
	 */
	public String list(){
		
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		//��ȡ���°汾���Ұ���������
		query.latestVersion().orderByProcessDefinitionName().desc();
		List<ProcessDefinition> list = query.list();
		//ѹջ
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}
	
	private File zipFile;
	public void setZipFile(File zipFile) {
		this.zipFile = zipFile;
	}
	/**
	 * �������̶���
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String deploy() throws FileNotFoundException{
		
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder.addZipInputStream(new ZipInputStream(new FileInputStream(zipFile)));
		deploymentBuilder.deploy();
		return "toList";
	}
	
	private String id;
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * չʾͼƬ
	 * @return
	 */
	public String showpng(){
		//��ȡpng��Ӧ��������
		InputStream diagram = repositoryService.getProcessDiagram(id);
		ActionContext.getContext().getValueStack().set("pngDiagram", diagram);
		return "viewpng";
	}
	
	public String delete(){
		
		String delTag = "0";
		//�������̶���ID��ѯ����ID
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		query.processDefinitionId(id);
		ProcessDefinition result = query.singleResult();
		String deploymentId = result.getDeploymentId();
		
		try {
			repositoryService.deleteDeployment(deploymentId);
		} catch (Exception e) {
			//˵����ǰҪɾ�������̶���������ڱ�ʹ��
			delTag = "1";
			ActionContext.getContext().getValueStack().set("delTag", delTag);
			ProcessDefinitionQuery query2 = repositoryService.createProcessDefinitionQuery();
			//��ȡ���°汾���Ұ���������
			query2.latestVersion().orderByProcessDefinitionName().desc();
			List<ProcessDefinition> list = query2.list();
			//ѹջ
			ActionContext.getContext().getValueStack().set("list", list);
			return "list";
		}
		
		return "toList";
	}
	

}
