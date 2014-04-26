package api.users

class User {

    static constraints = {
		
		name blank:false, maxSize:45
		sex nullable: false,  inList: ['M', 'F']
		phone maxSize:50
		email blank:false, email:true, unique:true
		password blank:false, minSize:4, maxSize:20
		neighborhoodId nullable:false
		dateNan nullable:false
		origin nullable:false
		dateDeleted nullable:true
		picture nullable:true
    }
	
	String name 
	String email 
	String password
	Date dateNan
	Date dateUpdate = new Date()
	Date dateRegistered = new Date()
	Date dateDeleted
	String origin
	String sex
	String phone
	String neighborhoodId
	String picture
	String status = "pending" 
	
	static mapping = {
		table "User"
		id column:"idUser"
	}
	
	
}
