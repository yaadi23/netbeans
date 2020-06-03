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
package org.netbeans.modules.cnd.qnavigator.navigator;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.modules.cnd.api.model.CsmFile;
import org.netbeans.modules.cnd.api.model.CsmFunction;
import org.netbeans.modules.cnd.api.model.CsmFunctionDefinition;
import org.netbeans.modules.cnd.api.model.CsmInclude;
import org.netbeans.modules.cnd.api.model.CsmOffsetable;
import org.netbeans.modules.cnd.api.model.util.CsmKindUtilities;
import org.netbeans.modules.cnd.modelutil.CsmUtilities;

/**
 *
 */
public class GoToDefinitionAction extends AbstractAction {

    private final CsmOffsetable csmObject;

    public GoToDefinitionAction(String name, CsmOffsetable csmObject) {
        this.csmObject = csmObject;
        putValue(Action.NAME, name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (CsmKindUtilities.isInclude(csmObject)) {
            CsmFile includeFile = ((CsmInclude)csmObject).getIncludeFile();
            if (includeFile != null) {
                CsmUtilities.openSource(includeFile, 1, 1);
            }
        } else if (CsmKindUtilities.isFunctionDefinition(csmObject)) {
            CsmFunctionDefinition definition = (CsmFunctionDefinition)csmObject;
            CsmFunction declaration = definition.getDeclaration();
            if (declaration != null && declaration != definition) {
                CsmUtilities.openSource(declaration);
            }
        } else if (CsmKindUtilities.isFunctionDeclaration(csmObject)) {
            CsmFunction declaration = (CsmFunction)csmObject;
            CsmFunctionDefinition definition = declaration.getDefinition();
            if (definition != null && definition != declaration) {
                CsmUtilities.openSource(definition);
            }
        }
    }
}