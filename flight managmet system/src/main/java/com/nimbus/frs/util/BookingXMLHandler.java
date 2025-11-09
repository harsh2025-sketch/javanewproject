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

import com.nimbus.frs.model.Booking;

/**
 * XML Handler for Booking data operations
 */
public class BookingXMLHandler {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String filePath;
    
    public BookingXMLHandler(String filePath) {
        this.filePath = filePath;
        initializeFile();
    }
    
    private void initializeFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                Document document = org.dom4j.DocumentHelper.createDocument();
                Element root = document.addElement("bookings");
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
     * Add a new booking to XML
     */
    public void addBooking(Booking booking) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        Element bookingElement = root.addElement("booking");
        bookingElement.addAttribute("id", booking.getBookingId());
        
        bookingElement.addElement("passengerId").setText(booking.getPassengerId());
        bookingElement.addElement("flightId").setText(booking.getFlightId());
        bookingElement.addElement("seatNumber").setText(booking.getSeatNumber());
        bookingElement.addElement("travelClass").setText(booking.getTravelClass());
        bookingElement.addElement("fare").setText(String.valueOf(booking.getFare()));
        bookingElement.addElement("taxes").setText(String.valueOf(booking.getTaxes()));
        bookingElement.addElement("totalAmount").setText(String.valueOf(booking.getTotalAmount()));
        bookingElement.addElement("bookingDate").setText(booking.getBookingDate().format(DATE_FORMATTER));
        bookingElement.addElement("status").setText(booking.getStatus());
        bookingElement.addElement("paymentId").setText(booking.getPaymentId() != null ? booking.getPaymentId() : "");
        bookingElement.addElement("paymentStatus").setText(booking.getPaymentStatus());
        
        saveDocument(document);
    }
    
    /**
     * Get all bookings from XML
     */
    public List<Booking> getAllBookings() throws DocumentException {
        List<Booking> bookings = new ArrayList<>();
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> bookingElements = root.elements("booking");
        for (Element element : bookingElements) {
            bookings.add(parseBookingElement(element));
        }
        
        return bookings;
    }
    
    /**
     * Get booking by ID
     */
    public Booking getBookingById(String bookingId) throws DocumentException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> bookingElements = root.elements("booking");
        for (Element element : bookingElements) {
            if (bookingId.equals(element.attributeValue("id"))) {
                return parseBookingElement(element);
            }
        }
        
        return null;
    }
    
    /**
     * Get bookings by passenger ID
     */
    public List<Booking> getBookingsByPassengerId(String passengerId) throws DocumentException {
        List<Booking> bookings = new ArrayList<>();
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> bookingElements = root.elements("booking");
        for (Element element : bookingElements) {
            if (passengerId.equals(element.elementText("passengerId"))) {
                bookings.add(parseBookingElement(element));
            }
        }
        
        return bookings;
    }
    
    /**
     * Get bookings by flight ID
     */
    public List<Booking> getBookingsByFlightId(String flightId) throws DocumentException {
        List<Booking> bookings = new ArrayList<>();
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> bookingElements = root.elements("booking");
        for (Element element : bookingElements) {
            if (flightId.equals(element.elementText("flightId"))) {
                bookings.add(parseBookingElement(element));
            }
        }
        
        return bookings;
    }
    
    /**
     * Update booking
     */
    public void updateBooking(Booking booking) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> bookingElements = root.elements("booking");
        for (Element element : bookingElements) {
            if (booking.getBookingId().equals(element.attributeValue("id"))) {
                element.element("passengerId").setText(booking.getPassengerId());
                element.element("flightId").setText(booking.getFlightId());
                element.element("seatNumber").setText(booking.getSeatNumber());
                element.element("travelClass").setText(booking.getTravelClass());
                element.element("fare").setText(String.valueOf(booking.getFare()));
                element.element("taxes").setText(String.valueOf(booking.getTaxes()));
                element.element("totalAmount").setText(String.valueOf(booking.getTotalAmount()));
                element.element("bookingDate").setText(booking.getBookingDate().format(DATE_FORMATTER));
                element.element("status").setText(booking.getStatus());
                element.element("paymentId").setText(booking.getPaymentId() != null ? booking.getPaymentId() : "");
                element.element("paymentStatus").setText(booking.getPaymentStatus());
                
                saveDocument(document);
                break;
            }
        }
    }
    
    /**
     * Cancel booking
     */
    public void cancelBooking(String bookingId) throws DocumentException, IOException {
        Booking booking = getBookingById(bookingId);
        if (booking != null) {
            booking.setStatus("CANCELLED");
            updateBooking(booking);
        }
    }
    
    /**
     * Delete booking
     */
    public void deleteBooking(String bookingId) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> bookingElements = root.elements("booking");
        for (Element element : bookingElements) {
            if (bookingId.equals(element.attributeValue("id"))) {
                root.remove(element);
                saveDocument(document);
                break;
            }
        }
    }
    
    private Booking parseBookingElement(Element element) {
        Booking booking = new Booking();
        booking.setBookingId(element.attributeValue("id"));
        booking.setPassengerId(element.elementText("passengerId"));
        booking.setFlightId(element.elementText("flightId"));
        booking.setSeatNumber(element.elementText("seatNumber"));
        booking.setTravelClass(element.elementText("travelClass"));
        booking.setFare(Double.parseDouble(element.elementText("fare")));
        booking.setTaxes(Double.parseDouble(element.elementText("taxes")));
        booking.setTotalAmount(Double.parseDouble(element.elementText("totalAmount")));
        booking.setBookingDate(LocalDateTime.parse(element.elementText("bookingDate"), DATE_FORMATTER));
        booking.setStatus(element.elementText("status"));
        
        String paymentId = element.elementText("paymentId");
        if (paymentId != null && !paymentId.isEmpty()) {
            booking.setPaymentId(paymentId);
        }
        
        booking.setPaymentStatus(element.elementText("paymentStatus"));
        
        return booking;
    }
}
