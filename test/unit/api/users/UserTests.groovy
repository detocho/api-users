package api.users



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

    def registeredUser
    def sampleUser

    @Before
    void setUp(){

        registeredUser = new User(

                name:'David Paz',
                sex: 'M',
                phone:'Casa (55) 55-2345-2534',
                email:'dpaz@detocho.com.mx',
                password:'dtm123456',
                cityId: "15",
                origin:"maxibandas"

        )

        mockForConstraintsTests(User, [registeredUser])

        sampleUser = new User(

                name:'Gio David Paz',
                phone:'cel. 55-1222-2222',
                email:'gio.paz@detocho.com.mx',
                password:'dtm123456',
                cityId:"16",
                origin:"maxibandas"

        )
    }



    void test_UserIsNotValidate_WhitEmailUnique(){

        sampleUser.email = 'dpaz@detocho.com.mx'
        assertFalse(sampleUser.validate())
        assertEquals('Mail exists','unique',sampleUser.errors['email'])
    }

    void test_UserIsNotValidate_whitFormatEmailInvalid(){
        sampleUser.email = 'ddd@asdsa'
        assertFalse(sampleUser.validate())
        assertEquals('Email format not valid','email',sampleUser.errors['email'])
    }

    void test_UserIsNotValidate_whitPasswordLongMinInvalid(){

        sampleUser.password = '123'
        assertFalse(sampleUser.validate())
        assertEquals('Password is not valid','minSize', sampleUser.errors['password'])
    }

    void test_UserIsNotValidate_whitPasswordLongMaxInvalid(){
        sampleUser.password = '1234567890asdfghjkklkl'
        assertFalse(sampleUser.validate())
        assertEquals('Password is not valid','maxSize',sampleUser.errors['password'])
    }

    void test_UserIsValid_whitStatusActiveByDefaultValue(){
        assertEquals('The user is active','active',sampleUser.status)
    }


    void test_creaUsuario(){

        def ejemplo =  new User(
                name:'User Registrado1',
                sex: 'M',
                phone:'Casa (55) 55-2345-2534',
                email:'user1@detocho.com.mx',
                password:'dtm123456',
                cityId: "15",
                status:"active",
                dateNan:new Date() - 645,
                origin: "maxibandas"
        )

        if(!ejemplo.save()){
            print 'ocurrio un error, pero cual?'
            print ejemplo.errors
        }
    }


}
