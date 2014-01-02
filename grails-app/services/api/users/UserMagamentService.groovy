package api.users
import java.text.MessageFormat
import javassist.NotFoundException
import org.apache.ivy.plugins.conflict.ConflictManager
import grails.converters.*


class UserMagamentService {
	static transactional = true
	
    def getUser(){
		
		
		def userResult = User.findAll()
		
		if (userResult == null){
			throw new NotFoundException("No hay usuarios registrados", "not registered")
		}
		
		return userResult  
	
		
	}
	
	def getUser(def id){
		
		
		if (id == null) {
			
			throw new NotFoundException("el id es null", "is not must be null")
			
			
		}
		
		def userResult = User.findById(id)
		
		if (userResult == null){
			throw new NotFoundException("El usuario, no existe", "not found")
		}
		
		return userResult
		
	}
	
	
	def createUser(def jsonUser){

		def responseMessage = ''
		
		User newUser = new User(
				nombre: jsonUser?.nombre,
				apellidos: jsonUser?.apellidos,
				sexo: jsonUser?.sexo,
				telefono:jsonUser?.telefono,
				email: jsonUser?.email,
				password: jsonUser?.password,
				estadoId: jsonUser?.estadoId,
				recibirPromociones: jsonUser?.recibirPromociones,
				status:jsonUser?.status,
				ipRegistro : jsonUser?.ipRegistro,
				fechaNacimiento: new Date().parse("MM-dd-yyyy",jsonUser?.fechaNacimiento)
			)	
		/*
		if (!newUser.validate()){
			//newUser.errors.allErrors.each {responseMessage += MessageFormat.format(it.defaultMessage, it.arguments) + " "}
            //throw new BadRequestException("Invalid user data", "bad_request", responseMessage)
            throw new NotFoundException("Email already registered", "not found")

		}else if (User.findByEmail(newUser.email) != null){
				throw new NotFoundException("Email already registered", "not found")
		}


		if(newUser.hasErrors()){
			//tratamos los errores
		}
*/
		if(!newUser.validate())
		{
			newUser.errors.allErrors.each {responseMessage += MessageFormat.format(it.defaultMessage, it.arguments) + " "}
			return responseMessage
		}
		else{
		newUser.save()
		}

		return newUser
		
	}

	def modifyUser(def id, def jsonUser){

		def responseMessage = ''
		def obteinedUser = User.findById(id)

		if (obteinedUser != null){

			obteinedUser.nombre = jsonUser?.nombre
			obteinedUser.apellidos = jsonUser?.apellidos

			if(!obteinedUser.validate()){

					newUser.errors.allErrors.each {responseMessage += MessageFormat.format(it.defaultMessage, it.arguments) + " "}
					return responseMessage
				}else{
					obteinedUser.save()
				}
		} 

		return obteinedUser
	}
	
}
