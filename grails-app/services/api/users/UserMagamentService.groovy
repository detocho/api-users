package api.users
import java.text.MessageFormat
import javassist.NotFoundException
import org.apache.ivy.plugins.conflict.ConflictManager
import grails.converters.*
import grails.plugin.gson.converters.GSON


class UserMagamentService {
	static transactional = true
	
    def getUser(){
		
		def userResult = User.findAll()
		
		if (userResult == null){
			throw new NotFoundException("No hay usuarios registrados", "not registered")
		}
		
		userResult  
	
		
	}
	
	def getUser(def id){
		
		
		if (id == null) {
			
			throw new NotFoundException("el id es null", "is not must be null")
			
			
		}
		
		def userResult = User.findById(id)
		
		if (userResult == null){
			throw new NotFoundException("El usuario, no existe", "not found")
		}
		
		userResult
		
	}
	
	
	def createUser(def jsonUser){

		def responseMessage = ''
		
		User newUser = new User(
				name: jsonUser?.nombre,
				sex: jsonUser?.sexo,
				phone:jsonUser?.telefono,
				email: jsonUser?.email,
				password: jsonUser?.password,
				neighborhoodId: jsonUser?.estadoId,
				picture: jsonUser?.picture,
				origin: jsonUser?.origen,
				dateNan: new Date().parse("MM-dd-yyyy",jsonUser?.fechaNacimiento)
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

	def accessUser(def email, def password){

		def obteinedUser = User.findByEmailAndPassword(email, password)
		def token
		def codigo
		def access
		
		if (obteinedUser != null)
		{
			token = java.net.URLEncoder.encode(obteinedUser.id+'')
			token = token.encodeAsEncripcion()
			codigo = 'Valid Acces Token'
		}
		else{
			token = ''
			codigo = 'Invalid Acces Token'
		}

		access = [
			'token': token,
			'codigo':codigo
		]

		return access

	}
	
}
