import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*;
def Message processData(Message message) {
    
    def property = message.getProperties()
    def index = property.get("index").toInteger()
    def body = message.getBody(String)
    def jsonParser = new JsonSlurper()
    def jsonObject = jsonParser.parseText(body)
    
    def json = jsonObject.data.results[index].thumbnail
    def name = jsonObject.data.results[index].name
    def id = jsonObject.data.results[index].id
    def description = jsonObject.data.results[index].description
     
    message.setHeader(
                      "images",
                      "<h1> id: " + id + "</h1> "+
                      "<h1> name: " + name + " </h1> "+
                      "<h1> description: " + description + "</h1> "+
                      "<img src=" +json.path+"/standard_fantastic."+json.extension+ " />" )
    index += 1
    message.setProperty("index", index)
    
    return message;
}