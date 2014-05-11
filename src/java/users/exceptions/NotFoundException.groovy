package users.exceptions


class NotFoundException extends Exception {
    def status 
    def menssage

    def NotFoundException( inMessage, inStatus) {
        status = inStatus
        message = inMessage
    }
}