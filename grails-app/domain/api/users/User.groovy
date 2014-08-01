package api.users

class User {

    static constraints = {
		
		name            maxSize:150, nullable: true
		sex             nullable: true,  inList: ['Hombre','Mujer']
		phone           maxSize:128
		email           blank:false, email:true, unique:true
		password        blank:false, minSize:4, maxSize:20
        locationId      nullable:false
        dateOfBirth     nullable:true
		origin          nullable:false
		dateDeleted     nullable:true
		picture         nullable:true
        dateRegistered  nullable:false
        userType       nullable:true
    }
	
	String name
	String email 
	String password
    String phone
    String locationId
    String origin
    Date dateUpdate = new Date()
	Date dateRegistered = new Date()
	Date dateDeleted
    Date dateOfBirth
	String sex
	String picture
	String status = "active"
    String userType = "normal"

}
