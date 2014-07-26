package api.users

import static org.junit.Assert.*
import org.junit.*
import grails.plugin.gson.converters.GSON
import users.exceptions.NotFoundException
import users.exceptions.ConflictException
import users.exceptions.BadRequestException

class UserTests  {

    UserController userController
    User ejemplo

    @Before
    void setUp() {

        ejemplo = new User(
            
            name:'sample user',
            sex:'H',
            phone:'cel. 55-1222-2222',
            email:'ejemplo12345@detocho.com.mx',
            password:'dtm123456',
            cityId:"16",
            dateNan:new Date() - 545,
            origin:"maxibandas",
            picture:"http://www.detocho.com.mx"
            
            ).save()

        userController = new UserController()
    }

    @After
    void tearDown() {
        
    }

    @Test
    void test_must_get_UserList_with_userId_is_blank() {

        
        def response = userController.getUser()
        assert response.status == HttpServletResponse.SC_OK
        println response
    }
}
