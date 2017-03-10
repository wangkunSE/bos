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

import bos.sshproject.base.page.PageBean;
import bos.sshproject.decidezone.service.IDecidedzoneService;
import bos.sshproject.region.domin.Region;
import bos.sshproject.region.service.IRegionService;
import bos.sshproject.staff.service.IStaffService;
import bos.sshproject.subarea.service.ISubareaService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	@Autowired
	protected IDecidedzoneService decidedzoneService;
	
	
	@Autowired
	protected IRegionService regionService;
	
	@Autowired
	protected IStaffService staffService;
	
	@Autowired
	protected ISubareaService subareaService;
	
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
	

	/**
	 * 构造方法 动态创建model
	 */
	public BaseAction() {
		
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
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
	
	public void writePageBean2Json(PageBean pageBean,String[] excludes) throws IOException{
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
		
		String json = jsonObject.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
	}
}
