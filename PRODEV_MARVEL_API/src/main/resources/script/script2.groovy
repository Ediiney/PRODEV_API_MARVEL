
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*;

def Message processData(Message message) {
   
    def body = message.getBody(String)
    def jsonParser = new JsonSlurper()
    def jsonObject = jsonParser.parseText(body)
    def json
    
    def array = []
    
    for ( int i = 0; i < jsonObject.data.results.size(); i++){
        json = JsonOutput.toJson(
        path : jsonObject.data.results[i].thumbnail.path + ".jpg"
        
        )
    array.push(json)
    }
    
    message.setHeader("img", json)
    message.setBody(array.toString())
    message.setHeader("Content-Type", "application/json")
    
    return message;

}