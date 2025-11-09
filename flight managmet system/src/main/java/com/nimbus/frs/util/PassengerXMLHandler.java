package com.nimbus.frs.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.nimbus.frs.model.Passenger;

/**
 * XML Handler for Passenger data operations
 */
public class PassengerXMLHandler {
    private String filePath;
    
    public PassengerXMLHandler(String filePath) {
        this.filePath = filePath;
        initializeFile();
    }
    
    private void initializeFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                Document document = org.dom4j.DocumentHelper.createDocument();
                Element root = document.addElement("passengers");
                saveDocument(document);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private Document loadDocument() throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(new File(filePath));
    }
    
    private void saveDocument(Document document) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter(filePath), format);
        writer.write(document);
        writer.close();
    }
    
    /**
     * Add a new passenger to XML
     */
    public void addPassenger(Passenger passenger) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        Element passengerElement = root.addElement("passenger");
        passengerElement.addAttribute("id", passenger.getPassengerId());
        
        passengerElement.addElement("firstName").setText(passenger.getFirstName());
        passengerElement.addElement("lastName").setText(passenger.getLastName());
        passengerElement.addElement("email").setText(passenger.getEmail());
        passengerElement.addElement("phone").setText(passenger.getPhone());
        passengerElement.addElement("dateOfBirth").setText(passenger.getDateOfBirth() != null ? passenger.getDateOfBirth() : "");
        passengerElement.addElement("gender").setText(passenger.getGender() != null ? passenger.getGender() : "");
        passengerElement.addElement("passportNumber").setText(passenger.getPassportNumber() != null ? passenger.getPassportNumber() : "");
        passengerElement.addElement("nationality").setText(passenger.getNationality() != null ? passenger.getNationality() : "");
        passengerElement.addElement("address").setText(passenger.getAddress() != null ? passenger.getAddress() : "");
        passengerElement.addElement("mealPreference").setText(passenger.getMealPreference() != null ? passenger.getMealPreference() : "");
        passengerElement.addElement("specialRequests").setText(passenger.getSpecialRequests() != null ? passenger.getSpecialRequests() : "");
        
        saveDocument(document);
    }
    
    /**
     * Get all passengers from XML
     */
    public List<Passenger> getAllPassengers() throws DocumentException {
        List<Passenger> passengers = new ArrayList<>();
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> passengerElements = root.elements("passenger");
        for (Element element : passengerElements) {
            passengers.add(parsePassengerElement(element));
        }
        
        return passengers;
    }
    
    /**
     * Get passenger by ID
     */
    public Passenger getPassengerById(String passengerId) throws DocumentException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> passengerElements = root.elements("passenger");
        for (Element element : passengerElements) {
            if (passengerId.equals(element.attributeValue("id"))) {
                return parsePassengerElement(element);
            }
        }
        
        return null;
    }
    
    /**
     * Get passenger by email
     */
    public Passenger getPassengerByEmail(String email) throws DocumentException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> passengerElements = root.elements("passenger");
        for (Element element : passengerElements) {
            if (email.equals(element.elementText("email"))) {
                return parsePassengerElement(element);
            }
        }
        
        return null;
    }
    
    /**
     * Update passenger information
     */
    public void updatePassenger(Passenger passenger) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> passengerElements = root.elements("passenger");
        for (Element element : passengerElements) {
            if (passenger.getPassengerId().equals(element.attributeValue("id"))) {
                element.element("firstName").setText(passenger.getFirstName());
                element.element("lastName").setText(passenger.getLastName());
                element.element("email").setText(passenger.getEmail());
                element.element("phone").setText(passenger.getPhone());
                element.element("dateOfBirth").setText(passenger.getDateOfBirth() != null ? passenger.getDateOfBirth() : "");
                element.element("gender").setText(passenger.getGender() != null ? passenger.getGender() : "");
                element.element("passportNumber").setText(passenger.getPassportNumber() != null ? passenger.getPassportNumber() : "");
                element.element("nationality").setText(passenger.getNationality() != null ? passenger.getNationality() : "");
                element.element("address").setText(passenger.getAddress() != null ? passenger.getAddress() : "");
                element.element("mealPreference").setText(passenger.getMealPreference() != null ? passenger.getMealPreference() : "");
                element.element("specialRequests").setText(passenger.getSpecialRequests() != null ? passenger.getSpecialRequests() : "");
                
                saveDocument(document);
                break;
            }
        }
    }
    
    /**
     * Delete passenger
     */
    public void deletePassenger(String passengerId) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> passengerElements = root.elements("passenger");
        for (Element element : passengerElements) {
            if (passengerId.equals(element.attributeValue("id"))) {
                root.remove(element);
                saveDocument(document);
                break;
            }
        }
    }
    
    private Passenger parsePassengerElement(Element element) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(element.attributeValue("id"));
        passenger.setFirstName(element.elementText("firstName"));
        passenger.setLastName(element.elementText("lastName"));
        passenger.setEmail(element.elementText("email"));
        passenger.setPhone(element.elementText("phone"));
        passenger.setDateOfBirth(element.elementText("dateOfBirth"));
        passenger.setGender(element.elementText("gender"));
        passenger.setPassportNumber(element.elementText("passportNumber"));
        passenger.setNationality(element.elementText("nationality"));
        passenger.setAddress(element.elementText("address"));
        passenger.setMealPreference(element.elementText("mealPreference"));
        passenger.setSpecialRequests(element.elementText("specialRequests"));
        
        return passenger;
    }
}
