import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import com.sap.it.api.ITApiFactory;
import com.sap.it.api.mapping.ValueMappingApi
import com.sap.it.api.securestore.SecureStoreService
import com.sap.it.api.securestore.UserCredential
import com.sap.it.api.securestore.exception.SecureStoreException
import groovy.json.*;

def Message processData(Message message) {
    
    def hash_marvel = message.getProperty("property_hash")
    def api_marvel = message.getProperty("property_apikey")
    def ts_marvel = message.getProperty("property_ts")
    def secureStorageService =  ITApiFactory.getService(SecureStoreService.class, null)
   
    
    try{
        def secureParameter_1 = secureStorageService.getUserCredential("ts_marvel")
        def secureParameter_2 = secureStorageService.getUserCredential("apikey_marvel")
        def secureParameter_3 = secureStorageService.getUserCredential("hash_marvel")
        
        def ts = secureParameter_1.getPassword().toString()
        def apikey = secureParameter_2.getPassword().toString()
        def hash = secureParameter_3.getPassword().toString()
        
        message.setProperty("ts-marvel", ts)
        message.setProperty("apikey-marvel", apikey)
        message.setProperty("hash-marvel", hash)
    } catch(Exception e){
        throw new SecureStoreException("Secure Parameter not available")
    }
    
    return message;
}
