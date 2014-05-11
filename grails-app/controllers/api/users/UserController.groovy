package api.users

import javassist.NotFoundException
import javax.servlet.http.HttpServletResponse
import grails.converters.*
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.plugin.gson.converters.GSON



class UserController {
	
	def userMagamentService 

	def notAllowed() {
		
				def method = request.method
				//render 'metodo ${method} no encotrado'
				//[response: [message: e.message, error: e.error, status: e.status, cause: e.cause], status: HttpServletResponse.SC_UNAUTHORIZED]
				//[response: [message: "Method $method not allowed.", error: "method_not_allowed", status: HttpServletResponse.SC_METHOD_NOT_ALLOWED, cause: []], status: HttpServletResponse.SC_METHOD_NOT_ALLOWED]
	}


	def test(){

		def palabra = 'david'
		String encriptado =palabra.encodeAsEncripcion() //'TgUpvEXZLoE=' 
		String desencriptado = encriptado.decodeEncripcion()
		render 'david encriptado = '+encriptado
		render '<br>desencriptado = '+desencriptado
	}

	
    def getUser() {
		
		
		def id = params.id
		def result
		try{
			if (!id)
			{
				result = userMagamentService.getUser()
				
			}else
			{
				
				result = userMagamentService.getUser(params.long('id'))
			}
			response.setStatus( HttpServletResponse.SC_OK)
			response.setContentType "application/json; charset=utf-8"
			render result as GSON
			
		}catch(NotFoundException e)
		{
			//render "Usuario not found"
			def mapExcepction = [
				message: e.message,
				error:"El errror",
				status: e.status
			]
			render mapExcepction as GSON
			//[response: [message: badRequestException.message, error: badRequestException.error, status: HttpServletResponse.SC_BAD_REQUEST, cause: badRequestException.internalCause], status: HttpServletResponse.SC_BAD_REQUEST]

		}
		catch(Exception e){
			//render "Cocurrio algun otro error"
			response.setStatus(HttpServletResponse.SC_NOT_FOUND)
			response.setContentType "application/json; charset=utf-8"
			def mapExcepction = [
				message: e.message,
				error:"El errror",
				status: 404
			]
			render mapExcepction as GSON
		}
		
		//render result   as GSON
		
		
	}

	def addUser(){

		
		//try{
			def createUser = userMagamentService.createUser(request.JSON)
			render createUser as GSON
		/*}
		catch (e) {

			render "usuraio no creado"
		}*/

		


	}

	def putUser(){
		
		def id = params.long('id')
		def modifiedUser = userMagamentService.modifyUser(id, request.JSON)
		render modifiedUser as GSON
	}

	def access(){

		def email = params.email
		def pass = params.password

		

		def tokenAccess = userMagamentService.accessUser(email, pass)
		render tokenAccess as GSON


		

	}
}
