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

package org.netbeans.modules.cnd.highlight.error;

import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.errorstripe.UpToDateStatus;
import org.netbeans.spi.editor.errorstripe.UpToDateStatusProvider;

/**
 *org.netbeans.modules.cnd.highlight.CppUpToDateStatusProvider
 */
public class CppUpToDateStatusProvider extends UpToDateStatusProvider {

    private UpToDateStatus current;
    private static final boolean TRACE = Boolean.getBoolean("cnd.uptodate.trace");

    public CppUpToDateStatusProvider() {
        current = UpToDateStatus.UP_TO_DATE_DIRTY;
    }
    
    @Override
    public UpToDateStatus getUpToDate() {
        if( TRACE ) System.err.printf("getUpToDate -> %s\n", current);
        return current;
    }

    //package
    synchronized  void setUpToDate(UpToDateStatus status) {
        if( TRACE ) System.err.printf("setUpToDate: %s -> %s\n", current, status);
        if (current != status) {
            firePropertyChange(PROP_UP_TO_DATE, current, status);
            current = status;
        }
    }

    public boolean isValid() {
        return true;
    }
    
    public static synchronized CppUpToDateStatusProvider get(BaseDocument doc) {
        if (doc == null) {
            return null;
        }
        
        CppUpToDateStatusProvider provider = (CppUpToDateStatusProvider) doc.getProperty(CppUpToDateStatusProvider.class);

        if (provider == null) {
            doc.putProperty(CppUpToDateStatusProvider.class, provider = new CppUpToDateStatusProvider());
        }

        return provider;
    }

}