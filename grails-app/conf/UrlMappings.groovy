class UrlMappings {

	static mappings = {
		
		
		"/$id?" {
			controller = "User"
			action = [GET: 'getUser', POST: 'addUser', PUT: 'putUser', DELETE: 'notAllowed']
		}
		
		

		"/search" {
			controller = "User"
			action = [GET: 'notAllowed', POST: 'notAllowed', PUT: 'notAllowed', DELETE: 'notAllowed']
		}

    /*
		"/access/$email?&$password?"{
			controller = "User"
			action = [GET:'access']
		}
	*/
		
		
	}
}
