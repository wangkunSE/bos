package bos.sshproject.crm;

import java.util.List;


/**
 * Զ�̵���crm�Ľ��
 * @author Administrator
 *
 */
public interface CustomerService {
	/**
	 *  ����û�й������ͻ��Ķ���
	 * @return
	 */
	public List<Customer> findnoassociationCustomers();

	/**
	 *  ���ҹ�ϵ���ͻ��Ķ���
	 * @param decidedZoneId
	 * @return
	 */
	public List<Customer> findhasassociationCustomers(String decidedZoneId);

	/**
	 * ����������ͻ�
	 * @param customerIds
	 * @param decidedZoneId
	 */
	public void assignCustomersToDecidedZone(Integer[] customerIds, String decidedZoneId);
	
	/**
	 * �����ֻ��Ų�ѯ�ͻ�
	 * @param number
	 * @return
	 */
	public Customer findCustomerByPhoneNumber(String number);
	
	/**
	 * ����ȡ����ַ��ѯ����id
	 * @param address
	 * @return
	 */
	public String finDecidedzoneIdByPickaddress(String address);
}
