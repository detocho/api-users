package api.users


import javax.servlet.http.HttpServletResponse
import grails.converters.*
//import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.plugin.gson.converters.GSON
import users.exceptions.NotFoundException
import users.exceptions.ConflictException
import users.exceptions.BadRequestException




class UserController {

    def userMagamentService

    def notAllowed() {

        def method = request.method

        response.setStatus( HttpServletResponse.SC_METHOD_NOT_ALLOWED)
        response.setContentType "application/json; charset=utf-8"

        def mapResult = [
                message: "Method $method not allowed",
                status: HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                error:"not_allowed"
        ]
        render mapResult as GSON
    }

    /*
    def test(){

        def palabra = 'david'
        String encriptado =palabra.encodeAsEncripcion() //'TgUpvEXZLoE='
        String desencriptado = encriptado.decodeEncripcion()
        render 'david encriptado = '+encriptado
        render '<br>desencriptado = '+desencriptado
    }
    */

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

        }catch(NotFoundException e){

            response.setStatus(e.status)
            response.setContentType "application/json; charset=utf-8"

            def mapExcepction = [
                    message: e.getMessage(),
                    status: e.status,
                    error: e.error
            ]
            render mapExcepction as GSON

        }catch(Exception e){

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.setContentType "application/json; charset=utf-8"
            def mapExcepction = [
                    message: e.getMessage(),
                    status: HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    error: "internal_server_error"
            ]
            render mapExcepction as GSON
        }


    }

    def addUser(){

        def result
        try{

            result = userMagamentService.createUser(request.JSON)
            response.setStatus( HttpServletResponse.SC_CREATED)
            response.setContentType "application/json; charset=utf-8"
            render result as GSON

        }catch (BadRequestException e) {

            response.setStatus(e.status)
            response.setContentType "application/json; charset=utf-8"

            def mapExcepction = [
                    message: e.getMessage(),
                    status: e.status,
                    error: e.error
            ]
            render mapExcepction as GSON

        }catch (ConflictException e){

            response.setStatus(e.status)
            response.setContentType "application/json; charset=utf-8"

            def mapExcepction = [
                    message: e.getMessage(),
                    status: e.status,
                    error: e.error
            ]
            render mapExcepction as GSON

        }catch (Exception e){

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.setContentType "application/json; charset=utf-8"
            def mapExcepction = [
                    message: e.message,
                    status: HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    error: "internal_server_error"
            ]
            render mapExcepction as GSON

        }




    }

    def putUser(){

        def id = params.long('id')
        try {
            def modifiedUser = userMagamentService.modifyUser(id, request.JSON)
            render modifiedUser as GSON
        }catch(NotFoundException e){

            response.setStatus(e.status)
            response.setContentType "application/json; charset=utf-8"

            def mapExcepction = [
                    message: e.getMessage(),
                    status: e.status,
                    error: e.error
            ]
            render mapExcepction as GSON

        }catch (BadRequestException e) {

            response.setStatus(e.status)
            response.setContentType "application/json; charset=utf-8"

            def mapExcepction = [
                    message: e.getMessage(),
                    status: e.status,
                    error: e.error
            ]
            render mapExcepction as GSON

        }catch (Exception e){

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.setContentType "application/json; charset=utf-8"
            def mapExcepction = [
                    message: e.message,
                    status: HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    error: "internal_server_error"
            ]
            render mapExcepction as GSON

        }
    }

    /*
    def access(){

        def email = params.email
        def pass = params.password



        def tokenAccess = userMagamentService.accessUser(email, pass)
        render tokenAccess as GSON




    }
    */
}
