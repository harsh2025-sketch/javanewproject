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

import com.nimbus.frs.model.User;

/**
 * XML Handler for User data operations
 */
public class UserXMLHandler {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String filePath;
    
    public UserXMLHandler(String filePath) {
        this.filePath = filePath;
        initializeFile();
    }
    
    private void initializeFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                Document document = org.dom4j.DocumentHelper.createDocument();
                Element root = document.addElement("users");
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
     * Add a new user to XML
     */
    public void addUser(User user) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        Element userElement = root.addElement("user");
        userElement.addAttribute("id", user.getUserId());
        
        userElement.addElement("email").setText(user.getEmail());
        userElement.addElement("password").setText(user.getPassword());
        userElement.addElement("firstName").setText(user.getFirstName());
        userElement.addElement("lastName").setText(user.getLastName());
        userElement.addElement("phone").setText(user.getPhone() != null ? user.getPhone() : "");
        userElement.addElement("role").setText(user.getRole());
        userElement.addElement("registrationDate").setText(user.getRegistrationDate().format(DATE_FORMATTER));
        userElement.addElement("active").setText(String.valueOf(user.isActive()));
        
        saveDocument(document);
    }
    
    /**
     * Get all users from XML
     */
    public List<User> getAllUsers() throws DocumentException {
        List<User> users = new ArrayList<>();
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> userElements = root.elements("user");
        for (Element element : userElements) {
            users.add(parseUserElement(element));
        }
        
        return users;
    }
    
    /**
     * Get user by ID
     */
    public User getUserById(String userId) throws DocumentException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> userElements = root.elements("user");
        for (Element element : userElements) {
            if (userId.equals(element.attributeValue("id"))) {
                return parseUserElement(element);
            }
        }
        
        return null;
    }
    
    /**
     * Get user by email
     */
    public User getUserByEmail(String email) throws DocumentException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> userElements = root.elements("user");
        for (Element element : userElements) {
            if (email.equals(element.elementText("email"))) {
                return parseUserElement(element);
            }
        }
        
        return null;
    }
    
    /**
     * Update user information
     */
    public void updateUser(User user) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> userElements = root.elements("user");
        for (Element element : userElements) {
            if (user.getUserId().equals(element.attributeValue("id"))) {
                element.element("email").setText(user.getEmail());
                element.element("password").setText(user.getPassword());
                element.element("firstName").setText(user.getFirstName());
                element.element("lastName").setText(user.getLastName());
                element.element("phone").setText(user.getPhone() != null ? user.getPhone() : "");
                element.element("role").setText(user.getRole());
                element.element("active").setText(String.valueOf(user.isActive()));
                
                saveDocument(document);
                break;
            }
        }
    }
    
    /**
     * Delete user
     */
    public void deleteUser(String userId) throws DocumentException, IOException {
        Document document = loadDocument();
        Element root = document.getRootElement();
        
        List<Element> userElements = root.elements("user");
        for (Element element : userElements) {
            if (userId.equals(element.attributeValue("id"))) {
                root.remove(element);
                saveDocument(document);
                break;
            }
        }
    }
    
    /**
     * Validate user credentials
     */
    public User validateUser(String email, String hashedPassword) throws DocumentException {
        User user = getUserByEmail(email);
        if (user != null && user.getPassword().equals(hashedPassword) && user.isActive()) {
            return user;
        }
        return null;
    }
    
    private User parseUserElement(Element element) {
        User user = new User();
        user.setUserId(element.attributeValue("id"));
        user.setEmail(element.elementText("email"));
        user.setPassword(element.elementText("password"));
        user.setFirstName(element.elementText("firstName"));
        user.setLastName(element.elementText("lastName"));
        user.setPhone(element.elementText("phone"));
        user.setRole(element.elementText("role"));
        user.setRegistrationDate(LocalDateTime.parse(element.elementText("registrationDate"), DATE_FORMATTER));
        user.setActive(Boolean.parseBoolean(element.elementText("active")));
        
        return user;
    }
}
