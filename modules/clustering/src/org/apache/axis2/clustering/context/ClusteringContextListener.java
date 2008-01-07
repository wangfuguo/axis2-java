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
package org.apache.axis2.clustering.context;

import org.apache.axis2.clustering.ClusteringFault;
import org.apache.axis2.clustering.MessageSender;
import org.apache.axis2.context.AbstractContext;
import org.apache.axis2.context.ContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 */
public class ClusteringContextListener implements ContextListener {
    private static final Log log = LogFactory.getLog(ClusteringContextListener.class);

    private MessageSender sender;

    public ClusteringContextListener(MessageSender sender) {
        this.sender = sender;
    }

    public void contextCreated(AbstractContext context) {
    }

    public void contextRemoved(AbstractContext context) {
        ContextClusteringCommand command =
                ContextClusteringCommandFactory.getRemoveCommand(context);
        if(command != null){
            try {
                sender.sendToGroup(command);
            } catch (ClusteringFault e) {
                log.error("Cannot send context removed message to cluster", e);
            }
        }
    }
}
