import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*;

def Message processData(Message message) {
    
    def propriedades = message.getProperties()
    def index = propriedades.get("index")
    index += 1
    
    message.setProperty("index", index)
    return message;
}