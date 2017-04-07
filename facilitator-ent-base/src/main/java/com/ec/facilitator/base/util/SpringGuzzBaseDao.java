/*
 * Copyright 2008-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ec.facilitator.base.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.guzz.GuzzContext;
import org.guzz.dao.PageFlip;
import org.guzz.dao.WriteTemplate;
import org.guzz.orm.se.SearchExpression;
import org.guzz.orm.sql.BindedCompiledSQL;
import org.guzz.service.core.DynamicSQLService;
import org.guzz.transaction.ReadonlyTranSession;
import org.guzz.transaction.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 提供给Spring事务容器使用的BaseDao
 * @author 张荣英
 * @date 2017年4月7日 下午10:44:04
 */
public class SpringGuzzBaseDao {

	@Autowired(required = false)
	@Qualifier("guzzLocalTranMgr")
	private TransactionManager transactionManager;

	@Autowired(required = false)
	private GuzzContext guzzContext;

	@Autowired(required = false)
	private WriteTemplate writeTemplate;

	/**
	 * Return the WriteTemplate for this DAO, pre-initialized with the
	 * GuzzContext/TransactionManager or set explicitly.
	 * <p>
	 * <b>Note: The returned WriteTemplate is a shared instance.</b>
	 */
	public final WriteTemplate getWriteTemplate() {
		return this.writeTemplate;
	}

	public void setWriteTemplate(WriteTemplate writeTemplate) {
		this.writeTemplate = writeTemplate;
	}

	public Serializable insert(Object domainObject) {
		return getWriteTemplate().insert(domainObject);
	}

	public boolean update(Object domainObject) {
		return getWriteTemplate().update(domainObject);
	}

	public int executeUpdate(String id, Map params) {
		return getWriteTemplate().executeUpdate(id, params);
	}

	public boolean delete(Object domainObject) {
		return getWriteTemplate().delete(domainObject);
	}

	public Serializable insert(Object domainObject, Object tableCondition) {
		return getWriteTemplate().insert(domainObject, tableCondition);
	}

	public boolean update(Object domainObject, Object tableCondition) {
		return getWriteTemplate().update(domainObject, tableCondition);
	}

	public boolean delete(Object domainObject, Object tableCondition) {
		return getWriteTemplate().delete(domainObject, tableCondition);
	}

	public Object getForRead(Class domainClass, Serializable pk) {
		return this.getForRead(domainClass, pk, true);
	}
	public Object getForRead(Class domainClass, Serializable pk,
			boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}
		try {
			return session.findObjectByPK(domainClass, pk);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}
	
	public Object findObject(String id, Map params) {
		return this.findObject(id, params, true);
	}
	public Object findObject(String id, Map params, boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}
		try {
			return session.findObject(id, params);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public Object findObject(BindedCompiledSQL bsql) {
		return this.findObject(bsql, true);
	}
	public Object findObject(BindedCompiledSQL bsql, boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.findObject(bsql);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}
	public Object findObject(SearchExpression se) {
		return this.findObject(se, true);
	}
	public Object findObject(SearchExpression se, boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.findObject(se);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}
	
	public Object findObjectByPK(Class domainClass, int pk) {
		return this.findObjectByPK(domainClass,pk, true);
	}
	public Object findObjectByPK(Class domainClass, int pk, boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.findObjectByPK(domainClass, pk);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public Object findObjectByPK(Class domainClass, Serializable pk) {
		return findObjectByPK(domainClass,pk, true);
	}
	public Object findObjectByPK(Class domainClass, Serializable pk,
			boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.findObjectByPK(domainClass, pk);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public Object findCell00(String id, Map params, String returnType) {
		return this.findCell00(id,  params, returnType, true);
	}
	/**
	 * Return the value of first column in the first row.
	 * 
	 * @param id
	 *            Sql id defined in guzz.xml or {@link DynamicSQLService}
	 * @param params
	 *            Named parameters
	 * @param returnType
	 *            The data type of the result to return. eg: int, long, float...
	 * @see ReadonlyTranSession#findCell00(String, Map, String)
	 */
	public Object findCell00(String id, Map params, String returnType,
			boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.findCell00(id, params, returnType);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public Object findCell00(BindedCompiledSQL bsql, String returnType) {
		return this.findCell00(bsql, returnType, true);
	}
	/**
	 * Return the value of first column in the first row.
	 * 
	 * @param bsql
	 *            BindedCompiledSQL to execute.
	 * @param returnType
	 *            The data type of the result to return. eg: int, long, float...
	 * @see ReadonlyTranSession#findCell00(BindedCompiledSQL, String)
	 */
	public Object findCell00(BindedCompiledSQL bsql, String returnType,
			boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.findCell00(bsql, returnType);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public long count(SearchExpression se) {
		return this.count(se, true);
	}
	/** 执行se中的count操作，返回long类型的数据。从slave数据库读取。 */
	public long count(SearchExpression se, boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.count(se);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public List list(String id, Map params) {
		return this.list(id, params, true);	
	}
	/**
	 * 
	 * query without pagination.
	 * 
	 * @param id
	 *            the id of the sql defined in the guzz.xml
	 * @param params
	 **/
	public List list(String id, Map params, boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.list(id, params);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public List list(String id, Map params, int startPos, int maxSize) {
		return this.list(id,  params,startPos, maxSize, true);
	}
	/**
	 * @param id
	 *            the id of the sql defined in the guzz.xml
	 * @param params
	 * @param startPos
	 *            the first is 1, the second is 2...
	 * @param maxSize
	 **/
	public List list(String id, Map params, int startPos, int maxSize,
			boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.list(id, params, startPos, maxSize);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public List list(SearchExpression se) {
		return this.list(se, true);
	}
	/** 执行se中的list操作。从slave数据库读取。 */
	public List list(SearchExpression se, boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.list(se);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	
	public List list(BindedCompiledSQL bsql, int startPos, int maxSize) {
		return this.list(bsql, startPos, maxSize, true);
	}
	/**
	 * List from slave database.
	 * 
	 * @param bsql
	 *            BindedCompiledSQL
	 * @param startPos
	 *            start from 1
	 * @param maxSize
	 */
	public List list(BindedCompiledSQL bsql, int startPos, int maxSize,
			boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}
		try {
			return session.list(bsql, startPos, maxSize);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	
	public PageFlip page(SearchExpression se) {
		return this.page(se, true);
	}
	/** 执行se中的count操作，返回long类型的数据。从slave数据库读取。 */
	public PageFlip page(SearchExpression se, boolean withDelay) {
		ReadonlyTranSession session = null;
		boolean isSpringTran = false;
		if (withDelay) {
			session = transactionManager.openDelayReadTran();
		} else {
			session = this.writeTemplate.exportReadAPI();
			if (session != null) {
				isSpringTran = true;
			} else {
				session = transactionManager.openNoDelayReadonlyTran();
			}
		}

		try {
			return session.page(se);
		} finally {
			if (isSpringTran == false) {
				session.close();
			}
		}
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;

		if (this.getWriteTemplate() == null) {
			this.setWriteTemplate(this.transactionManager
					.createBindedWriteTemplate());
		}
	}

	public void setGuzzContext(GuzzContext guzzContext) {
		this.guzzContext = guzzContext;
		setTransactionManager(guzzContext.getTransactionManager());
	}

	public GuzzContext getGuzzContext() {
		return this.guzzContext;
	}

}
