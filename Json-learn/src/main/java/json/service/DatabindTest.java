package json.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.ObjectNode;
import json.entity.Student;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author : [seaflower]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @Time : [2023/8/14 22:50]
 */
public class DatabindTest {
    public static void main(String[] args) throws JsonProcessingException {
        // 从 JSON 到 POJO 对象
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue("{\"name\":\"Bob\", \"age\":13}", Student.class);
        System.out.println(student.name + " " + student.age);
        
        // 从 POJO 到 JSON 
        String jsonString = objectMapper.writeValueAsString(student);
        System.out.println(jsonString);
        
        // json to map
//        Map<String, String> map = objectMapper.readValue(jsonString, Map.class);
//        System.out.println(map);
//        System.out.println(map.get("age"));


        Map map = objectMapper.readValue(jsonString, Map.class);
        System.out.println(map);
        System.out.println(map.get("age"));
        
        // 遍历
        try {
            objectMapper.writeValue(new File("student.json"), student);
            // read to treeModel 
            JsonNode root = objectMapper.readTree(new File("E:/JavaLearn-Git/student.json"));
            System.out.println(root.get("name").asText());
            // add a node
            root.withObject("/settlement").put("cards", "bank");
            String json = objectMapper.writeValueAsString(root);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
