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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.03.21 at 10:56:51 AM CDT 
//


package org.apache.axis2.jaxws.description.xml.handler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistence-unit-ref element contains a declaration of Deployment Component's reference to a
 * persistence unit associated within a Deployment Component's environment. It consists of:
 * <p/>
 * - an optional description - the persistence unit reference name - an optional persistence unit
 * name.  If not specified, the default persistence unit is assumed. - optional injection targets
 * <p/>
 * Examples:
 * <p/>
 * <persistence-unit-ref> <persistence-unit-ref-name>myPersistenceUnit </persistence-unit-ref-name>
 * </persistence-unit-ref>
 * <p/>
 * <persistence-unit-ref> <persistence-unit-ref-name>myPersistenceUnit </persistence-unit-ref-name>
 * <persistence-unit-name>PersistenceUnit1 </persistence-unit-name> </persistence-unit-ref>
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * <p>Java class for persistence-unit-refType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="persistence-unit-refType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://java.sun.com/xml/ns/javaee}descriptionType"
 * maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="persistence-unit-ref-name" type="{http://java.sun.com/xml/ns/javaee}jndi-nameType"/>
 *         &lt;element name="persistence-unit-name" type="{http://java.sun.com/xml/ns/javaee}string"
 * minOccurs="0"/>
 *         &lt;group ref="{http://java.sun.com/xml/ns/javaee}resourceGroup"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "persistence-unit-refType", propOrder = {
        "description",
        "persistenceUnitRefName",
        "persistenceUnitName",
        "mappedName",
        "injectionTarget"
        })
public class PersistenceUnitRefType {

    @XmlElement(namespace = "http://java.sun.com/xml/ns/javaee", required = true)
    protected List<DescriptionType> description;
    @XmlElement(name = "persistence-unit-ref-name", namespace = "http://java.sun.com/xml/ns/javaee",
                required = true)
    protected JndiNameType persistenceUnitRefName;
    @XmlElement(name = "persistence-unit-name", namespace = "http://java.sun.com/xml/ns/javaee")
    protected org.apache.axis2.jaxws.description.xml.handler.String persistenceUnitName;
    @XmlElement(name = "mapped-name", namespace = "http://java.sun.com/xml/ns/javaee")
    protected XsdStringType mappedName;
    @XmlElement(name = "injection-target", namespace = "http://java.sun.com/xml/ns/javaee",
                required = true)
    protected List<InjectionTargetType> injectionTarget;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the description property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the description property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link DescriptionType }
     */
    public List<DescriptionType> getDescription() {
        if (description == null) {
            description = new ArrayList<DescriptionType>();
        }
        return this.description;
    }

    /**
     * Gets the value of the persistenceUnitRefName property.
     *
     * @return possible object is {@link JndiNameType }
     */
    public JndiNameType getPersistenceUnitRefName() {
        return persistenceUnitRefName;
    }

    /**
     * Sets the value of the persistenceUnitRefName property.
     *
     * @param value allowed object is {@link JndiNameType }
     */
    public void setPersistenceUnitRefName(JndiNameType value) {
        this.persistenceUnitRefName = value;
    }

    /**
     * Gets the value of the persistenceUnitName property.
     *
     * @return possible object is {@link String }
     */
    public org.apache.axis2.jaxws.description.xml.handler.String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    /**
     * Sets the value of the persistenceUnitName property.
     *
     * @param value allowed object is {@link String
     *              }
     */
    public void setPersistenceUnitName(
            org.apache.axis2.jaxws.description.xml.handler.String value) {
        this.persistenceUnitName = value;
    }

    /**
     * Gets the value of the mappedName property.
     *
     * @return possible object is {@link XsdStringType }
     */
    public XsdStringType getMappedName() {
        return mappedName;
    }

    /**
     * Sets the value of the mappedName property.
     *
     * @param value allowed object is {@link XsdStringType }
     */
    public void setMappedName(XsdStringType value) {
        this.mappedName = value;
    }

    /**
     * Gets the value of the injectionTarget property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the injectionTarget property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInjectionTarget().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link InjectionTargetType }
     */
    public List<InjectionTargetType> getInjectionTarget() {
        if (injectionTarget == null) {
            injectionTarget = new ArrayList<InjectionTargetType>();
        }
        return this.injectionTarget;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link java.lang.String }
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link java.lang.String }
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

}