import api.users.User


class BootStrap {
	
	def init = { servletContext ->
		
					test{
	
						def user01 = new User(
							name:'User Registrado1',
							sex: 'M',
							phone:'Casa (55) 55-2345-2534',
							email:'user1@detocho.com.mx',
							password:'dtm123456',
							neighborhoodId: "15",
							origin: "test",
							dateNan:new Date()-365,
							picture:"http:www.maxibandas.com.mx"
	
						)
	
						user01.save()
						
	
					}
		
					production{

				/*		
						def user01 = new User(
							nombre:'User Registrado1',
							apellidos:'Apellidos',
							sexo: 'M',
							telefono:'Casa (55) 55-2345-2534',
							email:'user10@detocho.com.mx',
							password:'dtm123456',
							estadoId: 15,
							recibirPromociones: true,
							status:1,
							fechaNacimiento:new Date()-365,
							ipRegistro:'192.168.1.1'
	
						)
	
						user01.save()
	
				def user02 = new User(
						nombre:'User Registrado2',
						apellidos:'Apellidos',
						sexo: 'M',
						telefono:'Casa (55) 55-2345-2534',
						email:'user2@detocho.com.mx',
						password:'dtm123456',
						estadoId: 15,
						recibirPromociones: true,
						status:1,
						fechaNacimiento:new Date()-365,
						ipRegistro:'192.168.1.1'
	
				)
	
				user02.save()
	
				def user03 = new User(
						nombre:'User Registrado3',
						apellidos:'Apellidos',
						sexo: 'M',
						telefono:'Casa (55) 55-2345-2534',
						email:'user3@detocho.com.mx',
						password:'dtm123456',
						estadoId: 15,
						recibirPromociones: true,
						status:1,
						fechaNacimiento:new Date()-365,
						ipRegistro:'192.168.1.1'
				)
	
				user03.save()
		
		
				*/	
					}
		
					development{
		
		
						def user01 = new User(
							name:'User Registrado1',
							sex: 'M',
							phone:'Casa (55) 55-2345-2534',
							email:'user10@detocho.com.mx',
							password:'dtm123456',
							neighborhoodId: "15",
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
						neighborhoodId: "15",
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
						neighborhoodId:"15",
						origin: "test",
						dateNan:new Date()-365,
						picture:'http://www.google.com'
				)
	
				user03.save()
		
		
		
					}
					
	}
		
		
			def destroy = {
			}
    
}
