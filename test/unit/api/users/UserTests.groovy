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
			
			nombre:'User Registrado',
			apellidos:'Apellidos',
			sexo: 'M',
			telefono:'Casa (55) 55-2345-2534',
			email:'dpaz@detocho.com.mx',
			password:'dtm123456',
			estadoId: 15,
			recibirPromociones: true,
			status:1,
			fechaNacimiento:new Date()-365
			
			)
		
		mockForConstraintsTests(User, [registeredUser])
		
		sampleUser = new User(
			
			nombre:'sample user',
			apellidos:'ejemplo',
			sexo:'H',
			telefono:'cel. 55-1222-2222',
			email:'ejemplo@detocho.com.mx',
			password:'dtm123456',
			estadoId:16,
			recibirPromociones:false,
			status:0,
			fechaNacimiento:new Date()-545
			
			)
	}
	
	void test_UserIsNotValid_WhitBlankName(){
		
		sampleUser.nombre = ''
		assertFalse(sampleUser.validate())
		assertEquals('Name is blank','blank',sampleUser.errors['nombre'])
	}
	
	void test_UserIsNotValidate_WhitEmailUnique(){
		
		sampleUser.email = 'dpaz@detocho.com.mx'
		assertFalse(sampleUser.validate())
		assertEquals('Mail repetido','unique',sampleUser.errors['email'])
	}
	
	void test_UserIsNotValidate_whitFormatEmailInvalid(){
		sampleUser.email = 'ddd@asdsa'
		assertFalse(sampleUser.validate())
		assertEquals('Formato de email invalido','email',sampleUser.errors['email'])
	}
	
	void test_UserIsNotValidate_whitPasswordLongMinInvalid(){
		
		sampleUser.password = '123'
		assertFalse(sampleUser.validate())
		assertEquals('Password no es valido','minSize', sampleUser.errors['password'])
	}
	
	void test_UserIsNotValidate_whitPasswordLongMaxInvalid(){
		sampleUser.password = '1234567890asdfghjkklkl'
		assertFalse(sampleUser.validate())
		assertEquals('Password demasiado largo','maxSize',sampleUser.errors['password'])
	}
	
	void test_UserIsNotValidate_whitFechaNacimeintoNull(){
		
		sampleUser.fechaNacimiento = null
		assertFalse(sampleUser.validate())
		assertEquals('La fecha de nacimiento no puede ser nula','nullable', sampleUser.errors['fechaNacimiento'])
	}
	
	void test_creaUsuario(){
		
		def ejemplo =  new User(
		nombre:'User Registrado1',
		apellidos:'Apellidos',
		sexo: 'M',
		telefono:'Casa (55) 55-2345-2534',
		email:'user1@detocho.com.mx',
		password:'dtm123456',
		estadoId: 15,
		recibirPromociones: true,
		status:1,
		fechaNacimiento:new Date(),
		ipRegistro: '192.168.1.1'
		)
		
		if(!ejemplo.save()){
			print 'ocurrio un error, pero cual?'
			print ejemplo.errors
		}
	}

	
}
