//import android.os.Build
//import android.os.Bundle
//import android.security.keystore.KeyGenParameterSpec
//import android.security.keystore.KeyProperties
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import com.example.structureapplication.R
//import java.security.KeyStore
//import javax.crypto.Cipher
//import javax.crypto.KeyGenerator
//import javax.crypto.SecretKey
//import javax.crypto.spec.IvParameterSpec
//
//class KeyChainDemoActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val keyGenerator =
//            KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
//        val keyGenParameterSpec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            KeyGenParameterSpec.Builder(
//                "MyKeyAlias",
//                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
//            )
//                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
//                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
//                .build()
//        } else {
//
//        }
//
//        keyGenerator.init(keyGenParameterSpec)
//        keyGenerator.generateKey()
//
//        val pair = encryptData("Test this encryption")
//
//        val decryptedData = decryptData(pair.first, pair.second)
//
//        val encrypted = pair.second.toString(Charsets.UTF_8)
//        println("Encrypted data: $encrypted")
//        println("Decrypted data: $decryptedData")
//
//
//
//        Log.d("fdfsfsfsffsf", encrypted)
//        Log.d("fdfsfsfsffsf", decryptedData)
//
//    }
//
//    fun getKey(): SecretKey {
//        val keystore = KeyStore.getInstance("AndroidKeyStore")
//        keystore.load(null)
//
//        val secretKeyEntry = keystore.getEntry("MyKeyAlias", null) as KeyStore.SecretKeyEntry
//        return secretKeyEntry.secretKey
//    }
//
//    fun encryptData(data: String): Pair<ByteArray, ByteArray> {
//        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
//
//        var temp = data
//        while (temp.toByteArray().size % 16 != 0)
//            temp += "\u0020"
//
//        cipher.init(Cipher.ENCRYPT_MODE, getKey())
//
//        val ivBytes = cipher.iv
//        val encryptedBytes = cipher.doFinal(temp.toByteArray(Charsets.UTF_8))
//
//        return Pair(ivBytes, encryptedBytes)
//    }
//
//    fun decryptData(ivBytes: ByteArray, data: ByteArray): String {
//        val cipher = Cipher.getInstance("AES/CBC/NoPadding")
//        val spec = IvParameterSpec(ivBytes)
//
//        cipher.init(Cipher.DECRYPT_MODE, getKey(), spec)
//        return cipher.doFinal(data).toString(Charsets.UTF_8).trim()
//    }
//}
//
