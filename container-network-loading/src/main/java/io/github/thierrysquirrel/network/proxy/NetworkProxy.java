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
package io.github.thierrysquirrel.network.proxy;

import io.github.thierrysquirrel.network.annotation.DeleteRequest;
import io.github.thierrysquirrel.network.annotation.GetRequest;
import io.github.thierrysquirrel.network.annotation.PostRequest;
import io.github.thierrysquirrel.network.annotation.PutRequest;
import io.github.thierrysquirrel.network.intercept.InterceptRequest;
import io.github.thierrysquirrel.network.loading.NetworkLoading;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * ClassName: NetworkProxy
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class NetworkProxy implements InvocationHandler {
    private NetworkLoading networkLoading;

    public NetworkProxy(NetworkLoading networkLoading) {
        this.networkLoading = networkLoading;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        GetRequest getRequest = method.getAnnotation(GetRequest.class);
        if (!Objects.isNull(getRequest)) {
            return InterceptRequest.intercept(networkLoading, args, method.getParameters(), getRequest, method.getReturnType());
        }

        PostRequest postRequest = method.getAnnotation(PostRequest.class);
        if (!Objects.isNull(postRequest)) {
            return InterceptRequest.intercept(networkLoading, args, method.getParameters(), postRequest, method.getReturnType());
        }

        PutRequest putRequest = method.getAnnotation(PutRequest.class);
        if (!Objects.isNull(putRequest)) {
            return InterceptRequest.intercept(networkLoading, args, method.getParameters(), putRequest, method.getReturnType());
        }

        DeleteRequest deleteRequest = method.getAnnotation(DeleteRequest.class);
        if (!Objects.isNull(deleteRequest)) {
            return InterceptRequest.intercept(networkLoading, args, method.getParameters(), deleteRequest, method.getReturnType());
        }

        return method.invoke(proxy, args);
    }

    public NetworkLoading getNetworkLoading() {
        return networkLoading;
    }

    public void setNetworkLoading(NetworkLoading networkLoading) {
        this.networkLoading = networkLoading;
    }

    @Override
    public String toString() {
        return "NetworkProxy{" +
                "networkLoading=" + networkLoading +
                '}';
    }
}
