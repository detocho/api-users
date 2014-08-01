environments {
    development {
        grails {
            mongo {
                host = "localhost"
                databaseName = "maxibandas"
            }
        }
    }
    test {
        grails {
            mongo {
                host = "localhost"
                databaseName = "maxibandas"
            }
        }
    }
    production {
        grails {
            mongo {

                // replicaSet = []
                username = ""
                password = ""
                databaseName = ""
            }
        }
    }
}