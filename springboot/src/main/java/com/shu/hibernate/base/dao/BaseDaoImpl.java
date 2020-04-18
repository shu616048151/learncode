package com.shu.hibernate.base.dao;

import com.shu.hibernate.base.vo.Parameter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author sshuxibing
 * @param <T>
 */
public class BaseDaoImpl<T> implements IbaseDao<T> {
    private HibernateTemplate hibernateTemplate;
    private Class<T> entityClass;

    private static final int BATCH_MAX_ROW = 10;
    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T entity) throws Exception {
        hibernateTemplate.save(entity);
        return entity;
    }

    public int batchSave(final List<T> array) throws Exception {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException {
                for(int i=0;i<array.size();i++){
                    session.save(array.get(i));
                    if (i%BATCH_MAX_ROW==0){
                        session.flush();
                        session.clear();
                    }
                }
                session.flush();
                session.clear();
                return array.size();
            }
        });
    }

    public int batchUpdate(final List<T> array) throws Exception {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException {
                for(int i=0;i<array.size();i++){
                    session.update(array.get(i));
                    if (i%BATCH_MAX_ROW==0){
                        session.flush();
                        session.clear();
                    }
                }
                session.flush();
                session.clear();
                return array.size();
            }
        });
    }

    public int batchSave(T[] array) throws Exception {
        return 0;
    }

    public T saveOrUpdate(T entity) throws Exception {
        hibernateTemplate.saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) throws Exception {
        hibernateTemplate.update(entity);
        return entity;
    }

    public int update(final String hql) throws Exception {
        return update(hql,null);
    }

    public int update(final String hql, final Parameter parameter) throws Exception {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setParameter(query,parameter);
                return query.executeUpdate();
            }
        });
    }

    public void delete(T entity) throws Exception {
        hibernateTemplate.delete(entity);
    }

    public int deleteWithHql(String hql) throws Exception {
        return deleteWithHql(hql,null);
    }

    public int deleteWithHql(final String hql, final Parameter parameter) throws Exception {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<Integer>() {
            public Integer doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setParameter(query,parameter);
                return query.executeUpdate();
            }
        });
    }

    public void deleteAll(Collection entities) throws Exception {
        hibernateTemplate.deleteAll(entities);
    }

    public T getOne(int id) {
        return hibernateTemplate.get(entityClass,id);
    }

    public T loadOne(int id) {
        return hibernateTemplate.load(entityClass,id);
    }

    public T findOne(String hql) {
        return findOne(hql,null);
    }

    public T findOne(final String hql,final Parameter parameter) {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<T>() {
            public T doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setParameter(query,parameter);
                return (T) query.setMaxResults(1).uniqueResult();
            }
        });
    }

    public Object findOneWithAny(String hql) {
        return findOneWithAny(hql,null);
    }

    public Object findOneWithAny(final String hql, final Parameter parameter) {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setParameter(query,parameter);
                return query.setMaxResults(1).uniqueResult();
            }
        });
    }

    public long findCount(String hql) {
        return 0;
    }

    public long findCount(final String hql, final Parameter parameter) {
        Long l=hibernateTemplate.executeWithNativeSession(new HibernateCallback<Long>() {
            public Long doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setParameter(query,parameter);
                return (Long) query.setMaxResults(1).uniqueResult();
            }
        });
        return l==null?0:l;
    }

    public List<T> findList(final String hql, final Parameter parameter) {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<List<T>>() {
            public List<T> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                setParameter(query,parameter);
                return query.list();
            }
        });
    }

    public List<T> findList(String hql) {
        return findList(hql,null);
    }

    public List findListWithAny(String hql) {
        return findListWithAny(hql,null);
    }

    public List findListWithAny(final String hql, final Parameter parameter) {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<List>() {
            public List doInHibernate(Session session) throws HibernateException {
                    Query query = session.createQuery(hql);
                    setParameter(query,parameter);
                    return query.list();
            }
        });
    }

    public long findCountBySql(final String sql) {
      Long l= hibernateTemplate.executeWithNativeSession(new HibernateCallback<Long>() {
          public Long doInHibernate(Session session) throws HibernateException {
              Query query = session.createSQLQuery(sql);
              return (Long) query.setMaxResults(1).uniqueResult();
          }
      });
      return l==null?0:l;
    }

    public List<T> findMore(final int begin, final int size, final String queryHql, final Parameter queryParameter) {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<List<T>>() {
            public List<T> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(queryHql);
                query.setFirstResult(begin);
                query.setMaxResults(size);
                setParameter(query,queryParameter);
                return query.list();
            }
        });
    }

    public List<T> findMore(int begin, int size, String queryHql) {
        return findMore(begin,size,queryHql,null);
    }

    public List findMoreWithAny(final int begin, final int size, final String queryHql, final Parameter queryParameter) {
        return hibernateTemplate.executeWithNativeSession(new HibernateCallback<List>() {
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(queryHql);
                query.setFirstResult(begin);
                query.setMaxResults(size);
                setParameter(query,queryParameter);
                return query.list();
            }
        });
    }

    public List findMoreWithAny(int begin, int size, String queryHql) {
        return findMore(begin,size,queryHql);
    }


    /**
     * 给给query封装参数
     * @param query
     * @param parameter
     */
    private void setParameter(Query query, Parameter parameter) {
        if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
                if (value instanceof Collection<?>) {
                    query.setParameterList(string, (Collection<?>) value);
                } else if (value instanceof Object[]) {
                    query.setParameterList(string, (Object[]) value);
                } else {
                    query.setParameter(string, value);
                }
            }
        }
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
