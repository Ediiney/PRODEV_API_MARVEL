import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*;

def Message processData(Message message) {
    
    def propriedades = message.getProperties()
    def array = propriedades.get("array")
    def jsonParser = new JsonSlurper()
    def jsonObject = jsonParser.parseText(array)
    def index = propriedades.get("index")
    message.setHeader("image", jsonObject[index])

    return message;
}