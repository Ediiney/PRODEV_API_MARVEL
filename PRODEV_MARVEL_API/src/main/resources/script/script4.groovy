import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*;
def Message processData(Message message) {
    
    def body = message.getBody(String)
    def jsonParser = new JsonSlurper()
    def jsonObject = jsonParser.parseText(body)
    def json
    def array = []
    
    // for ( int i = 0; i < jsonObject.data.results.size(); i++){
    //     json = JsonOutput.toJson(
    //     jsonObject.data.results[i].thumbnail.path + "/portrait_fantastic.jpg" )  
            
    // array.push(json)
    // }
    
    jsonObject.data.results.each{e ->
        json = JsonOutput.toJson(  
                                    "<h1> id: "+ e.id +"</h1>"+
                                    "<h1> name: " +e.name + "</h1> " +
                                    "<h1> Description: " + e.description + " </h1> " +
                                    "<img  src="+e.thumbnail.path+"/portrait_fantastic."+e.thumbnail.extension+ " />")
        array.push(json)
    }
  
    message.setProperty("json_size", jsonObject.size())
    message.setProperty("array", array.toString())
    message.setBody(array.toString())
    message.setHeader("images", array)
    return message;
}