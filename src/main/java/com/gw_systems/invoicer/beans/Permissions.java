package com.gw_systems.invoicer.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Permissions {
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, name="PERMISSION_SEQ", sequenceName="PERMISSION_SEQ")
	@GeneratedValue(generator="PERMISSION_SEQ", strategy=GenerationType.SEQUENCE)
	int id;
	
	@Column(name="permission", nullable=false)
	String permission;
	
	@Column(name="description", nullable=true)
	String description;
}
