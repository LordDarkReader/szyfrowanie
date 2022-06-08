package aes256.szyfrowanie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String test() throws IOException, ScriptException, NoSuchMethodException, ParseException {


    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");
// read script file
        engine.eval(Files.newBufferedReader(Paths.get("C:/Czarek/Scripts/Jsfunctions.js"), StandardCharsets.UTF_8));

    Invocable inv = (Invocable) engine;
// call function from script file
        inv.invokeFunction("openInNewTab", "https://stackoverflow.com/questions/22856279/call-external-javascript-functions-from-java-code");


        return "OK";
    }
}







