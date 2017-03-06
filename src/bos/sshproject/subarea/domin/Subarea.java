package bos.sshproject.subarea.domin;

import bos.sshproject.decidezone.domin.Decidezone;
import bos.sshproject.region.domin.Region;

/**
 * Subarea entity. @author MyEclipse Persistence Tools
 */

public class Subarea implements java.io.Serializable {

	// Fields

	private String id;
	private Decidezone decidezone;
	private Region region;
	private String addresskey;
	private String startnum;
	private String endnum;
	private String single;
	private String position;

	// Constructors

	/** default constructor */
	public Subarea() {
	}

	/** minimal constructor */
	public Subarea(String id) {
		this.id = id;
	}

	/** full constructor */
	public Subarea(String id, Decidezone decidezone, Region region,
			String addresskey, String startnum, String endnum, String single,
			String position) {
		this.id = id;
		this.decidezone = decidezone;
		this.region = region;
		this.addresskey = addresskey;
		this.startnum = startnum;
		this.endnum = endnum;
		this.single = single;
		this.position = position;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Decidezone getDecidezone() {
		return this.decidezone;
	}

	public void setDecidezone(Decidezone decidezone) {
		this.decidezone = decidezone;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getAddresskey() {
		return this.addresskey;
	}

	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}

	public String getStartnum() {
		return this.startnum;
	}

	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}

	public String getEndnum() {
		return this.endnum;
	}

	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}

	public String getSingle() {
		return this.single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}