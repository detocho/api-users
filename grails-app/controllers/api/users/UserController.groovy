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

				response.setStatus( HttpServletResponse.SC_METHOD_NOT_ALLOWED)
				response.setContentType "application/json; charset=utf-8"
				
				def mapResult = [
					message: "Method $method not allowed",
					status: HttpServletResponse.SC_METHOD_NOT_ALLOWED
				]
				render mapResult as GSON
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
			
			if (!id){
				result = userMagamentService.getUser()
			} else {
				result = userMagamentService.getUser(params.long('id'))
			}

			response.setStatus( HttpServletResponse.SC_OK)
			response.setContentType "application/json; charset=utf-8"
			render result as GSON
			
		}catch(NotFoundException e)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND)
			response.setContentType "application/json; charset=utf-8"

			def mapExcepction = [
				message: e.message,
				status: e.status
			]
			render mapExcepction as GSON

		}
		catch(Exception e){
			
			response.setStatus(500)
			response.setContentType "application/json; charset=utf-8"
			def mapExcepction = [
				message: e.message,
				status: 500
			]
			render mapExcepction as GSON
		}
		
		
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
