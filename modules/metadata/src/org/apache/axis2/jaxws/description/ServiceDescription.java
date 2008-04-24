/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.axis2.jaxws.description;

import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.jaxws.catalog.JAXWSCatalogManager;
import org.apache.axis2.jaxws.description.xml.handler.HandlerChainsType;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.PortInfo;
import java.util.Collection;
import java.util.List;

/**
 * A ServiceDescription corresponds to a Service under which there can be a collection of enpdoints.
 * In WSDL 1.1 terms, then, a ServiceDescription corresponds to a wsdl:Service under which there are
 * one or more wsdl:Port entries. The ServiceDescription is the root of the metdata abstraction
 * Description hierachy.
 * <p/>
 * The Description hierachy is:
 * <pre>
 * ServiceDescription
 *     EndpointDescription[]
 *         EndpointInterfaceDescription
 *             OperationDescription[]
 *                 ParameterDescription[]
 *                 FaultDescription[]
 * <p/>
 * <b>ServiceDescription details</b>
 * <p/>
 *     CORRESPONDS TO:
 *         On the Client: The JAX-WS Service class or generated subclass.
 * <p/>
 *         On the Server: The Service implementation.  Note that there is a 1..1
 *         correspondence between a ServiceDescription and EndpointDescription
 *         on the server side.
 * <p/>
 *     AXIS2 DELEGATE:      None
 * <p/>
 *     CHILDREN:            1..n EndpointDescription
 * <p/>
 *     ANNOTATIONS:
 *         None
 * <p/>
 *     WSDL ELEMENTS:
 *         service
 * <p/>
 *  </pre>
 */

public interface ServiceDescription {
    
	public abstract EndpointDescription[] getEndpointDescriptions();

    public abstract Collection<EndpointDescription> getEndpointDescriptions_AsCollection();

    public abstract EndpointDescription getEndpointDescription(QName portQName);

    // Called the client-side to retrieve defined and dynamic ports
    public abstract EndpointDescription getEndpointDescription(QName portQName, Object serviceDelegateKey);

    /**
     * Return the EndpointDescriptions corresponding to the SEI class.  Note that Dispatch endpoints
     * will never be returned because they do not have an associated SEI.
     *
     * @param seiClass
     * @return
     */
    public abstract EndpointDescription[] getEndpointDescription(Class seiClass);

    public abstract ConfigurationContext getAxisConfigContext();

    public abstract ServiceClient getServiceClient(QName portQName, Object serviceDelegateKey);

    public abstract QName getServiceQName();

    /**
     * Return the handler chain configuration information as a HandlerChainsType object.  If the
     * key is non-null then it is used to look for handler chain configuration information in the
     * sparse metadata.  The order in which the configuration information is resolved is:
     * 1) Look in sparse composite if the key is not null
     * 2) Look in the composite
     * 3) Look for a HandlerChain annotation and read in the file it specifies  
     * 
     * @param serviceDelegateKey May be null.  If non-null, used to look for service-delegate
     *     specific sparse composite information.
     * @return A HandlerChainsType object or null
     */
    public abstract HandlerChainsType getHandlerChain(Object serviceDelegateKey);
    
    /**
     * Return the handler chain configuration information as a HandlerChainsType object.
     * This is the same as calling getHandlerChain(null).
     * @see #getHandlerChain(Object)
     */
    public abstract HandlerChainsType getHandlerChain();
    
    /**
     * Returns a list of the ports for this serivce.  The ports returned are the - Ports declared
     * ports for this Service.  They can be delcared in the WSDL or via annotations. - Dynamic ports
     * added to the service
     *
     * @param serviceDelegateKey This should always be non-null when called via ServiceDelegate and is
     *                            used to help retrieve dynamic ports per client

     * @return
     */
    public List<QName> getPorts(Object serviceDelegateKey);

    public ServiceRuntimeDescription getServiceRuntimeDesc(String name);

    public void setServiceRuntimeDesc(ServiceRuntimeDescription ord);
    
    public boolean isServerSide();
    
    /**
     * Answer if MTOM is enabled for the service represented by this Service Description.  This
     * is currently only supported on the service-requester side; it is not supported on the 
     * service-provider side.  If the key is non-null, it is used to look up an sparse metadata
     * that may have been specified when the Service Description was created.
     *  
     * @param key If non-null, used to look up any sparse metadata that may have been specified
     *     when the service was created.
     * @return TRUE if mtom was enabled either in the sparse metadata or in the composite; FALSE
     *     othewise.
     */
    public boolean isMTOMEnabled(Object key);
    
    public QName getPreferredPort(Object key);
    
    public JAXWSCatalogManager getCatalogManager();

    /**
     * Answer information for resolved handlers for the given port.  This information is set
     * when the handler resolver initially resolves the handlers based on the handler 
     * configuration information.  It is cached on the service description for performance 
     * so that subsequent queries by other handler resolvers for the same port do not have to
     * re-resolve the information from the handler configuration information.  
     * 
     * @param portInfo Port for which the handler information is desired
     * @return An object containing information for the resolved handlers, or null if no 
     *     information is found in the cache.
     */
    public ResolvedHandlersDescription getResolvedHandlersDescription(PortInfo portInfo);

    /**
     * Cache information for handlers which have been resolved for this port. This information is set
     * when the handler resolver initially resolves the handlers based on the handler 
     * configuration information.  It is cached on the service description for performance 
     * so that subsequent queries by other handler resolvers for the same port do not have to
     * re-resolve the information from the handler configuration information.
     *   
     * @param portInfo Port for which the handler information should be cached
     * @param resolvedHandlersInfo An object containing information for the resolved handlers
     */
    public void setResolvedHandlersDescription(PortInfo portInfo, ResolvedHandlersDescription resolvedHandlersInfo);
}