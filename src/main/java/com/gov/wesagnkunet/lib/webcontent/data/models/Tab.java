package com.gov.wesagnkunet.lib.webcontent.data.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lib_webcontents_tab")
public class Tab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private String url;
    
	@Column(nullable = false)
	private Integer relativeOrder;

	@Column(nullable = false)
	private String clazz;

	@ManyToOne
	private Tab parentTab;

	@OneToMany(mappedBy = "parentTab")
	private List<Tab> childrenTabs;


	public Tab(String text, String url, Integer relativeOrder, String clazz, Tab parentTab){
		this.text = text;
		this.url = url;
		this.relativeOrder = relativeOrder;
		this.clazz= clazz;
		this.parentTab = parentTab;
	}

	public boolean hasParent(){
		return parentTab != null;
	}

}
