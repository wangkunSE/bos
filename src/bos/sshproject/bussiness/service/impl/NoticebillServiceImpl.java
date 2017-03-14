package bos.sshproject.bussiness.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bos.sshproject.bussiness.dao.INoticebillDao;
import bos.sshproject.bussiness.dao.IWorkbillDao;
import bos.sshproject.bussiness.domain.Noticebill;
import bos.sshproject.bussiness.domain.Workbill;
import bos.sshproject.bussiness.service.INoticebillService;
import bos.sshproject.crm.CustomerService;
import bos.sshproject.decidezone.dao.IDecidedzoneDao;
import bos.sshproject.decidezone.domain.Decidezone;
import bos.sshproject.staff.domain.Staff;
import bos.sshproject.user.domain.User;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

	@Resource
	private INoticebillDao noticebillDao;
	
	@Autowired
	private CustomerService customerService;

	@Resource
	private IDecidedzoneDao decidedzoneDao;
	
	@Resource
	private IWorkbillDao workbillDao;
	@Override
	public void save(Noticebill model) {
		noticebillDao.save(model);
		//获取取件地址
		String pickaddress = model.getPickaddress();
		//根据取件地址查询定区id---从crm服务查询
		String did = customerService.finDecidedzoneIdByPickaddress(pickaddress);
		//通过定区id查询取派员
		if(did!=null){
			
			Decidezone decidezone = decidedzoneDao.findById(did);
			Staff staff = decidezone.getStaff();
			model.setStaff(staff);
			model.setOrdertype("自动");
			//为取派员创建工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//当前时间
			workbill.setPickstate("未取件");
			workbill.setRemark(model.getRemark());
			workbill.setStaff(staff);
			workbill.setType("新单");
			//保存工单
			workbillDao.save(workbill);
			//发送短信提示取派员
		}else{
			model.setOrdertype("人工");
		}
		
	}
}
