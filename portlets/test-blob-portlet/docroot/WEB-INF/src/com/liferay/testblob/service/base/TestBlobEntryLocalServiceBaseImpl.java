/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.testblob.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import com.liferay.testblob.model.TestBlobEntry;
import com.liferay.testblob.model.TestBlobEntryBlobFieldBlobModel;
import com.liferay.testblob.service.TestBlobEntryLocalService;
import com.liferay.testblob.service.persistence.TestBlobEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the test blob entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.testblob.service.impl.TestBlobEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.testblob.service.impl.TestBlobEntryLocalServiceImpl
 * @see com.liferay.testblob.service.TestBlobEntryLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class TestBlobEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements TestBlobEntryLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.testblob.service.TestBlobEntryLocalServiceUtil} to access the test blob entry local service.
	 */

	/**
	 * Adds the test blob entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testBlobEntry the test blob entry
	 * @return the test blob entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TestBlobEntry addTestBlobEntry(TestBlobEntry testBlobEntry) {
		testBlobEntry.setNew(true);

		return testBlobEntryPersistence.update(testBlobEntry);
	}

	/**
	 * Creates a new test blob entry with the primary key. Does not add the test blob entry to the database.
	 *
	 * @param testBlobEntryId the primary key for the new test blob entry
	 * @return the new test blob entry
	 */
	@Override
	@Transactional(enabled = false)
	public TestBlobEntry createTestBlobEntry(long testBlobEntryId) {
		return testBlobEntryPersistence.create(testBlobEntryId);
	}

	/**
	 * Deletes the test blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testBlobEntryId the primary key of the test blob entry
	 * @return the test blob entry that was removed
	 * @throws PortalException if a test blob entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TestBlobEntry deleteTestBlobEntry(long testBlobEntryId)
		throws PortalException {
		return testBlobEntryPersistence.remove(testBlobEntryId);
	}

	/**
	 * Deletes the test blob entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testBlobEntry the test blob entry
	 * @return the test blob entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TestBlobEntry deleteTestBlobEntry(TestBlobEntry testBlobEntry) {
		return testBlobEntryPersistence.remove(testBlobEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(TestBlobEntry.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return testBlobEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return testBlobEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return testBlobEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return testBlobEntryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return testBlobEntryPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public TestBlobEntry fetchTestBlobEntry(long testBlobEntryId) {
		return testBlobEntryPersistence.fetchByPrimaryKey(testBlobEntryId);
	}

	/**
	 * Returns the test blob entry with the primary key.
	 *
	 * @param testBlobEntryId the primary key of the test blob entry
	 * @return the test blob entry
	 * @throws PortalException if a test blob entry with the primary key could not be found
	 */
	@Override
	public TestBlobEntry getTestBlobEntry(long testBlobEntryId)
		throws PortalException {
		return testBlobEntryPersistence.findByPrimaryKey(testBlobEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(testBlobEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(TestBlobEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("testBlobEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(testBlobEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(TestBlobEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"testBlobEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(testBlobEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(TestBlobEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("testBlobEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return testBlobEntryLocalService.deleteTestBlobEntry((TestBlobEntry)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return testBlobEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the test blob entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of test blob entries
	 * @param end the upper bound of the range of test blob entries (not inclusive)
	 * @return the range of test blob entries
	 */
	@Override
	public List<TestBlobEntry> getTestBlobEntries(int start, int end) {
		return testBlobEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of test blob entries.
	 *
	 * @return the number of test blob entries
	 */
	@Override
	public int getTestBlobEntriesCount() {
		return testBlobEntryPersistence.countAll();
	}

	/**
	 * Updates the test blob entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testBlobEntry the test blob entry
	 * @return the test blob entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TestBlobEntry updateTestBlobEntry(TestBlobEntry testBlobEntry) {
		return testBlobEntryPersistence.update(testBlobEntry);
	}

	@Override
	public TestBlobEntryBlobFieldBlobModel getBlobFieldBlobModel(
		Serializable primaryKey) {
		Session session = null;

		try {
			session = testBlobEntryPersistence.openSession();

			return (TestBlobEntryBlobFieldBlobModel)session.get(TestBlobEntryBlobFieldBlobModel.class,
				primaryKey);
		}
		catch (Exception e) {
			throw testBlobEntryPersistence.processException(e);
		}
		finally {
			testBlobEntryPersistence.closeSession(session);
		}
	}

	/**
	 * Returns the test blob entry local service.
	 *
	 * @return the test blob entry local service
	 */
	public TestBlobEntryLocalService getTestBlobEntryLocalService() {
		return testBlobEntryLocalService;
	}

	/**
	 * Sets the test blob entry local service.
	 *
	 * @param testBlobEntryLocalService the test blob entry local service
	 */
	public void setTestBlobEntryLocalService(
		TestBlobEntryLocalService testBlobEntryLocalService) {
		this.testBlobEntryLocalService = testBlobEntryLocalService;
	}

	/**
	 * Returns the test blob entry persistence.
	 *
	 * @return the test blob entry persistence
	 */
	public TestBlobEntryPersistence getTestBlobEntryPersistence() {
		return testBlobEntryPersistence;
	}

	/**
	 * Sets the test blob entry persistence.
	 *
	 * @param testBlobEntryPersistence the test blob entry persistence
	 */
	public void setTestBlobEntryPersistence(
		TestBlobEntryPersistence testBlobEntryPersistence) {
		this.testBlobEntryPersistence = testBlobEntryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		PersistedModelLocalServiceRegistryUtil.register("com.liferay.testblob.model.TestBlobEntry",
			testBlobEntryLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.testblob.model.TestBlobEntry");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return TestBlobEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return TestBlobEntry.class;
	}

	protected String getModelClassName() {
		return TestBlobEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = testBlobEntryPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = TestBlobEntryLocalService.class)
	protected TestBlobEntryLocalService testBlobEntryLocalService;
	@BeanReference(type = TestBlobEntryPersistence.class)
	protected TestBlobEntryPersistence testBlobEntryPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}