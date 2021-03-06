/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.movingimage24.cdi.jms;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * <p>
 * A Message Driven Bean that asynchronously receives and processes
 * messages of the form key=value and updates a database table with
 * those values using the services of an injected bean.
 * </p>
 *
 * @author Serge Pagop (spagop@redhat.com)
 * @author Mike Musgrove (mmusgrov@redhat.com)
 *
 */
@MessageDriven(name = "JmsListener", activationConfig = {
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/MovieQueue"),
@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class JmsListener implements MessageListener {    
	@Inject
	Logger logger;

    public void onMessage(Message msg) {
    	logger.info("Received Message: " + msg.toString());
    }
}
