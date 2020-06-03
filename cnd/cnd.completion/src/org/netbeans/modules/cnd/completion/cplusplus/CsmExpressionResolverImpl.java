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
package org.netbeans.modules.cnd.completion.cplusplus;

import java.util.Collection;
import java.util.List;
import org.netbeans.modules.cnd.api.model.CsmInstantiation;
import org.netbeans.modules.cnd.api.model.CsmObject;
import org.netbeans.modules.cnd.api.model.CsmOffsetable;
import org.netbeans.modules.cnd.completion.cplusplus.ext.CsmCompletionQuery;
import org.netbeans.modules.cnd.completion.csm.CompletionResolver;
import org.netbeans.modules.cnd.spi.model.services.CsmExpressionResolverImplementation;
import static org.netbeans.modules.cnd.api.model.services.CsmExpressionResolver.ResolvedTypeHandler;

/**
 *
 */
@org.openide.util.lookup.ServiceProvider(service=org.netbeans.modules.cnd.spi.model.services.CsmExpressionResolverImplementation.class)
public final class CsmExpressionResolverImpl implements CsmExpressionResolverImplementation {

    @Override
    public Collection<CsmObject> resolveObjects(CsmOffsetable expression, List<CsmInstantiation> instantiations) {
        CsmCompletionQuery query = getCompletionQuery(expression);
        Collection<CsmObject> objects = query.queryObjects(expression, instantiations);
        return objects;
    }

    @Override
    public void resolveType(CsmOffsetable expression, List<CsmInstantiation> instantiations, ResolvedTypeHandler task) {
        CsmCompletionQuery query = getCompletionQuery(expression);
        query.queryType(expression, instantiations, task);
    }    
    
    private static CsmCompletionQuery getCompletionQuery(CsmOffsetable expression) {
        return CsmCompletionProvider.createCompletionResolver(expression.getContainingFile(), CompletionResolver.QueryScope.GLOBAL_QUERY, null);
    }        
}