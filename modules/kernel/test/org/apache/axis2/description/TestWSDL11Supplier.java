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

package org.apache.axis2.description;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.dataretrieval.WSDLSupplier;

public class TestWSDL11Supplier implements WSDLSupplier {
    public Definition getWSDL(AxisService service) throws AxisFault {
        Definition def = null;
        try {
            def = WSDLFactory.newInstance().newDefinition();
            def.setQName(new QName("TestWSDL11SupplierDefinition"));
        } catch (WSDLException e) {
            e.printStackTrace();
        }
        return def;

    }
}
