package bos.sshproject.region.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.base.page.PageBean;
import bos.sshproject.region.domin.Region;
import bos.sshproject.region.service.IRegionService;
import bos.sshproject.utils.PinYin4jUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 区域管理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	
	@Autowired
	private IRegionService regionService;

	
	private File myFile;
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	
	public String importXls() throws FileNotFoundException, IOException{
		
		String flag = "1";
		try {
			//使用poi解析Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(myFile));
			HSSFSheet sheetAt = workbook.getSheetAt(0);
			List<Region> list = new ArrayList<Region>();
			
			for (Row row : sheetAt) {
				
				int rowNum = row.getRowNum();
				if( rowNum == 0){
					continue;
				}
				
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				
				Region region = new Region(id, province, city, district, postcode, null, null, null);
				
				city = city.substring(0,city.length() - 1);
				String[] stringToPinyin = PinYin4jUtils.stringToPinyin(city);
				String citycode = StringUtils.join(stringToPinyin,"");
				
				//简码
				province = province.substring(0,province.length()-1);
				district = district.substring(0,district.length()-1);
				String info = province+city+district;
				String[] headByString = PinYin4jUtils.getHeadByString(info);
				String shortcode = StringUtils.join(headByString,"");
				
				region.setCitycode(citycode);
				region.setShortcode(shortcode);
				
				list.add(region);
			}
			
			regionService.saveBatch(list);
			
		} catch (Exception e) {
			
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		
		return NONE;
	}
	
	public String pageQuery() throws IOException{
		
		regionService.pageQuery(pageBean);
		
		this.writePageBean2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize"});
		
		return NONE;
	}

}
