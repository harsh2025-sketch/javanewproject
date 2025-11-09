package com.nimbus.frs.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.nimbus.frs.model.Flight;

/**
 * XML Handler for Flight data operations
 */
public class FlightXMLHandler {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String filePath;
    
    public FlightXMLHandler(String filePath) {
        this.filePath = filePath;
        initializeFile();
    }
    
    private void initializeFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                Document document = org.dom4j.DocumentHelper.createDocument();
                Element root = document.addElement("flights");
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
     * Add a new flight to XML
     */
    public void addFlight(Flight flight) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        Element flightElement = root.addElement("flight");
        flightElement.addAttribute("id", flight.getFlightId());
        
        flightElement.addElement("flightNumber").setText(flight.getFlightNumber());
        flightElement.addElement("airline").setText(flight.getAirline());
        flightElement.addElement("source").setText(flight.getSource());
        flightElement.addElement("destination").setText(flight.getDestination());
        flightElement.addElement("departureTime").setText(flight.getDepartureTime().format(DATE_FORMATTER));
        flightElement.addElement("arrivalTime").setText(flight.getArrivalTime().format(DATE_FORMATTER));
        flightElement.addElement("totalSeats").setText(String.valueOf(flight.getTotalSeats()));
        flightElement.addElement("availableSeats").setText(String.valueOf(flight.getAvailableSeats()));
        flightElement.addElement("economyFare").setText(String.valueOf(flight.getEconomyFare()));
        flightElement.addElement("businessFare").setText(String.valueOf(flight.getBusinessFare()));
        flightElement.addElement("status").setText(flight.getStatus());
        flightElement.addElement("aircraftType").setText(flight.getAircraftType() != null ? flight.getAircraftType() : "");
        
        saveDocument(document);
    }
    
    /**
     * Get all flights from XML
     */
    public List<Flight> getAllFlights() throws DocumentException {
        List<Flight> flights = new ArrayList<>();
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> flightElements = root.elements("flight");
        for (Element element : flightElements) {
            flights.add(parseFlightElement(element));
        }
        
        return flights;
    }
    
    /**
     * Get flight by ID
     */
    public Flight getFlightById(String flightId) throws DocumentException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> flightElements = root.elements("flight");
        for (Element element : flightElements) {
            if (flightId.equals(element.attributeValue("id"))) {
                return parseFlightElement(element);
            }
        }
        
        return null;
    }
    
    /**
     * Update flight information
     */
    public void updateFlight(Flight flight) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> flightElements = root.elements("flight");
        for (Element element : flightElements) {
            if (flight.getFlightId().equals(element.attributeValue("id"))) {
                element.element("flightNumber").setText(flight.getFlightNumber());
                element.element("airline").setText(flight.getAirline());
                element.element("source").setText(flight.getSource());
                element.element("destination").setText(flight.getDestination());
                element.element("departureTime").setText(flight.getDepartureTime().format(DATE_FORMATTER));
                element.element("arrivalTime").setText(flight.getArrivalTime().format(DATE_FORMATTER));
                element.element("totalSeats").setText(String.valueOf(flight.getTotalSeats()));
                element.element("availableSeats").setText(String.valueOf(flight.getAvailableSeats()));
                element.element("economyFare").setText(String.valueOf(flight.getEconomyFare()));
                element.element("businessFare").setText(String.valueOf(flight.getBusinessFare()));
                element.element("status").setText(flight.getStatus());
                
                if (element.element("aircraftType") != null) {
                    element.element("aircraftType").setText(flight.getAircraftType() != null ? flight.getAircraftType() : "");
                }
                
                saveDocument(document);
                break;
            }
        }
    }
    
    /**
     * Delete flight
     */
    public void deleteFlight(String flightId) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> flightElements = root.elements("flight");
        for (Element element : flightElements) {
            if (flightId.equals(element.attributeValue("id"))) {
                root.remove(element);
                saveDocument(document);
                break;
            }
        }
    }
    
    /**
     * Search flights by source and destination
     */
    public List<Flight> searchFlights(String source, String destination, LocalDateTime date) throws DocumentException {
        List<Flight> flights = new ArrayList<>();
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> flightElements = root.elements("flight");
        for (Element element : flightElements) {
            Flight flight = parseFlightElement(element);
            
            boolean sourceMatch = source == null || source.isEmpty() || 
                                 flight.getSource().equalsIgnoreCase(source);
            boolean destMatch = destination == null || destination.isEmpty() || 
                               flight.getDestination().equalsIgnoreCase(destination);
            boolean dateMatch = date == null || 
                               flight.getDepartureTime().toLocalDate().equals(date.toLocalDate());
            
            if (sourceMatch && destMatch && dateMatch && flight.getAvailableSeats() > 0) {
                flights.add(flight);
            }
        }
        
        return flights;
    }
    
    /**
     * Update seat availability
     */
    public void updateSeatAvailability(String flightId, int seatsToBook) throws DocumentException, IOException {
        Flight flight = getFlightById(flightId);
        if (flight != null) {
            flight.setAvailableSeats(flight.getAvailableSeats() - seatsToBook);
            updateFlight(flight);
        }
    }
    
    private Flight parseFlightElement(Element element) {
        Flight flight = new Flight();
        flight.setFlightId(element.attributeValue("id"));
        flight.setFlightNumber(element.elementText("flightNumber"));
        flight.setAirline(element.elementText("airline"));
        flight.setSource(element.elementText("source"));
        flight.setDestination(element.elementText("destination"));
        flight.setDepartureTime(LocalDateTime.parse(element.elementText("departureTime"), DATE_FORMATTER));
        flight.setArrivalTime(LocalDateTime.parse(element.elementText("arrivalTime"), DATE_FORMATTER));
        flight.setTotalSeats(Integer.parseInt(element.elementText("totalSeats")));
        flight.setAvailableSeats(Integer.parseInt(element.elementText("availableSeats")));
        flight.setEconomyFare(Double.parseDouble(element.elementText("economyFare")));
        flight.setBusinessFare(Double.parseDouble(element.elementText("businessFare")));
        flight.setStatus(element.elementText("status"));
        
        if (element.element("aircraftType") != null) {
            flight.setAircraftType(element.elementText("aircraftType"));
        }
        
        return flight;
    }
}
