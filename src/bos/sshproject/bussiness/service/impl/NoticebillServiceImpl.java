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
		//��ȡȡ����ַ
		String pickaddress = model.getPickaddress();
		//����ȡ����ַ��ѯ����id---��crm�����ѯ
		String did = customerService.finDecidedzoneIdByPickaddress(pickaddress);
		//ͨ������id��ѯȡ��Ա
		if(did!=null){
			
			Decidezone decidezone = decidedzoneDao.findById(did);
			Staff staff = decidezone.getStaff();
			model.setStaff(staff);
			model.setOrdertype("�Զ�");
			//Ϊȡ��Ա��������
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//׷������
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//��ǰʱ��
			workbill.setPickstate("δȡ��");
			workbill.setRemark(model.getRemark());
			workbill.setStaff(staff);
			workbill.setType("�µ�");
			//���湤��
			workbillDao.save(workbill);
			//���Ͷ�����ʾȡ��Ա
		}else{
			model.setOrdertype("�˹�");
		}
		
	}
}
