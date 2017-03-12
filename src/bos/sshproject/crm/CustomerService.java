package bos.sshproject.crm;

import java.util.List;


/**
 * 远程调用crm的借口
 * @author Administrator
 *
 */
public interface CustomerService {
	/**
	 *  查找没有关联到客户的定区
	 * @return
	 */
	public List<Customer> findnoassociationCustomers();

	/**
	 *  查找关系到客户的定区
	 * @param decidedZoneId
	 * @return
	 */
	public List<Customer> findhasassociationCustomers(String decidedZoneId);

	/**
	 * 给定区分配客户
	 * @param customerIds
	 * @param decidedZoneId
	 */
	public void assignCustomersToDecidedZone(Integer[] customerIds, String decidedZoneId);
	
	/**
	 * 根据手机号查询客户
	 * @param number
	 * @return
	 */
	public Customer findCustomerByPhoneNumber(String number);
	
	/**
	 * 根据取件地址查询定区id
	 * @param address
	 * @return
	 */
	public String finDecidedzoneIdByPickaddress(String address);
}
