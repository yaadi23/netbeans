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


package org.netbeans.modules.cnd.debugger.common2.debugger.breakpoints.props;

import org.netbeans.modules.cnd.debugger.common2.utils.props.PropertyOwnerSupport;
import org.netbeans.modules.cnd.debugger.common2.utils.props.EnumProperty;
import org.netbeans.modules.cnd.debugger.common2.values.AccessBA;

public final class AccessBAProperty extends EnumProperty {
    
    private AccessBA value = AccessBA.AFTER;

    public AccessBAProperty(PropertyOwnerSupport po,
			    String name, String key, boolean ro, 
			  AccessBA initialValue) {
	super(po, name, key, ro);
	value = initialValue;
    }

    // interface Property
    @Override
    protected final void setFromStringImpl(String s) {
	value = AccessBA.byTag(s);
    }

    // interface EnumProperty
    @Override
    protected final void setFromNameImpl(String s) {
	value = AccessBA.valueOf(s);
    }

    // interface Property
    @Override
    public final String toString() {
	if (value != null)
	    return value.toString();
	else
	    return null;
    } 
    
    // interface Property
    @Override
    protected final void setFromObjectImpl(Object o) {
	value = (AccessBA) o;
    }

    // interface Property
    @Override
    public final Object getAsObject() {
	return value;
    }

    public final void set(AccessBA a) {
	value = a;
	setDirty();
    }

    public final AccessBA get() {
	return value;
    }
}
