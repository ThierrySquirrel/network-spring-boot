/**
 * Copyright 2026/6/2 ThierrySquirrel
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package io.github.thierrysquirrel.network.intercept;


import io.github.thierrysquirrel.network.core.domain.BodyDomain;
import io.github.thierrysquirrel.network.core.http.builder.UniformResourceLocatorBuilder;
import io.github.thierrysquirrel.network.core.http.factory.RequestBuilderFactory;
import io.github.thierrysquirrel.network.core.http.factory.UniformResourceLocatorBuilderFactory;
import io.github.thierrysquirrel.network.core.http.factory.execution.RequestFactoryExecution;
import io.github.thierrysquirrel.network.core.http.strategy.RequestBuilderStrategy;
import io.github.thierrysquirrel.network.error.NetworkException;
import io.github.thierrysquirrel.network.loading.NetworkLoading;
import okhttp3.Request;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: InterceptRequest
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class InterceptRequest {
    private InterceptRequest() {
    }

    public static <T extends Annotation> Object intercept(NetworkLoading networkLoading, Object[] args, Parameter[] params, T annotation, Class<?> resultType) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, NetworkException {
        Map<String, String> headers = new HashMap<>();
        UniformResourceLocatorBuilder uriBuilder = UniformResourceLocatorBuilderFactory.create(annotation);
        Map<String, File> fileMap = new HashMap<>();
        BodyDomain bodyDomain = new BodyDomain();
        for (int i = 0; i < params.length; i++) {
            RequestBuilderStrategy.builder(uriBuilder, headers, fileMap, bodyDomain, params[i], args[i]);
        }
        if (fileMap.isEmpty()) {
            fileMap = null;
        }
        Request request = RequestBuilderFactory.createRequest(annotation, uriBuilder.builder(), headers, fileMap, bodyDomain.getBody());
        return RequestFactoryExecution.execute(networkLoading, request, resultType);
    }
}
