package api.users

class User {

    static constraints = {
		
		nombre blank:false, maxSize:45
		apellidos maxSize:20
		sexo nullable: false,  inList: ['M', 'F']
		telefono maxSize:45
		email blank:false, email:true, unique:true
		password blank:false, minSize:4, maxSize:20
		estadoId nullable:false
		recibirPromociones nullable:false
		fechaNacimiento nullable:false
		ipRegistro nullable:true
	
    }
	
	String nombre
	String apellidos
	String sexo
	String telefono
	String email
	String password
	int estadoId
	Boolean recibirPromociones
	int status
	String ipRegistro
	Date fechaNacimiento
	Date fechaRegistro = new Date() 
	Date fechaModificacion = new Date()
	
	
	static mapping = {
		table "User"
		id column:"idUser"
	}
	
	
}
