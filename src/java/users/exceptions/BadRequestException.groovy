package users.exceptions
import javax.servlet.http.HttpServletResponse

class BadRequestException extends RuntimeException {
    def status 
    def error

    def BadRequestException(message) {
      

        super(message.toString())
        this.error = "bad_request"
        this.status = HttpServletResponse.SC_BAD_REQUEST
    }

}