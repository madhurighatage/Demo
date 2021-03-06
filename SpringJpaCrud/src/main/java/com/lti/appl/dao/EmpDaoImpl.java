package com.lti.appl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.appl.beans.Employee;

@Repository
public class EmpDaoImpl implements EmpDao{

	@PersistenceContext
	private EntityManager em;
	
	static List<Employee> empList= new ArrayList();

	public List<Employee> getEmpList() {
		
		Query qry = em.createQuery("select e from Employee e");
		empList=qry.getResultList();		
		return empList;
	}
	@Override
	@Transactional
	public int addEmp(Employee e) {
		em.persist(e);
        return e.getEmpNo();
	}

	@Override
	@Transactional
	public int updateEmp(int empNo, double sal) {
		Employee e=em.find(Employee.class, empNo);
		
		System.out.println(e);
		e.setEmpSal(sal);
		em.merge(e);
		
		return e.getEmpNo();
	}

	@Override
	public Employee getByEmpId(int empNo) {
		Employee e=em.find(Employee.class, empNo);		
		return e;
		
	}
	
	
}
