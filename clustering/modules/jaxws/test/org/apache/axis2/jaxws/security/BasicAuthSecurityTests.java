/*
 * Copyright 2004,2005 The Apache Software Foundation.
 * Copyright 2006 International Business Machines Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.axis2.jaxws.security;

import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import junit.framework.TestCase;
import org.apache.axis2.jaxws.BindingProvider;

public class BasicAuthSecurityTests extends TestCase {

    private String endpointUrl = "http://localhost:8080/axis2/services/BasicAuthSecurityService";
    private String xmlString = "<invoke>test input</invoke>";
    private QName SERVICE_QNAME = new QName("http://ws.apache.org/axis2", "BasicAuthSecurityService");
    private QName PORT_QNAME = new QName("http://ws.apache.org/axis2", "SimpleProviderServiceSOAP11port0");

	private String USER_ID = "testid";
	private String PASSWORD = "testid";

    protected void setUp() throws Exception {
            super.setUp();
    }

    protected void tearDown() throws Exception {
            super.tearDown();
    }

    public BasicAuthSecurityTests(String name) {
        super(name);
    }
    
    public void testBasicAuth() throws Exception {
        System.out.println("---------------------------------------");
        System.out.println("test: " + getName());
        
        Dispatch<String> dispatch = getDispatch(Service.Mode.PAYLOAD,
        		                                endpointUrl,
        		                                SOAPBinding.SOAP11HTTP_BINDING);
        
        System.out.println(">> Invoking Dispatch<String> BasicAuthSecurityService");
        String retVal = dispatch.invoke(xmlString);
        System.out.println(">> Response [" + retVal + "]");
    }
    
    public void testBasicAuth_uid_pwd() throws Exception {
        System.out.println("---------------------------------------");
        System.out.println("test: " + getName());
        
        Dispatch<String> dispatch = getDispatch(Service.Mode.PAYLOAD,
        		                                endpointUrl,
        		                                SOAPBinding.SOAP11HTTP_BINDING);
        
        dispatch.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, USER_ID);
		dispatch.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PASSWORD);

        System.out.println(">> Invoking Dispatch<String> BasicAuthSecurityService");
        String retVal = dispatch.invoke(xmlString);
        System.out.println(">> Response [" + retVal + "]");
    }
    
    public void testBasicAuth_uid()throws Exception{
        System.out.println("---------------------------------------");
        System.out.println("test: " + getName());
        
        Dispatch<String> dispatch = getDispatch(Service.Mode.PAYLOAD,
        		                                endpointUrl,
        		                                SOAPBinding.SOAP11HTTP_BINDING);
        
        dispatch.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, USER_ID);

        System.out.println(">> Invoking Dispatch<String> BasicAuthSecurityService");
        
        try{
        	String retVal = dispatch.invoke(xmlString);
            System.out.println(">> Response [" + retVal + "]");
            
            fail("Set USERID with no PASSWORD: WebServiceException is expected");
        }
        catch(WebServiceException wse){
        	System.out.println(getName()+": "+ wse);
        }
    }
    
    public void testBasicAuth_pwd()throws Exception{
        System.out.println("---------------------------------------");
        System.out.println("test: " + getName());
        
        Dispatch<String> dispatch = getDispatch(Service.Mode.PAYLOAD,
        		                                endpointUrl,
        		                                SOAPBinding.SOAP11HTTP_BINDING);
        
		dispatch.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PASSWORD);

        System.out.println(">> Invoking Dispatch<String> BasicAuthSecurityService");
        
        try{
        	String retVal = dispatch.invoke(xmlString);
            System.out.println(">> Response [" + retVal + "]");
            
            fail("Set PASSWORD with no USERID: WebServiceException is expected");
        }
        catch(WebServiceException wse){
        	System.out.println(getName()+": "+ wse);
        }
    }
    
	/**
	 * Auxiliary method, generates a Dispatch object on demand
	 * 
	 * @param mode
	 *            Service.Mode
	 * @param endpoint
	 *            endpoint address
	 * @param binding
	 *            binding type
	 * @return
	 */
	private Dispatch<String> getDispatch(Service.Mode mode, String endpoint,String binding) {
		
		Service service = Service.create(SERVICE_QNAME);
		
		service.addPort(PORT_QNAME, binding, endpoint);
		javax.xml.ws.Dispatch<String> dispatch = service.createDispatch(PORT_QNAME, String.class,mode);

		assertNotNull("Dispatch not null", dispatch);

		return dispatch;
	}
}