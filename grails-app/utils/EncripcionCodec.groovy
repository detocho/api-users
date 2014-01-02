
import java.security.*
import javax.crypto.*
import javax.crypto.spec.*

class EncripcionCodec{

	

	static encode ={ str ->
		
		def cipher = getCipher(Cipher.ENCRYPT_MODE)
    	return cipher.doFinal(str.bytes).encodeBase64()

	}


	static decode ={ str ->
		
		def cipher = getCipher(Cipher.DECRYPT_MODE)
    	return new String(cipher.doFinal(str.decodeBase64()))

	}

	private static getCipher(mode) {
    	def keySpec = new DESKeySpec(getPassword())
    	def cipher = Cipher.getInstance("DES")
    	def keyFactory = SecretKeyFactory.getInstance("DES")
    	cipher.init(mode, keyFactory.generateSecret(keySpec))
    	return cipher
  	}


  	 private static getPassword() { "d@vidP@z".getBytes("UTF-8") }
}