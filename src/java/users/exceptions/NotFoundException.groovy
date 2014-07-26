package users.exceptions
import javax.servlet.http.HttpServletResponse

class NotFoundException extends RuntimeException {

    def status
    def error

    def NotFoundException(message) {

        super(message.toString())
        this.error = "not_found"
        this.status = HttpServletResponse.SC_NOT_FOUND
    }


}