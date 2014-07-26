package api.users

class User {

    static constraints = {
		
		name            maxSize:45, nullable: true
		sex             nullable: true,  inList: ['M', 'F']
		phone           maxSize:50
		email           blank:false, email:true, unique:true
		password        blank:false, minSize:4, maxSize:20
        cityId          nullable:false
		dateNan         nullable:true
		origin          nullable:false
		dateDeleted     nullable:true
		picture         nullable:true
    }
	
	String name
	String email 
	String password
    String phone
    String cityId
    String origin
    Date dateUpdate = new Date()
	Date dateRegistered = new Date()
	Date dateDeleted
    Date dateNan
	String sex
	String picture
	String status = "active"
	
	static mapping = {
		table "User"
		id column:"idUser"
	}
	
	
}
