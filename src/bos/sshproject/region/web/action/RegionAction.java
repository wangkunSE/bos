package bos.sshproject.region.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import bos.sshproject.base.action.BaseAction;
import bos.sshproject.region.domin.Region;
import bos.sshproject.region.service.IRegionService;

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

}
