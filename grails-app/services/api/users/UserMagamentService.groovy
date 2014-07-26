package api.users
import java.text.MessageFormat
import org.apache.ivy.plugins.conflict.ConflictManager
import grails.converters.*
import grails.plugin.gson.converters.GSON
import users.exceptions.NotFoundException
import users.exceptions.ConflictException
import users.exceptions.BadRequestException


class UserMagamentService {
    static transactional = true

    def getUser(){

        def userResult = User.findAll()

        if (userResult == null){
            throw new NotFoundException("Users not found")
        }

        userResult


    }

    def getUser(def id){


        if (id == null) {
            throw new NotFoundException("You most provider userid")
        }

        def userResult = User.findById(id)

        if (userResult == null){
            throw new NotFoundException("The userId not found")
        }

        userResult

    }


    def createUser(def jsonUser){

        def responseMessage = ''

        User newUser = new User(

                phone:jsonUser?.phone,
                email: jsonUser?.email,
                password: jsonUser?.password,
                cityId: jsonUser?.cityId,
                origin: jsonUser?.origin
        )

        if(!newUser.validate())
        {
            newUser.errors.allErrors.each {
                responseMessage += MessageFormat.format(it.defaultMessage, it.arguments) + " "
            }
            throw new BadRequestException(responseMessage)

        } else if (User.findByEmail(newUser.email) != null){

            throw new ConflictException("EL email "+newUser.email+"ya esta registrado")

        } else{

            newUser.save()
        }

        newUser

    }

    def modifyUser(def id, def jsonUser){

        def responseMessage = ''

        if (!id) {
            throw new NotFoundException("You most provider userid")
        }
        def obteinedUser = User.findById(id)

        if (!obteinedUser){
            throw new NotFoundException("The User with userId="+id+" not found")
        }
        // debemos agregar un validaro de json
        obteinedUser.name = jsonUser?.name
        obteinedUser.sex = jsonUser?.sex
        obteinedUser.phone = jsonUser?.phone
        obteinedUser.cityId = jsonUser?.cityId
        obteinedUser.picture = jsonUser?.picture
        obteinedUser.dateNan = new Date().parse("MM-dd-yyyy",jsonUser?.date_of_birth)
        obteinedUser.dateUpdate = new Date()


        if(!obteinedUser.validate()){

            obteinedUser.errors.allErrors.each {
                responseMessage += MessageFormat.format(it.defaultMessage, it.arguments) + " "
            }
            throw new BadRequestException(responseMessage)

        }

        obteinedUser.save()

        obteinedUser
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
