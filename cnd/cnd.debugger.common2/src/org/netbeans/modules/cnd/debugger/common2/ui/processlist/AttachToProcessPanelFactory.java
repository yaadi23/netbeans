/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.cnd.debugger.common2.ui.processlist;

import javax.swing.JButton;
import org.netbeans.modules.cnd.debugger.common2.debugger.DialogManager;
import org.netbeans.modules.cnd.debugger.common2.debugger.actions.AttachPanel;
import org.netbeans.modules.cnd.debugger.common2.debugger.api.EngineType;
import org.netbeans.modules.cnd.debugger.common2.debugger.spi.AttachPanelFactory;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 */
@ServiceProvider(service = AttachPanelFactory.class, position = 100)
public class AttachToProcessPanelFactory extends AttachPanelFactory{

    @Override
    public boolean supports(EngineType debuggerType) {
        return true;
    }

    @Override
    public AttachPanel create(DialogManager dialogManager, JButton okButton, EngineType debuggerType) {
        return AttachToProcessTopComponent.getInstance(dialogManager, okButton, debuggerType);
    }
    
}