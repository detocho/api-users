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
			
			name:'User Registrado',
			sex: 'M',
			phone:'Casa (55) 55-2345-2534',
			email:'dpaz@detocho.com.mx',
			password:'dtm123456',
			neighborhoodId: "15",
			dateNan:new Date() - 365,
			origin:"maxibandas",
			picture:"http://www.google.com"
			
			)
		
		mockForConstraintsTests(User, [registeredUser])
		
		sampleUser = new User(
			
			name:'sample user',
			sex:'H',
			phone:'cel. 55-1222-2222',
			email:'ejemplo@detocho.com.mx',
			password:'dtm123456',
			neighborhoodId:"16",
			dateNan:new Date() - 545,
			origin:"maxibandas",
			picture:"http://www.detocho.com.mx"
			
			)
	}
	
	void test_UserIsNotValid_WhitBlankName(){
		
		sampleUser.name = ''
		assertFalse(sampleUser.validate())
		assertEquals('Name is blank','blank',sampleUser.errors['name'])
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
	
	void test_UserIsNotValidate_whitFechaNacimeintoNull(){
		
		sampleUser.dateNan = null
		assertFalse(sampleUser.validate())
		assertEquals('La fecha de nacimiento no puede ser nula','nullable', sampleUser.errors['dateNan'])
	}
	
	void test_creaUsuario(){
		
		def ejemplo =  new User(
		name:'User Registrado1',
		sex: 'M',
		phone:'Casa (55) 55-2345-2534',
		email:'user1@detocho.com.mx',
		password:'dtm123456',
		neighborhoodId: "15",
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
