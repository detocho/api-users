dataSource {
    pooled = true
    //driverClassName = "com.mysql.jdbc.Driver"
    driverClassName = "org.h2.Driver"
    //username = "sa"
    //password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
    
    /*    
		dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    */
		
		dataSource {
			dbCreate = "update"  //create-drop // one of 'create', 'create-drop','update'
			username = "root"
			password = ""
			url = "jdbc:mysql://localhost/detocho"
		}
    
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            
            //dbCreate = "update"
            //url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
              dbCreate = "create-drop"  //create-drop // one of 'create', 'create-drop','update'
              username = "root"
              password = "GIOdavid28574348"
              url = "jdbc:mysql://localhost/detocho"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
