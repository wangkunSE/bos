package bos.sshproject.base.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import bos.sshproject.authority.service.IFunctionService;
import bos.sshproject.authority.service.IRoleService;
import bos.sshproject.base.page.PageBean;
import bos.sshproject.bussiness.service.INoticebillService;
import bos.sshproject.bussiness.service.IWorkordermanageService;
import bos.sshproject.crm.CustomerService;
import bos.sshproject.decidezone.service.IDecidedzoneService;
import bos.sshproject.region.domain.Region;
import bos.sshproject.region.service.IRegionService;
import bos.sshproject.staff.service.IStaffService;
import bos.sshproject.subarea.service.ISubareaService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	/**
	 * 构造方法 动态创建model
	 */
	public BaseAction() {
		
		ParameterizedType genericSuperclass = null;
		if(this.getClass().getGenericSuperclass() instanceof ParameterizedType){
			genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		}else{
		//当前为代理Action
			genericSuperclass = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
		}
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> clazz = (Class<T>) actualTypeArguments[0];
		detachedCriteria = DetachedCriteria.forClass(clazz);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			model = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	@Autowired
	protected IRoleService roleService;
	
	@Autowired
	protected IFunctionService functionService;
	
	@Autowired
	protected IWorkordermanageService workordermanageService;
	
	@Autowired
	protected IDecidedzoneService decidedzoneService;
	
	@Autowired
	protected INoticebillService noticebillService;
	
	@Autowired
	protected IRegionService regionService;
	
	@Autowired
	protected IStaffService staffService;
	
	@Autowired
	protected ISubareaService subareaService;
	
	@Autowired
	protected CustomerService customerService;
	protected T model;
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	
	public  void  writeList2Json(List list, String[] excludes) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONArray jsonObject = JSONArray.fromObject(list, jsonConfig);
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType(
				"text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	
	//分页用
	protected PageBean pageBean = new PageBean();
	DetachedCriteria detachedCriteria = null;
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}
	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	

	
	public void writePageBean2Json(PageBean pageBean,String[] excludes) throws IOException{
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
		
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
	
	public void writeObject2Json(Object object,String[] excludes) throws IOException{
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(object,jsonConfig);
		
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
