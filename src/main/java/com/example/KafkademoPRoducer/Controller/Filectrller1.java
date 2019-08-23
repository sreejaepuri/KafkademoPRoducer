package com.example.KafkademoPRoducer.Controller;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class Filectrller1 {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate123;

    private static  String topic="saroja";

    @GetMapping("publish/{name}")
    public String publishingName(@PathVariable  String name)
    {
        kafkaTemplate.send(topic,name);
        System.out.println("name==================>"+name);
        return name+"published Successfully";
    }

    @GetMapping("/file")
    public String publishingFile() throws IOException {
        String data=new String(Files.readAllBytes(Paths.get("E:\\Kafka\\hello.drl")));
        kafkaTemplate.send(topic,data);
        return "File published Successfully";

    }

}
