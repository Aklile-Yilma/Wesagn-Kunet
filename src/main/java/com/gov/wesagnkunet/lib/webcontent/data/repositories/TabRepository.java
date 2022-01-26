package com.gov.wesagnkunet.lib.webcontent.data.repositories;

import java.util.List;

import com.gov.wesagnkunet.lib.webcontent.data.models.Tab;

import org.springframework.data.repository.CrudRepository;

public interface TabRepository extends CrudRepository<Tab, Integer>{

	public List<Tab> findByClazzOrderByRelativeOrderAsc(String clazz);

	public List<Tab> findByClazzAndParentTabOrderByRelativeOrderAsc(String clazz, Tab parentTab);

	public Tab findByText(String text);

}
