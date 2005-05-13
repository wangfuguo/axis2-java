/*
* Copyright 2004,2005 The Apache Software Foundation.
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
package org.apache.axis.phaseresolver;

import org.apache.axis.description.HandlerDescription;
import org.apache.axis.description.PhasesInclude;
import org.apache.axis.engine.AxisConfiguration;
import org.apache.axis.engine.AxisFault;
import org.apache.axis.engine.Handler;
import org.apache.axis.engine.Phase;

import java.util.ArrayList;


/**
 * This class hold all the phases found in the service.xml and server.xml
 */
public class PhaseHolder {

    /**
     * Field phasemetadatholder
     */
    private ArrayList phasemetadatholder;

    private ArrayList phaseList;

    public PhaseHolder(ArrayList phases) {
        this.phaseList = phases;
        fillPhaseMetaData();
    }

    private void fillPhaseMetaData() {
        phasemetadatholder = new ArrayList();
        for (int i = 0; i < phaseList.size(); i++) {
            Phase phase = (Phase) phaseList.get(i);
            phasemetadatholder.add(new PhaseMetadata(phase.getPhaseName()));
        }

    }

    /**
     * Method isPhaseExist
     *
     * @param phaseName
     * @return
     */
    private boolean isPhaseExist(String phaseName) {
        for (int i = 0; i < phasemetadatholder.size(); i++) {
            PhaseMetadata phase = (PhaseMetadata) phasemetadatholder.get(i);
            if (phase.getName().equals(phaseName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method addHandler
     *
     * @param handler
     * @throws PhaseException
     */
    public void addHandler(HandlerDescription handler) throws PhaseException {
        String phaseName = handler.getRules().getPhaseName();
        if (isPhaseExist(phaseName)) {
            getPhaseMetaData(phaseName).addHandler(handler);
        } else {
            throw new PhaseException("Invalid Phase ," + phaseName
                    + "for the handler "
                    + handler.getName()
                    + " dose not exit in server.xml or refering to phase in diffrent flow");
        }
    }

    /**
     * Method getPhaseMetaData
     *
     * @param phaseName
     * @return
     */
    private PhaseMetadata getPhaseMetaData(String phaseName) {
        for (int i = 0; i < phasemetadatholder.size(); i++) {
            PhaseMetadata phase = (PhaseMetadata) phasemetadatholder.get(i);
            if (phase.getName().equals(phaseName)) {
                return phase;
            }
        }
        return null;
    }

    /**
     * this method is used to get the actual phase object given in the phase array list
     *
     * @param phaseName
     * @return
     */
    private Phase getPhase(String phaseName) {
        for (int i = 0; i < phaseList.size(); i++) {
            Phase phase = (Phase) phaseList.get(i);
            if (phase.getPhaseName().equals(phaseName)) {
                return phase;
            }
        }
        return null;
    }


    public ArrayList getOrderHandler() throws PhaseException {
        ArrayList handlerList = new ArrayList();
        //OrderThePhases();
        HandlerDescription[] handlers;
        for (int i = 0; i < phasemetadatholder.size(); i++) {
            PhaseMetadata phase =
                    (PhaseMetadata) phasemetadatholder.get(i);
            handlers = phase.getOrderedHandlers();
            for (int j = 0; j < handlers.length; j++) {
                handlerList.add(handlers[j]);
            }

        }
        return handlerList;
    }


    /**
     * This is used to order handlers inside a phase
     *
     * @throws PhaseException
     */
    public void getOrderedHandlers() throws PhaseException {
        HandlerDescription[] handlers;
        for (int i = 0; i < phasemetadatholder.size(); i++) {
            PhaseMetadata phase =
                    (PhaseMetadata) phasemetadatholder.get(i);
            Phase axisPhase = getPhase(phase.getName());
            handlers = phase.getOrderedHandlers();
            if (axisPhase != null) {
                for (int j = 0; j < handlers.length; j++) {
                    axisPhase.addHandler(handlers[j].getHandler());
                }
            }
        }
    }


    public void buildTransportChain(PhasesInclude trnsport, int chainType)
            throws PhaseException {
        try {
            //OrderThePhases();

            HandlerDescription[] handlers;
            Class handlerClass = null;
            Handler handler;
            switch (chainType) {
                case PhaseMetadata.IN_FLOW:
                    {
                        ArrayList inChain = new ArrayList();
                        for (int i = 0; i < phasemetadatholder.size(); i++) {
                            PhaseMetadata phase =
                                    (PhaseMetadata) phasemetadatholder.get(i);
                            Phase axisPhase = new Phase(phase.getName());
                            handlers = phase.getOrderedHandlers();
                            for (int j = 0; j < handlers.length; j++) {
                                try {
                                    handlerClass = Class.forName(handlers[j].getClassName(), true,
                                            Thread.currentThread().getContextClassLoader());
                                    handler =
                                            (Handler) handlerClass.newInstance();
                                    handler.init(handlers[j]);
                                    handlers[j].setHandler(handler);
                                    axisPhase.addHandler(handlers[j].getHandler());
                                } catch (ClassNotFoundException e) {
                                    throw new PhaseException(e);
                                } catch (IllegalAccessException e) {
                                    throw new PhaseException(e);
                                } catch (InstantiationException e) {
                                    throw new PhaseException(e);
                                }
                            }
                            inChain.add(axisPhase);
                        }
                        trnsport.setPhases(inChain, AxisConfiguration.INFLOW);
                        break;
                    }
                case PhaseMetadata.OUT_FLOW:
                    {
                        ArrayList outChain = new ArrayList();
                        for (int i = 0; i < phasemetadatholder.size(); i++) {
                            PhaseMetadata phase =
                                    (PhaseMetadata) phasemetadatholder.get(i);
                            Phase axisPhase = new Phase(phase.getName());
                            handlers = phase.getOrderedHandlers();
                            for (int j = 0; j < handlers.length; j++) {
                                try {
                                    handlerClass = Class.forName(handlers[j].getClassName(), true,
                                            Thread.currentThread().getContextClassLoader());
                                    handler =
                                            (Handler) handlerClass.newInstance();
                                    handler.init(handlers[j]);
                                    handlers[j].setHandler(handler);
                                    axisPhase.addHandler(handlers[j].getHandler());
                                } catch (ClassNotFoundException e) {
                                    throw new PhaseException(e);
                                } catch (IllegalAccessException e) {
                                    throw new PhaseException(e);
                                } catch (InstantiationException e) {
                                    throw new PhaseException(e);
                                }
                            }
                            outChain.add(axisPhase);
                        }
                        trnsport.setPhases(outChain, AxisConfiguration.OUTFLOW);
                        break;
                    }
                case PhaseMetadata.FAULT_IN_FLOW:
                    {
                        ArrayList faultChain = new ArrayList();
                        for (int i = 0; i < phasemetadatholder.size(); i++) {
                            PhaseMetadata phase =
                                    (PhaseMetadata) phasemetadatholder.get(i);
                            Phase axisPhase = new Phase(phase.getName());
                            handlers = phase.getOrderedHandlers();
                            for (int j = 0; j < handlers.length; j++) {
                                try {
                                    handlerClass = Class.forName(handlers[j].getClassName(), true,
                                            Thread.currentThread().getContextClassLoader());
                                    handler =
                                            (Handler) handlerClass.newInstance();
                                    handler.init(handlers[j]);
                                    handlers[j].setHandler(handler);
                                    axisPhase.addHandler(handlers[j].getHandler());
                                } catch (ClassNotFoundException e) {
                                    throw new PhaseException(e);
                                } catch (IllegalAccessException e) {
                                    throw new PhaseException(e);
                                } catch (InstantiationException e) {
                                    throw new PhaseException(e);
                                }
                            }
                            faultChain.add(axisPhase);
                        }
                        trnsport.setPhases(faultChain, AxisConfiguration.FAULT_IN_FLOW);
                        break;
                    }
                case PhaseMetadata.FAULT_OUT_FLOW:
                    {
                        ArrayList faultChain = new ArrayList();
                        for (int i = 0; i < phasemetadatholder.size(); i++) {
                            PhaseMetadata phase =
                                    (PhaseMetadata) phasemetadatholder.get(i);
                            Phase axisPhase = new Phase(phase.getName());
                            handlers = phase.getOrderedHandlers();
                            for (int j = 0; j < handlers.length; j++) {
                                try {
                                    handlerClass = Class.forName(handlers[j].getClassName(), true,
                                            Thread.currentThread().getContextClassLoader());
                                    handler =
                                            (Handler) handlerClass.newInstance();
                                    handler.init(handlers[j]);
                                    handlers[j].setHandler(handler);
                                    axisPhase.addHandler(handlers[j].getHandler());
                                } catch (ClassNotFoundException e) {
                                    throw new PhaseException(e);
                                } catch (IllegalAccessException e) {
                                    throw new PhaseException(e);
                                } catch (InstantiationException e) {
                                    throw new PhaseException(e);
                                }
                            }
                            faultChain.add(axisPhase);
                        }
                        trnsport.setPhases(faultChain, AxisConfiguration.FAULT_OUT_FLOW);
                        break;
                    }
            }
        } catch (AxisFault e) {
            throw new PhaseException(e);
        }
    }

}
