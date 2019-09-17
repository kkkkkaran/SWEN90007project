package source;
import java.util.ArrayList;
import java.util.List;

import source.dataSource.*;
import source.domain.*;

public class UnitOfWork {
	@SuppressWarnings("rawtypes")
	private static ThreadLocal current = new ThreadLocal();
	
	private List<DomainObject> newObjects = new ArrayList<DomainObject>();
	private List<DomainObject> dirtyObjects = new ArrayList<DomainObject>();
	private List<DomainObject> deletedObjects = new ArrayList<DomainObject>();
	
/**
 * create a new UnitOfWork instance in current thread
 */
public static void newCurrent() { 
	setCurrent(new UnitOfWork());
}

@SuppressWarnings("unchecked")
/**
 * create set a UnitOfWork instance to current thread
 * @param uow
 */
public static void setCurrent(UnitOfWork uow) { 
	current.set(uow);
}

/**
 * Get UnitOfWork instance in current thread
 * @return
 */
public static UnitOfWork getCurrent() {
	return (UnitOfWork) current.get();
}

/**
 * register the domain object which need to be registered
 * @param obj
 */
public void registerDirty(DomainObject obj) {
	 if(!checkIfInList(obj)) {
		 dirtyObjects.add(obj);
	 }	 
}

/**
 * register the domain object which need to be deleted
 * @param obj
 */
public void registerDeleted(DomainObject obj) {
	 if(!checkIfInList(obj)) {
		 deletedObjects.add(obj);
	 }	 
}

/**
 * register the domain object which need to be created
 * @param obj
 */
public void registerNew(DomainObject obj) {
	 if(!checkIfInList(obj)) {
		 newObjects.add(obj);
	 }
}

/**
 * check whether the domain object is in any of the object lists.
 * @param obj
 * @return
 */
public boolean checkIfInList(DomainObject obj) {
	 if(dirtyObjects.contains(obj)) {
		 return true;
	 }
	 if(deletedObjects.contains(obj)) {
		 return true;
	 }
	 if(newObjects.contains(obj)) {
		 return true;
	 }
	 return false;
}

/**
 * execute the update
 * @param sessionId
 * @return
 */
public boolean commit(String sessionId) {
	boolean addResult = true;
	boolean updateResult = true;
	boolean deleteResult = true;
	//deal with new objects
	for(DomainObject obj: newObjects) {
		if(!addResult) {
			return false;
		}
		try {
			//DataMapper dm = (DataMapper) Class.forName(
					"dataMapper."+obj.getClass().getSimpleName()+"Mapper").getDeclaredConstructor().newInstance();
			addResult = dm.insert(obj);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//deal with dirty objects
	for(DomainObject obj: dirtyObjects) {
		if(!updateResult) {
			return false;
		}
		
		try {
			DataMapper dm = (DataMapper) Class.forName(
					"dataMapper."+obj.getClass().getSimpleName()+"Mapper").getDeclaredConstructor().newInstance();
			ImplicitMapper im = new ImplicitMapper(
					dm, sessionId, obj.getClass().getSimpleName());
			updateResult = im.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//deal with deleted objects
	for(DomainObject obj: deletedObjects) {
		if(!deleteResult) {
			return false;
		}
		
		try {
			DataMapper dm = (DataMapper) Class.forName(
					"dataMapper."+obj.getClass().getSimpleName()+"Mapper").getDeclaredConstructor().newInstance();
			ImplicitMapper im = new ImplicitMapper(
					dm, sessionId, obj.getClass().getSimpleName());
			deleteResult = im.delete(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	return addResult&&updateResult&&deleteResult;
}
	
}
