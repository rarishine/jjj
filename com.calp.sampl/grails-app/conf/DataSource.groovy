dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username="root"
 password="52xmercury"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost:3306/MeTesting"
        }
    }
    }
    test {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost:3306/MeTesting"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url =  "jdbc:mysql://localhost:3306/MeTesting"
        }
    }
