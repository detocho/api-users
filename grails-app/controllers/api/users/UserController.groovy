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
				render 'metodo ${method} no encotrado'
				//[response: [message: "Method $method not allowed.", error: "method_not_allowed", status: HttpServletResponse.SC_METHOD_NOT_ALLOWED, cause: []], status: HttpServletResponse.SC_METHOD_NOT_ALLOWED]
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
			
		}catch(NotFoundException e)
		{
			render "Usuario not found"
		}
		
		render result  as GSON
		
		
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
}
