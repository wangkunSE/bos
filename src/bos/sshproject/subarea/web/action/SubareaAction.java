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
		//�ڲ�ѯ֮ǰ��װ����
		DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		Region region = model.getRegion();
		if( StringUtils.isNotBlank(addresskey)){
			//���յ�ַ�ؼ���ģ����ѯ
			detachedCriteria2.add(Restrictions.like("addresskey", addresskey));
		}
		if(region!=null){
			
			detachedCriteria2.createAlias("region", "r");
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			if( StringUtils.isNotBlank(province)){
				//����ʡ�ؼ���ģ����ѯ
				detachedCriteria2.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if( StringUtils.isNotBlank(city)){
				//�����йؼ���ģ����ѯ
				detachedCriteria2.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if( StringUtils.isNotBlank(district)){
				//�������ؼ���ģ����ѯ
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
		
		//���ڴ��д���һ��Excel�ļ�
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//����һ��sheetҳ
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("��������");
		//����������
		HSSFRow headRow = hssfSheet.createRow(0);
		headRow.createCell(0).setCellValue("��������");
		headRow.createCell(1).setCellValue("������");
		headRow.createCell(2).setCellValue("��ַ�ؼ���");
		headRow.createCell(3).setCellValue("ʡ����");
		
		for(Subarea subarea :list){
			HSSFRow dataRow = hssfSheet.createRow(hssfSheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
			dataRow.createCell(2).setCellValue(subarea.getAddresskey());
			Region region = subarea.getRegion();
			dataRow.createCell(3).setCellValue(region.getProvince()+region.getCity()+region.getDistrict());
		}
		
		String filename = "��������.xls";
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		//һ��������ͷ
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletActionContext.getResponse().setContentType(mimeType);
		ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
		
		hssfWorkbook.write(outputStream);
		return NONE;
	}
}
