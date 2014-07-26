import api.users.User


class BootStrap {
	
	def init = { servletContext ->
		
		test{
			
		User registrado = new User(
            
            name:'User Registrado',
            sex: 'M',
            phone:'Casa (55) 55-2345-2534',
            email:'dpaz12345@detocho.com.mx',
            password:'dtm123456',
            cityId: "15",
            dateNan:new Date() - 365,
            origin:"maxibandas",
            picture:"http://www.google.com"
            
            )
        registrado.save()

			
		}
		
		production{
			
		}
		
		development{
			
			if (User.count() == 0) {
				def user01 = new User(
					name:'User Registrado1',
					sex: 'M',
					phone:'Casa (55) 55-2345-2534',
					email:'user10@detocho.com.mx',
					password:'dtm123456',
					cityId: "15",
					origin: "test",
					dateNan:new Date()-365,
					picture:'urlpicture'
					
					)
				
				user01.save()
				
				def user02 = new User(
					name:'User Registrado2',
					sex: 'M',
					phone:'Casa (55) 55-2345-2534',
					email:'user2@detocho.com.mx',
					password:'dtm123456',
					cityId: "15",
					origin: "test",
					dateNan:new Date()-365,
					picture:'http://www.detocho.com.mx'
					
					)
				
				user02.save()
				
				def user03 = new User(
					name:'User Registrado3',
					sex: 'M',
					phone:'Casa (55) 55-2345-2534',
					email:'user3@detocho.com.mx',
					password:'dtm123456',
					cityId: "15",
					origin: "test",
					dateNan:new Date()-365,
					picture:'http://www.google.com'
					)
				
				user03.save()
				
			}		
			
		}
		
	}
	
	
	def destroy = {
	}
	
}
