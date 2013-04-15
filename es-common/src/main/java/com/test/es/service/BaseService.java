package com.test.es.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.test.es.entity.AbstractEntity;
import com.test.es.plugin.entity.LogicDeleteable;
import com.test.es.plugin.entity.Searchable;
import com.test.es.repository.BaseRepository;
import com.test.es.util.ReflectUtils;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:下午4:31:31
 */
public abstract class BaseService<M extends AbstractEntity,ID extends Serializable> implements InitializingBean {

	protected final Class<M> entityClass;
	
	protected BaseRepository<M,ID> baseRepository;
	
	public  BaseService(){
		this.entityClass = ReflectUtils.findParameterizedType(getClass(),0);
	}
	
	public void setBaseRepository(BaseRepository<M,ID> baseRepository){
		this.baseRepository = baseRepository;
	}
	
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}
	
	public  M save(M m){
		return this.baseRepository.save(m);
	}
	
	public M saveAndFlush(M m){
		m = save(m);
		this.baseRepository.flush();
		return  m;
	}
	
	public M update(M m){
		return this.update(m);
	}
	
	
	public void delete(ID id){
		M m = findOne(id);
		delete(m);
	}
	
	public M findOne(ID id){
		if(null == id){
			return null;
		}
		return this.baseRepository.findOne(id);
	}
	
	public  void delete(M m){
		if(null == m){
			return ;
		}
		 if(m instanceof LogicDeleteable) {
	            ((LogicDeleteable) m).markDeleted();
	            baseRepository.save(m);
	        } else {
	            baseRepository.delete(m);
	        }
	}
	
	public boolean exists(ID id){
		return this.baseRepository.exists(id);
	}
	
	
	/**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public long count() {
        return baseRepository.count();
    }
	
    /**
     * 查询所有实体
     *
     * @return
     */
    public List<M> findAll() {
        return baseRepository.findAll();
    }
    
    public List<M> findAll(Sort sort){
    	return this.baseRepository.findAll(sort);
    }
    
    
    public Page<M> findAll(Pageable pageable){
    	return this.baseRepository.findAll(pageable);
    }
    
    /**
     * 按条件不分页查询实体
     *
     * @param searchable 条件
     * @return
     */
    public List<M> findAllByNoPage(Searchable searchable) {
        return baseRepository.findAll(searchable.getSpecifications(entityClass));
    }
    
    
    /**
     * 按条件排序查询实体
     *
     * @param searchable 条件
     * @return
     */
    public List<M> findAllBySort(Searchable searchable) {
        return baseRepository.findAll(searchable.getSpecifications(entityClass), searchable.getSort());
    }
    
    /**
     * 按条件分页并排序查询实体
     *
     * @param searchable 条件
     * @return
     */
    public Page<M> findAll(Searchable searchable) {
        return baseRepository.findAll(searchable.getSpecifications(entityClass), searchable.getPage());
    }
    
    /**
     * 按条件分页并排序统计实体数量
     *
     * @param searchable 条件
     * @return
     */
    public Long count(Searchable searchable) {
        return baseRepository.count(searchable.getSpecifications(entityClass));
    }

    
    public void delete(ID[] ids){
    	if(ArrayUtils.isEmpty(ids)){
    		return ;
    	}
    	
    	 List<M> models = new ArrayList<M>();
    	 for(ID id : ids) {
	    	 M model = null;
	         try {
	             model = (M)entityClass.newInstance();
	         }catch (Exception e) {
	             throw new RuntimeException("batch delete " + entityClass.getName() + " error", e);
	         }
	         
	         try {
	                BeanUtils.setProperty(model, "id", id);
	            } catch (Exception e) {
	                throw new RuntimeException("batch delete " + entityClass.getName() + " error, can not set id", e);
	            }

	            models.add(model);
         
    	 }
    	 
    	 
    }
	
	
	

}

