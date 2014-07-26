package users.exceptions
import javax.servlet.http.HttpServletResponse

class ConflictException extends RuntimeException {

    def status
    def error

    def ConflictException(message) {
      
        super(message.toString())
        this.error = "conflict"
        this.status = HttpServletResponse.SC_CONFLICT
    }
}