package bos.sshproject.subarea.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.region.domin.Region;
import bos.sshproject.subarea.domin.Subarea;
import bos.sshproject.utils.FileUtils;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

	public String add(){
		
		subareaService.save(model);
		return "list";
	}
	
	public String pageQuery() throws IOException{
		//在查询之前封装条件
		DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		Region region = model.getRegion();
		if( StringUtils.isNotBlank(addresskey)){
			//按照地址关键字模糊查询
			detachedCriteria2.add(Restrictions.like("addresskey", addresskey));
		}
		if(region!=null){
			
			detachedCriteria2.createAlias("region", "r");
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			if( StringUtils.isNotBlank(province)){
				//按照省关键字模糊查询
				detachedCriteria2.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if( StringUtils.isNotBlank(city)){
				//按照市关键字模糊查询
				detachedCriteria2.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if( StringUtils.isNotBlank(district)){
				//按照区关键字模糊查询
				detachedCriteria2.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		
		subareaService.pageQuery(pageBean);
		String[] excludes = new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzone"};
		this.writePageBean2Json(pageBean, excludes);
		return NONE;
	}
	
	public String exportXls() throws IOException{
		List<Subarea> list = subareaService.findAll();
		
		//在内存中传建一个Excel文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//创建一个sheet页
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("分区数据");
		//创建标题行
		HSSFRow headRow = hssfSheet.createRow(0);
		headRow.createCell(0).setCellValue("分区数据");
		headRow.createCell(1).setCellValue("区域编号");
		headRow.createCell(2).setCellValue("地址关键字");
		headRow.createCell(3).setCellValue("省市区");
		
		for(Subarea subarea :list){
			HSSFRow dataRow = hssfSheet.createRow(hssfSheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
			dataRow.createCell(2).setCellValue(subarea.getAddresskey());
			Region region = subarea.getRegion();
			dataRow.createCell(3).setCellValue(region.getProvince()+region.getCity()+region.getDistrict());
		}
		
		String filename = "分区数据.xls";
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		//一个流两个头
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletActionContext.getResponse().setContentType(mimeType);
		ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
		
		hssfWorkbook.write(outputStream);
		return NONE;
	}
}
