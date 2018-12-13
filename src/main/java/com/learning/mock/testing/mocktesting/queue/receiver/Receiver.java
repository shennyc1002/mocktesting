package com.learning.mock.testing.mocktesting.queue.receiver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.mock.testing.mocktesting.entity.Employee;

public class Receiver {

    public void processMessage(String message)
    {
        Employee employee = new Employee();

        try {
            employee = new ObjectMapper().readValue(message, Employee.class);
            System.out.println("Received a message from queue");


        } catch (JsonParseException e) {
          System.out.println("Bad JSON in message: " + e);
        } catch (JsonMappingException e) {
            System.out.println("cannot map JSON to NotificationRequest: " + message);
            System.out.println("Object Parsing error: " + e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
