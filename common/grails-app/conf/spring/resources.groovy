// Place your Spring DSL code here
beans = {
    dataSource(com.alibaba.druid.pool.DruidDataSource) {
        url="${grailsApplication.config.dataSource.url}"
        username="${grailsApplication.config.dataSource.username}"
        password="${grailsApplication.config.dataSource.password}"

        filters="stat"

        maxActive=20
        initialSize=1
        maxWait=60000
        minIdle=1

        timeBetweenEvictionRunsMillis=60000
        minEvictableIdleTimeMillis=300000

        testWhileIdle=true
        testOnBorrow=false
        testOnReturn=false

        poolPreparedStatements=true
        maxOpenPreparedStatements=20
    }
}
